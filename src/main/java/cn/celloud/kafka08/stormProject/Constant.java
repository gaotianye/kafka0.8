package cn.celloud.kafka08.stormProject;

import java.util.Date;
import java.util.Random;

import cn.celloud.kafka08.stormProject.utils.DateUtils;

public class Constant {
	//topic
	static final String TOPIC = "storm_topic_20170606";
	static final String GROUP_ID = "group_20170606";
	static final String ZK_CONNECT = "master:2181,slave1:2181,slave2:2181";

	public static void main(String[] args) {
//		Random random = new Random();
//		for(int i=0;i<100;i++){
//			System.out.println(random.nextInt(12));
//		}
		String formatTime = DateUtils.formatTime(new Date());
		System.out.println(formatTime);
	}
}
