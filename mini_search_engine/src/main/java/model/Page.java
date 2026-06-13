package model;

import java.util.List;

public class Page {
    private String pageName;
    private String title;
    private String content;
    private List<String> urls;

    public Page() {
    }

    public Page(String pageName, String title, String content, List<String> urls) {
        this.pageName = pageName;
        this.title = title;
        this.content = content;
        this.urls = urls;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getUrl() {
        return urls;
    }

    public void setUrl(List<String> url) {
        this.urls = url;
    }

    @Override
    public String toString() {
        return "Document{" +
                "filename='" + pageName + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", urls=" + urls +
                '}';
    }
}
