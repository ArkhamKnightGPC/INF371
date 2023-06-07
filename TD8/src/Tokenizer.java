public class Tokenizer {
    boolean isStart;
    boolean isIntNum;
    double num;
    boolean isNonIntNum;
    int decimalDigits;
    boolean isMinUnary;
    boolean isNeg;
    boolean isMemoryRef;
    Calculator calc;

    public Tokenizer(){
        this.isStart = true;
        this.isIntNum = false;
        this.isNonIntNum = false;
        this.num = 0;
        this.decimalDigits = 0;
        this.isMinUnary = false;
        this.isNeg = false;
        this.isMemoryRef = false;
        this.calc = new Calculator();
    }

    void readChar(char c){

        if(c=='C'){
            calc.commandInit();
            this.isStart = true;
            this.isIntNum = false;
            this.isNonIntNum = false;
            this.num = 0;
            this.decimalDigits = 0;
            this.isMinUnary = false;
            this.isNeg = false;
            this.isMemoryRef = false;
        }

        if(c == '$'){
            this.isMemoryRef = true;
        }

        if(Character.isDigit(c)){//chiffre
            this.isStart = false;
            int valC = Character.getNumericValue(c);
            if(this.isNonIntNum){
                decimalDigits++;
                num += Math.pow(10, -decimalDigits)*valC;
            }else{
                if(this.isIntNum){
                    num = 10*num + valC;
                }else{
                    this.isIntNum = true;
                    num = valC;
                }
            }
        }else if(c=='.'){
            this.isIntNum = false;
            this.isNonIntNum = true;
            decimalDigits = 0;
        }else{
            if(this.isIntNum || this.isNonIntNum){
                if(this.isNeg)num = -num;
                if(this.isMemoryRef){
                    calc.commandReadMemory((int)num);
                    this.isMemoryRef = false;
                }else{
                    calc.commandDouble(num);
                }
                this.isNeg = false;
                this.isMinUnary = true;
            }
            this.isIntNum = false;
            this.isNonIntNum = false;
            this.num = 0;
            if(c == '='){
                this.isStart = true;
                calc.commandEqual();
                this.isMinUnary = false;
            }else if(c=='+'){
                calc.commandOperator(Operator.PLUS);
                this.isMinUnary = false;
            }else if(c=='-'){
                if(this.isMinUnary){
                    calc.commandOperator(Operator.MINUS);
                    this.isMinUnary = false;
                }else{
                    this.isNeg = true;
                }
            }else if(c=='*'){
                calc.commandOperator(Operator.MULT);
                this.isMinUnary = false;
            }else if(c=='/'){
                calc.commandOperator(Operator.DIV);
                this.isMinUnary = false;
            }else if(c=='('){
                calc.commandLPar();
                this.isStart = true;
                this.isMinUnary = false;
            }else if(c==')'){
                calc.commandRPar();
                this.isMinUnary = true;
            }
        }
    }

    void readString(String s){
        for(int i=0; i<s.length(); i++){
            char c = s.charAt(i);
            readChar(c);
        }
    }
}
