package caixa.verso.cache;

import io.quarkus.cache.CacheKeyGenerator;
import io.quarkus.logging.Log;

import java.lang.reflect.Method;

public class KeyGenerator implements CacheKeyGenerator {

    @Override
    public Object generate(Method method, Object... methodParams) {
        String nomeDoMetodo = method.getName();
        Object page = methodParams[0];
        Object pageSize = methodParams[1];
        String minhaKey = String.format("%s.%s.%s", nomeDoMetodo, page, pageSize);
        Log.info("Gerando minha key customizada: " + minhaKey);
        return minhaKey;
    }
}
