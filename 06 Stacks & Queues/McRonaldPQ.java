//name :   date:

import java.util.*;
public class McRonaldPQ
{
   public static final int TIME = 1079;  //18 hrs * 60 min
   public static void main(String[] args)
   {
      int[] numberCustomers = {0, 0, 0 ,0};
      int[] totalWait = {0, 0, 0 ,0};
      int[] longestWait = {0, 0, 0 ,0};
      int[] servingTime = {100, 100, 100};
      int[] count = {0, 0, 0};
                  
      PriorityQueue<Customer> customers = new PriorityQueue<Customer>();
      Queue<Customer> service1 = new LinkedList<Customer>();
      Queue<Customer> service2 = new LinkedList<Customer>();
      Queue<Customer> service3 = new LinkedList<Customer>();
     
      for(int i=0; i<TIME; i++)
      {
         if(Math.random()<0.5)
         {
            Customer c = new Customer(i);
            customers.add(c);
            numberCustomers[12-c.getGrade()]++;
         }
         
         if(!service1.isEmpty())
         {
            count[0]++;
            if(servingTime[0]==count[0])
            {
               Customer c = service1.remove();
               int x = i - c.getTime();
               totalWait[12-c.getGrade()]+=x;
               if(x>longestWait[12-c.getGrade()])
                  longestWait[12-c.getGrade()] = x; 
            }
         }
         if(!service2.isEmpty())
         {
            count[1]++;
            if(servingTime[1]==count[1])
            {
               Customer c = service2.remove();
               int x = i - c.getTime();
               totalWait[12-c.getGrade()]+=x;
               if(x>longestWait[12-c.getGrade()])
                  longestWait[12-c.getGrade()] = x;        
            }
         }
         if(!service3.isEmpty())
         {
            count[2]++;
            if(servingTime[2]==count[2])
            {
               Customer c = service3.remove();
               int x = i - c.getTime();
               totalWait[12-c.getGrade()]+=x;
               if(x>longestWait[12-c.getGrade()])
                  longestWait[12-c.getGrade()] = x; 
            }
         }
         
         
         if(service1.isEmpty()&&!customers.isEmpty())
         {
            service1.add(customers.remove());
            servingTime[0] = (int)(Math.random()*6+2);
            count[0] = 0;
         }   
         if(service2.isEmpty()&&!customers.isEmpty())
         {
            service2.add(customers.remove());
            servingTime[1] = (int)(Math.random()*6+2);
            count[1] = 0;
         }
         if(service3.isEmpty()&&!customers.isEmpty())
         {
            service3.add(customers.remove());
            servingTime[2] = (int)(Math.random()*6+2);
            count[2] = 0;
         }
      
         //System.out.print(i+": "); 
       //  display(customers);
      //     display(merge(service1, service2, service3, customers));  
      }
      
      int counter = 1079;
      while(!(customers.isEmpty()&&service1.isEmpty()&&service2.isEmpty()&&service3.isEmpty()))
      {
         if(!service1.isEmpty())
         {
            count[0]++;
            if(servingTime[0]==count[0])
            {
               Customer c = service1.remove();
               int x = counter - c.getTime();
               totalWait[12-c.getGrade()]+=x;
               if(x>longestWait[12-c.getGrade()])
                  longestWait[12-c.getGrade()] = x; 
            }
         }
         if(!service2.isEmpty())
         {
            count[1]++;
            if(servingTime[1]==count[1])
            {
               Customer c = service2.remove();
               int x = counter - c.getTime();
               totalWait[12-c.getGrade()]+=x;
               if(x>longestWait[12-c.getGrade()])
                  longestWait[12-c.getGrade()] = x;          
            }
         }
         if(!service3.isEmpty())
         {
            count[2]++;
            if(servingTime[2]==count[2])
            {
               Customer c = service3.remove();
               int x = counter - c.getTime();
               totalWait[12-c.getGrade()]+=x;
               if(x>longestWait[12-c.getGrade()])
                  longestWait[12-c.getGrade()] = x;            
            }
         }
         
         
         if(service1.isEmpty()&&!customers.isEmpty())
         {
            service1.add(customers.remove());
            servingTime[0] = (int)(Math.random()*6+2);
            count[0] = 0;
         }   
         if(service2.isEmpty()&&!customers.isEmpty())
         {
            service2.add(customers.remove());
            servingTime[1] = (int)(Math.random()*6+2);
            count[1] = 0;
         }
         if(service3.isEmpty()&&!customers.isEmpty())
         {
            service3.add(customers.remove());
            servingTime[2] = (int)(Math.random()*6+2);
            count[2] = 0;
         }
            
       //  System.out.print(counter+": "); 
       //  display(customers);
      //      display(merge(service1, service2, service3, customers));   
         counter++;     
      }
   
      System.out.println("Customer\tTotal Served\tLongest Wait\tAverage Wait");
      System.out.println("Senior\t\t" + numberCustomers[0] + "\t\t\t" + longestWait[0] + "\t\t\t" + (double)totalWait[0]/numberCustomers[0]);
      System.out.println("Junior\t\t" + numberCustomers[1] + "\t\t\t" + longestWait[1] + "\t\t\t" + (double)totalWait[1]/numberCustomers[1]);
      System.out.println("Sophomore\t" + numberCustomers[2] + "\t\t\t" + longestWait[2] + "\t\t\t" + (double)totalWait[2]/numberCustomers[2]);
      System.out.println("Freshman\t" + numberCustomers[3] + "\t\t\t" + longestWait[3] + "\t\t\t" + (double)totalWait[3]/numberCustomers[3]);
   
   }
   public static Queue<Integer> merge(Queue<Integer> a, Queue<Integer> b, Queue<Integer> c, Queue<Integer> d)
   {
      Queue<Integer>  temp = new LinkedList<Integer>();
      Queue<Integer>  temp1 = new LinkedList(a);
      Queue<Integer>  temp2= new LinkedList(b);
      Queue<Integer>  temp3= new LinkedList(c);
      Queue<Integer>  temp4 =new LinkedList(d);
   
      
      while(!temp1.isEmpty())
         temp.add(temp1.remove());
      while(!temp2.isEmpty())
         temp.add(temp2.remove());
      while(!temp3.isEmpty())
         temp.add(temp3.remove());
      while(!temp4.isEmpty())
         temp.add(temp4.remove());
   
      return temp;
   }
   public static void display(Queue<Integer> q)
   {
      System.out.println(q);
   }
}
class Customer implements Comparable<Customer>
{
   int grade;
   int time;
   public Customer(int i)
   {
      int x = (int)(Math.random()*4+1);
      switch(x)
      {
         case 1: grade = 12;
            break;
         case 2: grade = 11;
            break;
         case 3: grade = 10;
            break;
         case 4: grade = 9;
            break;
      }
      time = i;
   }
     
   public int getGrade()
   {
      return grade;
   }
   public int getTime()
   {
      return time;
   }
   public int compareTo(Customer c)
   {
      return c.getGrade() - grade;      
   }
}


