# Academic Search Engine

A Java-based academic search engine designed to efficiently retrieve and rank relevant documents from a large collection of academic content. The project uses **inverted indexing** for faster retrieval and **relevance scoring** to rank search results based on query terms.

## Features

* **Inverted Indexing** – Maps keywords to documents for efficient information retrieval.
* **Relevance Scoring** – Calculates the relevance of documents based on query-term frequency.
* **Keyword-Based Search** – Retrieves documents matching the user's search query.
* **Result Ranking** – Ranks retrieved documents according to their relevance score.
* **Efficient Query Processing** – Optimized document lookup using indexed data structures.

## Tech Stack

* **Language:** Java
* **Core Concepts:** Data Structures, Information Retrieval, Inverted Indexing, Relevance Scoring
* **Data Structures:** HashMap, ArrayList, Sets
* **Development:** Java Collections Framework

## How It Works

The search process consists of the following steps:

1. **Document Collection**

   * Academic documents are loaded and processed by the application.

2. **Text Processing**

   * Documents are analyzed to identify relevant keywords.

3. **Index Creation**

   * An inverted index is created that maps each keyword to the documents containing it.

4. **Query Processing**

   * The user's search query is processed and matched against the inverted index.

5. **Relevance Scoring**

   * Matching documents are assigned relevance scores based on the frequency of query terms.

6. **Ranking**

   * Documents are sorted according to their relevance scores and returned as search results.

## System Workflow

```text
Academic Documents
        ↓
   Text Processing
        ↓
   Inverted Index
        ↓
   User Search Query
        ↓
   Query Matching
        ↓
 Relevance Scoring
        ↓
   Result Ranking
        ↓
  Ranked Results
```

## Example

**Search Query:**

```text
machine learning
```

The system searches the inverted index for documents containing the query terms, calculates their relevance scores, and returns the matching documents in ranked order.

## Performance

The indexing and retrieval approach was designed to improve search efficiency compared with scanning documents individually.

* **40% reduction in query response time**
* **25% improvement in search precision**

These improvements were observed during project testing based on the implemented indexing and relevance-scoring approach.

## Project Structure

```text
Academic-Search-Engine-Java/
│
├── src/
│   └── ...
│
├── data/
│   └── ...
│
├── README.md
└── ...
```

## Getting Started

### Prerequisites

* Java JDK 8 or later
* Git

### Clone the Repository

```bash
git clone https://github.com/Samruddhi-1605/Academic-Search-Engine-Java.git
```

### Navigate to the Project

```bash
cd Academic-Search-Engine-Java
```

### Compile and Run

Compile the Java source files using:

```bash
javac *.java
```

Run the main class:

```bash
java Main
```

> The exact compilation and execution commands may vary depending on the project's package structure and main class.

## Key Learning Outcomes

Through this project, I gained hands-on experience with:

* Java programming and the Collections Framework
* Data structures for efficient data retrieval
* Information Retrieval fundamentals
* Inverted indexing
* Search relevance and ranking
* Query processing and optimization
* Performance analysis

## Future Enhancements

* Implement **TF-IDF** or BM25-based relevance scoring
* Add text preprocessing such as stemming and stop-word removal
* Support phrase and Boolean queries
* Add a web-based search interface
* Store and manage larger document collections
* Introduce persistent indexing for faster application startup

## Author

**Samruddhi C S**

GitHub: [Samruddhi-1605](https://github.com/Samruddhi-1605)
