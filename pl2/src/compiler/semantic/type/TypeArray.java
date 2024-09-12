package compiler.semantic.type;

import es.uned.lsi.compiler.semantic.ScopeIF;
import es.uned.lsi.compiler.semantic.type.TypeBase;

/* Class for TypeArray. */

public class TypeArray
    extends TypeBase
{   
	private int firstIndex, lastIndex;
	private TypeSimple baseType;

	/**
     * Constructor para TypeArray.
     * @param scope El ambito de declaracion del tipo.
     */
    public TypeArray (ScopeIF scope) {
        super (scope);
    }

    /**
     * Constructor para TypeArray.
     * @param scope El ambito de declaracion del tipo.
     * @param name El nombre del tipo.
     */
    public TypeArray (ScopeIF scope, String name) {
        super (scope, name);
    }

    /**
     * Constructor para TypeArray.
     * @param scope		 El ambito de declaracion del tipo.
     * @param name		 El nombre del tipo.
     * @param firstIndex El limite inferior del vector.
     * @param lastIndex	 El limite superior del vector.
     */
    public TypeArray (ScopeIF scope, String name, int firstIndex, int lastIndex) {
    	super(scope, name);
    	this.firstIndex = firstIndex;
    	this.lastIndex  = lastIndex;
    }
    
    /**
     * Constructor para TypeArray.
     * @param scope		 El ambito de declaracion del tipo.
     * @param name		 El nombre del tipo.
     * @param baseType 	 El tipo de los elementos del vector.
     */
    public TypeArray (ScopeIF scope, String name, TypeSimple baseType) {
    	super(scope, name);
    	this.baseType = baseType;
    }

    /**
     * Constructor para TypeArray.
     * @param scope		 El ambito de declaracion del tipo.
     * @param name		 El nombre del tipo.
     * @param firstIndex El limite inferior del vector.
     * @param lastIndex	 El limite superior del vector.
     * @param baseType 	 El tipo de los elementos del vector.
     */
    public TypeArray (ScopeIF scope, String name, int firstIndex, int lastIndex, TypeSimple baseType) {
    	super(scope, name);
    	this.firstIndex = firstIndex;
    	this.lastIndex  = lastIndex;
    	this.baseType   = baseType;
    }
    
    /**
     * Devuelve el limite inferior del vector.
     * @return el limite inferior del vector.
     */
    public int getFirstIndex() {
    	return firstIndex;
    }
    
    /**
     * Devuelve el limite superior del vector.
     * @return el limite superior del vector.
     */
    public int getLastIndex() {
    	return lastIndex;
    }
    
    /**
     * Devuelve el tipo de los elementos del vector.
     * @return el tipo de los elementos del vector.
     */
    public TypeSimple getBaseType() {
    	return baseType;
    }
    
    /**
     * Devuelve la longitud del vector.
     * @return la longitud del vector.
     */
    @Override
    public int getSize() {
        return (lastIndex - firstIndex) + 1;
    }
}
