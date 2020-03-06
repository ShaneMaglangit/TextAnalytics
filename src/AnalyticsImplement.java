import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class AnalyticsImplement implements AnalyticsInterface {
    private StringBuilder articleBuilder;
    private ArrayList<String> stopWords;
    private String[] words;

    public AnalyticsImplement() {
        articleBuilder = new StringBuilder();
        stopWords = new ArrayList<String>();
        loadStopWords();
    }

    /**
     * Read contents of the given file.
     *
     * @param s name of the file.
     */
    @Override
    public void fileInputRead(String s) {
        if (!s.matches(".*\\.txt")) s += ".txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(s))) {
            String next;
            while ((next = reader.readLine()) != null) {
                articleBuilder.append(next);
                articleBuilder.append("\n");
            }
            words = articleBuilder.toString().replaceAll("[^\\w’'-]+", " ").trim().split(" +");
        } catch (IOException ioException) {
            // TODO: Handle Exception
            ioException.printStackTrace();
        }
    }

    /**
     * Count all sentences within the given file
     *
     * @return number of sentences
     */
    @Override
    public int countSentences() {
        return articleBuilder.toString().replaceAll("\n+", " ").trim().split("[\\.\\?\\!]").length;
    }

    /**
     * Count all words within the given file
     *
     * @return number of words
     */
    @Override
    public int countTotalWords() {
        return words.length;
    }

    /**
     * Count all stop words within the given file
     *
     * @return number of stop words
     */
    @Override
    public int countStopWords() {
        int count = 0;
        for (String word : words) {
            if (stopWords.contains(word.toLowerCase())) count++;
        }
        return count;
    }


    /**
     * Count all non stop words within the given file
     *
     * @return number of non stop words
     */
    @Override
    public int countNonStopWords() {
        int count = 0;
        for (String word : words) {
            if (!stopWords.contains(word.toLowerCase())) count++;
        }
        return count;
    }

    /**
     * Count all paragraphs within the given file
     * @return number of paragraphs
     */
    @Override
    public int countParagraphs() {
        return articleBuilder.toString().split("[\n]{2,}").length;
    }

    /**
     * Generate a file containing the frequencies of terms within the
     * specified file
     */
    @Override
    public void countTermFrequencyWrite(int TotalTerms) {
        // Map all terms
        HashMap<String, Integer> terms = new HashMap<String, Integer>();
        for (String word : words) terms.put(word.toLowerCase(), terms.getOrDefault(word.toLowerCase(), 0) + 1);

        // Write the terms into TermFrequencies.txt
        try (FileWriter writer = new FileWriter("TermFrequencies.txt")) {
            for (String key : terms.keySet()) {
                Double freq = Double.valueOf(terms.get(key)) / TotalTerms;
                writer.write(key + " : " + String.format("%.4f", freq) + "\n");
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    @Override
    public void fileAnalyticsResultWrite() {
        StringBuilder sb = new StringBuilder();
        System.out.println("Counting sentences...");
        sb.append("Number of Sentences: " + countSentences() + "\n");
        System.out.println("Counting all the words...");
        sb.append("Number of Words: " + countTotalWords() + "\n");
        System.out.println("Counting all the stopwords...");
        sb.append("Number of Stop Words: " + countStopWords() + "\n");
        System.out.println("Counting all the non-stopwords...");
        sb.append("Number of Non-Stop Words: " + countNonStopWords() + "\n");
        System.out.println("Counting paragraphs...");
        sb.append("Number of Paragraphs: " + countParagraphs() + "\n");
        System.out.println("Calculating frequencies...");
        countTermFrequencyWrite(countTotalWords());
        System.out.println("Writing results...");
        try (FileWriter writer = new FileWriter("AnalysisResults.txt")) {
            writer.write(sb.toString());
            System.out.println("Done!");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    /**
     * Load all stop words from the text file
     */
    public void loadStopWords() {
        try (BufferedReader reader = new BufferedReader(new FileReader("stopwords.txt"))) {
            String next;
            while ((next = reader.readLine()) != null) stopWords.add(next);
        } catch (IOException ioException) {
            // TODO: Handle Exception
            ioException.printStackTrace();
        }
    }
}