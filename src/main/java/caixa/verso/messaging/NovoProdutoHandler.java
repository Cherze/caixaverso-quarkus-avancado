package caixa.verso.messaging;

import io.quarkus.logging.Log;
import io.smallrye.common.annotation.NonBlocking;
import io.smallrye.reactive.messaging.kafka.IncomingKafkaRecord;
import io.vertx.core.json.JsonObject;
import jakarta.enterprise.context.ApplicationScoped;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Message;

import java.util.Optional;
import java.util.concurrent.CompletionStage;

@ApplicationScoped
public class NovoProdutoHandler {

    @Incoming("produto-cadastrado-com-sucesso")
    @Retry(maxRetries = 2, delay = 1000)
    @NonBlocking
    public CompletionStage<Void> consomeNovoProduto(Message<ConsumerRecord<String, String>> mensagem) {
        Log.info("Produto cadastrado com sucesso: " + mensagem.getPayload().value());
        /**Optional<IncomingKafkaRecord> metadata = mensagem.getMetadata(IncomingKafkaRecord.class);

        metadata.ifPresent(m -> {
            Log.info("Partição: " + m.getPartition());
            Log.info("Offset: " + m.getOffset());
            Log.info("Timestamp: " + m.getTimestamp());
        });

        System.out.println("metadata "+metadata);**/

        return mensagem.ack();
    }

    @Incoming("produto-com-erro")
    public CompletionStage<Void> consomeDLQ(Message<JsonObject> mensagem) {
        Log.info("Consumindo produto com erro DLQ: " + mensagem.getPayload());

        return mensagem.ack();
    }


}
