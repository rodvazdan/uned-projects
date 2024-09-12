package compiler.syntax.nonTerminal;

/* Clase para ParametroEscribir. */

public class ParametroEscribir
	extends NonTerminal
{
	private Expresion  expression;
	private String	   parameter = "";
	
	/* Constructor por defecto. */
	public ParametroEscribir() {
		super();
	}
	
	/**
	 * Constructor para ParametroEscribir.
	 * @param expression	expresion aritmetica
	 */
	public ParametroEscribir (Expresion expression) {
		super();
		this.expression = expression;
	}
	
	/**
	 * Constructor para ParametroEscribir.
	 * @param parameter    cadena de caracteres que sera escrita por la salida estandar
	 */
	public ParametroEscribir (String parameter) {
		super();
		this.parameter = parameter;
	}
	
	/**
	 * Devuelve la expresion aritmetica que sera escrita por la salida estandar.
	 * @return expresion aritmetica
	 */
	public Expresion getExpression() {
		return this.expression;
	}
	
	/**
	 * Establece la expresion aritmetica que sera escrita por la salida estandar.
	 * @param expression	expresion aritmetica
	 */
	public void setExpression (Expresion expression) {
		this.expression = expression;
	}
	
	/**
	 * Devuelve la cadena de caracteres que sera escrita por la salida estandar.
	 * @return cadena de caracteres
	 */
	public String getParameter() {
		return this.parameter;
	}
	
	/**
	 * Establece la cadena de caracteres que sera escrita por la salida estandar.
	 * @param parameter    cadena de caracteres
	 */
	public void setParameter (String parameter) {
		this.parameter = parameter;
	}
	
	/**
	 * Devuelve una cadena de caracteres que representa al objeto.
	 * @return cadena de caracteres que representa al objeto
	 */
	@Override
	public String toString() {
		return this.getClass().getName() + "@" + Integer.toHexString(this.hashCode());
	}
	
	/**
	 * Devuelve un codigo hash para el objeto.
	 * @return devuelve un codigo hash para el objeto
	 */
	@Override
	public int hashCode() {
		return 67 * super.hashCode() + this.expression.hashCode();
	}
	
	/**
	 * Compara este objeto con otro.
	 * @param other	   el otro objeto
	 * @return true si ambos objetos son iguales; false en cualquier otro caso
	 */
	@Override
	public boolean equals (Object other) {
		if (!(other instanceof ParametroEscribir)) return false;
		else {
			ParametroEscribir parametroEscribir = (ParametroEscribir) other;
			return parametroEscribir.getExpression().equals(this.getExpression())
				&& parametroEscribir.getIntermediateCode().equals(this.getIntermediateCode());
		}
	}
}