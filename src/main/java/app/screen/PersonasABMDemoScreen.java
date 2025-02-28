package app.screen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.JButton;

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
import thejavalistener.fwk.frontend.MyValidation;
import thejavalistener.fwk.frontend.ScreenTemplate;
import thejavalistener.fwk.util.UDate;
import thejavalistener.fwk.util.string.MyString;

@Component
public class PersonasABMDemoScreen extends ScreenTemplate
{
	@Autowired
	private Facade facade;
	
	// validaciones
	private MyValidation validations;

	private MyForm form;

	private MyComboBox<Persona> cbPersonas;
	private MyTextField tfNombre;
	private MyTextField tfFechaNacimiento;
	private JButton bGuardar;
	private JButton bEliminar;
			
	@Override
	protected void createUI()
	{
		form = getForm();

		// combo de personas (con todas las personas)
		cbPersonas = new MyJComboBox<>(new EscuchaPersonas());
		cbPersonas.setTToString(p->p.getNombre());
		cbPersonas.setSpecialItem("-- No seleccionado --");
		form.addRow().add(cbPersonas.c()).layout(1);
		
		// textField nombre y fechaNacimiento
		tfNombre = new MyTextField();
		form.addRow().add("Nombre").add(tfNombre.c()).layout(.3,.7);
		
		tfFechaNacimiento = new MyTextField();
		form.addRow().add("Fec. Nac.").add(tfFechaNacimiento.c()).layout(.3,.7);

		form.addSeparator();

		// botones eliminar y guardar
		bEliminar = new JButton("Eliminar");
		bEliminar.addActionListener(new EscuchaEliminar());
		bGuardar = new JButton("Guardar");
		bGuardar.addActionListener(new EscuchaGuardar());
		form.addRow().add(bEliminar).add(bGuardar);
		
		form.makeForm();
		
		MyFocusTraversalPolicy fp=new MyFocusTraversalPolicy(this);
		fp.add(cbPersonas.c());
		fp.add(tfNombre.c());
		fp.add(tfFechaNacimiento.c());
		fp.add(bEliminar);
		fp.add(bGuardar);
		
		validations = new MyValidation();
		validations.assertTrue("nombre","Debe ingresar el nombre",()->!tfNombre.getText().isEmpty());
		validations.assertTrue("fechaNacimiento","La fecha debe tener este formato (dd-mm-aaaa)",()->MyString.matches(tfFechaNacimiento.getText(),MyString.DATE_DDMMYYYY_REGEX));
	}
	
	private void _clearForm()
	{
		cbPersonas.selectSpecialItem();
		tfNombre.setText("");
		tfFechaNacimiento.setText("");
	}
		
	@Override
	public void onDataUpdated()
	{
		List<Persona> cursos=facade.obtenerPersonas();
		cbPersonas.setItems(cursos);
		cbPersonas.selectSpecialItem();
	}
	
	class EscuchaPersonas implements ItemListener
	{
		@Override
		public void itemStateChanged(ItemEvent e)
		{
			if( !cbPersonas.isSpecialItemSelected())
			{
				Persona p = cbPersonas.getSelectedItem();
				tfNombre.setText(p.getNombre());
				tfFechaNacimiento.setText(new UDate(p.getFechaNacimiento()).asDDMMYYYY('-'));
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

				Persona p = new Persona();
				p.setNombre(tfNombre.getText());
				p.setFechaNacimiento(new UDate(tfFechaNacimiento.getText()).toSqlDate());
				if( !cbPersonas.isSpecialItemSelected() )
				{
					p.setIdPersona(cbPersonas.getSelectedItem().getIdPersona());
				}
				
				facade.altaOModificacion(p);

				// actualiza los combos y listas
				dataUpdated();
				
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
			if( !cbPersonas.isSpecialItemSelected() )
			{
				Persona p = cbPersonas.getSelectedItem();
				facade.eliminar(p);
				
				// actualiza los combos y listas
				dataUpdated();
				
				_clearForm();
			}
		}
	}
		
	@Override
	public String getName()
	{
		return "ABM Personas";
	}
}
