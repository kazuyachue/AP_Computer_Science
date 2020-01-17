//Author:  
//Date:    
import java.util.*;
  
public class Hailstone
{
  
   private static String hailstones1 = "";
   private static String hailstones2 = "";
   
   public static void main(String[] args)
   {
      System.out.println("Hailstone Numbers!");
      System.out.print("Enter the start value: ");
      Scanner sc = new Scanner(System.in);
      int start = sc.nextInt();
      int count = hailstone(start);
      System.out.println("hailstone(" + start + ") returns " + count + " because " + hailstones1);
      System.out.println("takes " + count + " steps." );
      int count2 = hailstone(start, 1);
      System.out.println("hailstone(" + start + ") returns " + count2 + " because " + hailstones2);
      System.out.println("takes " + count2 + " steps." );
   
      
   }
      //recursive, counts the steps with a variable
   public static int hailstone(int n, int count)
   {
      if(n==1)
      {
         hailstones2+="1";
         return count;
      }
      else
         if(n%2==0)
         {
            hailstones2+=n+"-";
            count++;
            return hailstone(n/2,count);
         }
         else 
         {
            hailstones2+=n+"-";
            count++;
            return hailstone(3*n+1,count);
         }
   }
		//recursive, counts the steps without a variable
   public static int hailstone(int n)
   {
      if(n==1)
      {
         hailstones1+="1";
         return 1;
      }
      else
         if(n%2==0)
         {
            hailstones1+=n+"-";
            return 1+hailstone(n/2);
         }
         else 
         {
            hailstones1+=n+"-";
            return 1+hailstone(3*n+1);
         }   
   }
}