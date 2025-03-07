package app.screen;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JScrollPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import app.Facade;
import app.mapping.Persona;
import thejavalistener.fwk.awt.MyException;
import thejavalistener.fwk.awt.MyFocusTraversalPolicy;
import thejavalistener.fwk.awt.form.MyForm;
import thejavalistener.fwk.awt.list.MyComboBox;
import thejavalistener.fwk.awt.list.MyJComboBox;
import thejavalistener.fwk.awt.textarea.MyTextField;
import thejavalistener.fwk.console.MyConsole;
import thejavalistener.fwk.console.Progress;
import thejavalistener.fwk.frontend.MyAbstractScreen;
import thejavalistener.fwk.frontend.MyValidation;
import thejavalistener.fwk.frontend.ScreenConsoleTemplate;
import thejavalistener.fwk.util.MyRegex;
import thejavalistener.fwk.util.MyThread;
import thejavalistener.fwk.util.UDate;
import thejavalistener.fwk.util.string.MyString;

@Component
public class ConsoleDemoScreen extends MyAbstractScreen
{
	private MyConsole c;
	
	@Override
	protected void createUI()
	{
		c = new MyConsole(this);
		
		JScrollPane scrollPane = new JScrollPane(c.getTextPane().c());
		setLayout(new BorderLayout());
		add(scrollPane,BorderLayout.CENTER);
	}
			
	@Override
	public void onDataUpdated()
	{
	}
	
	@Override
	public void start()
	{
		allowAppSwitch(false);

		c.cls();
		c.banner("You are Welcome!");
		c.println("Plesae, press any key continue...").pressAnyKey();

		String name = c.print("What's your name? ").readlnString();
		c.println("Hi, "+name);

		String pwd = c.print("Enter your password:").readlnPassword();
		c.println("Your password is: \""+pwd+"\"    [fg(ORANGE)]:o)[x]");	
		
		int age = c.print("How years old are you ?").readlnInteger();
		c.println("You are "+age+" years old...");
		
		String email = c.print("What's your email? ").readlnString(MyRegex.EMAIL);
		c.println("Your email is: "+email);
		
		String ops[] = {"Mar del Plata","Pinamar","Necochea","Mar de Ajo","Miramar"};
		int op = c.print("Where you goin on vacatio? ").menuln(ops);
		c.println("Qué capo! Your hollidays will be in: "+ops[op]);
		
		String x = c.print("Happy (YES/NO)? ").input().oneOfln("YES","NO");
		c.println("Veo que "+x+" estás contento...");
				

		String fName = c.println("Select a file: ").fileExplorer();
		c.println("Your file is: "+fName);
		
		c.println("Press any key to start a process...").pressAnyKey();
		
		c.print("Processing: ");
		Progress p = c.progressBar(20,100);
		p.execute(()->{
			for(int i=0; i<100; i++)
			{
				MyThread.randomSleep(230);
				p.increase(MyString.generateRandom());
			}
		}).ln();
		
		c.println("Process time: "+p.elapsedTime()/1000+" secs.");
		
		
		c.print("Other processing, using meter: ");
		Progress q = c.progressMeter(100);
		q.execute(()->{
			for(int i=0; i<100; i++)
			{
				MyThread.randomSleep(150);
				q.increase();
			}
		}).ln();
		
		c.println("Process time: "+q.elapsedTime()/1000+" secs.");
		
		
		
		c.println("Press 'X' key to finish this Console Demo").pressAnyKey('X');
		
		c.println("Now you can choce any other demo app!");
		allowAppSwitch(true);
	}
		
	@Override
	public String getName()
	{
		return "Demo Consola";
	}
}
