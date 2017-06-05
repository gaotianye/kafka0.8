package cn.celloud.kafka08.djt;

import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.message.MessageAndMetadata;
/**
 * djt--大讲台
 * @author Administrator
 *
 */
public class ConsumerMsgDjt implements Runnable {
	private KafkaStream<byte[], byte[]> kafkaStream;
	private int threadNum;
	
	public ConsumerMsgDjt(KafkaStream<byte[], byte[]> kafkaStream,int threadNum){
		this.kafkaStream = kafkaStream;
		this.threadNum = threadNum;
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
				System.out.println(String.format("thread:%s msg:%s partition:%s offset:%s",
						threadNum,new String(next.message()),next.partition(),next.offset()));
			}else{
				System.out.println(String.format("thread:%s key:%s msg:%s partition:%s offset:%s",
						threadNum,key,new String(next.message()),next.partition(),next.offset()));
			}
		}
	}
}
