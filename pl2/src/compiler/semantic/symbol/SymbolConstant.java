package compiler.semantic.symbol;

import es.uned.lsi.compiler.semantic.ScopeIF;
import es.uned.lsi.compiler.semantic.symbol.SymbolBase;
import es.uned.lsi.compiler.semantic.type.TypeIF;

/* Clase para SymbolConstant. */

public class SymbolConstant
    extends SymbolBase
{
    /**
     * Constructor para SymbolConstant.
     * @param scope el ambito de declaracion.
     * @param name el nombre del simbolo.
     * @param type el tipo del simbolo.
     */
    public SymbolConstant (ScopeIF scope,
                           String name,
                           TypeIF type)
    {
        super (scope, name, type);
    } 
}