package model;

import utils.SortedTable;

public class SymbolTable {
	private SortedTable<Symbol> table = new SortedTable<Symbol>();
	private Integer currentSymbolCode = 200;
	
	public Symbol addIdentifier(String identifier) {
		Symbol foundSymbol = this.getSymbolForIdentifier(identifier);
		
		if (foundSymbol != null) {
			return foundSymbol;
		} else {
			Symbol symbol = new Symbol(currentSymbolCode, identifier);
			this.table.addElement(symbol);
			
			this.currentSymbolCode++;
			
			return symbol;
		}
	}
	
	public Symbol getSymbolForIdentifier(String identifier) {
		Symbol toFind = new Symbol(0, identifier);
		
		return this.table.findElement(toFind);
	}
	
	@Override
	public String toString() {
		return this.table.toString();
	}
}
