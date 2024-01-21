import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * The type Matcher extractor.
 * This class extracts the hypernyms and hyponyms from a text.
 */

public class MatcherExtractor {
    private final DataBaseHyperHypo dbHyperHypo;
    private final List<Patterns> listPatterns;

    /**
     * Constructor.
     *
     * @param dbHyperHypo  a database.
     * @param listPatterns patterns to search for in the text.
     */
    public MatcherExtractor(DataBaseHyperHypo dbHyperHypo, List<Patterns> listPatterns) {
        this.dbHyperHypo = dbHyperHypo;
        this.listPatterns = listPatterns;
    }


    /**
     * The method extracts the hypernyms and hyponyms from a file.
     *
     * @param file  a file.
     * @param lemma a lemma.
     * @throws IOException if error occurs.
     */
    public void extractFromFile(File file, String lemma) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String checkLine;
        //for each line in the file
        while ((checkLine = reader.readLine()) != null) {
            //if the line contains the lemma
            if (checkLine.contains(lemma)) {
                //check inside the line there for matches to one of the patterns
                checkMatches(checkLine);
            }
        }
    }


    /**
     * The method extracts the hypernyms and hyponyms from a line.
     * It checks if the line contains a match to one of the patterns.
     * If it does, it extracts the hypernym and hyponyms from the match.
     * It adds the hypernym and hyponyms to the database.
     *
     * @param line a text.
     * @throws IOException if error occurs.
     */
    public void checkMatches(String line) {
        //for each pattern
        for (Patterns pattern : listPatterns) {
            List<String> listMatches = pattern.getMatches(line);
            //if there is no match to the pattern in the line continue
            if (listMatches.isEmpty()) {
                continue;
            }
            //for each match to the pattern
            for (String match : listMatches) {
                //extract the noun phrases from the match
                List<String> nps = Patterns.getNPs(match);
                //get the hypernym from the match by the index of the hypernym
                String hypernym = nps.get(pattern.getIndexHypernym());
                //remove the hypernym from the list of nps to get the hyponyms
                nps.remove(pattern.getIndexHypernym());
                for (String hyponym : nps) {
                    // add the hypernym and hyponym to the database
                    dbHyperHypo.addToDB(hypernym, hyponym);
                }
            }
        }
    }
}