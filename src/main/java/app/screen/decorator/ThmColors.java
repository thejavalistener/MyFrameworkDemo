package app.screen.decorator;

import java.awt.Color;

import org.springframework.stereotype.Component;

@Component
public class ThmColors
{
	// background general de todos los paneles
	public static final Color background = new Color(18,18,18);
	
	// background de las diapositivas
	public static final Color thumbnailBackground = new Color(29,29,29);
	
	// texto destacado (seleccionado o título del disco por ejemplo)
	public static final Color highlight = new Color(249,249,249);
	
	// texto por defecto
	public static final Color unhighlight = new Color(173,173,173);

	
	// sombra que aplica sobre el rollover de los textos
	public static final Color shadow = new Color(49,49,49);
	
	// font del sistema
	public static final String fontName = "Calibri";
	
	// color para los divisores de paneles
	public static final Color divider = new Color(51,51,51);

}
