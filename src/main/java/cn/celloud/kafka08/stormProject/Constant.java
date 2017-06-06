package cn.celloud.kafka08.stormProject;

public class Constant {
	//topic
	static final String TOPIC = "storm_topic_20170606";
	//kafka_connect
	static final String KAFKA_CONNECT = "master:9092,slave1:9092,slave2:9092";
	//serializer_class
	static final String SERIALIZER_CLASS = "kafka.serializer.StringEncoder";
}
