package compiler.syntax.nonTerminal;

import es.uned.lsi.compiler.semantic.type.TypeIF;

public class ParteIzquierdaAsignacion
	extends NonTerminal
{
	private String	name;
	private TypeIF 	type;
	
	/* Constructor por defecto. */
	public ParteIzquierdaAsignacion() {
		super();
	}
	
	/**
	 * Constructor para ParteIzquierdaAsignacion.
	 * @param name	un identificador de variable o parametro
	 */
	public ParteIzquierdaAsignacion (String name) {
		super();
		this.name = name;
	}
	
	/**
	 * Constructor para ParteIzquierdaAsignacion.
	 * @param type	tipo del identificador
	 */
	public ParteIzquierdaAsignacion (TypeIF type) {
		super();
		this.type = type;
	}
	
	/**
	 * Constructor para ParteIzquierdaAsignacion.
	 * @param name	nombre del identificador 
	 * @param type	tipo del identificador
	 */
	public ParteIzquierdaAsignacion (String name, TypeIF type) {
		super();
		this.name = name;
		this.type = type;
	}
	
	/**
	 * Devuelve el nombre del identificador de variable o parámetro.
	 * @return nombre del identificador
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Establece el nombre del identificador de variable o parametro.
	 * @param name	 nombre del identificador
	 */
	public void setName (String name) {
		this.name = name;
	}
	
	/**
	 * Devuelve el tipo del identificador de variable o parametro.
	 * @return tipo del identificador
	 */
	public TypeIF getType() {
		return this.type;
	}
	
	/**
	 * Establece el tipo del identificador de variable o parametro.
	 * @param type	 tipo del identificador
	 */
	public void setType (TypeIF type) {
		this.type = type;
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
		return 67 * 67 * super.hashCode() + 67 * this.name.hashCode() + this.type.hashCode();
	}

	/**
	 * Compara este objeto con otro.
	 * @param other	  el otro objeto
	 * @return verdadero si ambos objetos son iguales; falso en cualquier otro caso
	 */
	@Override
	public boolean equals (Object other) {
		if (!(other instanceof ParteIzquierdaAsignacion)) return false;
		else {
			ParteIzquierdaAsignacion parteizquierdaasignacion = (ParteIzquierdaAsignacion) other;
			return parteizquierdaasignacion.name.equals(this.name)
				&& parteizquierdaasignacion.type.equals(this.type);
		}
	}
}