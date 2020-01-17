//name:    date:  

import java.io.*;      //the File class
import java.util.*;    //the Scanner class
   
public class Widget_Driver
{
   public static final int numberOfWidgets = 57;
   public static void main(String[] args) throws Exception
   {
      Comparable[] array = input("widget.txt");
      Selection.sort(array);
      print(array);
   }
          	
   public static Comparable[] input(String filename) throws Exception
   {
      Scanner infile = new Scanner(new File(filename));
      Comparable[] array = new Comparable[24];
      for(int i=0;i<array.length;i++)
         array[i] = new Widget(infile.nextInt(), infile.nextInt());
      return array;
   }
   	  
   public static void print(Object[] mango)
   {
      for(Object x : mango )    
         System.out.println(x.toString());
      System.out.println();
   }
}
   /////////////////////////////////////////////////////
	//name:    date:

class Widget implements Comparable<Widget>
{
   int pounds;
   int ounces;
   public Widget(int x, int y)
   {
      pounds = x;
      ounces = y;
   }
   public int getPounds()
   {
      return pounds;
   }
   public int getOunces()
   {
      return ounces;
   }
   public void setPounds(int x)
   {
      pounds = x;
   }
   public void setOunces(int y)
   {
      ounces = y;
   }
   public String toString()
   {
      return pounds + " lbs., " + ounces + " oz.";
   }
   public int compareTo(Widget other)
   {
      if(pounds == other.getPounds()  && ounces == other.getOunces())
         return 0;
      else if(pounds>other.getPounds())
         return 1;
      else if(pounds == other.getPounds() && ounces > other.getOunces())
         return 1;
      else
         return -1;
   }
   public boolean equals(Widget other)
   {
      if(pounds == other.getPounds()  && ounces == other.getOunces())
         return true;
      else
         return false;   
   }
}