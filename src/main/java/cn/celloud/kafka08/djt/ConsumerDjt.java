package cn.celloud.kafka08.djt;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

public class ConsumerDjt {
	public static void main(String[] args) {
		//1.读取一个消费者组的配置文件信息（如果有多个消费者组，需要读取多个配置文件）
		Properties properties = new Properties();
		//不用读取配置文件，而是自己手动添加k-v
		properties.put("zookeeper.connect", "master:2181,slave1:2181,slave2:2181");
		properties.put("group.id", "test-consumer-group-20170530-1108");
		//2.创建消费者连接
		ConsumerConnector consumerConn = Consumer.createJavaConsumerConnector(new ConsumerConfig(properties));
		//3.指定消费的topic和该topic的消费者数目
		//key:topic名称  value：同一个消费者组中，几个线程/几个消费者去消费
		HashMap<String, Integer> topicConnMap = new HashMap<String,Integer>();
		String topic = "dajiangtai_test1";
		//创建5个线程的线程池
		int threads = 5;
		ExecutorService executor = Executors.newFixedThreadPool(threads);
		topicConnMap.put(topic,threads);
		//4.获取消费者流list对象（此消费者组中的 消费者对象  去获取topic中的信息）
		//注意1:messageStreams的key指topic，value是一个list
		//注意2:KafkaStream<byte[], byte[]> 中的key和value指的是消息的key 和消息的value
		//注意3:list元素的个数代表着几个消费者线程
		Map<String, List<KafkaStream<byte[], byte[]>>> messageStreams = consumerConn.createMessageStreams(topicConnMap);
		List<KafkaStream<byte[], byte[]>> MsgList = messageStreams.get(topic);
		int threadNum = 0;
		for (KafkaStream<byte[], byte[]> kafkaStream : MsgList) {
			executor.submit(new ConsumerMsgDjt(kafkaStream,threadNum));
			threadNum++;
		}
	}
}
