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
	 * @param name	identificador de la función
	 */
	public LlamadaFuncion (String name) {
		super();
		this.name = name;
	}
	
	/**
	 * Constructor para LlamadaFuncion.
	 * @param type	tipo de la función
	 */
	public LlamadaFuncion (TypeIF type) {
		super();
		this.type = type;
	}

	/**
	 * Constructor para LlamadaFuncion.
	 * @param name	identificador de la función
	 * @param type	tipo de la función
	 */
	public LlamadaFuncion (String name, TypeIF type) {
		super();
		this.name = name;
		this.type = type;
	}

	/**
	 * Constructor para LlamadaFuncion.
	 * @param name	identificador de la función
	 * @param type	tipo de la función
	 * @param parameters lista de parámetros actuales
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
	 * Devuelve el identificador de la función.
	 * @return identificador de la función
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Establece el identificador de la función.
	 * @param name  identificador de la función
	 */
	public void setName (String name) {
		this.name = name;
	}
	
	/**
	 * Devuelve el tipo de la función.
	 * @return tipo de la función
	 */
	public TypeIF getType() {
		return this.type;
	}
	
	/**
	 * Establece el tipo del función
	 * @param type	 tipo de la función
	 */
	public void setType (TypeIF type) {
		this.type = type;
	}
	
	/**
	 * Devuelve la lista de parámetros actuales.
	 * @return lista de parámetros actuales.
	 */
	public ArrayList<SelectorValorOReferencia> getParameters() {
		return parameters;
	}
	
	/**
	 * Establece la lista de parámetros actuales.
	 * @param parameters lista de parámetros actuales.
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
	 * Devuelve un código hash para el objeto.
	 * @return código hash para el objeto
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