//package app.screen;
//
//import app.screen.decorator.ThmPageDecoratorImple;
//import thejavalistener.fwk.awt.MyAwt;
//import thejavalistener.fwk.awt.testui.ActionUIListener;
//import thejavalistener.fwk.awt.testui.MyTestUI;
//import thejavalistener.fwk.awt.testui.MyTestUIInterface;
//
//public class ThumPageExample
//{
//	private static ThmSection section = null;
//	private static ThmPage page = null;
//	
//	public static void main(String[] args)
//	{
//		MyAwt.setWindowsLookAndFeel();
//		
//		page = new ThmPage();
//		page.init();
//		
//		MyTestUI mtu = MyTestUI.test(page.c());
//		
//		mtu.addButton("Add Section",new EscuchaAddSection());		
//		mtu.addButton("Add Thumbnail",new EscuchaAddThumbnail());
//		mtu.addButton("Remove Sections",e->page.removeSections());
//		
//		mtu.maximize().run();
//	}	
//	
//	static class EscuchaAddSection implements ActionUIListener
//	{
//		private int i=0;
//		
//		@Override
//		public void onClick(MyTestUIInterface t)
//		{
//			section = new ThmSection();
//			section.setTitle("Section "+Integer.toString(i++));
//			page.addSection(section);
//		}
//	}
//	
//	static class EscuchaAddThumbnail implements ActionUIListener
//	{
//		@Override
//		public void onClick(MyTestUIInterface t)
//		{
//			Thumbnail th = new Thumbnail();
//			th.setAlbum(Thumbnail.createDemoAlbum());
//			section.add(th);
//		}
//	}
//}
