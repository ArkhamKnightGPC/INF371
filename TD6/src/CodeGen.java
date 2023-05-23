import java.util.*;

import edu.polytechnique.xvm.asm.*;
import edu.polytechnique.xvm.asm.interfaces.*;

public final class CodeGen {
  private Vector<AsmInstruction> instructions;
  private Map<String, Integer>   labels;
  private Map<String, Integer>   offsets;

  public CodeGen() {
    this.instructions = new Vector<AsmInstruction>();
    this.labels = new HashMap<String, Integer>();
    this.offsets = new HashMap<String, Integer>();
  }

  @SuppressWarnings("unused")
  private static int labelc = 0;

  public static String generateLabel() {
    // Generate a fresh label using `labelc'.
    // For example, lXXX where XXX is the value of labelc.
    // Two generated labels should never be equal.
    // A label must start with a lowercase letter.
    String newLabel = "";
    int newLabelRandomLength = (int)Math.random()%20;
    for(int i=0; i<newLabelRandomLength; i++){
      char c = 'a';
      for(int j = (int)Math.random()%26; j>=0; j--)c++;
      newLabel += "" + c;
    }
    return newLabel + Integer.toString(labelc);
  }

  public void pushLabel(String label) {
    labels.put(label, instructions.size());
  }

  public void pushInstruction(AsmInstruction asm) {
    instructions.add(asm);
  }

  public void pushLocalVariable(String name, int offset) {
    offsets.put(name, offset);
  }

  public int getVariableOffset(String name){
    return offsets.get(name);
  }
  
  public void clearLocals() {
    this.offsets.clear();
  }
  
  @Override
  public String toString() {
    return Printer.programToString(this.instructions, this.labels);
  }
}
