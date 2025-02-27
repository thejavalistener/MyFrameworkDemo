package app.screen;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import app.Facade;
import app.mapping.Persona;
import thejavalistener.fwk.awt.MyAwt;
import thejavalistener.fwk.awt.MyException;
import thejavalistener.fwk.awt.MyFocusTraversalPolicy;
import thejavalistener.fwk.awt.dialog.MyInstantForm;
import thejavalistener.fwk.awt.form.MyForm;
import thejavalistener.fwk.awt.link.MyLink;
import thejavalistener.fwk.awt.link.MyLinkButton;
import thejavalistener.fwk.awt.list.MyComboBox;
import thejavalistener.fwk.awt.list.MyJComboBox;
import thejavalistener.fwk.awt.progres.MyProgressListener;
import thejavalistener.fwk.awt.progres.MyProgressPane;
import thejavalistener.fwk.awt.searchbox.MySearchBox;
import thejavalistener.fwk.awt.textarea.MyTextField;
import thejavalistener.fwk.frontend.MyValidation;
import thejavalistener.fwk.frontend.ScreenTemplate;
import thejavalistener.fwk.frontend.texttable3.FromObjectArrayList;
import thejavalistener.fwk.frontend.texttable3.MySingleTable;
import thejavalistener.fwk.util.MyThread;
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
		
	private EscuchaPersona escuchaPersonas = null;
	private EscuchaGuardar escuchaGuardar = null;
	private EscuchaEliminar escuchaEliminar = null;
	
	@Override
	protected void createUI()
	{
		form = getForm();
		double wL = .3;
		double wC = .7;

		// combo de personas (con todas las personas)
		cbPersonas = new MyJComboBox<>(escuchaPersonas=new EscuchaPersona());
		cbPersonas.setTToString(p->p.getNombre());
		cbPersonas.setSpecialItem("-- Todos --");
		form.addRow().add(cbPersonas.c()).layout(1);
		
		// textField nombre y fechaNacimiento
		tfNombre = new MyTextField();
		tfNombre.addValidation(s->!s.isEmpty(),"Debe completar el nombre","Error");
		form.addRow().add("Nombre").add(tfNombre.c()).layout(.3,.7);
		
//		cbAlumno = new MyJComboBox<>(escuchaAlumno=new EscuchaAlumno());
//		cbAlumno.setTToString(al->al.getNombre());
//		MyLink lnkAlumno = new MyLink("Alumno",MyLink.LINK);
//		lnkAlumno.setActionListener(new EscuchaLinkAlumno());
//		form.addRow().add(lnkAlumno.c()).add(cbAlumno.c()).layout(wL,wC);
		
		cbInstancia = new MyJComboBox<>(escuchaInstancia=new EscuchaInstancia());
		cbInstancia.setTToString(i->i.getDescripcion());
		MyLink lnkInstancia = new MyLink("Instancia");
		form.addRow().add(lnkInstancia.c()).add(cbInstancia.c()).layout(wL,wC);

		form.addSeparator();
		
		cbTema = new MyJComboBox<>();
		cbTema.setItemListener(l->form.setVisibleRow("califManual",cbTema.getSelectedItem()!=null && cbTema.getSelectedItem().getFlgManual()==1));
		cbTema.setTToString(t->Integer.toString(t.getNroTema()));
		MyLink lnkTema = new MyLink("Tema");
		form.addRow().add(lnkTema.c()).add(cbTema.c()).layout(wL,wC);
		
		tfManual = new MyTextField();
		tfManual.addMask(MyTextField.MASK_INTEGER);
		form.addRow("califManual").add(new MyLink("C. Manual").c()).add(tfManual.c()).layout(wL,wC);		
		
		tfRespuestas = new MyTextField();
		tfRespuestas.addMask(MyTextField.MASK_UPPERCASE);
		tfRespuestas.addMask(MyTextField.MASK_AÑZ);
		bProcesar = new JButton("Procesar");
		form.addRow().add(new MyLink("Respuestas").c()).add(tfRespuestas.c()).add(bProcesar).layout(wL,wC*.5,wC*.5);		
		bEditar = new JButton("Editar");
		form.addRowL().add(bEditar);
		
		form.addSeparator();		
		
		// botones: ACEPTAR/CANCELAR
		bCancelar = new JButton("Cancelar");
		bGuardar = new JButton("Guardar");
		form.addRowR().add(bCancelar).add(bGuardar);
		
		// link: RECALCULAR NOTAS
		lnkAnularExamen = new MyLinkButton("Anular examen",Color.RED);
		lnkAnularExamen.setActionListener(new EscuchaAnularExamen());
		form.addRowR().add(lnkAnularExamen.c());

		
		MyLink lnkRecalcular = new MyLinkButton("Recalcular notas",Color.RED);
		lnkRecalcular.setActionListener(new EscuchaRecalcular());
		form.addRowR().add(lnkRecalcular.c());
		
		// link: Cerrar Cursada
		MyLinkButton lnkCerrarCursada = new MyLinkButton("Cerrar cursada",Color.BLUE);
		lnkCerrarCursada.setActionListener(new EscuchaCierreCursada());
		form.addRowL().add(lnkCerrarCursada.c());
		
		MyLink lnkBuscarCopiones = new MyLinkButton("Buscar copias");
		lnkBuscarCopiones.setActionListener(l->getMyApp().pushScreen(ScreenBuscarCopiones.class));
		form.addRowL().add(lnkBuscarCopiones.c());

		MyLink lnkAlumnosXAfirmacion = new MyLinkButton("Afirmaciones mal respondidas");
		lnkAlumnosXAfirmacion.setActionListener(l->getMyApp().pushScreen(ScreenEstadisticaXAfirmacion.class));
		form.addRowL().add(lnkAlumnosXAfirmacion.c());

		form.addSeparator();		

		MyLink lnkNuevoSeguimiento = new MyLinkButton("Seguimiento estudiante");
		lnkNuevoSeguimiento.setActionListener(new EscuchaNuevoSeguimiento());
		form.addRowL().add(lnkNuevoSeguimiento.c());
		
		lnkNotificaciones = new MyLinkButton("Notificar calificaciones y seguimientos ");
		form.addRowL().add(lnkNotificaciones.c());

		// link: ENVIAR CORREO PERSONALIZADO
		lnkEmailPers = new MyLinkButton("Enviar correo personalizado");
		lnkEmailPers.setActionListener(l->getMyApp().pushScreen(ScreenEmails.class));

		form.addRowL().add(lnkEmailPers.c());

		form.addSeparator();
		
		form.makeForm();
		
		bEditar.addActionListener(new EscuchaEditar());
		bProcesar.addActionListener(new EscuchaProcesar());
		bCancelar.addActionListener(new EscuchaCancelar());
		bGuardar.addActionListener(new EscuchaGuardar());
		
		MyFocusTraversalPolicy fp=new MyFocusTraversalPolicy(this);
		fp.add(cbAlumno.c());
		fp.add(cbInstancia.c());
		fp.add(cbTema.c());
		fp.add(tfManual.c());
		fp.add(tfRespuestas.c());
		fp.add(bProcesar);
		fp.add(bEditar);
		fp.add(bGuardar);
		_estadoComponentes(1);	
		
		validations = new MyValidation();
		
		validations.assertTrue("alumno","Debe seleccionar un alumno",()->cbAlumno.getSelectedItem()!=null);
		validations.assertTrue("tema","Debe seleccionar un tema",()->cbTema.getSelectedItem()!=null);
		validations.assertTrue("instancia","Debe seleccionar una instancia",()->cbInstancia.getSelectedItem()!=null);
		validations.assertTrue("respuesta","Debe ingresar una respuesta",()->!tfRespuestas.getText().isEmpty());
		
		lnkNotificaciones.setActionListener(new EscuchaEnviarSituacionAcademica());
		
	}
	
	
	@Override
	public void onDataUpdated()
	{
		// completo el combo de cursos con todos los cursos
		List<String> cursos=facade.cursosObtener();
		cbCurso.setItems(cursos);
		cbCurso.selectSpecialItem();

		// todos los alumnos
		List<Alumno> alumnos=facade.alumnosObtener();
		cbAlumno.setItems(alumnos);
		cbAlumno.setUnselected();
		
		// completo el combo de instancias y selecciono la instancia activa
		List<Instancia> instancias=facade.instanciasObtener();
		cbInstancia.setItems(instancias);
		Instancia instanciaActiva=facade.instanciaObtenerActiva();
		cbInstancia.setSelectedItem(i->i.getIdInstancia()==instanciaActiva.getIdInstancia());

		// combo de temas con todos los temas
		List<Tema> temas=facade.temasObtener(instanciaActiva);
		cbTema.setItems(temas);
		cbTema.setUnselected();		
	}
	
	private void _limpiar()
	{
		getConsole().clear();
		tfRespuestas.setText("");
		tfManual.setText("");
		cbAlumno.setUnselected();
		cbAlumno.requestFocus();
		_estadoComponentes(1);
		procesoEnMarcha = false;
		lnkAnularExamen.setClickeable(procesoEnMarcha);
	}

	private class EscuchaCancelar implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if( procesoEnMarcha )
			{
				String mssg = "No sea han guardado los cambios.\n¿Desea cancelar de todos modos?";
				String title = "No se guardaron los cambios";
				if( MyAwt.showConfirmYES_NO(mssg,title,getOuter())==0)
				{
					_limpiar();	
				}
			}
			else
			{
				_limpiar();
			}	
		}
	}

	@Autowired
	private AlumnoInfo ai;

	@Autowired 
	EvaluacionInfo ei;
	
	private class EscuchaProcesar implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			try
			{
				getConsole().clear();

				// valido los textField y el tema
				cbTema.validateNotUnselected("Debe seleccionar un tema","Tema");
				tfRespuestas.runValidation(MyTextField.VALID_NOTEMPTY,"Falta ingresar las respuestas","Respuestas");

				Tema tema=cbTema.getSelectedItem();
				String rtas=tfRespuestas.getText();

				// valido que los datos sean consistentes
				facade.respuestasValidar(tema,rtas);

				// proceso
				Integer califManual = tfManual.getText().length()>0?Integer.parseInt(tfManual.getText()):null;
				eval = facade.respuestasEvaluar(tema,rtas,califManual);

				Alumno a = cbAlumno.getSelectedItem();
				String txt = "";
				
				if( a!=null )
				{
					txt+=ai.getDatosPersonales2(a)+"\n\n";
				}	
				
				txt+=ei.getSumario(eval)+"\n\n";				
				txt+=ei.getModulos(eval)+"\n\n";
				
				if( a!=null )
				{
					txt+="Exámenes anteriores:\n";
					Instancia i = cbInstancia.getSelectedItem();
					txt+=ai.getExamenes(a,r->r.getInstancia().getIdInstancia()!=i.getIdInstancia())+"\n\n";

					txt+="Seguimientos:\n";					
					txt+=ai.getSeguimientos(a)+"\n\n";
				}				
					
				getConsole().appendFormatedText(txt);
				
				procesoEnMarcha = true;
				lnkAnularExamen.setClickeable(procesoEnMarcha);

				getConsole().setCaretPosition(0);
				
				_estadoComponentes(2);
				bGuardar.requestFocus();
			}
			catch(MyException ex)
			{
				showException(ex);
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
				validations.assertAll();			
				
				UDate hoy=new UDate();

				Respuesta rta=new Respuesta();
				rta.setAlumno(cbAlumno.getSelectedItem());
				rta.setInstancia(cbInstancia.getSelectedItem());
				
				Tema t = cbTema.getSelectedItem();
				rta.setTema(t);
				rta.setRespuestasB3(tfRespuestas.getText());
//				rta.setFecha(hoy.toSqlTimestamp());
//				rta.setUltActualizacion(hoy.toSqlTimestamp());
				rta.setCalificacion(eval.getCalificacion());
				rta.setFechaNotif(null);
				
				String sCMan = tfManual.getText().trim();
				if( t.getFlgManual()==1 && sCMan.length()>0  )
				{
					rta.setCalificacionManual(Integer.parseInt(sCMan));
				}
			
				facade.respuestaRegistrar(rta);

				_limpiar();								
			}
			catch(MyException ex)
			{
				showException(ex);
			}			
		}
	}

	private void _estadoComponentes(int estado)
	{		
		// califManual
		Tema tema = cbTema.getSelectedItem();
		boolean mostrarCalifManual = tema!=null && tema.getFlgManual()==1;
		form.setVisibleRow("califManual",mostrarCalifManual);
		
		switch(estado)
		{
			case 1: // ingresando
				cbCurso.setEnabled(true);
				cbAlumno.setEnabled(true);
				cbInstancia.setEnabled(true);
				cbTema.setEnabled(true);
				tfRespuestas.setEnabled(true);
				tfManual.setEnabled(true);
				bCancelar.setEnabled(true);
				bProcesar.setEnabled(true);
				bEditar.setEnabled(false);
				bGuardar.setEnabled(false);
				getConsole().setText("");
				break;
			case 2: // procesado
				cbCurso.setEnabled(false);
				cbAlumno.setEnabled(false);
				cbInstancia.setEnabled(false);
				cbTema.setEnabled(false);
				tfRespuestas.setEnabled(false);
				tfManual.setEnabled(false);
//				form.setVisibleRow("califManual",true);
				
				bCancelar.setEnabled(true);
				bProcesar.setEnabled(false);
				bEditar.setEnabled(true);
				bGuardar.setEnabled(true);
				break;
			case 3: // todo blockeado
				cbCurso.setEnabled(false);
				cbAlumno.setEnabled(false);
				cbInstancia.setEnabled(false);
				cbTema.setEnabled(false);
				tfRespuestas.setEnabled(false);
				tfManual.setEnabled(false);
//				form.setVisibleRow("califManual",false);

				bCancelar.setEnabled(false);
				bProcesar.setEnabled(false);
				bEditar.setEnabled(false);
				bGuardar.setEnabled(false);
				break;

		}
	}

	private class EscuchaEditar implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			_estadoComponentes(1);
			bProcesar.requestFocus();
		}
	}

	// -- Cambio en el ComboBox de Curso --
	private class EscuchaCurso implements ItemListener
	{
		public void itemStateChanged(ItemEvent e)
		{
			String c=cbCurso.getSelectedItem();

			List<Alumno> lst;
			if( cbCurso.isSpecialItemSelected() )
			{
				lst=facade.alumnosObtener();
			}
			else
			{
				lst=facade.alumnosObtener(c);
			}

			cbAlumno.setItems(lst);
			cbAlumno.setUnselected();
		}
	}
	
	class EscuchaAlumno implements ItemListener
	{
		public void itemStateChanged(ItemEvent e)
		{
			Alumno a = cbAlumno.getSelectedItem();
			Instancia i = cbInstancia.getSelectedItem();
			
			// busco la resuesta del alumno para esta instancia
			Respuesta r = facade.respuestaObtener(a,i);
			
			if( r!=null )
			{
				tfRespuestas.setText(r.getRespuestasB3());
				cbTema.setSelectedItem(t->t.getNroTema().equals(r.getTema().getNroTema()));
				form.setVisibleRow("califManual",r.getTema().getFlgManual()==1);
				if( r.getTema().getFlgManual()==1 )
				{
					String califmanual = MyString.ifNull(r.getCalificacionManual(),"");
					tfManual.setText(califmanual);
				}
			}
			else
			{
				tfRespuestas.setText("");
				tfManual.setText("");
			}			
		}
	}

	// -- Cambio en el ComboBox de Curso --
	class EscuchaInstancia implements ItemListener
	{
		public void itemStateChanged(ItemEvent e)
		{
			Alumno a = cbAlumno.getSelectedItem();
			Instancia i = cbInstancia.getSelectedItem();

			List<Tema> temas = facade.temasObtener(i);
			cbTema.setItems(temas);
			
			if(a!=null)
			{
				Respuesta rta=facade.respuestaObtener(a,i);
				if(rta!=null)
				{
					tfRespuestas.setText(rta.getRespuestasB3());
					cbTema.setSelectedItem(x->x.getNroTema()==rta.getTema().getNroTema());
					
					if( rta.getTema().getFlgManual()==1 )
					{
						String califmanual = MyString.ifNull(rta.getCalificacionManual(),"");
						tfManual.setText(califmanual);
					}

				}
				else
				{
					tfRespuestas.setText("");
					tfManual.setText("");
				}
			}
		}
	}
	
	@Autowired
	private AlumnoSearchBoxController controller;
	
	class EscuchaLinkAlumno implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			MySearchBox<Alumno> msb = createSearchBox(Alumno.class,controller);
			
			msb.getTable().headers("Curso","Nombre","Legajo").layout(80,200,80);
			msb.getTable().setBeanToObjectArrayPolicy(a->new Object[]{a.getCurso(),a.getNombre(),a.getLegajo()});
			Alumno aSelected = msb.show();
			if( aSelected!=null )
			{
				cbCurso.setSelectedItem(s->s.equals(aSelected.getCurso()));
				cbCurso.forceItemEvent();
				cbAlumno.setSelectedItem(a->a.getLegajo().equals(aSelected.getLegajo()));
				cbAlumno.forceItemEvent();
			}
		}
	}
	
	class EscuchaRecalcular implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			// muestro un compo con los temas
			MyComboBox<Tema> tmpTemas=new MyJComboBox<>();
			tmpTemas.setTToString(t->"Tema "+t.getNroTema());
			tmpTemas.setItems(cbTema.getItems());

			Object[] message= {"Tema a recalcular:",tmpTemas.c()};
			int option=JOptionPane.showConfirmDialog(null,message,"Elige una opción",JOptionPane.OK_CANCEL_OPTION);

			
			// si confirma, invoco a respuestasRecalcular y muestro los resultados
			
			if( option==JOptionPane.OK_OPTION )
			{
				Tema tema = tmpTemas.getSelectedItem();
				
				// invoco al proceso del facade
				List<Object[]> nuevosRtdos = facade.respuestasRecalcularCalificacion(tema);
				
				if( !nuevosRtdos.isEmpty() )
				{
					MySingleTable tt = new MySingleTable();
					String[] headers = {"Curso","Legajo","Nombre","c/Anterior","c/Actual"};
					tt.headers(headers);
					tt.layout(7,10,30,10,10);
					tt.loadData(new FromObjectArrayList(nuevosRtdos));
					tt.makeTable();
					getConsole().setText(tt.drawTable());
				}
				else
				{
					getConsole().setText("No se registraron cambios");
				}
			}
		}		
	}
	
	@Autowired
	private ApplicationContext ctx;
	
	// -- CLICK EN ENVIAR MAILS --
	class EscuchaEnviarSituacionAcademica implements ActionListener,MyProgressListener 
	{
		MyProgressPane mp = null; 
		
		public void actionPerformed(ActionEvent e) 
		{
			int nCalif = facade.respuestaObtenerNoNotificadas().size();
			int nSegui = facade.seguimientoObtenerNoNotificados().size();
			
			if( nCalif==0 && nSegui==0 )
			{
				showInformationMessage("No hay calificaciones ni seguimientos para enviar","Todo al día");
				return;
			}

			String msg = _prepararMensaje(nCalif,nSegui);
			if( showConfirmQuestionMessage(msg,"Confirmación") )
			{
				mp = new MyProgressPane("Notificando situación académica","Espere...",getMyApp().getMyAppContainer().c());
				mp.setProgressListener(this);
				mp.show();

				CalificacionesYSeguimientosDatasource dataSource = ctx.getBean(CalificacionesYSeguimientosDatasource.class);
				CalificacionesYSeguimientosController controller = ctx.getBean(CalificacionesYSeguimientosController.class);
				controller.setProgressPane(mp);
				
				MyThread.start(()->sendEmails(dataSource,controller));
			}
			
			
		}
		
		private String _prepararMensaje(int c,int s)
		{
			boolean hayCalif = false;
			String msg = "¿Desea enviar";
			if( c==1 )
			{
				msg+=" 1 calificación";
				hayCalif=true;
			}
			else
			{
				if( c>1 )
				{
					msg+=" "+c+" calificaciones";
					hayCalif=true;
				}
			}

			if( s==1 )
			{
				msg+=hayCalif?" y":"";
				msg+=" 1 seguimiento";
			}
			else
			{
				if( s>1 )
				{
					msg+=hayCalif?" y":"";
					msg+=" "+s+" seguimientos";
				}
			}
			
			return msg+"?";
		}

		@Override
		public void progressTerminated(boolean interrupted, int currentValue)
		{
			if( interrupted )
			{
				showWarningMessage("El proceso ha sido interrumpido","Proceso interrumpido");
			}
		}		
	}
	
	class EscuchaNuevoSeguimiento implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			Alumno a = cbAlumno.getSelectedItem();
			
			if( a!=null )
			{
				getMyApp().pushScreen(ScreenMarcasPositivas.class,a);
			}
			else
			{
				getMyApp().pushScreen(ScreenMarcasPositivas.class);				
			}
		}
	}
	
	class EscuchaAnularExamen implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			Alumno a = cbAlumno.getSelectedItem();
			Instancia i = cbInstancia.getSelectedItem();
			
			Respuesta r = facade.respuestaObtener(a,i);
			if( r==null )
			{
				showInformationMessage("Aún no se registró ninguna respuesta de\n"+a.getNombre()+" para el "+i.getDescripcion(),"Sin cambios");					
				return;
			}
			
			String motivo = MyAwt.inputText("Motivo:","",getOuter());
			
			if( motivo==null )
			{
				return;
			}
			
			String mssg = "Está a punto de anular la calificación del ";
			mssg+=i.getDescripcion()+" de "+a.getNombre()+"\n";
			mssg+=", por la siguiente razón: "+motivo+"\n";
			mssg+="¿Confirma la acción?";
			
			if( showConfirmWarningMessage(mssg,"ATENCIÓN") )
			{
				SimpleMailMessage smm = new SimpleMailMessage();
				smm.setFrom(null);
				smm.setTo(a.getEmail());
				smm.setSubject("ANULACIÓN DE EXAMEN");
				
				// envio mail
				String body = "";
				body+="Estimado "+a.getNombre()+": Te informo que tu "+i.getDescripcion()+" ";
				body+="ha sido anulado por el siguiente motivo: ["+motivo.toUpperCase()+"]\n";
				body+="Te pido disculpas por las molestias ocasionadas.\n\n";
				body+="Cordialmente, \n";
				body+="Prof. Ing. Pablo A. Sznajdleder.\n";
				smm.setText(body);
				sendEmail(smm);
				
				// elimino
				facade.respuestaAnular(a,i);
				showInformationMessage("El respuesta fue anulada","Sin cambios");		
				
				_estadoComponentes(1);
			}
		}
	}
	
	
	
	class EscuchaCierreCursada implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
//			MyConsole c = new MyConsole(false,getOuter().getMyApp().getMyAppContainer().c());
//			Integer x = c.print("Ingrese entero: ").readInteger();
//			MyLog.out("---------------------------------"+x);
			if( cbAlumno.isUnselected() )
			{
				showErrorMessage("Debe seleccionar un alumno","RROR");
				return;
			}
			
			Alumno a = cbAlumno.getSelectedItem();
			
			MyInstantForm f = createInstantForm("Cierre de cursada");
			f.addField("nota","Nota").value(a.getCierreNota()).valid(s->Integer.parseInt(s)<=10,"La nota debe estar entre 1 y 10").mask(MyTextField.MASK_INTEGER);
			f.addField("tp","Entregó TP?").value(a.getCierreTP()).valid(s->MyString.oneOf(s,"true","false"),"Responde SI o NO si entregó el TP").mask(MyTextField.MASK_LOWERCASE);
			f.showForm();
			
			if( !f.wasCanceled() )
			{
				Integer nota = Integer.parseInt(f.getValue("nota")); 
				Boolean tp = f.getValue("tp").equals("SI");
				
				facade.cerrarCursada(a,nota,tp);
				
				showInformationMessage("Los datos se registraron correctamente","Perfecto!");
			}
			
		}
	}
	
	class EscuchaPersona implements ItemListener
	{
		@Override
		public void itemStateChanged(ItemEvent e)
		{
		}
	}
	
	class EscuchaEliminar implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
		};
	}
	
	@Override
	public String getName()
	{
		return "ABM Personas";
	}
}
