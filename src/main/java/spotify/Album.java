package spotify;

import java.sql.Date;

public class Album
{
	private int albumId;
	private String sportfyId;
	private String title;
	private Integer releasedYear;
	private Integer recordedYear;
	private Artist mainArtist;
	private String coverUrl;
	private Date addedAt;
	
	public int getAlbumId()
	{
		return albumId;
	}
	public void setAlbumId(int albumId)
	{
		this.albumId=albumId;
	}
	public String getSportfyId()
	{
		return sportfyId;
	}
	public void setSportfyId(String sportfyId)
	{
		this.sportfyId=sportfyId;
	}
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title=title;
	}
	public Integer getReleasedYear()
	{
		return releasedYear;
	}
	public void setReleasedYear(Integer releasedYear)
	{
		this.releasedYear=releasedYear;
	}
	public Integer getRecordedYear()
	{
		return recordedYear;
	}
	public void setRecordedYear(Integer recordedYear)
	{
		this.recordedYear=recordedYear;
	}
	public Artist getMainArtist()
	{
		return mainArtist;
	}
	public void setMainArtist(Artist mainArtist)
	{
		this.mainArtist=mainArtist;
	}
	public String getCoverUrl()
	{
		return coverUrl;
	}
	public void setCoverUrl(String coverUrl)
	{
		this.coverUrl=coverUrl;
	}
	public Date getAddedAt()
	{
		return addedAt;
	}
	public void setAddedAt(Date addedAt)
	{
		this.addedAt=addedAt;
	}
}
