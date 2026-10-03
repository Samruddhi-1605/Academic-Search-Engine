import java.util.*;

public class SearchEngine {
    private InvertedIndex index;
    private List<Document> documents;

    public SearchEngine(InvertedIndex index, List<Document> documents) {
        this.index = index;
        this.documents = documents;
    }

    private double tfIdf(int docId, String term) {
        Map<Integer, Integer> postings = index.getPostings(term);
        int tf = postings.getOrDefault(docId, 0);
        int df = postings.size();
        int N = documents.size();
        return tf * Math.log((double) N / (1 + df));
    }

    private String getSnippet(String content, String term) {
        int idx = content.indexOf(term);
        if (idx == -1)
            return content.substring(0, Math.min(80, content.length())) + "...";
        int start = Math.max(0, idx - 30);
        int end = Math.min(content.length(), idx + 50);
        return "..." + content.substring(start, end) + "...";
    }

    public void search(String query) {
        query = query.trim();

        if (query.contains(" AND ") || query.contains(" OR ") || query.contains(" NOT ")) {
            handleBooleanQuery(query);
        } else {
            handleNormalQuery(query);
        }
    }

    private void handleNormalQuery(String query) {
        boolean isPhrase = query.startsWith("\"") && query.endsWith("\"");
        String cleanQuery = query.replace("\"", "").toLowerCase();
        String[] terms = cleanQuery.replaceAll("[^a-z\\s]", "").split("\\s+");

        Map<Integer, Double> docScores = new HashMap<>();
        Map<Integer, Double> docNorms = new HashMap<>();
        double queryNorm = 0;

        for (String term : terms) {
            double qWeight = 1;
            queryNorm += qWeight * qWeight;

            for (int docId : index.getPostings(term).keySet()) {
                double dWeight = tfIdf(docId, term);
                docScores.put(docId, docScores.getOrDefault(docId, 0.0) + dWeight * qWeight);
                docNorms.put(docId, docNorms.getOrDefault(docId, 0.0) + dWeight * dWeight);
            }
        }

        queryNorm = Math.sqrt(queryNorm);

        Map<Integer, Double> finalScores = new HashMap<>();
        for (int docId : docScores.keySet()) {
            double denom = Math.sqrt(docNorms.get(docId)) * queryNorm;
            if (denom != 0)
                finalScores.put(docId, docScores.get(docId) / denom);
        }

        if (isPhrase) {
            finalScores.entrySet().removeIf(e -> !documents.get(e.getKey()).getContent().contains(cleanQuery));
        }

        displayResults(finalScores, terms[0]);
    }

    private void handleBooleanQuery(String query) {
        String[] parts = new String[0];
        Set<Integer> resultSet = new HashSet<>();

        if (query.contains(" AND ")) {
            parts = query.split(" AND ");
            resultSet.addAll(getDocs(parts[0]));
            resultSet.retainAll(getDocs(parts[1]));
        } else if (query.contains(" OR ")) {
            parts = query.split(" OR ");
            resultSet.addAll(getDocs(parts[0]));
            resultSet.addAll(getDocs(parts[1]));
        } else if (query.contains(" NOT ")) {
            parts = query.split(" NOT ");
            resultSet.addAll(getDocs(parts[0]));
            resultSet.removeAll(getDocs(parts[1]));
        }

        Map<Integer, Double> scores = new HashMap<>();
        for (int docId : resultSet) {
            scores.put(docId, 1.0);
        }

        displayResults(scores, parts[0].toLowerCase());
    }

    private Set<Integer> getDocs(String term) {
        term = term.toLowerCase().replaceAll("[^a-z\\s]", "");
        return index.getPostings(term).keySet();
    }

    private void displayResults(Map<Integer, Double> scores, String highlightTerm) {
        scores.entrySet().stream()
                .sorted((a, b) -> Double.compare(b.getValue(), a.getValue()))
                .forEach(e -> {
                    String content = documents.get(e.getKey()).getContent();
                    String snippet = getSnippet(content, highlightTerm);
                    System.out.println("File: " + documents.get(e.getKey()).getName());
                    System.out.println("Score: " + String.format("%.4f", e.getValue()));
                    System.out.println("Snippet: " + snippet);
                    System.out.println("----------------------------------");
                });
    }
}
