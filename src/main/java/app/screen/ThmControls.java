package app.screen;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.screen.decorator.ThmControlsDecorator;
import thejavalistener.fwk.awt.link.MyLink;
import thejavalistener.fwk.awt.link.MyLinkGroup;
import thejavalistener.fwk.awt.panel.MatrixLayout;
import thejavalistener.fwk.awt.panel.MyBorderLayout;
import thejavalistener.fwk.awt.splitpane.MySplitPane;
import thejavalistener.fwk.util.MyColor;

@Component
public class ThmControls
{
	@Autowired
	private ThmControlsDecorator decorator;

	private MyLinkGroup lnkGrpFilters;
	private MyLinkGroup lnkGrpLabels;

	private MyMultiSplitPane splitPane;
	private JPanel contentPane;
	private JPanel pHome;
	private JPanel pFilters;
	private JPanel pLabels;

	private MyLink lnkHomeTitle;
	private MyLink lnkFilterTitle;
	private MyLink lnkLabelTitle;
	
	private ThmControlsListener listener;
	
	private ThmControls outer = this;
	
	public ThmControls()
	{
		pHome = new JPanel(new MatrixLayout(1,0,0,MatrixLayout.LEFT_ALIGN));
		pFilters = new JPanel(new MatrixLayout(1,0,0,MatrixLayout.LEFT_ALIGN));
		pLabels = new JPanel(new MatrixLayout(1,0,0,MatrixLayout.LEFT_ALIGN));

		splitPane = new MyMultiSplitPane(MySplitPane.HORIZONTAL,pHome,pFilters,pLabels);
		
		contentPane = new  MyBorderLayout();
		contentPane.add(splitPane.c(),BorderLayout.CENTER);

		lnkGrpFilters = new MyLinkGroup();
		lnkGrpFilters.setActionListener(new EscuchaFilter());
		lnkGrpLabels = new MyLinkGroup();	
		lnkGrpLabels.setActionListener(new EscuchaLabel());
		
		lnkHomeTitle = new MyLink("");
		lnkFilterTitle = new MyLink("");
		lnkLabelTitle = new MyLink("");
				
		pHome.add(lnkHomeTitle.c());
		pFilters.add(lnkFilterTitle.c());
		pLabels.add(lnkLabelTitle.c());
		
	}
	
	public void init()
	{
		splitPane.setDividerSize(1);
		
		splitPane.setDividerLocation(0,decorator.getHomeAreaHeight());
		splitPane.setDividerLocation(1,decorator.getFilterAreaHeight());
		
		splitPane.setDividerColor(decorator.getDividerColor());
		pHome.setBackground(decorator.getHomeBackground());
		pFilters.setBackground(decorator.getFiltersBackground());
		pLabels.setBackground(decorator.getLabelsBackground());	

		decorator.decoreHomeTitle(lnkHomeTitle);
		lnkHomeTitle.setText(decorator.getDefaultHomeTitle());

		decorator.decoreFilterTitle(lnkFilterTitle);
		lnkFilterTitle.setText(decorator.getDefaultFilterTitle());

		lnkLabelTitle.setText(decorator.getDefaultLabelTitle());
		decorator.decoreLabelTitle(lnkLabelTitle);
	}

	public void addHome(String homeContent)
	{
		MyLink lnk = new MyLink(homeContent);
		
		if( decorator!=null )
		{
			decorator.decoreHome(lnk);
		}
	
		lnkGrpFilters.addLink(lnk);

		pHome.add(lnk.c());
		contentPane.validate();
	}
	
	public void addFilter(String filter)
	{
		MyLink lnk = new MyLink(filter);
		
		if( decorator!=null )
		{
			decorator.decoreFilter(lnk);
		}
	
		lnkGrpFilters.addLink(lnk);

		pFilters.add(lnk.c());
		contentPane.validate();
	}
	
	public void addLabel(String label)
	{			
		MyLink lnk = new MyLink(label);
		
		if( decorator!=null )
		{
			decorator.decoreLabel(lnk);
		}

		lnkGrpLabels.addLink(lnk);		

		pLabels.add(lnk.c());
		contentPane.validate();
	}
	
	public void removeLabels()
	{
		int n = pLabels.getComponentCount()-1;
		while(pLabels.getComponentCount()>1 )
		{
			java.awt.Component cmp = pLabels.getComponent(n);
			if( !cmp.equals(lnkLabelTitle.c()) )
			{
				pLabels.remove(cmp);
				n--;
			}
		}

		pLabels.revalidate();
		pLabels.repaint();
		
		lnkLabelTitle.setText(decorator.getDefaultLabelTitle());
	}	
	
	public void setSelectedFilter(String filter)
	{
		throw new RuntimeException("No implementado todavía");
	}
	
	public void setSelectedLabel(String label)
	{
		throw new RuntimeException("No implementado todavía");
	}

	public void setLabelTitle(String title)
	{
		lnkLabelTitle.setText(title);
	}
	
	public void setFilterTitle(String title)
	{
		lnkFilterTitle.setText(title);
	}
	
	public void setListener(ThmControlsListener lst)
	{
		this.listener = lst;
	}
	
	public java.awt.Component c()
	{
		return contentPane;
	}
	
	class EscuchaFilter implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if(listener!=null)
			{
				String filter = lnkGrpFilters.getSelected().getText();
				listener.filterSelected(outer,filter.toUpperCase());
			}
		}
	}
	class EscuchaLabel implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			String filter = lnkGrpFilters.getSelected().getText();
			String label = lnkGrpLabels.getSelected().getText();
			listener.labelSelected(outer,filter,label);
		}
	}
}
