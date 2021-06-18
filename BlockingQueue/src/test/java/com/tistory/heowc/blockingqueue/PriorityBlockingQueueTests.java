package com.tistory.heowc.blockingqueue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.stream.IntStream;

class PriorityBlockingQueueTests {

	private PriorityBlockingQueue<Integer> queue;

	@BeforeEach
	void before_init() {
		queue = new PriorityBlockingQueue<>();
	}

	@Test
	void test_offer() {
		IntStream.range(0, 10)
				.forEach(action -> queue.offer(new Random().nextInt(100) + 1));

		queue.forEach(action -> System.out.println(queue.poll()));
	}
}
