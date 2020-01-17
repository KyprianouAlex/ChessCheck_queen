//  Alexander Kyprianou
//  COP 3503

import static java.lang.Math.pow;
import java.util.ArrayList;

public class SneakyQueens {

    public static boolean allTheQueensAreSafe(ArrayList <String> coordinateStrings, int boardSize)
    {
        int numCoords = coordinateStrings.size();
        String sub;
        
        int[][] convertedCoords = new int[2][coordinateStrings.size()];
        
        //Iterate through each elemenet of the Array List &
        //populate convertedCoords[][]
        for(int i = 0; i < numCoords; i++)
        {           
            //Convert Column and save
            sub = coordinateStrings.get(i).replaceAll("[^a-z]", "");
            convertedCoords[0][i] = convertCoord(sub);
            
            //Save Row number
            sub = coordinateStrings.get(i).replaceAll("[a-z]", "");
            convertedCoords[1][i] = Integer.parseInt(sub);
        } 
        
        //Check for any queens that can attack horizontally or vertically
        if (searchHorizVert(convertedCoords, boardSize))
            return false;
        
        if(searchDia(convertedCoords, boardSize))
            return false;
        
        //All of the Queens are safe!
        return true;
    }
    
  
    //Convert alphabetic coordinates to numeric coordinates
    public static int convertCoord(String sub)
    {
        double k = 0.0;
        int val = 0;
            
        char[] arr = sub.toCharArray();
        
        for(int i = arr.length-1; i >=0; i--)
        {
            val = val + (arr[i] - 96)*((int)pow(26, k));
            k++;
        }
        return val;
    }
    
    //Search the convertedCoord array for a horizontal or vertical match
    public static boolean searchHorizVert(int[][] arr, int boardSize)
    {
        
        int[] x = new int[boardSize + 1];
        int[] y = new int[boardSize + 1];
        
        for(int i : arr[0])
        {
           if (x[i] == 1)
               return true;
           x[i] = 1;
        }
        
        for(int j : arr[1])
        {
           if (y[j] == 1)
               return true;
           y[j] = 1;
        }
        
        return false;
    }
    
    //Search the convertedCoord array for matching y-intercepts
    //using a +1 or -1 slope. Any queens with matching y-intercepts
    //can attack one another
    public static boolean searchDia(int[][] arr, int boardSize)
    {
        int[] posB = new int[2*boardSize + 1];
        int[] negB = new int[2*boardSize + 1];
        
        for(int i = 0; i < arr[0].length; i++)
        {
            if( posB[ (arr[1][i] - arr[0][i]) + boardSize ] == 1)
                return true;
            posB[ (arr[1][i] - arr[0][i]) + boardSize ] = 1;
            
            if(negB[ arr[1][i] + arr[0][i] ] == 1)
                return true;
            negB[ arr[1][i] + arr[0][i] ] = 1;
        }
        
        return false;
    }
    
    
    public static double difficultyRating()
    {
        return 3.8;
    }
 
    public static double hoursSpent()
    {
        return 8.5;
    }  
}
