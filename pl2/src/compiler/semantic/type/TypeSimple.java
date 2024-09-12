package compiler.semantic.type;

import compiler.CompilerContext;

import es.uned.lsi.compiler.code.ExecutionEnvironmentIF;
import es.uned.lsi.compiler.semantic.ScopeIF;
import es.uned.lsi.compiler.semantic.type.TypeBase;

/* Clase para TypeSimple. */

public class TypeSimple
    extends TypeBase
{
    /**
     * Constructor para TypeSimple.
     * @param scope el ambito de declaracion.
     */
    public TypeSimple (ScopeIF scope) {
        super (scope);
    }
    
    /**
     * Constructor para TypeSimple.
     * @param scope el ambito de declaracion.
     * @param name el nombre del tipo.
     */
    public TypeSimple (ScopeIF scope, String name) {
        super(scope, name);
    }
    
    /**
     * Devuelve el tamaño del tipo.
     * @return el tamaño del tipo.
     */
    @Override
    public int getSize () {
        ExecutionEnvironmentIF environment = CompilerContext.getExecutionEnvironment ();
        return environment.getTypeSize (this);
    }
}
