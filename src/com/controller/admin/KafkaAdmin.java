package com.controller.admin;
import org.apache.kafka.clients.admin.*;
import java.util.*;
import java.util.concurrent.ExecutionException;
public class KafkaAdmin {
	private AdminClient admin;
	private Properties props;
	private ListTopicsOptions options;
	
	//Constructor - to connect to AdminAPI
	public KafkaAdmin(String kafkaServer)
	{
		this.props = new Properties();
		props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer);
		this.admin = AdminClient.create(props);
		this.options = new ListTopicsOptions();
	}
	//Destructor - to release AdminAPI connection
	public void finalize() throws Throwable
	{
		this.admin.close();
	}
	public void close()
	{
		this.admin.close();
	}	
	public Set<String> listTopics()
	{
		ListTopicsResult topics = this.admin.listTopics(this.options);
		Set<String> currentTopicList = new HashSet<String>();
		try {
			currentTopicList = topics.names().get();
			return currentTopicList;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return currentTopicList;
	}
	public Set<String> listTopics(ListTopicsOptions options)
	{
		this.options = options;
		return this.listTopics();
	}
	
	public boolean deleteTopics(Collection<String> topics)
	{
		try {
			DeleteTopicsResult deleteStatus = this.admin.deleteTopics(topics);
			deleteStatus.all();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public boolean createTopics(Collection<String> topics, int numPartitions, short replicationFactor)
	{
		try {
			Set<NewTopic> newTopics = new HashSet<NewTopic>();
			for(String topic: topics)
			{
				NewTopic _topic = new NewTopic(topic, numPartitions, replicationFactor);
				newTopics.add(_topic);
			}
			CreateTopicsResult createStatus = this.admin.createTopics(newTopics);
			createStatus.all();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
