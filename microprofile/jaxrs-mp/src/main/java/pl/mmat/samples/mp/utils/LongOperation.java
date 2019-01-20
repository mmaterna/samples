package pl.mmat.samples.mp.utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LongOperation {

	private LongOperation() {}
	
	public static void sleep() {
		try {
			long millis = (long) (Math.random() * 2000);
			log.info("sleep random: {}", millis);
			Thread.sleep(millis);
		} catch (InterruptedException e) { }
	}
}
