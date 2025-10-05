package caixa.verso.messaging;

import caixa.verso.model.Produto;
import io.smallrye.mutiny.Multi;
import io.smallrye.reactive.messaging.kafka.KafkaClientService;
import io.smallrye.reactive.messaging.kafka.KafkaProducer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

@ApplicationScoped

public class KafkaMensageria {

    @Inject
    KafkaClientService kcService;


    @Channel("novo-produto")
    Emitter<NovoProdutoEvento> emissorDeContaCriadaEvento;

    public void enviaMsg(Produto produto) {
        NovoProdutoEvento evento = new NovoProdutoEvento(produto.getId(), produto.getNome(), produto.getDescricao(), produto.getPreco(), produto.getCriadoEm());
        KafkaProducer<String, NovoProdutoEvento> producer = kcService.getProducer("novo-produto");
        producer.runOnSendingThread(p -> {
            p.send(new ProducerRecord<String, NovoProdutoEvento>("novo-produto-topic", evento));
        }).await().indefinitely();

    }
/**
    @Outgoing("novo-produto")
    public Multi<NovoProdutoEvento> geraEvento() {
        AtomicLong counter = new AtomicLong(1);
        return Multi.createFrom()
                .ticks()
                .every(Duration.ofMinutes(5)) // A cada 5 minutos
                .map(item -> new NovoProdutoEvento(
                        counter.getAndIncrement(), // ID sequencial
                        "Produto-" + counter.get(),
                        "Descrição do produto " + counter.get(),
                        Math.random() * 1000, // Preço aleatório
                        LocalDateTime.now()
                ));
    }**/

    public record NovoProdutoEvento(Long id, String nome, String descricao, double preco, LocalDateTime agora) {
    }
}

