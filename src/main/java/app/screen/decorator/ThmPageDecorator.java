package app.screen.decorator;

import java.awt.Color;

import thejavalistener.fwk.awt.link.MyLink;

public interface ThmPageDecorator
{
	public Color getPageBackground();
	public int getSectionGap();
	public int getScrollbarSpeed();
	public ThmSectionDecorator getSectionDecorator();
	public int getScrollbarWidth();
	public Color getScrollbarForeground();
}
