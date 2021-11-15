package dev.heowc;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class ArticleTest {

    @DisplayName("lombok으로 만든 article은 id가 같으면 hashCode가 같다.")
    @Test
    void lombokTest() {
        final LombokArticle lombok = LombokArticle.builder()
                .id(1L).title("lombok").content("test")
                .build();

        final LombokArticle otherLombok = LombokArticle.builder()
                .id(1L).title("lombok2").content("test2")
                .build();

        assertThat(lombok.hashCode()).isEqualTo(otherLombok.hashCode());
    }

    @DisplayName("lombok으로 만든 article은 id가 다르면 hashCode가 다르다.")
    @Test
    void lombokTest2() {
        final LombokArticle lombok = LombokArticle.builder()
                .id(1L).title("lombok").content("test1")
                .build();

        final LombokArticle otherLombok = LombokArticle.builder()
                .id(2L).title("lombok").content("test2")
                .build();

        assertThat(lombok.hashCode()).isNotEqualTo(otherLombok.hashCode());
    }

    @DisplayName("autovalue으로 만든 article은 모든 필드가 같으면 hashCode가 같다.")
    @Test
    void autoValueTest() {
        final AutoValueArticle autoValue = AutoValueArticle.builder()
                .id(1L).title("autoValue").content("test")
                .build();
        final AutoValueArticle otherAutoValue = AutoValueArticle.builder()
                .id(1L).title("autoValue").content("test")
                .build();

        assertThat(autoValue.hashCode()).isEqualTo(otherAutoValue.hashCode());
    }

    @DisplayName("autovalue으로 만든 article은 하나의 필드라도 다르면 hashCode가 다르다.")
    @ParameterizedTest
    @CsvSource({
            "2,autoValue,test",
            "1,autoValue2,test",
            "1,autoValue,test2"
    })
    void autoValueTest(Long id, String title, String content) {
        final AutoValueArticle autoValue = AutoValueArticle.builder()
                .id(1L).title("autoValue").content("test")
                .build();
        final AutoValueArticle otherAutoValue = AutoValueArticle.builder()
                .id(id).title(title).content(content)
                .build();

        assertThat(autoValue.hashCode()).isNotEqualTo(otherAutoValue.hashCode());
    }

    @DisplayName("effective java 3rd에서 권장사항으로 만든 article은 id와 title이 같으면 hashCode가 같다.")
    @Test
    void effectiveTest() {
        final EffectiveArticle effective = new EffectiveArticle(1L, "effective", "test");
        final EffectiveArticle otherEffective = new EffectiveArticle(1L, "effective", "test2");

        assertThat(effective.hashCode()).isEqualTo(otherEffective.hashCode());
    }


    @DisplayName("effective java 3rd에서 권장사항으로 만든 article은 id,title 중 하나라도 다르면 hashCode가 다르다.")
    @ParameterizedTest
    @CsvSource({
            "2,effective,test",
            "1,effective2,test2",
            "2,effective2,test3"
    })
    void effectiveTest(Long id, String title, String content) {
        final EffectiveArticle effective = new EffectiveArticle(1L, "effective", "test");
        final EffectiveArticle otherEffective = new EffectiveArticle(id, title, content);

        assertThat(effective.hashCode()).isNotEqualTo(otherEffective.hashCode());
    }

    @DisplayName("record로 만든 article은 id와 title이 같으면 hashCode가 같다.")
    @Test
    void recordTest() {
        final RecordArticle record = new RecordArticle(1L, "record", "test");
        final RecordArticle otherRecord = new RecordArticle(1L, "record", "test");
        assertThat(record.hashCode()).isEqualTo(otherRecord.hashCode());
    }


    @ParameterizedTest
    @DisplayName("record로 만든 article은 id,title 중 하나라도 다르면 hashCode가 다르다.")
    @CsvSource({
            "2,record,test",
            "1,record2,test2",
            "2,record2,test3"
    })
    void recordTest(Long id, String title, String content) {
        final RecordArticle record = new RecordArticle(1L, "record", "test");
        final RecordArticle otherRecord = new RecordArticle(id, title, content);
        assertThat(record.hashCode()).isNotEqualTo(otherRecord.hashCode());
    }

    @Test
    void test() {
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
        final EffectiveArticle effective = new EffectiveArticle(1L, "effective", "test");
        assertThat(lombok).isNotEqualTo(autoValue);
        assertThat(autoValue).isNotEqualTo(effective);
        assertThat(effective).isNotEqualTo(lombok);
    }
}
