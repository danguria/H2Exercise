package utils;

public class Debug {
	public static void log(String msg) {
		System.out.println("[" + Thread.currentThread().getId() + "]" + msg);
	}
	public static void logTitle(String msg) {
		log("===============================================");
		log(msg);
		log("===============================================");
	}

}
