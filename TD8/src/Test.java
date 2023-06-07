public class Test {

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        calculator.commandDouble(10); System.out.print("B");
        calculator.commandOperator(Operator.MULT); System.out.print("A");
        calculator.commandLPar(); System.out.print("Y");
        calculator.commandDouble(2); System.out.print("O");
        calculator.commandOperator(Operator.PLUS); System.out.print("N");
        calculator.commandDouble(3); System.out.print("E");
        calculator.commandRPar(); System.out.print("T");
        calculator.commandEqual(); System.out.println("A");
        System.out.println(calculator.getResult());

        calculator.commandDouble(3);
        calculator.commandEqual();
        calculator.commandDouble(2);
        calculator.commandEqual();
        calculator.commandDouble(1);
        calculator.commandEqual();
        calculator.commandReadMemory(1);
        System.out.println(calculator.getResult());

        Tokenizer tokenizer = new Tokenizer();
        tokenizer.readChar('8');System.out.print("N");
        tokenizer.readChar('+');System.out.print("O");
        tokenizer.readChar('-');System.out.print("S");
        tokenizer.readChar('2');System.out.print("S");
        tokenizer.readChar('=');System.out.println("A");
        //tokenizer.readString("8+-2=");
        System.out.println(tokenizer.calc.getResult());
    }
    
}
