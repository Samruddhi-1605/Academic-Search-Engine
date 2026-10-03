import java.util.*;

public class InvertedIndex {
    private Map<String, Map<Integer, Integer>> index = new HashMap<>();

    // Add document terms to the index
    public void addDocument(Document doc) {
        String[] tokens = doc.getContent().split("\\s+");
        for (String token : tokens) {
            index.putIfAbsent(token, new HashMap<>());
            Map<Integer, Integer> postings = index.get(token);
            postings.put(doc.getId(), postings.getOrDefault(doc.getId(), 0) + 1);
        }
    }

    // Get documents containing the term
    public Map<Integer, Integer> getPostings(String term) {
        return index.getOrDefault(term, new HashMap<>());
    }

    public Set<String> getAllTerms() {
        return index.keySet();
    }
}
