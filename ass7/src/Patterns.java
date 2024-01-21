import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The type Patterns.
 * This class contains the patterns to search for in the text.
 * It also contains method that checks if the given line matches to the regex pattern.
 */
public class Patterns {
    public static final List<Patterns> LIST_PATTERNS =
            List.of(new Patterns("such NP as NP( , NP)*( (, )?(and|or) NP)?", 0),
                    new Patterns("NP (, )?including NP( (, )NP)*( (, )?(and|or) NP)?", 0),
                    new Patterns("NP (, )?especially NP( (, )NP)*( (, )?(and|or) NP)?", 0),
                    new Patterns("NP (, )?such as NP( (, )NP)*( (, )?(and|or) NP)?", 0),
                    new Patterns("NP (, )?which is ((an example|a kind|a class) of )?NP", 1));


    private static final String NP_REGEX = "<np>([^<]*)</np>";

    private final Pattern pattern;
    private final int indexHypernym;

    /**
     * Constructor.
     *
     * @param patternRegex  the pattern.
     * @param indexHypernym the index of the hypernym.
     */
    public Patterns(String patternRegex, int indexHypernym) {
        this.pattern = Pattern.compile(patternRegex.replace("NP", NP_REGEX));
        this.indexHypernym = indexHypernym;
    }

    /**
     * @param match a sentence.
     * @return list of all NPs in the sentence.
     */
    public static List<String> getNPs(String match) {
        //find all noun phrases in the match
        Matcher matcher = Pattern.compile(NP_REGEX).matcher(match);
        List<String> nounPhrases = new ArrayList<>();
        while (matcher.find()) {
            //add the noun phrase without the <np> tags
            nounPhrases.add(matcher.group(1));
        }
        return nounPhrases;
    }

    /**
     * @return hypernymIndex.
     */
    public int getIndexHypernym() {
        return indexHypernym;
    }

    /**
     * this method checks if the given line matches to the regex pattern.
     * if it does, it returns the match.
     *
     * @param line a line.
     * @return list of all sentences that match the regex.
     */
    public List<String> getMatches(String line) {
        Matcher matcher = pattern.matcher(line);

        List<String> matchesList = new ArrayList<>();
        while (matcher.find()) {
            matchesList.add(matcher.group());
        }
        return matchesList;
    }
}