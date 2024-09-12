package compiler.semantic.symbol;

import java.util.ArrayList;

import es.uned.lsi.compiler.semantic.ScopeIF;
import es.uned.lsi.compiler.semantic.symbol.SymbolBase;
import es.uned.lsi.compiler.semantic.type.TypeIF;

/**
 * Class for SymbolProcedure.
 */

// TODO: Student work
//       Include properties to characterize procedure calls

public class SymbolProcedure
    extends SymbolBase
{
	private ArrayList<SymbolParameter> parameters = new ArrayList<>();
	
    /**
     * Constructor for SymbolProcedure.
     * @param scope The declaration scope.
     * @param name The symbol name.
     * @param type The symbol type.
     */
    public SymbolProcedure (ScopeIF scope, 
                            String name,
                            TypeIF type)
    {
        super (scope, name, type);
    }
	
	/**
	 * Devuelve la lista de parámetros.
	 * @return la lista de parámetros
	 */
	public ArrayList<SymbolParameter> getParameters()
	{
		return this.parameters;
	}
	
	/**
	 * Establece la lista de parámetros.
	 * @param parameters la lista de parámetros del procedimiento
	 */
	public void addParameter (SymbolParameter param)
	{
		this.parameters.add(0, param);
	}
}
