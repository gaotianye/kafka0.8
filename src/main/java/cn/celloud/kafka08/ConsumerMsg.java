package cn.celloud.kafka08;

import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.message.MessageAndMetadata;

public class ConsumerMsg implements Runnable {
	private KafkaStream<byte[], byte[]> kafkaStream;
	
	public ConsumerMsg(KafkaStream<byte[], byte[]> kafkaStream){
		this.kafkaStream = kafkaStream;
	}
	
	public void run() {
		ConsumerIterator<byte[], byte[]> iterator = kafkaStream.iterator();
		while(iterator.hasNext()){
			MessageAndMetadata<byte[], byte[]> next = iterator.next();
			String key = "";
			if(next.key()!=null){
				key = new String(next.key());
			}
			if(key.isEmpty()){
				System.out.println(String.format("msg:%s partition:%s offset:%s",
						new String(next.message()),next.partition(),next.offset()));
			}else{
				System.out.println(String.format("key:%s msg:%s partition:%s offset:%s",
						key,new String(next.message()),next.partition(),next.offset()));
			}
		}
	}
}
