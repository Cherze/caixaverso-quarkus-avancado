package caixa.verso.service;

import io.quarkus.oidc.IdToken;
import io.quarkus.oidc.UserInfo;
import io.quarkus.security.identity.SecurityIdentity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import org.eclipse.microprofile.jwt.JsonWebToken;
import java.util.Set;

@ApplicationScoped
public class TokenService {

    @Inject
    @IdToken
    JsonWebToken idToken;  // Para autenticação

    @Inject
    JsonWebToken accessToken;  // Para autorização

    @Inject
    UserInfo userInfo;

    @Inject
    SecurityIdentity securityIdentity;

    // Informações do ID Token (usuário)
    public String getUserId() {
        return idToken.getSubject();
    }

    public String getUserName() {
        return idToken.getClaim("preferred_username");
    }

    public String getUserEmail() {
        return idToken.getClaim("email");
    }

    public String getUserFullName() {
        return idToken.getClaim("name");
    }

    // Informações do Access Token (permissões)
    public Set<String> getRoles() {
        return securityIdentity.getRoles();
    }

    public boolean hasRole(String role) {
        return securityIdentity.hasRole(role);
    }

    public boolean canReadProdutos() {
        return hasRole("user") || hasRole("admin");
    }

    public boolean canWriteProdutos() {
        return hasRole("admin");
    }

    // Validação de scopes do Access Token
    public boolean hasScope(String scope) {
        return accessToken.getGroups().contains(scope) ||
                accessToken.getClaim("scope").toString().contains(scope);
    }

    public String getTokenIssuer() {
        return accessToken.getIssuer();
    }

    public boolean isTokenValid() {
        return accessToken.getExpirationTime() > System.currentTimeMillis() / 1000;
    }
}
