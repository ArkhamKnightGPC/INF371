import edu.polytechnique.xvm.asm.opcodes.*;
import java.util.Optional;

@SuppressWarnings("unused")
public final class IAssign extends AbstractInstruction {
  public final Optional<String> lvalue; // (optional) left-value
  public AbstractExpr           rvalue; // right-value (expression)

  public IAssign(Optional<String> lvalue, AbstractExpr rvalue) {
    this.lvalue = lvalue;
    this.rvalue = rvalue;
  }

  @Override
  public void codegen(CodeGen cg) {
    String lstring = lvalue.orElse("");
    if(lvalue.isPresent()){//attribution
      int varOffset = cg.getVariableOffset(lstring);
      this.rvalue.codegen(cg);
      cg.pushInstruction(new WFR(varOffset));
    }else{//expression
      this.rvalue.codegen(cg);
    }
  }
}
