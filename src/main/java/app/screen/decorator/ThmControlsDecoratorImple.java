package app.screen.decorator;

import java.awt.Color;
import java.awt.Font;

import org.springframework.stereotype.Component;

import thejavalistener.fwk.awt.link.MyLink;

@Component
public class ThmControlsDecoratorImple implements ThmControlsDecorator
{
	private Font font = new Font(ThmColors.fontName,Font.PLAIN,14);
	
	@Override
	public Color getFiltersBackground()
	{
		return ThmColors.background;
	}

	@Override
	public Color getLabelsBackground()
	{
		return ThmColors.background;
	}

	@Override
	public Color getHomeBackground()
	{
		return ThmColors.background;
	}

	@Override
	public int getHomeAreaHeight()
	{
		return 150;
	}
	
	@Override
	public int getFilterAreaHeight()
	{
		return 150;
	}
	
	@Override
	public Color getDividerColor()
	{
		return ThmColors.divider;
	}
	
	@Override
	public void decoreFilterTitle(MyLink lnk)
	{
		decoreLabelTitle(lnk);
	}

	@Override
	public void decoreLabelTitle(MyLink lnk)
	{
		// unselected
		
		// foreground
		lnk.getStyle().linkForegroundUnselected = ThmColors.unhighlight;

		// background
		lnk.getStyle().linkBackgroundUnselected = ThmColors.background;
		
		// rollover foreground
		lnk.getStyle().linkForegroundRolloverUnselected = ThmColors.unhighlight;

		// rollover background
		lnk.getStyle().linkBackgroundRolloverUnselected = ThmColors.background;
		
		lnk.c().revalidate();
	}	

	@Override
	public void decoreFilter(MyLink lnk)
	{
		decoreLabel(lnk);
	}
	
	@Override
	public void decoreLabel(MyLink lnk)
	{
		
		// unselected
		
			// foreground
			lnk.getStyle().linkForegroundUnselected = ThmColors.unhighlight;

			// background
			lnk.getStyle().linkBackgroundUnselected = ThmColors.background;
			
			// rollover foreground
			lnk.getStyle().linkForegroundRolloverUnselected = ThmColors.unhighlight;
	
			// rollover background
			lnk.getStyle().linkBackgroundRolloverUnselected = ThmColors.shadow;

		// selected
			
			// foreground
			lnk.getStyle().linkForegroundSelected = ThmColors.highlight;

			// background
			lnk.getStyle().linkBackgroundSelected = ThmColors.background;
			
			// rollover foreground
			lnk.getStyle().linkForegroundRolloverSelected = ThmColors.highlight;
	
			// rollover background
			lnk.getStyle().linkBackgroundRolloverSelected = ThmColors.background;
		
		lnk.getStyle().setLinkFont(font);

	}

	
	@Override
	public String getDefaultFilterTitle()
	{
		return "FILTERS";
	}

	@Override
	public String getDefaultLabelTitle()
	{
		return "LABELS";
	}

	@Override
	public void decoreHomeTitle(MyLink lnkHomeTitle)
	{
		decoreLabelTitle(lnkHomeTitle);
	}
	@Override
	public void decoreHome(MyLink lnk)
	{
		decoreLabel(lnk);
	}

	@Override
	public String getDefaultHomeTitle()
	{
		return "HOME";
	}
}