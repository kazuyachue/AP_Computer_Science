// Name:    Date:
import java.util.*;
import java.io.*;
public class SetsOfLetters
{
   public static void main(String[] args) throws FileNotFoundException
   {
      //Scanner sc = new Scanner(System.in);
      //System.out.print("Enter the file name: ");
      //String fileName = sc.next();
      String fileName = "declarationLast.txt";
      fillTheSets(fileName);
   }
   public static void fillTheSets(String fn) throws FileNotFoundException
   {
      Scanner infile = new Scanner(new File(fn));
      Set<String> commonLower = new HashSet<String>();
      Set<String> commonUpper = new HashSet<String>();
      Set<String> commonOther = new HashSet<String>();
      boolean bool = true;
            
      while(infile.hasNextLine())
      {
         String str = infile.nextLine();
         System.out.println(str);   
         Set<String> setLower = new HashSet<String>();
         Set<String> setUpper = new HashSet<String>();
         Set<String> setOther = new HashSet<String>();
         
         for(Character c: str.toCharArray())
         {
            if(!Character.isLetter(c))
               setOther.add(c+"");
            else if(Character.isLowerCase(c))
               setLower.add(c+"");
            else
               setUpper.add(c+"");
         }
         
         Set<String> setLower2 = new TreeSet<String>(setLower);
         Set<String> setUpper2 = new TreeSet<String>(setUpper);
         Set<String> setOther2 = new TreeSet<String>(setOther);         
         
         System.out.print("Lower Case: ");
         System.out.print(setLower2);
         System.out.print("\nUpper Case: ");
         System.out.print(setUpper2);
         System.out.print("\nOther: ");
         System.out.print(setOther2);
         System.out.println("\n");
            
         if(bool)
         {
            commonLower = setLower;
            commonUpper = setUpper;
            commonOther = setOther;
            bool = false;
         }
         else
         {
            commonLower = common(setLower, commonLower);
            commonUpper = common(setUpper, commonUpper);
            commonOther = common(setOther, commonOther);
         }
      }
      
      Set<String> commonLower2 = new TreeSet<String>(commonLower);
      Set<String> commonUpper2 = new TreeSet<String>(commonUpper);
      Set<String> commonOther2 = new TreeSet<String>(commonOther);       
      
      System.out.print("Common Lower Case: ");
      System.out.print(commonLower2);
      System.out.print("\n Common Upper Case: ");
      System.out.print(commonUpper2);
      System.out.print("\n Common Other: ");
      System.out.print(commonOther2);
   }
   private static Set<String> common(Set<String> s, Set<String> c)
   {
      ArrayList<String> toDelete = new ArrayList<String>();
      Iterator<String> it = c.iterator();
      while(it.hasNext())
      {
         String temp = it.next();
         if(!s.contains(temp))
            toDelete.add(temp);
      }
      for(String x: toDelete)
         c.remove(x);         
      return c;   
   }
}