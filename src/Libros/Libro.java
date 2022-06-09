package Libros;
public class Libro {
	private String isbn;
	private String autor;
	private int año;
	private String titulo;
	private String edicion;
	private String tema;
	private String desc;
	private int numCop;
	private int numCopDisp;
	private int numEst;
	private int fila;
	public Libro(String isbn,String autor,int año,String titulo,
			String edicion,String tema,String desc,
			int numCop,int numCopDisp,int numEst,int fila) {
		this.setIsbn(isbn);
		this.setAutor(autor);
		this.setAño(año);
		this.setTitulo(titulo);
		this.setEdicion(edicion);
		this.setTema(tema);
		this.setDesc(desc);
		this.setNumCop(numCop);
		this.setNumCopDisp(numCopDisp);
		this.setFila(fila);
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public int getAño() {
		return año;
	}
	public void setAño(int año) {
		this.año = año;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getEdicion() {
		return edicion;
	}
	public void setEdicion(String edicion) {
		this.edicion = edicion;
	}
	public String getTema() {
		return tema;
	}
	public void setTema(String tema) {
		this.tema = tema;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public int getNumCop() {
		return numCop;
	}
	public void setNumCop(int numCop) {
		this.numCop = numCop;
	}
	public int getNumCopDisp() {
		return numCopDisp;
	}
	public void setNumCopDisp(int numCopDisp) {
		this.numCopDisp = numCopDisp;
	}
	public int getNumEst() {
		return numEst;
	}
	public void setNumEst(int numEst) {
		this.numEst = numEst;
	}
	public int getFila() {
		return fila;
	}
	public void setFila(int fila) {
		this.fila = fila;
	}
}
