package compiler.syntax.nonTerminal;

import java.util.ArrayList;
import java.util.List;

import es.uned.lsi.compiler.intermediate.QuadrupleIF;
import es.uned.lsi.compiler.intermediate.TemporalIF;
import es.uned.lsi.compiler.syntax.nonTerminal.NonTerminalIF;

/* Clase abstracta para simbolos no terminales. */

public abstract class NonTerminal implements NonTerminalIF {
	private List<QuadrupleIF>	intermediateCode;
	private TemporalIF			temporal = null;
    
    /* Constructor por defecto: inicializa la lista de codigo intermedio. */
    public NonTerminal () {
        super();
        this.intermediateCode = new ArrayList<QuadrupleIF> ();
    }

    /**
	 * Devuelve el codigo intermedio.
	 * @return codigo intermedio
	 */
	public List<QuadrupleIF> getIntermediateCode () {
        return this.intermediateCode;
    }

	/**
	 * Establece el codigo intermedio.
	 * @param code codigo intermedio a establecer
	 */
	public void setIntermediateCode (List<QuadrupleIF> intermediateCode) {
        this.intermediateCode = intermediateCode;
    }

	/**
	 * Devuelve la variable temporal.
	 * @return la variable temporal
	 */
	public TemporalIF getTemporal() {
		return this.temporal;
	}

	/**
	 * Establece la variable temporal.
	 * @param temporal valor para la variable temporal
	 */
	public void setTemporal (TemporalIF temporal) {
		this.temporal = temporal;
	}
}