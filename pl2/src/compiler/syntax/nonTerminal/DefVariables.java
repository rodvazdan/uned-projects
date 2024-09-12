package compiler.syntax.nonTerminal;

import java.util.ArrayList;

/* Clase para la lista de variables o parametros que van a ser declarados. */

public class DefVariables
	extends NonTerminal
{
	private ArrayList<SelectorValorOReferencia> variables;
	
	/* Constructor por defecto. */
	public DefVariables() {
		super();
		this.variables = new ArrayList<SelectorValorOReferencia>();
	}
	
	/**
	 * Constructor para DefVariables.
	 * @param variables	La lista de variables a declarar.
	 * @param type		El tipo de las variables.
	 */
	public DefVariables (ArrayList<SelectorValorOReferencia> variables) {
		super();
		this.variables = variables;
	}
	
	/**
	 * Devuelve la lista de variables a declarar.
	 * @return la lista de variables a declarar.
	 */
	public ArrayList<SelectorValorOReferencia> getVariables() {
		return variables;
	}
	
	/**
	 * Añade una lista de variables a la lista de variables actual.
	 * @param variables lista de variables a declarar.
	 */
	public void addVariables (ArrayList<SelectorValorOReferencia> variables) {
		for (SelectorValorOReferencia symbol : variables) {
			this.variables.add(symbol);
		}
	}
}