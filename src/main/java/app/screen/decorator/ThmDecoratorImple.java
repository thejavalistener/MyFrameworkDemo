package app.screen.decorator;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;

import org.springframework.stereotype.Component;

import thejavalistener.fwk.awt.link.MyLink;

@Component
public class ThmDecoratorImple implements ThmDecorator
{
	@Override
	public Color getThumbnailBackground()
	{
		return ThmColors.thumbnailBackground;
	}
	
	@Override
	public void decoreTitle(MyLink lnk)
	{
		lnk.getStyle().linkForegroundUnselected = ThmColors.highlight;
		lnk.getStyle().setLinkFont(new Font(ThmColors.fontName,Font.PLAIN,16));
//		Insets i = lnk.getStyle().linkBackgroundInsets;
//		i.top = 7;
		
		Insets i = lnk.getStyle().linkInsets;
		i.top = 5;
		i.bottom = 0;
	}				
	
	@Override
	public void decoreArtist(MyLink lnk)
	{
		
		decoreReleasedYear(lnk);
		Insets i = lnk.getStyle().linkInsets;
		i.bottom = 3;
		i.top = 0;
		
//		lnk.getStyle().linkForegroundUnselected = ThmColors.unhighlight;
//		lnk.getStyle().linkForegroundRolloverUnselected = ThmColors.shadow;
//		lnk.getStyle().setLinkBackgroundUnselected(getThumbnailBackground());
//		lnk.getStyle().setLinkBackgroundRolloverUnselected(getThumbnailBackground());
//
//		lnk.getStyle().setLinkFont(new Font(ThmColors.fontName,Font.PLAIN,14));
//		Insets i = lnk.getStyle().linkBackgroundInsets;
//		i.top = 0;
//		i.bottom = 3;

		
		
//		lnk.getStyle().linkForegroundUnselected = ThmColors.unhighlight;
//		lnk.getStyle().linkForegroundRolloverUnselected = ThmColors.highlight;
//		lnk.getStyle().setLinkFont(new Font(ThmColors.fontName,Font.PLAIN,14));
//		lnk.getStyle().setLinkBackgroundUnselected(getThumbnailBackground());
//		lnk.getStyle().setLinkBackgroundRolloverUnselected(getThumbnailBackground());
//		Insets i = lnk.getStyle().linkBackgroundInsets;
//		i.top = 0;
//		i.bottom = 3;
	}
		
	@Override
	public void decoreReleasedYear(MyLink lnk)
	{
		// unselected
			lnk.getStyle().linkForegroundUnselected = ThmColors.unhighlight;
			lnk.getStyle().linkForegroundRolloverUnselected = ThmColors.highlight;
			lnk.getStyle().setLinkBackgroundUnselected(getThumbnailBackground());
			lnk.getStyle().setLinkBackgroundRolloverUnselected(ThmColors.shadow);

			lnk.getStyle().setLinkFont(new Font(ThmColors.fontName,Font.PLAIN,14));

		Insets i = lnk.getStyle().linkBackgroundInsets;
		i.top = 3;
		i.bottom = 0;
		
		i = lnk.getStyle().linkInsets;
		i.bottom = 7;
		
	}
	
	@Override
	public void decoreRecordedYear(MyLink lnk)
	{
		decoreReleasedYear(lnk);
//		lnk.getStyle().linkForegroundUnselected = ThmColors.unhighlight;
//		lnk.getStyle().linkForegroundRolloverUnselected = ThmColors.highlight;
//		lnk.getStyle().setLinkFont(new Font(ThmColors.fontName,Font.PLAIN,14));
//		lnk.getStyle().setLinkBackgroundUnselected(getThumbnailBackground());
//		lnk.getStyle().setLinkBackgroundRolloverUnselected(getThumbnailBackground());
//		Insets i = lnk.getStyle().linkBackgroundInsets;
//		i.top = 0;
//		i.bottom = 3;
	}

	@Override
	public int getImageSize()
	{
		return 280;
	}
}
