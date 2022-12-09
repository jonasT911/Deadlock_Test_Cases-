package SplitSync;

public class SplitSync implements Runnable {
	static class Resource {
		public int x;
	}

	static Resource resource = new Resource();

	public static void main(String[] args) {
		new SplitSync();
		new SplitSync();
	}

	public SplitSync() {
		new Thread(this).start();
	}

	/** increments resource.x */
	public void run() {
		int y;
		synchronized (resource) { // A
			y = resource.x;
		}
		synchronized (resource) { // B
// invariant: (resource.x == y)
			resource.x = y + 1;
		}
	}
}