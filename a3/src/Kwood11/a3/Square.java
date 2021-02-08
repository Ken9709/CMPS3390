package Kwood11.a3;

/**
 * Square class represents a square with all equal sides
 * and extends the shape base class
 * @author Kenneth Wood
 * @version 1.0
 */
public class Square extends Shape{
    private float width;

    /**
     * Default constructor sets the type to shape
     */
    public Square() {
        super();
        this.setType(Type.SQUARE);
        this.setWidth(0.0f);
    }

    /**
     * Override constructor sets the type and width
     * @param width float representing the width of the square
     */
    public Square(float width){
        this.setType(Type.SQUARE);
        this.width = width;

    }

    /**
     * Gets the width of a square
     * @return float that represents the width of the square
     */
    public float getWidth() {
        return width;
    }

    /**
     * sets the width of a square
     * @param width float that represents the width of the square
     */
    public void setWidth(float width) {
        this.width = width >= 0 ? width : 0;
    }

    /**
     * function that returns the area of a square
     * @return double that represents the area of a square
     */
    public double getArea(){
        return this.width *2;
    }

    /**
     * Override of the toString function to print
     * shapes nicely
     * @return String that represents all properties of a shape
     */
    @Override
    public String toString(){
        return String.format("%s Area: %-6.2f | Width: %-10f|", super.toString(),
                 this.getArea(),this.width);
    }
}
