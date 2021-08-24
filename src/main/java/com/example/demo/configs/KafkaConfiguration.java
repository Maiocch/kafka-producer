package com.example.demo.configs;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

@Configuration
public class KafkaConfiguration {
	
	private final KafkaProperties kafkaProperties;
	
	public KafkaConfiguration(KafkaProperties kafkaProperties) {
		super();
		this.kafkaProperties = kafkaProperties;
	}

	@Bean
	public NewTopic myTopic() {
		return TopicBuilder.name("my_topic").partitions(2).build();
	}
	
	@Bean("kafkaTemplateJson")
	public KafkaTemplate<String, byte[]> kafkaTemplateJson(){
		return new KafkaTemplate<>(producerFctory());
	}
	
	private ProducerFactory<String, byte[]> producerFctory(){
		return new DefaultKafkaProducerFactory<>(producerConfigJson());
	}
	
	private Map<String, Object> producerConfigJson(){
		Map<String, Object> props = new HashMap<String, Object>(kafkaProperties.buildProducerProperties());
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

		props.put(ProducerConfig.LINGER_MS_CONFIG, 3000);
		props.put(ProducerConfig.BATCH_SIZE_CONFIG, 20000);
		props.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, "snappy");

		return props;
	}
	
}
