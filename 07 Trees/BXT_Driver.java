//name:     date:
import java.util.*;
  
/*******************
Represents a binary expression tree.
The BXT can build itself from a postorder expression.  It can
evaluate and print itself. It also prints an inorder string and a preorder string.  
**********************/
class BXT
{
   private int count;
   private TreeNode root;
   public BXT()
   {
      count = 0;
      root = null;
   }
   
  
  /*  enter your instance methods here.  */
  
   public void buildTree(String str)   //buildTree
   {
      StringTokenizer st = new StringTokenizer(str);
      Stack<TreeNode> stack = new Stack<TreeNode>();
      while(st.hasMoreTokens())
      {
         String s = st.nextToken();
         if("*/+-".indexOf(s)!=-1)
         {
            TreeNode temp = stack.pop();
            TreeNode t = new TreeNode(s, stack.pop(), temp);
            stack.push(t);
         }
         else
         {
            TreeNode t = new TreeNode(s);
            stack.push(t);
         }
      }
      root = stack.pop();
   }
      
   public String display()   //display
   {
      return display(root, 0);
   }
   private String display(TreeNode t, int level)   
   {
      String toRet = "";
      if(t == null)
         return "";
      toRet += display(t.getRight(), level + 1); 
      for(int k = 0; k < level; k++)
         toRet += "\t";
      toRet += t.getValue() + "\n";
      toRet += display(t.getLeft(), level + 1);
      return toRet;
   }
   
   public String inorderTraverse()  //inorderTraverse
   {
      return inorderTraverse(root);
   }
   private  String inorderTraverse(TreeNode t)      
   {
      String toReturn = "";
      if(t == null)
         return "";
      toReturn += inorderTraverse(t.getLeft()); 
      toReturn += t.getValue() + " ";        
      toReturn += inorderTraverse(t.getRight());        
      return toReturn;
   }

   public String preorderTraverse()  //preorderTraverse
   {
      return preorderTraverse(root);
   }
   private String preorderTraverse(TreeNode t)   
   { 
      String toReturn = "";
      if(t == null)
         return "";
      toReturn += t.getValue() + " "; 
      toReturn += preorderTraverse(t.getLeft());         
      toReturn += preorderTraverse(t.getRight());        
      return toReturn;
   }

   public double evaluateTree()  //evaluateTree   
   {
      return evaluateTree(root);
   }
   private double evaluateTree(TreeNode t)       
   {
      if("+-*/".indexOf(t.getValue()+"")==-1)
         return Double.parseDouble(t.getValue()+"");
      else
      {
         if(t.getValue().equals("+"))
            return evaluateTree(t.getLeft()) + evaluateTree(t.getRight());
         else if(t.getValue().equals("-"))
            return evaluateTree(t.getLeft()) - evaluateTree(t.getRight());
         else if(t.getValue().equals("*"))
            return evaluateTree(t.getLeft()) * evaluateTree(t.getRight());
         else if(t.getValue().equals("/"))
            return evaluateTree(t.getLeft()) / evaluateTree(t.getRight());
      }
      return -1l;
   }
}
/*******************
Driver for a binary expression tree class.
Input: a postfix string, each token separated by a space.
**********************/
public class BXT_Driver
{
   public static void main(String[] args)
   {
      ArrayList<String> postExp = new ArrayList<String>();
      postExp.add("14 -5 / ");
      postExp.add("20 3 -4 + * ");
      postExp.add("2 3 + 5 / 4 5 - *");
   
      for( String postfix : postExp )
      {
         System.out.println("Postfix Exp: "  + postfix);
         BXT tree = new BXT();
         tree.buildTree( postfix );
         System.out.println("BXT: "); 
         System.out.println( tree.display() );
         System.out.print("Infix order:  ");
         System.out.println( tree.inorderTraverse() );
         System.out.print("Prefix order:  ");
         System.out.println( tree.preorderTraverse() );
         System.out.print("Evaluates to " + tree.evaluateTree());
         System.out.println( "\n------------------------");
      }
   }
}

/***************************************
 Postfix Exp: 14 -5 / 
 BXT: 
 	-5
 /
 	14
 Infix order:  14 / -5 
 Prefix order:  / 14 -5 
 Evaluates to -2.8
 ------------------------
 Postfix Exp: 20 3 -4 + * 
 BXT: 
 		-4
 	+
 		3
 *
 	20
 Infix order:  20 * 3 + -4 
 Prefix order:  * 20 + 3 -4 
 Evaluates to -20.0
 ------------------------
 Postfix Exp: 2 3 + 5 / 4 5 - *
 BXT: 
 		5
 	-
 		4
 *
 		5
 	/
 			3
 		+
 			2
 Infix order:  2 + 3 / 5 * 4 - 5 
 Prefix order:  * / + 2 3 5 - 4 5 
 Evaluates to -1.0
 ------------------------
 */