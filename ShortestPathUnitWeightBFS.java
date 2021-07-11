import java.util.*;
public class ShortestPathUnitWeightBFS {
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);

		System.out.println("Enter number of vertices");

		int v = sc.nextInt();
		
		List<List<Integer>> adj = new ArrayList<>();
		for (int i = 0; i < v; i++) {
			adj.add(new ArrayList<>());
		}
		System.out.println("Enter number of edges ");
		int e = sc.nextInt();
		System.out.println("Enter source and destination");
        ShortestPathUnitWeightBFS gph=new ShortestPathUnitWeightBFS();
		for (int i = 0; i < e; i++) {
			int source = sc.nextInt();
			int destination = sc.nextInt();
			gph.addEdge(source, destination, adj);
		}
        // System.out.println("enter the source node");
        // int src=sc.nextInt();
        gph.getShortestPath(0,v,adj);
        sc.close();
    }
    private void getShortestPath(int src, int v, List<List<Integer>> adj) {
        int distance[]=new int[v];
        Arrays.fill(distance,Integer.MAX_VALUE);
        Queue<Integer> q=new LinkedList<>();
        q.add(src);
        distance[src]=0;
        while(!q.isEmpty()) 
    	{ 
    		int node = q.poll();  
    		 
    		for(int it:adj.get(node)){
    		    if(distance[node] + 1 < distance[it]){
    		        distance[it] = distance[node] + 1;
    		        q.add(it);
    		    }
    		} 
    	}
        for (int i = 0; i < distance.length; i++) {
            System.out.print(distance[i]+" ");
        }
    }
    void addEdge(int source, int destination, List<List<Integer>> adj) {
		adj.get(source).add(destination);
		adj.get(destination).add(source);
	}
}
