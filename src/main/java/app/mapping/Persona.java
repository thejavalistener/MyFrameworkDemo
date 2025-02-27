package app.mapping;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="DEMO_PERSONA")
public class Persona
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_persona")
	private int idPersona;

	@Column(name="nombre")
	private String nombre;
	
	@Column(name="fecha_nacimiento")
	private Date fechaNacimiento;

	public int getIdPersona()
	{
		return idPersona;
	}
	public void setIdPersona(int idPersona)
	{
		this.idPersona=idPersona;
	}
	public String getNombre()
	{
		return nombre;
	}
	public void setNombre(String nombre)
	{
		this.nombre=nombre;
	}
	public Date getFechaNacimiento()
	{
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento)
	{
		this.fechaNacimiento=fechaNacimiento;
	}
	
}
