package compiler.syntax.nonTerminal;

import es.uned.lsi.compiler.semantic.type.TypeIF;

public class ValorRango
	extends NonTerminal
{
	private int	valueRange = 0;
	private TypeIF valueType = null;
	private String variableName = null;
	
	/* Constructor por defecto. */
	public ValorRango() {
		super();
	}
	
	/**
	 * Constructor para ValorRango.
	 * @param valueRange	valor para el rango de posiciones
	 */
	public ValorRango (int valueRange) {
		super();
		this.valueRange = valueRange;
	}
	
	/**
	 * Constructor para ValorRango.
	 * @param variableName 	identificador de una variable
	 * @param valueType		tipo de la variable dada
	 */
	public ValorRango (String variableName, TypeIF valueType) {
		super();
		this.variableName = variableName;
		this.valueType = valueType;
	}
	
	/**
	 * Constructor para ValorRango.
	 * @param valueRange	valor para el rango de posiciones
	 * @param valueType		tipo del valor dado
	 */
	public ValorRango (int valueRange, TypeIF valueType) {
		super();
		this.valueRange = valueRange;
		this.valueType = valueType;
	}
	
	/**
	 * Devuelve el valor para el rango de posiciones.
	 * @return valor para el rango de posiciones
	 */
	public int getValue() {
		return this.valueRange;
	}
	
	/**
	 * Establece el valor del rango de posiciones.
	 * @param valueRange	valor para el rango de posiciones
	 */
	public void setValue (int valueRange) {
		this.valueRange = valueRange;
	}

	/**
	 * Establece el tipo del valor del rango.
	 * @param valueType tipo del valor del rango
	 */
	public void setType (TypeIF valueType) {
		this.valueType = valueType;
	}

	/**
	 * Devuelve el tipo del valor del rango.
	 * @return tipo del valor del rango.
	 */
	public TypeIF getType() {
		return valueType;
	}
	
	/**
	 * Devuelve el identificador de la variable dada como valor del rango.
	 * @return identificador de una variable
	 */
	public String getName() {
		return variableName;
	}

	/**
	 * Devuelve un codigo hash para el objeto.
	 * @return codigo hash para el objeto
	 */
	@Override
	public int hashCode() {
		return super.hashCode() + this.valueRange;
	}

	/**
	 * Compara este objeto con otro.
	 * @param other el otro objeto
	 * @return verdadero si ambos objetos son iguales; falso en cualquier otro caso
	 */
	@Override
	public boolean equals (Object other) {
		if (!(other instanceof ValorRango))	return false;
		else {
			ValorRango valorango = (ValorRango) other;
			return (valorango.valueRange == this.valueRange);
		}
	}

	/**
	 * Devuelve una cadena de caracteres que representa al objeto.
	 * @return cadena de caracteres que representa al objeto
	 */
	@Override
	public String toString() {
		return this.getClass().getName() + "@" + Integer.toHexString(this.hashCode());
	}
}