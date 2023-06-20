package typo;

import java.awt.Graphics;
import java.util.LinkedList;

public abstract class Group extends Box{

    protected double ascent, descent, width, stretchingCapacity;

    protected final LinkedList<Box> list = new LinkedList<Box>();

    public void add(Box b){
        this.list.addLast(b);
    }

    @Override
    public double getWidth() {
        return this.width;
    }

    @Override
    public double getAscent() {
        return this.ascent;
    }

    @Override
    public double getDescent() {
        return this.descent;
    }

    @Override
    public double getStretchingCapacity() {
        return this.stretchingCapacity;
    }

    @Override
    public String toString() {
        String groupString = String.format("[w=%g, a=%g, d=%g, sC=%g]{",
            this.getWidth(), this.getAscent(),
            this.getDescent(), this.getStretchingCapacity());
        for(Box b : this.list){
            String boxString = b.toString();
            groupString = groupString.concat("\n\t" + boxString + ",");
        }
        groupString = groupString.concat("\n}");
        return groupString;
    }

    public abstract boolean doDraw(Graphics graph, double x, double y, double w);
    
}
