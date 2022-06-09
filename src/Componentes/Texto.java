package Componentes;
import java.awt.*;
import javax.swing.*;
import javax.swing.text.Document;
public class Texto extends JTextField{
	private static final long serialVersionUID = 1L;
	private String placeholder;
	
	public Texto() {
		this.setForeground(Colores.fuenteP);
		this.setBorder(BorderFactory.createLineBorder(Colores.bordeText));
	}
	public Texto(final Document pDoc,final String pText,final int pColumns){
        super(pDoc, pText, pColumns);
    }
    public Texto(final int pColumns) {
        super(pColumns);
    }
    public Texto(final String pText) {
        super(pText);
    }
    public Texto(final String pText, final int pColumns) {
        super(pText, pColumns);
    }
    public String getPlaceholder() {
        return placeholder;
    }
    protected void paintComponent(final Graphics pG) {
        super.paintComponent(pG);
        if (placeholder == null || placeholder.length() == 0 || getText().length() > 0) {
            return;
        }
        final Graphics2D g = (Graphics2D) pG;
        g.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(new Color(116,116,116));
        g.drawString(placeholder, getInsets().left, pG.getFontMetrics()
            .getMaxAscent() + getInsets().top);
    }
    public void setPlaceholder(final String s) {
        placeholder = s;
    }
}
