package posting;

import java.util.ArrayList;
import java.util.List;

public class Posting {
    private String fileName;
    private List<Integer> positions = new ArrayList<>();
    private int termFrequency;

    public Posting() {

    }

    public Posting(String fileName) {
        this.fileName = fileName;
    }

    public void addPosition(int position) {
        positions.add(position);
        termFrequency++;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public List<Integer> getPositions() {
        return positions;
    }

    public void setPositions(List<Integer> positions) {
        this.positions = positions;
    }

    public int getTermFrequency() {
        return termFrequency;
    }

    public void setTermFrequency(int termFrequency) {
        this.termFrequency = termFrequency;
    }

    @Override
    public String toString() {
        return "Posting{file= '" + fileName + "', tf= " + termFrequency + ", positions= " + positions + "}";
    }
}
