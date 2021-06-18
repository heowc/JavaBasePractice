package dev.heowc.rxjava;

import io.reactivex.rxjava3.core.Flowable;

public class Main {
	public static void main(String[] args) {
		final Flowable<String> flowable = Flowable.just("hello", "rxjava", "3");
		flowable.subscribe(System.out::println);
	}
}
