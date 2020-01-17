//name:     date:
   
import java.util.*;
public class Postfix
{
   public static void main(String[] args)
   {
      System.out.println("Postfix  -->  Evaluate");
      ArrayList<String> postExp = new ArrayList<String>();
      /*  enter tests here  */
      postExp.add("345*+");
      postExp.add("34*5+");
      postExp.add("45+53*-");
      postExp.add("34+56+*");
      postExp.add("345+*2-5/");
      postExp.add("812*+93/-");
      
      
      for( String pf : postExp )
      {
         System.out.println(pf + "\t\t" + eval(pf));
      }
   }
   public static int eval(String postfix)
   {
      Stack<String> stack = new Stack<String>();
      for(char ch: postfix.toCharArray())
      {
         if(isOperator(ch))
            stack.push(eval(Integer.parseInt(stack.pop()),Integer.parseInt(stack.pop()),ch)+"");
         else
            stack.push(ch+"");      
      }
      return Integer.parseInt(stack.pop());
   }
   public static int eval(int a, int b, char ch)
   {
      if(ch=='+')
         return a+b;
      else if(ch=='-')
         return b-a;
      else if(ch=='*')
         return a*b;
      else
         return b/a;
   }
   public static boolean isOperator(char ch)
   {
      if("+-*/".indexOf(ch)!=-1)
         return true;
      else
         return false;
   }
}

/*
 Postfix  -->  Evaluate
 345*+		23
 34*5+		17
 45+53*-		-6
 34+56+*		77
 345+*2-5/		5
 812*+93/-		7  
 */