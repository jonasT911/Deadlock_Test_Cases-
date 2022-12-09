package DeadlockWaitTwo;

public class DeadlockWait implements Runnable {
	static class Lock {
	}

	static Lock a = new Lock();
	static Lock b = new Lock(); // the locks

	public static void main(String[] args) {
		new DeadlockWait("First", true);
		new DeadlockWait("Second", false);
	}

	String name;
	boolean ab; // do we grab them in the order "ab"?

	public DeadlockWait(String name, boolean ab) {
		this.ab = ab;
		this.name = name;
		new Thread(this, name).start();
	}

	public void run() {
		if (ab) {
			synchronized (a) {
				synchronized (b) {
					try {
						b.wait();
					} catch (InterruptedException i) {
						System.out.println(name + " was interrupted!");
					}
				}
			}
		} else {
			synchronized (a) {
			}
			synchronized (b) {
				b.notify();
			}
		}
	}
}