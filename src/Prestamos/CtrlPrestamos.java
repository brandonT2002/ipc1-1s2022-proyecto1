package Prestamos;
import Libros.*;
public class CtrlPrestamos {
	public static Prestamo[] prestamos = new Prestamo[30];
	
	public static boolean dispLibro(String isbn) {
		for(Libro libro : CtrlLibros.libros) {
			if(libro != null && libro.getIsbn().equals(isbn) && libro.getNumCopDisp() > 0) {
				return true;
			}
		}
		return false;
	}
	public static Prestamo pendLibro(String cui) {
		for(Prestamo prestamo : prestamos) {
			if(prestamo != null && prestamo.getCui().equals(cui) && !prestamo.getEstado()) {
				return prestamo;
			}
		}
		return null;
	}
	public static boolean modCantLibro(String isbn, int cant) {
		for(int i = 0; i < CtrlLibros.libros.length; i++) {
			if(CtrlLibros.libros[i] != null && CtrlLibros.libros[i].getIsbn().equals(isbn)) {
				Libro tmp = CtrlLibros.libros[i];
				 CtrlLibros.libros[i] = new Libro(isbn,tmp.getAutor(),tmp.getAño(),tmp.getTitulo(),tmp.getEdicion(),tmp.getTema(),tmp.getDesc(),
						 tmp.getNumCop(),tmp.getNumCopDisp() + cant,tmp.getNumEst(),tmp.getFila());
				 return true;
			}
		}
		return false;
	}
	public static boolean nuevoPrestamo(String isbn, String cui, String fechaP, boolean estado) {
		for(int i = 0; i < prestamos.length; i ++) {
			if(prestamos[i] == null) {
				prestamos[i] = new Prestamo(isbn,cui,fechaP,"",estado);
				return modCantLibro(isbn,-1);
			}
		}
		return false;
	}
	public static boolean devLibro(String isbn, String cui, String fechaD) {
		for(int i = 0; i < prestamos.length; i++) {
			if(prestamos[i] != null && prestamos[i].getIsbn().equals(isbn) && prestamos[i].getCui().equals(cui) && !prestamos[i].getEstado()) {
				Prestamo tmp = prestamos[i];
				prestamos[i] = new Prestamo(isbn,cui,tmp.getFechaP(),fechaD,true);
				return modCantLibro(isbn,1);
			}
		}
		return false;
	}
	static int cantPrestamo(String cui) {
		int n = 0;
		for(Prestamo prestamo : prestamos) {
			if(prestamo != null && prestamo.getCui().equals(cui)) {
				n ++;
			}
		}
		return n;
	}
	public static Prestamo[] historial(String cui) {
		int n = cantPrestamo(cui);
		if(n == 0) return null;
		Prestamo[] historial = new Prestamo[n];
		for(Prestamo prestamo : prestamos) {
			if(prestamo != null && prestamo.getCui().equals(cui)) {
				for(int i = 0; i < historial.length; i++) {
					if(historial[i] == null) {
						historial[i] = prestamo;
						break;
					}
				}
			}
		}
		return historial;
	}
}
