package com.tistory.heowc.datastructure;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.EmptyStackException;

@TestMethodOrder(MethodOrderer.MethodName.class)
class QueueTests {

	private static Queue QUEUE;

	@BeforeAll
	public static void beforeClass_init() {
		QUEUE = new Queue(5);
	}

	@Test
	void test1_offer() throws Exception {
		try {
			QUEUE.offer(1);
			QUEUE.offer(2);
			QUEUE.offer(3);
			QUEUE.offer(4);
			QUEUE.offer(5);
			QUEUE.offer(6);
		} catch (StackOverflowError e) {
			e.printStackTrace();
		}
	}

	@Test
	void test2_poll() throws Exception {
		try {
			System.out.println(QUEUE.poll());
			System.out.println(QUEUE.poll());
			System.out.println(QUEUE.poll());
			System.out.println(QUEUE.poll());
			System.out.println(QUEUE.poll());
			System.out.println(QUEUE.poll());
		} catch (EmptyStackException e) {
			e.printStackTrace();
		}
	}
}
