package com.cs.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.stream.Stream;

/**
 * Created by mihaicostea on 09/11/14.
 */
public class Grammar {
    private HashSet<String> nonterminals = new HashSet<String>();
    private HashSet<String> terminals = new HashSet<String>();
    private String startingSymbol;
    private HashSet<Production> productions = new HashSet<Production>();

    public Grammar() {

    }

//    public Grammar(Automaton automaton) {
//        for (String state : automaton.getStates()) {
//            this.nonterminals.add(state);
//        }
//        for (String symbol: automaton.getAlphabet()) {
//            this.terminals.add(symbol);
//        }
//        this.startingSymbol = automaton.getStartingState();
//
//        for (Transition transition : automaton.getTransitions()) {
//            Production production = new Production();
//            production.setStartingSymbol(transition.getStartState());
//            production.setDestinationSymbol(transition.getEndState());
//            production.setTerminalSymbol(transition.getSymbol());
//
//            this.productions.add(production);
//            if (automaton.getFinalStates().contains(transition.getEndState())) {
//                Production newProduction = new Production();
//                newProduction.setStartingSymbol(transition.getStartState());
//                newProduction.setTerminalSymbol(transition.getSymbol());
//
//                this.productions.add(newProduction);
//            }
//        }
//
//        if (automaton.getFinalStates().contains(automaton.getStartingState())) {
//            Production production = new Production();
//            production.setStartingSymbol(automaton.getStartingState());
//            production.setTerminalSymbol("empty");
//
//            this.productions.add(production);
//        }
//    }

    public HashSet<String> getNonterminals() {
        return nonterminals;
    }

    public void setNonterminals(HashSet<String> nonterminals) {
        this.nonterminals = nonterminals;
    }

    public HashSet<String> getTerminals() {
        return terminals;
    }

    public void setTerminals(HashSet<String> terminals) {
        this.terminals = terminals;
    }

    public String getStartingSymbol() {
        return startingSymbol;
    }

    public void setStartingSymbol(String startingSymbol) {
        this.startingSymbol = startingSymbol;
    }

    public HashSet<Production> getProductions() {
        return productions;
    }

    public void setProductions(HashSet<Production> productions) {
        this.productions = productions;
    }

    public void readFromFile(String filename) throws IOException {
        Stream<String> lines = Files.lines(Paths.get(filename));

        String linesArray[] = (String[])lines.toArray();
        String nonterminalsFromString[] = linesArray[0].split(" ");
        for (String nonterminal : nonterminalsFromString) {
            this.nonterminals.add(nonterminal);
        }
        String terminalsFromString[] = linesArray[1].split(" ");
        for (String terminal: terminalsFromString) {
            this.terminals.add(terminal);
        }
        this.startingSymbol = linesArray[2];
        for (int i=3; i<linesArray.length; i++) {
            this.productions.add(new Production(linesArray[i]));
        }
    }

    public boolean isRegular() {
        for (Production production : this.productions) {
            if (!this.nonterminals.contains(production.getStartingSymbol())) {
                return false;
            }

            if (production.getDestinationSymbol() != null) {
                if (!this.terminals.contains(production.getTerminalSymbol())) {
                    return false;
                }
            } else if (!this.terminals.contains(production.getTerminalSymbol())) {
                if (!production.getTerminalSymbol().equals("empty")) {
                    return false;
                }
            }
        }

        return true;
    }

    public String toString() {
        String output = new String();
        output += "N={";
        for (String nonterminal : this.getNonterminals()) {
            output += String.format("%s,", nonterminal);
        }
        output += "}" + "\n";

        output += "E={";
        for (String terminal : this.getTerminals()) {
            output += String.format("%s,", terminal);
        }
        output += "}" + "\n";

        output += "P=";
        output += "{";
        for (Production production : this.getProductions()) {
            output += production + "\n";
        }
        output += "}" + "\n";

        output += "S=" + this.getStartingSymbol() + "\n";

        output += "Regular? " + this.isRegular();

        return output;
    }
}
