//Name:       Date:
import java.util.*;

public class Fib
{
   public static final int DEFAULT = 42;
      
   public static void main(String[] args)
   {
      int n = DEFAULT;
      System.out.println("Recursive");
      calculate(new Fib1(), n);
      System.out.println("Iterative, stored in an array");
      calculate(new Fib2(), n);
      System.out.println("Recursive, stored in an arrayList");
      calculate(new Fib3(), n);
      System.out.println("Recursive, stored in a hashMap");
      calculate(new Fib4(), n);		
   }
      
   public static void calculate(Fibber fibber, int n)
   {
      long start = System.nanoTime();
      int f = fibber.fib(n);
      long finish = System.nanoTime();
      long time = finish - start;
      
      System.out.print("fib(" + n + ") = " + f);
      System.out.println(" (" + time + " nanoseconds)");		
      System.out.println();		
   }
    
   private static class Fib1 implements Fibber
   {      
      public int fib(int n)
      {
         if(n == 1 || n == 2)
            return 1;
         else
            return fib(n - 1) + fib(n - 2);
      }
   }
   private static class Fib2 implements Fibber
   {
      public int fib(int n)
      {
         int[] array = new int[n+1];
         for(int i=1;i<array.length;i++)
         {
            if(i==1||i==2)
               array[i]=1;
            else
               array[i] = array[i-1] + array[i-2];
         }
         return array[n];
      } 
   }
   private static class Fib3 implements Fibber
   {
      ArrayList<Integer> array;
      public Fib3()
      {
         array = new ArrayList<Integer>();
         array.add(1);
         array.add(1);
      }
      public int fib(int n)
      {
         if(array.size()>=n)
            return array.get(n-1);
         else
         {
            int temp = fib(n-1) + fib(n-2);
            array.add(temp);
            return temp;
         }
      }
   }
   private static class Fib4 implements Fibber
   {
      Map<Integer, Integer> map;
      public Fib4()
      {
         map = new HashMap<Integer, Integer>();
         map.put(1, 1);
         map.put(2, 1);
      }
      public int fib(int n)
      {
         if(map.containsKey(n))
            return map.get(n);
         else
         {
            int temp = fib(n-1) + fib(n-2);
            map.put(n, temp);
            return temp;
         }
      }
   }
   
   private interface Fibber
   {
      public abstract int fib(int n);
   }
}
   	/*
    Recursive
    fib(42) = 267914296 (3276558048 nanoseconds)
    
    Iterative, stored in an array
    fib(42) = 267914296 (4988 nanoseconds)
    
    Recursive, stored in an arrayList
    fib(42) = 267914296 (64025 nanoseconds)
    
    Recursive, stored in a hashMap
    fib(42) = 267914296 (177793 nanoseconds)
    
   	*/
