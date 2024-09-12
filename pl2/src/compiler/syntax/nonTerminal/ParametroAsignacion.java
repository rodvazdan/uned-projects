package compiler.syntax.nonTerminal;

import es.uned.lsi.compiler.semantic.type.TypeIF;

public class ParametroAsignacion
	extends NonTerminal
{
	private Expresion	expression;
	
	/* Constructor por defecto. */
	public ParametroAsignacion() {
		super();
	}
	
	/**
	 * Constructor para ParametroAsignacion.
	 * @param expression	expresion en el lado derecho de la asignacion
	 */
	public ParametroAsignacion (Expresion expression) {
		super();
		this.expression = expression;
	}
	
	/**
	 * Devuelve la expresion en el lado derecho de la asignacion.
	 * @return expresion en el lado derecho de la asignacion
	 */
	public Expresion getExpression() {
		return this.expression;
	}
	
	/**
	 * Establece la expresion en el lado derecho de la asignacion.
	 * @param expression	expresion en el lado derecho de la asignacion
	 */
	public void setExpression (Expresion expression) {
		this.expression = expression;
	}
	
	/**
	 * Devuelve el tipo de la expresion en el lado derecho de la asignacion.
	 * @return tipo de la expresion
	 */
	public TypeIF getType() {
		return this.expression.getType();
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
	 * @return codigo hash para el objeto
	 */
	@Override
	public int hashCode() {
		return 67 * super.hashCode() + this.expression.hashCode();
	}

	/**
	 * Compara este objeto con otro.
	 * @param other	  el otro objeto
	 * @return verdadero si ambos objetos son iguales; falso en cualquier otro caso
	 */
	@Override
	public boolean equals (Object other) {
		if (!(other instanceof ParametroAsignacion)) return false;
		else {
			ParametroAsignacion parametroasignacion = (ParametroAsignacion) other;
			return parametroasignacion.expression.equals(this.expression);
		}
	}
}