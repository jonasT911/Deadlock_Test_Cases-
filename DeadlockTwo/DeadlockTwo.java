package DeadlockTwo;

public class DeadlockTwo implements Runnable {
	static class Lock {
	}

	static Lock a = new Lock();
	static Lock b = new Lock(); // the locks
	static Lock c = new Lock();
	public static void main(String[] args) {
		for(int i=0;i<100;i++) {
		new DeadlockTwo("First", true);
		new DeadlockTwo("Second", false);
		}
	}

	boolean ab; // do we grab them in the order "ab"?

	public DeadlockTwo(String name, boolean ab) {
		this.ab = ab;
		new Thread(this, name).start();
	}

	public synchronized void run() {
		synchronized(c) {
		if (ab) {
			LockA A=new LockA();
					A.foo(); // recursively calls LockB.bar, obtaining lock on B
		} else {
			LockB B=new LockB();
			B.foo();// recursively calls LockA.bar, obtaining lock on A
		}
	}}

		class LockA{
			public void foo() {
				synchronized(a) {
					LockB B=new LockB();
					B.bar();
				}
			}
			public void bar() {
				synchronized(a) {
					System.out.println("a");
				}
			}
		}
		
		class LockB{
			public void foo() {
				synchronized(b) {
					LockA A=new LockA();
					A.bar();
				}
			}
			public void bar() {
				synchronized(b) {
					System.out.println("b");
				}
			}
		}
}
