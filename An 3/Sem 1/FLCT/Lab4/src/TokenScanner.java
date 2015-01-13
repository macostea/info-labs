import Model.PIFentry;
import Model.Symbol;
import Model.SymbolTable;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;



public class TokenScanner {

    /*
        Token scanner
        - use readTokens( filename ) to read the tokens contained in the minilanguage specification
        (i.e  keywords/operators and their numerical value)
        - use detectTokens(filename) to parse the source code contained in the file and fill out the symbol table and the PIF
     */
	private Map <String,Integer> tokenCodes=null;
	private BufferedReader cReader=null;
	private int errorline=0;
    private ArrayList<PIFentry> PIF=new ArrayList<PIFentry>();
	SymbolTable symbolTable=new SymbolTable();
	
	public ArrayList<PIFentry> getPIF(){
        return this.PIF;
    }

	public void readTokens(String filename){
		tokenCodes=new HashMap<String,Integer>();
		String text;
		String tokens[];
		try {
			cReader=new BufferedReader(new FileReader(filename));
			
			try {
				while(cReader.ready()){
					text=cReader.readLine();
					tokens=text.split(" ");
					tokenCodes.put(tokens[0], Integer.parseInt(tokens[1]));
					
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
	}
	
	public Integer getTokenCode(String s){
		Integer tCode=null;
		tCode=this.tokenCodes.get(s);
		return tCode;
		
	}
	
	public String getTokenType(String s){
		String type=null;
		
		if(tokenCodes.containsKey(s)){
		
		if(getTokenCode(s)>=2 && getTokenCode(s)<=11){
			type="keyword";
		}
		if(getTokenCode(s)>=12 && getTokenCode(s)<=18){
			type="separator";
		}
		if(getTokenCode(s)>=19 && getTokenCode(s)<=29){
			type="operator";
		}
		}
		return type;
		
	}
	
	
	public void printSymbolTable(){
		System.out.println(symbolTable);
	}

    public void printPIF(){
        System.out.println(PIF);
    }
	
	
	private void classifyUserDef(String token){
		
		//Int constant
			if(Pattern.matches("-?[0-9]{1,10}", token)){
				Symbol sAdd=symbolTable.addSymbol(token);
				System.out.println(sAdd.getPos()+" 1"+"  "+token);
                PIF.add(new PIFentry(sAdd.getPos(),1,"IDENTIFIER"));
				return;
			}
		
			
		//String constant
			if(Pattern.matches("\".*\"", token)){
				Symbol sAdd=symbolTable.addSymbol(token);
				System.out.println(sAdd.getPos()+" 1"+"  "+token);
                PIF.add(new PIFentry(sAdd.getPos(),1,"IDENTIFIER"));
				return;
			}
			
		//Variables	
			if(Pattern.matches("[a-zA-Z]\\w{0,7}",token)){
				Symbol sAdd=symbolTable.addSymbol(token);
				System.out.println(sAdd.getPos()+" 0"+"  "+token);
                PIF.add(new PIFentry(sAdd.getPos(),0,"IDENTIFIER"));
				return;
			}
			
			
			//Array decl --nu apare
			if(Pattern.matches("[a-zA-Z]\\w{0,7}\\[[0-9]*\\]",token)){
				Symbol sAdd=symbolTable.addSymbol(token);
				System.out.println(sAdd.getPos()+" 0"+"  "+token);
                PIF.add(new PIFentry(sAdd.getPos(),0,token));
				return;
			}
			
			
				System.out.println("Error: "+token+"on line "+errorline);
			
		
				
	}
	
	public void detectTokens(String filename){
		
		
		String text;
		String tokens[];
		String last;
		int i;
		
		try {
			cReader=new BufferedReader(new FileReader(filename));
			System.out.println("Program internal form");
	
			try {
				while(cReader.ready()){
					text=cReader.readLine();
					System.out.println();
					tokens=text.split(" ");
					
				
					for(i=0;i<tokens.length;i++){
						tokens[i]=tokens[i].trim();
						//System.out.print(" "+tokens[i]);
						
						//operator, keyword or separator;
						if (getTokenType(tokens[i])!=null){
								System.out.println(getTokenCode(tokens[i])+" -1"+"  "+tokens[i]);
                                PIF.add(new PIFentry(getTokenCode(tokens[i]),-1,tokens[i]));
							}
						
						
						if(getTokenType(tokens[i])==null){
							if(tokens[i].charAt(tokens[i].length()-1)==';'){
								tokens[i]=tokens[i].replace(tokens[i].substring(tokens[i].length()-1), "");
								this.classifyUserDef(tokens[i]);
								System.out.println(getTokenCode(";")+" -1"+"  "+";");
                                PIF.add(new PIFentry(getTokenCode(";"),-1,";"));
								
							}
							
							else
								this.classifyUserDef(tokens[i]);
						}
							
								
						
					
					
					}
					errorline++;	
						
					
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
		cReader=null;	
	}
		
		
		
		
}
