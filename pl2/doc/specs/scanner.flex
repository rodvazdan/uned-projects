package compiler.lexical;
import compiler.syntax.sym;
import compiler.lexical.Token;
import es.uned.lsi.compiler.lexical.ScannerIF;
import es.uned.lsi.compiler.lexical.LexicalError;
import es.uned.lsi.compiler.lexical.LexicalErrorManager;



%%

%public
%class Scanner
%char
%line
%column
%cup
%ignorecase
%unicode



%implements ScannerIF
%scanerror LexicalError

// incluir aqui, si es necesario otras directivas
%{



LexicalErrorManager lexicalErrorManager = new LexicalErrorManager ();



 Token createToken (int x)	{
 	Token token = new Token (x);
 	 token.setLine (yyline + 1);
 	 token.setColumn (yycolumn + 1);
 	 token.setLexema (yytext ());
 	 return token;
}

%}  


//expresiones:


FIN_LINEA = \r|\n|\r\n
ESPACIO_BLANCO = [ \t\r\n\f]
ID = (_[a-zA-Z0-9]|[a-zA-Z])([a-zA-Z0-9_])*
NUM = 0|[1-9][0-9]*
CADENA_TEXTO = \"([^\"])*\"





%state COMMENT



%%

<YYINITIAL> 
{


//Declaracion de tokens:

	"("  		{return createToken (sym.PIZQUIERDO);}
	")"  		{return createToken (sym.PDERECHO);}
	"*"  		{return createToken (sym.PRODUCTO);}
	"+"  		{return createToken (sym.SUMA);}
	","  		{return createToken (sym.DIDENTIFICADORES);}
	"."  		{return createToken (sym.FINPROGRAMA);}
	".."  		{return createToken (sym.RANGO);}
	":"  		{return createToken (sym.DELIMFUNC);}
	";"  		{return createToken (sym.DSENTENCIAS);}
	"<"  		{return createToken (sym.MENORQUE);}
	"="  		{return createToken (sym.ASIGNACION);}
	"=="  		{return createToken (sym.IGUALDAD);}
	"subprogramas"  		{return createToken (sym.COMIENZOSUBPROGRAMAS);}
	"["  		{return createToken (sym.CIZQUIERDO);}
	"]"  		{return createToken (sym.CDERECHO);}
	"booleano"  		{return createToken (sym.BOOLEANO);}
	"cierto"  		{return createToken (sym.CIERTO);}
	"comienzo"  		{return createToken (sym.COMIENZO);}
	"constantes"  		{return createToken (sym.BCONSTANTES);}
	"de"  		{return createToken (sym.ASIGNACIONVECTOR);}
	"devolver"  		{return createToken (sym.FRETURN);}
	"en"  		{return createToken (sym.RANGOBUCLEPARA);}
	"entero"  		{return createToken (sym.ENTERO);}
	"entonces"  		{return createToken (sym.STARTIF);}
	"escribir"  		{return createToken (sym.ESCRIBIR);}
	"falso"  		{return createToken (sym.FALSO);}
	"fin"  		{return createToken (sym.FINBLOQUESENTENCIAS);}
	"funcion"  		{return createToken (sym.DECLAREFUNCION);}
	"no"  		{return createToken (sym.NOLOGICO);}
	"para"  		{return createToken (sym.COMIENZOBUCLEPARA);}
	"procedimiento"  		{return createToken (sym.COMIENZOPROCEDIMIENTO);}
	"programa"  		{return createToken (sym.COMIENZOPROGRAMA);}
	"si"  		{return createToken (sym.COMIENZOSI);}
	"sino"  		{return createToken (sym.ALTERNATIVASI);}
	"tipos"  		{return createToken (sym.COMIENZODECLTIPOS);}
	"var"  		{return createToken (sym.PARAMETROVALOR);}
	"variables"  		{return createToken (sym.DECLVARIABLES);}
	"vector"  		{return createToken (sym.DECLVECTOR);}
	"y"  		{return createToken (sym.YLOGICA);}
	{ID}  		{return createToken (sym.IDEN);}
	{NUM}  		{return createToken (sym.NUM);}
	{CADENA_TEXTO}	{return createToken (sym.STRING);}

	


	"#"	           { yybegin(COMMENT); }



{ESPACIO_BLANCO}	{}





[^] {
		LexicalError error = new LexicalError ();
		error.setLine (yyline + 1);
		error.setColumn (yycolumn + 1);
		error.setLexema (yytext ());
		lexicalErrorManager.lexicalError (error);
		lexicalErrorManager.lexicalFatalError("Token no reconocido: " + yytext());
	}
	
	



 }
<COMMENT>{

	{FIN_LINEA} { yybegin(YYINITIAL); }
	[^] {}

}

	 









