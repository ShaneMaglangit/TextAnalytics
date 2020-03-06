public interface AnalyticsInterface {
    /**
     * Reads  the  text  file  as input which will be analyzed.
     * @param s name of the text file.
     */
    public void fileInputRead(String s);

    /**
     * Counts the total number of sentences in the document input.
     * @return total number of sentences
     */
    public int countSentences();

    /**
     * Counts  the total  number  of words/terms in the document input.
     * @return total number of words
     */
    public int countTotalWords();

    /**
     * Counts the total number of stop words in the document input.
     * Stop words are English words which do not contain any importance.
     * For example, the words ‘are’, ‘is’, ‘that’, ‘this’, ‘a’ are stop words.
     * @return total number of stop words.
     */
    public int countStopWords();

    /**
     * Counts the total number of non stop words in the document input.
     * @return total number of non stop words.
     */
    public int countNonStopWords();

    /**
     * Counts  the  total  number  of paragraphs in the document input
     * @return total number of paragraphs
     */
    public int countParagraphs();

    /**
     * Counts the frequency of each term present in the whole document.
     * @param TotalTerms
     */
    public void countTermFrequencyWrite(int TotalTerms);

    /**
     * Writes the overall results of the analysis in a text file called AnalysisResults.txt
     */
    public void fileAnalyticsResultWrite();
}
