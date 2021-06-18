package com.tistory.heowc.blockingqueue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

class LinkedBlockingQueueTests {

	private LinkedBlockingQueue<Integer> queue;

	@BeforeEach
	public void before_init() {
		queue = new LinkedBlockingQueue<>(); // capacity를 지정하지 않으면, Integer.MAX_VALUE 로 지정
	}

	@Test
	void test_offer() throws Exception {
		IntStream.range(0, 10)
				.forEach(value -> queue.offer(value));

		assertThat(queue.remainingCapacity()).isEqualTo(Integer.MAX_VALUE - 10)
				.as("Queue Remain Capacity is ( 2^31 - 11 )");
		queue.clear();
	}

	@Test
	void test_drainToFromMultiThread() {
		CountDownLatch countDownLatch = new CountDownLatch(100);

		ExecutorService putPool = Executors.newFixedThreadPool(2);
		ExecutorService takePool = Executors.newFixedThreadPool(1);

		for (int i = 0; i < 100; i++) {
			putPool.execute(() -> {
				int number = ThreadLocalRandom.current().nextInt(10);
				System.out.println("inserted: " + number);
				queue.offer(number);
				countDownLatch.countDown();
			});
		}

		while (countDownLatch.getCount() != 0 || !queue.isEmpty()) {
			takePool.execute(() -> {
				if (queue.size() >= 10) {
					System.out.println(putPool);
					List<Integer> list = new ArrayList<>();
					queue.drainTo(list, 10);
					System.out.println(Arrays.toString(list.toArray()));
				}
			});
		}

		System.out.println("remain size = " + queue.size());
	}
}
