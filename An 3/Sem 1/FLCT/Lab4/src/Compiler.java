import Model.Grammar;
import Utils.RecursiveDescentParser;

import java.io.IOException;

public class Compiler {

	public static void main(String[] args) {
	        Grammar testGrammar=new Grammar();
        try {
            testGrammar.readFromFile("testgrammar.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
       System.out.print(testGrammar);
        TokenScanner tScan=new TokenScanner();
        tScan.readTokens("tokens2.txt");
        tScan.detectTokens("input.txt");
//        System.out.println("\n\nModel.Symbol table:\n________________");
//        tScan.printSymbolTable();

        RecursiveDescentParser parser = new RecursiveDescentParser(testGrammar, tScan.getPIF());
        System.out.println(parser.parse());
	}

}
