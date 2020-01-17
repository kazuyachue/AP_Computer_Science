public class Testing
{
   static int count = 0;
   public static void main(String[] args)
   {
      printStar(20);
      System.out.println(count+"");
   }
   public static void printStar(int nNum)
   {
      if(nNum>0)
      {
         count++;
         printStar(nNum/2);
      }
   }
}