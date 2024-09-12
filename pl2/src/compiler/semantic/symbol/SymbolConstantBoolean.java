package compiler.semantic.symbol;

import es.uned.lsi.compiler.semantic.ScopeIF;
import es.uned.lsi.compiler.semantic.type.TypeIF;

public class SymbolConstantBoolean
	extends SymbolConstant
{
	private String value;
	
	/**
	 * Constructor para SymbolConstantBoolean.
	 * @param scope el ambito de declaracion.
	 * @param name el nombre del simbolo.
	 * @param type el tipo del simbolo.
	 */
	public SymbolConstantBoolean(ScopeIF scope,
								 String name,
								 TypeIF type) {
		super(scope, name, type);
	}
	
	/**
	 * Constructor para SymbolConstanBoolean.
	 * @param scope el ambito de declaracion.
	 * @param name el nombre del simbolo.
	 * @param type el tipo del simbolo.
	 * @param value el valor del simbolo.
	 */
	public SymbolConstantBoolean(ScopeIF scope,
								 String name,
								 TypeIF type,
								 String value) {
		super(scope, name, type);
		this.value = value;
	}
	
	/**
	 * Devuelve el valor del simbolo.
	 * @return el valor del simbolo.
	 */
	public String getValue() {
		return value;
	}

}
