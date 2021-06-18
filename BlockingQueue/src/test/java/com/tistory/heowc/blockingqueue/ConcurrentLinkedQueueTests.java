package com.tistory.heowc.blockingqueue;

import org.junit.jupiter.api.BeforeEach;

import java.util.concurrent.ConcurrentLinkedQueue;

class ConcurrentLinkedQueueTests {

	private ConcurrentLinkedQueue<Integer> queue;

	@BeforeEach
	public void before_init() {
		queue = new ConcurrentLinkedQueue<>();
	}


}
