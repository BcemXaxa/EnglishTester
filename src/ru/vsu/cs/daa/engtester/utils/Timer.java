package ru.vsu.cs.daa.engtester.utils;

public class Timer {
	private long start;
	private long end;

	public void start() {
		start = System.nanoTime();
	}

	public void end() {
		end = System.nanoTime();
	}

	public double durationSeconds() {
		return (end - start) / 1000000000.;
	}

	public double durationMilliseconds() {
		return (end - start) / 1000000.;
	}
	public double durationMicroseconds() {
		return (end - start) / 1000.;
	}

	public long durationNanoseconds() {
		return end - start;
	}

	public double durationMinutes() {
		return durationSeconds() / 60;
	}
}
