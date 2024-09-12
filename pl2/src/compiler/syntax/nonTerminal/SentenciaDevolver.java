package compiler.syntax.nonTerminal;

import es.uned.lsi.compiler.semantic.type.TypeIF;

public class SentenciaDevolver
	extends NonTerminal
{
	private Expresion expression;
	private TypeIF expressionType;
	
	/* Constructor por defecto. */
	public SentenciaDevolver() {
		super();
	}
	
	/**
	 * Constructor para SentenciaDevolver.
	 * @param expression	  expresion que se va a devolver
	 * @param expressionType  tipo de la expresion que se va a devolver
	 */
	public SentenciaDevolver (Expresion expression, TypeIF type) {
		super();
		this.expression = expression;
		this.expressionType = type;
	}
	
	/**
	 * Establece la expresion que se va a devolver.
	 * @param expression expresion que se va a devolver.
	 */
	public void setExpression (Expresion expression) {
		this.expression = expression;
	}
	
	/**
	 * Devuelve la expresion que se va a devolver.
	 * @return expresion que se va a devolver.
	 */
	public Expresion getExpression() {
		return expression;
	}
	
	/**
	 * Establece el tipo de la expresion que se va a devolver.
	 * @param expressionType el tipo de la expresion que se va a devolver.
	 */
	public void setType (TypeIF type) {
		this.expressionType = type;
	}
	
	/**
	 * Devuelve el tipo de la expresion que se va a devolver.
	 * @return el tipo de la expresion que se va a devolver.
	 */
	public TypeIF getType() {
		return expressionType;
	}
}