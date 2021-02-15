package Kwood11.a3;

/**
 * Shape class is the base class for all other Shape types
 * @author Kenneth Wood
 * @version 1.0
 */
public class Shape {
    private Type type;
    private float area;

    /**
     * Default constructor sets the type to shape
     */
    public Shape() {
        this.type = Type.SHAPE;
    }

    /**
     * Gets the type of the shape
     * @return Type enum of the shape
     */
    public Type getType() {
        return this.type;
    }

    /**
     * sets the type of the shape
     * @param type Type enum of the shape
     */
    public void setType(Type type) {
        this.type = type;
    }

    /**
     * Override of the toString function to print
     * shapes nicely
     * @return String that represents the object
     */

    public double getArea(){
        return 0.0;
    }

    @Override
    public String toString(){
        return String.format("Type: %-14s|", this.type);
    }
}
