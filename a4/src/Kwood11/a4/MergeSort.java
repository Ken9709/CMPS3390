package Kwood11.a4;

import Kwood11.a3.Shape;

/**
 * Merge sort combines multithreadded sorted arrays into one
 * @author Kenneth Wood
 * @version 1.0
 */


public class MergeSort extends Thread{
    private Shape[] shapes1;
    private Shape[] shapes2;
    private Shape[] sortedShapes;

    /**
     * default constructor sets the shape arrays
     * @param s1 represents the first array
     * @param s2 represents the second array
     */
    public MergeSort(Shape[] s1, Shape[] s2){
        this.shapes1 =s1;
        this.shapes2 = s2;
        this.sortedShapes = new Shape[shapes1.length+s2.length];
    }

    /**
     * Override of the run function to allow for threads
     * to run
     */
    @Override
    public void run(){
        int i = 0; // current index of shapes1
        int j =0; // current index of shapes2
        int k=0; // current index of sortedShapes

        while (i<shapes1.length && j< shapes2.length){
            if(this.shapes1[i].getArea() <this.shapes2[j].getArea()){
                this.sortedShapes[k++] = this.shapes1[i++];
            } else {
                this.sortedShapes[k++] = this.shapes2[j++];
            }
        }

        while(i <this.shapes1.length){
            this.sortedShapes[k++] = this.shapes1[i++];
        }
        while (j<this.shapes2.length){
            this.sortedShapes[k++] = this.shapes2[j++];
        }
        System.out.println("Merge Thread Complete");
    }
    /**
     * function that returns the sorted shapes array
     * @return an array of merge sorted shapes
     */
    public Shape[] getSortedShapes() {
        return sortedShapes;
    }
}
