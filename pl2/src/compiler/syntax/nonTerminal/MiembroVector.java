package compiler.syntax.nonTerminal;

import es.uned.lsi.compiler.semantic.type.TypeIF;

/* Representa el acceso a un vector. */

public class MiembroVector
	extends NonTerminal
{
	private String    name;
	private TypeIF	  type = null;
	private int		  valueRange = 0;
	
	/* Constructor por defecto. */
	public MiembroVector() {
		super();
	}
	
	/**
	 * Constructor para MiembroVector.
	 * @param name	nombre del vector
	 */
	public MiembroVector (String name) {
		this.name = name;
	}
	
	/**
	 * Constructor para MiembroVector.
	 * @param type	tipo del vector
	 */
	public MiembroVector (TypeIF type) {
		this.type = type;
	}
	
	/**
	 * Constructor para MiembroVector.
	 * @param valueRange valor del rango de posiciones del vector
	 */
	public MiembroVector (int valueRange) {
		this.valueRange = valueRange;
	}
	
	/**
	 * Constructor para MiembroVector.
	 * @param name	nombre del vector
	 * @param type	tipo del vector
	 */
	public MiembroVector (String name, TypeIF type) {
		this.name = name;
		this.type       = type;
	}
	
	/**
	 * Constructor para MiembroVector.
	 * @param name		  nombre del vector
	 * @param valueRange  valor del rango de posiciones del vector
	 */
	public MiembroVector (String name, int valueRange) {
		this.name = name;
		this.valueRange = valueRange;
	}
	
	/**
	 * Constructor para MiembroVector.
	 * @param name			nombre del vector
	 * @param type			tipo del vector
	 * @param valueRange	valor del rango de posiciones del vector
	 */
	public MiembroVector (String name,
						  TypeIF type,
						  int valueRange)
	{
		this.name = name;
		this.type       = type;
		this.valueRange = valueRange;
	}

	/**
	 * Devuelve el nombre del vector.
	 * @return nombre del vector
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Establece el nombre del vector.
	 * @param name	nombre del vector a establecer
	 */
	public void setName (String name) {
		this.name = name;
	}

	/**
	 * Devuelve el tipo del vector.
	 * @return tipo del vector
	 */
	public TypeIF getType() {
		return this.type;
	}

	/**
	 * Establece el tipo del vector.
	 * @param type	tipo del vector a establecer
	 */
	public void setType (TypeIF type) {
		this.type = type;
	}
	
	/**
	 * Devuelve el valor para el rango de posiciones del vector.
	 * @return valor para el rango de posiciones
	 */
	public int getValue() {
		return this.valueRange;
	}
	
	/**
	 * Establece el valor del rango de posiciones del vector.
	 * @param valueRange	valor para el rango de posiciones
	 */
	public void setValue (int valueRange) {
		this.valueRange = valueRange;
	}

	/**
	 * Returns a hash code for the object.
	 * @return a hash code for the object.
	 */
	@Override
	public int hashCode() {
		return 67 * type.hashCode();
	}

	/**
	 * Compares this object with another one.
	 * @param other the other object.
	 * @return true if both objects are equals.
	 */
	@Override
	public boolean equals (Object other) {
		if (!(other instanceof MiembroVector))	return false;
		else {
			MiembroVector miembrovector = (MiembroVector) other;
			return miembrovector.type.equals(this.type);
		}
	}

	/**
	 * Returns a string representing the object.
	 * @return a string representing the object.
	 */
	@Override
	public String toString() {
		return this.getClass().getName() + "@" + Integer.toHexString(this.hashCode());
	}
}