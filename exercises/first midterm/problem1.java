
import java.io.*;
import java.util.*;

class Canvas {
    private String identifier;
    private ArrayList<Integer> sides;

    public Canvas(String identifier) {
        this.identifier = identifier;
        this.sides = new ArrayList<>();
    }

    public void insertSquare(int side) {
        sides.add(side);
    }

    public int getTotalSquares() {
        return sides.size();
    }

    public int calculateTotalPerimeter() {
        int total = 0;
        for (int side : sides) {
            total += side * 4;
        }
        return total;
    }

    public String getIdentifier() {
        return identifier;
    }
}

class ShapesApplication {
    private ArrayList<Canvas> canvasList;

    ShapesApplication() {
        canvasList = new ArrayList<>();
    }

    int readCanvases(InputStream in) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        int totalCount = 0;
        String currentLine;

        while ((currentLine = reader.readLine()) != null) {
            String[] tokens = currentLine.split("\\s+");
            totalCount += tokens.length - 1;

            Canvas canvas = new Canvas(tokens[0]);
            for (int i = 1; i < tokens.length; i++) {
                canvas.insertSquare(Integer.parseInt(tokens[i]));
            }
            canvasList.add(canvas);
        }
        return totalCount;
    }

    public void printLargestCanvasTo(PrintStream out) {
        PrintWriter writer = new PrintWriter(out);

        Canvas largest = canvasList.get(0);
        int maxPerimeter = largest.calculateTotalPerimeter();

        for (Canvas canvas : canvasList) {
            int currentPerimeter = canvas.calculateTotalPerimeter();
            if (currentPerimeter > maxPerimeter) {
                maxPerimeter = currentPerimeter;
                largest = canvas;
            }
        }

        writer.println(String.format("%s %s %s",
                largest.getIdentifier(),
                largest.getTotalSquares(),
                largest.calculateTotalPerimeter()));
        writer.flush();
    }
}

public class Shapes1Test {
    public static void main(String[] args) throws IOException {
        ShapesApplication shapesApplication = new ShapesApplication();
        System.out.println("===READING SQUARES FROM INPUT STREAM===");
        System.out.println(shapesApplication.readCanvases(System.in));
        System.out.println("===PRINTING LARGEST CANVAS TO OUTPUT STREAM===");
        shapesApplication.printLargestCanvasTo(System.out);
    }
}