package chapter4.calculator;

/**
 * ���������
 */

public abstract class Operation {
    protected double number1;
    protected  double number2;

    public double getNumber1() {
        return number1;
    }

    public void setNumber1(double number1) {
        this.number1 = number1;
    }

    public double getNumber2() {
        return number2;
    }

    public void setNumber2(double number2) {
        this.number2 = number2;
    }

    /**
     * ͨ��������д������� ����� Number1 �� number2�Ľ��
     * @return
     */
    public abstract double getResult();
}
