package app.screen;

import java.awt.Color;

import javax.swing.JPanel;

import thejavalistener.fwk.awt.MyAwt;
import thejavalistener.fwk.awt.panel.MyRandomColorPanel;
import thejavalistener.fwk.awt.splitpane.MySplitPane;
import thejavalistener.fwk.awt.testui.MyTestUI;

public class MyMultiSplitPaneTest
{
	public static void main(String[] args)
	{
		MyAwt.setWindowsLookAndFeel();
		JPanel p1 = new MyRandomColorPanel();
		JPanel p2 = new MyRandomColorPanel();
		JPanel p3 = new MyRandomColorPanel();
		
		MyMultiSplitPane msp = new MyMultiSplitPane(MySplitPane.HORIZONTAL,p1,p2,p3);
		msp.setDividerSize(1);
		msp.setDividerColor(Color.GREEN);
		msp.setDividerLocation(0,100);
		msp.setDividerLocation(1,200);
		
		
		MyTestUI.test(msp.c()).run();
	}
}
