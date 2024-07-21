package calculator;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class calculator {
	JTextField textField=new JTextField();
	//单例模式
	private static calculator instance;
	private calculator(){}
	public static calculator getInstance() {
		   if (instance==null) {
			    instance=new calculator();
		    }
		   return instance;
	}
	//main函数
	public static void main(String[] args) {
		 getInstance().begin();
	}
	public void showNumber(String num) {
		textField.setText(num);
	}
	private void begin() {
		//JTextField设置
		textField.setHorizontalAlignment(JTextField.RIGHT);//靠右输出
		textField.setPreferredSize(new Dimension(400,90));
		textField.setFont(new Font("楷体",Font.BOLD,40));
		//Jpanel（面板）设置
		JPanel panel=new JPanel();
		panel.setLayout(new GridLayout(6,4));
		//监听器
		ActionListener actionListener =new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String command=e.getActionCommand();
				Control.getInstance().pressBtn(command);
			}
		};
		//按钮设置
		JButton []btn=new JButton[24];
	      btn[0]= new JButton("%");
		btn[0].setActionCommand("%");
		 btn[1]= new JButton("CE");
		 btn[1].setActionCommand("CE");
		btn[2]= new JButton("C");
		btn[2].setActionCommand("C");
		btn[3]= new JButton("→");
		btn[3].setActionCommand("→");
		btn[4]= new JButton("1/x");
		btn[4].setActionCommand("1/x");
		btn[5]= new JButton("x²");
		btn[5].setActionCommand("x²");
		btn[6]= new JButton("√x");
		btn[6].setActionCommand("√x");
		btn[7]= new JButton("÷");
		btn[7].setActionCommand("÷");
		btn[8]= new JButton("7");
		btn[8].setActionCommand("7");
		btn[9]= new JButton("8");
		btn[9].setActionCommand("8");
		btn[10]= new JButton("9");
		btn[10].setActionCommand("9");
		btn[11]= new JButton("×");
		btn[11].setActionCommand("×");
		btn[12]= new JButton("4");
		btn[12].setActionCommand("4");
		btn[13]= new JButton("5");
		btn[13].setActionCommand("5");
		btn[14]= new JButton("6");
		btn[14].setActionCommand("6");
		btn[15]= new JButton("—");
		btn[15].setActionCommand("—");
		btn[16]= new JButton("1");
		btn[16].setActionCommand("1");
		btn[17]= new JButton("2");
		btn[17].setActionCommand("2");
		btn[18]= new JButton("3");
		btn[18].setActionCommand("3");
		btn[19]= new JButton("+");
		btn[19].setActionCommand("+");
		btn[20]= new JButton("+/-");
		btn[20].setActionCommand("+/-");
		btn[21]= new JButton("0");
		btn[21].setActionCommand("0");
		btn[22]= new JButton(".");
		btn[22].setActionCommand(".");
		btn[23]= new JButton("=");
		btn[23].setActionCommand("=");
		for(int i=0;i<=23;i++) {
			btn[i].addActionListener(actionListener);
			btn[i].setFont(new Font("宋体",Font.BOLD,20));
			panel.add(btn[i]);
		}
		//JFrame设置
		JFrame frame=new JFrame("计算器");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout());//布局
		frame.getContentPane().add(textField, BorderLayout.NORTH);//放入textField
		frame.getContentPane().add(panel,BorderLayout.CENTER);
		frame.setSize(400, 500);
		frame.setVisible(true);
	} 
}
