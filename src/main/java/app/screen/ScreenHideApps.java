package app.screen;

import java.awt.FlowLayout;

import javax.swing.JButton;

import org.springframework.stereotype.Component;

import thejavalistener.fwk.console.Progress;
import thejavalistener.fwk.frontend.MyAbstractScreen;
import thejavalistener.fwk.util.MyColor;
import thejavalistener.fwk.util.MyThread;
import thejavalistener.fwk.util.string.MyRegex;
import thejavalistener.fwk.util.string.MyString;

@Component
public class ScreenHideApps extends MyAbstractScreen
{
	@Override
	protected void createUI()
	{
		setLayout(new FlowLayout());
		JButton bHide = new JButton("Hide Apps");
		bHide.addActionListener(l->getMyApp().getMyAppContainer().showApps(false));
		add(bHide);

		JButton bShow = new JButton("Show Apps");
		bShow.addActionListener(l->getMyApp().getMyAppContainer().showApps(true));
		add(bShow);		

		JButton bHideScr = new JButton("Hide Screens");
		bHideScr.addActionListener(l->getMyApp().showScreens(false));
		add(bHideScr);

		JButton bShowScr = new JButton("Show Screens");
		bShowScr.addActionListener(l->getMyApp().showScreens(true));
		add(bShowScr);		
	}
			
	@Override
	public void onDataUpdated()
	{
	}
	
	@Override
	public void start()
	{
	}
		
	@Override
	public String getName()
	{
		return "Hide/Show";
	}
}
