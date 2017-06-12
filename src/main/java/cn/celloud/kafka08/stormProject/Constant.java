package cn.celloud.kafka08.stormProject;

import java.util.Date;

import cn.celloud.kafka08.stormProject.utils.DateUtils;


public class Constant {
	//topic
	static final String ORDER_TOPIC = "storm_order_topic_"+DateUtils.formatDate2(new Date());
	//kafka_connect
	static final String KAFKA_CONNECT = "master:9092,slave1:9092,slave2:9092";
	//serializer_class
	static final String SERIALIZER_CLASS = "kafka.serializer.StringEncoder";
	//num.partitions
	static final String NUM_PARTITIONS = "3";
}
