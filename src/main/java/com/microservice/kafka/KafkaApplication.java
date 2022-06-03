package com.microservice.kafka;

import java.time.Duration;
import java.util.Arrays;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StoreQueryParameters;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Grouped;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.kafka.streams.state.QueryableStoreType;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.StreamsBuilderFactoryBean;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.javafaker.Faker;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@SpringBootApplication
//@EnableKafkaStreams
public class KafkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaApplication.class, args);
	}
	
	/*@RequiredArgsConstructor
	@Component
	class Producer{
		private final KafkaTemplate<Integer, String> template;
		Faker faker;
		
		@EventListener(ApplicationStartedEvent.class)
		public void generate() {
			faker = Faker.instance();
			final Flux<Long> interval = Flux.interval(Duration.ofMillis(1_000));
			
			final Flux<String> quotes = Flux.fromStream(Stream.generate(new Supplier<String>() {
				@Override
				public String get() {
					// TODO Auto-generated method stub
					return faker.hobbit().quote();
				}
			}));
			
			Flux.zip(interval, quotes)
				.map(it -> template.send("hobbit", faker.random().nextInt(42), it.getT2())).blockLast();
		}
		
	}*/
	
	/*@Component
	class Consumer{
		
		@KafkaListener(topics={"streams-wordcount-output"}, groupId="spring-boot-kafka")
		public void consume(ConsumerRecord<String, Long> record)
		{
			System.out.println("received = " + record.value() + " con key " + record.key());
		}
	}*/
	
	//Creando topics(temas)
	@Bean
	NewTopic transferWallet() {
		return TopicBuilder.name("transferWallet").partitions(15).replicas(3).build();
	}
	
	@Bean
	NewTopic detailclient() {
		return TopicBuilder.name("detailclient").partitions(15).replicas(3).build();
	}
	
	@Bean
	NewTopic client() {
		return TopicBuilder.name("client").partitions(15).replicas(3).build();
	}
	
	@Bean
	NewTopic product() {
		return TopicBuilder.name("product").partitions(15).replicas(3).build();
	}
	
	@Bean
	NewTopic movements() {
		return TopicBuilder.name("movements").partitions(15).replicas(3).build();
	}
	
	@Bean
	NewTopic hobbit2() {
		return TopicBuilder.name("hobbit2").partitions(15).replicas(3).build();
	}
	
	@Bean
	NewTopic counts()
	{
		return TopicBuilder.name("streams-wordcount-output").partitions(6).replicas(3).build();
	}
	
	/*@Component
	class Processor
	{
		
		@Autowired
		public void process(StreamsBuilder builder)
		{
			
			final Serde<Integer> integerSerde = Serdes.Integer();
			final Serde<String> stringSerde = Serdes.String();
			final Serde<Long> longSerde = Serdes.Long();
			
			KStream<Integer, String> textLines = builder
					.stream("hobbit", Consumed.with(integerSerde, stringSerde));
			
			KTable<String, Long> wordCounts = textLines
					.flatMapValues(value -> Arrays.asList(value.toLowerCase().split("\\W+")))
					.groupBy((key, value) -> value, Grouped.with(stringSerde, stringSerde))
					.count(Materialized.as("counts"));
			
			wordCounts.toStream().to("streams-wordcount-output", Produced.with(stringSerde, longSerde));
						
		}
		
	}*/
	
	/*@RestController
	@RequiredArgsConstructor
	class RestService
	{
		private final KafkaTemplate<Integer, String> template;
		private final StreamsBuilderFactoryBean factoryBean;
		
		@GetMapping("/count/{word}")
		public Long getCount(@PathVariable String word)
		{
			final KafkaStreams kafkaStreams = factoryBean.getKafkaStreams();
			final ReadOnlyKeyValueStore<String, Long> counts = kafkaStreams
					.store(StoreQueryParameters.fromNameAndType("counts", QueryableStoreTypes.keyValueStore()));
			return counts.get(word);
		}
		
		@GetMapping("/publish/{message}")
		public void publish(@PathVariable String message)
		{
			log.info("metodo publish: "+message);

			template.send("streams-wordcount-output", 45, message);
		}
		
	}*/	

}
