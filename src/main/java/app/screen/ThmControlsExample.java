package app.screen;

import app.screen.decorator.ThmControlsDecoratorImple;
import thejavalistener.fwk.awt.MyAwt;
import thejavalistener.fwk.awt.testui.MyTestUI;
import thejavalistener.fwk.util.string.MyString;

public class ThmControlsExample
{
	public static void main(String[] args)
	{
		MyAwt.setWindowsLookAndFeel();
		
		ThmControls thmc = new ThmControls();
		thmc.init();
		thmc.setListener(new EscuchaControls());
		MyTestUI.test(thmc.c()).addButton("Add filter",e->thmc.addFilter(MyString.generateRandom().toLowerCase()))
							   .addButton("Add Label",e->thmc.addLabel(MyString.generateRandom().toLowerCase()))
							   .addButton("Remove Labels",e->thmc.removeLabels())
		                       .run();
	}
	
	static class EscuchaControls implements ThmControlsListener
	{
		@Override
		public void filterSelected(ThmControls thmc,String filter)
		{
			thmc.setLabelTitle(filter);
		}

		@Override
		public void labelSelected(ThmControls thmc,String filter, String label)
		{
			System.out.println("Filter: "+filter+", Label: "+label);
		}
	}
}
