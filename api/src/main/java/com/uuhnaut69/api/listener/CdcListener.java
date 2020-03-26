package com.uuhnaut69.api.listener;

import com.uuhnaut69.api.service.product.ProductService;
import io.debezium.config.Configuration;
import io.debezium.data.Envelope;
import io.debezium.embedded.EmbeddedEngine;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.kafka.connect.data.Field;
import org.apache.kafka.connect.data.Struct;
import org.apache.kafka.connect.source.SourceRecord;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import static com.uuhnaut69.api.config.DebeziumConnectorConfig.APP_NAME;
import static com.uuhnaut69.api.config.DebeziumConnectorConfig.PRODUCT_TABLE;
import static io.debezium.data.Envelope.FieldName.*;
import static java.util.stream.Collectors.toMap;

/**
 * @author uuhnaut
 * @project demo
 */
@Slf4j
@Component
public class CdcListener {

    private final Executor executor = Executors.newSingleThreadExecutor();

    private final EmbeddedEngine engine;

    private final ProductService productService;

    public CdcListener(Configuration connector, ProductService productService) {
        this.engine = EmbeddedEngine.create().using(connector).notifying(this::handleEvent).build();
        this.productService = productService;
    }

    @PostConstruct
    private void start() {
        this.executor.execute(engine);
    }

    @PreDestroy
    private void stop() {
        if (this.engine != null) {
            this.engine.stop();
        }
    }

    private void handleEvent(SourceRecord sourceRecord) {

        /*
         Omit schema change events
         */
        if (sourceRecord.topic().equals(APP_NAME)) {
            return;
        }

        Struct sourceRecordValue = (Struct) sourceRecord.value();
        if (sourceRecordValue != null) {
            Envelope.Operation operation = Envelope.Operation.forCode((String) sourceRecordValue.get(OPERATION));
            Map<String, Object> message;
            String record = AFTER;

            if (operation == Envelope.Operation.DELETE) {
                record = BEFORE;
            }

            Struct struct = (Struct) sourceRecordValue.get(record);
            message = struct.schema().fields().stream().map(Field::name)
                    .filter(fieldName -> struct.get(fieldName) != null)
                    .map(fieldName -> Pair.of(fieldName, struct.get(fieldName)))
                    .collect(toMap(Pair::getKey, Pair::getValue));

            Struct extract = (Struct) sourceRecordValue.get(SOURCE);
            String tableChange = extract.get("table").toString();

            if (tableChange.equals(PRODUCT_TABLE)) {
                this.productService.maintainReadModel(message, operation);
                log.info("Product Data Changed: {} with Operation: {}", message, operation.name());
            }
        }
    }
}
