package org.example;

import dataset_loader.DatasetLoader;
import model.Page;
import java.io.IOException;
import java.util.List;

public class Main {
    static void main(String[] args) throws IOException {
        DatasetLoader loader = new DatasetLoader();
        List<Page> pages = loader.load("dataset");
        for(Page page : pages) {
            System.out.println(page);
        }
    }
}
