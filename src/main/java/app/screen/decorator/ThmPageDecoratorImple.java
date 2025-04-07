package app.screen.decorator;

import java.awt.Color;

import org.springframework.stereotype.Component;

@Component
public class ThmPageDecoratorImple implements ThmPageDecorator
{
	ThmSectionDecorator sectionDecorator = new ThmSectionDecoratorImple();

	@Override
	public Color getPageBackground()
	{
		return new Color(18,18,18);
	}

	@Override
	public int getSectionGap()
	{
		return 35;
	}

	@Override
	public int getScrollbarSpeed()
	{
		return 35;
	}
	
	@Override
	public int getScrollbarWidth()
	{
		return 7;
	}
	
	@Override
	public Color getScrollbarForeground()
	{
		return new Color(173,173,173);
	}

	@Override
	public ThmSectionDecorator getSectionDecorator()
	{
		return sectionDecorator;
	}

}
