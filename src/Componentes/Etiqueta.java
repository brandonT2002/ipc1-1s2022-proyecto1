package Componentes;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
public class Etiqueta extends JLabel {
	private static final long serialVersionUID = 1L;
	public Etiqueta(int x,int y,int w,int h,String texto,Color colorF,int fuente,boolean centrado) {
		this.setText(texto);
		this.setForeground(colorF);
		this.setBounds(x,y,w,h);
		this.setFont(new Font("Tahoma",Font.BOLD,fuente));
		if(centrado) this.setHorizontalAlignment(JLabel.CENTER);
		this.setVerticalAlignment(JLabel.CENTER);
	}
}