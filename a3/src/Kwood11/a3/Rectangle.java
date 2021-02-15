package Kwood11.a3;

/**
 * Rectangle class represents a rectangle with different width
 * and height, extends the square class
 * @author Kenneth Wood
 * @version 1.0
 */
public class Rectangle extends Square{
    private float height;

    /**
     * Default constructor sets the type to shape
     */
    public Rectangle() {
        super();
        this.setType(Type.RECTANGLE);
    }

    /**
     * Override constructor sets type, width, and height
     * @param width float representing the width of the rectangle
     * @param height float representing the height of the rectangle
     */
    public Rectangle(float width, float height){
        this.setType(Type.RECTANGLE);
        this.setWidth(width);
        this.height = height;
    }

    /**
     * Gets the Height of a rectangle
     * @return float representing the height of a rectangle
     */
    public float getHeight() {
        return height;
    }

    /**
     * Sets the height of the rectangle
     * @param height float represneting the height of a rectangle
     */
    public void setHeight(float height) {
        this.height = height >= 0 ? height : 0;
    }

    /**
     * Override Function that returns the area of a rectangle
     * @return double that represents the area of a rectangle
     */
    @Override
    public double getArea(){
        return this.height*this.getWidth();
    }

    /**
     * Override of the toString function to print shapes nicely
     * @return String that represents all properties of a shape
     */
    @Override
    public String toString(){
        return String.format("%s Height: %-6.2f", super.toString(), this.height);
    }
}
