package cn.celloud.kafka08.stormProject.utils;

public class SleepUtils {
	public static void sleep(long time){
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
