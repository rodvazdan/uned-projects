package compiler.semantic.type;

import java.util.ArrayList;

import compiler.semantic.symbol.SymbolParameter;
import es.uned.lsi.compiler.semantic.ScopeIF;

public class TypeFunction
	extends TypeProcedure
{
	private TypeSimple returnType;
	
	/**
	 * Constructor para TypeFunction.
	 * @param scope el embito de declaracion
	 */
	public TypeFunction (ScopeIF scope) {
		super(scope);
	}
	
	/**
	 * Constructor para TypeFunction.
	 * @param scope el ambito de declaracion
	 * @param name  el nombre del procedimiento
	 */
	public TypeFunction (ScopeIF scope, String name) {
		super(scope, name);
	}
	
	/**
	 * Constructor para TypeFunction.
	 * @param scope      el ambito de declaracion
	 * @param name       el nombre del procedimiento
	 * @param parameters la lista de parametros
	 */
	public TypeFunction (ScopeIF scope, String name, ArrayList<SymbolParameter> parameters) {
		super(scope, name, parameters);
	}
	
	/**
	 * Constructor para TypeFunction.
	 * @param scope 	 el ambito de declaracion
	 * @param name  	 el nombre de la funcion
	 * @param returnType el tipo de retorno. Debe ser un tipo entero o logico.
	 */
	public TypeFunction (ScopeIF scope,
			             String name,
			             TypeSimple returnType)
	{
		super(scope, name);
		this.returnType = returnType;
	}
	
	/**
	 * Constructor para TypeFunction.
	 * @param scope 	 el ambito de declaracion
	 * @param name  	 el nombre de la funcion
	 * @param parameters la lista de parametros de la funcion
	 * @param returnType el tipo de retorno. Debe ser un tipo entero o logico.
	 */
	public TypeFunction (ScopeIF scope,
			             String name,
			             ArrayList<SymbolParameter> parameters,
			             TypeSimple returnType)
	{
		super(scope, name, parameters);
		this.returnType = returnType;
	}
	
	/**
	 * Devuelve el tipo de retorno de la funcion.
	 * @return el tipo de retorno
	 */
	public TypeSimple getReturnType() {
		return this.returnType;
	}
	
	/**
	 * Establece el tipo de retorno de la funcion.
	 * @param returnType el tipo de retorno
	 */
	public void setReturnType (TypeSimple returnType) {
		this.returnType = returnType;
	}
    
    /**
     * Devuelve el tamaño del tipo de la funcion.
     * @return el tamaño del tipo.
     */
    @Override
    public int getSize() {
        return 1;
    }
}