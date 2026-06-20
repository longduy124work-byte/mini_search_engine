package page_rank_calculator;

import java.util.*;

public class PageRankCalculator {
    private static final Double DAMPING_FACTOR = 0.85; //d trong công thức
    private static final int MAX_ITERATIONS = 100;
    private static final Double CONVERGENCE_THRESHOLD = 0.0001; //giới hạn hội tụ

    public Map<String, Double> calculate(Map<String, List<String>> graph, Map<String, List<String>>reversedGraph) {
        //inbound = reverseGraph.getOrDefault
        //linkCount = graph.get(inbound).size()
        //Khởi tạo all pageRank = 1/N -> N là tất cả website trong graph
        Set<String> allPages = graph.keySet();
        Map<String, Double> pageRanks = new HashMap<>();
        int allPageCount = allPages.size(); //N trong công thức (1-d)/N
        for(String page: allPages) {
           pageRanks.put(page, 1.0/allPageCount); //khởi tạo page rank của tất cả các page ban đầu bằng 1/N
        }
        for(int i = 0; i<MAX_ITERATIONS; i++) {
            Map<String, Double> newPageRanks = new HashMap<>();
            for(String page: allPages) {
                double sum = 0.0;
                List<String> inboundPages = reversedGraph.getOrDefault(page, new ArrayList<>());
                for(String inbound: inboundPages) {
                    Double inboundPageRank = pageRanks.get(inbound);
                    int outboundLinkCount = graph.get(inbound).size();
                    sum += inboundPageRank/outboundLinkCount;
                }
                double newPR = (1-DAMPING_FACTOR)/allPageCount + DAMPING_FACTOR * sum;
                newPageRanks.put(page, newPR);
            }

            //Kiểm tra hội tụ
            double delta = 0.0;
            for(String page: allPages) {
                delta += Math.abs(newPageRanks.get(page) - pageRanks.get(page));
            }
            pageRanks = newPageRanks;
            System.out.println("\n--- Vòng " + (i + 1) + " ---");
            pageRanks.entrySet().stream()
                    .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                    .forEach(e -> System.out.printf("%-35s → %.6f%n", e.getKey(), e.getValue()));

            if(delta < CONVERGENCE_THRESHOLD) {
                System.out.println("Hội tụ sau: " + (i+1) + " vòng");
                break;
            }
        }

        return pageRanks;
    }
}
