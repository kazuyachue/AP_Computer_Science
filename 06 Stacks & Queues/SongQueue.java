///name:    date:
//first program on queues.
import java.io.*;
import java.util.*;
public class SongQueue
{
   private static Scanner infile;
   private static Queue<String> songQueue;
   
   public static void main(String[] args) throws Exception
   {
      fillPlayList();
      printSongList();
      infile = new Scanner(System.in);
      String prompt = "\tAdd song (A), Play song (P), Delete song (D), Quit (Q):  ";
      System.out.print(prompt);
      String str = infile.next().toUpperCase();
      while(!str.equals("Q"))
      { 
         processRequest( str );
         System.out.print(prompt);
         str = infile.next().toUpperCase();;
      } 
      System.out.println();
      System.out.println("No more music for you today.  Goodbye!");
      infile.close();
   }
   public static void fillPlayList()throws IOException
   {
      infile = new Scanner(new File("songs.txt"));
      songQueue = new LinkedList<String>();
      while(infile.hasNext())
      {
         String[] temp = infile.nextLine().split(" - ");
         songQueue.add(temp[0]);
      }
   }
   public static void processRequest(String str)
   {
      if(str.equals("A"))
         add();
      else if(str.equals("P"))
      {
         if(songQueue.isEmpty())
         {
            System.out.println("\tError, no songs to play.");
            printSongList();
         }
         else
            play();
      }
      else if(str.equals("D"))
      {
         if(songQueue.isEmpty())
         {
            System.out.println("\tError, no songs to delete.");
            printSongList();
         }
         else
            delete();
      }
      else
         System.out.println("Try again!");
   }
   public static void add()
   {
      System.out.print("\tSong to add? ");
      songQueue.add(infile.next());   
      printSongList();
   }
   public static void play()
   {
      System.out.println("\nNow playing: " + songQueue.remove());
      printSongList();
   }
   public static void delete()
   {
      System.out.print("\tEnter song to delte (exact macth): ");
      String delete = infile.next();
      boolean deleted = false;
      String first = songQueue.peek();
      String current = songQueue.remove();
      
      
      while(true)
      {
         if(current.equals(delete))
            deleted = true;
         else if(songQueue.peek().equals(first))
         {
            songQueue.add(current);
            break;
         }
         else
            songQueue.add(current);   
         current = songQueue.remove();
      }
      if(!deleted)
         System.out.println("\tError, song not found in queue.");
      printSongList();
   }
   public static void printSongList()
   {
      System.out.println("\nYour music queue: " + songQueue + "\n");
   }
}