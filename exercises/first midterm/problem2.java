import java.util.*;
import java.io.*;

// ======================= InvalidCanvasException =======================

class InvalidCanvasException extends Exception {
    public InvalidCanvasException(String msg) {
        super(msg);
    }
}

// ================================ Canvas ================================

class Canvas {
    private String id;
    private List<Double> areas;
    private int circles;
    private int squares;

    public Canvas(String id) {
        this.id = id;
        this.areas = new ArrayList<>();
        this.circles = 0;
        this.squares = 0;
    }

    public void addCircle(double radius) {
        double area = Math.PI * radius * radius;
        areas.add(area);
        circles++;
    }

    public void addSquare(double side) {
        double area = side * side;
        areas.add(area);
        squares++;
    }

    public double totalArea() {
        return areas.stream().mapToDouble(d -> d).sum();
    }

    public double minArea() {
        return areas.stream().mapToDouble(d -> d).min().orElse(0);
    }

    public double maxArea() {
        return areas.stream().mapToDouble(d -> d).max().orElse(0);
    }

    public double averageArea() {
        if (areas.isEmpty()) return 0;
        return totalArea() / areas.size();
    }

    public String getId() {
        return id;
    }

    public int getCircles() {
        return circles;
    }

    public int getSquares() {
        return squares;
    }

    public int totalShapes() {
        return areas.size();
    }
}

// ============================ ShapesApplication ============================

class ShapesApplication {
    private double maxArea;
    private List<Canvas> canvases;

    public ShapesApplication(double maxArea) {
        this.maxArea = maxArea;
        this.canvases = new ArrayList<>();
    }

    public void readCanvases(InputStream inputStream) {
        Scanner sc = new Scanner(inputStream);

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;

            String[] parts = line.split("\\s+");

            String canvasId = parts[0];
            Canvas canvas = new Canvas(canvasId);

            boolean valid = true;

            try {
                for (int i = 1; i < parts.length; i += 2) {
                    String type = parts[i];
                    double size = Double.parseDouble(parts[i + 1]);

                    double area = type.equals("S")
                            ? size * size
                            : Math.PI * size * size;

                    if (area > maxArea)
                        throw new InvalidCanvasException(
                                String.format("Canvas %s has a shape with area larger than %.2f",
                                        canvasId, maxArea)
                        );

                    if (type.equals("S"))
                        canvas.addSquare(size);
                    else if (type.equals("C"))
                        canvas.addCircle(size);
                }
            } catch (InvalidCanvasException e) {
                System.out.println(e.getMessage());
                valid = false;
            }

            if (valid)
                canvases.add(canvas);
        }
    }

    public void printCanvases(OutputStream os) {
        PrintWriter pw = new PrintWriter(os);

        canvases.sort(Comparator.comparing(Canvas::totalArea).reversed());

        for (Canvas c : canvases) {
            pw.printf(
                    "%s %d %d %d %.2f %.2f %.2f\n",
                    c.getId(),
                    c.totalShapes(),
                    c.getCircles(),
                    c.getSquares(),
                    c.minArea(),
                    c.maxArea(),
                    c.averageArea()
            );
        }

        pw.flush();
    }
}

// ================================== MAIN ==================================

public class Shapes2Test {
    public static void main(String[] args) {

        ShapesApplication shapesApplication = new ShapesApplication(10000);

        System.out.println("===READING CANVASES AND SHAPES FROM INPUT STREAM===");
        shapesApplication.readCanvases(System.in);

        System.out.println("===PRINTING SORTED CANVASES TO OUTPUT STREAM===");
        shapesApplication.printCanvases(System.out);
    }
}
