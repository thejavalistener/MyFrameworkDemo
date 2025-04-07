package app.screen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.screen.decorator.ThmControlsDecorator;
import app.screen.decorator.ThmGUIDacorator;
import app.screen.decorator.ThmGUIDecoratorImple;
import app.screen.decorator.ThmPageDecorator;
import thejavalistener.fwk.awt.panel.MyRandomColorPanel;
import thejavalistener.fwk.frontend.MyAbstractScreen;

@Component
public class JavaMusicLibraryScreen extends MyAbstractScreen
{
	@Autowired
	private ThmGUI gui;
	
	@Override
	protected void createUI()
	{
		setLayout(new BorderLayout());	
		add(gui.c(),BorderLayout.CENTER);
	}
	
	@Override
	public void init()
	{
		getMyApp().getMyAppContainer().showApps(false);
		getMyApp().showScreens(false);

		
		gui.init();
		
		// home
		gui.getControls().addHome("Últimas incorporaciones");
		gui.getControls().addHome("Más escuchados");
		gui.getControls().addHome("Historial de reproducción");
		gui.getControls().addHome("Playlists");

		// labels
		gui.getControls().addFilter("Artistas");
		gui.getControls().addFilter("Géneros");
		gui.getControls().addFilter("Instrumentos");
		gui.getControls().addFilter("Años de grabación");

		gui.getControls().addLabel("Harp");
		gui.getControls().addLabel("Piano");
		gui.getControls().addLabel("Guitar");
		gui.getControls().addLabel("Saxophone");
		gui.getControls().addLabel("Trumpet");
		gui.getControls().addLabel("Vibraphone");
		
		
		ThmSection section = gui.getPage().createSection("The Beatles");
		
		section.addAlbum(Thumbnail.createDemoAlbum());
		section.addAlbum(Thumbnail.createDemoAlbum());
		section.addAlbum(Thumbnail.createDemoAlbum());
		section.addAlbum(Thumbnail.createDemoAlbum());
		section.addAlbum(Thumbnail.createDemoAlbum());
		section.addAlbum(Thumbnail.createDemoAlbum());
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
		return "Java Music Library";
	}
}
