package ru.vsu.cs.daa.engtester.experimental.utils;

public class Stopwatch {
	public enum Unit {
		NANO,
		MICRO,
		MILLI,
		SECONDS,
		MINUTES
	}
	private long start;
	private long elapsed;
	private boolean running;

	public void start() {
		elapsed = 0;
		running = true;
		start = System.nanoTime();
	}

	public void resume() {
		if (!running) {
			running = true;
			start = System.nanoTime();
		}
	}

	public void stop() {
		if (running) {
			elapsed += System.nanoTime() - start;
			running = false;
		}
	}

	public double getTime(Unit unit) {
		long timeNano;
		if (running) {
			timeNano = elapsed + System.nanoTime() - start;
		} else {
			timeNano = elapsed;
		}

		return switch (unit){
			case NANO -> timeNano;
			case MICRO -> timeNano/1e3;
			case MILLI -> timeNano/1e6;
			case SECONDS -> timeNano/1e9;
			case MINUTES -> timeNano/1e9/60;
		};
	}
}
