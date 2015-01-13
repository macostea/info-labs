package Model;

import java.util.ArrayList;
import java.util.List;

public class Production {
    private String startingSymbol;
    private String[] terminalSymbols;

    public Production() {

    }

    public Production(String productionString) {
        String[] productionArray = productionString.split(">");
        startingSymbol=productionArray[0];
        terminalSymbols=productionArray[1].split(" ");
    }

    public String getStartingSymbol() {
        return startingSymbol;
    }

    public void setStartingSymbol(String startingSymbol) {
        this.startingSymbol = startingSymbol;
    }


    public String[] getTerminalSymbols() {
        return terminalSymbols;
    }

    public void setTerminalSymbols(String[] terminalSymbols) {
        this.terminalSymbols = terminalSymbols;
    }

    public String toString() {
        String result=this.startingSymbol+" > ";
        for(String token: this.terminalSymbols)
            result+=token+" ";
        return result;
    }

}