public class WordOccurrence {
    private String name;
    private int occurrence;

    public WordOccurrence(String name, int occurrence) {
        this.name = name;
        this.occurrence = occurrence;
    }

    public String getName() {
        return name;
    }

    public int getOccurrence() {
        return occurrence;
    }
    @Override
    public String toString() {
        return "{name: \"" + name + "\", occurrence: " + occurrence + "}";
    }
}
