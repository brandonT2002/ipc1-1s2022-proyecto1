package Componentes;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import Interfaz.Libros;
import Interfaz.Prestamos;
import Libros.Libro;
import Prestamistas.Prestamista;
import javax.swing.JScrollPane;
public class Tabla extends JPanel {
	private static final long serialVersionUID = 1L;
	String[] encabezado;
	String[][] datos;
	JScrollPane scroll;
	JTable tabla;
	JPanel base;
	int[] distribucion;
	int tamaño = 10;
	int margen;
	DefaultTableCellRenderer alineacion;
	Color fuente = Color.WHITE,borde = Color.GRAY,fondo = new Color(195,195,195);
	
	public Tabla() {	
		this.setLayout(null);
		this.setBackground(borde);
		base = new JPanel();
		base.setLayout(null);
	}
	public void fuente(int tamaño) {
		this.tamaño = tamaño;
	}
	public void header(String[] encabezado,int[] distribucion) {
		this.encabezado = encabezado;
		this.distribucion = distribucion;
	}
	public void color(Color fuente,Color borde,Color fondo) {
		this.fuente = fuente;
		this.borde = borde;
		this.fondo = fondo;
		this.setBackground(borde);
	}
	public void datos(String[][] datos,DefaultTableCellRenderer alineacion) {
		this.datos = datos;
		this.alineacion = alineacion;
		tabla = new JTable(datos,encabezado) {
			private static final long serialVersionUID = 1L;
			public boolean editCellAt(int row, int column, java.util.EventObject e) {
				return false;
			}
		};
		tabla.setRowSelectionAllowed(false);
		tabla.getTableHeader().setReorderingAllowed(false);
		for(int i = 0; i < encabezado.length; i++) {
			tabla.getColumnModel().getColumn(i).setResizable(false);
			tabla.getColumn(encabezado[i]).setCellRenderer(alineacion);
		}
		for(int i = 0; i < distribucion.length; i++) {
			tabla.getColumnModel().getColumn(i).setMaxWidth(distribucion[i]);
			tabla.getColumnModel().getColumn(i).setMinWidth(distribucion[i]);
		}
	}
	public void datos(String[][] datos) {
		this.datos = datos;
		tabla = new JTable(datos,encabezado) {
			private static final long serialVersionUID = 1L;
			public boolean editCellAt(int row, int column, java.util.EventObject e) {
				return false;
			}
		};
		tabla.setRowSelectionAllowed(false);
		tabla.getTableHeader().setReorderingAllowed(false);
		for(int i = 0; i < encabezado.length; i++) {
			tabla.getColumnModel().getColumn(i).setResizable(false);
		}
		for(int i = 0; i < distribucion.length; i++) {
			tabla.getColumnModel().getColumn(i).setMaxWidth(distribucion[i]);
			tabla.getColumnModel().getColumn(i).setMinWidth(distribucion[i]);
		}
	}
	public void reajustar() {
		int xi = 1;
		int[] tmp = new int[encabezado.length];
		for(int i = 0; i < tmp.length; i++) {
			tmp[i] = tabla.getColumnModel().getColumn(i).getMinWidth();
		}
		int mayor = mayor(tmp); 
		for(int i = 0; i < distribucion.length; i++) {
			int ancho = distribucion[i]-1;
			if(datos.length*16 > base.getHeight() && distribucion[i] == mayor) {
				ancho -= 18;
				tabla.getColumnModel().getColumn(i).setMaxWidth(ancho);
				tabla.getColumnModel().getColumn(i).setMinWidth(ancho);
			}
			if(datos.length*16 > base.getHeight() && i > 0 && distribucion[i - 1] == mayor) {
				xi -= 18;
				mayor = 0;
			}
			if(datos.length*16 < base.getHeight() && i == distribucion.length - 1) ancho --;
			this.add(rotulo(encabezado[i],xi,1,ancho,18));
			xi += distribucion[i];
		}
		margen = xi;
	}
	public void acciones(String texto,Object[] objeto,String tipo) {
		for(int i = 0; i < datos.length; i++) {
			Object obj = objeto[i];
			Boton accion = new Boton(texto);
			accion.ubicacion(margen-distribucion[distribucion.length - 1]-1,i*16,tabla.getColumnModel().getColumn(distribucion.length - 1).getWidth(),15);
			accion.texto(Colores.fuenteP,tamaño);
			accion.diseño(0,null,Colores.blanco);
			accion.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(tipo.equalsIgnoreCase("Libro")) {
						new Libros().formMLibros((Libro) obj);
					}
					else if(tipo.equalsIgnoreCase("Prestamista")) {
						new Prestamos().historial((Prestamista) obj);
					}
				}
			});
			tabla.add(accion);
		}
	}
	public void locDim(int x,int y,int altura) {
		int w = 0;
		for(int i = 0; i < distribucion.length; i++) w+= distribucion[i];
		this.setBounds(x,y,w,altura);
		tabla.setFont(new Font("Tahoma",Font.PLAIN,tamaño));
		scroll = new JScrollPane(tabla);
		base.setBounds(0,20,this.getWidth(),this.getHeight()-20);
		scroll.setBounds(0,-21,base.getWidth(),base.getHeight()+21);
		tabla.setBounds(0,0,scroll.getWidth(),scroll.getHeight());
		base.add(scroll);
		reajustar();
		this.add(base);
		tabla.setFont(new Font("Tahoma",Font.PLAIN,tamaño));
	}
	public JPanel rotulo(String texto,int x,int y,int w,int h) {
		JPanel rotulo = new JPanel();
		rotulo.setBackground(fondo);
		rotulo.setBounds(x,y,w,h);
		rotulo.setLayout(null);
		rotulo.add(new Etiqueta(0,0,rotulo.getWidth(),rotulo.getHeight(),texto,fuente,tamaño,true));
		return rotulo;
	}
	public int mayor(int[] arreglo) {
		for (int i = 0; i < arreglo.length - 1; i++) {
		    for (int j = 0; j < arreglo.length - i - 1; j++) {
		        if(arreglo[j] < arreglo[j + 1]){
		            int tmp = arreglo[j + 1];
		            arreglo[j + 1] = arreglo[j];
		            arreglo[j] = tmp;
		        }
		    }
		}
		return arreglo[0];
	}
}