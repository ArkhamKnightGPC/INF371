import java.util.List;
import java.util.Optional;

import edu.polytechnique.mjava.ast.TProcDef;
import edu.polytechnique.mjava.ast.VarDecl;
import edu.polytechnique.xvm.asm.opcodes.GSB;
import edu.polytechnique.xvm.asm.opcodes.PUSH;
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

    cg.pushLabel(labelOfProcName(proc.getName()));

    //offset is defined relative to frame pointer
    int offset = -args.size();

    //1. take arguments in stack, declare as local variables
    for(VarDecl arg : args){
      cg.pushLocalVariable(arg.getName(), offset++);
    }

    offset+= 2;//jump over pc,fp in stack

    //2. declare all local variables and push onto stack
    for(VarDecl local : locals){
      cg.pushLocalVariable(local.getName(), offset++);
      cg.pushInstruction(new PUSH(0));

    }
    
    //3. execute body
    is.codegen(cg);

    //4. we can clear locals
    cg.clearLocals();
    
    //5. if function does not have explicit return, we add return instruction
    if(!proc.getRtype().isPresent()){
      Optional<AbstractExpr> nullResult = Optional.ofNullable(null);
      IReturn Ireturn = new IReturn(nullResult);
      Ireturn.codegen(cg);
    }

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
