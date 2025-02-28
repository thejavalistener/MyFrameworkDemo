package app;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import app.mapping.Persona;
import thejavalistener.fwk.backend.DaoSupport;

@Component
public class Facade extends DaoSupport
{
	@Transactional
	public void altaOModificacion(Persona p)
	{
		if( p.getIdPersona()==null )
		{
			insert(p);
		}
		else
		{
			Persona x = find(Persona.class,p.getIdPersona());
			x.setNombre(p.getNombre());
			x.setFechaNacimiento(p.getFechaNacimiento());
		}
	}
	
	@Transactional
	public void eliminar(Persona p)
	{
		Persona x = find(Persona.class,p.getIdPersona());
		delete(x);
	}
	
	public List<Persona> obtenerPersonas()
	{
		return queryMultipleRows("FROM Persona p");
	}
}
