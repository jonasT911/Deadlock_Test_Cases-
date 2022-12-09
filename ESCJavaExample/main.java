package ESCJavaExample;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thing thing = new Thing();
		int numThreads = 10;

		Thread list[] = new Thread[numThreads];
		for (int i = 0; i < numThreads; i++) {
			Thread t = new Thread(new Tree(thing));
			list[i] = t;
			t.start();
		}

		for (Thread thread : list) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
