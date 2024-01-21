import java.io.File;
import java.io.IOException;

/**
 * @author 322638354 Yedidya Peles <\yedpel@gmail.com>.
 * The type Discover hypernym.
 * This class discovers the hypernyms of a given lemma in a given directory.
 */
public class DiscoverHypernym {
    /**
     * The main method.
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        File directory = new File(args[0]);
        String lemma = args[1];

        File[] listFiles = directory.listFiles();

        DataBaseHyperHypo dataBaseHyperHypo = new DataBaseHyperHypo();
        MatcherExtractor matcherExtractor = new MatcherExtractor(dataBaseHyperHypo, Patterns.LIST_PATTERNS);
        try {
            if (listFiles != null) {
                //for each file in the directory extract the hypernyms and hyponyms
                for (File file : listFiles) {
                    matcherExtractor.extractFromFile(file, lemma);
                }
            }
        } catch (IOException e) {
            //if there is no directory or lemma
            System.out.println("Error, please enter a valid directory and lemma");
        }
        //print the results
        dataBaseHyperHypo.printLemmaSumInEachHyper(lemma);
    }
}