package Prestamos;
public class Prestamo {
	private String isbn;
	private String cui;
	private String fechaP;
	private String fechaD;
	private boolean estado;
	
	public Prestamo(String isbn, String cui,String fechaP, String fechaD, boolean estado){
		this.setIsbn(isbn);
		this.setCui(cui);
		this.setFechaP(fechaP);
		this.setFechaD(fechaD);
		this.setEstado(estado);
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getCui() {
		return cui;
	}
	public void setCui(String cui) {
		this.cui = cui;
	}
	public String getFechaP() {
		return fechaP;
	}
	public void setFechaP(String fechaP) {
		this.fechaP = fechaP;
	}
	public String getFechaD() {
		return fechaD;
	}
	public void setFechaD(String fechaD) {
		this.fechaD = fechaD;
	}
	public boolean getEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
}