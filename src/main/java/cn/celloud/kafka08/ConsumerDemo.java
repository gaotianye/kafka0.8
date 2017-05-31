package cn.celloud.kafka08;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

public class ConsumerDemo {
	public static void main(String[] args) {
		//1.读取一个消费者组的配置文件信息（如果有多个消费者组，需要读取多个配置文件）
		Properties properties = new Properties();
		try {
			properties.load(ConsumerDemo.class.getClassLoader().getResourceAsStream("consumer.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		//2.创建消费者连接
		ConsumerConnector consumerConn = Consumer.createJavaConsumerConnector(new ConsumerConfig(properties));
		//3.指定消费的topic和该topic的消费者数目
		//key:topic名称  value：同一个消费者组中，几个线程/几个消费者去消费
		HashMap<String, Integer> topicConnMap = new HashMap<String,Integer>();
		String topic = "gaotianye_test3";
		topicConnMap.put(topic,2);
		//4.获取消费者流list对象（此消费者组中的 消费者对象  去获取topic中的信息）
		//注意1:messageStreams的key指topic，value是一个list
		//注意2:KafkaStream<byte[], byte[]> 中的key和value指的是消息的key 和消息的value
		//注意3:list元素的个数代表着几个消费者线程
		Map<String, List<KafkaStream<byte[], byte[]>>> messageStreams = consumerConn.createMessageStreams(topicConnMap);
		List<KafkaStream<byte[], byte[]>> MsgList = messageStreams.get(topic);
		for (KafkaStream<byte[], byte[]> kafkaStream : MsgList) {
			//如果数据一直有，第二个迭代就永远不会进来的。所以需要启动多个线程。
			new Thread(new ConsumerMsg(kafkaStream)).start();
		}
	}
}
