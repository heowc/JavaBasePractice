package dev.heowc;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class AutoValueArticle {

    abstract Long id();
    abstract String title();
    abstract String content();

    public static Builder builder() {
        return new AutoValue_AutoValueArticle.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder id(Long id);

        public abstract Builder title(String title);

        public abstract Builder content(String content);

        public abstract AutoValueArticle build();
    }
}