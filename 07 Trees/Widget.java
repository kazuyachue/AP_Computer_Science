	//Torbert, e-mail: mr@torbert.com, website: www.mr.torbert.com
	//version 7.22.2003
	//mlbillington@fcps.edu  version 10/14/05, 10/09/2006, 4/25/2007

/*************************************** 
Modify the Widget class so that it hashes on its
values, not on its address.   Be sure that compareTo(),
equals(Object) and hashCode() agree with each other.
****************************************/
    public class Widget implements Comparable<Widget>
   {
   	//data fields
      private int myPounds, myOunces;
   	//constructors
       public Widget()
      {
         myPounds = myOunces = 0;
      }
       public Widget(int x)
      {
         myPounds = x;
         myOunces = 0;
      }
       public Widget(int x, int y)
      {
         myPounds = x;
         myOunces = y;
      }
       public Widget(Widget arg)
      {
         myPounds = arg.getPounds();
         myOunces = arg.getOunces();
      }
   	//accessors and modifiers
       public int getPounds()
      {
         return myPounds;
      }
       public int getOunces()
      {
         return myOunces;
      }
       public void setPounds(int x)
      {
         myPounds = x;
      }
       public void setOunces(int x)
      {
         myOunces = x;
      }
   	//other methods
       public int compareTo(Widget w)
      {
         if(myPounds < w.getPounds())
            return -1;
         if(myPounds > w.getPounds())
            return 1;
         if(myOunces < w.getOunces())
            return -1;
         if(myOunces > w.getOunces())
            return 1;
         return 0;
      }
       public boolean equals(Widget arg)    
      {
         return compareTo(arg) == 0;
      }
       public String toString()
      {
         return myPounds + " lbs. " + myOunces + " oz.";
      }
      
          /* 2 new methods for this lab */
//           public boolean equals(Object arg)
//          {
//               return equals((Widget)arg );
//          }
//           public int hashCode()
//          {
//             return toString().hashCode();
//          }   
   }