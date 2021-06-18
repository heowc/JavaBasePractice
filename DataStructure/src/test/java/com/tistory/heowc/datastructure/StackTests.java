package com.tistory.heowc.datastructure;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.EmptyStackException;

import static org.junit.jupiter.api.Assertions.assertThrows;

@TestMethodOrder(MethodOrderer.MethodName.class)
class StackTests {

	private static Stack STACK;

	@BeforeAll
	static void beforeClass_init() {
		STACK = new Stack(5);
	}

	@Test
	void test1_push() {
		STACK.push(1);
		STACK.push(2);
		STACK.push(3);
		STACK.push(4);
		STACK.push(5);
	}

	@Test
	void test2_pop() {
		STACK.pop();
		STACK.pop();
		STACK.pop();
		STACK.pop();
		STACK.pop();
	}

	@Test
	void test3_pop() {
		assertThrows(EmptyStackException.class, () -> {
			STACK.pop();
		});
	}

	@Test
	void test4_push() {
		try {
			STACK.push(1);
			STACK.push(2);
			STACK.push(6);
			STACK.push(4);
			STACK.push(5);
		} catch (StackOverflowError e) {
			e.printStackTrace();
		}
	}
}
