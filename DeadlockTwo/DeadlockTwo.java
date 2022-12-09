package DeadlockTwo;

public class DeadlockTwo implements Runnable {
	static class Lock {
	}

	static Lock a = new Lock();
	static Lock b = new Lock(); // the locks

	public static void main(String[] args) {
		new DeadlockTwo("First", true);
		new DeadlockTwo("Second", false);
	}

	boolean ab; // do we grab them in the order "ab"?

	public DeadlockTwo(String name, boolean ab) {
		this.ab = ab;
		new Thread(this, name).start();
	}

	public synchronized void run() {
		if (ab) {
			LockA A=new LockA();
					A.foo(); // recursively calls LockB.bar, obtaining lock on B
		} else {
			LockB B=new LockB();
			B.foo();// recursively calls LockA.bar, obtaining lock on A
		}
	}

		class LockA{
			public void foo() {
				synchronized(a) {
					LockB B=new LockB();
					B.bar();
				}
			}
			public void bar() {
				synchronized(a) {
					
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
					
				}
			}
		}
}
