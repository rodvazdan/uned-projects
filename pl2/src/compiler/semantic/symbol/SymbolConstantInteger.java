package compiler.semantic.symbol;

import es.uned.lsi.compiler.semantic.ScopeIF;
import es.uned.lsi.compiler.semantic.type.TypeIF;

public class SymbolConstantInteger
	extends SymbolConstant
{
	private int value;
	
	/**
	 * Constructor para SymbolConstantInteger.
	 * @param scope el ambito de declaracion.
	 * @param name el nombre del simbolo.
	 * @param type el tipo del simbolo.
	 */
	public SymbolConstantInteger(ScopeIF scope,
								 String name,
								 TypeIF type) {
		super(scope, name, type);
	}
	
	/**
	 * Constructor para SymbolConstantInteger.
	 * @param scope el ambito de declaracion.
	 * @param name el nombre del simbolo.
	 * @param type el tipo del simbolo.
	 * @param value el valor del simbolo.
	 */
	public SymbolConstantInteger(ScopeIF scope,
								 String name,
								 TypeIF type,
								 int value) {
		super(scope, name, type);
		this.value = value;
	}
	
	/**
	 * Devuelve el valor del simbolo.
	 * @return el valor del simbolo.
	 */
	public int getValue() {
		return value;
	}

}
