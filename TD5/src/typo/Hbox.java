package typo;

import java.awt.Graphics;

public class Hbox extends Group{

    @Override
    public String toString() {
        return String.format("Hbox" + super.toString());
    }

    @Override
    public void add(Box b){
        super.add(b);
        this.width += b.getWidth();
        this.descent = Math.max(this.descent, b.getDescent());
        this.ascent = Math.max(this.ascent, b.getAscent());
        this.stretchingCapacity += b.getStretchingCapacity();
    }

    @Override
    public boolean doDraw(Graphics graph, double x, double y, double w) {
        double mw = this.getWidth();
        if(mw > w){
            for(Box b : list){
                b.doDraw(graph, x, y, w);
            }
            return false;
        }else{
            for(Box b : list){
                double stretchRatio = b.getStretchingCapacity()/this.getStretchingCapacity();
                double space = w*stretchRatio;
                b.doDraw(graph, x, y, space);
            }
            return true;
        }
    }
    
}
