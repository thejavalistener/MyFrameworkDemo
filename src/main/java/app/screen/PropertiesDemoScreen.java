package app.screen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import app.Facade;
import app.mapping.Persona;
import thejavalistener.fwk.awt.MyAwt;
import thejavalistener.fwk.awt.MyException;
import thejavalistener.fwk.awt.MyFocusTraversalPolicy;
import thejavalistener.fwk.awt.form.MyForm;
import thejavalistener.fwk.awt.list.MyComboBox;
import thejavalistener.fwk.awt.list.MyJComboBox;
import thejavalistener.fwk.awt.searchbox.MySearchBox;
import thejavalistener.fwk.awt.searchbox.MySearchBoxController;
import thejavalistener.fwk.awt.textarea.MyTextField;
import thejavalistener.fwk.console.MyConsole;
import thejavalistener.fwk.console.Progress;
import thejavalistener.fwk.frontend.MyValidation;
import thejavalistener.fwk.frontend.ScreenFormConsoleTemplate;
import thejavalistener.fwk.properties.MyFrameworkProperty;
import thejavalistener.fwk.properties.MyProperties;
import thejavalistener.fwk.util.MyThread;
import thejavalistener.fwk.util.Pair;
import thejavalistener.fwk.util.UDate;
import thejavalistener.fwk.util.string.MyString;

@Component
public class PropertiesDemoScreen extends ScreenFormConsoleTemplate
{
	@Autowired
	private MyProperties properties;
	
	// validaciones
	private MyValidation validations;

	private MyForm form;

	private MyComboBox<Pair> cbProperties;
	private MyTextField tfPropName;
	private MyTextField tfPropValue;
	private JButton bGuardar;
	private JButton bEliminar;
			
	@Override
	protected void createUI()
	{
		form = getForm();

		// combo de personas (con todas las personas)
		cbProperties = new MyJComboBox<>(new EscuchaProperties());
		cbProperties.setTToString(p->p.getA().toString());
		cbProperties.setSpecialItem("-- No seleccionado --");
		form.addRow().add(cbProperties.c()).layout(1);
		
		tfPropName = new MyTextField();
		form.addRow().add("Prop. Name").add(tfPropName.c()).layout(.3,.7);
		
		tfPropValue = new MyTextField();
		form.addRow().add("Prop. Value").add(tfPropValue.c()).layout(.3,.7);

		form.addSeparator();

		// botones eliminar y guardar
		bEliminar = new JButton("Eliminar");
		bEliminar.addActionListener(new EscuchaEliminar());
		bGuardar = new JButton("Guardar");
		bGuardar.addActionListener(new EscuchaGuardar());
		form.addRow().add(bEliminar).add(bGuardar);

		form.makeForm();
		
		MyFocusTraversalPolicy fp=new MyFocusTraversalPolicy(this);
		fp.add(cbProperties.c());
		fp.add(tfPropName.c());
		fp.add(tfPropValue.c());
		fp.add(bEliminar);
		fp.add(bGuardar);
		
		validations = new MyValidation();
		validations.assertTrue("propName","Debe ingresar el nombre",()->!tfPropName.getText().isEmpty());
		validations.assertTrue("propValue","Debe ingresar un valor",()->!tfPropName.getText().isEmpty());
	}
	
	private void _clearForm()
	{
		cbProperties.selectSpecialItem();
		tfPropName.setText("");
		tfPropValue.setText("");
	}
		
	@Override
	public void onDataUpdated()
	{	
		List<Pair> props = properties.getAll(PropertiesDemoScreen.class);
		cbProperties.setItems(props);
		cbProperties.selectSpecialItem();
	}
	
	class EscuchaProperties implements ItemListener
	{
		@Override
		public void itemStateChanged(ItemEvent e)
		{
			if( !cbProperties.isSpecialItemSelected())
			{
				Pair p = cbProperties.getSelectedItem();
				tfPropName.setText(p.getA().toString());
				tfPropValue.setText(p.getB().toString());
			}
			else
			{
				_clearForm();
			}
		}
	}
	
	private class EscuchaGuardar implements ActionListener
	{
		@Transactional
		public void actionPerformed(ActionEvent e)
		{
			try
			{
				// Si alguna validación falla => Exception
				validations.assertAll();

				Pair pair = cbProperties.getSelectedItem();
				String propName = pair!=null?pair.getA().toString():tfPropName.getText();
				String propValue = tfPropValue.getText();
				
				String action = pair!=null?"actualizará":"creará";
				String s = console.print("Se "+action+" el valor de: "+tfPropName.getText()+". Confirma? (SI/NO)").input().oneOfln("SI","NO");
				if( s.equals("SI") )
				{
					properties.putString(PropertiesDemoScreen.class,propName,propValue);
					String action2 = pair!=null?"actualizada":"creada";
					console.println("La propiedad "+propName+" ha sido "+action2+".");
					
					// actualiza los combos y listas
					dataUpdated();
				}
				else
				{
					console.println("No se registraron cambios.");
				}

				_clearForm();
			}
			catch(MyException ex)
			{
				showException(ex);
			}			
		}
	}
	
	class EscuchaEliminar implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			Pair pair = cbProperties.getSelectedItem();
			if( pair!=null )
			{
				String propName = pair.getStringA();
				
				String s = console.print("Se eliminará la propiedad "+pair.getStringA()+". Confirma? (SI/NO)").input().oneOfln("SI","NO");
				if( s.equals("SI") )
				{
					properties.remove(PropertiesDemoScreen.class,propName);
					console.println("La propiedad "+propName+" ha sido eliminada.");
				}
				else
				{
					console.println("No se registraron cambios.");
				}
			}
			
			// actualiza los combos y listas
			dataUpdated();
			
			_clearForm();	
		}
	}
	
	@Override
	public String getName()
	{
		return "ABM Personas";
	}	
}
