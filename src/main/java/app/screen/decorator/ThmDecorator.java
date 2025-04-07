package app.screen.decorator;

import java.awt.Color;

import thejavalistener.fwk.awt.link.MyLink;

public interface ThmDecorator
{
	public Color getThumbnailBackground();
	public void decoreTitle(MyLink lnk);
	public void decoreArtist(MyLink lnk);
	public void decoreReleasedYear(MyLink lnk);
	public void decoreRecordedYear(MyLink lnk);
	public int getImageSize();
}
