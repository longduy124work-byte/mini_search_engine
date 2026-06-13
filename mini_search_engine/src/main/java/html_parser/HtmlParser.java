package html_parser;
import model.Page;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HtmlParser {
    public Page parse(File htmlFile) throws IOException {
        Document doc = Jsoup.parse(htmlFile, "UTF-8");
        List<Element> links = doc.select("a[href]");
        List<String> urls = new ArrayList<>();
        for(Element link : links) {
            urls.add(link.attr("href"));
        }
        return new Page(htmlFile.getName(), doc.title(), doc.body().text(), urls);
    }
}
