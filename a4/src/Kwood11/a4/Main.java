package Kwood11.a4;

import Kwood11.a3.*;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InterruptedException{
	// write your code here
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        System.out.print("Do you want  [S]ingle Thread or [D]ual Thread?");
        char selection = scanner.next().charAt(0);

        System.out.print("How many shapes do you want to sort? ");
            int count = scanner.nextInt();
            Shape[] shapes = new Shape[count];

            for (int i=0; i<count; i++){
                int t = random.nextInt(5);
                switch(t){
                    case 0:
                        shapes[i] = new Circle(random.nextFloat()*20f);
                        break;
                    case 1:
                        shapes[i] = new Oval(random.nextFloat()*20f, random.nextFloat()*20f);
                        break;
                    case 2:
                        shapes[i] = new Square(random.nextFloat()*20f);
                        break;
                    case 3:
                        shapes[i] = new Rectangle(random.nextFloat()* 20f, random.nextFloat()*20f);
                        break;
                    case 4:
                        shapes[i] = new RightTriangle(random.nextFloat()* 20f, random.nextFloat()*20f);
                        break;
                }
            }
        switch(selection){
            case 's':
            case 'S':
                singleSort(shapes);
                break;
            case 'd':
            case 'D':
                dualSort(shapes);
                break;
        }
    }

    private static void singleSort(Shape[] shapes) throws InterruptedException {
        ThreadSort threadSort = new ThreadSort(shapes, 0, shapes.length);
        long startTime = System.nanoTime();
        threadSort.start();
        threadSort.join();
        long endTime = System.nanoTime();

        long duration =(endTime- startTime) / 1000000;

        for(Shape s : threadSort.gettShapes()){
            System.out.println(s);
        }
        System.out.println("Single Thread Sort: " + duration);
    }

    private static void dualSort(Shape[] shapes) throws InterruptedException {
        int mid =Math.round(shapes.length/2);

        ThreadSort t1 = new ThreadSort(shapes, 0, mid);
        ThreadSort t2 = new ThreadSort(shapes, mid, shapes.length);
        t1.start();
        t2.start();

        t1.join();
        t2.join();

        MergeSort m = new MergeSort(t1.gettShapes(), t2.gettShapes());
        m.start();
        m.join();

        for(Shape s :m.getSortedShapes()){
            System.out.println(s);
        }

    }
}
