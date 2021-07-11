import java.util.*;
public class ToposortDirectedDFS {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

		System.out.println("Enter number of vertices");

		int v = sc.nextInt();
		ToposortDirectedDFS graph = new ToposortDirectedDFS();
		List<List<Integer>> adj = new ArrayList<>();
		for (int i = 0; i < v; i++) {
			adj.add(new ArrayList<>());
		}
		System.out.println("Enter number of edges ");
		int e = sc.nextInt();
		System.out.println("Enter source and destination");
		for (int i = 0; i < e; i++) {
			int source = sc.nextInt();
			int destination = sc.nextInt();

			graph.addEdge(source, destination, adj);
		}
		graph.dfsToposort(v,adj);
       
        sc.close();
		
    }
    private void dfsToposort(int v, List<List<Integer>> adj) {
		boolean visited[]=new boolean[v];
		Stack<Integer> s=new Stack<>();
		for (int i = 0; i < v; i++) {
			if(!visited[i])
			{
				findToposort(i,adj,s,visited);
			}
		}
		System.out.println("the toposort is ");
		while(!s.empty())
		{
			System.out.print(s.pop()+" ");
		}
	}
private void findToposort(int i, List<List<Integer>> adj, Stack<Integer> s, boolean[] visited) {
     visited[i]=true;
	 for (Integer neighbor : adj.get(i)) {
		 if(!visited[neighbor])
		 {
			 visited[neighbor]=true;
			 findToposort(neighbor, adj, s, visited);
		 }
	 }
	 s.push(i);

	}
	private void addEdge(int source, int destination, List<List<Integer>> adj) {
        adj.get(source).add(destination);
        
    }
  
}
