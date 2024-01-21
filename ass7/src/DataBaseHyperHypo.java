import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * The type Data base hyper hypo.
 * A class that represents a database of hypernyms and hyponyms.
 * The database is a map of hypernyms and hyponyms.
 * Each hypernym is mapped to a map of hyponyms and the number of times they appear.
 */
public class DataBaseHyperHypo {
    private final Map<String, Map<String, Integer>> dataBase = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    /**
     * Adding a hypernym and hyponym to the database.
     *
     * @param hypernym a hypernym.
     * @param hyponym  a hyponym.
     */
    public void addToDB(String hypernym, String hyponym) {
        if (!dataBase.containsKey(hypernym)) {
            dataBase.put(hypernym, new TreeMap<>(String.CASE_INSENSITIVE_ORDER));
        }
        //getting the map of hyponyms and the number of times they appear.
        Map<String, Integer> sumRelation = dataBase.get(hypernym);

        //adding the hyponym to the hypernym's map and increasing the
        //number of times it appears in the hypernym by 1.
        sumRelation.put(hyponym, sumRelation.getOrDefault(hyponym, 0) + 1);
    }

    /**
     * printLemmaSumInEachHyper.
     * Printing the number of times a lemma appears in each hypernym.
     *
     * @param lemma a lemma.
     */
    public void printLemmaSumInEachHyper(String lemma) {
        //filtering the hypernyms that contain the lemma out of the database,
        //and sorting them by the number of times the lemma appears in them.
        String toPrint = dataBase.entrySet()
                .stream()
                .filter(e -> e.getValue().containsKey(lemma))
                .sorted((e1, e2) -> e2.getValue().get(lemma) - e1.getValue().get(lemma))
                .map(e -> String.format("%s: (%d)", e.getKey(), e.getValue().get(lemma)))
                .collect(Collectors.joining("\n"));
        // print the results
        if (!toPrint.isEmpty()) {
            System.out.println(toPrint);
        } else {
            System.out.println("The lemma doesn't appear in the corpus.");
        }
    }
}