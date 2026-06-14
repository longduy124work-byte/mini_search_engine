package indexer;

import model.Page;
import posting.Posting;
import text_processor.TextProcessor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Indexer {
    private final TextProcessor processor = new TextProcessor();
    private final Map<String, List<Posting>> mapInvertedIndex = new HashMap<>();

    public void buildIndex(List<Page> pages) {
        for(Page page : pages) {
            indexPage(page);
        }
    }

    public void indexPage(Page page) {
        List<String> tokens = processor.process(page.getContent());
        for(int position = 0; position < tokens.size(); position++) {
            String token = tokens.get(position);
            addToMapInvertedIndex(token, page.getPageName(), position);
        }
    }

    public void addToMapInvertedIndex(String token, String pageName, int position) {
        mapInvertedIndex.putIfAbsent(token, new ArrayList<>());
        List<Posting> postings = mapInvertedIndex.get(token); //postings của token tương ứng

        Posting existing = checkPostingExisting(postings, pageName);
        if(existing == null) {
            Posting posting = new Posting(pageName);
            posting.addPosition(position);
            postings.add(posting);
        } else {
            existing.addPosition(position);
        }


    }

    public Posting checkPostingExisting(List<Posting> postings, String pageName) {
        for(Posting posting : postings) {
            if(posting.getFileName().equals(pageName)) {
                return posting;
            }
        }
        return null;
    }


    public Map<String, List<Posting>> getMapInvertedIndex() {
        return mapInvertedIndex;
    }
}
