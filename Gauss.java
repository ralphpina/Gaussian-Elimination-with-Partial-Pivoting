import java.util.*;

public class Gauss {
  
  //set to true to print debug statements to console
  private static boolean debug = true;
    
  public static void main(String[] args) {
    
    //matrix
    double[][] testMatrix = {{-1,2,3}, {4,-2,8}, {2,1,8}};
      
    //print matrix and # of columns to console
    printMatrix(testMatrix);    
    System.out.println("matrix.length = " + testMatrix.length);
    System.out.println("------------------");
    
    //gauss partial pivot
    double[][] answer = gaussPartialPivot(testMatrix);
    printMatrix(answer);
    
  }
  
  private static double[][] gaussPartialPivot(double[][] matrix) {
    for (int k = 0; k < matrix[0].length; k++) { //for k = 1:n
      int max = 0; //used to track pivot point
      if(debug)
        System.out.println("column = " + k); 
      if ((k + 1) < matrix[0].length) { //zero indexed, so it checks if there is another row
        max = findLargestPivot(matrix, k); //choose ip(k) such that |A(ip,k)|= max{|A(i,k)|: i >= k}
      }
      if (max == -1) { // if A(ipk,k) = 0
        System.out.println("warning ('Pivot in Gaussian Elimination is zero')");
        return matrix;
      } else if (max != 0) { //wap A(k,k),..., A(k,n) with A(ipk,k),..., A(ipk,n)
        System.out.println("swap " + k + " with " + max);
        matrix = swap(matrix, k, max); 
      }
      
      if(debug)
        printMatrix(matrix);
      
      for (int i = k + 1; i < matrix[0].length; i++) { //for i = k + 1:n
        if(debug)
          System.out.println("A(i,K) before = " + matrix[i][k]);
        
        matrix[i][k] = matrix[i][k] / matrix[k][k]; //A(i,k) = A(i,k)/A(k,k)
        
        if(debug)
          System.out.println("A(i,K) after = " + matrix[i][k]);
        
        for (int j = k + 1; j < matrix[0].length; j++) { //for j = k + 1:n
          if(debug)
            System.out.println("A(i,j) before = " + matrix[i][j]);
          
          matrix[i][j] = matrix[i][j] - matrix[i][k] * matrix[k][j]; //A(i,j) = A(i,j) - A(i,k) * A(k,j)
          
          if(debug) {
            System.out.println("A(i,j) after = " + matrix[i][j]);
            printMatrix(matrix);
          }
        }
      }
    }
    
    return matrix;
  }
  
  private static int findLargestPivot(double[][] matrix, int col) {
    int maxRow = - 1;
    double maxValue = 0;
    for(int row = col; row < matrix.length; row++) {
      if (Math.abs(matrix[row][col]) > maxValue && Math.abs(matrix[row][col]) > 0) {
        maxRow = row;
        maxValue = Math.abs(matrix[row][col]);
      }
    }
    if (debug)
      System.out.println("maxRow = " + maxRow);
    return maxRow;
  }
  
  private static double[][] swap(double[][] matrix, int col, int row) {
    double temp;
    
    for (int i = col; i < matrix[col].length; i++) {
      temp = matrix[col][i];
      matrix[col][i] = matrix[row][i];
      matrix[row][i] = temp;
    }
    return matrix;
  }
  
  private static void printMatrix(double[][] matrix) {
    for (int i = 0; i < matrix.length; i++) 
      System.out.println(Arrays.toString(matrix[i]));
    
    System.out.println("------------------");
  }
 }
  