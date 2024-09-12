package compiler.syntax.nonTerminal;

/* Clase para SentenciaEscribir. */

public class SentenciaEscribir extends NonTerminal
{
	private ParametroEscribir	param;
	
	/* Constructor por defecto. */
	public SentenciaEscribir() {
		super();
	}
	
	/**
	 * Constructor para SentenciaEscribir.
	 * @param parametro   parametro que sera escrito por la salida estandar
	 */
	public SentenciaEscribir (ParametroEscribir parametro) {
		super();
		this.param = parametro;
	}
	
	/**
	 * Devuelve el parametro a escribir por la salida estandar.
	 * @return parametro a escribir por la salida estandar
	 */
	public ParametroEscribir getParam() {
		return param;
	}
	
	/**
	 * Establece el parametro a escribir por la salida estandar.
	 * @param param   parametro a escribir por la salida estandar
	 */
	public void setParam (ParametroEscribir param) {
		this.param = param;
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
		return 67 * super.hashCode() + this.param.hashCode();
	}
	
	/**
	 * Compara este objeto con otro.
	 * @param other	  el otro objeto
	 * @return true si ambos objetos son iguales; false en cualquier otro caso
	 */
	@Override
	public boolean equals (Object other) {
		if (!(other instanceof SentenciaEscribir)) return false;
		else {
			SentenciaEscribir sentenciaEscribir = (SentenciaEscribir) other;
			return sentenciaEscribir.getIntermediateCode().equals(this.getIntermediateCode());
		}
	}
}