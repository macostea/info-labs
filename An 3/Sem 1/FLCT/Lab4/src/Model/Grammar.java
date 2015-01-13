package Model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
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
        String text;
        String tokens[];
        BufferedReader cReader;

             cReader = new BufferedReader(new FileReader(filename));
             text = cReader.readLine();    //nonterminals
             tokens = text.split(" ");
             for (String token : tokens) {
                 this.nonterminals.add(token);
             }

             text = cReader.readLine();    //terminals
             tokens = text.split(" ");
             for (String token : tokens) {
                 this.terminals.add(token);
             }

             text = cReader.readLine();    //starting symbol
             this.setStartingSymbol(text);


             while (cReader.ready()) {
                 text = cReader.readLine();
                 this.productions.add(new Production(text));
             }



    }

    public boolean symbolIsTerminal(String symbol) {
        return this.terminals.contains(symbol);
    }

    public boolean symbolIsNonterminal(String symbol) {
        return this.nonterminals.contains(symbol);
    }

    public List<Production> productionsForSymbol(String symbol) {
        List<Production> productions = new ArrayList<Production>();
        for (Production production : this.productions) {
            if (production.getStartingSymbol().equals(symbol)) {
                productions.add(production);
            }
        }

        return productions;
    }

  /*  public boolean isRegular() {
        for (Model.Production production : this.productions) {
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
    */

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

        return output;
    }
}