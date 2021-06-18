package dev.heowc.rxjava;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.subscribers.TestSubscriber;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class BaseTest {

	private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);

	@Test
	void filter() {
		final TestSubscriber<String> subscriber = new TestSubscriber<>();

		Flowable.just("hello", "rxjava", "3")
				.doOnEach(it -> logger.info("before filter - {}", it))
				.filter(it -> !"hello".equals(it))
				.doOnEach(it -> logger.info("after filter - {}", it))
				.subscribe(subscriber);

//		[Test worker] INFO dev.heowc.rxjava.BaseTest - before filter - OnNextNotification[hello]
//		[Test worker] INFO dev.heowc.rxjava.BaseTest - before filter - OnNextNotification[rxjava]
//		[Test worker] INFO dev.heowc.rxjava.BaseTest - after filter - OnNextNotification[rxjava]
//		[Test worker] INFO dev.heowc.rxjava.BaseTest - before filter - OnNextNotification[3]
//		[Test worker] INFO dev.heowc.rxjava.BaseTest - after filter - OnNextNotification[3]
//		[Test worker] INFO dev.heowc.rxjava.BaseTest - before filter - OnCompleteNotification
//		[Test worker] INFO dev.heowc.rxjava.BaseTest - after filter - OnCompleteNotification

		subscriber.assertResult("rxjava", "3");
	}
}
