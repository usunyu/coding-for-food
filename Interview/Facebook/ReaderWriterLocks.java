/*
Read Access   	If no threads are writing, and no threads have requested write access.
Write Access   	If no threads are reading or writing.

http://tutorials.jenkov.com/java-concurrency/read-write-locks.html
 */

public class ReaderWriterLocks {
	private int readers = 0;
	private int writers = 0;
	private int writeRequests = 0;

	public synchronized void lockRead() throws InterruptedException {
		while (writers > 0 || writeRequests > 0) {
			wait();
		}
		readers++;
	}

	public synchronized void unlockRead() {
		readers--;
		notifyAll();
	}

	public synchronized void lockWrite() throws InterruptedException {
		writeRequests++;

		while (readers > 0 || writers > 0) {
			wait();
		}
		writeRequests--;
		writers++;
	}

	public synchronized void unlockWrite() throws InterruptedException {
		writers--;
		notifyAll();
	}
}