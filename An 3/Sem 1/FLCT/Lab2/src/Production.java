/**
 * Created by mihaicostea on 09/11/14.
 */
public class Production {
    private String startingSymbol;
    private String destinationSymbol;
    private String terminalSymbol;

    public Production() {

    }

    public Production(String productionString) {
        String productionArray[] = productionString.split(" ");
        if (productionArray.length == 3) {
            this.startingSymbol = productionArray[0];
            this.terminalSymbol = productionArray[1];
            this.destinationSymbol = productionArray[2];
        } else {
            this.startingSymbol = productionArray[0];
            this.terminalSymbol = productionArray[1];
        }
    }

    public String getStartingSymbol() {
        return startingSymbol;
    }

    public void setStartingSymbol(String startingSymbol) {
        this.startingSymbol = startingSymbol;
    }

    public String getDestinationSymbol() {
        return destinationSymbol;
    }

    public void setDestinationSymbol(String destinationSymbol) {
        this.destinationSymbol = destinationSymbol;
    }

    public String getTerminalSymbol() {
        return terminalSymbol;
    }

    public void setTerminalSymbol(String terminalSymbol) {
        this.terminalSymbol = terminalSymbol;
    }

    public String toString() {
        if (this.destinationSymbol != null) {
            return this.startingSymbol + " -> " + this.terminalSymbol + this.destinationSymbol;
        } else {
            return this.startingSymbol + " -> " + this.terminalSymbol;
        }
    }

}
