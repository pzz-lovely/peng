package chapter4.factory;

import chapter4.calculator.Operation;
import chapter4.calculator.OperationAdd;
import chapter4.calculator.OperationSub;

/**
 * 简单工厂模式
 */
public class OperationFactory {
    public static Operation getOperation(String operation) {
        Operation opera  =null;
        switch (operation) {
            case "+":
                opera = new OperationAdd();
                break;
            case "-":
                opera = new OperationSub();
                break;
            case "*":
                opera = null;
                break;
            case "/":
                break;
        }
        return opera;
    }
}
