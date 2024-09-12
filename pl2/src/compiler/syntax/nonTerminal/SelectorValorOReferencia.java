package compiler.syntax.nonTerminal;

import es.uned.lsi.compiler.semantic.type.TypeIF;

/* Clase para SelectorValorOReferencia. */

public class SelectorValorOReferencia
	extends NonTerminal
{
	private String name = null;
	private TypeIF type = null;
	private ValorConstante value = null;
	
	/* Constructor por defecto. */
	public SelectorValorOReferencia() {
		super();
	}
	
	/**
	 * Constructor for SelectorValorOReferencia.
	 * @param name El nombre del simbolo.
	 */
	public SelectorValorOReferencia (String name) {
		super();
		this.name = name;
	}
	
	/**
	 * Constructor for SelectorValorOReferencia.
	 * @param type El tipo del simbolo.
	 */
	public SelectorValorOReferencia (TypeIF type) {
		super();
		this.type = type;
	}
	
	/**
	 * Constructor for SelectorValorOReferencia.
	 * @param name El nombre del simbolo.
	 * @param type El tipo del simbolo.
	 */
	public SelectorValorOReferencia (String name, TypeIF type) {
		super();
		this.name = name;
		this.type = type;
	}
	
	/**
	 * Constructor for SelectorValorOReferencia.
	 * @param value El valor del simbolo.
	 * @param type El tipo del simbolo.
	 */
	public SelectorValorOReferencia (ValorConstante value, TypeIF type) {
		super();
		this.value = value;
		this.type = type;
	}
	
	/**
	 * Constructor for SelectorValorOReferencia.
	 * @param mvector	Un elemento de un vector.
	 */
	public SelectorValorOReferencia (MiembroVector mvector) {
		super();
		this.name = mvector.getName();
		this.type = mvector.getType();
		this.value = new ValorConstante(Integer.toString(mvector.getValue()));
	}
	
	/**
	 * Devuelve el nombre del simbolo.
	 * @return el nombre del simbolo.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Establece el nombre del simbolo.
	 * @param name El nombre del simbolo.
	 */
	public void setName (String name) {
		this.name = name;
	}
	
	/**
	 * Devuelve el tipo del simbolo.
	 * @return el tipo del simbolo.
	 */
	public TypeIF getType() {
		return type;
	}
	
	/**
	 * Establece el tipo del simbolo.
	 * @param name El tipo del simbolo.
	 */
	public void setType (TypeIF type) {
		this.type = type;
	}
	
	/**
	 * Devuelve el valor del simbolo.
	 * @return el valor del simbolo.
	 */
	public String getValue() {
		if (value != null) {
			return value.getValue();
		} else {
			return "";
		}
	}
	
	/**
	 * Establece el valor del simbolo.
	 * @param value El valor del simbolo.
	 */
	public void setValue (ValorConstante value) {
		this.value = value;
	}
}