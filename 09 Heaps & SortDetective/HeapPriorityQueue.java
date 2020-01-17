 //Name:   Date:
 
 //implement the API for java.util.PriorityQueue
 //test this class by using it in McRonaldPQ_working.java.
 //add(E) and remove()  must work in O(log n) time
 
import java.util.*;
public class HeapPriorityQueue<E extends Comparable<E>> 
{
   private ArrayList<E> myHeap;
   
   public HeapPriorityQueue()
   {
      myHeap = new ArrayList<E>();
      myHeap.add(null);
   }
  
   public void heapDown(ArrayList<E> array, int k, int size)
   {
      int left = 2 * k;
      int right = 2 * k + 1;
      if(k > size || left > size)
         return;
      if(right > size)
      {
         if(array.get(k).compareTo(array.get(left))>0)
            swap(array, k, left);
      }
      else
      {
         int minChild = (array.get(left).compareTo(array.get(right))<0)? left:right;
         if(array.get(k).compareTo(array.get(minChild))>0)
         {
            swap(array, k, minChild);
            heapDown(array, minChild, size);
         }
      }  
   }
   public void heapUp(ArrayList<E> array, int k, int size)
   {
      int parent = k / 2;
      if(k > size || parent < 1)
         return;
      if(array.get(k).compareTo(array.get(parent))>0)
      {
         swap(array, k, parent);
         heapUp(array, parent, size);
      }
   }

   public void swap(ArrayList<E> array, int a, int b)
   {
      E temp = array.get(a);
      array.set(a, array.get(b));
      array.set(b, temp);
   }
   public String toString()
   {
      return myHeap+"";  
   } 
   public E peek()
   {
      if(myHeap.size()>1)
         return myHeap.get(1);
      else
         return null;
   }
  
   public E remove()
   {
      E temp = myHeap.get(1);
      swap(myHeap, 1, myHeap.size()-1);
      myHeap.remove(myHeap.size()-1);
      heapDown(myHeap, 1, myHeap.size()-1);
      return temp;
   }
  
   public boolean add(E o)
   {
      myHeap.add(o);
      heapUp(myHeap, myHeap.size()-1, myHeap.size());
      return true;
   }
   public boolean isEmpty()
   {
      return myHeap.size()<2;
   }
}
