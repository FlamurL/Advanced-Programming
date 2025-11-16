import java.util.Scanner;
import java.io.*;
import java.util.*;

public class ShapesTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Canvas canvas = new Canvas();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(" ");
            int type = Integer.parseInt(parts[0]);
            String id = parts[1];
            if (type == 1) {
                Color color = Color.valueOf(parts[2]);
                float radius = Float.parseFloat(parts[3]);
                canvas.add(id, color, radius);
            } else if (type == 2) {
                Color color = Color.valueOf(parts[2]);
                float width = Float.parseFloat(parts[3]);
                float height = Float.parseFloat(parts[4]);
                canvas.add(id, color, width, height);
            } else if (type == 3) {
                float scaleFactor = Float.parseFloat(parts[2]);
                System.out.println("ORIGNAL:");
                System.out.print(canvas);
                canvas.scale(id, scaleFactor);
                System.out.printf("AFTER SCALING: %s %.2f\n", id, scaleFactor);
                System.out.print(canvas);
            }

        }
    }
}

enum Color {
    RED, GREEN, BLUE
}
interface Scalable {
    void scale(float scaleFactor);
}

interface Stackable {
    float weight(); // area
}

abstract class Shape implements Scalable, Stackable {
    String id;
    Color color;

    public Shape(String id, Color color) {
        this.id = id;
        this.color = color;
    }

    public String getId() {
        return id;
    }

    abstract String typeLetter();  // "C" или "R"

    @Override
    public String toString() {
        return String.format(
                "%s: %5s %10s %10.2f",
                typeLetter(),
                id,
                color,
                weight()
        );
    }

}


class Circle extends Shape {
    float radius;

    public Circle(String id, Color color, float radius) {
        super(id, color);
        this.radius = radius;
    }

    @Override
    public void scale(float scaleFactor) {
        radius *= scaleFactor;
    }

    @Override
    public float weight() {
        return (float) (Math.PI * radius * radius);
    }

    @Override
    String typeLetter() {
        return "C";
    }
}

class Rectangle extends Shape {
    float width;
    float height;

    public Rectangle(String id, Color color, float width, float height) {
        super(id, color);
        this.width = width;
        this.height = height;
    }

    @Override
    public void scale(float scaleFactor) {
        width *= scaleFactor;
        height *= scaleFactor;
    }

    @Override
    public float weight() {
        return width * height;
    }

    @Override
    String typeLetter() {
        return "R";
    }
}

class Canvas {

    List<Shape> shapes = new ArrayList<>();

    // Вметнување во листа сортирано во опаѓачки редослед без sort()
    private void insertShape(Shape s) {
        int i = 0;
        while (i < shapes.size() && shapes.get(i).weight() >= s.weight()) {
            i++;
        }
        shapes.add(i, s); // вметнување на точното место
    }

    void add(String id, Color color, float radius) {
        insertShape(new Circle(id, color, radius));
    }

    void add(String id, Color color, float width, float height) {
        insertShape(new Rectangle(id, color, width, height));
    }

    void scale(String id, float scaleFactor) {
        // 1. најди ја формата
        Shape target = null;
        for (Shape s : shapes) {
            if (s.id.equals(id)) {
                target = s;
                break;
            }
        }
        if (target == null) return;

        // 2. скалирај
        target.scale(scaleFactor);

        // 3. отстрани и повторно вметни за да се зачува редоследот
        shapes.remove(target);
        insertShape(target);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Shape s : shapes) {
            sb.append(s.toString()).append("\n");
        }
        return sb.toString();
    }
}
