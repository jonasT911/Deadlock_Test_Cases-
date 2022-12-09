package SharedBuffer;

public class BufferWorks {
	static final int ITEMS_PRODUCED = 2;

	static class Producer implements Runnable {
		private Buffer buffer;

		public Producer(Buffer b, String n) {
			buffer = b;
		}

		public void run() {
			try {
				for (int i = 0; i < ITEMS_PRODUCED; i++) {
					String name="Blah";
					buffer.enq(name);
				}
			} catch (InterruptedException i) {
				System.err.println(i);
			}
		}
	}

	static class Consumer implements Runnable {
		private Buffer buffer;

		public Consumer(Buffer b) {
			buffer = b;
		}

		public void run() {
			try {
				for (int i = 0; i < ITEMS_PRODUCED * 2; i++) { // while (true)
					buffer.deq();
				}
			} catch (InterruptedException i) {
				System.err.println(i);
			}
		}
	}

	static class Buffer { // shared bounded buffer
		static final int CAPACITY = 1;
// Need extra slot to tell full from empty
		static final int BUFSIZE = CAPACITY + 1;
		private int first, last;
		private Object[] els;

		public Buffer() {
			first = 0;
			last = 0;
			els = new Object[BUFSIZE];
		}

		public synchronized void enq(Object x) throws InterruptedException {
			while ((last + 1) % BUFSIZE == first)
				this.wait();
			els[last] = x;
			last = (last + 1) % BUFSIZE;
			this.notifyAll();
		}

		public synchronized Object deq() throws InterruptedException {
			while (first == last)
				this.wait();
			Object val = els[first];
			first = (first + 1) % BUFSIZE;
			this.notifyAll();
			return val;
		}
	}
}
