package link_graph_builder;

import model.Page;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LinkGraphBuilder {
    public Map<String, List<String>> buildGraph(List<Page> pages) {
        Map<String, List<String>> graph = new HashMap<>();
        for(Page page: pages) {
            graph.put(page.getPageName(), page.getUrl());
        }
        return graph;
    }

    public Map<String, List<String>> buildReverseGraph(Map<String, List<String>> originalGraph) {
        Map<String, List<String>> reverseGraph = new HashMap<>();
        for(Map.Entry<String, List<String>> map :originalGraph.entrySet()) {
            String source = map.getKey();
            List<String> destinations = map.getValue();
            for(String destination: destinations) {
                reverseGraph.putIfAbsent(destination, new ArrayList<>());
                reverseGraph.get(destination).add(source);
            }
        }
        return reverseGraph;
    }

    public void printGraph(Map<String, List<String>> graph, String title) {
        System.out.println("\n========== " + title + " ==========");
        for (Map.Entry<String, List<String>> entry : graph.entrySet()) {
            System.out.println(entry.getKey() + " (" + entry.getValue().size() + ")");
            for (String link : entry.getValue()) {
                System.out.println("    → " + link);
            }
        }
    }
}
