package compiler.semantic.type;

import java.util.ArrayList;

import compiler.semantic.symbol.SymbolParameter;
import es.uned.lsi.compiler.semantic.ScopeIF;
import es.uned.lsi.compiler.semantic.type.TypeBase;

public class TypeProcedure
	extends TypeBase
{
	private ArrayList<SymbolParameter> parameters = new ArrayList<>();
	
	/**
	 * Constructor para TypeProcedure.
	 * @param scope el ambito de declaracion
	 */
	public TypeProcedure (ScopeIF scope) {
		super(scope);
	}
	
	/**
	 * Constructor para TypeProcedure.
	 * @param scope el ambito de declaracion
	 * @param name  el nombre del procedimiento
	 */
	public TypeProcedure (ScopeIF scope, String name) {
		super(scope, name);
	}
	
	/**
	 * Constructor para TypeProcedure.
	 * @param scope      el ambito de declaracion
	 * @param name       el nombre del procedimiento
	 * @param parameters la lista de parametros
	 */
	public TypeProcedure (ScopeIF scope, String name, ArrayList<SymbolParameter> parameters) {
		super(scope, name);
		this.parameters = parameters;
	}
	
	/**
	 * Devuelve la lista de parametros.
	 * @return la lista de parametros
	 */
	public ArrayList<SymbolParameter> getParameters() {
		return this.parameters;
	}
	
	/**
	 * Establece la lista de parametros.
	 * @param parameters la lista de parametros del procedimiento
	 */
	public void addParameter (SymbolParameter param) {
		this.parameters.add(0, param);
	}
    
    /**
     * Devuelve el tamaño del tipo del procedimiento.
     * @return el tamaño del tipo.
     */
    @Override
    public int getSize() {
        return 1;
    }
}