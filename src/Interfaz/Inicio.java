package Interfaz;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import Componentes.*;
public class Inicio extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	Boton libros,prestamos,bloquear,desbloquear;
	JPanel bloqueo,sistema,lib,prest;
	Texto frase;
	public Libros gLibros;
	public Prestamos gPrestamos;
	Color menu1 = Colores.celeste1;
	Color menu2 = Colores.celeste2;
	public Inicio() {
		super("Biblioteca");
		this.setLayout(null);
		this.setBounds(-10,0,1390,760);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setBackground(Colores.blanco);
		
		sistemaBloqueado();
	}
	public void sistemaBloqueado() {
		bloqueo = new JPanel();
		bloqueo.setLayout(null);
		bloqueo.setBounds(0,0,this.getWidth(),this.getHeight());
		bloqueo.setBackground(Colores.blanco);
		this.add(bloqueo);
		
		bloqueo.add(new Etiqueta(0,200,this.getWidth(),30,"Biblioteca",Colores.naranja1,30,true));
		
		frase = new Texto();
		frase.setSize(480,25);
		frase.setLocation((bloqueo.getWidth() - frase.getWidth()) / 2,(bloqueo.getHeight() - 8*frase.getHeight()) / 2);
		frase.setPlaceholder("Ingrese la Frase Secreta");
		bloqueo.add(frase);
		
		desbloquear = new Boton("Desbloquear");
		desbloquear.ubicacion(620,350,150,35);
		desbloquear.texto(Colores.blanco,17);
		desbloquear.diseño(3,Colores.naranja2,Colores.naranja3);
		desbloquear.addActionListener(this);
		bloqueo.add(desbloquear);
		
		JPanel marco = new JPanel();
		marco.setLayout(null);
		marco.setBounds(5,5,1355,703);
		marco.setBackground(null);
		marco.setBorder(BorderFactory.createLineBorder(Colores.naranja2,3));
		bloqueo.add(marco);
	}
	public void sistema1() {
		sistema();
		
		gestionLibros();
		ventana();
		menu();
	}
	public void sistema2() {
		sistema();
		
		gestionPrestamos();
		ventana();
		menu();
	}
	public void sistema() {
		sistema = new JPanel();
		sistema.setLayout(null);
		sistema.setBounds(0,0,this.getWidth(),this.getHeight());
		sistema.setBackground(Colores.blanco);
		this.add(sistema);
		
		bloquear = new Boton("Bloquear");
		bloquear.ubicacion(1230,5,130,30);
		bloquear.texto(Colores.blanco,15);
		bloquear.diseño(3,Colores.rojo1,Colores.rojo2);
		bloquear.addActionListener(this);
		sistema.add(bloquear);
	}
	public void gestionLibros() {
		gLibros = new Libros();
		gLibros.setBounds(8,41,1349,664);
		sistema.add(gLibros);
	}
	public void gestionPrestamos() {
		gPrestamos = new Prestamos();
		gPrestamos.setBounds(8,41,1349,664);
		sistema.add(gPrestamos);
	}
	public void ventana() {
		lib = new JPanel();
		lib.setLayout(null);
		lib.setBounds(8,38,130,3);
		lib.setBackground(Colores.blanco);
		sistema.add(lib);
		
		prest = new JPanel();
		prest.setLayout(null);
		prest.setBounds(141,38,130,3);
		prest.setBackground(Colores.blanco);
		prest.setVisible(false);
		sistema.add(prest);
		
		JPanel marco = new JPanel();
		marco.setLayout(null);
		marco.setBounds(5,38,1355,670);
		marco.setBackground(null);
		marco.setBorder(BorderFactory.createLineBorder(menu1,3));
		sistema.add(marco);
	}
	public void menu() {
		JPanel menu = new JPanel();
		menu.setLayout(null);
		menu.setBounds(5,5,269,36);
		menu.setBackground(menu1);
		sistema.add(menu);
		
		libros = new Boton("Libros");
		libros.ubicacion(3,3,130,30);
		libros.texto(menu1,15);
		libros.diseño(0,null,Colores.blanco);
		libros.setEnabled(false);
		libros.addActionListener(this);
		menu.add(libros);
		
		prestamos = new Boton("Préstamos");
		prestamos.ubicacion(136,3,130,30);
		prestamos.texto(Colores.blanco,15);
		prestamos.diseño(0,null,menu2);
		prestamos.addActionListener(this);
		menu.add(prestamos);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == libros) {
			sistema.setVisible(false);
			this.remove(sistema);
			sistema1();
			
			libros.texto(menu1,15);
			libros.diseño(0,null,Colores.blanco);
			libros.setEnabled(false);
			
			prestamos.texto(Colores.blanco,15);
			prestamos.diseño(0,null,menu2);
			prestamos.setEnabled(true);
			
			lib.setVisible(true);
			prest.setVisible(false);
		}
		else if(e.getSource() == prestamos) {
			sistema.setVisible(false);
			this.remove(sistema);
			sistema2();
			
			libros.texto(Colores.blanco,15);
			libros.diseño(0,null,menu2);
			libros.setEnabled(true);
			
			prestamos.texto(menu1,15);
			prestamos.diseño(0,null,Colores.blanco);
			prestamos.setEnabled(false);
			
			lib.setVisible(false);
			prest.setVisible(true);
		}
		else if(e.getSource() == bloquear) {
			sistema.setVisible(false);
			this.remove(sistema);
			sistemaBloqueado();
		}
		else if(e.getSource() == desbloquear) {
			if(!frase.getText().equals("")) {
				if(frase.getText().equalsIgnoreCase("Primer Proyecto")) {
					bloqueo.setVisible(false);
					this.remove(bloqueo);
					sistema1();
				}else {
					JOptionPane.showMessageDialog(null,"La frase no es correcta");
					frase.setText("");
				}
			}else {
				JOptionPane.showMessageDialog(null,"Debe ingresar la frase secreta");
			}
		}
	}
}