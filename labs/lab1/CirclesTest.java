/* 
Да се дефинира интерфејс Movable што ќе ги дефинира основните својства на еден движечки објект:

движење нагоре (void moveUp())
движење надолу (void moveLeft())
движење надесно (void moveRight())
движење налево (void moveLeft())
пристап до моменталните x,y координати на објектот (int getCurrentXPosition() и int getCurrentYPosition()).
Постојат два типа на движечки објекти: движечка точка (MovingPoint) и движечки круг (MovingCircle). Да се дефинираат овие две класи коишто го имплементираат интерфејсот Movable.

Во класата MovingPoint се чуваат информации за:

x и y координати (цели броеви)
xSpeed и ySpeed : степенот на поместување на движечката точка во x насока и y насока (цели броеви)
За класата да се имплементираат:

конструктор со аргументи: MovablePoint(int x, int y, int xSpeed, int ySpeed),
методите наведени во интерфејсот Movable
toString метод кој дава репрезентација на објектите во следнот формат Movable point with coordinates (5,35)
Во класата MovingCircle се чуваат информации за:

радиусот на движечкиот круг (цел број)
центарот на движечкиот круг (објект од класата MovingPoint).
За класата да се имплементираат:

конструктор со аргументи: MovableCircle(int radius, MovablePoint center)
методите наведени во интерфејсот Movable
toString метод којшто дава репрезентација на објектите во следниот формат Movable circle with center coordinates (48,21) and radius 3
Првите четири методи од Movable (moveUp, modeDown, moveRight, moveLeft) треба да фрлат исклучок од тип ObjectCanNotBeMovedException доколку придвижувањето во соодветната насока не е возможно, односно со придвижувањето се излегува од дефинираниот простор во класата MovablesCollection. При движење на објекти од тип MovableCircle се смета дека кругот излегол од просторот, доколку неговиот центар излезе од просторот. Дозволено е дел до кругот да излезе од просторот, се додека центарот е се уште во просторот. Справете се со овие исклучоци на соодветните места. Погледнете во тест примерите какви пораки треба да се печатат кога ќе се фати исклучок од овој тип и имплементирајте го истото.

Да се дефинира класа MovablesCollection во која што ќе се чуваат информации за:

низа од движечки објекти (Movable [] movable)
статичка променлива за максималната вредност на координатата X (минималната е предодредена на 0)
статичка променлива за максималната вредност на координатата Y (минималната е предодредена на 0)
За класата да се имплементираат следните методи:

конструктор MovablesCollection(int x_MAX, int y_MAX)
void addMovableObject(Movable m) - метод за додавање на движечки објект во колекцијата од сите движечки објекти. Пред да се додади објектот, мора да се провери дали истиот е може да се вклопи во дефинираниот простор, односно истиот да не излегува од границите 0-X_MAX за x координатата и 0-Y_MAX за y координатата. Доколку станува збор за движечки круг, потребно е целиот круг да се наоѓа во наведениот интервал на вредности. Доколку движечкиот објект не може да биде вклопен во просторот, да се фрли исклучок од тип MovableObjectNotFittableException. Потребно е да се справите со исклучокот на соодветното место во main методот. Погледнете во тест примерите какви пораки треба да се печатат кога ќе се фати исклучок од овој тип и имплементирајте го истото.
void moveObjectsFromTypeWithDirection (TYPE type, DIRECTION direction)- метод за придвижување на движечките објекти од тип type во насока direction. TYPE и DIRECTION се енумерации кои се задедени во почетниот код. Во зависност од насоката зададена во аргументот, да се повика соодветниот метод за придвижување.
toString() - метод кој дава репрезентација на колекцијата од движечки објекти во следниот формат: Collection of movable objects with size [големина на колекцијата]: , по што во нов ред следуваат информации за сите движечки објекти во колекцијата.


Input	
3
1 21 9 19 20 32
1 18 41 18 13 32
0 13 55 18 4

Result
===COLLECTION CONSTRUCTOR AND ADD METHOD TEST===
Movable circle with center (21,9) and radius 32 can not be fitted into the collection
Movable circle with center (18,41) and radius 32 can not be fitted into the collection
Collection of movable objects with size 1:
Movable point with coordinates (13,55)

MOVE POINTS TO THE LEFT
Point (-5,55) is out of bounds
Collection of movable objects with size 1:
Movable point with coordinates (13,55)

MOVE CIRCLES DOWN
Collection of movable objects with size 1:
Movable point with coordinates (13,55)

CHANGE X_MAX AND Y_MAX
MOVE POINTS TO THE RIGHT
Collection of movable objects with size 1:
Movable point with coordinates (31,55)

MOVE CIRCLES UP
Collection of movable objects with size 1:
Movable point with coordinates (31,55)







*/import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

enum TYPE {
    POINT,
    CIRCLE
}

enum DIRECTION {
    UP,
    DOWN,
    LEFT,
    RIGHT
}

// Интерфејс Movable
interface Movable {
    void moveUp() throws ObjectCanNotBeMovedException;
    void moveDown() throws ObjectCanNotBeMovedException;
    void moveLeft() throws ObjectCanNotBeMovedException;
    void moveRight() throws ObjectCanNotBeMovedException;
    int getCurrentXPosition();
    int getCurrentYPosition();
}


class ObjectCanNotBeMovedException extends Exception {
    public ObjectCanNotBeMovedException(int x, int y) {
        super(String.format("Point (%d,%d) is out of bounds", x, y));
    }
}


class MovableObjectNotFittableException extends Exception {
    public MovableObjectNotFittableException(String message) {
        super(message);
    }
}


class MovablePoint implements Movable {
    private int x;
    private int y;
    private int xSpeed;
    private int ySpeed;

    public MovablePoint(int x, int y, int xSpeed, int ySpeed) {
        this.x = x;
        this.y = y;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }

    @Override
    public void moveUp() throws ObjectCanNotBeMovedException {
        int newY = y + ySpeed;
        if (newY > MovablesCollection.getyMax() || newY < 0) {
            throw new ObjectCanNotBeMovedException(x, newY);
        }
        y = newY;
    }

    @Override
    public void moveDown() throws ObjectCanNotBeMovedException {
        int newY = y - ySpeed;
        if (newY < 0 || newY > MovablesCollection.getyMax()) {
            throw new ObjectCanNotBeMovedException(x, newY);
        }
        y = newY;
    }

    @Override
    public void moveLeft() throws ObjectCanNotBeMovedException {
        int newX = x - xSpeed;
        if (newX < 0 || newX > MovablesCollection.getxMax()) {
            throw new ObjectCanNotBeMovedException(newX, y);
        }
        x = newX;
    }

    @Override
    public void moveRight() throws ObjectCanNotBeMovedException {
        int newX = x + xSpeed;
        if (newX > MovablesCollection.getxMax() || newX < 0) {
            throw new ObjectCanNotBeMovedException(newX, y);
        }
        x = newX;
    }

    @Override
    public int getCurrentXPosition() {
        return x;
    }

    @Override
    public int getCurrentYPosition() {
        return y;
    }

    @Override
    public String toString() {
        return String.format("Movable point with coordinates (%d,%d)", x, y);
    }
}

class MovableCircle implements Movable {
    private int radius;
    private MovablePoint center;

    public MovableCircle(int radius, MovablePoint center) {
        this.radius = radius;
        this.center = center;
    }

    @Override
    public void moveUp() throws ObjectCanNotBeMovedException {
        center.moveUp();
    }

    @Override
    public void moveDown() throws ObjectCanNotBeMovedException {
        center.moveDown();
    }

    @Override
    public void moveLeft() throws ObjectCanNotBeMovedException {
        center.moveLeft();
    }

    @Override
    public void moveRight() throws ObjectCanNotBeMovedException {
        center.moveRight();
    }

    @Override
    public int getCurrentXPosition() {
        return center.getCurrentXPosition();
    }

    @Override
    public int getCurrentYPosition() {
        return center.getCurrentYPosition();
    }

    public int getRadius() {
        return radius;
    }

    @Override
    public String toString() {
        return String.format("Movable circle with center coordinates (%d,%d) and radius %d",
                center.getCurrentXPosition(), center.getCurrentYPosition(), radius);
    }
}


class MovablesCollection {
    private List<Movable> movables;
    private static int xMax;
    private static int yMax;

    public MovablesCollection(int xMax, int yMax) {
        MovablesCollection.xMax = xMax;
        MovablesCollection.yMax = yMax;
        this.movables = new ArrayList<>();
    }

    public static int getxMax() {
        return xMax;
    }

    public static int getyMax() {
        return yMax;
    }

    public static void setxMax(int xMax) {
        MovablesCollection.xMax = xMax;
    }

    public static void setyMax(int yMax) {
        MovablesCollection.yMax = yMax;
    }

    public void addMovableObject(Movable m) throws MovableObjectNotFittableException {
        int x = m.getCurrentXPosition();
        int y = m.getCurrentYPosition();

        if (m instanceof MovableCircle) {
            MovableCircle circle = (MovableCircle) m;
            int radius = circle.getRadius();
            
           
            if (x - radius < 0 || x + radius > xMax || y - radius < 0 || y + radius > yMax) {
                throw new MovableObjectNotFittableException(
                    String.format("Movable circle with center (%d,%d) and radius %d can not be fitted into the collection", x, y, radius)
                );
            }
        } else {
          
            if (x < 0 || x > xMax || y < 0 || y > yMax) {
                throw new MovableObjectNotFittableException(
                    String.format("Movable point with coordinates (%d,%d) can not be fitted into the collection", x, y)
                );
            }
        }

        movables.add(m);
    }

    public void moveObjectsFromTypeWithDirection(TYPE type, DIRECTION direction) {
        for (Movable m : movables) {
            if (type == TYPE.POINT && m instanceof MovablePoint) {
                try {
                    moveObject(m, direction);
                } catch (ObjectCanNotBeMovedException e) {
                    System.out.println(e.getMessage());
                }
            } else if (type == TYPE.CIRCLE && m instanceof MovableCircle) {
                try {
                    moveObject(m, direction);
                } catch (ObjectCanNotBeMovedException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    private void moveObject(Movable m, DIRECTION direction) throws ObjectCanNotBeMovedException {
        switch (direction) {
            case UP:
                m.moveUp();
                break;
            case DOWN:
                m.moveDown();
                break;
            case LEFT:
                m.moveLeft();
                break;
            case RIGHT:
                m.moveRight();
                break;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Collection of movable objects with size %d:\n", movables.size()));
        for (Movable m : movables) {
            sb.append(m.toString()).append("\n");
        }
        return sb.toString().trim();
    }
}


public class CirclesTest {
    public static void main(String[] args) {
        System.out.println("===COLLECTION CONSTRUCTOR AND ADD METHOD TEST===");
        MovablesCollection collection = new MovablesCollection(100, 100);
        Scanner sc = new Scanner(System.in);
        int samples = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < samples; i++) {
            String inputLine = sc.nextLine();
            String[] parts = inputLine.split(" ");
            int x = Integer.parseInt(parts[1]);
            int y = Integer.parseInt(parts[2]);
            int xSpeed = Integer.parseInt(parts[3]);
            int ySpeed = Integer.parseInt(parts[4]);
            if (Integer.parseInt(parts[0]) == 0) { //point
                try {
                    collection.addMovableObject(new MovablePoint(x, y, xSpeed, ySpeed));
                } catch (MovableObjectNotFittableException e) {
                    System.out.println(e.getMessage());
                }
            } else { //circle
                int radius = Integer.parseInt(parts[5]);
                try {
                    collection.addMovableObject(new MovableCircle(radius, new MovablePoint(x, y, xSpeed, ySpeed)));
                } catch (MovableObjectNotFittableException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        System.out.println(collection.toString());
        System.out.println("\nMOVE POINTS TO THE LEFT");
        collection.moveObjectsFromTypeWithDirection(TYPE.POINT, DIRECTION.LEFT);
        System.out.println(collection.toString());
        System.out.println("\nMOVE CIRCLES DOWN");
        collection.moveObjectsFromTypeWithDirection(TYPE.CIRCLE, DIRECTION.DOWN);
        System.out.println(collection.toString());
        System.out.println("\nCHANGE X_MAX AND Y_MAX");
        MovablesCollection.setxMax(90);
        MovablesCollection.setyMax(90);
        System.out.println("MOVE POINTS TO THE RIGHT");
        collection.moveObjectsFromTypeWithDirection(TYPE.POINT, DIRECTION.RIGHT);
        System.out.println(collection.toString());
        System.out.println("\nMOVE CIRCLES UP");
        collection.moveObjectsFromTypeWithDirection(TYPE.CIRCLE, DIRECTION.UP);
        System.out.println(collection.toString());
    }
}