package app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import app.screen.JavaMusicLibraryScreen;
import thejavalistener.fwk.awt.MyAwt;
import thejavalistener.fwk.frontend.MyAppContainer;
import thejavalistener.fwk.frontend.hql.screen.HQLScreen;

public class Main 
{
	public static void main(String[] args)
	{
		// seteo el look and feel
		MyAwt.setWindowsLookAndFeel();
		
	    // levanto el contexto de spring		
	    ApplicationContext ctx=new ClassPathXmlApplicationContext("classpath:/spring.xml");
	    
	    // application container
		MyAppContainer appContainer = ctx.getBean(MyAppContainer.class);
		appContainer.createApp("Java Music Library",JavaMusicLibraryScreen.class);
		appContainer.createApp("Consola HQL",HQLScreen.class);	

		appContainer.init();
	}
}
