package Gobang;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JSplitPane;


public class fourthpanel extends JSplitPane{
       //单例模式
	private static fourthpanel instance=new fourthpanel();
	private fourthpanel() {
		setDividerLocation(400);
		setEnabled(false);
		setLeftComponent(toolpanel.getInstance());
		setRightComponent(ChessPanel.getInstance());
		
	};
	public static fourthpanel getInstance() {
		 return instance;
	}
}
