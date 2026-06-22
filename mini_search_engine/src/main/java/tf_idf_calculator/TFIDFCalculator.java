package tf_idf_calculator;

import posting.Posting;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TFIDFCalculator {
    public Map<String, Double> calculate(List<Posting> postings, int totalDocument) {
        Map<String, Double> tfidfScore = new HashMap<>();
        for(Posting posting: postings) {
            int tf = posting.getTermFrequency();
            double idf = Math.log((double) totalDocument/postings.size());
            double tf_idf = tf * idf;
            tfidfScore.put(posting.getFileName(), tf_idf);
        }
        return tfidfScore;
    }
}
