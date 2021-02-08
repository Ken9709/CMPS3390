package Kwood11.a3;
import java.util.Scanner;
import java.util.Random;


/**
 * Main entry point for A3
 * @author Kenneth Wood
 * @version 1.0
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("How many shapes should be made?");
        Scanner scan = new Scanner(System.in);
        int amount = scan.nextInt();
        Shape[] shapes = new Shape[amount];
        Random ran = new Random();
        for (int i = 0; i < amount; i++) {
            int x = ran.nextInt(5);
            float y = ran.nextInt(21);
            float z = ran.nextInt(21);
            System.out.println(x);
            switch (x) {
                case 0:
                    Circle circle = new Circle(y);
                    shapes[i] = circle;
                    break;
                case 1:
                    Oval oval = new Oval(y, z);
                    shapes[i] = oval;
                    break;
                case 2:
                    Square square = new Square(y);
                    shapes[i] = square;
                    break;
                case 3:
                    Rectangle rectangle = new Rectangle(y, z);
                    shapes[i] = rectangle;
                    break;
                case 4:
                    RightTriangle rightTriangle = new RightTriangle(y, z);
                    shapes[i] = rightTriangle;
                    break;
            }
        }
        for (int i=0; i < amount; i++){
            System.out.println(shapes[i]);
        }
    }
}





