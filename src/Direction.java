/**
 * Created by Barnabas_Ravindranath on 3/19/17.
 */
public enum Direction {

    NORTH (0,-1),
    SOUTH (0,1),
    EAST (1,0),
    WEST (-1,0),
    NORTHEAST(1,-1),
    NORTHWEST(-1,-1),
    SOUTHEAST(1,1),
    SOUTHWEST(-1,1);

    private final int dy, dx;

    Direction (int x, int y){
        this.dx = x;
        this.dy = y;
    }

    public int getDx() {
        return dx;
    }


    public int getDy() {
        return dy;
    }



}

