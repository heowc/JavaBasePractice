package com.tistory.heowc.blockingqueue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.SynchronousQueue;

import static org.assertj.core.api.Assertions.assertThat;

class SynchronousQueueTests {

	private SynchronousQueue<Integer> queue;

	@BeforeEach
	void before_init() {
		queue = new SynchronousQueue<>(); // default is false
	}

	@Test
	void test_offer() throws Exception {
		// This queue does not have buffer
		assertThat(queue.offer(1)).isFalse().as("Queue full");
	}
}
