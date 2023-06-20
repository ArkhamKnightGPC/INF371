package typo;

import java.awt.Graphics;

public class Space extends Box{

    private double width, stretchingCapacity;

    public Space(double width, double stretchingCapacity){
        this.width = width;
        this.stretchingCapacity = stretchingCapacity;
    }

    @Override
    public double getWidth() {
        return this.width;
    }

    @Override
    public double getAscent() {
        return 0;
    }

    @Override
    public double getDescent() {
        return 0;
    }

    @Override
    public String toString() {
        return String.format("Space" + super.toString());
    }

    @Override
    public double getStretchingCapacity() {
        return this.stretchingCapacity;
    }

    @Override
    public boolean doDraw(Graphics graph, double x, double y, double w) {
        return true;
    }
    
}
