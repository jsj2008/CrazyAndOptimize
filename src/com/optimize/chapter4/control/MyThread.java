package com.optimize.chapter4.control;

public class MyThread extends Thread {

	private volatile boolean stop = false;

	public void stopMe() {
		stop = true;
	}

	public void run() {
		int i = 0;
		while (!stop) {
			i++;
		}
		System.out.println(i);
		System.out.println("Stop Thread");
	}

	public static void main(String[] args) throws InterruptedException {
		MyThread t = new MyThread();
		t.start();
		Thread.sleep(1000);
		t.stopMe();
		Thread.sleep(1000);
	}
}
