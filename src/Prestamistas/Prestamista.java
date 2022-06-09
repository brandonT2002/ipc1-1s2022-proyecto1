package Prestamistas;
public class Prestamista {
	private String nombre;
	private String apellido;
	private String cui;
	
	Prestamista(String nombre, String apellido, String cui){
		this.setNombre(nombre);
		this.setApellido(apellido);
		this.setCui(cui);
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getCui() {
		return cui;
	}
	public void setCui(String cui) {
		this.cui = cui;
	}
}
