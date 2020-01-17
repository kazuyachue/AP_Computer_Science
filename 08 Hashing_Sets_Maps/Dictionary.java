   //Name:     Date:
import java.io.*;
import java.util.*;
public class Dictionary
{
   public static void main(String[] args) 
   {
      Scanner infile = null;
      try
      {
         infile = new Scanner(new File("spanglish.txt"));
         System.setOut(new PrintStream(new FileOutputStream("dictionaryOutput.txt")));
      }
      catch(Exception e)
      {
      }
      
      Map<String, Set<String>> eng2spn = makeDictionary( infile );
      System.out.println("ENGLISH TO SPANISH");
      display(eng2spn);
   
      Map<String, Set<String>> spn2eng = reverse(eng2spn);
      System.out.println("SPANISH TO ENGLISH");
      display(spn2eng);
   }
   public static Map<String, Set<String>> makeDictionary(Scanner infile)
   {
      Map<String, Set<String>> dictionary = new HashMap<String, Set<String>>();
      
      while(infile.hasNext())
      {
         String english = infile.next();
         String spanish = infile.next();
         add(dictionary, english, spanish);
      }
      
      return dictionary;
   }
   private static void add(Map<String, Set<String>> dictionary, String word, String translation)
   { 
      Set<String> temp;
      
      if(dictionary.containsKey(word))
      {
         temp = dictionary.get(word);
         temp.add(translation);
      }
      else
      {
         temp = new TreeSet<String>();
         temp.add(translation);
      }
      
      dictionary.put(word,temp);   
   }
   public static void display(Map<String, Set<String>> m)
   {
      Map<String, Set<String>> m2 = new TreeMap<String, Set<String>>(m);
      Set<String> key = m2.keySet();
      Iterator it = key.iterator();
   
      while(it.hasNext())
      {
         Object obj = it.next();
         System.out.println("\t" + obj + " " + m2.get(obj));
      }
      System.out.println();
   }
   public static Map<String, Set<String>> reverse(Map<String, Set<String>> dictionary)
   {
      Map<String, Set<String>> reversed = new HashMap<String, Set<String>>();
      for(Map.Entry<String, Set<String>> entry: dictionary.entrySet())
      {
         Set<String> temp = entry.getValue();
         Iterator it = temp.iterator();
         while(it.hasNext())
            add(reversed,it.next().toString(),entry.getKey());
      }
      return reversed;
   }
}
      /********************
	INPUT:
   	holiday
		fiesta
		holiday
		vacaciones
		party
		fiesta
		celebration
		fiesta
     <etc.>
  *********************************** 
	OUTPUT:
		ENGLISH TO SPANISH
			banana [banana]
			celebration [fiesta]
			computer [computadora, ordenador]
			double [doblar, doble, duplicar]
			father [padre]
			feast [fiesta]
			good [bueno]
			hand [mano]
			hello [hola]
			holiday [fiesta, vacaciones]
			party [fiesta]
			plaza [plaza]
			priest [padre]
			program [programa, programar]
			sleep [dormir]
			son [hijo]
			sun [sol]
			vacation [vacaciones]

		SPANISH TO ENGLISH
			banana [banana]
			bueno [good]
			computadora [computer]
			doblar [double]
			doble [double]
			dormir [sleep]
			duplicar [double]
			fiesta [celebration, feast, holiday, party]
			hijo [son]
			hola [hello]
			mano [hand]
			ordenador [computer]
			padre [father, priest]
			plaza [plaza]
			programa [program]
			programar [program]
			sol [sun]
			vacaciones [holiday, vacation]

**********************/