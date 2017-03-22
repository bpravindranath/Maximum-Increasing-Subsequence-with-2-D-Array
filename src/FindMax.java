import java.util.ArrayList;
import java.util.Stack;


public class FindMax {

    //Stack and Array
    private Stack<Point> max_sequence;
    public int [][] m;

    //Constructor
    public FindMax (int [][] matrix) {
       max_sequence =  new Stack<>();
       m = matrix;
   }

   //Finds the Maximum Subsequence
   public Stack<Point> solve(Stack<Point> stack, Point startpoint) {
       ArrayList<Point> dir_point = getdirections(startpoint);

       for (Point pt : dir_point) {

           if (isInBounds(pt) && (getValueAt(pt) > getValueAt(startpoint))) {

               stack.push(pt);
               stack = solve(stack, pt);
               stack.pop();

           } else {

               checkStack(stack);
            }
       }

           return stack;
   }


   public ArrayList<Point> getdirections(Point point){

       ArrayList<Point> points = new ArrayList<>();

       for (Direction direction: Direction.values()) {
           Point p = point.moveIn(direction);
           points.add(p);
       }
       return points;
   }

    private void checkStack(Stack<Point> stack) {
        if (stack.size() > max_sequence.size()) {

            max_sequence = (Stack<Point>) stack.clone();
        }
    }


    public boolean isInBounds(Point p) {
        if ((p.gety() >= 0) && (p.gety() < m.length)) {
            if ((p.getx() >= 0) && (p.getx() < m[p.gety()].length)) {
                return true;
            }
        }
        return false;
    }

    private int getValueAt(Point p){

        return m[p.getx()][p.gety()];
    }


    public void outputAnswer() {

        while (max_sequence.size() > 0) {
            Point point = max_sequence.pop();
            System.out.println(getValueAt(point) + "\t" + "( " + point.getx() + ", " + point.gety() + ")");
        }

    }

}
