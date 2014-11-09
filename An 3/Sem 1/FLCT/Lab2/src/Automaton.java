import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.stream.Stream;

/**
 * Created by mihaicostea on 09/11/14.
 */
public class Automaton {
    private HashSet<String> states = new HashSet<String>();
    private HashSet<String> alphabet = new HashSet<String>();
    private HashSet<Transition> transitions = new HashSet<Transition>();
    private String startingState;
    private HashSet<String> finalStates = new HashSet<String>();

    public Automaton() {

    }

    public Automaton(Grammar grammar) {
        for (String nonterminal : grammar.getNonterminals()) {
            this.getStates().add(nonterminal);
        }
        this.getStates().add("K");

        for (String terminal : grammar.getTerminals()) {
            this.getAlphabet().add(terminal);
        }
        this.setStartingState(grammar.getStartingSymbol());

        for (Production production : grammar.getProductions()) {
            if (production.getTerminalSymbol().equals("empty")) {
                this.finalStates.add(production.getStartingSymbol());
            } else {
                this.transitions.add(new Transition(production));
            }
        }

        this.finalStates.add("K");
    }

    public HashSet<String> getStates() {
        return states;
    }

    public void setStates(HashSet<String> states) {
        this.states = states;
    }

    public HashSet<String> getAlphabet() {
        return alphabet;
    }

    public void setAlphabet(HashSet<String> alphabet) {
        this.alphabet = alphabet;
    }

    public HashSet<Transition> getTransitions() {
        return transitions;
    }

    public void setTransitions(HashSet<Transition> transitions) {
        this.transitions = transitions;
    }

    public String getStartingState() {
        return startingState;
    }

    public void setStartingState(String startingState) {
        this.startingState = startingState;
    }

    public HashSet<String> getFinalStates() {
        return finalStates;
    }

    public void setFinalStates(HashSet<String> finalStates) {
        this.finalStates = finalStates;
    }

    public void readFromFile(String filename) throws IOException {
        Stream<String> lines = Files.lines(Paths.get(filename));

        String linesArray[] = lines.toArray(size -> new String[size]);
        String statesFromString[] = linesArray[0].split(" ");
        for (String state : statesFromString) {
            this.states.add(state);
        }
        String alphabetFromString[] = linesArray[1].split(" ");
        for (String letter: alphabetFromString) {
            this.alphabet.add(letter);
        }
        this.startingState = linesArray[2];
        String finalStatesFromString[] = linesArray[3].split(" ");
        for (String state: finalStatesFromString) {
            this.finalStates.add(state);
        }

        for (int i=4; i<linesArray.length; i++) {
            this.transitions.add(new Transition(linesArray[i]));
        }
    }

    public String toString() {
        String output = new String();

        output += "Q={";
        for (String state : this.getStates()) {
            output += state;
        }
        output += "}" + "\n";

        output += "E={";
        for (String symbol : this.getAlphabet()) {
            output += symbol;
        }
        output += "}" + "\n";

        output += "δ=";
        output += "{" + "\n";
        for (Transition transition : this.getTransitions()) {
            output += transition + "\n";
        }
        output += "}" + "\n";

        output += "q0=" + this.getStartingState() + "\n";

        output += "F={" + "\n";

        for (String finalState : this.finalStates) {
            output += finalState + "\n";
        }

        output += "}";

        return output;
    }
}
