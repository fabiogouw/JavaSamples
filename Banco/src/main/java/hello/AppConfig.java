package hello;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.Serializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import java.util.HashMap;
import java.util.Map;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;

@Configuration
public class AppConfig {
	@Bean
	public FonteDados fonteDados() {
		return new FonteDadosEmMemoria();
	}

	@Bean
	public Map<String, Object> producerConfigs() {
	  Map<String, Object> props = new HashMap<>();
	  // list of host:port pairs used for establishing the initial connections to the Kakfa cluster
	  props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "191.235.86.51:9092");
	  props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
	  props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
  
	  return props;
	}
  
	@Bean
	public ProducerFactory<String, String> producerFactory() {
	  return new DefaultKafkaProducerFactory<>(producerConfigs());
	}
  
	@Bean
	public KafkaTemplate<String, String> kafkaTemplate() {
	  return new KafkaTemplate<>(producerFactory());
	}

	@Bean
	public Map<String, Object> consumerConfigs() {
	  Map<String, Object> props = new HashMap<>();
	  // list of host:port pairs used for establishing the initial connections to the Kakfa cluster
	  props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "191.235.86.51:9092");
	  props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "transacoes");
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
	  return props;
	}	

	@Bean
	public ConsumerFactory<String, String> consumerFactory() {
		return new DefaultKafkaConsumerFactory<>(consumerConfigs());
	}

	@Bean
  public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaListenerContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(consumerFactory());
    return factory;
  }
}
