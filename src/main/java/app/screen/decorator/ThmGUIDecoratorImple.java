package app.screen.decorator;

import java.awt.Color;

import org.springframework.stereotype.Component;

@Component
public class ThmGUIDecoratorImple implements ThmGUIDacorator
{
	@Override
	public int getDividerLocation()
	{
		return 200;
	}
	
	@Override
	public Color getDividerColor()
	{
		return new Color(51,51,51);
	}

	@Override
	public Color getBackground()
	{
		return new Color(18,18,18);
	}
}
