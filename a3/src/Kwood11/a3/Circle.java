package Kwood11.a3;

/**
 * Circle class extends shape and represents a perfect circle
 * @author Kenneth Wood
 * @version 1.0
 */
public class Circle extends Shape {
    private float radius;

    /**
     * default constructor sets the type to circle
     */
    public Circle() {
        super();
        this.setType(Type.CIRCLE);
    }

    /**
     * Override constructor sets the type to circle and the radius
     * to a random float
     * @param radius float representing the radius of a circle
     */
    public Circle(float radius){
        this.setType(Type.CIRCLE);
        this.radius = radius;
    }

    /**
     * Gets the radius of a circle
     * @return float representing the radius of a circle
     */
    public float getRadius() {
        return radius;
    }

    /**
     * Sets the radius of a circle to a random float
     * @param radius float representing the radius of a circle
     */
    public void setRadius(float radius) {
        this.radius = radius;
    }

    /**
     * Function to return the area of a circle
     * @return double representing the area of a circle
     */
    public double getArea(){
        return (3.14* this.radius*this.radius);
    }

    /**
     * Override of the toString function to print shapes nicely
     * @return String that represents all properties of a shape
     */
    @Override
    public String toString(){
        return String.format("%s Area: %-6.2f | Radius: %-10f|", super.toString(),
                this.getArea(), this.radius);
    }
}
