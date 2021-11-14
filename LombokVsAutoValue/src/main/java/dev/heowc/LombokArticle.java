package dev.heowc;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LombokArticle {

    private Long id;
    private String title;
    private String content;
}
