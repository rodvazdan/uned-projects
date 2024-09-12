package compiler.semantic.symbol;

import compiler.semantic.type.TypeSimple;
import es.uned.lsi.compiler.semantic.ScopeIF;
import es.uned.lsi.compiler.semantic.type.TypeIF;

/* Clase para SymbolFunction. */

public class SymbolFunction
    extends SymbolProcedure
{
    private TypeSimple returnType;
    private boolean hasReturnStatement = false;

    /**
     * Constructor para SymbolFunction.
     * @param scope el ambito de declaracion.
     * @param name el nombre del simbolo.
     * @param type el tipo del simbolo.
     */
    public SymbolFunction (ScopeIF scope, 
                           String name,
                           TypeIF type)
    {
        super (scope, name, type);
    }
    
    /**
     * Constructor para SymbolFunction.
     * @param scope el ambito de declaracion.
     * @param name el nombre del simbolo.
     * @param type el tipo del simbolo.
     * @param returnType el tipo de retorno;
     */
    public SymbolFunction (ScopeIF scope, 
                           String name,
                           TypeIF type,
                           TypeSimple returnType)
    {
        super (scope, name, type);
        this.returnType = returnType;
    }
    
    /**
     * Devuelve el tipo de retorno de la funcion.
     * @return el tipo de retorno.
     */
    public TypeSimple getReturnType() {
    	return returnType;
    }
    
    /**
     * Establece el tipo de retorno de la funcion.
     * @param returnType el tipo de retorno.
     */
    public void setReturnType (TypeSimple returnType) {
    	this.returnType = returnType;
    }
    
    /**
     * Comprueba si la funcion contiene al menos una sentencia de retorno.
     * @return true si la funcion contiene al menos una sentencia de retorno.
     */
    public boolean hasReturnStatement() {
    	return hasReturnStatement;
    }
    
    /**
     * Establece si la funcion contiene al menos una sentencia de retorno.
     * @param value valor logico que indica si la funcion contiene al menos una sentencia de retorno.
     */
    public void hasReturnStatement (boolean value) {
    	this.hasReturnStatement = value;
    }
}
