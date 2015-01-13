package Model;

public class Symbol implements Comparable<Symbol> {
	
	private Integer fPos;
	private String content;
	
	//Constructors
	public Symbol(Integer fPos, String content) {
		this.fPos=fPos;
		this.content=content;
	}
	
	public Symbol(String content){
		this.content=content;
		this.fPos=-1;
	}
	
	public Symbol(){
		this.content=null;
		this.fPos=-1;
	}

	//getters, setters
	
	public Integer getPos(){
		return fPos;
		
	}
	
	public String getContent(){
		return content;
	}
	
	public void setPos(Integer pos){
		this.fPos=pos;
	}
	
	public void setContent(String c){
		this.content=c;
	}

	@Override
	public String toString() {
		String res=this.content+" | "+this.fPos+"\n";
		return res;
	}
	
	
	
	//Comparison overrides for lexicographically sorted table
	
	@Override
	public int compareTo(Symbol comp) {
		return this.getContent().compareTo(comp.getContent());
	}
	
	@Override
	public boolean equals(Object comp) {
		if (comp instanceof Symbol) {
			return this.getContent().equals(((Symbol) comp).getContent());
		} else {
			return false;
		}
	}
	
	
	@Override
	public int hashCode() {
		return this.content.hashCode();
	}
	
	
	


}
