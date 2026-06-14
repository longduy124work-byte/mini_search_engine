package text_processor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TextProcessor {
    private static final Set<String> STOPWORDS = Set.of(
            "a", "an", "the", "is", "are", "was", "were",
            "in", "on", "at", "to", "for", "of", "and",
            "or", "but", "it", "its", "this", "that",
            "with", "as", "by", "from", "be", "has",
            "have", "had", "not", "also", "such", "their",
            "which", "than", "into", "between", "through"
    );

    public List<String> process(String htmlContent) {
        //trả về 1 list String lower case, bỏ các kí tự đặc biệt và không chứa stop word
        String loweredText = htmlContent.toLowerCase();
        String[] rawTokens = loweredText.split("[^a-z0-9]+");
        List<String> tokens = new ArrayList<>();
        for(String token : rawTokens) {
            if(!token.isEmpty() && !STOPWORDS.contains(token)) {
                tokens.add(token);
            }
        }
        return tokens;
    }
}
