package app.screen;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import app.screen.decorator.ThmSectionDecorator;
import spotify.Album;
import thejavalistener.fwk.awt.link.MyLink;
import thejavalistener.fwk.awt.panel.MatrixLayout;
import thejavalistener.fwk.awt.panel.MyBorderLayout;
import thejavalistener.fwk.awt.panel.MyPanel;

@Component
public class ThmSection
{
	@Autowired
	private ThmSectionDecorator decorator;
	
	@Autowired
	private ApplicationContext ctx;
	
	private JPanel contentPane;
	private MyLink lnkTitle;
	private JPanel matrix;
	
	public ThmSection()
	{
		contentPane = new MyBorderLayout();
		
		lnkTitle = new MyLink(" ");
		contentPane.add(lnkTitle.c(),BorderLayout.NORTH);
		
		matrix = new MyPanel(0,0,0,0);
		matrix.setBorder(null);
		
		contentPane.add(matrix,BorderLayout.CENTER);
	}
	
	private void _setBackground(Color c)
	{
		contentPane.setBackground(c);
		matrix.setBackground(c);
	}
		
	public void addAlbum(Album a)
	{
		Thumbnail thm = ctx.getBean(Thumbnail.class);
		thm.init();
		thm.setAlbum(a);
		matrix.add(thm.c());
	}
	
	public void setTitle(String t)
	{
		if( decorator!=null )
		{
			decorator.decoreSectionTitle(lnkTitle);
		}

		lnkTitle.setText(t);
	}
	
	public java.awt.Component c()
	{
		return contentPane;
	}

	public void init()
	{
		int nCols = decorator.getColumnCount();
		int vGap = decorator.getVHGap()[0];
		int hGap = decorator.getVHGap()[1];		
		decorator.decoreSectionTitle(lnkTitle);
		matrix.setLayout(new MatrixLayout(nCols,hGap,vGap,MatrixLayout.CENTER_ALIGN));
		_setBackground(decorator.getSectionBackground());
	}
}
