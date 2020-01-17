//name:   date: 
//for use with Graphs6: Dijkstra
//             Graphs7: Dijkstra with Cities

import java.io.*;
import java.util.*;

class Edge {
   public final wVertex target;
   public final double weight;
   
   public Edge(wVertex argTarget, double argWeight) {
      target = argTarget;
      weight = argWeight;
   }
}

class wVertex implements Comparable<wVertex>, wVertexInterface
{
   private final String name;
   private ArrayList<Edge> adjacencies;
   private double minDistance = Double.POSITIVE_INFINITY;
 
    /*  enter your code here   */ 
   public wVertex(String s)
   {
      name = s;
      adjacencies = new ArrayList<Edge>();
   }
   public String toString()     //just return the name
   {
      return name;
   }
   public String getName()
   {
      return name;
   }
   public double getMinDistance()
   {
      return minDistance;
   }   
   public void setMinDistance(double m)
   {
      minDistance = m;
   }
   public ArrayList<Edge> getAdjacencies()
   {
      return adjacencies;
   }
   public int compareTo(wVertex other)
   {
      if(minDistance<other.getMinDistance())
         return -1;
      else if(minDistance>other.getMinDistance())
         return 1;
      else
         return 0;
   }     
   public void addEdge(Edge e)
   {
      adjacencies.add(e);
   } 
}

interface wVertexInterface 
{
   public String toString();
   
   public String getName();
   
   public double getMinDistance();
   
   public void setMinDistance(double m);
   
   // public wVertex getPrevious();         //Graphs 7
    
   // public void setPrevious(wVertex v);   //Graphs 7
   
   public ArrayList<Edge> getAdjacencies();
   
   public int compareTo(wVertex other);
}


public class TJGraphAdjListWeighted implements TJGraphAdjListWeightedInterface
{
   private ArrayList<wVertex> vertices = new ArrayList<wVertex>();
   private Map<String, Integer> nameToIndex = new HashMap<String, Integer>();
  
   /*  enter your code here   */ 
   
   public List<wVertex> getVertices()
   {
      return vertices;
   }
   
   public wVertex getVertex(int i)
   {
      return vertices.get(i);
   }
   
   public wVertex getVertex(String vertexName)
   {
      return getVertex(nameToIndex.get(vertexName));
   }
   
   public void addVertex(String v)
   {
      vertices.add(new wVertex(v));
      nameToIndex.put(v, vertices.size()-1);
   }
   
   public void addEdge(String source, String target, double weight)
   {
      vertices.get(nameToIndex.get(source)).addEdge(new Edge(new wVertex(target), weight));
   }
     
   public void minimumWeightPath(String vertexName)
   {
      PriorityQueue<wVertex> pq = new PriorityQueue<wVertex>();
      wVertex source = getVertex(vertexName);
      source.setMinDistance(0);
      pq.add(source);
      
      while(!pq.isEmpty())
      {
         wVertex vertex = pq.remove();
         ArrayList<Edge> neighbors = vertex.getAdjacencies();
         for(Edge e: neighbors)
         {
            if(vertex.getMinDistance()+e.weight<getVertex(e.target.getName()).getMinDistance())
            {
               wVertex v =getVertex(e.target.getName());
               v.setMinDistance(vertex.getMinDistance()+e.weight);
               pq.add(v);
            }
         }
      }
   }
   
   
}    
interface TJGraphAdjListWeightedInterface 
{
   public List<wVertex> getVertices();
   
   public wVertex getVertex(int i);
   
   public wVertex getVertex(String vertexName);
   
   public void addVertex(String v);
   
   public void addEdge(String source, String target, double weight);
     
   public void minimumWeightPath(String vertexName);   //Dijkstra's
   
   /*  Graphs 7 */
       
//  public List<wVertex> getShortestPathTo(wVertex v);
    
//  public TJGraphAdjListWeighted graphFromEdgeListData(File vertexNames, File edgeListData) throws FileNotFoundException 
 
}