//Ms. Galanos
//version 12.7.2016

import twitter4j.*;       //set the classpath to lib\twitter4j-core-4.0.4.jar
import java.util.List;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;
import java.util.StringTokenizer;
import java.lang.Thread;

public class Twitter_Driver
{
   private static PrintStream consolePrint;

   public static void main (String []args) throws TwitterException, IOException, InterruptedException
   {
      consolePrint = System.out; // this preserves the standard output so we can get to it later      
   
      // PART 1
      // set up classpath and properties file
          
      TJTwitter bigBird = new TJTwitter(consolePrint);
      
      // Create and set a String called message here
   
   /*
     String message = "I just tweeted from Java";
     bigBird.tweetOut(message);
       */
       
   
      // PART 2
      // Choose a public Twitter user's handle 
     
      /*Scanner scan = new Scanner(System.in);
      consolePrint.print("Please enter a Twitter handle, do not include the @symbol --> ");
      String twitter_handle = scan.next();
     
      // Find and print the most popular word they tweet 
   
      while (!twitter_handle.equals("done"))
      {
         bigBird.queryHandle(twitter_handle);
         consolePrint.println("The most common word from @" + twitter_handle + " is: " + bigBird.mostPopularWord()+ ".");
         consolePrint.println("The word appears " + bigBird.getFrequencyMax() + " times.");
         consolePrint.println();
         consolePrint.print("Please enter a Twitter handle, do not include the @ symbol --> ");
         twitter_handle = scan.next();
      } */
   
      // PART 3
      bigBird.investigate();
      
      
   }//end main         
      
}//end driver        
      
class TJTwitter 
{
   private Twitter twitter;
   private PrintStream consolePrint;
   private List<Status> statuses;
   private List<String> terms;
   private String popularWord;
   private int frequencyMax;
  
   public TJTwitter(PrintStream console)
   {
      // Makes an instance of Twitter - this is re-useable and thread safe.
      // Connects to Twitter and performs authorizations.
      twitter = TwitterFactory.getSingleton(); 
      consolePrint = console;
      statuses = new ArrayList<Status>();
      terms = new ArrayList<String>();
   }

  /******************  Part 1 *******************/
  /** 
   * This method tweets a given message.
   * @param String  a message you wish to Tweet out
   */
   public void tweetOut(String message) throws TwitterException, IOException
   {
      twitter.updateStatus(message);
   }

   
  /******************  Part 2 *******************/
  /** 
   * This method queries the tweets of a particular user's handle.
   * @param String  the Twitter handle (username) without the @sign
   */
   @SuppressWarnings("unchecked")
   public void queryHandle(String handle) throws TwitterException, IOException
   {
      statuses.clear();
      terms.clear();
      fetchTweets(handle);
      splitIntoWords();	
      removeCommonEnglishWords();
      sortAndRemoveEmpties();
   }
	
  /** 
   * This method fetches the most recent 2,000 tweets of a particular user's handle and 
   * stores them in an arrayList of Status objects.  Populates statuses.
   * @param String  the Twitter handle (username) without the @sign
   */
   public void fetchTweets(String handle) throws TwitterException, IOException
   {
      // Creates file for dedebugging purposes
      PrintStream fileout = new PrintStream(new FileOutputStream("tweets.txt")); 
      Paging page = new Paging (1,200);
      int p = 1;
      while (p <= 10)
      {
         page.setPage(p);
         statuses.addAll(twitter.getUserTimeline(handle,page)); 
         p++;        
      }
      int numberTweets = statuses.size();
      fileout.println("Number of tweets = " + numberTweets);
   
      int count=1;
      for (Status j: statuses)
      {
         fileout.println(count+".  "+j.getText());
         count++;
      }
   } 
   public ArrayList<String> fetchTweets(String handle, String file) throws TwitterException, IOException
   {
      // Creates file for dedebugging purposes
      statuses.clear();
      PrintStream fileout = new PrintStream(new FileOutputStream(file)); 
      Paging page = new Paging (1,100);
      int p = 1;
      while (p <= 10)
      {
         page.setPage(p);
         statuses.addAll(twitter.getUserTimeline(handle,page)); 
         p++;        
      }
   
      ArrayList<String> tweets = new ArrayList<String>();
      
      for (Status j: statuses)
      {
         fileout.println(j.getText());
         tweets.add(j.getText());
      }
         
      return tweets;
   }   
  

  /** 
   * This method takes each status and splits them into individual words.   
   * Remove punctuation by calling removePunctuation, then store the word in terms.  
   */
   public void splitIntoWords()
   {
      for(Status i: statuses)
      {
         StringTokenizer st = new StringTokenizer(i.getText());
         while(st.hasMoreTokens())
            terms.add(removePunctuation(st.nextToken()));
         
      }
   }

  /** 
   * This method removes common punctuation from each individual word.
   * Consider reusing code you wrote for a previous lab.     
   * Consider if you want to remove the # or @ from your words. Could be interesting to keep (or remove).
   * @ param String  the word you wish to remove punctuation from
   * @ return String the word without any punctuation  
   */
   private String removePunctuation( String s )
   {
      final String punct = ".,?!:;\"(){}[]<>=+-—_@&$%^#";
   
      for(int i=0;i<s.length();i++)
      {
         if(punct.indexOf(s.charAt(i))!=-1)
         {
            s = s.substring(0,i) + s.substring(i+1,s.length());
            i--;
         }
      }
      return s;
   }

  /** 
   * This method removes common English words from the list of terms.
   * Remove all words found in commonWords.txt  from the argument list.    
   * The count will not be given in commonWords.txt. You must count the number of words in this method.  
   * This method should NOT throw an excpetion.  Use try/catch.   
   */
   @SuppressWarnings("unchecked")
   private void removeCommonEnglishWords()
   {	
      try
      {
         Scanner infile = new Scanner(new File("commonWords.txt"));
         ArrayList<String> commonWords = new ArrayList<String>();
         while(infile.hasNext())
            commonWords.add(infile.next());
         for(int i=0;i<terms.size();i++)
         {
            for(String x: commonWords)
            {
               if(terms.get(i).equalsIgnoreCase(x))
               {
                  terms.remove(i);
                  i--;
                  break;
               }
            }
         }
      
      }
      catch(FileNotFoundException e)
      {
         System.out.println("oops!");
         e.printStackTrace();      
      }
   }

  /** 
   * This method sorts the words in terms in alphabetically (and lexicographic) order.  
   * You should use your sorting code you wrote earlier this year.
   * Remove all empty strings while you are at it.  
   */
   @SuppressWarnings("unchecked")
   public void sortAndRemoveEmpties()
   {
      terms = sort(terms);
      for(int i=0;i<terms.size();i++)
         if(terms.get(i).isEmpty())
         {
            terms.remove(i);
            i--;
         }   
   }
   public static List<String> sort(List<String>  list)
   { 
      for(int i=0;i<list.size();i++)
      {
         swap(list,list.size()-1-i,findMax(list,i));
      }
      return list;
   }
   private static int findMax(List<String> list, int n)
   {
      int max = 0;
      for(int i=1;i<list.size()-n;i++)
         if(list.get(i).compareTo(list.get(max))>0)
            max = i;
      return max;
   }
   private static void swap(List<String> list, int a, int b)
   {
      String temp = list.get(a);
      list.set(a, list.get(b));
      list.set(b, temp);
   }

  /** 
   * This method returns the most common word from terms.    
   * Consider case - should it be case sensitive?  The choice is yours.
   * @return String the word that appears the most times
   * @post will popopulate the frequencyMax variable with the frequency of the most common word 
   */
   @SuppressWarnings("unchecked")
   public String mostPopularWord()
   {
      frequencyMax = 0;
      String popular = "";
      int counter = 1;
   
      for(int i=1;i<terms.size();i++)
      {
         if(terms.get(i-1).equals(terms.get(i)))
            counter++;
         else
         {
            if(counter > frequencyMax)
            {
               frequencyMax = counter;
               popular = terms.get(i-1);
            }
            counter = 1;
         }
      }
      return popular;
   }
  
  /** 
   * This method returns the number of times the most common word appears.    
   * Note:  variable is populated in mostPopularWord()
   * @return int frequency of most common word
   */
   public int getFrequencyMax()
   {
      return frequencyMax;
   }

//By Kazuya Chue and Zach Nguyen
  /******************  Part 3 *******************/
   public void investigate () throws TwitterException, IOException, InterruptedException
   {
      Scanner scan = new Scanner(System.in);
           
      consolePrint.print("Welcome to the Twitter Quiz!\nPlease enter 4 Twitter handles, do not include the @symbol -->\n");
      consolePrint.print("1. ");
      String twitter_handle1 = scan.next();
      consolePrint.print("2. ");
      String twitter_handle2 = scan.next();
      consolePrint.print("3. ");
      String twitter_handle3 = scan.next();
      consolePrint.print("4. ");
      String twitter_handle4 = scan.next();
      
      //System.out.println(twitter_handle1 + ", " + twitter_handle2 + ", " + twitter_handle3 + ", " + twitter_handle4);
   
      
      ArrayList<String> tweets1 = fetchTweets(twitter_handle1,"tweets1.txt");
      ArrayList<String> tweets2 = fetchTweets(twitter_handle2,"tweets2.txt");
      ArrayList<String> tweets3 = fetchTweets(twitter_handle3,"tweets3.txt");
      ArrayList<String> tweets4 = fetchTweets(twitter_handle4,"tweets4.txt");
      
      consolePrint.print("Please enter the number of questions you would like! (1-100)  ");
      int num = scan.nextInt();
      
      System.out.println("\nWe will now give you a multiple choice tweet quiz! Match each tweet to a handle!\n");
      Thread.sleep(6000);
      
      game(num, tweets1, tweets2, tweets3,tweets4, twitter_handle1, twitter_handle2, twitter_handle3, twitter_handle4, scan);
   }
   public void game(int num, ArrayList<String> tweets1, ArrayList<String> tweets2, ArrayList<String> tweets3, ArrayList<String> tweets4, String twitter_handle1, String twitter_handle2, String twitter_handle3, String twitter_handle4, Scanner scan) throws InterruptedException
   {
      int correct = 0;
      
      for(int x = 0; x < num; x++)
      {
         double random = Math.random();
         int r = (int) (Math.random() * 99 + 1);
       
         if(random < 0.25)
         {
            consolePrint.println(tweets1.get(r));
            consolePrint.println("\nWho tweeted this? (" + twitter_handle1 + ", " + twitter_handle2 + ", " + twitter_handle3 + ", " + twitter_handle4 +")");
            if(quiz(twitter_handle1, scan))
               correct++;
         }
         else if(random < 0.5)
         {         
            consolePrint.println(tweets2.get(r));
            consolePrint.println("\nWho tweeted this? (" + twitter_handle1 + ", " + twitter_handle2 + ", " + twitter_handle3 + ", " + twitter_handle4 +")");
            if(quiz(twitter_handle2, scan))
               correct++;
         }
         else if(random < 0.75)
         {
            consolePrint.println(tweets3.get(r));
            consolePrint.println("\nWho tweeted this? (" + twitter_handle1 + ", " + twitter_handle2 + ", " + twitter_handle3 + ", " + twitter_handle4 +")");
            boolean bool =  quiz(twitter_handle3, scan);
            if(bool)
               correct++;
         }
         else
         {
            consolePrint.println(tweets4.get(r));
            consolePrint.println("\nWho tweeted this? (" + twitter_handle1 + ", " + twitter_handle2 + ", " + twitter_handle3 + ", " + twitter_handle4 +")");
            boolean bool =  quiz(twitter_handle4, scan);
            if(bool)
               correct++;
         }
         Thread.sleep(2000);
      }
      scoreReport(correct, num);
   }
   public boolean quiz(String handle, Scanner scan)
   {
      if(scan.next().equalsIgnoreCase(handle))
      {
         consolePrint.println("Correct!\n");
         return true;
      }
      else
      {
         consolePrint.println("Wrong :( Correct Answer is: " + handle +"\n");
         return false;
      }
   }
   public void scoreReport(int correct, int num)
   {
      consolePrint.println("Your score: " + correct + " correct answers!");
      
      if(correct < (0.6*num))
         consolePrint.println("Try Again! You got an F");
      else if(correct < (0.7*num))
         consolePrint.println("Try Again! You got a D");
      else if(correct < (0.8*num))
         consolePrint.println("Try Again! You got a C");
      else if(correct < (0.9*num))
         consolePrint.println("Good job! You got a B");
      else if(correct < num)
         consolePrint.println("Good job! You got a A");
      else if(correct == num)
         consolePrint.println("Perfect!!! You got an A+!");
   }
  
  /** 
   * This method determines how many people in Arlington, VA 
   * tweet about the Miami Dolphins.  Hint:  not many. :(
   */
   public void sampleInvestigate ()
   {
      Query query = new Query("Miami Dolphins");
      query.setCount(100);
      query.setGeoCode(new GeoLocation(38.8372839,-77.1082443), 5, Query.MILES);
      query.setSince("2015-12-1");
      try {
         QueryResult result = twitter.search(query);
         System.out.println("Count : " + result.getTweets().size()) ;
         for (Status tweet : result.getTweets()) {
            System.out.println("@"+tweet.getUser().getName()+ ": " + tweet.getText());  
         }
      } 
      catch (TwitterException e) {
         e.printStackTrace();
      } 
      System.out.println(); 
   }  

}  

