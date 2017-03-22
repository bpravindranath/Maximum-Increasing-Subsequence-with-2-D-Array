/**
 * Created by Barnabas_Ravindranath on 3/19/17.
 */

import java.io.*;
import java.util.Scanner;
import java.util.Stack;
import java.io.IOException;


public class ProgrammingAssignment3  {

    static int[][] intarray;
    static final int x = 15, y = 15;
    static int x_axis, y_axis;

    public static void main(String[] args) throws IOException {

        //Initialize Stack and 2-D Array
        Stack<Point> current_stack = new Stack<>();
        intarray = new int[x][y];

        //Import 2-D Array from File
        intarray = makematrix("testdataHW4.txt");

        //Find Coordinate that Begins the Longest Increasing Subsequence and Prints the 2-D Array with Each
        // Coordinate Representing the Longest Increasing Subsequence
        longestincreasingPath(intarray);

        //Initialize the Find Max Class by Passing the 2-D Array
        FindMax find = new FindMax(intarray);

        //Initialize the Point Class by Creating a Point That Points to the Coordinate
        // That Begins the Longest Increasing Subsequence
        Point point = new Point(x_axis,y_axis);

        //Pushing the Current Coordinate in the Stack Which Will Eventually Hold the Path of the Increasing Subsequece
        current_stack.push(point);

        //The Call to Find the Maximum Increasing Subsequence
        find.solve(current_stack, point);

        System.out.println();

        //Prints the Coordinates from the Stack
        find.outputAnswer();

        System.out.println("Longest Increasing Sequence Starts at (" + x_axis + " , " + y_axis + ")");

    }


    public static int[][] makematrix(String file) {

        int[][] matrix = new int[x][y];
        Scanner scan = null;

        try {
            scan = new Scanner(new File(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int row = 0;
        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            String[] number = line.split(",");
            for (int col = 0; col < y; col++) {
                matrix[row][col] = Integer.parseInt(number[col].trim());
            }
            row++;
        }

        scan.close();
        return matrix;
    }


    static int longestincreasingPath(int[][] matrix){


        if (matrix == null || matrix.length == 0|| matrix[0].length == 0) {
            return 0;
        }

            int [][] cache = new int[matrix.length][matrix[0].length];
            int max_length = 0;
            int max = 0;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {

                int tem_lengths = check_direction(i, j,matrix, cache);
                max_length = Math.max(max_length, tem_lengths);

                if (max < max_length){
                    max = max_length;
                    x_axis = i;
                    y_axis = j;

                }
            }
        }

        for (int k = 0; k < matrix.length; k++) {
            for (int l = 0; l < matrix[0].length; l++) {

                System.out.print(cache[k][l] + " ");

                if(l == 14){
                    System.out.println();
                }
            }
        }

        return max_length;

    }



    static int check_direction(int i, int j, int[][] matrix, int[][] cache) {


        if (cache[i][j] > 0) {
            return cache[i][j];
        }
        else {

            int temp = 0;

            if (i - 1 > -1 && matrix[i][j] < matrix[i - 1][j]) {
//                    System.out.print(matrix[i-1][j]+ " ");
                temp = Math.max(temp, check_direction(i - 1, j, matrix, cache));
            }
            //down
            if (i + 1 < x && matrix[i][j] < matrix[i + 1][j]) {
//                    System.out.print(matrix[i+1][j]+ " ");
                temp = Math.max(temp, check_direction(i + 1, j, matrix, cache));
            }
            //left
            if (j - 1 > -1 && matrix[i][j] < matrix[i][j - 1]) {
//                    System.out.print(matrix[i][j-1]+ " ");
                temp = Math.max(temp, check_direction(i, j - 1, matrix, cache));
            }
            //right
            if (j + 1 < y && matrix[i][j] < matrix[i][j + 1]) {
//                    System.out.print(matrix[i][j+1]+ " ");
                temp = Math.max(temp, check_direction(i, j + 1, matrix, cache));
            }
            //Upper-Left
            if ((i - 1 > -1 && j - 1 > -1) && (matrix[i][j] < matrix[i - 1][j - 1])) {
//                    System.out.print(matrix[i - 1][j - 1]+ " ");
                temp = Math.max(temp, check_direction(i - 1, j - 1, matrix, cache));
            }

            //Upper-Right
            if ((i - 1 > -1 && j + 1 < y) && (matrix[i][j] < matrix[i - 1][j + 1])) {
//                    System.out.print(matrix[i-1][j+1]+ " ");
                temp = Math.max(temp, check_direction(i - 1, j + 1, matrix, cache));

            }

            //Bottom-Left
            if ((i + 1 < x && j - 1 > -1) && (matrix[i][j] < matrix[i + 1][j - 1])) {
//                    System.out.print(matrix[i+1][j-1]+ " ");
                temp = Math.max(temp, check_direction(i + 1, j - 1, matrix, cache));
            }
            //Bottom-Right
            if ((i + 1 < x && j + 1 < y) && (matrix[i][j] < matrix[i + 1][j + 1])) {
//                    System.out.print(matrix[i+1][j+1] + " ");
                temp = Math.max(temp, check_direction(i + 1, j + 1, matrix, cache));
            }

            cache[i][j] = ++temp;

            return temp;

        }


    }

}
