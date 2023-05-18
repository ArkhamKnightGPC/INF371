package typo;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;

public class Glyph extends Box{
    final private static FontRenderContext frc 
    = new FontRenderContext(null, false, false);
    final private Font font;
    final private char[] chars;
    final private Rectangle2D bounds;

    public Glyph(Font font, char c){
        this.font = font;
        this.chars = new char[1];
        this.chars[0] = c;
        TextLayout layout = new TextLayout("" + chars[0], font, frc);
        this.bounds = layout.getBounds();
    }

    @Override
    public double getStretchingCapacity(){
        return 0;
    }

    @Override
    public double getWidth(){
        return this.bounds.getWidth();
    }

    @Override
    public double getAscent(){
        return -this.bounds.getY();
    }

    @Override
    public double getDescent(){
        return this.bounds.getHeight() + bounds.getY();
    }

    @Override
    public String toString() {
        return String.format("Glyph(%s)" + super.toString(), chars[0]);
    }

    @Override
    public boolean doDraw(Graphics graph, double x, double y, double w) {
        graph.setFont(font);
        double coordX = x - bounds.getX();
        double coordY = y - bounds.getY();
        graph.drawChars(chars, 0, 0, (int)coordX, (int)coordY);
        return false;
    }
}
