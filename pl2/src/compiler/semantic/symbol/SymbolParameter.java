package compiler.semantic.symbol;

import es.uned.lsi.compiler.semantic.ScopeIF;
import es.uned.lsi.compiler.semantic.symbol.SymbolBase;
import es.uned.lsi.compiler.semantic.type.TypeIF;

/* Clase para SymbolParameter. */

public class SymbolParameter
    extends SymbolBase
{  
    /**
     * Constructor para SymbolParameter.
     * @param scope el ambito de declaracion.
	 * @param name el nombre del simbolo.
	 * @param type el tipo del simbolo.
     */
    public SymbolParameter (ScopeIF scope, 
                           String name,
                           TypeIF type)
    {
        super (scope, name, type);
    } 
}
