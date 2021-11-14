package dev.heowc;

public class EffectiveArticle {

    private Long id;
    private String title;
    private String content;

    public EffectiveArticle(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    @Override
    public int hashCode() {
        return calculateHashCode(id, title);
    }

    private static int calculateHashCode(Long id, String title) {
        int c = Long.hashCode(id);
        int result = c;
        result = 31 * result + (title == null ? 0 : title.hashCode());
                            // {참조타입}.hashCode()
                            // for (핵심필드.hashCode) // Arrays.hashCode
        return result;
    }
}
