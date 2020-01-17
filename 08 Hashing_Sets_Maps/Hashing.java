 //name:    date:
 /*  Assignment:  This hashing program results in collisions.
     You are to implement three different collision schemes: linear 
     probing, rehashing, and chaining.  Then implement a search 
     algorithm that is appropriate for each collision scheme.
 */
import java.util.*;
import javax.swing.*;
public class Hashing
{
   public static void main(String[] args)
   {
      int arrayLength = Integer.parseInt(JOptionPane.showInputDialog(
                         "Hashing!\n"+
                         "Enter the size of the array:  "));//20
       
      int numItems = Integer.parseInt(JOptionPane.showInputDialog(
                         "Add n items:  "));               //15
     
      int scheme = Integer.parseInt(JOptionPane.showInputDialog(
           "The Load Factor is " + (double)numItems/arrayLength +
           "\nWhich collision scheme?\n"+
           "1. Linear Probing\n" +
           "2. Rehashing\n"+
           "3. Chaining"));
      Hashtable table = null;
      switch( scheme )
      {
         case 1:   
            table = new HashtableLinearProbe(arrayLength);
            break;
         case 2: 
            table = new HashtableRehash(arrayLength);
            break;
         case 3:  
            table = new HashtableChaining(arrayLength);
            break;
         default:  System.exit(0);    
      }
      for(int i = 0; i < numItems; i++)
         table.add("Item" + i);
      int itemNumber = Integer.parseInt(JOptionPane.showInputDialog(
                       "Search for:  Item0" + " to "+ "Item"+(numItems-1)));
      while( itemNumber != -1 )
      {
         String key = "Item" + itemNumber;
         int index = table.indexOf(key); 
         if( index >= 0)    //found it
            System.out.println(key + " found  at index " + index);
         else
            System.out.println(key + " not found!");
         itemNumber = Integer.parseInt(JOptionPane.showInputDialog(
                       "Search for:  Item0" + " to "+ "Item"+(numItems-1)));                           
      } 
      System.exit(0);
   }
}
/*********************************************/
interface Hashtable
{
   void add(Object obj);
   int indexOf(Object obj);
}
/***************************************************/ 
class HashtableLinearProbe implements Hashtable
{
   private Object[] array;
   public HashtableLinearProbe(int size)
   {
      array = new Object[size];              //constructor
   }
   public void add(Object obj)
   {
      int code = obj.hashCode();
      int index = Math.abs(code % array.length);
      if(array[index]==null)  //empty
      {
         array[index]=obj;   //insert it
         System.out.println(obj + "\t" + code + "\t" + index);
      }
      else //collision
      {
         System.out.println(obj + "\t" + code + "\tCollision at "+ index);
         index = linearProbe(index);
         array[index] = obj;
         System.out.println(obj + "\t" + code + "\t" + index);
      }
   }  
   public int linearProbe(int index)
   {
      //be sure to wrap around the array.
      while(array[index]!=null)
      {
         if(index==array.length-1)
            index = 0;
         else
            index++;
      }
            
      return index;
   }
   public int indexOf(Object obj)     
   {
      int index = Math.abs(obj.hashCode() % array.length);
      while(array[index] != null)
      {
         if(array[index].equals(obj))  //found it
         {
            return index; 
         }
         else //search for it in a linear probe manner
         {
            System.out.println("Looking at index " + index);
            index++;
         }
      }
      return -1;     //not found
   }
}
/*****************************************************/
class HashtableRehash implements Hashtable
{
   private Object[] array;
   private int constant = 2;
   public HashtableRehash(int size)
   {
      array = new Object[size];  
      while(gcd(constant,size)!=1)
         constant++;               //constructor
                             //find a constant that is relatively prime to the size of the array
   }
   private int gcd(int x, int y)
   {
      int temp;
      while(y!=0)
      {
         temp = x;
         x = y;
         y = temp%y;
      }
      return x;
   }
   public void add(Object obj)
   {
      int code = obj.hashCode();
      int index = Math.abs(code % array.length);
      if( array[index]==null )  //empty
      {
         array[index] = obj;  //insert it
         System.out.println(obj + "\t" + code + "\t" + index);
      }
      else //collision
      {
         System.out.println(obj + "\t" + code + "\tCollision at "+ index);
         while(array[index]!=null)
            index = rehash(index);
         array[index] = obj;
         System.out.println(obj + "\t" + code + "\t" + index);
      }
   }  
   public int rehash(int index)
   {
      int x = (index+constant) % array.length;
      return x;
   }
   public  int indexOf(Object obj)
   {
      int index = Math.abs(obj.hashCode() % array.length);
      while(array[index] != null)
      {
         if( array[index].equals(obj) )  //found it
         {
            return index;
         }
         else //search for it in a rehashing manner
         {
            index = rehash(index);
            System.out.println("Looking at index " + index);
         }
      }
      return -1;  //not found
   }
}
/********************************************************/
class HashtableChaining implements Hashtable
{
   private LinkedList[] array;
   public HashtableChaining(int size)
   {
      array = new LinkedList[size];         //instantiate the array
      for(int i=0;i<array.length;i++)                    //instantiate the LinkedLists
         array[i] = new LinkedList();
   }
   public void add(Object obj)
   {
      int code = obj.hashCode();
      int index = Math.abs(code % array.length);
      array[index].addFirst(obj);
      System.out.println(obj + "\t" + code + " " + " at " +index + ": "+ array[index]);
   }  
   public int indexOf(Object obj)
   {
      int index = Math.abs(obj.hashCode() % array.length);
      if( !array[index].isEmpty() )
      {
         if(array[index].getFirst().equals(obj))  //found it
         {
            return index;
         }
         else //search for it in a chaining manner
         {
            int x = 1;
            while(x<array[index].size())
            {
               if(array[index].get(x).equals(obj))
                  return index;
               else
                  x++;
            }
         }
      }
      return -1;     //not found
   }
}