package compiler.semantic.symbol;

import es.uned.lsi.compiler.semantic.ScopeIF;
import es.uned.lsi.compiler.semantic.symbol.SymbolBase;
import es.uned.lsi.compiler.semantic.type.TypeIF;

/* Clase paraSymbolVariable. */

public class SymbolVariable
    extends SymbolBase
{
	private int address = 0;
	
    /**
     * Constructor para SymbolVariable.
     * @param scope el ambito de declaracion.
	 * @param name el nombre del simbolo.
	 * @param type el tipo del simbolo.
     */
    public SymbolVariable (ScopeIF scope, 
                           String name,
                           TypeIF type)
    {
        super (scope, name, type);
    }
    
    /**
     * Devuelve la direccion de memoria asociada a la variable.
     * @return direccion de memoria asociada a la variable.
     */
    public int getAddress() {
    	return this.address;
    }
    
    /**
     * Establece la direccion de memoria asociada a la variable
     * @param value la direccion de memoria asociada a la variable.
     */
    public void setAddress (int value) {
    	this.address = value;
    }
}
