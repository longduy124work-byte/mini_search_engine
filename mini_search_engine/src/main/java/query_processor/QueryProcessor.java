package query_processor;

import indexer.Indexer;
import model.Page;
import posting.Posting;
import search_result.SearchResult;
import text_processor.TextProcessor;
import tf_idf_calculator.TFIDFCalculator;

import java.util.*;

public class QueryProcessor {
    private final TextProcessor textProcessor;
    private final Indexer indexer;
    private final int totalDocument;
    private TFIDFCalculator tfidfCalculator;
    private final Map<String, Double> pageRanks;
    private final Map<String, String> pageTitles;

    public QueryProcessor(Indexer indexer, List<Page> pages, Map<String, Double> pageRanks) {
        this.indexer = indexer;
        this.totalDocument = pages.size();
        this.pageRanks = pageRanks;
        this.pageTitles = new HashMap<>();
        this.textProcessor = new TextProcessor();
        this.tfidfCalculator = new TFIDFCalculator();
        for(Page page: pages) {
            pageTitles.put(page.getPageName(), page.getTitle());
        }
    }

    public List<SearchResult> process(String queryString) {
        List<String> tokens = textProcessor.process(queryString);
        if(tokens.isEmpty()) {
            System.out.println("Search input không hợp lệ");
            return new ArrayList<>();
        }
        Map<String, Double> aggregatedTFIDFScores = new HashMap<>();
        for(String token: tokens) {
            List<Posting> postings = indexer.getMapInvertedIndex().get(token);
            if(postings == null) {
                System.out.println("Không tìm thấy token: " + token);
                continue;
            }
            Map<String, Double> tokenPostingTFIDFScores = tfidfCalculator.calculate(postings, totalDocument);
            for(String pageName: tokenPostingTFIDFScores.keySet()) {
                aggregatedTFIDFScores.merge(pageName, tokenPostingTFIDFScores.get(pageName), Double::sum);
            }
        }
        List<SearchResult> results  = new ArrayList<>();
        for(String pageName: aggregatedTFIDFScores.keySet()) {
            double tfidfScore = aggregatedTFIDFScores.get(pageName);
            double pageRank = pageRanks.get(pageName);
            String pageTitle = pageTitles.get(pageName);
            SearchResult result = new SearchResult(pageName, pageTitle, pageRank, tfidfScore);
            results.add(result);
        }

        results.sort(Comparator.comparingDouble(SearchResult::getFinalScore).reversed());
        return results;

    }
}
