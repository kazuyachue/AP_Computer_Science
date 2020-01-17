    /* M.L. Billington, 10/02/2006.
    Uses the helper classes Selection and Insertion. 
	 Students are to write the Selection and Insertion classes.
    */
import java.util.*;
import java.io.*;
public class Sorts
{
   public static void main(String[] args) throws Exception
   {
        //Part 1, for doubles
      int n = (int)(Math.random()*100);
      double[] array = new double[n];
      for(int k = 0; k < array.length; k++)
         array[k] = Math.random();	
      print(array);
      System.out.println("*************  *************");
      array = Selection.sort(array);
      //array = Insertion.sort(array);
      print(array);
         
      	//Part 2, for Strings
      int size = 100;
      Scanner sc = new Scanner(new File("declaration.txt"));
      Comparable[] arrayStr = new String[size];
      for(int k = 0; k < arrayStr.length; k++)
         arrayStr[k] = sc.next();	
      print(arrayStr);
      System.out.println("*************  *************");
      arrayStr = Selection.sort(arrayStr);
      //arrayStr = Insertion.sort(arrayStr);
      print(arrayStr);
   }
   public static void print(double[] a)
   {
      // for(int k = 0; k < a.length; k++)    //old style
      //       System.out.println(a[k]);
      for(double d : a)                      // for-each loop     
         System.out.println(d);
      System.out.println();
   }
   public static void print(Object[] papaya)
   {
      for(Object item : papaya)     //for-each
         System.out.println( item );
   }
}
   //*******************************************************************
  //Name:              Date:
  //The Selection class will have methods sort(), findMax() and swap().
  //Three versions of each method will have to be written, to work 
  //for doubles, Strings, and Comparables.
  
class Selection
{
   public static double[] sort(double[] array)
   { 
      for(int i=0;i<array.length;i++)
      {
         swap(array,array.length-1-i,findMax(array,i));
      }
      return array;
   }
   public static void sort(double[] array, int low, int high)
   {
      int count = 0;
      for(int i=low;i<=high;i++)
      {
         swap(array,high-count,findMax(array, i));
         count++;
      }
   }
   private static int findMax(double[] array, int n)
   {
      int max = 0;
      for(int i=1;i<array.length-n;i++)
         if(array[i]>array[max])
            max = i;
      return max;
   }
   private static void swap(double[] array, int a, int b)
   {
      double temp = array[a];
      array[a]=array[b];
      array[b] = temp;
   }
   	/***************************************************
   	  for Strings
   	  ***********************************************/
   public static String[] sort(String[] array)
   {
      for(int i=0;i<array.length;i++)
      {
         swap(array,array.length-1-i,findMax(array,i));
      }
      return array;   
   }
   public static int findMax(String[] array, int upper)
   {
      int max = 0;
      for(int i=1;i<array.length-upper;i++)
         if(array[i].compareTo(array[max])>0)
            max = i;
      return max;
   }
   public static void swap(String[] array, int a, int b)
   {
      String temp = array[a];
      array[a]=array[b];
      array[b] = temp;
   }
   	/***************************************************
   	 for Comparables,
   	      Swap() is for Objects.
   	      make sure that print() is for Objects, too.
   	  ***********************************************/
   @SuppressWarnings("unchecked")//this removes the warning for Comparable
   public static Comparable[] sort(Comparable[] array)
   {
      for(int i=0;i<array.length;i++)
      {
         swap(array,array.length-1-i,findMax(array,i));
      }
      return array;
   }
   @SuppressWarnings("unchecked")
   public static int findMax(Comparable[] array, int upper)
   {
      int max = 0;
      for(int i=1;i<array.length-upper;i++)
         if(array[i].compareTo(array[max])>0)
            max = i;
      return max;
   }
   public static void swap(Object[] array, int a, int b)
   {
      Object temp = array[a];
      array[a]=array[b];
      array[b] = temp;
   
   }
}   

//**********************************************************
  //Name:              Date:
  //The Insertion class 
  //write enough methods to handle doubles and Comparables.
class Insertion
{
   public static double[] sort(double[] array)
   { 
      for(int i=1;i<array.length;i++)
         shift(array, i, array[i]);
   
      return array;
   }
   private static int shift(double[] array, int index, double value)
   {
      int loc = index - 1;
      while( loc>=0 && value<array[loc])
      {
         array[loc+1]=array[loc];
         loc--;
      }
      array[loc+1] = value;
      return -1;
   }
   @SuppressWarnings("unchecked")
    public static Comparable[] sort(Comparable[] array)
   { 
      for(int i=1;i<array.length;i++)
         shift(array, i, array[i]);
   
      return array;
   }
   @SuppressWarnings("unchecked")
    private static void shift(Comparable[] array, int index, Comparable value)
   {
      int loc = index - 1;
      while( loc>=0 && value.compareTo(array[loc])<0)
      {
         array[loc+1]=array[loc];
         loc--;
      }
      array[loc+1] = value;
   }
}
