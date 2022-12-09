package DiningPhilosphers;

import java.util.concurrent.Semaphore;

class PhilosopherDeadlock implements Runnable {
	private int i; // which philosopher
	private static int N; // # of philosophers
	private static Semaphore[] fork;
	private static PhilosopherDeadlock[] philosopher;

	public static void main(String[] args) {
		N = Integer.parseInt(args[0]);
		fork = new Semaphore[N];
		philosopher = new PhilosopherDeadlock[N];
		for (int i = 0; i < N; i++) {
			fork[i] = new Semaphore(1);
			philosopher[i] = new PhilosopherDeadlock(i);
		}
		for (int i = 0; i < N; i++)
			new Thread(philosopher[i]).start();
	}

	public PhilosopherDeadlock(int num) {
		i = num;
	}

	public void run() {
		for (;;) {
			take_forks();
			eat();
			put_forks();
			think();
		}
	}

	void take_forks() {
		fork[i].down(i);
		fork[(i + 1) % N].down(i);
	}

	void put_forks() {
		fork[i].up(i);
		fork[(i + 1) % N].up(i);
	}
}
