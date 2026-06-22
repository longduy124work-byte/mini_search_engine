package search_result;

public class SearchResult {
    private String pageName;
    private String title;
    private double pageRank;
    private double tfidfScore;

    public SearchResult() {
    }

    public SearchResult(String pageName, String title, double pageRank, double tfidfScore) {
        this.pageName = pageName;
        this.title = title;
        this.pageRank = pageRank;
        this.tfidfScore = tfidfScore;
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

    public double getPageRank() {
        return pageRank;
    }

    public void setPageRank(double pageRank) {
        this.pageRank = pageRank;
    }

    public double getTfidfScore() {
        return tfidfScore;
    }

    public void setTfidfScore(double tfidfScore) {
        this.tfidfScore = tfidfScore;
    }

    public double getFinalScore() {
        return tfidfScore + pageRank;
    }

    @Override
    public String toString() {
        return String.format("%-35s | TF-IDF: %6.4f | PR: %6.4f | Final: %6.4f",
                pageName, tfidfScore, pageRank, getFinalScore());
    }
}
