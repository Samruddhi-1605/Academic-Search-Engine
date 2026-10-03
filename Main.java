import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        List<Document> documents = new ArrayList<>();
        String folderPath = "C:/Users/HP/Documents/AcademicSearchEngine/data";

        int id = 0;
        for (Path file : Files.newDirectoryStream(Paths.get(folderPath), "*.txt")) {
            documents.add(new Document(id++, file.toString()));
        }

        InvertedIndex index = new InvertedIndex();
        for (Document doc : documents) {
            index.addDocument(doc);
        }

        SearchEngine engine = new SearchEngine(index, documents);

        Scanner sc = new Scanner(System.in);
        System.out.println("Academic Search Engine Ready. Enter query:");
        while (true) {
            System.out.print("> ");
            String query = sc.nextLine();
            if (query.equalsIgnoreCase("exit"))
                break;
            engine.search(query);
        }
    }
}
