package Utils;

import Model.*;


import java.util.List;
import java.util.Stack;

/**
 * Created by mihaicostea on 12/01/15.
 */

public class RecursiveDescentParser {
    private Grammar grammar;
    private List<PIFentry> pif;

    public RecursiveDescentParser(Grammar grammar, List<PIFentry> pif) {
        this.grammar = grammar;
        this.pif = pif;
    }
    public String parse() {
        String message = "muiecacap";

        ParserState parserState = ParserState.NORMAL;
        int i = 0;
        Stack<Object> alpha = new Stack<Object>();
        Stack<String> beta = new Stack<String>();
        beta.push(this.grammar.getStartingSymbol());
        List<Production> currentProductionList = null;
        int currentProductionIndex = 0;


        while ((parserState != ParserState.FINISH) && (parserState != ParserState.ERROR)) {
            if (parserState == ParserState.NORMAL) {
                if (beta.isEmpty() && (i == this.pif.size())) {
                    parserState = ParserState.FINISH;
                } else {
                    String betaTop = beta.peek();
                    if (this.grammar.symbolIsNonterminal(betaTop)) {
                        currentProductionIndex = 0;
                        currentProductionList = this.grammar.productionsForSymbol(betaTop);
                        Production firstProduction = currentProductionList.get(currentProductionIndex);
                        alpha.push(firstProduction);
                        beta.pop();
                        for (String symbol : firstProduction.getTerminalSymbols()) {
                            beta.push(symbol);
                        }
                    } else if (i < this.pif.size() && this.grammar.symbolIsTerminal(betaTop) && betaTop.equals(this.pif.get(i).value())) {
                        alpha.push(this.pif.get(i).value());
                        beta.pop();
                        i++;
                    } else {
                        // FIND A DIFFERENT WAY TO CHECK THE END OF THE EXPRESSION
                        if (i == this.pif.size()) {
                            i--;
                        }
                        parserState = ParserState.BACK;
                    }
                }
            } else if (parserState == ParserState.BACK) {
                if (alpha.peek().equals(this.pif.get(i).value())) {
                    alpha.pop();
                    beta.push(this.pif.get(i).value());
                    i--;
                } else {
                    if (currentProductionIndex + 1 < currentProductionList.size()) {
                        currentProductionIndex++;
                        parserState = ParserState.NORMAL;
                        alpha.pop();
                        alpha.push(currentProductionList.get(currentProductionIndex));
                        for (int it = currentProductionList.get(currentProductionIndex - 1).getTerminalSymbols().length - 1; it >= 0; it--) {
                            if (beta.peek().equals(currentProductionList.get(currentProductionIndex - 1).getTerminalSymbols()[it])) {
                                beta.pop();
                            }
                        }
                        for (String symbol : currentProductionList.get(currentProductionIndex).getTerminalSymbols()) {
                            beta.push(symbol);
                        }
                    } else {
                        String currentStartingSymbol = currentProductionList.get(currentProductionIndex).getStartingSymbol();
                        if (i == 1 && currentStartingSymbol.equals(this.grammar.getStartingSymbol())) {
                            parserState = ParserState.ERROR;
                        } else {
                            alpha.pop();
                            String[] terminalSymbols = currentProductionList.get(currentProductionIndex).getTerminalSymbols();
                            for (int it = terminalSymbols.length - 1; it >= 0; it--) {
                                if (beta.peek().equals(currentProductionList.get(currentProductionIndex).getTerminalSymbols()[it])) {
                                    beta.pop();
                                }
                            }
                            beta.push(currentStartingSymbol);

                            // Recreate the currentProductionList
                            if (alpha.peek().getClass().equals(Production.class)) {
                                currentProductionList = this.grammar.productionsForSymbol(((Production) alpha.peek()).getStartingSymbol());
                                currentProductionIndex = currentProductionList.indexOf(alpha.peek());
                            }
                        }
                    }
                }
            }
        }

        if (parserState == ParserState.ERROR) {
            message = "error";
        } else if (parserState == ParserState.FINISH) {
            // do amazing things
        }
        return message;
    }
}

