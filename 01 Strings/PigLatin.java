//name: Kazuya Chue     date:  9/6/16

import java.util.*;
import java.io.*;
public class PigLatin
{
   public static void main(String[] args) throws IOException
   {
      //part_1_using_pig();
      part_2_using_piglatenizeFile();
   }
   
   public static void part_1_using_pig()
   {
      Scanner sc = new Scanner(System.in);
      while(true)
      {
         System.out.print("\nWhat word? ");
         String s = sc.next();
         if (s.equals("-1")) 
            System.exit(0);
         String p = pig(s);
         System.out.println("***** " + p + " *****");
      }		
   }
   public static String pig(String s)
   {
      char ch;
      boolean capitalized;
      boolean hasPunctuation;
      String piglatin = s;
      String punct1,punct2;
      punct1 = punct2 = "";
      final String punct = ".,?!:;\"(){}[]<>";
      
      if(punct.indexOf(s.charAt(0))==-1&&punct.indexOf(s.charAt(s.length()-1))==-1)
         hasPunctuation = false;
      else 
      {
         hasPunctuation = true;
         while(punct.indexOf(s.charAt(0))!=-1)
         {
            punct1 = punct1 + s.charAt(0);
            s = s.substring(1);
         }
         while(punct.indexOf(s.charAt(s.length()-1))!=-1)
         {
            punct2 = s.charAt(s.length()-1) + punct2;
            s = s.substring(0,s.length()-1);
         }
      
      }
   
      capitalized = Character.isUpperCase(s.charAt(0));
            
      s = Character.toLowerCase(s.charAt(0))+s.substring(1);
      
      ch = s.charAt(0);
      
      if(ch=='a' || ch=='e' || ch=='i'|| ch=='o' || ch=='u')
      {
         piglatin = s + "way";
      }
      
      else
      {
         for(int i=1;i<s.length();i++){
            ch = s.charAt(i);
            if( ch=='u' || s.charAt(i-1)=='q')
            {
               piglatin = s.substring(i+1) + s.substring(0,i+1) + "ay";
               break;
            }         
            else if(ch=='a' || ch=='e' || ch=='i'|| ch=='o' || ch=='u' || ch=='y')
            {
               piglatin = s.substring(i) + s.substring(0,i) + "ay";
               break;
            }
            else if (i==s.length()-1 && ch!='a' && ch!='e' && ch!='i'&& ch!='o' && ch!='u' && ch!='y')
               piglatin = "INVALID";
         }  
         
      }
      
     /* if(!piglatin.equals("INVALID"))
      {      
         String original = piglatin;
         piglatin = "";
         for(int i = original.length()-1;i>=0;i--)
         {
            piglatin = piglatin + original.charAt(i);
         } 
      }*/
      
      if(capitalized)
         piglatin = Character.toUpperCase(piglatin.charAt(0))+piglatin.substring(1);
      if(hasPunctuation)
         piglatin = punct1 + piglatin + punct2;   
      
      return piglatin;
      
   }

   public static void part_2_using_piglatenizeFile() throws IOException 
   {
      Scanner sc = new Scanner(System.in);
      System.out.print("Input Filename (Including .txt)? Example: PigLatin.txt:");
      String filename = sc.next();
      Scanner infile = new Scanner(new File(filename));  //PigLatin.txt
      System.out.print("Output Filename (Including .txt)? Example: PigLatinOut.txt:");
      String filenameOut = sc.next();
      piglatenizeFile( infile, filenameOut );
      System.out.println("Piglatin done!");
      sc.close();
   }
   /****************************** 
   *  take in a filename, and creates a file that is the inputted file
   *  fully piglatenized. The outputFile should be in piglatin form
   *  PigLatin.txt should become IgLatinpay.txt.
   *
   *  Note: filename will have .txt on it.
   ******************************/
   public static void piglatenizeFile(Scanner infile, String filename) throws IOException
   {   
      try{
         StringTokenizer st;  
         
        // filename = filename.substring(0,filename.length()-4);
        System.setOut(new PrintStream(new FileOutputStream(filename)));
        
         while(infile.hasNext())
         {
            st = new StringTokenizer(infile.nextLine());
            while(st.hasMoreTokens())
            {
               System.out.print(pig(st.nextToken())+" ");         
            }
            System.out.println("");
         }
      
      }
      catch(NullPointerException e){
         System.out.println("Error: File Not Found");
         System.exit(0);
      }
      System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
   }
}
