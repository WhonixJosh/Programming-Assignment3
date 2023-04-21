package com.company;

/*Copyright (c) Dec 21, 2014 CareerMonk Publications and others.
 * E-Mail           	: info@careermonk.com
 * Creation Date    	: 2015-01-10 06:15:46
 * Last modification	: 2006-05-31
               by		: Narasimha Karumanchi
 * File Name			: CLLNode.java
 * Book Title			: Data Structures And Algorithms Made In Java
 * Warranty         	: This software is provided "as is" without any
 * 							warranty; without even the implied warranty of
 * 							merchantability or fitness for a particular purpose.
 *
 * Some modifications by Matthew Hayes
 */

import java.io.*;
import java.util.*;

import javax.swing.text.StyledEditorKit.BoldAction;



class LinkedList<Integer> implements Iterable<Integer> {
    private ListNode<Integer> head;     // beginning of linked list
    private int n;                      // number of elements in linked list

    // helper linked list class
    private static class ListNode<Integer> {
        private Integer data;
        private ListNode<Integer> next;
    }

    public LinkedList() {
        head = null;
        n = 0;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        return n;
    }

    public void add(Integer data) {
        ListNode<Integer> oldfirst = head;
        head = new ListNode<Integer>();
        head.data = data;
        head.next = oldfirst;
        n++;
    }

    public Iterator<Integer> iterator()  {
        return new ListIterator(head);
    }

    // an iterator, doesn't implement remove() since it's optional
    private class ListIterator implements Iterator<Integer> {
        private ListNode<Integer> current;

        public ListIterator(ListNode<Integer> head) {
            current = head;
        }

        public boolean hasNext()  { return current != null;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Integer next() {
            if (!hasNext()) throw new NoSuchElementException();
            Integer data = current.data;
            current = current.next;
            return data;
        }
    }
}
class Graph {
    private static final String NEWLINE = System.getProperty("line.separator");

    private final int V;
    private int E;
    private LinkedList<Integer>[] adjList;

    // Initializes an empty graph with V vertices and 0 edges.

    public Graph(int V) {
        if (V < 0) throw new IllegalArgumentException("Number of vertices must be nonnegative");
        this.V = V;
        this.E = 0;
        adjList = (LinkedList<Integer>[]) new LinkedList[V];
        for (int u = 0; u < V; u++) {
            adjList[u] = new LinkedList<Integer>();
        }
    }

    // random graph with V vertices and E edges
    public Graph(int V, int E) {
        this(V);
        if (E > (long) V*(V-1)/2 + V) throw new IllegalArgumentException("Too many edges");
        if (E < 0)                    throw new IllegalArgumentException("Too few edges");
        Random random = new Random();

        // can be inefficient
        while (this.E != E) {
            int u = random.nextInt(V);
            int v = random.nextInt(V);
            addEdge(u, v);
        }
    }



    // Initializes a new graph.
    public Graph(Graph G) {
        this(G.V());
        this.E = G.E();
        for (int u = 0; u < G.V(); u++) {
            // reverse so that adjacency list is in same order as original
            Stack<Integer> reverse = new Stack<Integer>();
            for (int v : G.adjList[u]) {
                reverse.push(v);
            }
            for (int v : reverse) {
                adjList[u].add(v);
            }
        }
    }

    // Returns the number of vertices in this graph.
    public int V() {
        return V;
    }

    // Returns the number of edges in this graph.
    public int E() {
        return E;
    }

    // throw an IllegalArgumentException unless {@code 0 <= u < V}
    private void validateVertex(int u) {
        if (u < 0 || u >= V)
            throw new IllegalArgumentException("vertex " + u + " is not between 0 and " + (V-1));
    }

    public void addEdge(int u, int v) {

        //Assumes graph is undirected
        validateVertex(u);
        validateVertex(v);
        E++;
        adjList[u].add(v);
        adjList[v].add(u);
    }

    // Returns the vertices adjacent to vertex {@code u}.
    public Iterable<Integer> adjList(int u) {
        validateVertex(u);
        return adjList[u];
    }

    public int degree(int u) {
        validateVertex(u);
        return adjList[u].size();
    }

    // Returns a string representation of this graph.
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("Undirected graph" + NEWLINE);
        s.append(V + " vertices, " + E + " edges " + NEWLINE);
        for (int u = 0; u < V; u++) {
            s.append(u + ": ");
            for (int v : adjList[u]) {
                s.append(v + " ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }

    // test code


}

public class Main {

    public static void depthFirstSearch(HashMap<Integer, Boolean> visit, Graph g) {
        for (int i = 0; i < g.V(); i++) {
            if(visit.get(i) == false){
                if(i % 2 == 0){System.out.println(i);}
                visit.put(i, true);
                dfs(visit, g.adjList(i), g);
            }
        }
    }

    public static void dfs(HashMap<Integer, Boolean> visit, Iterable<Integer> adjacent, Graph g) {
        for (Integer vertex : adjacent) {
            if(visit.get(vertex) == false){
                if(vertex % 2 == 0){System.out.println(vertex);}
                visit.put(vertex, true);
                dfs(visit, g.adjList(vertex), g);
            }
        }
    }

    public static int localMinimum(List<Integer> elems) {
        int middle = elems.size() / 2;

        if(middle ==0 || elems.get(middle -1) > elems.get(middle) && middle == elems.size()-1 ||
           elems.get(middle) < elems.get(middle + 1)){return middle; }
        
        else if(middle > 0 && elems.get(middle -1 ) < elems.get(middle)){return localMinimum(elems.subList(0, middle + 1)); }
        
        return localMinimum(elems.subList(middle, elems.size()- 1));
    }

    public static void main(String[] args) {
        HashMap<Integer, Boolean> visited = new HashMap<Integer, Boolean>();    //Keeps track of whether or not a vertex has been visited
        
        List<Integer> elems = new ArrayList<Integer>();

        elems.add(4);
        elems.add(-1);
        elems.add(0);
        elems.add(-2);
        elems.add(3);
        elems.add(5);
        elems.add(8);
        elems.add(6);
        elems.add(21);
        elems.add(-60);
        elems.add(42);
        elems.add(5);
        elems.add(422);
        elems.add(3);
        elems.add(-70);
        elems.add(5);
        

        Graph G = new Graph(18);

        for(int i = 0; i < G.V(); i++){
            visited.put(i,false);
        }
        

        G.addEdge(0, 1);
        G.addEdge(0, 4);
        G.addEdge(0, 7);
        G.addEdge(0, 14);

        G.addEdge(1, 2);
        G.addEdge(1, 5);

        G.addEdge(2, 6);

        G.addEdge(3, 6);

        G.addEdge(5, 6);
        G.addEdge(5, 8);
        G.addEdge(5, 9);

        G.addEdge(6, 9);
        G.addEdge(6, 10);

        G.addEdge(7, 11);
        G.addEdge(7, 14);

        G.addEdge(8, 12);

        G.addEdge(9, 10);

        G.addEdge(10, 17);

        G.addEdge(11, 14);
        G.addEdge(11, 15);
        G.addEdge(11, 12);

        G.addEdge(12, 13);
        G.addEdge(12, 15);
        G.addEdge(12, 16);

        G.addEdge(13, 16);
        G.addEdge(13, 17);

        G.addEdge(14, 15);



       System.out.println(G.toString());  //Do not uncomment until graph is complete

       System.out.println("DFS Path:");
       depthFirstSearch(visited, G);

       int localmin = localMinimum(elems);

       System.out.printf("\nLocal Minimum Index: %d", localmin);
    }
}