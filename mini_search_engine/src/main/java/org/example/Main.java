package org.example;
import dataset_loader.DatasetLoader;
import indexer.Indexer;
import link_graph_builder.LinkGraphBuilder;
import model.Page;
import page_rank_calculator.PageRankCalculator;
import query_processor.QueryProcessor;
import search_result.SearchResult;
import text_processor.TextProcessor;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        DatasetLoader loader = new DatasetLoader();
        List<Page> pages = loader.load("dataset");
        Indexer indexer = new Indexer();
        indexer.buildIndex(pages);
        LinkGraphBuilder linkGraphBuilder = new LinkGraphBuilder();
        Map<String, List<String>> graph = linkGraphBuilder.buildGraph(pages);
        Map<String, List<String>> reversedGraph = linkGraphBuilder.buildReverseGraph(graph);
        PageRankCalculator pageRankCalculator = new PageRankCalculator();
        Map<String, Double> pageRanks = pageRankCalculator.calculate(graph, reversedGraph);
        QueryProcessor queryProcessor = new QueryProcessor(indexer, pages, pageRanks);
        String[] queries = {
                "machine learning algorithms",
                "web development javascript",
                "neural network deep learning",
                "programming language"
        };

        for (String query : queries) {
            System.out.println("\n========== QUERY: \"" + query + "\" ==========");
            List<SearchResult> results = queryProcessor.process(query);
            results.forEach(System.out::println);
        }
    }
}


