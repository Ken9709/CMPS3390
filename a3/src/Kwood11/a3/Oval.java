package Kwood11.a3;

/**
 * Oval class that extends Circle and adds a
 * second radius to represent an oval
 * @author Kenneth Wood
 * @version 1.0
 */
public class Oval extends Circle{
    private float radius2;

    /**
     * default constructor sets the type to circle
     */
    public Oval() {
        super();
        this.setType(Type.OVAL);
    }

    /**
     * Override constructor that sets type, radius, and radius2
     * @param radius float that represents the first radius of an oval
     * @param radius2 float that represents the second radius of an oval
     */
    public Oval(float radius, float radius2) {
        this.setType(Type.OVAL);
        this.setRadius(radius);
        this.radius2 = radius2;
    }

    /**
     * gets the second radius of the oval
     * @return float that represents the second radius of the oval
     */
    public float getRadius2() {
        return radius2;
    }

    /**
     * Sets the second radius of the oval
     * @param radius2 float that represents the second radius of the oval
     */
    public void setRadius2(float radius2) {
        this.radius2 = radius2;
    }

    /**
     * Override of the circle's getArea. Takes into account the second radius
     * in calculation
     * @return double that represents the oval's area
     */
    @Override
    public double getArea() {
        return (this.getRadius() * this.radius2 * 3.14);
    }

    /**
     * Override of the toString function to print shapes nicely
     * @return String that represents all properties of a shape
     */
    @Override
    public String toString() {
        return String.format("%s Radius2: %-6.2f", super.toString(), this.radius2);
    }
}
