import edu.polytechnique.xvm.asm.opcodes.*;

@SuppressWarnings("unused")
public final class EVar extends AbstractExpr {
  public final String name; // variable name

  public EVar(String name) {
    this.name = name;
  }

  @Override
  public void codegen(CodeGen cg) {
    int varOffset = cg.getVariableOffset(name);
    cg.pushInstruction(new RFR(varOffset));
  }
}
