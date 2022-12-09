package DeadlockThree;

public class Deadlock3 implements Runnable {
	static class Lock {
	}

	static Lock a = new Lock();
	static Lock b = new Lock();
	static Lock c = new Lock(); // the locks

	public static void main(String[] args) {
		new Deadlock3("First", 0);
		new Deadlock3("Second", 1);
		new Deadlock3("Third", 2);
	}

	int order; // code indicating lock grabbing order

	public Deadlock3(String name, int order) {
		this.order = order;
		new Thread(this, name).start();
	}

	public void run() {
		if (order == 0) {
			synchronized (a) {
				synchronized (b) {
				}
			}
		} else if (order == 1) {
			synchronized (b) {
				synchronized (c) {
				}
			}
		} else {
			synchronized (c) {
				synchronized (a) {
				}
			}
		}
	}
}