package dev.heowc;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(of = "id")
public class LombokArticle {

    private Long id;
    private String title;
    private String content;
}
