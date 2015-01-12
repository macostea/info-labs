package com.cs;

import com.cs.model.Grammar;
import com.cs.model.ParserState;

import java.util.List;
import java.util.Stack;

/**
 * Created by mihaicostea on 12/01/15.
 */
public class RecursiveDescentParsing {
    private Grammar grammar;
    private List<Integer> pif;

    public RecursiveDescentParsing(Grammar grammar, List<Integer> pif) {
        this.grammar = grammar;
        this.pif = pif;
    }

    public String parse() {
        ParserState parserState = ParserState.NORMAL;
        int i = 0;
        Stack<Object> alpha = new Stack<Object>();
        Stack<Object> beta = new Stack<Object>();
        beta.push(this.grammar.getStartingSymbol());

        while ((parserState != ParserState.FINISH) && (parserState != ParserState.ERROR)) {
            if (parserState == ParserState.NORMAL) {
                if (beta.isEmpty() && (i == this.pif.size())) {
                    parserState = ParserState.FINISH;
                } else {

                }
            }
        }
    }
}
