package Kwood11.a3;

/**
 *RightTriangle class represents right triangles with sides with a width and height
 * that may be the same or different, and extends the Rectangle class
 * @author Kenneth Wood
 * @version 1.0
 */
public class RightTriangle extends Rectangle{

    /**
     * Default constructor sets type and height
     */
    public RightTriangle() {
        super();
        this.setType(Type.RIGHT_TRIANGLE);
    }

    /**
     * Override constructor sets type, width, and height
     * @param width float representing the width of the right triangle
     * @param height float representing the height of the right triangle
     */
    public RightTriangle(float width, float height){
        this.setType(Type.RIGHT_TRIANGLE);
        this.setWidth(width);
        this.setHeight(height);
    }

    /**
     * Override ges the area of the right triangle
     * @return double representing the area of the right triangle
     */
    @Override
    public double getArea(){
        return (this.getHeight()*this.getWidth())/2;
    }
}
