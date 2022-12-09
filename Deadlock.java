public class Deadlock implements Runnable {
	static class Lock {
	}

	static Lock a = new Lock();
	static Lock b = new Lock(); // the locks

	public static void main(String[] args) {
		new Deadlock("First", true);
		new Deadlock("Second", false);
	}

	boolean ab; // do we grab them in the order "ab"?

	public Deadlock(String name, boolean ab) {
		this.ab = ab;
		new Thread(this, name).start();
	}

	public void run() {
		if (ab) {
			synchronized (a) {
				synchronized (b) {
				}
			}
		} else {
			synchronized (b) {
				synchronized (a) {
				}
			}
		}
	}
}