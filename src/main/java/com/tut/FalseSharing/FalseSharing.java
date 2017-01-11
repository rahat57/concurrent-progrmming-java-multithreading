package com.tut.FalseSharing;
public class FalseSharing {

	public static int NUM_THREAD_MAX = 4;
	public final static long ITERATIONS = 50_000_000L;

	private static VolatileLongPadded[] paddedLongs;
	private static volatilelongunpadded[] unPaddedLongs;

	public final static class VolatileLongPadded {

		public long q1, q2, q3, q4, q5, q6;
		public volatile long value = 0L;
		public long q11, q12, q13, q14, q15, q16;
	}

	public final static class volatilelongunpadded {
		public volatile long value = 0L;
	}

	static {

		paddedLongs = new VolatileLongPadded[NUM_THREAD_MAX];
		for (int i = 0; i < paddedLongs.length; i++) {
			paddedLongs[i] = new VolatileLongPadded();
		}

		unPaddedLongs = new volatilelongunpadded[NUM_THREAD_MAX];
		for (int i = 0; i < unPaddedLongs.length; i++) {
			unPaddedLongs[i] = new volatilelongunpadded();

		}
	}

	public static void main(String[] args) throws InterruptedException {

		runBenchmark();
	}

	private static void runBenchmark() throws InterruptedException {

		long being, end;

		for (int n = 1; n <= NUM_THREAD_MAX; n++) {

			Thread[] threads = new Thread[n];

			for (int j = 0; j < threads.length; j++) {
				threads[j] = new Thread(createPaddedRunnable(j));
			}

			being = System.currentTimeMillis();
			for (Thread t : threads) {
				t.start();
			}

			for (Thread t : threads) {
				t.join();
			}

			end = System.currentTimeMillis();
			System.out.println(" Padded # threads "+n+" - T = "+ (end - being)+"ms");

			for (int j = 0; j < threads.length; j++) {
				threads[j] = new Thread(createUnPaddedRunnable(j));
			}

			being = System.currentTimeMillis();
			for (Thread t : threads) {
				t.start();
			}

			for (Thread t : threads) {
				t.join();
			}

			end = System.currentTimeMillis();

			System.out.println("UnPadded # threads  "+n+"  - T = " 	+ (end - being)+"ms\\");
		}

	}
	
	private static Runnable createPaddedRunnable(final int k) {
		
		Runnable paddedTouch = () -> {
			long i = ITERATIONS + 1 ;
			while (0 != --i) {
				paddedLongs[k].value = i;	
			}
		};
	return paddedTouch;
		
	}
	
	private static Runnable createUnPaddedRunnable(final int k) {
		
		Runnable paddedTouch = () -> {
			long i = ITERATIONS + 1 ;
			while (0 != --i) {
				unPaddedLongs[k].value = i;	
			}
		};
	return paddedTouch;
		
	}

}
