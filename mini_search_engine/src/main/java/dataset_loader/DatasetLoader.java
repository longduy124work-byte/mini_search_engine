package dataset_loader;

import html_parser.HtmlParser;
import model.Page;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DatasetLoader {
    public List<Page> load(String folderPath) throws IOException {
        File folder = new File(folderPath);
        HtmlParser parser = new HtmlParser();
        List<Page> pages = new ArrayList<>();
        for(File htmlFile : folder.listFiles()) {
            if(htmlFile.getName().equals(".DS_Store")) {
                continue;
            }
            pages.add(parser.parse(htmlFile));
        }
        return pages;
    }
}
