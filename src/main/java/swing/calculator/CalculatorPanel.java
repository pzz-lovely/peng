package swing.calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A panel with calculator buttons and result display
 * 带有计算器按钮和结果显示的面板
 */
public class CalculatorPanel extends JPanel {
    private JButton display;
    private JPanel panel;
    private double result;
    private String lastCommand;
    private boolean start;
    public CalculatorPanel(){
        setLayout(new BorderLayout());      //设置布局
        result= 0 ;
        lastCommand = "=";
        start = true;

        //add the display
        display = new JButton("0");
        display.setEnabled(false);
        add(display,BorderLayout.NORTH);    //北
        ActionListener insert = new InsertAction();
        ActionListener command = new CommandAction();

        //add the buttons in a 4 x 4grid
        panel =new JPanel();
        panel.setLayout(new GridLayout(4,4));  //网格布局


    }

    /**
     * Add a button to the center panel
     * @param label
     * @param listener
     */
    private void addButton(String label, ActionListener listener) {
        JButton button = new JButton(label);
        button.addActionListener(listener);
        panel.add(button);
    }
    private class InsertAction implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String input = e.getActionCommand();    //获取动作命令
            if (start) {
                display.setText("");
                start = false;
            }
            display.setText(display.getText() + input);
        }
    }

    private class CommandAction implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();  //获取动作命令
            if (start) {
                if (command.equals("-")) {
                    display.setText(command);
                    start = false;
                }else lastCommand= command;
            }else {
                calculate(Double.parseDouble(display.getText()));
                lastCommand = command;
                start = true;
            }
        }
    }

    public void calculate(double x) {
        if(lastCommand.equals("+"))result+=x;
        else if(lastCommand.equals("-"))result-=x;
        else if(lastCommand.equals("*"))result*=x;
        else if(lastCommand.equals("/"))result/=x;
        display.setText(" " + result);
    }

}
