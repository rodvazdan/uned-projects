package compiler.syntax.nonTerminal;

import java.util.ArrayList;

public class ListaSentencias
	extends NonTerminal
{
	private ArrayList<Sentencia> statements;
	
	/* Constructor por defecto. */
	public ListaSentencias() {
		super();
		statements = new ArrayList<Sentencia>();
	}
	
	/**
	 * Inserta una nueva sentencia a la lista de sentencias.
	 * @param s	 Una sentencia.
	 */
	public void add (Sentencia s) {
		statements.add(0, s);
	}
	
	/**
	 * Devuelve la lista de sentencias.
	 * @return la lista de sentencias.
	 */
	public ArrayList<Sentencia> getStatements() {
		return statements;
	}
}