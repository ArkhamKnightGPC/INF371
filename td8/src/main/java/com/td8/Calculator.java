package com.td8;

import java.util.*;

public class Calculator {
    Stack<Double> numbers;
    Stack<Operator> operators;
    LinkedList<Double> results;

    public Calculator(){
        this.numbers = new Stack<Double>();
        this.operators = new Stack<Operator>();
        this.results = new LinkedList<Double>();
    }

    @Override
    public String toString() {
        return numbers.toString() + "\n" + operators.toString();
    }

    void pushDouble(double d){
        numbers.add(d);
    }

    void pushOperator(Operator o){
        operators.push(o);
    }

    public double getResult(){
        if(numbers.empty()){
            throw new RuntimeException();
        }else{
            double d = numbers.pop();
            numbers.push(d);
            return d;
        }
    }

    public void executeBinOperator(Operator op){
        if(op.equals(Operator.OPEN))return;

        double num1 = numbers.pop();
        double num2 = numbers.pop();

        double result = 0;
        switch(op){
            case PLUS:
                result = num1 + num2;
                break;
            case MINUS:
                result = num2 - num1;
                break;
            case MULT:
                result = num1*num2;
                break;
            case DIV:
                result = num2/num1;
                break;
            default:
                break;
        }
        numbers.push(result);
    }

    static int precedence(Operator op){
        int priority = 0;
        switch(op){
            case PLUS:
                priority = 0;
                break;
            case MINUS:
                priority = 0;
                break;
            case MULT:
                priority = 1;
                break;
            case DIV:
                priority = 1;
                break; 
            case OPEN:
                priority = -1;
                break;
        }
        return priority;
    }

    void commandOperator(Operator op){
        int opPrecedence = precedence(op);
        while(!operators.empty()){
            Operator cur = operators.pop();
            if(precedence(cur) >= opPrecedence){//pop cur from stack
                executeBinOperator(cur);
            }else{
                operators.push(cur);//put back in stack
                break;
            }
        }
        operators.push(op);//insert new operator
    }

    void commandDouble(double d){
        numbers.push(d);
    }

    void commandEqual(){
        while(!operators.empty()){
            executeBinOperator(operators.pop());
        }
        results.add(getResult());
    }

    void commandLPar(){
        operators.push(Operator.OPEN);
    }

    void commandRPar(){
        Operator cur;
        do{
            cur = operators.pop();
            executeBinOperator(cur);
        }while(cur != Operator.OPEN);
    }

    void commandInit(){
        while(!numbers.empty())numbers.pop();
        while(!operators.empty())operators.pop();
    }

    void commandReadMemory(int i){
        commandDouble(results.get(results.size()-i));
    }
}
