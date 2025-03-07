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
	public Integer altaOModificacion(Persona p)
	{
		int ret;
		if( p.getIdPersona()==null )
		{
			insert(p);
			ret = 1;
		}
		else
		{
			Persona x = find(Persona.class,p.getIdPersona());
			x.setNombre(p.getNombre());
			x.setFechaNacimiento(p.getFechaNacimiento());
			ret = 2;
		}
		
		return ret;
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

	public List<Persona> buscarPorNombre(String pattern)
	{
		String hql = "FROM Persona p WHERE LOWER(p.nombre) LIKE LOWER('%" + pattern.toString() + "%') ";
		return queryMultipleRows(hql);
	}
}
