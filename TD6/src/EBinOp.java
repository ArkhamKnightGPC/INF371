import edu.polytechnique.xvm.asm.opcodes.*;
import edu.polytechnique.mjava.ast.BinOp;

@SuppressWarnings("unused")
public final class EBinOp extends AbstractExpr {
  public final BinOp op; // operator (enum)
  public final AbstractExpr left; // left operand
  public final AbstractExpr right; // right operand

  public EBinOp(BinOp op, AbstractExpr left, AbstractExpr right) {
    this.op = op;
    this.left = left;
    this.right = right;
  }

  @Override
  public void codegen(CodeGen cg) {
    switch (this.op) {
      case ADD:
        this.left.codegen(cg);
        this.right.codegen(cg);
        cg.pushInstruction(new ADD());
        break;
      case SUB:
        this.left.codegen(cg);
        this.right.codegen(cg);
        cg.pushInstruction(new SUB());
        break;
      case MUL:
        this.left.codegen(cg);
        this.right.codegen(cg);
        cg.pushInstruction(new MULT());
        break;
      case DIV:
        this.left.codegen(cg);
        this.right.codegen(cg);
        cg.pushInstruction(new DIV());
        break;
      case AND:
        this.left.codegen(cg);
        this.right.codegen(cg);
        cg.pushInstruction(new AND());
        break;
      case OR:
        this.left.codegen(cg);
        this.right.codegen(cg);
        cg.pushInstruction(new OR());
        break;
      case EQ:
        this.left.codegen(cg);
        this.right.codegen(cg);
        cg.pushInstruction(new EQ());
        break;
      case NEQ:
        this.left.codegen(cg);
        this.right.codegen(cg);
        cg.pushInstruction(new EQ());
        cg.pushInstruction(new NOT());
        break;
      case LT:
        this.left.codegen(cg);
        this.right.codegen(cg);
        cg.pushInstruction(new LT());
        break;
      case LE:
        this.left.codegen(cg);
        this.right.codegen(cg);
        cg.pushInstruction(new LT());
        this.left.codegen(cg);
        this.right.codegen(cg);
        cg.pushInstruction(new EQ());
        cg.pushInstruction(new OR());
        break;
      case GT:
        this.right.codegen(cg);
        this.left.codegen(cg);
        cg.pushInstruction(new LT());
        break;
      case GE:
        this.left.codegen(cg);
        this.right.codegen(cg);
        cg.pushInstruction(new LT());
        cg.pushInstruction(new NOT());
        break;
    }
  }
}
