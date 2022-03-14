package alda.graph;

import alda.graph.UndirectedGraph;

import java.lang.reflect.Array;
import java.util.*;

public class MyUndirectedGraph<T> implements UndirectedGraph<T>{
	//private ArrayList<Node<T>> nodes = new ArrayList<Node<T>>();
	//private ArrayList<Edge<T>> edges = new ArrayList<Edge<T>>();
	private final HashMap<Node<T>, HashMap<Node<T>, Integer>> nodes = new HashMap<>();
	private int edges = 0;
	
	class Node<T>{
		private final T data;
		
		Node(T data){
			this.data = data;
		}
		
		@Override
		public boolean equals(Object obj){
			if(this == obj){return true;}
			if(obj == null || getClass() != obj.getClass()){return false;}
			Node<T> node = (Node<T>)obj;
			return data.equals(node.data);
		}
		
		@Override
		public int hashCode(){
			return Objects.hash(data);
		}
	}
	
//	class Edge<T>{
//		Node<T> source = null;
//		Node<T> destination = null;
//		int weight;
//
//		Edge(Node<T> source, Node<T> destination, int weight){
//			this.source = source;
//			this.destination = destination;
//			this.weight = weight;
//		}
//
//		public int getWeight(){
//			return weight;
//		}
//
//		@Override
//		public boolean equals(Object obj){
//			if(this == obj){return true;}
//			if(obj == null || getClass() != obj.getClass()){return false;}
//			Edge<T> edge = (Edge<T>)obj;
//			return weight == edge.weight && source.equals(edge.source) && destination.equals(edge.destination);
//		}
//
//		@Override
//		public int hashCode(){
//			return Objects.hash(source, destination, weight);
//		}
//	}
	
	@Override
	public int getNumberOfNodes(){
		return nodes.size();
	}
	
	@Override
	public int getNumberOfEdges(){
		return edges;
	}
	
	@Override
	public boolean add(T newNode){
//		if(nodes.contains(new Node<T>(newNode))){return false;}
//		else{
//			nodes.add(new Node(newNode));
//			return true;
//		}
		if(nodes.containsKey(new Node(newNode))){return false;}
		else{
			nodes.put(new Node(newNode), new HashMap<>());
			return true;
		}
	}
	
	@Override
	public boolean connect(T node1, T node2, int cost){
//		if(cost <= 0){return false;}
//		if(nodes.contains(new Node<T>(node1)) && nodes.contains(new Node<T>(node2))){
//			edges.add(new Edge<T>(new Node<T>(node1), new Node<T>(node2), cost));
//			return true;
//		}
//		return false;
		if(cost <= 0){return false;}
		if(nodes.containsKey(new Node(node1)) &&
				nodes.containsKey(new Node(node2))){
			nodes.get(new Node(node1)).put(new Node(node2), cost);
			nodes.get(new Node(node2)).put(new Node(node1), cost);
			edges++;
			return true;
		}
		return false;
	}
	
	@Override
	public boolean isConnected(T node1, T node2){
//		for(Edge<T> edge : edges){
//			if(edge.source.equals(new Node<T>(node1)) && edge.destination.equals(new Node<T>(node2))){
//				return true;
//			}
//		}
//		return false;
		if(nodes.containsKey(new Node(node1)) && nodes.containsKey(new Node(node2))){
			if(nodes.get(new Node(node1)).containsKey(new Node(node2))){return true;}
			else if(nodes.get(new Node(node2)).containsKey(new Node(node1))){return true;}
		}
		return false;
	}
	
	@Override
	public int getCost(T node1, T node2){
//		for(Edge<T> edge : edges){
//			if(edge.source.equals(new Node<T>(node1)) && edge.destination.equals(new Node<T>(node2))){
//				return edge.weight;
//			}
//		}
//		return -1;
		if(nodes.containsKey(new Node(node1)) && nodes.get(new Node(node1)).containsKey(new Node(node2))){
			return nodes.get(new Node(node1)).get(new Node(node2));
		}else {return -1;}
	}
	
	@Override
	public List depthFirstSearch(T start, T end){
		return null;
	}
	
	@Override
	public List breadthFirstSearch(T start, T end){
		if(start == null){return null;}
		LinkedList<Node<T>> path = new LinkedList<>();
		HashMap<Node<T>, Boolean> visited = new HashMap<>();
		for(Map.Entry<Node<T>, HashMap<Node<T>, Integer>> entry : nodes.entrySet()){
			visited.put(entry.getKey(), false);
		}
		visited.replace(new Node<T>(start), true); // how can we be sure it isn't a duplicate?
		path.add(new Node<T>(start));
		
		while(visited.size() > 0){
			visited.get()
			visited.
			for(Map.Entry<Node<T>, Integer> entry1 : nodes.get(new Node<T>(start)).entrySet()){
			
			}
		}
		
		
//		void unweighted( Vertex s )
//		{
//			Queue<Vertex> q = new Queue<Vertex>( );
//			for each Vertex v
//				v.dist = INFINITY;
//			s.dist = 0;
//			q.enqueue( s );
//			while( !q.isEmpty( ) )
//			{
//				Vertex v = q.dequeue( );
//				for each Vertex w adjacent to v
//				if( w.dist == INFINITY )
//				{
//					w.dist = v.dist + 1;
//					w.path = v;
//					q.enqueue( w );
//				}
//			}
//		}
	}
	
	@Override
	public UndirectedGraph minimumSpanningTree(){
		//Prim's algoritm
		UndirectedGraph output = new MyUndirectedGraph<T>();
		Node<T> selected = (Node<T>) nodes.keySet().toArray()[0];
		output.add(selected);
		
		//int minWeight = 0;
		for(Map.Entry<Node<T>, HashMap<Node<T>, Integer>> entry : nodes.entrySet()){
			output.add(entry.getKey());
			for(Map.Entry<Node<T>, Integer> entry1 : nodes.get(entry).entrySet()){
				if(!output.isConnected(entry.getKey().data, entry1.getKey().data)){
					output.connect(entry.getKey().data, entry1.getKey().data, entry1.getValue());
				}
				//nodes.get(new Node(selected)).entrySet().toArray()[0]
			}
			
		}
		return null;
	}
}
