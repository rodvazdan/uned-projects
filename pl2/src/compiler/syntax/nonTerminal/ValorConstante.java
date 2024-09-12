package compiler.syntax.nonTerminal;

public class ValorConstante
	extends NonTerminal
{
	private String type = null;
	private String value = null;
	
	/* Constructor por defecto. */
	public ValorConstante() {
		super();
	}
	
	/**
	 * Constructor para ValorConstante.
	 * @param value Un valor constante.
	 */
	public ValorConstante (String value) {
		super();
		this.value = value;
	}
	
	/**
	 * Constructor para ValorConstante.
	 * @param type	El tipo del valor constante.
	 * @param value Un valor constante.
	 */
	public ValorConstante (String type, String value) {
		super();
		this.type  = type;
		this.value = value;
	}
	
	/**
	 * Devuelve el tipo del valor constante.
	 * @return el tipo del valor constante.
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * Devuelve el valor constante.
	 * @return el valor constante.
	 */
	public String getValue() {
		return value;
	}
}
