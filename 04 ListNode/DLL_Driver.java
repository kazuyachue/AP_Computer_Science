// name:    date:
public class DLL_Driver
{
   public static void main(String args[])
   {
      DLL list = new DLL();	
   
      list.addLast("Apple");
      list.addLast("Banana");
      list.addLast("Cucumber");
      list.add("Durian");
      list.add("Eggplant");
      
      System.out.println("The list is " + list);
      System.out.println("Size: " + list.size());
      Object obj = list.remove(2);
      System.out.println("Remove index 2: "+ obj);
      System.out.println("The list is " + list);
      System.out.println("Size: " + list.size());
   
      list.add(2,"Carrot");
      System.out.println("Add Carrot at index 2:   " + list);
      
      try
      {
         list.add(16,"Kiwi");    //out-of-bounds
      }
      catch(IndexOutOfBoundsException e)
      {
         System.out.println(e);
      }
       
       
      System.out.println("Get values at index 0 and First: " + list.get(0)+" and " + list.getFirst());
      System.out.println("No change in list: " +list);
         
      list.removeFirst();
      System.out.println( "Remove the First:  " + list); 
       
      list.addFirst("Artichoke");
      System.out.println("Add First:  " + list);
      System.out.println("Size: " + list.size());
       
      list.set(1, "Broccoli");
      System.out.println("Set value at index 1:  " + list);
   }
}

//////////////////////////////////////////////////////////
    
class DLL        //DoubleLinkedList
{
   private int size;
   private DLNode head = new DLNode(); //dummy node--very useful--simplifies the code
   
   public int size()
   {
      return size;
   }
   
  /* appends obj to end of list; increases size;
   	  @return true  */
   public boolean add(Object obj)
   {
      addLast(obj);
      return true;   
   }
   
   /* inserts obj at position index.  increments size. 
   	*/
   public void add(int index, Object obj) throws IndexOutOfBoundsException  //this the way the real LinkedList is coded
   {
      if(index > size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      /* enter your code below  */
      for(int i=0;i<index;i++)
         head = head.getNext();
      head.setNext(new DLNode(obj, head, head.getNext()));
      head.getNext().getNext().setPrev(head.getNext());
      while(head.getValue()!=null)
         head = head.getNext();
      size++;
   }
   
   /* return obj at position index.  
   	*/
   public Object get(int index)
   {
      for(int i=0;i<index+1;i++)
         head = head.getNext();
      Object obj = head.getValue();
      while(head.getValue()!=null)
         head = head.getNext();
      return obj;
   }
   
   /* replaces obj at position index.  
   		*/
   public void set(int index, Object obj)
   {
      for(int i=0;i<index;i++)
         head = head.getNext();
      head.setValue(obj);
      while(head.getValue()!=null)
         head = head.getNext();
   }
   
   /*  removes the node from position index.  decrements size.
   	  @return the object at position index.
   	 */
   public Object remove(int index)
   {
      if(index > size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      
      for(int i=0;i<index;i++)
         head = head.getNext();
      Object obj = head.getNext().getValue();
      head.setNext(head.getNext().getNext());
      head.getNext().setPrev(head);
      size--;
      while(head.getValue()!=null)
         head = head.getNext();
      return obj;
   }
   
  /* inserts obj at front of list; increases size;
     */
   public void addFirst(Object obj)
   {
      head.setNext(new DLNode(obj,head,head.getNext()));
      head.getNext().getNext().setPrev(head.getNext());
      size++;
   }
   
   /* appends obj to end of list; increases size;
       */
   public void addLast(Object obj)
   {
      head.setPrev(new DLNode(obj,head.getPrev(),head));
      head.getPrev().getPrev().setNext(head.getPrev());
      size++;
   }
   
   public Object getFirst()
   {
      return head.getNext().getValue();
   }
   
   public Object getLast()
   {
      return head.getPrev().getValue();
   }
   
   public Object removeFirst()
   {
      Object obj = head.getNext().getValue();
      head.setNext(head.getNext().getNext());
      head.getNext().setPrev(head);
      size--;
      return obj;
   }
   
   public Object removeLast()
   {
      Object obj = head.getPrev().getValue();
      head.setPrev(head.getPrev().getPrev());
      head.getPrev().setNext(head);
      size--;
      return obj;
   }
   
   public String toString()
   {
      String list = "";
      head = head.getNext();
      list += "[";
      while(head.getNext().getValue()!=null)
      {
         list += head.getValue() + ", ";
         head = head.getNext();
      }
      list+=head.getValue();
      list += "]";
      head = head.getNext();
      return list;
   }
}
	
//////////////////////////////////////

class DLNode 
{
   private Object value;
   private DLNode prev;
   private DLNode next;
   public DLNode(Object arg, DLNode p, DLNode n)
   {
      value=arg;
      prev=p;
      next=n;
   }
   public DLNode()
   {
      value=null;
      next=this;
      prev=this;
   }
   public void setValue(Object arg)
   {
      value=arg;
   }
   public void setNext(DLNode arg)
   {
      next=arg;
   }
   public void setPrev(DLNode arg)
   {
      prev=arg;
   }
   public DLNode getNext()
   {
      return next;
   }
   public DLNode getPrev()
   {
      return prev;
   }
   public Object getValue()
   {
      return value;
   }
}
