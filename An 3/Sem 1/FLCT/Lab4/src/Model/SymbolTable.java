package Model;

import Model.Symbol;
import Utils.LexSortList;

public class SymbolTable {
	private LexSortList<Symbol> table = new LexSortList<Symbol>();
	private Integer index = 0;
	
	public Symbol addSymbol(String content) {
		Symbol search = this.getSymbol(content);
		
		if (search != null) {
			return search;
		} else {
			Symbol symbol = new Symbol(index, content);
			this.table.addElement(symbol);
			
			this.index++;
			return symbol;
		}
	}
	
	public Symbol getSymbol(String content) {
		Symbol search = new Symbol(0, content);
		
		return this.table.findElement(search);
	}
	
	@Override
	public String toString() {
		return this.table.toString();
	}
}