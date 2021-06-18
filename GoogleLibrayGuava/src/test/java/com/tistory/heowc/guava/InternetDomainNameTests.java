package com.tistory.heowc.guava;

import com.google.common.net.InternetDomainName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InternetDomainNameTests {

	private InternetDomainName domainName;

	@BeforeEach
	void before_init() {
		domainName = InternetDomainName.from("heowc.tistory.com");

		System.out.println(domainName.toString());
	}

	@Test
	void test_topPrivateDomain() throws Exception {
		assertEquals(domainName.topPrivateDomain().toString(), "tistory.com");
	}

	@Test
	void test_publicSuffix() throws Exception {
		assertEquals(domainName.publicSuffix().toString(), "com");
	}

}
