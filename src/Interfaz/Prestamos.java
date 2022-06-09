package Interfaz;
import static Prestamistas.CtrlPrestamistas.prestamistas;
import static Prestamos.CtrlPrestamos.prestamos;
import Prestamistas.*;
import Prestamos.*;
import Libros.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableCellRenderer;
import Componentes.*;
public class Prestamos extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	Texto nCui,nNombre,nApellido;
	Texto pIsbn,pCui;
	Texto dIsbn,dCui;
	Boton crearPres,verPres,verDev,canP,canD;
	Tabla tabPrestamistas,tabPrestamos;
	JFrame formHistorial;
	boolean esModPres = false,esModDev = false;
	public Prestamos() {
		this.setLayout(null);
		this.setBackground(Colores.blanco);
		
		formNPrestamista();
		formNPrestamo();
		formDLibro();
		tablaPrestamistas();
		tablaPrestamos();
	}
	public void formNPrestamista() {
		this.add(new Etiqueta(50,30,200,30,"Registro Prestamista",Colores.celeste1,18,false));
		this.add(new Etiqueta(570,30,300,30,"Prestamistas Registrados",Colores.celeste1,18,false));
		
		nCui = new Texto();
		nCui.setBounds(50,70,481,25);
		nCui.setPlaceholder("CUI");
		this.add(nCui);
		
		nNombre = new Texto();
		nNombre.setBounds(50,100,481,25);
		nNombre.setPlaceholder("Nombre");
		this.add(nNombre);
		
		nApellido = new Texto();
		nApellido.setBounds(50,130,481,25);
		nApellido.setPlaceholder("Apellido");
		this.add(nApellido);
		
		crearPres = new Boton("Crear Prestamista");
		crearPres.ubicacion(371,30,160,25);
		crearPres.texto(Colores.blanco,15);
		crearPres.diseño(3,Colores.verde2,Colores.verde3);
		crearPres.addActionListener(this);
		this.add(crearPres);
	}
	public void formNPrestamo() {
		this.add(new Etiqueta(50,298,200,30,"Préstamo",Colores.celeste1,18,false));
		this.add(new Etiqueta(570,298,200,30,"Préstamos Realizados",Colores.celeste1,18,false));
		
		pIsbn = new Texto();
		pIsbn.setBounds(50,338,481,25);
		pIsbn.setPlaceholder("ISBN");
		this.add(pIsbn);
		
		pCui = new Texto();
		pCui.setBounds(50,378,481,25);
		pCui.setPlaceholder("CUI");
		pCui.setVisible(false);
		this.add(pCui);
		
		canP = new Boton("Cancelar");
		canP.ubicacion(371,268,160,25);
		canP.texto(Colores.blanco,15);
		canP.diseño(3,Colores.rojo1,Colores.rojo2);
		canP.setVisible(false);
		canP.addActionListener(this);
		this.add(canP);
		
		botConfPres("Verificar ISBN",Colores.morado1,Colores.morado2);
	}
	public void formDLibro() {
		this.add(new Etiqueta(50,495,200,30,"Devolución de Libros",Colores.celeste1,18,false));
		
		dIsbn = new Texto();
		dIsbn.setBounds(50,535,481,25);
		dIsbn.setPlaceholder("ISBN");
		this.add(dIsbn);
		
		dCui = new Texto();
		dCui.setBounds(50,575,481,25);
		dCui.setPlaceholder("CUI");
		dCui.setVisible(false);
		this.add(dCui);
		
		canD = new Boton("Cancelar");
		canD.ubicacion(371,465,160,25);
		canD.texto(Colores.blanco,15);
		canD.diseño(3,Colores.rojo1,Colores.rojo2);
		canD.setVisible(false);
		canD.addActionListener(this);
		this.add(canD);
		
		botConfDev("Verificar ISBN",Colores.morado1,Colores.morado2);
	}
	public void tablaPrestamistas() {
		String[] encabezado = {"No","Nombre","Apellido","CUI",""};
		int[] distribucion = {40,121,120,105,95};
		
		DefaultTableCellRenderer alineacion = new DefaultTableCellRenderer();
		alineacion.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		
		int cantPrestamistas = 0;
		for(Prestamista prestamista : prestamistas) {
			if(prestamista != null) cantPrestamistas ++;
		}
		String[][] listPrestamistas = new String[cantPrestamistas][5];
		cantPrestamistas = 0;
		for(Prestamista prestamista : prestamistas) {
			if(prestamista != null) {
				listPrestamistas[cantPrestamistas][0] = String.valueOf(cantPrestamistas + 1);
				listPrestamistas[cantPrestamistas][1] = prestamista.getNombre();
				listPrestamistas[cantPrestamistas][2] = prestamista.getApellido();
				listPrestamistas[cantPrestamistas][3] = prestamista.getCui();
				cantPrestamistas ++;
			}
		}
		
		tabPrestamistas = new Tabla();
		tabPrestamistas.color(Colores.blanco,Colores.verde2,Colores.verde3);
		tabPrestamistas.header(encabezado,distribucion);
		tabPrestamistas.datos(listPrestamistas,alineacion);
		tabPrestamistas.fuente(12);
		tabPrestamistas.locDim(570,70,200);
		tabPrestamistas.acciones("Historial",prestamistas,"Prestamista");
		this.add(tabPrestamistas);
	}
	public void tablaPrestamos() {
		String[] encabezado = {"No","Prestamista","CUI","Libro","ISBN","Fecha Préstamo","Fecha Devolucion"};
		int[] distribucion = {40,107,105,125,104,110,120};
		
		DefaultTableCellRenderer alineacion = new DefaultTableCellRenderer();
		alineacion.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		
		int cantPrestamos = 0;
		for(Prestamo prestamo : prestamos) {
			if(prestamo != null) cantPrestamos ++;
		}
		String[][] listPrestamos = new String[cantPrestamos][7];
		cantPrestamos = 0;
		for(Prestamo prestamo : prestamos) {
			if(prestamo != null) {
				listPrestamos[cantPrestamos][0] = String.valueOf(cantPrestamos + 1);
				listPrestamos[cantPrestamos][1] = CtrlPrestamistas.busP(prestamo.getCui()).getNombre();
				listPrestamos[cantPrestamos][2] = prestamo.getCui();
				listPrestamos[cantPrestamos][3] = CtrlLibros.bIsbnLibro(prestamo.getIsbn()).getTitulo();
				listPrestamos[cantPrestamos][4] = prestamo.getIsbn();
				listPrestamos[cantPrestamos][5] = prestamo.getFechaP();
				listPrestamos[cantPrestamos][6] = "-";
				if(prestamo.getEstado()) {
					listPrestamos[cantPrestamos][6] = prestamo.getFechaD();
				}
				cantPrestamos ++;
			}
		}
		
		tabPrestamos = new Tabla();
		tabPrestamos.color(Colores.blanco,Colores.verde2,Colores.verde3);
		tabPrestamos.header(encabezado,distribucion);
		tabPrestamos.datos(listPrestamos,alineacion);
		tabPrestamos.fuente(12);
		tabPrestamos.locDim(570,338,262);
		this.add(tabPrestamos);
	}
	public void rTablaPrestamistas() {
		tabPrestamistas.setVisible(false);
		this.remove(tabPrestamistas);
		tablaPrestamistas();
	}
	public void rTablaPrestamos() {
		tabPrestamos.setVisible(false);
		this.remove(tabPrestamos);
		tablaPrestamos();
	}
	public void botConfPres(String texto,Color borde,Color fondo) {
		verPres = new Boton(texto);
		verPres.ubicacion(371,298,160,25);
		verPres.texto(Colores.blanco,15);
		verPres.diseño(3,borde,fondo);
		verPres.addActionListener(this);
		this.add(verPres);
	}
	public void botConfDev(String texto,Color borde,Color fondo) {
		verDev = new Boton(texto);
		verDev.ubicacion(371,495,160,25);
		verDev.texto(Colores.blanco,15);
		verDev.diseño(3,borde,fondo);
		verDev.addActionListener(this);
		this.add(verDev);
	}
	public void rBotConfPres(String texto,Color borde,Color fondo) {
		verPres.setVisible(false);
		this.remove(verPres);
		botConfPres(texto,borde,fondo);
	}
	public void rBotConfDev(String texto,Color borde,Color fondo) {
		verDev.setVisible(false);
		this.remove(verDev);
		botConfDev(texto,borde,fondo);
	}
	public void historial(Prestamista prestamista) {
		formHistorial = new JFrame(prestamista.getCui()+" - "+prestamista.getNombre()+" "+prestamista.getApellido());
		formHistorial.setLayout(null);
		formHistorial.getContentPane().setBackground(Colores.blanco);
		formHistorial.setSize(619,460);
		formHistorial.setLocationRelativeTo(null);
		formHistorial.setResizable(false);
		formHistorial.setVisible(true);
		
		formHistorial.add(new Etiqueta(50,30,335,30,"Historial de Préstamos",Colores.morado1,16,false));
		formHistorial.add(new Etiqueta(50,50,100,30,"CUI",Colores.morado3,16,false));
		formHistorial.add(new Etiqueta(240,50,100,30,"Nombre",Colores.morado3,16,false));
		formHistorial.add(new Etiqueta(430,50,100,30,"Apellido",Colores.morado3,16,false));
		
		Texto cui = new Texto();
		cui.setBounds(50,80,120,25);
		cui.setText(prestamista.getCui());
		cui.setEditable(false);
		cui.setBackground(Colores.blanco);
		formHistorial.add(cui);
		
		Texto nombre = new Texto();
		nombre.setBounds(240,80,120,25);
		nombre.setText(prestamista.getNombre());
		nombre.setEditable(false);
		nombre.setBackground(Colores.blanco);
		formHistorial.add(nombre);
		
		Texto apellido = new Texto();
		apellido.setBounds(430,80,120,25);
		apellido.setText(prestamista.getApellido());
		apellido.setEditable(false);
		apellido.setBackground(Colores.blanco);
		formHistorial.add(apellido);
		
		String[] encabezado = {"No","ISBN","Título","Fecha Préstamo","Fecha Devolución"};
		int[] distribucion = {40,104,126,110,120};
		
		DefaultTableCellRenderer alineacion = new DefaultTableCellRenderer();
		alineacion.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		
		Prestamo[] historial = CtrlPrestamos.historial(prestamista.getCui());
		String[][] histPrestamos = {};
		if(historial != null) {
			histPrestamos = new String[historial.length][5];
			for(int i = 0; i < historial.length; i++) {
				histPrestamos[i][0] = String.valueOf(i + 1);
				histPrestamos[i][1] = historial[i].getIsbn();
				histPrestamos[i][2] = CtrlLibros.bIsbnLibro(historial[i].getIsbn()).getTitulo();
				histPrestamos[i][3] = historial[i].getFechaP();
				histPrestamos[i][4] = "-";
				if(historial[i].getEstado()) {
					histPrestamos[i][4] = historial[i].getFechaD();
				}
			}
		}
		
		Tabla tabHistorial = new Tabla();
		tabHistorial.color(Colores.blanco,Colores.morado1,Colores.morado2);
		tabHistorial.header(encabezado,distribucion);
		tabHistorial.datos(histPrestamos,alineacion);
		tabHistorial.fuente(12);
		tabHistorial.locDim(50,155,200);
		formHistorial.add(tabHistorial);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == verDev) {
			if(esModDev) {
				if(!dCui.getText().equals("")) {
					if(CtrlPrestamistas.verificarPres(dCui.getText())) {
						if(CtrlPrestamos.devLibro(dIsbn.getText(),dCui.getText(),DateTimeFormatter.ofPattern("dd/MM/yyyy").format(LocalDateTime.now()))) {
							esModDev = false;
							dCui.setVisible(false);
							canD.setVisible(false);
							
							rBotConfDev("Verificar ISBN",Colores.morado1,Colores.morado2);
							
							JOptionPane.showMessageDialog(null,"Libro devuelto exitosamente");
							rTablaPrestamos();
							dIsbn.setText("");
							dCui.setText("");
						}else {
							JOptionPane.showMessageDialog(null,"El prestamista no ha prestado este libro");
							dCui.setText("");
						}
					}else {
						JOptionPane.showMessageDialog(null,"El CUI no coincide con ninguno en el sistema");
						dCui.setText("");
					}
				}else {
					JOptionPane.showMessageDialog(null,"Para verificar al prestamista debe ingresar el CUI");
				}
			}else {
				if(!dIsbn.getText().equals("")) {
					if(CtrlLibros.verificarIsbn(dIsbn.getText())) {
						esModDev = true;
						dCui.setVisible(true);
						canD.setVisible(true);
						
						rBotConfDev("Devolver",Colores.verde2,Colores.verde3);
					}else {
						JOptionPane.showMessageDialog(null,"El ISBN no coincide con ninguno en el sistema");
						dIsbn.setText("");
					}
				}else {
					JOptionPane.showMessageDialog(null,"Para verificar el libro debe ingresar el ISBN");
				}
			}
		}
		else if(e.getSource() == verPres) {
			if(esModPres) {
				if(!pCui.getText().equals("")) {
					if(CtrlPrestamistas.verificarPres(pCui.getText())) {
						Prestamo pend = CtrlPrestamos.pendLibro(pCui.getText());
						if(pend == null) {
							esModPres = false;
							pCui.setVisible(false);
							canP.setVisible(false);
							
							rBotConfPres("Verificar ISBN",Colores.morado1,Colores.morado2);
							
							CtrlPrestamos.nuevoPrestamo(pIsbn.getText(),pCui.getText(),DateTimeFormatter.ofPattern("dd/MM/yyyy").format(LocalDateTime.now()),false);
							JOptionPane.showMessageDialog(null,"Libro prestado exitosamente");
							rTablaPrestamos();
							pIsbn.setText("");
							pCui.setText("");
						}else {
							Libro libPend = CtrlLibros.bIsbnLibro(pend.getIsbn());
							JOptionPane.showMessageDialog(null,
									"Libro Pendiente\nTitulo: "+libPend.getTitulo()+
									"\nISBN: "+libPend.getIsbn()+
									"\nFecha de Préstamo: "+pend.getFechaP());
							pCui.setText("");
						}
					}else {
						JOptionPane.showMessageDialog(null,"El CUI no coincide con ninguno en el sistema");
						pCui.setText("");
					}
				}else {
					JOptionPane.showMessageDialog(null,"Para verificar al prestamista debe ingresar el CUI");
				}
			}else {
				if(!pIsbn.getText().equals("")) {
					if(CtrlLibros.verificarIsbn(pIsbn.getText())) {
						if(CtrlPrestamos.dispLibro(pIsbn.getText())) {
							esModPres = true;
							pCui.setVisible(true);
							canP.setVisible(true);
							
							rBotConfPres("Prestar",Colores.verde2,Colores.verde3);
						}else {
							JOptionPane.showMessageDialog(null,"No hay libros disponibles");
							pIsbn.setText("");
						}
					}else {
						JOptionPane.showMessageDialog(null,"El ISBN no coincide con ninguno en el sistema");
						pIsbn.setText("");
					}
				}else {
					JOptionPane.showMessageDialog(null,"Para verificar el libro debe ingresar el ISBN");
				}
			}
		}
		else if(e.getSource() == canD) {
			dIsbn.setText("");
			dCui.setText("");
			dCui.setVisible(false);
			canD.setVisible(false);
			
			rBotConfDev("Verificar ISBN",Colores.morado1,Colores.morado2);
			
			esModDev = false;
		}
		else if(e.getSource() == canP) {
			pIsbn.setText("");
			pCui.setText("");
			pCui.setVisible(false);
			canP.setVisible(false);
			
			rBotConfPres("Verificar ISBN",Colores.morado1,Colores.morado2);
			
			esModPres = false;
		}
		else if(e.getSource() == crearPres) {
			if(!nCui.getText().equals("") && !nNombre.getText().equals("") && !nApellido.getText().equals("")) {
				try {
					Long.parseLong(nCui.getText());
					if(!CtrlPrestamistas.verificarPres(nCui.getText())) {
						if(CtrlPrestamistas.nuevoPres(nNombre.getText(),nApellido.getText(),nCui.getText())) {
							JOptionPane.showMessageDialog(null,"El prestamistas fue registrado exitosamente");
							rTablaPrestamistas();
							nCui.setText("");
							nNombre.setText("");
							nApellido.setText("");
						}else {
							JOptionPane.showMessageDialog(null,"El sistema alcanzó su máxima capacidad");
							nCui.setText("");
							nNombre.setText("");
							nApellido.setText("");
						}
					}else {
						JOptionPane.showMessageDialog(null,"El CUI ingresado ya existe en el sistema");
						nCui.setText("");
					}
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(null,"CUI inválido");
					nCui.setText("");
				}
			}else {
				JOptionPane.showMessageDialog(null,"Todos los campos son obligatorios");
			}
		}
	}
}