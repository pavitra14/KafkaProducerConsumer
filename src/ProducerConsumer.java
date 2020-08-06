// Base Packages
import com.controller.producer.*;
import com.controller.consumer.*;
import com.controller.admin.*;

import java.time.Duration;
// Utils
import java.util.*;

// Producer and Consumer APIs
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.Producer;
// Record(s) APIs
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.ConsumerRecord;


public class ProducerConsumer {	
	public static Scanner sc;
	public static void main(String[] args)
	{
		sc = new Scanner(System.in);
		println("Simple Kafka ProducerConsumer Example");
		println("-------------------------------------");
		println("Enter Service details: ");
		println("\t [1] Producer");
		println("\t [2] Consumer");
		println("\t [3] Kafka Admin Services");
		println("\t [0] Exit");
		print("Your Choice: ");
		int choice = sc.nextInt();
		//To fix nextLine null issue before switch-case in java scanner
		sc.nextLine();
		clearScreen();
		switch(choice) {
			case 1:
				runProducer();
				clearScreen();
				main(null);
				break;
			case 2:
				runConsumer();
				clearScreen();
				main(null);
				break;
			case 3:
				kafkaAdmin();
			default:
				System.exit(choice);
		}
				
	}
	
	public static void kafkaAdmin()
	{
		try {
			KafkaAdmin admin = new KafkaAdmin("localhost:9092");
			println("-------------------------------------");
			println("Enter Admin Services:");
			println("\t [1] List Topics");
			println("\t [2] Delete Topic");
			println("\t [3] Create Topic");
			println("\t [0] For Main Menu");
			int choice = sc.nextInt();
			//To fix nextLine null issue before switch-case in java scanner
			sc.nextLine();
			switch(choice) {
				case 1:
					Set<String> topicList = admin.listTopics();
					println("Total Topics : " + topicList.size());
					for(String topicName: admin.listTopics())
					{
						println("topic: \t" + topicName);
					}
					println("Press any key to continue");
					sc.nextLine();
					break;
				case 2:
					print("Enter Topic name to delete(comma seperated if multipe): ");
					String topicN = sc.nextLine();
					topicN = topicN.trim();
					Set<String> topics = new HashSet<String>(Arrays.asList(topicN.split(",")));
					if(admin.deleteTopics(topics))
					{
						println("Topics deleted.");
					} else {
						println("Topics could not be deleted.");
					}
					println("Press any key to continue");
					sc.nextLine();
					break;
				case 3:
					print("Enter Topic(s) to create(comma seperated if multiple): ");
					String topicNew = sc.nextLine();
					print("Enter no of Partitions: ");
					int numPartitions = sc.nextInt();
					print("Enter replication factor: ");
					short replicationFactor = sc.nextShort();
					
					topicNew = topicNew.trim();
					Set<String> newTopics = new HashSet<String>(Arrays.asList(topicNew.split(",")));
					if(admin.createTopics(newTopics, numPartitions, replicationFactor))
					{
						println("Topics created.");
					} else {
						println("Topics could not be created.");
					}
					break;
				case 0:
					clearScreen();
					main(null);
					break;
				default:
					System.exit(choice);
			}
			clearScreen();
			kafkaAdmin();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public static void runConsumer()
	{
		print("Enter Topic Name (Will be created if doesn't exist): ");
		String topicName = sc.nextLine();
		println("-------------------------------------");
		ConsumerConfiguration config = new ConsumerConfiguration();
		println("Opening KafkaConsumerAPI for topic: " + topicName);
		SimpleConsumer simpleConsumer = new SimpleConsumer(config);
		KafkaConsumer<String, String> consumer = simpleConsumer.getConsumer();
		println("Subscribing to topic: " + topicName);
		consumer.subscribe(Arrays.asList(topicName));
		println("Subscribed to topic: " + topicName);
		println("Listening to records on " + topicName);
		while(true)
		{
			ConsumerRecords<String, String> records = consumer.poll(Duration.ZERO);
			// print the offset,key and value for the consumer record
			for (ConsumerRecord<String, String> record : records)
			{
				System.out.printf("offset = %d, key = %s, value = %s\n", record.offset(), record.key(), record.value());
			}
		}
	}
	
	public static void runProducer()
	{
		print("Enter Topic Name (Will be created if doesn't exist): ");
		String topicName = sc.nextLine();
		println("-------------------------------------");
		ProducerConfiguration config = new ProducerConfiguration();
		println("Opening KafkaProducerAPI for topic: " + topicName);
		SimpleProducer simpleProducer = new SimpleProducer(config);
		Producer<String, CustomObject> producer = simpleProducer.getProducer();
		println("Starting Producer Stream [CTRL+C/exit to quit']");
		println("-----------------------------------------------");
		int c = 1;
		loop: while(true)
		{
			print("["+ c + "]:");
			String name = sc.nextLine();
			String email = sc.nextLine();
			if(name.equals("exit") || email.equals("exit"))				
			{
				println("Exiting...");
				break loop;
			}
			CustomObject co = new CustomObject(c, name, email);
			ProducerRecord<String, CustomObject> pr = new ProducerRecord<String, CustomObject>(topicName, co);
			producer.send(pr);
			c++;
		}

		println("-----------------------------------------------");
		println("Stopping producer.");
		producer.close();
		println("Stats:");
		println("\t Total Messages Sent: " + c);
		c=0;
	}
	
	
	/*
	 * MISC UTIL METHODS, Should be nullified before going to production
	 * TODO: Add a Debug Check, nullify if false
	 */
	public static void print(Object o)
	{
		System.out.print(o);
	}
	public static void println(Object o)
	{
		System.out.println(o);
	}
	public static void clearScreen() 
	{  
		for (int i = 0; i < 50; ++i) System.out.println();
	}
}
