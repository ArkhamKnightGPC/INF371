import java.util.Vector;

import edu.polytechnique.xvm.asm.opcodes.*;

public final class ECall extends AbstractExpr {
  public final String               name; // procedure name
  public final Vector<AbstractExpr> args; // arguments

  public ECall(String name, Vector<AbstractExpr> args) {
    this.name = name;
    this.args = args;
  }

  @Override
  public void codegen(CodeGen cg) {
    String procedureLabel = ProgramCodeGen.labelOfProcName(name);

    //1. push arguments onto the stack
    for(AbstractExpr expr : args){
      expr.codegen(cg);
    }

    //2. store FP,PC(return address) in stack, jump to procedure
    cg.pushInstruction(new GSB(procedureLabel));

    //3. clear arguments
    int countArgs = args.size();
    while(countArgs-- > 0)cg.pushInstruction(new POP());

    //4. push return value onto stack
    cg.pushInstruction(new PRX());
  }
}
