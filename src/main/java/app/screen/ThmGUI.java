package app.screen;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.screen.decorator.ThmGUIDacorator;
import thejavalistener.fwk.awt.panel.MyBorderLayout;
import thejavalistener.fwk.awt.panel.MyPanel;
import thejavalistener.fwk.awt.splitpane.MySplitPane;

@Component
public class ThmGUI
{
	@Autowired
	private ThmGUIDacorator decorator;

	@Autowired
	private ThmControls controls;

	@Autowired
	private ThmPage page;
	
	private JPanel contentPane;
	private JPanel pNorth;
	private MySplitPane splitPane;
	
	public ThmGUI()
	{
		contentPane = new MyBorderLayout();
	}
	
	public void init()
	{
		pNorth = new MyPanel(20,0,20,0);
		contentPane.add(pNorth,BorderLayout.NORTH);

		splitPane = new MySplitPane(MySplitPane.VERTICAL,controls.c(),page.c());
		contentPane.add(splitPane.c(),BorderLayout.CENTER);

		page.init();
		controls.init();
		
		pNorth.setBackground(decorator.getBackground());
		splitPane.setDividerColor(decorator.getDividerColor());
		splitPane.setDividerSize(1);
		splitPane.setDividerLocation(decorator.getDividerLocation());
	}
	
	public java.awt.Component c()
	{
		return contentPane;
	}

	public ThmControls getControls()
	{
		return controls;
	}

	public ThmPage getPage()
	{
		return page;
	}
}

