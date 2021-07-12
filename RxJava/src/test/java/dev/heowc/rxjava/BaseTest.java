package dev.heowc.rxjava;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subscribers.TestSubscriber;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

class BaseTest {

	private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);

	/*
		[Test worker] INFO dev.heowc.rxjava.BaseTest - before filter - OnNextNotification[hello]
		[Test worker] INFO dev.heowc.rxjava.BaseTest - before filter - OnNextNotification[rxjava]
		[Test worker] INFO dev.heowc.rxjava.BaseTest - after filter - OnNextNotification[rxjava]
		[Test worker] INFO dev.heowc.rxjava.BaseTest - before filter - OnNextNotification[3]
		[Test worker] INFO dev.heowc.rxjava.BaseTest - after filter - OnNextNotification[3]
		[Test worker] INFO dev.heowc.rxjava.BaseTest - before filter - OnCompleteNotification
		[Test worker] INFO dev.heowc.rxjava.BaseTest - after filter - OnCompleteNotification
	 */
	@Test
	void filter() {
		final TestSubscriber<String> subscriber = new TestSubscriber<>();

		Flowable.just("hello", "rxjava", "3")
				.doOnEach(it -> logger.info("before filter - {}", it))
				.filter(it -> !"hello".equals(it))
				.doOnEach(it -> logger.info("after filter - {}", it))
				.subscribe(subscriber);

		subscriber.assertResult("rxjava", "3");
	}

	/*
		[RxComputationThreadPool-3] INFO dev.heowc.rxjava.BaseTest - before filter - OnNextNotification[hello]
		[RxComputationThreadPool-3] INFO dev.heowc.rxjava.BaseTest - before filter - OnNextNotification[rxjava]
		[RxComputationThreadPool-3] INFO dev.heowc.rxjava.BaseTest - before filter - OnNextNotification[3]
		[RxComputationThreadPool-2] INFO dev.heowc.rxjava.BaseTest - filter - hello
		[RxComputationThreadPool-3] INFO dev.heowc.rxjava.BaseTest - before filter - OnCompleteNotification
		[RxComputationThreadPool-2] INFO dev.heowc.rxjava.BaseTest - filter - rxjava
		[RxComputationThreadPool-2] INFO dev.heowc.rxjava.BaseTest - filter - 3
		[RxComputationThreadPool-1] INFO dev.heowc.rxjava.BaseTest - after filter - OnNextNotification[rxjava]
		[RxComputationThreadPool-1] INFO dev.heowc.rxjava.BaseTest - after filter - OnNextNotification[3]
		[RxComputationThreadPool-1] INFO dev.heowc.rxjava.BaseTest - after filter - OnCompleteNotification
	 */
	@Test
	void filterOnOtherThread() throws InterruptedException {
		final TestSubscriber<String> subscriber = new TestSubscriber<>();

		Flowable.just("hello", "rxjava", "3")
				.observeOn(Schedulers.computation())
				.doOnEach(it -> logger.info("before filter - {}", it))
				.observeOn(Schedulers.computation())
				.filter(it -> {
					logger.info("filter - {}", it);
					return !"hello".equals(it);
				})
				.observeOn(Schedulers.computation())
				.doOnEach(it -> logger.info("after filter - {}", it))
				.observeOn(Schedulers.computation())
				.subscribe(subscriber);

		subscriber.await().assertResult("rxjava", "3");
	}

	/*
		[RxComputationThreadPool-1] INFO dev.heowc.rxjava.BaseTest - before flatMap - OnNextNotification[hello]
		[RxComputationThreadPool-1] INFO dev.heowc.rxjava.BaseTest - before flatMap - OnNextNotification[rxjava]
		[RxComputationThreadPool-1] INFO dev.heowc.rxjava.BaseTest - before flatMap - OnNextNotification[3]
		[RxComputationThreadPool-1] INFO dev.heowc.rxjava.BaseTest - before flatMap - OnCompleteNotification
		[RxComputationThreadPool-4] INFO dev.heowc.rxjava.BaseTest - after flatMap - OnNextNotification[3]
		[RxComputationThreadPool-2] INFO dev.heowc.rxjava.BaseTest - after flatMap - OnNextNotification[HELLO]
		[RxComputationThreadPool-2] INFO dev.heowc.rxjava.BaseTest - after flatMap - OnNextNotification[RXJAVA]
		[RxComputationThreadPool-2] INFO dev.heowc.rxjava.BaseTest - after flatMap - OnCompleteNotification
	 */
	@Test
	void flatMap() throws InterruptedException {
		final TestSubscriber<String> subscriber = new TestSubscriber<>();

		/*
			flatMap - 순서보장 X, 성능 O
			concatMap - 순서보장 O, 성능 X
			concatMapEager - 순서보장 O, 성능 O / But, 버퍼를 활용한 OOM 여지 있음
			concatMap.... -
		 */

		Flowable.just("hello", "rxjava", "3").delay(1, TimeUnit.SECONDS)
				.doOnEach(it -> logger.info("before flatMap - {}", it))
				.flatMap(it -> Flowable.just(it.toUpperCase()).delay(1, TimeUnit.SECONDS))
				.doOnEach(it -> logger.info("after flatMap - {}", it))
				.subscribe(subscriber);

		final List<String> actual = subscriber.awaitDone(2100, TimeUnit.MILLISECONDS)
				.assertValueCount(3)
				.values();
		assertThat(actual).contains("HELLO", "RXJAVA", "3");
	}

	/*
		[RxComputationThreadPool-5] INFO dev.heowc.rxjava.BaseTest - before map - OnNextNotification[hello]
		[RxComputationThreadPool-2] INFO dev.heowc.rxjava.BaseTest - before map - OnNextNotification[hello]
		[RxComputationThreadPool-5] INFO dev.heowc.rxjava.BaseTest - before map - OnNextNotification[rxjava]
		[RxComputationThreadPool-5] INFO dev.heowc.rxjava.BaseTest - before map - OnNextNotification[3]
		[RxComputationThreadPool-2] INFO dev.heowc.rxjava.BaseTest - before map - OnNextNotification[rxjava]
		[RxComputationThreadPool-4] INFO dev.heowc.rxjava.BaseTest - after map - OnNextNotification[HELLO]
		[RxComputationThreadPool-1] INFO dev.heowc.rxjava.BaseTest - after map - OnNextNotification[HELLO]
		[RxComputationThreadPool-2] INFO dev.heowc.rxjava.BaseTest - before map - OnNextNotification[3]
		[RxComputationThreadPool-5] INFO dev.heowc.rxjava.BaseTest - before map - OnCompleteNotification
		[RxComputationThreadPool-4] INFO dev.heowc.rxjava.BaseTest - subscribe 2 - HELLO
		[RxComputationThreadPool-1] INFO dev.heowc.rxjava.BaseTest - subscribe 1 - HELLO
		[RxComputationThreadPool-2] INFO dev.heowc.rxjava.BaseTest - before map - OnCompleteNotification
		[RxComputationThreadPool-4] INFO dev.heowc.rxjava.BaseTest - after map - OnNextNotification[RXJAVA]
		[RxComputationThreadPool-1] INFO dev.heowc.rxjava.BaseTest - after map - OnNextNotification[RXJAVA]
		[RxComputationThreadPool-4] INFO dev.heowc.rxjava.BaseTest - subscribe 2 - RXJAVA
		[RxComputationThreadPool-1] INFO dev.heowc.rxjava.BaseTest - subscribe 1 - RXJAVA
		[RxComputationThreadPool-1] INFO dev.heowc.rxjava.BaseTest - after map - OnNextNotification[3]
		[RxComputationThreadPool-4] INFO dev.heowc.rxjava.BaseTest - after map - OnNextNotification[3]
		[RxComputationThreadPool-1] INFO dev.heowc.rxjava.BaseTest - subscribe 1 - 3
		[RxComputationThreadPool-4] INFO dev.heowc.rxjava.BaseTest - subscribe 2 - 3
		[RxComputationThreadPool-1] INFO dev.heowc.rxjava.BaseTest - after map - OnCompleteNotification
		[RxComputationThreadPool-4] INFO dev.heowc.rxjava.BaseTest - after map - OnCompleteNotification
	 */
	@Test
	void observeOn() throws InterruptedException {
		final Flowable<String> flowable = Flowable.just("hello", "rxjava", "3").delay(1, TimeUnit.SECONDS)
				.observeOn(Schedulers.computation())
				.doOnEach(it -> logger.info("before map - {}", it))
				.map(String::toUpperCase)
				.observeOn(Schedulers.computation())
				.doOnEach(it -> logger.info("after map - {}", it));
		flowable.subscribe(data -> {
			logger.info("subscribe 1 - {}", data);
		});
		flowable.subscribe(data -> {
			logger.info("subscribe 2 - {}", data);
		});

		Thread.sleep(2000L);
	}
}
