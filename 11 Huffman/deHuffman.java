// Name:              Date:
import java.util.Scanner;
import java.io.*;
public class deHuffman
{
   public static void main(String[] args) throws IOException
   {
      Scanner keyboard = new Scanner(System.in);
      System.out.print("\nWhat binary message (middle part)? ");
      String middlePart = keyboard.next();
      Scanner sc = new Scanner(new File("message."+middlePart+".txt")); 
      String binaryCode = sc.next();
      Scanner huffLines = new Scanner(new File("scheme."+middlePart+".txt")); 
      	
      TreeNode root = huffmanTree(huffLines);
      String message = dehuff(binaryCode, root);
      System.out.println(message);
      	
      sc.close();
      huffLines.close();
   }
   public static TreeNode huffmanTree(Scanner huffLines)
   {
      TreeNode tree = new TreeNode(null);
      while(huffLines.hasNext())
      {
         TreeNode child = tree;
         String str = huffLines.nextLine();
         for(int i=1;i<str.length();i++)
         {
            if(str.charAt(i)=='0')
            {
               if(child.getLeft()==null)
                  child.setLeft(new TreeNode(null));
               child = child.getLeft();
            }
            else
            {
               if(child.getRight()==null)
                  child.setRight(new TreeNode(null));
               child = child.getRight();
            }
         }
         child.setValue(str.charAt(0)+"");    
      }
      return tree;
   }
   public static String dehuff(String text, TreeNode root)
   {
      String str = "";
      int i=0;
      while(i<text.length())
      {
         TreeNode temp = root;
         int length = str.length();
         while(str.length()==length)
         {
            if(temp.getValue()!=null)
               str+=temp.getValue();
            else if(text.charAt(i)=='0')
            {
               temp = temp.getLeft();
               i++;
            } 
            else
            {
               temp = temp.getRight();
               i++;
            }
         }      
      }
      return str;
   }
}

 /* TreeNode class for the AP Exams */
class TreeNode
{
   private Object value; 
   private TreeNode left, right;
   
   public TreeNode(Object initValue)
   { 
      value = initValue; 
      left = null; 
      right = null; 
   }
   
   public TreeNode(Object initValue, TreeNode initLeft, TreeNode initRight)
   { 
      value = initValue; 
      left = initLeft; 
      right = initRight; 
   }
   
   public Object getValue()
   { 
      return value; 
   }
   
   public TreeNode getLeft() 
   { 
      return left; 
   }
   
   public TreeNode getRight() 
   { 
      return right; 
   }
   
   public void setValue(Object theNewValue) 
   { 
      value = theNewValue; 
   }
   
   public void setLeft(TreeNode theNewLeft) 
   { 
      left = theNewLeft;
   }
   
   public void setRight(TreeNode theNewRight)
   { 
      right = theNewRight;
   }
}
