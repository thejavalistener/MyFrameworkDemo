package spotify;

public class Artist
{
	private int artistId;
	private String spotifyId;
	private String name;
	
	
	
	public int getArtistId()
	{
		return artistId;
	}
	public void setArtistId(int artistId)
	{
		this.artistId=artistId;
	}
	public String getSpotifyId()
	{
		return spotifyId;
	}
	public void setSpotifyId(String spotifyId)
	{
		this.spotifyId=spotifyId;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name=name;
	}
}
