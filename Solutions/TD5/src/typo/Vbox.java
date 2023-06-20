package typo;
import java.awt.Graphics;

public class Vbox extends Group{

    double lineSkip;
    boolean firstBox;

    public Vbox(double lineSkip){
        this.lineSkip = lineSkip;
        this.firstBox = true;
    }
    
    @Override
    public String toString() {
        return String.format("Vbox" + super.toString());
    }

    @Override
    public void add(Box b){
        super.add(b);
        this.width = Math.max(this.width, b.getWidth());
        this.stretchingCapacity = Math.max(this.stretchingCapacity, b.getStretchingCapacity());
        this.ascent += this.descent + b.getAscent();
        if(!firstBox){
            this.ascent += this.lineSkip;
        }else{
            firstBox = false;
        }
        this.descent = b.getDescent();
    }

    @Override
    public boolean doDraw(Graphics graph, double x, double y, double w) {
        double curY = y;
        for(Box b : list){
            b.doDraw(graph, x, curY, w);
            curY += b.getAscent() + b.getDescent() + this.lineSkip;
        }
        return true;
    }

}
