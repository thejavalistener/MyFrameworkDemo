//package app.screen;
//
//import app.screen.decorator.ThmGUIDecoratorImple;
//import thejavalistener.fwk.awt.MyAwt;
//import thejavalistener.fwk.awt.testui.MyTestUI;
//
//public class ThmGUIExample
//{
//	public static void main(String[] args)
//	{
//		MyAwt.setWindowsLookAndFeel();
//		
//		ThmGUI gui = new ThmGUI();
//		gui.init();
//		
//		gui.getControls().addFilter("Artista");
//		gui.getControls().addFilter("Género");
//		gui.getControls().addFilter("Año");
//		gui.getControls().addFilter("Instrumento");
//		
//		gui.getControls().addLabel("Albert King");
//		gui.getControls().addLabel("Albin Lee");
//		gui.getControls().addLabel("B.B. King");
//		gui.getControls().addLabel("Bob Dylan");
//		gui.getControls().addLabel("Clarence \"Gatemoth\" Brown");
//		gui.getControls().addLabel("Credence Cleawater Revival");
//		gui.getControls().addLabel("Freddie Ging");
//		gui.getControls().addLabel("Steve Ray Vaughan");
//		gui.getControls().addLabel("The Beatles");
//		gui.getControls().addLabel("The Rolling Stones");
//
//		ThmSection section = gui.getPage().createSection("The Beatles");
//		
//		Thumbnail t = new Thumbnail();
//		t.setAlbum(Thumbnail.createDemoAlbum());
//		section.add(t);
//		
//		t = new Thumbnail();
//		t.setAlbum(Thumbnail.createDemoAlbum());
//		section.add(t);
//	
//		t = new Thumbnail();
//		t.setAlbum(Thumbnail.createDemoAlbum());
//		section.add(t);
//		
//		t = new Thumbnail();
//		t.setAlbum(Thumbnail.createDemoAlbum());
//		section.add(t);
//		
//		t = new Thumbnail();
//		t.setAlbum(Thumbnail.createDemoAlbum());
//		section.add(t);
//		
//		t = new Thumbnail();
//		t.setAlbum(Thumbnail.createDemoAlbum());
//		section.add(t);
//		
//		t = new Thumbnail();
//		t.setAlbum(Thumbnail.createDemoAlbum());
//		section.add(t);
//		
//		t = new Thumbnail();
//		t.setAlbum(Thumbnail.createDemoAlbum());
//		section.add(t);
//		
//		t = new Thumbnail();
//		t.setAlbum(Thumbnail.createDemoAlbum());
//		section.add(t);
//				
//
//		MyTestUI.test(gui.c()).maximize().run();
//	}
//	
//	static class EscuchaControls implements ThmControlsListener
//	{
//		@Override
//		public void filterSelected(ThmControls thmc,String filter)
//		{
//			thmc.setLabelTitle(filter);
//		}
//
//		@Override
//		public void labelSelected(ThmControls thmc,String filter, String label)
//		{
//			System.out.println("Filter: "+filter+", Label: "+label);
//		}
//	}
//}
