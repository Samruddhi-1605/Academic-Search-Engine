import java.nio.file.*;
import java.util.Set;
import java.io.IOException;

public class Document {
    private int id;
    private String content;
    private String name;

    public Document(int id, String name) throws IOException {
        this.id = id;
        this.name = name;
        this.content = new String(Files.readAllBytes(Paths.get(name)));
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        String text = content.toLowerCase().replaceAll("[^a-z\\s]", "");
        String[] tokens = text.split("\\s+");

        Set<String> stopwords = Set.of(
                "a", "an", "the", "is", "are", "was", "were", "and", "or", "of",
                "to", "in", "on", "for", "with", "by", "at", "from");

        StringBuilder filtered = new StringBuilder();
        for (String token : tokens) {
            if (!stopwords.contains(token)) {
                filtered.append(token).append(" ");
            }
        }
        return filtered.toString().trim();
    }

    public String getName() {
        return name;
    }
}
