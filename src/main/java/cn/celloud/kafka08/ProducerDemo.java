package cn.celloud.kafka08;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

public class ProducerDemo {
	public static void main(String[] args) {
		//1.读取配置文件信息
		Properties properties = new Properties();
		try {
			properties.load(ProducerDemo.class.getClassLoader().getResourceAsStream("producer.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		//2.创建生产者对象
		Producer<String, String> producer = new Producer<String, String>(new ProducerConfig(properties));
		ArrayList<KeyedMessage<String, String>> list = new ArrayList<KeyedMessage<String, String>>();
		String topic = "gaotianye_test3";
		//3.下面的key要和生产者的key一致
		for(int i = 1;i<=1000;i++){
			int j = 0;
			if(i%3==0){
				j = 3;
			}else if(i%3==1){
				j = 1;
			}else if(i%3==2){
				j = 2;
			}
			KeyedMessage<String, String> message = new KeyedMessage<String, String>(topic,"key"+j,"ggg"+i);
			list.add(message);
		}
		//4.发送一个
//		producer.send(message1);
		//5.发送多个
		producer.send(list);
		//6.关闭
		producer.close();
	}
}
