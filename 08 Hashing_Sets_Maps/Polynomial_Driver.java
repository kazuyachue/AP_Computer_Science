 //Name:   Date:
 //modeling a polynomial using a treeMap
import java.util.*;
public class Polynomial_Driver
{
   public static void main(String[] args)
   {
      Polynomial poly = new Polynomial();
      poly.makeTerm(1, -4);
      poly.makeTerm(3, 2);
      poly.makeTerm(0, 2);
      System.out.println(poly.toString());
      double evaluateAt = 2.0;
      System.out.println("Evaluated at "+ evaluateAt +": " +poly.evaluateAt(evaluateAt));
   	   	
      Polynomial poly2 = new Polynomial();
      poly2.makeTerm(1, -5);
      poly2.makeTerm(4, 2);
      poly2.makeTerm(0, -3);
      poly2.makeTerm(2, 1); 
      System.out.println(poly2.toString());
   	
      System.out.println(poly.add(poly2));
      System.out.println(poly.multiply(poly2));
   }
}
interface PolynomialInterface
{
   public void makeTerm(Integer exp, Integer coef);
   public double evaluateAt(double x);
   public Polynomial add(Polynomial other);
   public Polynomial multiply(Polynomial other);
   public String toString();
}

class Polynomial implements PolynomialInterface
{
   Map<Integer, Integer> map;
   public Polynomial()
   {
      map = new TreeMap<Integer, Integer>();
   }
   public void makeTerm(Integer exp, Integer coef)
   {
      if(map.get(exp)==null)  
         map.put(exp, coef);
      else
      {
         int coefficient = map.get(exp);
         map.put(exp, coef+coefficient);
      }
   }
   public double evaluateAt(double x)
   {
      double value = 0.0;
      Set<Integer> set = map.keySet();
      Iterator it = set.iterator();
      while(it.hasNext())
      {
         int exponent = Integer.parseInt(it.next()+"");
         value+=Math.pow(x,exponent)*map.get(exponent);
      }
      return value;
   }
   public Polynomial add(Polynomial other)
   {
      Polynomial add = new Polynomial();
      Set<Integer> set = map.keySet();
      Iterator it = set.iterator();
      while(it.hasNext())
      {
         int exponent = Integer.parseInt(it.next()+"");
         if(map.get(exponent)!=null&&other.map.get(exponent)!=null)
            add.makeTerm(exponent, map.get(exponent)+other.map.get(exponent));
         else
            add.makeTerm(exponent, map.get(exponent));
      }
      Set<Integer> set2 = other.map.keySet();
      Iterator it2 = set2.iterator();
      while(it2.hasNext())
      {
         int exponent2 = Integer.parseInt(it2.next()+"");
         if(!set.contains(exponent2))
            add.makeTerm(exponent2, other.map.get(exponent2));
      }
      return add;
   }
   public Polynomial multiply(Polynomial other)
   {
      Polynomial multiply = new Polynomial();
      Set<Integer> set = map.keySet();
      Set<Integer> set2 = other.map.keySet();
      Iterator it = set.iterator();
            
      while(it.hasNext())
      {
         Iterator it2 = set2.iterator();
         int exp = Integer.parseInt(it.next()+"");
         while(it2.hasNext())
         {
            int exp2 = Integer.parseInt(it2.next()+"");
            multiply.makeTerm(exp+exp2, map.get(exp) * other.map.get(exp2));
         }
      }
      return multiply;
   }
   public String toString()
   {
      String display = "";
   
      Set<Integer> set = map.keySet();
      Iterator it = set.iterator();
      while(it.hasNext())
      {
         int exponent = Integer.parseInt(it.next()+"");
         if(map.get(exponent)==0);
         else if(map.get(exponent)==1)
         {
            if(exponent>1)
               display = "x^" + exponent + " + " + display;
            else if(exponent==1)
               display = "x + " + display; 
            else
               display = map.get(exponent) + display;
         }
         else if(map.get(exponent)==-1)
         {
            if(exponent>1)
               display = "-x^" + exponent + " + " + display;
            else if(exponent==1)
               display = "-x + " + display; 
            else
               display = map.get(exponent) + display;
         }
         else
         {
            if(exponent>1)
               display = map.get(exponent) + "x^" + exponent + " + " + display;
            else if(exponent==1)
               display = map.get(exponent) + "x + " + display; 
            else
               display = map.get(exponent) + display;
         }
      }
      return display;
   }
}
/*  
expected output
   2x^3 + -4x + 2
   10.0
   2x^4 + x^2 + -5x + -3
   2x^4 + 2x^3 + x^2 + -9x + -1
   4x^7 + -6x^5 + -6x^4 + -10x^3 + 22x^2 + 2x + -6
 */