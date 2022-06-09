package Componentes;
import java.awt.*;
import javax.swing.*;
public class AreaTexto extends JTextArea{
	private static final long serialVersionUID = 1L;
	private String placeholder;
	
	public AreaTexto() {
		this.setForeground(Colores.fuenteP);
		this.setBorder(BorderFactory.createLineBorder(null,0));
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
