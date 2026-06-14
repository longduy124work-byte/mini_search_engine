package org.example;
import dataset_loader.DatasetLoader;
import indexer.Indexer;
import model.Page;
import posting.Posting;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        DatasetLoader loader = new DatasetLoader();
        List<Page> pages = loader.load("dataset");
        Indexer indexer = new Indexer();
        indexer.buildIndex(pages);

        Map<String, List<Posting>> mapInvertedIndex = indexer.getMapInvertedIndex();

        String[] testWords = {"python", "programming", "machine"};
        for(String word : testWords) {
            System.out.println("===" + word + "===");
            List<Posting> postings = mapInvertedIndex.get(word);
            if(postings == null) {
                System.out.println("Không tìm thấy");
                continue;
            }
            for(Posting p: postings) {
                System.out.println(p);
            }
        }
    }
}
