package com.tistory.heowc.guava;

import com.google.common.net.InternetDomainName;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InternetDomainNameTests {

    private InternetDomainName domainName;

    @Before
    public void before_init() {
        domainName = InternetDomainName.from("heowc.tistory.com");

        System.out.println(domainName.toString());
    }

    @Test
    public void test_topPrivateDomain() throws Exception {
        Assert.assertEquals(domainName.topPrivateDomain().toString(), "tistory.com");
    }

    @Test
    public void test_publicSuffix() throws Exception {
        Assert.assertEquals(domainName.publicSuffix().toString(), "com");
    }

}
