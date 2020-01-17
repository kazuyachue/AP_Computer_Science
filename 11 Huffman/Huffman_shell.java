// name:        date: 
import java.util.*;
import java.io.*;
public class Huffman_shell
{
   public static Scanner keyboard = new Scanner(System.in);
   public static void main(String[] args) throws IOException
   {
      //Prompt for two strings 
      System.out.print("Encoding using Huffman codes");
      System.out.print("\nWhat message? ");
      String message = keyboard.nextLine();
   
      System.out.print("\nEnter middle part of filename:  ");
      String middlePart = keyboard.next();
   
      huffmanize( message, middlePart );
   }
   public static void huffmanize(String message, String middlePart) throws IOException
   {
         //Make a frequency table of the letters
      	//Put each letter-frequency pair into a HuffmanTreeNode. Put each 
   		//        node into a priority queue (or a min-heap).     
      	//Use the priority queue of nodes to build the Huffman tree
      	//Process the string letter-by-letter and search the tree for the 
   		//       letter. It's recursive. As you recur, build the path through the tree, 
   		//       where going left is 0 and going right is 1.
         //System.out.println the binary message 
      	//Write the binary message to the hard drive using the file name ("message." + middlePart + ".txt")
         //System.out.println the scheme from the tree--needs a recursive helper method
      	//Write the scheme to the hard drive using the file name ("scheme." + middlePart + ".txt")
      Map<String, Integer> table = makeFrequencyTable(message);
    
      PriorityQueue<HuffmanTreeNode> pq = makePriorityQueue(table);
      
      PriorityQueue<HuffmanTreeNode> pq2 = new PriorityQueue<HuffmanTreeNode>(pq);
    
      HuffmanTreeNode tree = makeHuffmanTreeNode(pq);
      
      HashMap<String, String> scheme = findScheme(pq2, tree);
      
      String code = findHuffmanCode(message, scheme);
               
      PrintWriter messageScanner = new PrintWriter(new FileWriter(new File("message."+middlePart+".txt")));
      messageScanner.println(code);
      messageScanner.close();
   
      System.setOut(new PrintStream(new FileOutputStream(new File("scheme." + middlePart + ".txt"))));
      printScheme(scheme, tree);
            
   }
   public static HashMap<String, Integer> makeFrequencyTable(String s)
   {
      HashMap<String, Integer> map = new HashMap<String, Integer>();
      for(char c:s.toCharArray())
      {
         if(!map.containsKey(c+""))
            map.put(c+"", 1);
         else
            map.put(c+"", map.get(c+"")+1);
      }
      return map;
   }
   public static PriorityQueue<HuffmanTreeNode> makePriorityQueue(Map<String, Integer> m)
   {
      PriorityQueue<HuffmanTreeNode> pq = new PriorityQueue<HuffmanTreeNode>();
      Iterator it = m.keySet().iterator();
      while(it.hasNext())
      {
         String str = it.next().toString();
         pq.add(new HuffmanTreeNode(str, m.get(str)));
      }
      return pq;
   }
   public static HuffmanTreeNode makeHuffmanTreeNode(PriorityQueue<HuffmanTreeNode> p)
   {
      while(p.size()>1)
      {
         HuffmanTreeNode temp = new HuffmanTreeNode(p.remove(), p.remove());
         p.add(temp);
      }
      return p.remove();
   }
   public static HashMap<String, String> findScheme(PriorityQueue p, HuffmanTreeNode t)
   {
      HashMap<String, String> map = new HashMap<String, String>();
      while(p.size()>0)
      {
         String str = ((HuffmanTreeNode)p.remove()).getValue();
         map.put(str, find(str, t));
      }
      return map;
   }
   public static void printScheme(Map<String, String> m, HuffmanTreeNode t)
   {
      if(t==null)
         return;
      else
      {
         if(!t.getValue().equals("*"))
            System.out.println(t.getValue() + m.get(t.getValue()));
         printScheme(m, t.getLeft());
         printScheme(m, t.getRight());
      }
   }
   public static String find(String s, HuffmanTreeNode t)
   {
      if(t==null)
         return null;
      else if(t.getValue().equals(s))
         return "";
      else
      {
         String str1 = find(s, t.getLeft());
         String str2 = find(s, t.getRight());
         if(str1!=null)
            return "0" + str1;
         else if(str2!=null)
            return "1" + str2;
         else
            return null;      
      }
   }
   public static String findHuffmanCode(String str, Map<String, String> m)
   {
      String s = "";
      for(int i=0;i<str.length();i++)
      {
         s+=m.get(str.charAt(i)+"");
      }   
      return s;
   }
}
	/*
	  * This tree node stores two values.  Look at TreeNode's API for some help.
	  * The compareTo method must ensure that the lowest frequency has the highest priority.
	  */
class HuffmanTreeNode implements Comparable<HuffmanTreeNode>
{
   private String str; 
   private int freq;
   private HuffmanTreeNode left, right;
   
   public HuffmanTreeNode(String s, int f)
   { 
      str = s;
      freq = f; 
      left = null; 
      right = null; 
   }
   
   public HuffmanTreeNode(HuffmanTreeNode initLeft, HuffmanTreeNode initRight)
   { 
      str = "*";
      freq = initLeft.getFrequency() + initRight.getFrequency();
      left = initLeft; 
      right = initRight; 
   }
   
   public String getValue()
   { 
      return str; 
   }
   
   public HuffmanTreeNode getLeft() 
   { 
      return left; 
   }
   
   public HuffmanTreeNode getRight() 
   { 
      return right; 
   }
   
   public int getFrequency()
   {
      return freq;
   }
   
   public void setValue(String theNewValue) 
   { 
      str = theNewValue; 
   }
   
   public void setLeft(HuffmanTreeNode theNewLeft) 
   { 
      left = theNewLeft;
   }
   
   public void setRight(HuffmanTreeNode theNewRight)
   { 
      right = theNewRight;
   }
   
   public void setFrequency(int f)
   {
      freq = f;
   }
   
   public int compareTo(HuffmanTreeNode t)
   {
      return freq - t.getFrequency();
   }
   public String toString()
   {
      return str+":"+freq;
   }

}