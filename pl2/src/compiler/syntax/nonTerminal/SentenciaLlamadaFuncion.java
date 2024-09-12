package compiler.syntax.nonTerminal;

import es.uned.lsi.compiler.semantic.type.TypeIF;

public class SentenciaLlamadaFuncion 
	extends NonTerminal
{
	private String name;
	private TypeIF type;
	
	/* Constructor por defecto. */
	public SentenciaLlamadaFuncion() {
		super();
	}

	/**
	 * Constructor para SentenciaLlamadaFuncion.
	 * @param name El nombre del procedimiento.
	 * @param type El tipo del procedimiento.
	 */
	public SentenciaLlamadaFuncion (String name, TypeIF type) {
		this.name = name;
		this.type = type;
	}

	/**
	 * Devuelve el tipo del procedimiento.
	 * @return el tipo del procedimiento.
	 */
	public TypeIF getType() {
		return type;
	}

	/**
	 * Establece el tipo del procedimiento.
	 * @param type El tipo del procedimiento.
	 */
	public void setType (TypeIF type) {
		this.type = type;
	}

	/**
	 * Devuelve un codigo hash para el objeto.
	 * @return devuelve un codigo hash para el objeto
	 */
	@Override
	public int hashCode() {
		return 67 * 67 * name.hashCode() + 67 * type.hashCode();
	}

	/**
	 * Compara este objeto con otro.
	 * @param other	   el otro objeto
	 * @return true si ambos objetos son iguales; false en cualquier otro caso
	 */
	@Override
	public boolean equals (Object other) {
		if (!(other instanceof SentenciaLlamadaFuncion))	return false;
		else {
			SentenciaLlamadaFuncion sentenciallamadafuncion = (SentenciaLlamadaFuncion) other;
			return sentenciallamadafuncion.name.equals(this.name)
				&& sentenciallamadafuncion.type.equals(this.type);
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