package com.tistory.heowc.blockingqueue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

class ArrayBlockingQueueTests {

	private ArrayBlockingQueue<String> queue;

	@BeforeEach
	void before_init() {
		queue = new ArrayBlockingQueue<>(10);
	}

	@Test
	void test_add() throws Exception {
		IntStream.range(0, 11)
				.forEach(value -> {
					try {
						queue.add(String.valueOf(value));
					} catch (IllegalStateException e) {
						e.printStackTrace(); // Queue full
					}
				});

		assertThat(queue.remainingCapacity()).isZero();
	}

	@Test
	void test_offer() throws Exception {
		IntStream.range(0, 11)
				.forEach(value -> {
					boolean isOffer = queue.offer(String.valueOf(value));

					if (!isOffer) {
						System.out.println("Queue full");
					}
				});

		assertThat(queue.remainingCapacity()).isZero();
	}

	@Test
	void test_put() throws Exception {
		IntStream.range(0, 10)
				.forEach(value -> {
					try {
						queue.put(String.valueOf(value));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				});
		// queue 이상으로 넣을 시, wait

		assertThat(queue.remainingCapacity()).isZero();
	}

	@Test
	void test_take() throws Exception {
		IntStream.range(0, 1)
				.forEach(value -> queue.offer(String.valueOf(value)));
		// queue가 빌 때 take 할 경우, wait
		assertThat(queue.take()).isEqualTo("0");
	}

	@Test
	void test_poll() throws Exception {
		assertThat(queue.poll()).isNull();
	}

	@Test
	void test_remove() throws Exception {
		assertThat(queue.remove("1")).isFalse();
	}


	@Test
	void test_drainTo() throws Exception {
		queue.offer("1");
		queue.offer("1");
		queue.offer("2");
		queue.offer("3");
		queue.offer("4");
		queue.offer("5");

		Set<String> removeSet = new HashSet<>();
		removeSet.add("1");

		assertThat(queue.drainTo(removeSet, 2)).isEqualTo(2);
	}
}
