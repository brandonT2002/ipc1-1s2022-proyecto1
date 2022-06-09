package Interfaz;
import static Libros.CtrlLibros.libros;
import Libros.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import Componentes.*;
public class Libros extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	Texto nIsbn,nAutor,nTitulo,nEdicion,nTemas,nCopias,nA�o,nEstanteria,nFila;
	Texto bTitulo,bA�oI,bA�oF,bAutor;
	Texto vIsbn,vAutor,vTitulo,vEdicion,vTemas,vCopAg,vCopDisp,vA�o,vEstanteria,vFila;
	AreaTexto nDesc,vDesc;
	Boton crearLibro,buscarS,buscarA;
	Boton modCan,confirmar;
	Tabla tabLib,tabLibBus;
	JFrame formModificar;
	String tIsbn;
	boolean esModificable = false;
	public Libros() {
		this.setLayout(null);
		this.setBackground(Colores.blanco);
		
		formNLibro();
		tablaLibros();
		buscador();
		tablaBusqueda(null);
	}
	public void formNLibro() {
		this.add(new Etiqueta(50,30,275,30,"Nuevo Libro",Colores.celeste1,18,false));
		
		nIsbn = new Texto();
		nIsbn.setBounds(50,70,410,25);
		nIsbn.setPlaceholder("ISBN");
		this.add(nIsbn);
		
		nAutor = new Texto();
		nAutor.setBounds(50,100,200,25);
		nAutor.setPlaceholder("Autor");
		this.add(nAutor);
		
		nTitulo = new Texto();
		nTitulo.setBounds(50,130,200,25);
		nTitulo.setPlaceholder("T�tulo");
		this.add(nTitulo);
		
		nEdicion = new Texto();
		nEdicion.setBounds(50,160,200,25);
		nEdicion.setPlaceholder("Edici�n");
		this.add(nEdicion);
		
		nTemas = new Texto();
		nTemas.setBounds(50,190,200,25);
		nTemas.setPlaceholder("Temas");
		this.add(nTemas);
		
		nCopias = new Texto();
		nCopias.setBounds(260,100,200,25);
		nCopias.setPlaceholder("Copias");
		this.add(nCopias);
		
		nA�o = new Texto();
		nA�o.setBounds(260,130,200,25);
		nA�o.setPlaceholder("A�o de Publicaci�n");
		this.add(nA�o);
		
		nEstanteria = new Texto();
		nEstanteria.setBounds(260,160,200,25);
		nEstanteria.setPlaceholder("No. de Estanter�a");
		this.add(nEstanteria);
		
		nFila = new Texto();
		nFila.setBounds(260,190,200,25);
		nFila.setPlaceholder("Fila de Estanter�a");
		this.add(nFila);
		
		nDesc = new AreaTexto();
		nDesc.setLineWrap(true);
		nDesc.setPlaceholder("Descripci�n");
		
		JScrollPane scroll = new JScrollPane (nDesc,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setBounds(50,220,410,50);
		scroll.setBorder(BorderFactory.createLineBorder(Colores.bordeText,1));
		this.add(scroll);
		
		crearLibro = new Boton("Crear Libro");
		crearLibro.ubicacion(330,30,130,25);
		crearLibro.texto(Colores.blanco,15);
		crearLibro.dise�o(3,Colores.verde2,Colores.verde3);
		crearLibro.addActionListener(this);
		this.add(crearLibro);
	}
	public void tablaLibros() {
		String[] encabezado = {"No","T�tulo","Autor","ISBN","A�o","Disponibles"};
		int[] distribucion = {40,270,150,100,70,130};
		
		DefaultTableCellRenderer alineacion = new DefaultTableCellRenderer();
		alineacion.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		
		int cantLibros = 0;
		for(Libro lib : libros) {
			if(lib != null) cantLibros++;
		}
		String[][] listLibros = new String[cantLibros][6];
		cantLibros = 0;
		for(Libro lib : libros) {
			if(lib != null) {
				listLibros[cantLibros][0] = String.valueOf(cantLibros + 1);
				listLibros[cantLibros][1] = lib.getTitulo();
				listLibros[cantLibros][2] = lib.getAutor();
				listLibros[cantLibros][3] = lib.getIsbn();
				listLibros[cantLibros][4] = String.valueOf(lib.getA�o());
				listLibros[cantLibros][5] = String.valueOf(lib.getNumCopDisp());
				cantLibros++;
			}
		}
		
		tabLib = new Tabla();
		tabLib.color(Colores.blanco,Colores.verde2,Colores.verde3);
		tabLib.header(encabezado,distribucion);
		tabLib.datos(listLibros,alineacion);
		tabLib.fuente(12);
		tabLib.locDim(550,70,200);
		this.add(tabLib);
	}
	public void buscador() {
		bTitulo = new Texto();
		bTitulo.setBounds(50,350,275,25);
		bTitulo.setPlaceholder("Ingrese el T�tulo del Libro");
		this.add(bTitulo);
		
		buscarS = new Boton("Buscar");
		buscarS.ubicacion(330,350,130,25);
		buscarS.texto(Colores.blanco,15);
		buscarS.dise�o(3,Colores.verde2,Colores.verde3);
		buscarS.addActionListener(this);
		this.add(buscarS);
		
		bA�oI = new Texto();
		bA�oI.setBounds(50,400,70,25);
		bA�oI.setPlaceholder("A�o Incio");
		this.add(bA�oI);
		
		bA�oF = new Texto();
		bA�oF.setBounds(125,400,70,25);
		bA�oF.setPlaceholder("A�o Fin");
		this.add(bA�oF);
		
		bAutor = new Texto();
		bAutor.setBounds(225,400,100,25);
		bAutor.setPlaceholder("Autor");
		this.add(bAutor);
		
		buscarA = new Boton("Buscar");
		buscarA.ubicacion(330,400,130,25);
		buscarA.texto(Colores.blanco,15);
		buscarA.dise�o(3,Colores.gris2,Colores.gris4);
		buscarA.setEnabled(false);
		buscarA.addActionListener(this);
		this.add(buscarA);
		
		bA�oI.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char n = e.getKeyChar();
				try {
					Integer.parseInt(bA�oI.getText());
					if(Character.isDigit(n)) {
						if(!bA�oI.getText().equals("") && !bA�oF.getText().equals("")) {
							buscarA.dise�o(3,Colores.verde2,Colores.verde3);
							buscarA.setEnabled(true);
						}else {
							buscarA.dise�o(3,Colores.gris2,Colores.gris4);
							buscarA.setEnabled(false);
						}
					}else {
						buscarA.dise�o(3,Colores.gris2,Colores.gris4);
						buscarA.setEnabled(false);
						bA�oI.setText("");
					}
				}catch(Exception e1) {
					buscarA.dise�o(3,Colores.gris2,Colores.gris4);
					buscarA.setEnabled(false);
					bA�oI.setText("");
				}
			}
		});
		
		bA�oF.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char n = e.getKeyChar();
				try {
					Integer.parseInt(bA�oF.getText());
					if(Character.isDigit(n)) {
						if(!bA�oI.getText().equals("") && !bA�oF.getText().equals("")) {
							buscarA.dise�o(3,Colores.verde2,Colores.verde3);
							buscarA.setEnabled(true);
						}else {
							buscarA.dise�o(3,Colores.gris2,Colores.gris4);
							buscarA.setEnabled(false);
						}
					}else {
						buscarA.dise�o(3,Colores.gris2,Colores.gris4);
						buscarA.setEnabled(false);
						bA�oF.setText("");
					}
				}catch(Exception e1) {
					buscarA.dise�o(3,Colores.gris2,Colores.gris4);
					buscarA.setEnabled(false);
					bA�oF.setText("");
				}
			}
		});
		
		bAutor.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if(!bAutor.getText().equals("")) {
					buscarA.dise�o(3,Colores.verde2,Colores.verde3);
					buscarA.setEnabled(true);
				}else {
					buscarA.dise�o(3,Colores.gris2,Colores.gris4);
					buscarA.setEnabled(false);
				}
			}
		});
	}
	public void formMLibros(Libro libro) {
		tIsbn = libro.getIsbn();
		
		formModificar = new JFrame(libro.getA�o()+" - "+libro.getTitulo());
		formModificar.setLayout(null);
		formModificar.getContentPane().setBackground(Colores.blanco);
		formModificar.setSize(615,460);
		formModificar.setLocationRelativeTo(null);
		formModificar.setResizable(false);
		formModificar.setVisible(true);
		
		formModificar.add(new Etiqueta(50,30,335,30,"Detalles del Libro - " + libro.getIsbn(),Colores.morado1,16,false));
		formModificar.add(new Etiqueta(50,50,150,30,"Autor",Colores.morado3,14,false));
		formModificar.add(new Etiqueta(50,110,150,30,"T�tulo",Colores.morado3,14,false));
		formModificar.add(new Etiqueta(50,170,150,30,"Edici�n",Colores.morado3,14,false));
		formModificar.add(new Etiqueta(50,230,150,30,"Temas",Colores.morado3,14,false));
		formModificar.add(new Etiqueta(50,290,150,30,"Descripci�n",Colores.morado3,14,false));
		formModificar.add(new Etiqueta(235,50,150,30,"Copias a Agregar",Colores.morado3,14,false));
		formModificar.add(new Etiqueta(235,110,150,30,"Copias Disponibles",Colores.morado3,14,false));
		formModificar.add(new Etiqueta(235,170,150,30,"No. de Estanter�a",Colores.morado3,14,false));
		formModificar.add(new Etiqueta(235,230,150,30,"Fila de Estanter�a",Colores.morado3,14,false));
		
		vAutor = new Texto();
		vAutor.setBounds(50, 80, 150, 25);
		vAutor.setText(libro.getAutor());
		vAutor.setEditable(false);
		vAutor.setBackground(Colores.blanco);
		formModificar.add(vAutor);
		
		vTitulo = new Texto();
		vTitulo.setBounds(50, 140, 150, 25);
		vTitulo.setText(libro.getTitulo());
		vTitulo.setEditable(false);
		vTitulo.setBackground(Colores.blanco);
		formModificar.add(vTitulo);
		
		vEdicion = new Texto();
		vEdicion.setBounds(50, 200, 150, 25);
		vEdicion.setText(libro.getEdicion());
		vEdicion.setEditable(false);
		vEdicion.setBackground(Colores.blanco);
		formModificar.add(vEdicion);
		
		vTemas = new Texto();
		vTemas.setBounds(50, 260, 150, 25);
		vTemas.setText(libro.getTema());
		vTemas.setEditable(false);
		vTemas.setBackground(Colores.blanco);
		formModificar.add(vTemas);
		
		vDesc = new AreaTexto();
		vDesc.setLineWrap(true);
		vDesc.setText(libro.getDesc());
		vDesc.setEditable(false);
		
		JScrollPane scroll = new JScrollPane (vDesc,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setBounds(50,320,335,50);
		scroll.setBorder(BorderFactory.createLineBorder(Colores.bordeText,1));
		formModificar.add(scroll);
		
		vCopAg = new Texto();
		vCopAg.setBounds(235, 80, 150, 25);
		vCopAg.setText("0");
		vCopAg.setPlaceholder("Ingrese una Cantidad");
		vCopAg.setEditable(false);
		vCopAg.setBackground(Colores.blanco);
		formModificar.add(vCopAg);
		
		vCopDisp = new Texto();
		vCopDisp.setBounds(235, 140, 150, 25);
		vCopDisp.setText(String.valueOf(libro.getNumCopDisp()));
		vCopDisp.setEditable(false);
		vCopDisp.setBackground(Colores.blanco);
		formModificar.add(vCopDisp);
		
		vEstanteria = new Texto();
		vEstanteria.setBounds(235, 200, 150, 25);
		vEstanteria.setText(String.valueOf(libro.getNumEst()));
		vEstanteria.setEditable(false);
		vEstanteria.setBackground(Colores.blanco);
		formModificar.add(vEstanteria);
		
		vFila = new Texto();
		vFila.setBounds(235, 260, 150, 25);
		vFila.setText(String.valueOf(libro.getFila()));
		vFila.setEditable(false);
		vFila.setBackground(Colores.blanco);
		formModificar.add(vFila);
		
		botonModCan("Modificar",Colores.morado1,Colores.morado2);
		
		confirmar = new Boton("Actualizar");
		confirmar.ubicacion(435, 140, 130, 25);
		confirmar.texto(Colores.blanco, 15);
		confirmar.dise�o(3, Colores.verde1, Colores.verde2);
		confirmar.setVisible(false);
		confirmar.addActionListener(this);
		formModificar.add(confirmar);
	}
	public void tablaBusqueda(Libro[] libros) {
		String[] encabezado = {"No","T�tulo","Autor","A�o",""};
		int[] distribucion = {40,220,150,100,200};
		
		DefaultTableCellRenderer alineacion = new DefaultTableCellRenderer();
		alineacion.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		
		String[][] listLibros = {};
		if(libros != null) {
			listLibros = new String[libros.length][5];
			int i = 0;
			for(Libro lib : libros) {
				if(lib != null) {
					listLibros[i][0] = String.valueOf(i + 1);
					listLibros[i][1] = lib.getTitulo();
					listLibros[i][2] = lib.getAutor();
					listLibros[i][3] = String.valueOf(lib.getA�o());
					i++;
				}
			}
		}
		
		tabLibBus = new Tabla();
		tabLibBus.color(Colores.blanco,Colores.verde2,Colores.verde3);
		tabLibBus.header(encabezado,distribucion);
		tabLibBus.datos(listLibros,alineacion);
		tabLibBus.fuente(12);
		tabLibBus.locDim(550,350,200);
		tabLibBus.acciones("Consultar o Modificar",libros,"Libro");
		this.add(tabLibBus);
	}
	public void resetearTabla() {
		tabLib.setVisible(false);
		this.remove(tabLib);
		tablaLibros();
	}
	public void resetearTablaB(Libro[] libros) {
		tabLibBus.setVisible(false);
		this.remove(tabLibBus);
		tablaBusqueda(libros);
	}
	public void resetearBotonModCan(String texto,Color borde,Color fondo) {
		modCan.setVisible(false);
		formModificar.remove(modCan);
		botonModCan(texto,borde,fondo);
	}
	public void botonModCan(String texto,Color borde,Color fondo) {
		modCan = new Boton(texto);
		modCan.ubicacion(435, 80, 130, 25);
		modCan.texto(Colores.blanco, 15);
		modCan.dise�o(3, borde, fondo);
		modCan.addActionListener(this);
		formModificar.add(modCan);
	}
	public void actionPerformed(ActionEvent e) {
		String mensaje = "";
		if(e.getSource() == confirmar) {
			if(!vCopAg.getText().equals("")) {
				try {
					Integer.parseInt(vCopAg.getText());
					if(Integer.parseInt(vCopAg.getText()) > 0) {
						CtrlLibros.modCantLibro(tIsbn, Integer.parseInt(vCopAg.getText()));
						JOptionPane.showMessageDialog(null, "Cantidad Disponible Actualizada");
						formModificar.dispose();
						resetearTabla();
						resetearTablaB(null);
					}else {
						JOptionPane.showMessageDialog(null, "Ingrese una cantidad mayor que cero");
						vCopAg.setText("");
					}
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(null, "Cantidad Invalida");
					vCopAg.setText("");
				}
			}else {
				JOptionPane.showMessageDialog(null, "Ingrese una cantidad");
			}
		}
		else if(e.getSource() == modCan) {
			if(esModificable) {
				esModificable = false;
				confirmar.setVisible(false);
				
				resetearBotonModCan("Modificar",Colores.morado1,Colores.morado2);
				
				vCopAg.setText("0");
				vCopAg.setEditable(false);
			}else {
				esModificable = true;
				confirmar.setVisible(true);
				
				resetearBotonModCan("Cancelar",Colores.rojo1,Colores.rojo2);
				
				vCopAg.setText("");
				vCopAg.setEditable(true);
			}
		}
		else if(e.getSource() == buscarA) {
			if(!bA�oI.getText().equals("") && !bA�oF.getText().equals("") && bAutor.getText().equals("")) {
				Libro[] encontrado = CtrlLibros.bLibro(Integer.parseInt(bA�oI.getText()),Integer.parseInt(bA�oF.getText()));
				if(encontrado != null) {
					limpiarBusqueda();
					resetearTablaB(encontrado);
				}else {
					JOptionPane.showMessageDialog(null,"Sin resultados de b�squeda");
					limpiarBusqueda();
					resetearTablaB(null);
				}
			}
			else if(!bAutor.getText().equals("") && bA�oI.getText().equals("") && bA�oF.getText().equals("")) {
				Libro[] encontrado = CtrlLibros.bLibro(bAutor.getText());
				if(encontrado != null) {
					limpiarBusqueda();
					resetearTablaB(encontrado);
				}else {
					JOptionPane.showMessageDialog(null,"Sin resultados de b�squeda");
					limpiarBusqueda();
					resetearTablaB(null);
				}
			}
			else if(!bA�oI.getText().equals("") && !bA�oF.getText().equals("") && !bAutor.getText().equals("")) {
				Libro[] encontrado = CtrlLibros.bLibro(bAutor.getText(),Integer.parseInt(bA�oI.getText()),Integer.parseInt(bA�oF.getText()));
				if(encontrado != null) {
					limpiarBusqueda();
					resetearTablaB(encontrado);
				}else {
					JOptionPane.showMessageDialog(null,"Sin resultados de b�squeda");
					limpiarBusqueda();
					resetearTablaB(null);
				}
			}else resetearTablaB(null);
		}
		else if(e.getSource() == buscarS) {
			if(!bTitulo.getText().equals("")){
				Libro[] encontrado = CtrlLibros.bSLibro(bTitulo.getText());
				if(encontrado != null) {
					bTitulo.setText("");
					resetearTablaB(encontrado);
				}else {
					JOptionPane.showMessageDialog(null,"Sin resultados de b�squeda");
					bTitulo.setText("");
					resetearTablaB(null);
				}
			}else resetearTablaB(null);
		}
		else if(e.getSource() == crearLibro) {
			String isbn = nIsbn.getText();
			String aut = nAutor.getText();
			String a�o = nA�o.getText();
			String tit = nTitulo.getText();
			String edc = nEdicion.getText();
			String tem = nTemas.getText();
			String des = nDesc.getText();
			String cop = nCopias.getText();
			String est = nEstanteria.getText();
			String fil = nFila.getText();
			if(!isbn.equals("") && !aut.equals("") && !a�o.equals("") && !tit.equals("") && !edc.equals("") && !tem.equals("") && !des.equals("") && !cop.equals("") && !est.equals("") && !fil.equals("")) {
				try { Long.parseLong(isbn); }catch(Exception e1) { nIsbn.setText(""); mensaje += "ISBN no v�lido\n"; }
				try { Integer.parseInt(a�o); }catch(Exception e1) { nA�o.setText(""); mensaje += "A�o no v�lido\n"; }
				try { Integer.parseInt(cop); }catch(Exception e1) { nCopias.setText(""); mensaje += "N�mero de Copias no v�lido\n"; }
				try { Integer.parseInt(est); }catch(Exception e1) { nEstanteria.setText(""); mensaje += "N�mero de Estanter�a no v�lido\n"; }
				try { Integer.parseInt(fil); }catch(Exception e1) { nFila.setText(""); mensaje += "N�mero de Fila no v�lido"; }
				try {
					if(!CtrlLibros.verificarIsbn(isbn)) {
						if(Long.parseLong(isbn) > 0 && Integer.parseInt(a�o) > 0 && Integer.parseInt(cop) > 0 && Integer.parseInt(est) > 0 && Integer.parseInt(fil) > 0) {
							if(CtrlLibros.nuevoLibro(
									isbn,aut,Integer.parseInt(a�o),tit,edc,tem,des,Integer.parseInt(cop),
									Integer.parseInt(cop),Integer.parseInt(est),Integer.parseInt(fil))) {
								JOptionPane.showMessageDialog(null,"El libro fue registrado exitosamente");
								limpiarForm();
								resetearTabla();
							}else {
								JOptionPane.showMessageDialog(null,"La biblioteca alcanz� su m�xima capacidad");
							}
						}else {
							JOptionPane.showMessageDialog(null,"Solo se permiten cantidades mayores que cero");
						}
					}else {
						JOptionPane.showMessageDialog(null,"El ISBN ya existe en el sistema");
						nIsbn.setText("");
					}
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(null,mensaje);
				}
			}else {
				JOptionPane.showMessageDialog(null,"Todos los campos son obligatorios");
			}
		}
	}
	public void limpiarForm() {
		nIsbn.setText("");
		nAutor.setText("");
		nA�o.setText("");
		nTitulo.setText("");
		nEdicion.setText("");
		nTemas.setText("");
		nDesc.setText("");
		nCopias.setText("");
		nEstanteria.setText("");
		nFila.setText("");
	}
	public void limpiarBusqueda() {
		bA�oI.setText("");
		bA�oF.setText("");
		bAutor.setText("");
		buscarA.dise�o(3,Colores.gris2,Colores.gris4);
		buscarA.setEnabled(false);
	}
}