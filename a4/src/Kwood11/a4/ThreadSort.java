package Kwood11.a4;

import Kwood11.a3.Shape;

/**
 * Thread sort creates as many threads as the user asks for
 * and sorts the arrays based on that amount
 * @author Kenneth Wood
 * @version 1.0
 */

public class ThreadSort extends Thread{
    private Shape[] tShapes;

    /**
     * default constructor creates the shape array and
     * bounds to split it by
     * @param shapes
     * @param lowBounds
     * @param upperBounds
     */
    public ThreadSort(Shape[] shapes, int lowBounds, int upperBounds) {
        this.tShapes = new Shape[upperBounds -lowBounds];

        System.arraycopy(shapes, lowBounds, this.tShapes, 0, (upperBounds-lowBounds));
    }
    /**
     * Override of the run function to allow for threads
     * to run
     */
    @Override
    public void run(){
        System.out.println("Thread started");
        int n = this.tShapes.length;
        Shape tmp;
        for(int i=0; i<n; i++){
            for(int j=1; j<n; j++){
                if (this.tShapes[j - 1].getArea() > this.tShapes[j].getArea()) {
                    // Swap
                    tmp = this.tShapes[j-1];
                    this.tShapes[j-1] = this.tShapes[j];
                    this.tShapes[j] =tmp;
                }
            }
        }
        System.out.println("Thread complete");
    }

    /**
     * getter function to return the shape array
     * @return tshapes a sorted array
     */
    public Shape[] gettShapes() {
        return tShapes;
    }
}
