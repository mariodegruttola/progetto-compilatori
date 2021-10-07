import java_cup.runtime.Symbol;
import java.util.HashMap;
import java.io.*;
import exception.FatalError;

%%

%unicode
%cupsym sym
%cup
%public
%line
%column

%{
    private HashMap<String, Symbol> symbolTable;
  /**
   * Creates a new scanner with symbol table inizialization.
   * There is also a java.io.InputStream version of this constructor.
   *
   * @param   in  the java.io.Reader to read input from.
   * @param   inizialize  is @true if the symbolTable must be inizialized, @false otherwise
   */
    public Yylex(java.io.Reader in,  boolean inizialize) {
        this.zzReader = in;
        if(inizialize)
            symbolTable = new HashMap<String, Symbol> ();
    }

    public void cleanSymbolTable() throws IOException{
        symbolTable = new HashMap<String, Symbol> ();
    }

    private Symbol installID(String lexeme){
        Symbol symbol =  new Symbol (sym.ID, lexeme);
        symbolTable.put(lexeme, symbol);
        return symbol;
	}

	public HashMap<String, Symbol> getSymbolTable(){

		return symbolTable;
	}
%}

whitespace = [ \r\n\t\f]
letter = [a-zA-Z]
number = (([1-9][0-9]*)|(0))
decimal= (\.([1-9]*[0-9]*[1-9]))
string_const = \"(.[^\"]*)\"
// string_constNotClosed = \"(.[^\"]*)
float_const = {number}{decimal}
int_const = {number}
id = ({letter}| "_")+(({number}*)("_")*({letter}*))*
commentLine = "//".*
commentMultiline = ("/*" ~"*/")
commentMultilineNotClosed = "/*"(.|[^.])
%%

";"	{ return new Symbol(sym.SEMI); }
","	{ return new Symbol(sym.COMMA); }
"int"	{ return new Symbol(sym.INT); }
"string"	{ return new Symbol(sym.STRING); }
"float"	{ return new Symbol(sym.FLOAT); }
"bool"	{ return new Symbol(sym.BOOL); }
"proc"	{ return new Symbol(sym.PROC); }
"("	{ return new Symbol(sym.LPAR); }
")"	{ return new Symbol(sym.RPAR); }
":"	{ return new Symbol(sym.COLON); }
"proc"	{ return new Symbol(sym.PROC); }
"corp"	{ return new Symbol(sym.CORP); }
"void"	{ return new Symbol(sym.VOID); }
"if"	{ return new Symbol(sym.IF); }
"then"	{ return new Symbol(sym.THEN); }
"elif"	{ return new Symbol(sym.ELIF); }
"fi"	{ return new Symbol(sym.FI); }
"else"	{ return new Symbol(sym.ELSE); }
"while"	{ return new Symbol(sym.WHILE); }
"do"	{ return new Symbol(sym.DO); }
"od"	{ return new Symbol(sym.OD); }
"readln"	{ return new Symbol(sym.READ); }
"write"	{ return new Symbol(sym.WRITE); }
":="	{ return new Symbol(sym.ASSIGN); }
"+"	{ return new Symbol(sym.PLUS); }
"-"	{ return new Symbol(sym.MINUS); }
"*"	{ return new Symbol(sym.TIMES); }
"/"	{ return new Symbol(sym.DIV); }
"="	{ return new Symbol(sym.EQ); }
"<>"	{ return new Symbol(sym.NE); }
"<"	{ return new Symbol(sym.LT); }
"<="	{ return new Symbol(sym.LE); }
">"	{ return new Symbol(sym.GT); }
">="	{ return new Symbol(sym.GE); }
"&&"	{ return new Symbol(sym.AND); }
"||"	{ return new Symbol(sym.OR); }
"!"	{ return new Symbol(sym.NOT); }
"null"	{ return new Symbol(sym.NULL); }
"true"	{ return new Symbol(sym.TRUE); }
"false"	{ return new Symbol(sym.FALSE); }
"->"    { return new Symbol(sym.RETURN); }
{id}	{ return this.installID(yytext()); }
{int_const} { return new Symbol(sym.INT_CONST, yytext()); }
{float_const}	{ return new Symbol(sym.FLOAT_CONST, yytext()); }
{string_const}	{ return new Symbol(sym.STRING_CONST, yytext()); }

// Per trovare un errore di stringa non chiusa ci basiamo sull'assunzione che ogni costante stringa debba essere
// contenuta tra due apici. Dunque per tutte le stringhe chiuse correttamente avremo sempre in totale un numero pari di
// apici ("). Nel caso in cui tale numero sia dispari significa che nel codice sorgente c'è una stringa costante non chiusa
// Per far riferimento all'apice (che è un carattere speciale) dobbiamo farlo precedere dal \
"\"" { try { throw new FatalError("Costante Stringa non chiusa"); } catch (FatalError e) { e. printStackTrace(); System.exit(1); } }
// {string_constNotClosed}	{ try { throw new FatalError("Costante Stringa non chiusa"); } catch (FatalError e) { e. printStackTrace(); System.exit(1); } }

{whitespace}    { /* ignore */ }
{commentLine}   { /* ignore */ }
{commentMultiline}   { /* ignore */ }
{commentMultilineNotClosed}   { try { throw new FatalError("Commento non chiuso"); } catch (FatalError e) { e. printStackTrace(); System.exit(1); } }

[^]			{ System.err.println("Errore Lessicale: carattere illegale <" + yytext() + "> sulla linea:colonna -> " + (yyline + 1) + ":" + (yycolumn + 1 + "."));
            System.exit(1); }
<<EOF>>     { return new Symbol(sym.EOF); }