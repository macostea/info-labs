/**
 * Created by mihaicostea on 09/11/14.
 */
package com.cs.model;

public class Production {
    private Character startingSymbol;
    private Character destinationSymbol;
    private Character terminalSymbol;

    public Production() {

    }

    public Production(String productionString) {
        Character[] productionArray = productionString.split(" ");
        if (productionArray.length == 3) {
            this.startingSymbol = productionArray[0];
            this.terminalSymbol = productionArray[1];
            this.destinationSymbol = productionArray[2];
        } else {
            this.startingSymbol = productionArray[0];
            this.terminalSymbol = productionArray[1];
        }
    }

    public Character getStartingSymbol() {
        return startingSymbol;
    }

    public void setStartingSymbol(Character startingSymbol) {
        this.startingSymbol = startingSymbol;
    }

    public Character getDestinationSymbol() {
        return destinationSymbol;
    }

    public void setDestinationSymbol(Character destinationSymbol) {
        this.destinationSymbol = destinationSymbol;
    }

    public Character getTerminalSymbol() {
        return terminalSymbol;
    }

    public void setTerminalSymbol(Character terminalSymbol) {
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
