package app.screen;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.Facade;
import app.mapping.Persona;
import thejavalistener.fwk.awt.searchbox.MySearchBoxController;

@Component
public class PersonaSearchBoxController implements MySearchBoxController<Persona>
{
	@Autowired
	private Facade facade;
	
	@Override
	public List<Persona> dataRequested(String toSearch)
	{
		return facade.buscarPorNombre(toSearch);
	}

}
