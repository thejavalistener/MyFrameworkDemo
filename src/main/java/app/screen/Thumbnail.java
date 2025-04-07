package app.screen;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import app.screen.decorator.ThmDecorator;
import spotify.Album;
import spotify.Artist;
import thejavalistener.fwk.awt.link.MyLink;
import thejavalistener.fwk.awt.link.MyLinkGrid;
import thejavalistener.fwk.awt.panel.GridLayout2;
import thejavalistener.fwk.util.MyColor;
import thejavalistener.fwk.util.MyNumber;
import thejavalistener.fwk.util.string.MyString;

@Component
@Scope("prototype")
public class Thumbnail 
{
	@Autowired
	private ThmDecorator decorator;
	
	private Album album;
	
	private MyLink lnkReaYear = null;
	private MyLink lnkRecYear = null;
	private MyLink lnkTitle = null;
	private MyLink lnkArtist = null;

	private JPanel contentPane;
	private ThumbImage tmbImage;
	private MyLinkGrid grid;

	public Thumbnail()
	{
		tmbImage = new ThumbImage();
		
		contentPane = new JPanel(new GridLayout2(2,1,0,0));
		
		contentPane.add(tmbImage);

		grid = new MyLinkGrid();

		// 1ra. fila: Título, año de realización y (si lo hay) de grabación
		
		grid.addNewRow().add(" ");
		lnkTitle = grid.getJustAddedLink();
		
		grid.addNewRow().add(" ");
		lnkArtist = grid.getJustAddedLink();
		lnkArtist.setClickeable(true);
		
		grid.addNewRow().add(" ");
		lnkReaYear = grid.getJustAddedLink();
		lnkReaYear.setClickeable(true);

		grid.add(" ");
		lnkRecYear = grid.getJustAddedLink();
		lnkRecYear.setClickeable(true);

		contentPane.add(grid.c());
	}
	
	public void init()
	{
		tmbImage.setImageSize(decorator.getImageSize());
		grid.setBackground(decorator.getThumbnailBackground());
		contentPane.setBackground(decorator.getThumbnailBackground());		
		decorator.decoreTitle(lnkTitle);
		lnkTitle.c().validate();
		decorator.decoreArtist(lnkArtist);
		lnkArtist.c().validate();
		decorator.decoreReleasedYear(lnkReaYear);
		lnkReaYear.c().validate();
		decorator.decoreRecordedYear(lnkRecYear);
		lnkRecYear.c().validate();
	}
	
	public void setAlbum(Album a)
	{
		this.album = a;
		tmbImage.load();

		lnkTitle.setText(a.getTitle());
		lnkArtist.setText(a.getMainArtist().getName());
		lnkReaYear.setText(a.getReleasedYear().toString());
		
		if( a.getRecordedYear()!=null )
		{
			lnkRecYear.setText(a.getRecordedYear().toString()); 
		}		
	}
	
	public java.awt.Component c()
	{
		return contentPane;
	}
		
	public static Album createDemoAlbum()
	{
		String[] covers = { 
				"https://hips.hearstapps.com/hmg-prod/images/eyj0exaioijkv1qilcjhbgcioijiuzi1nij9-eyjpbsi6wyjcl2fydhdvcmtcl2ltywdlrmlszvwvnwrlnduwmddkmje0mc5qcgcilcjyzxnpemusmtawmcjdfq-z1im-vmcm1-bqqxzpzkyxuqdmkhbzeanywzrbvzldlm-1669121479.jpg"
			    ,"https://cdn-images.dzcdn.net/images/cover/3c1ebf7765293ec02d3aa124aadb258c/0x1900-000000-80-0-0.jpg"
			    ,"https://akamai.sscdn.co/uploadfile/letras/albuns/2/1/5/8/26631686315643.jpg"
			    ,"https://www.udiscovermusica.com/wp-content/uploads/sites/7/2022/12/The-Beatles-Let-It-Be-album-cover-820-1536x1536-1.jpeg"
			    ,"https://cdn-images.dzcdn.net/images/cover/43504743d2efb7092130f1e2340e1ffa/0x1900-000000-80-0-0.jpg"
			    ,"https://cdn-images.dzcdn.net/images/cover/aa94ab293730bb7845d2aa8c672b2c29/500x500-000000-80-0-0.jpg"
		};

		Artist artist=new Artist();
		artist.setArtistId(1);
		artist.setName("Astrud Gilberto");
		artist.setSpotifyId("XXCCRRSS");

		
		int id = MyNumber.rndInt(covers.length);

		Album a=new Album();
		a.setAlbumId(id);
		a.setSportfyId("ABCDEFGHIJKLM");
		a.setTitle("A Certain Smile, A Certai...");
		a.setMainArtist(artist);
		a.setReleasedYear(1976);
		a.setRecordedYear(1968);
		a.setCoverUrl(covers[id]);
		
		return a;
	}
	
	public class ThumbImage extends JLabel
	{
		private int sizePx;

		public ThumbImage()
		{
			setOpaque(true);
			setBackground(MyColor.random());
		}
		
		public void setImageSize(int px)
		{
			this.sizePx = px;
			this.setPreferredSize(new Dimension(px,px));
		}

		public void load()
		{
			String imgName=MyString.lpad(Integer.toString(album.getAlbumId()),'0',10)+".jpg";
			String imgUrl=album.getCoverUrl();

			// Comienza el procesamiento en un hilo separado
			new Thread(() -> {
				try
				{
					File imgFile=new File(imgName);

					BufferedImage image;

					if(imgFile.exists())
					{
						// Si el archivo ya existe, lo cargamos
						image=ImageIO.read(imgFile);

						// Verificamos si el tamaño coincide
						if(image.getWidth()!=sizePx||image.getHeight()!=sizePx)
						{
							// Si no coincide, descargamos de nuevo
							image=downloadAndResize(imgUrl,imgFile,sizePx);
						}
					}
					else
					{
						// Si no existe, descargamos y procesamos
						image=downloadAndResize(imgUrl,imgFile,sizePx);
					}

					// Convierte la imagen en un ImageIcon y la muestra
					ImageIcon icon=new ImageIcon(image);
					SwingUtilities.invokeLater(() -> setIcon(icon)); // Actualiza el
																		// JLabel en
																		// el hilo
																		// de la UI

				}
				catch(IOException e)
				{
					e.printStackTrace();
					SwingUtilities.invokeLater(() -> setText("Error al cargar la imagen")); // Muestra
																							// un
																							// error
																							// en
																							// la
																							// UI
				}
			}).start();
		}

		// Método para descargar y redimensionar la imagen
		private BufferedImage downloadAndResize(String imgUrl, File imgFile, int sizePx) throws IOException
		{
			// Descarga la imagen desde la URL
			URL url=new URL(imgUrl);
			File tempFile=new File("temp_download.jpg"); // Archivo temporal

			FileUtils.copyURLToFile(url,tempFile);

			// Carga la imagen descargada
			BufferedImage image=ImageIO.read(tempFile);

			// Redimensiona la imagen al tamaño deseado
			image=resizeImage(image,sizePx,sizePx);

			// Guarda la imagen redimensionada en el archivo indicado
			ImageIO.write(image,"jpg",imgFile);

			// Limpia el archivo temporal
			tempFile.delete();

			return image;
		}

		// Método para redimensionar imágenes
		private BufferedImage resizeImage(BufferedImage originalImage, int width, int height)
		{
			BufferedImage resizedImage=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
			Graphics2D g=resizedImage.createGraphics();
			g.drawImage(originalImage,0,0,width,height,null);
			g.dispose(); // Libera recursos
			return resizedImage;
		}
	}	
}
