package app.screen;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollBar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import app.screen.decorator.ThmPageDecorator;
import thejavalistener.fwk.awt.panel.GridLayout2;
import thejavalistener.fwk.awt.panel.MyBorderLayout;
import thejavalistener.fwk.awt.panel.MyPanel;

@Component
public class ThmPage
{
	@Autowired
	private ApplicationContext ctx;
	
	@Autowired
	private ThmPageDecorator decorator;

	private JPanel contentPane;
	private JPanel ccontentPane;
	
	private MyScrollPane2 scrollPane;
	
	public ThmPage()
	{
		contentPane = new MyBorderLayout();
		contentPane.setBorder(null);
		ccontentPane = new MyPanel(0,0,0,0);
		ccontentPane.setBorder(null);
		ccontentPane.setLayout(new GridLayout2(0,1,0,0));
		
		contentPane.add(scrollPane=new MyScrollPane2(ccontentPane),BorderLayout.CENTER);
		scrollPane.setBorder(null);
	}
	
	public void init()
	{
		contentPane.setBackground(decorator.getPageBackground());
		ccontentPane.setBackground(decorator.getPageBackground());
		
		scrollPane.setBackground(decorator.getPageBackground());
		scrollPane.configureScrollBars(decorator.getScrollbarWidth(),decorator.getScrollbarForeground());
		
		JScrollBar vScrollBar = scrollPane.getVerticalScrollBar();

		// Configurar la velocidad
		vScrollBar.setUnitIncrement(decorator.getScrollbarSpeed());    // Incremento por unidad en el scroll vertical
		vScrollBar.setBlockIncrement(decorator.getScrollbarSpeed());  // Incremento por bloque en el scroll vertical
	}
	
	public ThmSection createSection(String title)
	{
		ThmSection section = ctx.getBean(ThmSection.class);
		section.init();

		section.setTitle(title);
		int sectionGap = decorator.getSectionGap();
		MyBorderLayout p = new MyBorderLayout(0,0,sectionGap,0);
		p.setBackground(decorator.getPageBackground());
		
		p.add(section.c(),BorderLayout.CENTER);
		ccontentPane.add(p);
		
		scrollPane.revalidate();
		ccontentPane.revalidate();

		return section;
	}
	
//	public void addSection(ThmSection section)
//	{
//		section.init();
//		int sectionGap = decorator.getSectionGap();
//		MyBorderLayout p = new MyBorderLayout(0,0,sectionGap,0);
//		p.setBackground(decorator.getPageBackground());
//		
//		p.add(section.c(),BorderLayout.CENTER);
//		ccontentPane.add(p);
//		
//		scrollPane.revalidate();
//		ccontentPane.revalidate();
//	}
	
	public void removeSections()
	{
		ccontentPane.removeAll();
		ccontentPane.revalidate();
		ccontentPane.repaint();
	}
		
	public java.awt.Component c()
	{
		return contentPane;
	}
}
