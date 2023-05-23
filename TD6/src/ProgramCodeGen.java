import java.util.List;

import edu.polytechnique.mjava.ast.TProcDef;
import edu.polytechnique.mjava.ast.VarDecl;
import edu.polytechnique.xvm.asm.opcodes.GSB;
import edu.polytechnique.xvm.asm.opcodes.STOP;

public class ProgramCodeGen {
  public final CodeGen cg = new CodeGen();

  public static String labelOfProcName(String name) {
    return String.format("__%s", name);
  }

  @SuppressWarnings("unused")
  public void codegen(TProcDef<AbstractExpr, AbstractInstruction> proc) {
    final List<VarDecl> args = proc.getArgs(); // Proc. arguments
    final List<VarDecl> locals = proc.getLocals(); // Proc. locals
    final AbstractInstruction is = proc.getBody(); // Proc. body

    throw new UnsupportedOperationException(); // FIXME
  }

  public void codegen(List<TProcDef<AbstractExpr, AbstractInstruction>> procs) {
    for (TProcDef<AbstractExpr, AbstractInstruction> proc : procs)
      this.codegen(proc);
  }

  public ProgramCodeGen() {
    this.cg.pushInstruction(new GSB(labelOfProcName("main")));
    this.cg.pushInstruction(new STOP());
  }
}
