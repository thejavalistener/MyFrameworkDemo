package spotify;

import java.util.List;

public class SpotifyService
{
	private String token = null;
	
	public SpotifyService(String tk)
	{
		this.token = tk;
	}
	
	// Retorna la lista de álbumes de la biblioteca del usuario.
	// Son aquellos álbumes que el usuario (identificado por el token)
	// agregó a su biblioteca
	public List<Album> getAlbums()
	{
		return null;
	}
	
	// Retorna la lista de dispositivos (parlantes, bocinas, audífonos) habilitados 
	// y disponibles para el usuario identificado por el token.
	public List<Device> getDevices()
	{
		return null;
	}
	
	// Reproduce un album en un dispositivo
	public void play(Album a, Device d)
	{
	}
	
	// Si está sonando alguna canción la detiene
	public void stop()
	{
		
	}
}
