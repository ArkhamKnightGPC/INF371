import edu.polytechnique.xvm.asm.opcodes.*;
import java.util.Optional;

@SuppressWarnings("unused")
public final class IReturn extends AbstractInstruction {
  public final Optional<AbstractExpr> result; // Value to return

  public IReturn() {
    this(Optional.empty());
  }

  public IReturn(Optional<AbstractExpr> result) {
    this.result = result;
  }

  @Override
  public void codegen(CodeGen cg) {
    //1. must return value using general register R
    if(result.isPresent()){
      AbstractExpr presentResult = result.get();
      presentResult.codegen(cg);
      cg.pushInstruction(new PXR());
    }
    //if there is no value to return (void), do nothing

    //2. jump to return address, restore FP
    cg.pushInstruction(new RET());
  }
}
