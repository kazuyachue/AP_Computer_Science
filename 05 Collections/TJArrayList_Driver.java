//  name:        date: 
//  implements the List interface with a backing array of Objects. 
//	 also overrides toString().
    
public class TJArrayList_Driver
{  
   public static void main(String [] args)
   {
      TJArrayList myList = new TJArrayList();	
   
      myList.add("Apple");
      myList.add("Banana");
      myList.add("Fig");
      myList.add(2, "Cucumber");
      myList.add(3, "Dates");
      System.out.println(myList);
      System.out.println("Size is " + myList.size());
      try
      {
         myList.add(12, "Peach");
      }
      catch(IndexOutOfBoundsException e)
      {
         System.out.println(e);
      }
      System.out.println("Get 2: " + myList.get(2));
      System.out.print("Set at 2: ");
      myList.set(2, "Cherry");
      System.out.println(myList);
      Object obj = myList.remove(2);
      System.out.println("Removed " + obj+ " from " + myList);
      System.out.println("Size is " + myList.size());
      System.out.print("Add too many items: ");
      for(int i = 3; i <= 10; i++)
         myList.add(new Integer(i));
      System.out.println(myList);
      System.out.println("Size is " + myList.size());   	
      System.out.println("Contains \"Breadfruit\"?  " + myList.contains("Breadfruit"));
      System.out.println("Contains \"Banana\"?  " + myList.contains("Banana"));
   }
}
   
class TJArrayList
{
   private int size;							//stores the number of objects
   private Object[] myArray;
   public TJArrayList()                //default constructor makes 10 cells
   {
      size = 0;
      myArray = new Object[10];
   }
   public int size()
   {
      return size;  
   }
 	/* appends obj to end of list; increases size;
         must be an O(1) operation when size < array.length, 
            and O(n) when it doubles the length of the array.
	      @return true  */
   public boolean add(Object obj)
   {
      if(size<myArray.length)
      {
         myArray[size] = obj;
      }
      else
      {
         Object[] temp = new Object[size*2];
         for(int i=0;i<size;i++)
         {
            temp[i] = myArray[i];
         }
         temp[size] = obj;
         myArray = temp;
      }
      size++;
      return true;
   }
      /* inserts obj at position index.  increments size. 
   		*/
   public void add(int index, Object obj) throws IndexOutOfBoundsException  //this the way the real ArrayList is coded
   {
      if(index > size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      Object[] temp = new Object[size+1];
      for(int i=0;i<index;i++)
         temp[i] = myArray[i];
      for(int x = size-1;x>=index;x--)
         temp[x+1] = myArray[x];
      temp[index] = obj;
      myArray = temp;   
      size++;
   }
      /* return obj at position index.  
   		*/
   public Object get(int index)
   {
      return myArray[index];
   }
    /* replaces obj at position index.  
   		*/
   public void set(int index, Object obj)
   {
      myArray[index] = obj;
   }
    /*  removes the node from position index. shifts elements 
        to the left.   Decrements size.
   	  @return the object at position index.
   	  */
   public Object remove(int index)
   {
      Object temp2 = myArray[index];
      Object[] temp = new Object[size-1];
      for(int i=0;i<index;i++)
         temp[i] = myArray[i];
      for(int x = index;x<size-1;x++)
         temp[x] = myArray[x+1];      
      size--;
      myArray = temp;
      return temp2;
   }
	 /*
      this method compares objects and should use .equals(), not ==
     	*/
   public boolean contains(Object obj)
   {
      boolean bool = false;
      for(int i=0;i<size;i++)
         if(myArray[i].equals(obj))
            bool = true;
      return bool;
   }
      /*returns a String of Objects in the array with square brackets and commas
        	*/
   public String toString()
   {
      String str = "[" + myArray[0];
      for(int i=1;i<size;i++)
         str += ", " + myArray[i];
      str += "]";
      return str;
   }
}