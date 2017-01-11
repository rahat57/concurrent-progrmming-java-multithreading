package com.tut.waitnotify;

public class ProducerConsumer {

	private static int[] buffer;
	private static int count;
	private static Object lock = new Object();

	static class Produuer {
		void Producer() {
			synchronized (lock) {
				if (isFull(buffer)) {
					try {
						lock.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				buffer[count++] = 1;
				lock.notify();
			}
		}

	}

	static class Consumer {
		void Consumer() {
			synchronized (lock) {

				if (isEmpty(buffer)) {
					try {
						lock.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				buffer[--count] = 0;
				lock.notify();
			}
		}

	}

	static boolean isFull(int[] buffer) {
		return count == buffer.length;
	}

	static boolean isEmpty(int[] buffer) {
		return count == 0;
	}

	public static void main(String[] args) throws InterruptedException {

		buffer = new int[10];
		count = 0;

		Produuer producer = new Produuer();
		Consumer consumer = new Consumer();

		Runnable producertask = () -> {
			for (int i = 0; i < 50; i++) {
				producer.Producer();
			}
			System.out.println("Done Producing ");
		};

		Runnable consumertask = () -> {
			for (int i = 0; i < 45; i++) {
				consumer.Consumer();
			}
			System.out.println("Done Consuming ");
		};

		Thread consumerThread = new Thread(consumertask);
		Thread producerThread = new Thread(producertask);

		consumerThread.start();
		producerThread.start();
		
		consumerThread.join();
		producerThread.join();
		

		System.out.println("Data in the buffer " + count);
	}

}
