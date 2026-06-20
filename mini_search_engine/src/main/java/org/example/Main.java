package org.example;
import dataset_loader.DatasetLoader;
import link_graph_builder.LinkGraphBuilder;
import model.Page;
import page_rank_calculator.PageRankCalculator;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        DatasetLoader loader = new DatasetLoader();
        List<Page> pages = loader.load("dataset");
//        TextProcessor processor = new TextProcessor();
        LinkGraphBuilder linkGraphBuilder = new LinkGraphBuilder();
        Map<String, List<String>> graph = linkGraphBuilder.buildGraph(pages);
        Map<String, List<String>> reversedGraph = linkGraphBuilder.buildReverseGraph(graph);
//        linkGraphBuilder.printGraph(graph, "GRAPH (outbound)");
//        linkGraphBuilder.printGraph(reversedGraph, "REVERSED GRAPH (inbound)");

        PageRankCalculator pageRankCalculator = new PageRankCalculator();
        Map<String, Double> pageRanks = pageRankCalculator.calculate(graph, reversedGraph);
//        pageRanks.entrySet().stream()
//                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
//                .forEach(e -> System.out.printf("%-35s → %.6f%n", e.getKey(), e.getValue()));

//        for (Page page : pages) {
//           List<String> tokens = processor.process(page.getContent());
//            System.out.println(tokens);
//        }
//
//        Indexer indexer = new Indexer();
//        indexer.buildIndex(pages);
//
//        Map<String, List<Posting>> mapInvertedIndex = indexer.getMapInvertedIndex();
//
//        String[] testWords = {"python", "programming", "machine"};
//        for(String word : testWords) {
//            System.out.println("===" + word + "===");
//            List<Posting> postings = mapInvertedIndex.get(word);
//            if(postings == null) {
//                System.out.println("Không tìm thấy");
//                continue;
//            }
//            for(Posting p: postings) {
//                System.out.println(p);
//            }
//        }
    }
}
