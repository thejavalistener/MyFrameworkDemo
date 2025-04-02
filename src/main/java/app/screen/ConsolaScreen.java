package app.screen;

import org.springframework.stereotype.Component;

import thejavalistener.fwk.console.Progress;
import thejavalistener.fwk.frontend.ScreenConsoleTemplate;
import thejavalistener.fwk.util.MyColor;
import thejavalistener.fwk.util.MyThread;
import thejavalistener.fwk.util.string.MyRegex;
import thejavalistener.fwk.util.string.MyString;

@Component
public class ConsolaScreen extends ScreenConsoleTemplate
{
	@Override
	protected void createUI()
	{
	}
			
	@Override
	public void onDataUpdated()
	{
	}
	
	@Override
	public void start()
	{
		allowAppSwitch(false);

		console.cls();
		console.banner("You are Welcome!");
		console.println("Plesae, press any key continue...").pressAnyKey();

		String name = console.print("What's your name? ").readlnString();
		console.println("Hi, "+name);

		String defval = console.print("Where you live? ").input().defval("Argentina").valid(s->!s.isEmpty()).readln();
		console.println("You live in "+defval);

		String pwd = console.print("Enter your password:").readlnPassword();
		console.println("Your password is: \""+pwd+"\"    [fg(ORANGE)]:o)[x]");	
		
		int age = console.print("How years old are you ?").readlnInteger();
		console.println("You are "+age+" years old...");
		
		String email = console.print("What's your email? ").readlnString(MyRegex.EMAIL);
		console.println("Your email is: "+email);
		
		String ops[] = {"Mar del Plata","Pinamar","Necochea","Mar de Ajo","Miramar"};
		int op = console.print("Where you goin on vacatio? ").menuln(ops);
		console.println("Qué capo! Your hollidays will be in: "+ops[op]);
		
		String x = console.print("Happy (YES/NO)? ").input().oneOfln("YES","NO");
		console.println("Veo que "+x+" estás contento...");
				
//		String fName = console.println("Select a file: ").fileExplorer();
//		console.println("Your file is: "+fName);
		
		console.println("Press any key to start a process...").pressAnyKey();
		
		console.print("Processing: ");
		Progress p = console.progressBar(20,100);
		p.execute(()->{
			for(int i=0; i<100; i++)
			{
				MyThread.randomSleep(230);
				String mssg = MyString.generateRandom()+" [fg("+MyColor.randomHexColorString()+")]"+MyString.generateRandom()+"[x]"; 
				p.increase(mssg);
			}
		}).ln();
		
		console.println("Process time: "+p.elapsedTime()/1000+" secs.");
		
		
		console.print("Other processing, using meter: ");
		Progress q = console.progressMeter(100);
		q.execute(()->{
			for(int i=0; i<100; i++)
			{
				MyThread.randomSleep(150);
				q.increase(MyString.generateRandom());
			}
		}).ln();
		
		console.println("Process time: "+q.elapsedTime()/1000+" secs.");
		
		console.println("Press 'X' key to finish this Console Demo").pressAnyKey('X');
		
		console.println("Now you can choce any other demo app!");
		allowAppSwitch(true);
	}
		
	@Override
	public String getName()
	{
		return "Demo Consola";
	}
}
