 //name:    date: 
import java.util.Scanner;
/****************************************************************
 Practice with a Binary Search Tree. Uses TreeNode.
Prompt the user for an input string.  Build a BST from the letters.
Display it as a sideways tree.  Prompt the user for a target and delete
 that node, if it exists. Show the updated tree.
*****************************************************************/
public class BinarySearchTreeDelete
{
   private static TreeNode root = null;
   public static void main(String[] args)
   {
      Scanner sc = new Scanner(System.in);
      System.out.print("Input string: ");
      String s = sc.nextLine();
   	         				//Case 1:  		ECSBPWANR 
   								//Case 2a:  	SNTPOR    
                           //Case 2b:	   HBRNVJSZIK  
   								//Case 3.a.i:  DBNACFSEJHM 
   								//Case 3.a.ii: NFSAKPXGQ 
   								//Case 3b:     RNVGPCAE 
   								//Case root:   N
   								//on the handout: HDJAGKFEOLTMNSU
      root = buildTree( root, s );
      System.out.println( display(root, 0) );
      System.out.print("Delete? ");
      String target = sc.next();
      if( contains( root, target ) )
      {
         root = delete( root, target );
         System.out.println("\n" + target+" deleted.");
      }
      else
         System.out.println("\n" + target+" not found");
      System.out.println( display(root, 0) );
   }
   public static TreeNode buildTree(TreeNode t, String s)
   {
      for(int k = 0; k < s.length(); k++)
         t = insert(t, "" + s.charAt(k));
      return t;
   }
	/**************************
	Recursive algorithm to build a BST:  if the node is null, insert the 
	new node.  Else, if the item is less, set the left node and recur to 
	the left.  Else, if the item is greater, set the right node and recur 
	to the right.   
	*****************************/
   public static TreeNode insert(TreeNode t, String s)
   {  	
      if(t==null)
         return new TreeNode(s);
      if(s.compareTo(t.getValue()+"")<0)
         t.setLeft(insert(t.getLeft(), s));
      else
         t.setRight(insert(t.getRight(), s));
      return t;
   }
   
   private static String display(TreeNode t, int level)
   {
      String toRet = "";
      if(t == null)
         return "";
      toRet += display(t.getRight(), level + 1); //recurse right
      for(int k = 0; k < level; k++)
         toRet += "\t";
      toRet += t.getValue() + "\n";
      toRet += display(t.getLeft(), level + 1); //recurse left
      return toRet;
   }
   
   public static boolean contains( TreeNode current, String target )
   {
      while(current != null)
      {
         int compare = target.compareTo((String)current.getValue());
         if( compare == 0 )
            return true;
         else if(compare<0)
            current = current.getLeft();
         else if(compare>0)
            current = current.getRight();
      }
      return false;
   }
   public static TreeNode delete(TreeNode current, String target)
   {
      TreeNode root = current;
      TreeNode parent = null;
      while(current !=null)
      {
         int compare = target.compareTo((String)current.getValue());
        // ------->  your code goes here
         if(compare<0)
         {
            parent = current;
            current = current.getLeft();
         }
         else if(compare>0)
         {
            parent = current;
            current = current.getRight();
         }
         else
         {
            if(parent == null)
            {
               if(current.getLeft()==null&&current.getRight()==null)
                  return null;
               else if(current.getLeft()==null)
                  return current.getRight();
               else if(current.getRight()==null)
                  return current.getLeft();
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
      return root;  //never reached
   }
}