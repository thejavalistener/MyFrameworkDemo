//package app.screen;
//
//import app.screen.decorator.ThmPageDecoratorImple;
//import thejavalistener.fwk.awt.MyAwt;
//import thejavalistener.fwk.awt.testui.ActionUIListener;
//import thejavalistener.fwk.awt.testui.MyTestUI;
//import thejavalistener.fwk.awt.testui.MyTestUIInterface;
//
//public class ThumSecionExample
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
//		section = new ThmSection();
//		section.setTitle("Sección de demo");
//		page.addSection(section);
//		
//		MyTestUI mtu = MyTestUI.test(page.c());
//		
//		mtu.addButton("Add Thumbnail",new EscuchaAddThumbnail());
//		
//		mtu.maximize().run();
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
