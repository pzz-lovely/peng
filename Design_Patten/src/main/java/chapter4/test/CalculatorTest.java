package chapter4.test;

import chapter4.calculator.Operation;
import chapter4.factory.OperationFactory;

public class CalculatorTest {
    public static void main(String[] args) {
        Operation operation = OperationFactory.getOperation("+");
        operation.setNumber1(1);
        operation.setNumber1(3);
        System.out.println(operation.getResult());

    }
}
