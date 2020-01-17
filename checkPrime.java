import java.util.Scanner;
import java.lang.Math;

public class checkPrime
{
   public static void main(String[] args)
   {
   
   
      Scanner in = new Scanner(System.in);
      System.out.print("\nCheck if Prime? ");
      int n = in.nextInt();
      if(isPrime(n))
         System.out.println("true");
      else
         System.out.println("false");
   
    
   }
   public static boolean isPrime(int n) {
      if(n < 2) 
         return false;
      if(n == 2 || n == 3) 
         return true;
      if(n%2 == 0 || n%3 == 0) 
         return false;
      int sqrtN = (int)Math.sqrt(n)+1;
      for(int i = 6; i <= sqrtN; i += 6) {
         if(n%(i-1) == 0 || n%(i+1) == 0) 
            return false;
      }
      return true;
   }
   public static boolean isPrime1(long n) {
      if(n < 2) 
         return false;
      if(n == 2 || n == 3) 
         return true;
      if(n%2 == 0 || n%3 == 0) 
         return false;
      long sqrtN = (long)Math.sqrt(n)+1;
      for(long i = 6L; i <= sqrtN; i += 6) {
         if(n%(i-1) == 0 || n%(i+1) == 0) 
            return false;
      }
      return true;
   }
   private static boolean isPrime2(int n)
   {
      int i;
      if(n==1)
         return false;
      for (i = 2; i < n; i++) {
         if (n % i == 0) {
            return false;
         }
      }
      return true;
   }
}