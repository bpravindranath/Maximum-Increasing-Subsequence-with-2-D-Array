/**
 * Created by Barnabas_Ravindranath on 3/19/17.
 */
public class Point {

    private int x;
    private int y;

    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }


    public Point moveIn(Direction direction) {
        return new Point(x + direction.getDx(), y + direction.getDy());
    }


    public int getx() {
        return x;
    }

    public int gety() {
        return y;
    }




}
