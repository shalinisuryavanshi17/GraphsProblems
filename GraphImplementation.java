import java.util.*;

class GraphImplementation {
    // adjacency list
    
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter number of vertices");
		
		int v = sc.nextInt();
		GraphImplementation graph = new GraphImplementation();
		List<List<Integer>> adj=new ArrayList<>();
		for (int i = 0; i < v; i++) {
			adj.add(new ArrayList<>());
		}
		System.out.println("Enter number of edges ");
		int e=sc.nextInt();
		System.out.println("Enter source and destination");
            for(int i = 0; i<e; i++) {
                int source = sc.nextInt();
                int destination = sc.nextInt();
                
                graph.addEdge(source, destination,adj);
            }
		System.out.println("the graph is ");
		graph.printGraph(adj);
		
		
		sc.close();
	}

	private void printGraph(List<List<Integer>> adj) {
		for (int i = 0; i < adj.size(); i++) {
			System.out.println("Adjacency list of vertex "+i);
			System.out.print("head");
			for (int j = 0; j < adj.get(i).size(); j++) {
				System.out.print(" -> "+adj.get(i).get(j));
			}
			System.out.println();
		}
	}

	private void addEdge(int source, int destination, List<List<Integer>> adj) {
		adj.get(source).add(destination);
		adj.get(destination).add(source);
	}
}
