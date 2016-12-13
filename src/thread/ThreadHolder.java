package thread;

import java.util.HashSet;

public class ThreadHolder {

	private static HashSet<Thread> threads = new HashSet<Thread>();

	public static void addThread(Thread thread) {
		threads.add(thread);
	}

	public static void removeThread(Thread thread) {
		threads.remove(thread);
	}

	public static void interruptAllThreads() {
		for (Thread thread : threads) {
			thread.interrupt();
		}
		threads.clear();
	}

}
