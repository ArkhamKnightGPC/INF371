import java.util.Optional;

import edu.polytechnique.xvm.asm.interfaces.AsmInstruction;
import edu.polytechnique.xvm.asm.opcodes.*;

@SuppressWarnings("unused")
public final class IIf extends AbstractInstruction {
  public final AbstractExpr condition; // condition (<> 0 => true)
  public final AbstractInstruction iftrue; // if "true (<> 0)" branch
  public final AbstractInstruction iffalse; // if "false (== 0)" branch

  public IIf(AbstractExpr condition,
      AbstractInstruction iftrue,
      AbstractInstruction iffalse) {
    this.condition = condition;
    this.iftrue = iftrue;
    this.iffalse = iffalse;
  }

  @Override
  public void codegen(CodeGen cg) {
    String ifFalseLabel = CodeGen.generateLabel();
    String endLabel = CodeGen.generateLabel();

    //Compute condition(true/1 or false/0)
    condition.codegen(cg);
    //if false, jump to ifFalse
    cg.pushInstruction(new GTZ(ifFalseLabel));
    //else we stay and run ifTrue
    iftrue.codegen(cg);
    //after ifTrue we skip ifFalse
    cg.pushInstruction(new GTO(endLabel));
    //GTZ lands here
    cg.pushLabel(ifFalseLabel);
    //execute ifFalse
    iffalse.codegen(cg);
    //GTO lands here
    cg.pushLabel(endLabel);
  }
}
