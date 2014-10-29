package main;

import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.Symbol;
import model.SymbolTable;
import definitions.CodificationsDefinitions;

public class Main {
	private static CodificationsDefinitions codes = new CodificationsDefinitions();
	private static SymbolTable symbolTable = new SymbolTable();
	
	public static void main(String[] args) throws IOException {
		if (args.length == 0) {
			System.out.println("Usage: Main.java f1,... \n f1,...  - List of files to be scanned");
		}
		
		for (String arg : args) {
			FileReader inputStream = null;
			
			try {
				inputStream = new FileReader(arg);
				int c;
				
				String lastBuffer = new String();
				String buffer = new String();
				
				while ((c = inputStream.read()) != -1) {
					if ((char)c != ' ' && (char)c != '\n' && !codes.isSeparator((char)c)) {
						buffer += (char)c;
					} else {
						Integer code = codes.codeForString(buffer);
						String value = "-1";
						if (code == null) {
							Symbol symbol = symbolTable.getSymbolForIdentifier(buffer);
							if (symbol != null) {
								code = symbol.getCode();
							} else {
								// Declaration
								if (buffer.length() <= 8) { // specification
									if (lastBuffer.equals("var")) {
										Symbol newSymbol = symbolTable.addIdentifier(buffer);
										code = 0; // identifier
										value = newSymbol.getCode().toString();
									} else if (lastBuffer.equals("let")) {
										Symbol newSymbol = symbolTable.addIdentifier(buffer);
										code = 1; // constant
										value = newSymbol.getCode().toString();
									}
								}
								
								// Random Identifier
								if (buffer.length() <= 8 && buffer.length() > 0) {
									Pattern p = Pattern.compile("[0-9].*");
									Matcher m = p.matcher(buffer);
									
									// Identifier
									if (!m.matches()) {
										Symbol newSymbol = symbolTable.addIdentifier(buffer);
										code = 0; // identifier
										value = newSymbol.getCode().toString();
									}
									
								} else { // Constants
									// String constant
									Pattern p = Pattern.compile("\".*\"");
									Matcher m = p.matcher(buffer);
									
									if (m.matches()) {
										Symbol newSymbol = symbolTable.addIdentifier(buffer.substring(1, buffer.length() - 1));
										code = 1; // constant
										value = newSymbol.getCode().toString(); 
									}
									
									// Integer constant
									p = Pattern.compile("[0-9]*");
									m = p.matcher(buffer);
									
									if (m.matches()) {
										Symbol newSymbol = symbolTable.addIdentifier(buffer);
										code = 1; // constant
										value = newSymbol.getCode().toString(); 
									}
								}
							}
						}
						
						if (code != null) {
							System.out.println(String.format("Code: %d, value: %s", code, value));
						}
						
						lastBuffer = new String(buffer);
						buffer = new String();
					}
				}	
			} finally {
				System.out.println("-------\nSymbol Table:\n");
				System.out.println(symbolTable);
				if (inputStream != null) {
					inputStream.close();
				}
			}
		}
	}

}
