package Prestamistas;
public class CtrlPrestamistas {
	public static Prestamista[] prestamistas = new Prestamista[15];
	public static boolean verificarPres(String cui) {
		for(Prestamista prestamista : prestamistas) {
			if(prestamista != null && prestamista.getCui().equals(cui)) {
				return true;
			}
		}
		return false;
	}
	public static boolean nuevoPres(String nombre, String apellido, String cui) {
		for(int i = 0; i < prestamistas.length; i++) {
			if(prestamistas[i] == null) {
				prestamistas[i] = new Prestamista(nombre,apellido,cui);
				return true;
			}
		}
		return false;
	}
	public static Prestamista busP(String cui) {
		for(Prestamista prestamista : prestamistas) {
			if(prestamista != null && prestamista.getCui().equals(cui)) {
				return prestamista;
			}
		}
		return null;
	}
}
