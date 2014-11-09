/**
 * Created by mihaicostea on 09/11/14.
 */
public class Transition {
    private String startState;
    private String endState;
    private String symbol;

    public Transition(String transitionString) {
        String transitionArray[] = transitionString.split(" ");
        this.startState = transitionArray[0];
        this.symbol = transitionArray[1];
        this.endState = transitionArray[2];
    }

    public Transition(Production production) {
        this.startState = production.getStartingSymbol();
        this.symbol = production.getTerminalSymbol();

        if (production.getDestinationSymbol() != null) {
            this.endState = production.getDestinationSymbol();
        } else {
            this.endState = "K";
        }
    }

    public String getStartState() {
        return startState;
    }

    public void setStartState(String startState) {
        this.startState = startState;
    }

    public String getEndState() {
        return endState;
    }

    public void setEndState(String endState) {
        this.endState = endState;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String toString() {
        return "δ(" + this.startState + ", " + this.symbol + ") = " + this.endState;
    }

}
