package compiler.code;

import java.util.Arrays;
import java.util.List;

import compiler.intermediate.Label;
import compiler.intermediate.Temporal;
import compiler.intermediate.Value;
import compiler.intermediate.Variable;
import compiler.semantic.type.TypeSimple;

import es.uned.lsi.compiler.code.ExecutionEnvironmentIF;
import es.uned.lsi.compiler.code.MemoryDescriptorIF;
import es.uned.lsi.compiler.code.RegisterDescriptorIF;
import es.uned.lsi.compiler.intermediate.LabelFactory;
import es.uned.lsi.compiler.intermediate.LabelIF;
import es.uned.lsi.compiler.intermediate.OperandIF;
import es.uned.lsi.compiler.intermediate.QuadrupleIF;

/**
 * Class for the ENS2001 Execution environment.
 */

public class ExecutionEnvironmentEns2001 
    implements ExecutionEnvironmentIF
{    
    private final static int      MAX_ADDRESS = 65535; 
    private final static String[] REGISTERS   = {
       ".PC", ".SP", ".SR", ".IX", ".IY", ".A", 
       ".R0", ".R1", ".R2", ".R3", ".R4", 
       ".R5", ".R6", ".R7", ".R8", ".R9"
    };
    
    private RegisterDescriptorIF registerDescriptor;
    private MemoryDescriptorIF   memoryDescriptor;
    
    /**
     * Constructor for ENS2001Environment.
     */
    public ExecutionEnvironmentEns2001 ()
    {       
        super ();
    }
    
    /**
     * Returns the size of the type within the architecture.
     * @return the size of the type within the architecture.
     */
    @Override
    public final int getTypeSize (TypeSimple type)
    {      
        return 1;  
    }
    
    /**
     * Returns the registers.
     * @return the registers.
     */
    @Override
    public final List<String> getRegisters ()
    {
        return Arrays.asList (REGISTERS);
    }
    
    /**
     * Returns the memory size.
     * @return the memory size.
     */
    @Override
    public final int getMemorySize ()
    {
        return MAX_ADDRESS;
    }
           
    /**
     * Returns the registerDescriptor.
     * @return Returns the registerDescriptor.
     */
    @Override
    public final RegisterDescriptorIF getRegisterDescriptor ()
    {
        return registerDescriptor;
    }

    /**
     * Returns the memoryDescriptor.
     * @return Returns the memoryDescriptor.
     */
    @Override
    public final MemoryDescriptorIF getMemoryDescriptor ()
    {
        return memoryDescriptor;
    }

    /**
     * Translate a quadruple into a set of final code instructions. 
     * @param cuadruple The quadruple to be translated.
     * @return a quadruple into a set of final code instructions. 
     */
    @Override
    public final String translate (QuadrupleIF quadruple)
    {    	
    	if (quadruple.getOperation().equals("VARGLOBAL")) {
        	StringBuffer sb = new StringBuffer();
        	String firstOperand = operation(quadruple.getFirstOperand());
        	String result = operation(quadruple.getResult());
        	sb.append(";" + quadruple.toString() + "\n");
        	sb.append("MOVE " + firstOperand + ", " + result);
        	return sb.toString();
        }

        if (quadruple.getOperation().equals("VARGLOBALVECTOR")) {
        	StringBuffer sb = new StringBuffer();
        	String firstOperand = operation2(quadruple.getFirstOperand());
        	String secondOperand = operation(quadruple.getSecondOperand());
        	sb.append(";" + quadruple.toString() + "\n");
        	sb.append("MOVE " + secondOperand + ", " + firstOperand);
        	return sb.toString();
        }

        if (quadruple.getOperation().equals("ADD")) {
        	StringBuffer sb = new StringBuffer();
        	String firstOperand = operation(quadruple.getFirstOperand());
        	String secondOperand = operation(quadruple.getSecondOperand());
        	String result = operation(quadruple.getResult());
        	sb.append(";" + quadruple.toString() + "\n");
        	sb.append("ADD " + firstOperand + ", " + secondOperand + "\n");
        	sb.append("MOVE .A, " + result);
        	return sb.toString();
        }
        
        if (quadruple.getOperation().equals("MUL")) {
        	StringBuffer sb = new StringBuffer();
        	String firstOperand = operation(quadruple.getFirstOperand());
        	String secondOperand = operation(quadruple.getSecondOperand());
        	String result = operation(quadruple.getResult());
        	sb.append(";" + quadruple.toString() + "\n");
        	sb.append("MUL " + firstOperand + ", " + secondOperand + "\n");
        	sb.append("MOVE .A, " + result);
        	return sb.toString();
        }
        
        if (quadruple.getOperation().equals("SUB")) {
        	StringBuffer sb = new StringBuffer();
        	String firstOperand = operation(quadruple.getFirstOperand());
        	String secondOperand = operation(quadruple.getSecondOperand());
        	String result = operation(quadruple.getResult());
        	sb.append(";" + quadruple.toString() + "\n");
        	sb.append("SUB " + firstOperand + ", " + secondOperand + "\n");
        	sb.append("MOVE .A, " + result);
        	return sb.toString();
        }
        
        if (quadruple.getOperation().equals("DEC")) {
        	StringBuffer sb = new StringBuffer();
        	String result = operation(quadruple.getResult());
        	sb.append(";" + quadruple.toString() + "\n");
        	sb.append("DEC " + result);
        	return sb.toString();
        }

        if (quadruple.getOperation().equals("INC")) {
        	StringBuffer sb = new StringBuffer();
        	String result = operation(quadruple.getResult());
        	sb.append(";" + quadruple.toString() + "\n");
        	sb.append("INC " + result);     	
        	return sb.toString();
        }

        if (quadruple.getOperation().equals("EQ")) {
        	LabelFactory labelFactory = new LabelFactory();
    		LabelIF label0 = labelFactory.create();
    		LabelIF label1 = labelFactory.create();
        	StringBuffer sb = new StringBuffer();
        	String firstOperand = operation(quadruple.getFirstOperand());
        	String secondOperand = operation(quadruple.getSecondOperand());
        	String result = operation(quadruple.getResult());
        	sb.append(";" + quadruple.toString() + "\n");    		
    		sb.append("CMP " + firstOperand + ", " + secondOperand + "\n");
    		sb.append("BNZ /" + label0 + "\n");
    		sb.append("MOVE #1, " + result + "\n");
    		sb.append("BR /" + label1 + "\n");
    		sb.append(label0 + " :\n");
    		sb.append("MOVE #0, " + result + "\n");
    		sb.append(label1 + " :");
        	return sb.toString();
        }

        if (quadruple.getOperation().equals("LS")) {
        	LabelFactory labelFactory = new LabelFactory();
    		LabelIF label0 = labelFactory.create();
    		LabelIF label1 = labelFactory.create();
        	StringBuffer sb = new StringBuffer();
        	String firstOperand = operation(quadruple.getFirstOperand());
        	String secondOperand = operation(quadruple.getSecondOperand());
        	String result = operation(quadruple.getResult());
        	sb.append(";" + quadruple.toString() + "\n");
    		sb.append("CMP " + firstOperand + ", " + secondOperand + "\n");
    		sb.append("BP /" + label0 + "\n");
    		sb.append("MOVE #1, " + result + "\n");
    		sb.append("BR /" + label1 + "\n");
    		sb.append(label0 + " :\n");
    		sb.append("MOVE #0, " + result + "\n");
    		sb.append(label1 + " :");
        	return sb.toString();
        }

        if (quadruple.getOperation().equals("AND")) {
        	StringBuffer sb = new StringBuffer();
        	String firstOperand = operation(quadruple.getFirstOperand());
        	String secondOperand = operation(quadruple.getSecondOperand());
        	String result = operation(quadruple.getResult());
        	sb.append(";" + quadruple.toString() + "\n");
    		sb.append("AND " + firstOperand + ", " + secondOperand + "\n");
    		sb.append("MOVE .A, " + result);
        	return sb.toString();
        }

        if (quadruple.getOperation().equals("NOT")) {
        	LabelFactory labelFactory = new LabelFactory();
    		LabelIF label0 = labelFactory.create();
    		LabelIF label1 = labelFactory.create();
        	StringBuffer sb = new StringBuffer();
        	String firstOperand = operation(quadruple.getFirstOperand());
        	String result = operation(quadruple.getResult());
        	sb.append(";" + quadruple.toString() + "\n");
        	sb.append("CMP #0, " + firstOperand + "\n");
        	sb.append("BNZ /" + label0 + "\n");
        	sb.append("MOVE #1, " + result + "\n");
        	sb.append("BR /" + label1 + " \n");
        	sb.append(label0 + " :\n");
        	sb.append("MOVE #0, " + result + "\n");
        	sb.append(label1 + " :\n");
        	
        	return sb.toString();
        }

        if (quadruple.getOperation().equals("BR")) {
        	StringBuffer sb = new StringBuffer();
        	String result = operation(quadruple.getResult());
        	sb.append(";" + quadruple.toString() + "\n");
        	sb.append("BR /" + result);
        	return sb.toString();
        }

        if (quadruple.getOperation().equals("BRF")) {
        	StringBuffer sb = new StringBuffer();
        	String firstOperand = operation(quadruple.getFirstOperand());
        	String result = operation(quadruple.getResult());
        	sb.append(";" + quadruple.toString() + "\n");
        	sb.append("CMP #0, " + result + "\n");
        	sb.append("BZ /" + firstOperand);
        	
        	return sb.toString();
        }
        
        if (quadruple.getOperation().equals("INL")) {
        	StringBuffer sb = new StringBuffer();
        	String result = operation(quadruple.getResult());
        	sb.append(";" + quadruple.toString() + "\n");
        	sb.append(result + " : NOP");
        	return sb.toString();
        }

        if (quadruple.getOperation().equals("MV")) {
        	StringBuffer sb = new StringBuffer();
        	String firstOperand = operation(quadruple.getFirstOperand());
        	String result = operation(quadruple.getResult());
        	sb.append(";" + quadruple.toString() + "\n");
        	sb.append("MOVE " + firstOperand + ", " + result);
        	return sb.toString();
        }

        if (quadruple.getOperation().equals("MVP")) {
        	StringBuffer sb = new StringBuffer();
        	String firstOperand = operation(quadruple.getFirstOperand());
        	String result = operation(quadruple.getResult());
        	sb.append(";" + quadruple.toString() + "\n");
        	
        	if (quadruple.getFirstOperand() instanceof Variable) {
        		sb.append("MOVE " + firstOperand + ", " + result);
        	} else {
        		sb.append("MOVE " + firstOperand + ", .R1\n");
        		sb.append("MOVE [.R1], " + result);
        	}
        	
        	return sb.toString();
        }

        if (quadruple.getOperation().equals("MVA")) {
        	StringBuffer sb = new StringBuffer();
        	String firstOperand = operation2(quadruple.getFirstOperand());
        	String result = operation(quadruple.getResult());
        	sb.append(";" + quadruple.toString() + "\n");
        	sb.append("MOVE " + firstOperand + ", " + result);
        	return sb.toString();
        }

        if (quadruple.getOperation().equals("STP")) {
        	StringBuffer sb = new StringBuffer();
        	String firstOperand = operation(quadruple.getFirstOperand());
        	String result = operation(quadruple.getResult());
        	sb.append(";" + quadruple.toString() + "\n");
        	sb.append("MOVE " + result + ", .R1\n");
        	sb.append("MOVE " + firstOperand + ", [.R1]");
        	return sb.toString();
        }

        if (quadruple.getOperation().equals("WRITESTRING")) {
        	StringBuffer sb = new StringBuffer();
        	String firstOperand = operation(quadruple.getFirstOperand());
        	sb.append(";" + quadruple.toString() + "\n");
        	sb.append("WRSTR /" + firstOperand + "\n");
        	sb.append("WRCHAR #10");
        	return sb.toString();
        }

        if (quadruple.getOperation().equals("WRITEINT")) {
        	StringBuffer sb = new StringBuffer();
        	String result = operation(quadruple.getResult());
        	sb.append(";" + quadruple.toString() + "\n");
        	sb.append("WRINT " + result + "\n");
        	sb.append("WRCHAR #10");
        	return sb.toString();
        }

        if (quadruple.getOperation().equals("WRITELN")) {
        	StringBuffer sb = new StringBuffer();
        	sb.append(";" + quadruple.toString() + "\n");
        	sb.append("WRCHAR #10");
        	return sb.toString();
        }
        
        if (quadruple.getOperation().equals("CADENA")) {
        	StringBuffer sb = new StringBuffer();
        	String firstOperand = operation(quadruple.getFirstOperand());
        	String result = operation(quadruple.getResult());
        	sb.append(";" + quadruple.toString() + "\n");
        	sb.append(firstOperand + " : DATA " + result);
        	return sb.toString();
        	
        }

        if (quadruple.getOperation().equals("HALT")) {
        	StringBuffer sb = new StringBuffer();
        	sb.append(";" + quadruple.toString() + "\n");
        	sb.append("HALT");
        	return sb.toString();
        }

        return quadruple.toString(); 
    }
    
    /**
     * Sets the addressing mode for an operand.
     * @param operand An operand of a quadruple.
     * @return string representing an addressing mode on an operand.
     */
    private String operation (OperandIF operand)
    {
    	if (operand instanceof Variable variable) {
    		return "/" + variable.getAddress();
    	}
    	
    	if (operand instanceof Value value) {
    		return "#" + value.getValue();
    	}
    	
    	if (operand instanceof Temporal temporal) {
    		return "/" + temporal.getAddress();
    	}
    	
    	if (operand instanceof Label label) {
    		return label.getName();
    	}
    	
    	return null;
    }
    
    private String operation2 (OperandIF operand)
    {
    	if (operand instanceof Variable variable) {
    		return "#" + variable.getAddress();
    	}
    	
    	if (operand instanceof Value value) {
    		return "/" + value.getValue();
    	}
    	
    	return null;
    }
}
