package dev.heowc.rxjava;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subscribers.TestSubscriber;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
}
