package thread;

import java.util.concurrent.CopyOnWriteArrayList;

public class ThreadHolder {

	private static CopyOnWriteArrayList<Thread> threads = new CopyOnWriteArrayList<Thread>();

	public static CopyOnWriteArrayList<Thread> getThreads() {
		return threads;
	}

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
		threads = new CopyOnWriteArrayList<Thread>();
	}

}
