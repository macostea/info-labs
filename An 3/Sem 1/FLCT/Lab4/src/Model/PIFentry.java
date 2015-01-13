package Model;

public class PIFentry
{
    private final int position; // keyword/op token code ( from tokens.txt) , or position in the symbol table ( if identifier/constant)
    private final int type;     //-1 if keyword, 0 if variable, 1 if constant
    private String value;   // the String representing the token (e.g "int", "while", "thisisastringconstant","1001)

    public PIFentry(int pos, int t,String val)
    {
        position=pos;
        type=t;
        value=val;
    }

    public int position()   { return position; }
    public int type() { return type; }
    public String value(){ return value;}

    @Override
    public String toString(){
        return position+" "+value;
    }
}