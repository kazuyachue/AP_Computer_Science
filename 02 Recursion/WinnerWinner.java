  // name:     date:    
public class WinnerWinner
{
   public static void main(String[] args)
   {
      Board b = new Board(3,3,"W-S-----X"); 
      System.out.println("Shortest path is " + b.win());   //2
      b = new Board(3,3,"S-W-----X"); 
      System.out.println("Shortest path is " + b.win());   //4
      b = new Board(3,3,"X-W-----S"); 
      System.out.println("Shortest path is " + b.win());   //4
      b = new Board(3,3,"----X--S-"); 
      System.out.println("Shortest path is " + b.win());   //1
      b = new Board(3,3,"SWW---WWX");
      System.out.println("Shortest path is " + b.win());   //4
      b = new Board(3,3,"SWW-W-W-X");        //no path exists
      System.out.println("Shortest path is " + b.win());   //-1
   }
}

class Board
{
   private char[][] board;  
   private int maxPath;
   
   public Board(int rows, int cols, String line)  
   {
      board = new char[rows][cols];
      for(int i = 0;i<board.length;i++)
         for(int x = 0;x<board[0].length;x++)
            board[i][x] = line.charAt(3*i+x);
      maxPath = rows*cols;
   }
   
   	/**	returns the length of the longest possible path in the Board   */
   public int getMaxPath()		
   {  
      return maxPath; 
   }	
   
   
   /**	checks to calculate the shortest path from S to X   */
   public int check(int r, int c)
   {	
      int counter1=0;
      int counter2=0;
      int counter3=0;
      int counter4=0;
   
      if(board[r][c]=='X')
         return 0;
      else
      {
         if(r<board.length-1)
         {
            if(board[r+1][c]!='W')
            {
               counter1+=check(r+1,c);
            }
         }
         if(r>0)
         {
            if(board[r-1][c]!='W')
            {
               counter2+=check(r-1,c);
            }
         }
         if(c<board[0].length-1)
         {
            if(board[r][c+1]!='W')
            {
               counter3+=check(r,c+1);
            }
         }  
         if(c>0)
         {
            if(board[r][c-1]!='W')
            {
               counter4+=check(r,c-1);
            }
         }
      }
      if(counter1==0)
         counter1 = 1000;
      if(counter2==0)
         counter2 = 1000;
      if(counter3==0)
         counter3 = 1000;
      if(counter4==0)
         counter4 = 1000;
         
      int min = Math.min(Math.min(counter1,counter2),Math.min(counter3,counter4));   
      if(min == 1000)
         min = -1;
      return min;
   }
      
   /** precondition:  S and X exist in board
       postcondition:  returns either the length of the path
                       from S to X, or -1, if no path exists. */
   public int win()
   {
      int shortestpath = -1;
      for(int x = 0;x<board.length;x++)
         for(int y = 0;y<board[0].length;y++)
            if(board[x][y]=='S')
               shortestpath = check(x,y);
      return shortestpath;
      
   }
}

/*  output:

    Shortest path is 2
    Shortest path is 4
    Shortest path is 4
    Shortest path is 1
    Shortest path is 4
    Shortest path is -1
    */