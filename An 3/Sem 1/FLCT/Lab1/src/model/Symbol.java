package model;

public class Symbol implements Comparable<Symbol>{
	private Integer code;
	private String identifier;
	
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	
	public Symbol(Integer code, String identifier) {
		this.code = code;
		this.identifier = identifier;
	}
	
	@Override
	public int compareTo(Symbol o) {
		return this.getIdentifier().compareTo(o.getIdentifier());
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof Symbol) {
			return this.identifier.equals(((Symbol) o).identifier);
		} else {
			return false;
		}
	}
	
	@Override
	public String toString() {
		return String.format("Code: %d, Identifier: %s\n", this.code, this.identifier);
	}
	
	@Override
	public int hashCode() {
		return this.identifier.hashCode();
	}
}
