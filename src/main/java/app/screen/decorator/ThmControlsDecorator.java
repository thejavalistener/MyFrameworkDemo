package app.screen.decorator;

import java.awt.Color;

import thejavalistener.fwk.awt.link.MyLink;

public interface ThmControlsDecorator
{
	public Color getHomeBackground();
	public Color getFiltersBackground();
	public Color getLabelsBackground();
	public int getHomeAreaHeight();
	public int getFilterAreaHeight();
	public Color getDividerColor();
	public void decoreHomeTitle(MyLink lnkHomeTitle);
	public void decoreFilterTitle(MyLink lnk);
	public void decoreLabelTitle(MyLink lnk);
	public void decoreHome(MyLink lnk);
	public void decoreFilter(MyLink lnk);
	public void decoreLabel(MyLink lnk);
	public String getDefaultHomeTitle();
	public String getDefaultFilterTitle();
	public String getDefaultLabelTitle();
}
