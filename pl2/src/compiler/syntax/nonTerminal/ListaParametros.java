package compiler.syntax.nonTerminal;

import java.util.ArrayList;

public class ListaParametros
	extends NonTerminal
{
	private ArrayList<SelectorValorOReferencia> parameters;
	
	/* Constructor por defecto. */
	public ListaParametros() {
		super();
		parameters = new ArrayList<SelectorValorOReferencia>();
	}
	
	/**
	 * Constructor para ListaParametros.
	 * @param parameters lista de parametros.
	 */
	public ListaParametros (ArrayList<SelectorValorOReferencia> parameters) {
		this.parameters = parameters;
	}
	
	/**
	 * Inserta un simbolo a la lista de parametros actual.
	 * @param s un simbolo.
	 */
	public void add (SelectorValorOReferencia s) {
		parameters.add(0, s);
	}
	
	/**
	 * Devuelve la lista de parametros
	 * @return la lista de parametros.
	 */
	public ArrayList<SelectorValorOReferencia> getParameters() {
		return parameters;
	}
}