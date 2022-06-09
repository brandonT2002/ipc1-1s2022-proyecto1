package Libros;
public class CtrlLibros {
	public static Libro[] libros = new Libro[15];
	
	public static boolean verificarIsbn(String isbn) {
		for(Libro libro : libros) {
			if(libro != null && libro.getIsbn().equals(isbn)) {
				return true;
			}
		}
		return false;
	}
	public static boolean nuevoLibro(String isbn,String autor,int año,String titulo,
			String edicion,String tema,String desc,
			int numCop,int numCopDisp,int numEst,int fila) {
		for(int i = 0; i < libros.length; i++) {
			if(libros[i] == null) {
				libros[i] = new Libro(isbn,autor,año,titulo,edicion,tema,desc,numCop,numCopDisp,numEst,fila);
				return true;
			}
		}
		return false;
	}
	public static Libro[] bSLibro(String titulo) {
		Libro [] bslibro = new Libro[1];
		for(Libro libro : libros) {
			if(libro != null && libro.getTitulo().equalsIgnoreCase(titulo)) {
				bslibro[0] = libro;
				return bslibro;
			}
		}
		return null;
	}
	static int cantLibro(int añoI, int añoF) {
		int n = 0;
		for(Libro libro : libros) {
			if(libro != null && libro.getAño() >= añoI && libro.getAño() <= añoF) {
				n ++;
			}
		}
		return n;
	}
	public static Libro[] bLibro(int añoI, int añoF) {
		int n = cantLibro(añoI,añoF);
		if(n == 0) return null;
		Libro[] aLibro = new Libro[n];
		for(Libro libro : libros) {
			if(libro != null && libro.getAño() >= añoI && libro.getAño() <= añoF) {
				for(int i = 0; i < aLibro.length; i++) {
					if(aLibro[i] == null) {
						aLibro[i] = libro;
						break;
					}
				}
			}
		}
		for(int i = 0; i < aLibro.length - 1; i++) {
			for(int j = 0; j < aLibro.length - i - 1; j++) {
				if(aLibro[j] != null && aLibro[j + 1] != null && aLibro[j].getAño() < aLibro[j + 1].getAño()) {
					Libro tmp = aLibro[j];
					aLibro[j] = aLibro[j + 1];
					aLibro[j + 1] = tmp;
				}
			}
		}
		return aLibro;
	}
	static int cantLibro(String autor) {
		int n = 0;
		for(Libro libro : libros) {
			if(libro != null && libro.getAutor().equalsIgnoreCase(autor)) {
				n ++;
			}
		}
		return n;
	}
	public static Libro[] bLibro(String autor) {
		int n = cantLibro(autor);
		if(n == 0) return null;
		Libro[] aLibro = new Libro[n];
		for(Libro libro : libros) {
			if(libro != null && libro.getAutor().equalsIgnoreCase(autor)) {
				for(int i = 0; i < aLibro.length; i++) {
					if(aLibro[i] == null) {
						aLibro[i] = libro;
						break;
					}
				}
			}
		}
		for(int i = 0; i < aLibro.length - 1; i++) {
			for(int j = 0; j < aLibro.length - i - 1; j++) {
				if(aLibro[j] != null && aLibro[j + 1] != null && aLibro[j].getAño() < aLibro[j + 1].getAño()) {
					Libro tmp = aLibro[j];
					aLibro[j] = aLibro[j + 1];
					aLibro[j + 1] = tmp;
				}
			}
		}
		return aLibro;
	}
	static int cantLibro(String autor, int añoI, int añoF) {
		int n = 0;
		for(Libro libro : libros) {
			if(libro != null && libro.getAutor().equalsIgnoreCase(autor) && libro.getAño() >= añoI && libro.getAño() <= añoF) {
				n ++;
			}
		}
		return n;
	}
	public static Libro[] bLibro(String autor, int añoI, int añoF) {
		int n = cantLibro(autor,añoI,añoF);
		if(n == 0) return null;
		Libro[] aLibro = new Libro[n];
		for(Libro libro : libros) {
			if(libro != null && libro.getAutor().equalsIgnoreCase(autor) && libro.getAño() >= añoI && libro.getAño() <= añoF) {
				for(int i = 0; i < aLibro.length; i++) {
					if(aLibro[i] == null) {
						aLibro[i] = libro;
						break;
					}
				}
			}
		}
		for(int i = 0; i < aLibro.length - 1; i++) {
			for(int j = 0; j < aLibro.length - i - 1; j++) {
				if(aLibro[j] != null && aLibro[j + 1] != null && aLibro[j].getAño() < aLibro[j + 1].getAño()) {
					Libro tmp = aLibro[j];
					aLibro[j] = aLibro[j + 1];
					aLibro[j + 1] = tmp;
				}
			}
		}
		return aLibro;
	}
	public static Libro bIsbnLibro(String isbn) {
		for(Libro libro : libros) {
			if(libro != null && libro.getIsbn().equals(isbn)) {
				return libro;
			}
		}
		return null;
	}
	public static boolean modCantLibro(String isbn, int cant) {
		for(int i = 0; i < CtrlLibros.libros.length; i++) {
			if(CtrlLibros.libros[i] != null && CtrlLibros.libros[i].getIsbn().equals(isbn)) {
				Libro tmp = CtrlLibros.libros[i];
				CtrlLibros.libros[i] = new Libro(isbn,tmp.getAutor(),tmp.getAño(),tmp.getTitulo(),tmp.getEdicion(),tmp.getTema(),tmp.getDesc(),
						 tmp.getNumCop() + cant,tmp.getNumCopDisp() + cant,tmp.getNumEst(),tmp.getFila());
				 return true;
			}
		}
		return false;
	}
}
