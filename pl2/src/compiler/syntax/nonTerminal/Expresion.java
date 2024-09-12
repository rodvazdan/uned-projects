package compiler.syntax.nonTerminal;

import es.uned.lsi.compiler.semantic.type.TypeIF;

public class Expresion
	extends NonTerminal
{
	private Expresion	leftExpression, rightExpression;
	private boolean		isIdentifier = false;
	private String		name = null;
	private TypeIF 		type;
	private String		value = null;

	/* Constructor por defecto. */
	public Expresion() {
		super();
	}
	
	/**
	 * Constructor para Expresion.
	 * @param leftExpression	expresion aritmetica o logica
	 */
	public Expresion (Expresion leftExpression) {
		super();
		this.leftExpression = leftExpression;
	}
	
	/**
	 * Constructor para Expresion.
	 * @param leftExpression	subexpresion en el lado izquierdo
	 * @param rightExpression	subexpresion en el lado derecho
	 */
	public Expresion (Expresion leftExpression, Expresion rightExpression) {
		super();
		this.leftExpression  = leftExpression;
		this.rightExpression = rightExpression;
	}
	
	/**
	 * Constructor para Expresion.
	 * @param name	nombre de un identificador de variable
	 */
	public Expresion (String name) {
		this.name = name;
		isIdentifier = true;
	}
	
	/**
	 * Constructor para Expresion.
	 * @param type	tipo de la expresion. Debe ser entero o logico.
	 */
	public Expresion (TypeIF type) {
		this.type = type;
	}
		
	/**
	 * Constructor para Expresion.
	 * @param name	nombre de un identificador de variable
	 * @param type	tipo de la expresion. Debe ser entero o logico
	 */
	public Expresion (String name, TypeIF type) {
		super();
		this.name = name;
		this.type = type;
		isIdentifier = true;
	}
	
	/**
	 * Constructor para Expresion.
	 * @param name	 nombre de un identificador de variable
	 * @param type	 tipo de la expresion. Debe ser entero o logico
	 * @param value	 valor de la expresion
	 */
	public Expresion (String name, TypeIF type, String value) {
		super();
		this.name  = name;
		this.type  = type;
		this.value = value;
		isIdentifier = true;
	}
	
	/**
	 * Constructor para Expresion.
	 * @param expression	expresion aritmetica o logica
	 * @param type       	tipo de la expresion. Debe ser entero o logico
	 */
	public Expresion (Expresion expression, TypeIF type) {
		super();
		this.leftExpression = expression;
		this.type           = type;
	}
	
	/**
	 * Constructor para Expresion.
	 * @param leftExpression  	subexpresion en el lado izquierdo
	 * @param rightExpression	subexpresion en el lado derecho
	 * @param type           	tipo de la expresion. Debe ser entero o logico.
	 */
	public Expresion (Expresion leftExpression,
					  Expresion rightExpression,
					  TypeIF type) {
		super();
		this.leftExpression  = leftExpression;
		this.rightExpression = rightExpression;
		this.type            = type;
	}
	
	/**
	 * Devuelve el nombre de un identificador de variable.
	 * @return nombre de un identificador
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Establece el nombre de un identificador de variable.
	 * @param name	nombre de un identificador
	 */
	public void setName (String name) {
		this.name = name;
		isIdentifier = true;
	}
	
	/**
	 * Comprueba si la expresión es un identificador o no.
	 * @return true si la expresion es un identificador; false en cualquier otro caso
	 */
	public boolean isIdentifier() {
		return isIdentifier;
	}
	
	/**
	 * Devuelve el tipo de la expresion.
	 * @return tipo de la expresion
	 */
	public TypeIF getType() {
		return this.type;
	}
	
	/**
	 * Establece el tipo de la expresion.
	 * @param type	tipo de la expresion. Debe ser entero o logico
	 */
	public void setType (TypeIF type) {
		this.type = type;
	}
	
	/**
	 * Devuelve el valor de la expresion.
	 * @return valor de la expresion
	 */
	public String getValue() {
		return this.value;
	}
	
	/**
	 * Establece el valor de la expresion.
	 * @param value	 valor de la expresion
	 */
	public void setValue (String value) {
		this.value = value;
	}

	/**
	 * Devuelve la subexpresion en el lado izquierdo de la expresion.
	 * @return la subexpresion en el lado izquierdo
	 */
	public Expresion getLeftExpression() {
		return this.leftExpression;
	}
	
	/**
	 * Establece la subexpresion en el lado izquierdo de la expresion.
	 * @param leftExpression	la subexpresion en el lado izquierdo
	 */
	public void setLeftExpression (Expresion leftExpression) {
		this.leftExpression = leftExpression;
	}
	
	/**
	 * Devuelve la subexpresion en el lado derecho de la expresion.
	 * @return la subexpresion en el lado derecho
	 */
	public Expresion getRightExpression() {
		return this.rightExpression;
	}
	
	/**
	 * Establece la subexpresion en el lado derecho de la expresion.
	 * @param rightExpression	la subexpresion en el lado derecho
	 */
	public void setRightExpression (Expresion rightExpression) {
		this.rightExpression = rightExpression;
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
	 * Devuelve un código hash para el objeto.
	 * @return código hash para el objeto
	 */
	@Override
	public int hashCode() {
		return 67 * 67 * 67 * 67 * 67 * super.hashCode() + 67 * 67 * 67 * 67 * this.name.hashCode() + 67 * 67 * 67 * this.value.hashCode()
			+ 67 * 67 * this.leftExpression.hashCode() + 67 * this.rightExpression.hashCode() + this.type.hashCode();
	}

	/**
	 * Compara este objeto con otro.
	 * @param other	   el otro objeto
	 * @return true si ambos objetos son iguales; false en cualquier otro caso
	 */
	@Override
	public boolean equals (Object other)
	{
		if (!(other instanceof Expresion)) return false;
		else {
			Expresion expression = (Expresion)other;
			return expression.getType().getName().equals(this.getType().getName());
		}
	}
}