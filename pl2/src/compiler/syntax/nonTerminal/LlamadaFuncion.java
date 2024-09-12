package compiler.syntax.nonTerminal;

import java.util.ArrayList;

import es.uned.lsi.compiler.semantic.type.TypeIF;

public class LlamadaFuncion
	extends NonTerminal
{
	private String  name;
	private TypeIF	type;
	ArrayList<SelectorValorOReferencia> parameters;
	
	/* Constructor por defecto. */
	public LlamadaFuncion() {
		super();
	}

	/**
	 * Constructor para LlamadaFuncion.
	 * @param name	identificador de la funci�n
	 */
	public LlamadaFuncion (String name) {
		super();
		this.name = name;
	}
	
	/**
	 * Constructor para LlamadaFuncion.
	 * @param type	tipo de la funci�n
	 */
	public LlamadaFuncion (TypeIF type) {
		super();
		this.type = type;
	}

	/**
	 * Constructor para LlamadaFuncion.
	 * @param name	identificador de la funci�n
	 * @param type	tipo de la funci�n
	 */
	public LlamadaFuncion (String name, TypeIF type) {
		super();
		this.name = name;
		this.type = type;
	}

	/**
	 * Constructor para LlamadaFuncion.
	 * @param name	identificador de la funci�n
	 * @param type	tipo de la funci�n
	 * @param parameters lista de par�metros actuales
	 */
	public LlamadaFuncion (String name,
						   TypeIF type,
						   ArrayList<SelectorValorOReferencia> parameters)
	{
		super();
		this.name = name;
		this.type = type;
		this.parameters = parameters;
	}

	/**
	 * Devuelve el identificador de la funci�n.
	 * @return identificador de la funci�n
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Establece el identificador de la funci�n.
	 * @param name  identificador de la funci�n
	 */
	public void setName (String name) {
		this.name = name;
	}
	
	/**
	 * Devuelve el tipo de la funci�n.
	 * @return tipo de la funci�n
	 */
	public TypeIF getType() {
		return this.type;
	}
	
	/**
	 * Establece el tipo del funci�n
	 * @param type	 tipo de la funci�n
	 */
	public void setType (TypeIF type) {
		this.type = type;
	}
	
	/**
	 * Devuelve la lista de par�metros actuales.
	 * @return lista de par�metros actuales.
	 */
	public ArrayList<SelectorValorOReferencia> getParameters() {
		return parameters;
	}
	
	/**
	 * Establece la lista de par�metros actuales.
	 * @param parameters lista de par�metros actuales.
	 */
	public void setParameters (ArrayList<SelectorValorOReferencia> parameters) {
		this.parameters = parameters;
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
	 * Devuelve un c�digo hash para el objeto.
	 * @return c�digo hash para el objeto
	 */
	@Override
	public int hashCode() {
		return 67 * super.hashCode() + this.type.hashCode();
	}

	/**
	 * Compara este objeto con otro.
	 * @param other	  el otro objeto
	 * @return verdadero si ambos objetos son iguales; falso en cualquier otro caso
	 */
	@Override
	public boolean equals (Object other) {
		if (!(other instanceof LlamadaFuncion)) return false;
		else {
			LlamadaFuncion llamadafuncion = (LlamadaFuncion) other;
			return llamadafuncion.type.equals(this.type);
		}
	}
}