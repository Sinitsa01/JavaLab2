package bsu.rfe.java.group6.lab2.SINITSA.var6B;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final int WIDTH = 500;
    private static final int HEIGHT = 320;

    private static double mem1;
    

    private JTextField textFieldX;
    private JTextField textFieldY;
    private JTextField textFieldZ;
    private JTextField textFieldResult;
    private JTextField textFieldMem1;
    private JTextField textFieldMem2;
    private JTextField textFieldMem3;


    private ButtonGroup radioButtons = new ButtonGroup();
    private Box hboxFormulaType = Box.createHorizontalBox();
    Box hboxVariablesType = Box.createHorizontalBox();


    private int formulaId = 1;
    

    public Double calculate1(Double x, Double y, Double z) {
        return Math.pow((Math.exp(Math.pow(1+z,2)+ Math.cos(Math.pow(y,3)*Math.PI))),0.25)/Math.pow(Math.cos(Math.pow(Math.E,x))+Math.sqrt(1/x)+Math.pow(Math.E,x*x),Math.sin(x));
    }

    public Double calculate2(Double x, Double y, Double z) {
        return (Math.tan(x*x)+Math.pow(y,0.5))/(z*Math.log10(x+y));
        }

    private void addRadioButton(String buttonName, final int formulaId) {
        JRadioButton button = new JRadioButton(buttonName);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                MainFrame.this.formulaId = formulaId;
                JButton imagePane = new JButton();
                imagePane.updateUI();
            }
        });
        radioButtons.add(button);
        hboxFormulaType.add(button);
    }
    
   

    public MainFrame()   {
        super("Вычисление формулы");
        setSize(WIDTH, HEIGHT);
        Toolkit kit = Toolkit.getDefaultToolkit();
        setLocation((kit.getScreenSize().width - WIDTH) / 2, (kit.getScreenSize().height - HEIGHT) / 2);


        hboxFormulaType.add(Box.createHorizontalGlue());
        addRadioButton("Формула 1", 1);
        addRadioButton("Формула 2", 2);
        radioButtons.setSelected(radioButtons.getElements().nextElement().getModel(), true);
        hboxFormulaType.add(Box.createHorizontalGlue());
        hboxFormulaType.setBorder(BorderFactory.createLineBorder(Color.YELLOW));


        hboxVariablesType.add(Box.createHorizontalGlue());
        

        textFieldMem1 = new JTextField("0", 15);
        textFieldMem1.setMaximumSize(textFieldMem1.getPreferredSize());
        hboxVariablesType.add(Box.createHorizontalStrut(10));
        hboxVariablesType.add(textFieldMem1);
        textFieldMem1.setEditable(false);
        hboxVariablesType.add(Box.createHorizontalStrut(10));
   

        
        hboxVariablesType.add(Box.createHorizontalGlue());
        hboxVariablesType.setBorder(BorderFactory.createLineBorder(Color.BLACK));


        JLabel labelForX = new JLabel("X:");
        textFieldX = new JTextField("0", 10);
        textFieldX.setMaximumSize(textFieldX.getPreferredSize());
        JLabel labelForY = new JLabel("Y:");
        textFieldY = new JTextField("0", 10);
        textFieldY.setMaximumSize(textFieldY.getPreferredSize());
        JLabel labelForZ = new JLabel("Z:");
        textFieldZ = new JTextField("0", 10);
        textFieldZ.setMaximumSize(textFieldZ.getPreferredSize());
        Box hboxVariables = Box.createHorizontalBox();
        hboxVariables.setBorder(BorderFactory.createLineBorder(Color.RED));
        hboxVariables.add(Box.createHorizontalGlue());
        hboxVariables.add(labelForX);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldX);
        hboxVariables.add(Box.createGlue());
        hboxVariables.add(labelForY);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldY);
        hboxVariables.add(Box.createGlue());
        hboxVariables.add(labelForZ);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldZ);
        hboxVariables.add(Box.createHorizontalGlue());


        JLabel labelForResult = new JLabel("Результат:");
        textFieldResult = new JTextField("0", 30);
        textFieldResult.setMaximumSize(textFieldResult.getPreferredSize());
        textFieldResult.setEditable(false);
        Box hboxResult = Box.createHorizontalBox();
        hboxResult.add(Box.createHorizontalGlue());
        hboxResult.add(labelForResult);
        hboxResult.add(Box.createHorizontalStrut(10));
        hboxResult.add(textFieldResult);
        hboxResult.add(Box.createHorizontalGlue());
        hboxResult.setBorder(BorderFactory.createLineBorder(Color.BLUE));


        JButton buttonCalc = new JButton("Вычислить");
        buttonCalc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {
                    Double x = Double.parseDouble(textFieldX.getText());
                    Double y = Double.parseDouble(textFieldY.getText());
                    Double z = Double.parseDouble(textFieldZ.getText());
                    mem1 = Double.parseDouble(textFieldMem1.getText());
                    Double result;
                    if (formulaId == 1)
                        result = calculate1(x, y, z);
                    else result = calculate2(x, y, z);
                    textFieldResult.setText(result.toString());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(MainFrame.this, "Ошибка в формате записи числа с плавающей точкой",
                            "Ошибочный формат числа", JOptionPane.WARNING_MESSAGE);
                }
            }
        });


        JButton buttonDel = new JButton("MC");
        buttonDel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                
                    mem1 = 0;
                    textFieldMem1.setText(String.valueOf(mem1));
               
            }
        });

        JButton buttonSum = new JButton("M+");
        buttonSum.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {
                    Double x = Double.parseDouble(textFieldX.getText());
                    Double y = Double.parseDouble(textFieldY.getText());
                    Double z = Double.parseDouble(textFieldZ.getText());
                    mem1 = Double.parseDouble(textFieldMem1.getText());
                    
                    Double result;
                        if (formulaId == 1) result = mem1 + calculate1(x, y, z);
                        else result = mem1 + calculate2(x, y, z);
                        textFieldMem1.setText(String.valueOf(result));
                   
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(MainFrame.this, "Ошибка в формате записи числа с плавающей точкой",
                            "Ошибочный формат числа", JOptionPane.WARNING_MESSAGE);
                }

            }
        });

        JButton buttonReset = new JButton("Очистить поля");
        buttonReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                textFieldX.setText("0");
                textFieldY.setText("0");
                textFieldZ.setText("0");
                textFieldResult.setText("0");
                mem1 = 0;
                textFieldMem1.setText(String.valueOf(mem1));
                
            }
        });



        Box hboxButtons = Box.createHorizontalBox();
        hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.add(buttonCalc);
        hboxButtons.add(Box.createHorizontalStrut(30));
        hboxButtons.add(buttonReset);
        hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.setBorder(BorderFactory.createLineBorder(Color.GREEN));


        Box hboxButtonsM = Box.createHorizontalBox();
        hboxButtonsM.add(Box.createHorizontalGlue());
        hboxButtonsM.add(buttonDel);
        hboxButtonsM.add(Box.createHorizontalStrut(30));
        hboxButtonsM.add(buttonSum);
        hboxButtonsM.add(Box.createHorizontalGlue());
        hboxButtonsM.setBorder(BorderFactory.createLineBorder(Color.WHITE));


        Box contentBox = Box.createVerticalBox();
        contentBox.add(Box.createVerticalGlue()) ;     
        contentBox.add(hboxFormulaType);
        contentBox.add(hboxButtonsM);

        contentBox.add(hboxVariablesType);
        contentBox.add(hboxVariables);
        contentBox.add(hboxResult);
        
        contentBox.add(hboxButtons);
        contentBox.add(Box.createVerticalGlue());
        getContentPane().add(contentBox, BorderLayout.CENTER);



    }


    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}