package cn.celloud.kafka08.stormProject;

import java.util.Date;
import java.util.Properties;
import java.util.Random;

import cn.celloud.kafka08.stormProject.utils.DateUtils;
import cn.celloud.kafka08.stormProject.utils.SleepUtils;
import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

public class KafkaProducer extends Thread{
	
	private final kafka.javaapi.producer.Producer<Integer,String> producer;
	private final String topic;
	private final Properties props = new Properties();
	
	public KafkaProducer(String topic){
		props.put("metadata.broker.list", Constant.KAFKA_CONNECT);
		props.put("serializer.class", Constant.SERIALIZER_CLASS);
		props.put("num.partitions", Constant.NUM_PARTITIONS);
		producer = new Producer<Integer, String>(new ProducerConfig(props));
		this.topic = topic;
	}
	
	@Override
	public void run() {
		Random random = new Random();
		String[] order_amt = {"10.10","20.10","50.20","70.50","15.50",
				"25.50","30.50","55.10","80.50","20.60",
				"89.50","90.80"};
		String[] area_id = {"1","2","3","4","5"};
		
		int i = 0;
		
		while(true){
//			if(i==10){
//				break;
//			}
			i++;
			String messageStr = i+"\t"+order_amt[random.nextInt(12)]+"\t"+
							DateUtils.formatTime(new Date())+"\t"+area_id[random.nextInt(5)];
			System.out.println("producer:"+messageStr);
			KeyedMessage<Integer, String> message = new KeyedMessage<Integer, String>(topic,messageStr);
			producer.send(message);
			SleepUtils.sleep(1000);
		}
	}
	
	public static void main(String[] args) {
		KafkaProducer producerThread = new KafkaProducer(Constant.TOPIC);
		producerThread.start();
	}
}
