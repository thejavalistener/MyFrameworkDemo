package app.screen;

import javax.swing.JButton;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import thejavalistener.fwk.awt.textarea.MyTextField;
import thejavalistener.fwk.frontend.MyAbstractScreen;
import thejavalistener.fwk.properties.MyProperties;


@Component
public class PropertyDemoScreen extends MyAbstractScreen
{
	@Autowired
	private MyProperties p;

	private MyTextField tfProp;
	private MyTextField tfValue;
		
	@Override
	protected void createUI()
	{
		
		tfProp = new MyTextField(10);
		tfValue = new MyTextField(10);
		
		JButton b = new JButton("Add Property");
		b.addActionListener(l->p.put(getClass(),tfProp.getText(),tfValue.getText()));
		JButton b2 = new JButton("View properties");
		b2.addActionListener(l->System.out.println(p.getAll(getClass(),tfValue.getText())));

		add(tfProp.c());
		add(tfValue.c());
		add(b);
		add(b2);
	}
	
	@Override
	public String getName()
	{
		return "XX";
	}
}
