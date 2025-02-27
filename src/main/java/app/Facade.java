package app;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.mapping.Persona;
import thejavalistener.fwk.backend.DaoSupport;

@Component
public class Facade extends DaoSupport
{
	@Autowired
	private EntityManager em = null;
	
	@Transactional
	public void altaOModificacion(Persona p)
	{
		// inserta o, si ya existe, modifica
		insertOrUpdate(p);
	}
	
	public List<Persona> obtenerPersonas()
	{
		return queryMultipleRows("FROM Persona p");
	}
}
