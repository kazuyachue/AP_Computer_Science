
 //name:    date:  
import java.util.*;
import java.io.*;

public class BSTobject_Driver
{
   public static BSTobject<String> tree = null;
   public static BSTobject<Widget> treeOfWidgets = null;
   public static int numberWidgets = 10;
   public static void main( String[] args ) 
   {
   //day one 
     //  tree = new BSTobject<String>();
   //       tree = build(tree, "T");
   //       System.out.print(tree);
   //       System.out.println("Size: " + tree.size());
   //       System.out.println("Is empty: "+ tree.isEmpty());
   		
   	//day two
   	//	Your assignment the second day is to finish the BSTobject class.  
   	//	Specifically, prompt the user for a string, put the characters into a BST, 
   	//	call toString on this tree, and print the size of the tree.
    
   //       Scanner keyboard = new Scanner(System.in);  
   //       System.out.print("Enter a string: ");
   //       String str = keyboard.next();
   //       tree = new BSTobject<String>();
   //       tree = build(tree, str);
   //       System.out.print(tree);
   //       System.out.println(tree.size());
      
      //day two, Widgets			
   	//	Next, fill your BST with 57 Widget objects from widgets.txt.  Display the tree. 
   	//	Then prompt the user to enter pounds and ounces.  If the tree contains that 
   	//	Widget, delete it, of course restoring the BST order. Display the new tree 
   	// and its size. If the tree does not contain that Widget, print "Not found".
   
      treeOfWidgets = new BSTobject<Widget>();
      treeOfWidgets = build(treeOfWidgets, new File("widget.txt"));
      System.out.print(treeOfWidgets);
      System.out.println(treeOfWidgets.size());
      
      Scanner keyboard = new Scanner(System.in);
      System.out.print("Enter pounds ");
      int pounds = keyboard.nextInt();
      System.out.print("Enter ounces ");
      int ounces = keyboard.nextInt();
      Widget w = new Widget(pounds, ounces);
      
      if(treeOfWidgets.contains(w))
      {
         treeOfWidgets.delete(w);
         System.out.print(treeOfWidgets);
         System.out.println(treeOfWidgets.size());
      }
      else
         System.out.println("Not found");
         
         
      //day three -- AVL tree  -----------------------
      
   }
   // build the tree for Strings, Day 1
   public static BSTobject<String> build(BSTobject<String> tree, String str)
   {
      for(Character c: str.toCharArray())
      {
         tree.add(c+"");
      }
      return tree; 
   }
     //build the tree for Widgets, Day 2
   public static BSTobject<Widget> build(BSTobject<Widget> tree, File file)
   {
      Scanner infile = null;
      try{
         infile = new Scanner( file  );
      }
      catch (Exception e)
      {
         System.exit(0);
      }
      for(int i = 0; i < 10; i++)   
      {
         tree.add(new Widget(infile.nextInt(), infile.nextInt()));
      }
      return tree;
   }
}

//////////////////////////////////
interface BSTinterface<E extends Comparable<E>>
{
   public E add( E element );     //returns the object
   public boolean contains( E element );
   public boolean isEmpty();
   public E delete( E element );  //returns the object, not a Node<E>
   public int size();
   public String toString();
}
//////////////////////////////////

class BSTobject <E extends Comparable<E>> implements BSTinterface<E>
{ 
    // 2 fields 
   Node<E> root;
   int size;
    // 1 default constructor
   public BSTobject()
   {
      root = null;
      size = 0;
   }
      
   //instance methods
   public E add( E obj )
   {
      root = add( root, obj );
      size++;
      return obj;
   }
    //recursive helper method
   private Node<E> add( Node<E> t, E obj )
   {
      if(t == null)
         t = new Node<E>(obj);
      else if(((Comparable)t.getValue()).compareTo(obj)>=0)
      {
         if(t.getLeft()==null)
            t.setLeft(new Node(obj));
         else
            add(t.getLeft(),obj);
      }
      else if(((Comparable)t.getValue()).compareTo(obj)<0)
      {
         if(t.getRight()==null)
            t.setRight(new Node(obj));
         else
            add(t.getRight(),obj);
      }
      return t;    
   }
   /* implement the interface here.  Use TreeNode as an example,
    but root is a field. You need add, contains, isEmpty, 
    delete, size, and toString.  */
   public boolean contains( E element )
   {
      Node<E> temp = root;
      while(temp!=null)
      {
         if(((Comparable)temp.getValue()).compareTo(element)>0)
            temp = temp.getLeft();
         else if(((Comparable)temp.getValue()).compareTo(element)<0)
            temp = temp.getRight();
         else
            return true;
      }
      return false;
   }
   public boolean isEmpty()
   {
      if(root == null)
         return true;
      else
         return false;   
   }
   public E delete( E element ) //returns the object, not a Node<E>
   {
      size--;
      Node<E> current = root;
      Node<E> parent = null;
      while(current !=null)
      {
         int compare = ((Comparable)current.getValue()).compareTo((element));
         // ------->  your code goes here
         if(compare>0)
         {
            parent = current;
            current = current.getLeft();
         }
         else if(compare<0)
         {
            parent = current;
            current = current.getRight();
         }
         else
         {
            if(parent == null)
            {
               if(current.getLeft()==null&&current.getRight()==null);
               else if(current.getLeft()==null)
                  root = root.getRight();
               else if(current.getRight()==null)
                  root = root.getLeft();
               else if(current.getLeft().getRight()==null)
               {
                  current.setValue(current.getLeft().getValue());
                  current.setLeft(current.getLeft().getLeft());
               }
               else
               {
                  current = current.getLeft();
                  while(current.getRight().getRight()!=null)
                  {
                     current = current.getRight();
                  }               
                  root.setValue(current.getRight().getValue());
                  if(current.getRight().getLeft()!=null)
                     current.setRight(current.getRight().getLeft());
                  else
                     current.setRight(null);
               }
            }
            else if(current.getLeft()==null&&current.getRight()==null)
            {
               if(parent.getLeft()==null)
                  parent.setRight(null);
               else if(((Comparable)parent.getLeft().getValue()).compareTo((Comparable)current.getValue())==0)
                  parent.setLeft(null);
               else
                  parent.setRight(null);
            }
            else if(current.getLeft()==null)
            {
               if(((Comparable)parent.getLeft().getValue()).compareTo((Comparable)current.getValue())==0)
                  parent.setLeft(current.getRight());
               else
                  parent.setRight(current.getRight());
            }
            else if(current.getRight()==null)
            {
               if(((Comparable)parent.getLeft().getValue()).compareTo((Comparable)current.getValue())==0)
                  parent.setLeft(current.getLeft());
               else
                  parent.setRight(current.getLeft());
            }
            else
            {
               if(current.getLeft().getRight()!=null)
               {
                  boolean bool = (((Comparable)parent.getRight().getValue()).compareTo((Comparable)current.getValue())==0);
                  current = current.getLeft();
                  while(current.getRight().getRight()!=null)
                  {
                     current = current.getRight();
                  }
                  if(current.getRight().getLeft()==null)
                  {
                     if(bool)
                     {
                        parent.getRight().setValue(current.getRight().getValue());
                        current.setRight(null);
                     }
                     else
                     {
                        parent.getLeft().setValue(current.getRight().getValue());
                        current.setRight(null);
                     }
                  }
                  else
                  {
                     if(bool)
                     {
                        parent.getRight().setValue(current.getRight().getValue());
                        current.setRight(current.getRight().getLeft());
                     }
                     else
                     {
                        parent.getLeft().setValue(current.getRight().getValue());
                        current.setRight(current.getRight().getLeft());
                     }
                     
                  }
               }
               else
               {
                  current.setValue(current.getLeft().getValue());
                  current.setLeft(current.getLeft().getLeft());
               }
            }
            current = null;
         }
      }
           //never reached
      return element;
   }
   public int size()
   {
      return size;   
   }
   public String toString()
   {
      return toString(root, 0);
   }
   private String toString(Node<E> t, int level)
   {
      String toRet = "";
      if(t == null)
         return "";
      toRet += toString(t.getRight(), level + 1); 
      for(int k = 0; k < level; k++)
         toRet += "\t";
      toRet += t.getValue() + "\n";
      toRet += toString(t.getLeft(), level + 1);
      return toRet;
   }
    
    /***************************private inner class **************/  
   private class Node<E>
   {
      private Object value; 
      private Node<E> left, right;
   
      public Node(Object initValue)
      { 
         value = initValue; 
         left = null; 
         right = null; 
      }
   
      public Node(Object initValue, Node<E> initLeft, Node<E> initRight)
      { 
         value = initValue; 
         left = initLeft; 
         right = initRight; 
      }
   
      public Object getValue()
      { 
         return value; 
      }
   
      public Node<E> getLeft() 
      { 
         return left; 
      }
   
      public Node<E> getRight() 
      { 
         return right; 
      }
   
      public void setValue(Object theNewValue) 
      { 
         value = theNewValue; 
      }
   
      public void setLeft(Node<E> theNewLeft) 
      { 
         left = theNewLeft;
      }
   
      public void setRight(Node<E> theNewRight)
      { 
         right = theNewRight;
      }
   
      // 3 fields 
         
      // 2 constructors, one-arg and three-arg
         
         
         
         
      //methods--Use TreeNode as an example. See the cheat sheet.
   
   }
}
