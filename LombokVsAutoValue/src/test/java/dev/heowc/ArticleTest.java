package dev.heowc;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ArticleTest {

    @Test
    public void test() {
        final LombokArticle lombok = LombokArticle.builder()
                .id(1L)
                .title("lombok")
                .content("test")
                .build();
        final AutoValueArticle autoValue = AutoValueArticle.builder()
                .id(1L)
                .title("autoValue")
                .content("test")
                .build();

        assertThat(lombok).isNotEqualTo(autoValue);
    }
}
