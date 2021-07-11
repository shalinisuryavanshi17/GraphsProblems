import java.util.*;
public class BipartiteGraph {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

		System.out.println("Enter number of vertices");

		int v = sc.nextInt();
		BipartiteGraph graph = new BipartiteGraph();
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
        System.out.println(graph.checkBipartite(adj,v));
        System.out.println(graph.checkBipartiteUsingDFS(adj,v));
        sc.close();
		
    }
    private boolean checkBipartiteUsingDFS(List<List<Integer>> adj, int v) {
        int color[]=new int[v];
        Arrays.fill(color, -1);//fill with -1 
        for (int i = 0; i < v; i++) {
            if(!isBipartiteGraphDFS(i,adj,color))
            {
                return false;
            }
        }

        return true;
    }
    private boolean isBipartiteGraphDFS(int i, List<List<Integer>> adj, int[] color) {

        for (Integer neigh: adj.get(i)) {
            if(color[neigh]==-1)
            {
             color[neigh]=1-color[i];
             if(!isBipartiteGraph(neigh, adj, color))
             {
                 return false;
             }   
            }
            else if(color[neigh]==color[i])
            {
                return false;
            }

        }
        return true;
    }
    private boolean checkBipartite(List<List<Integer>> adj, int v) {
        int color[]=new int[v];
        Arrays.fill(color, -1);//fill with -1 
        for (int i = 0; i < v; i++) {
            if(isBipartiteGraph(i,adj,color))
            {
                return true;
            }
        }
        return false;
    }
    private boolean isBipartiteGraph(int i, List<List<Integer>> adj, int[] color) {
        color[i]=1;//assign color 1
        Queue<Integer> q=new LinkedList<>();
        q.add(i);
        while(!q.isEmpty())
        {
            Integer node=q.poll();
            for (Integer neigh : adj.get(node)) {
                if(color[neigh]==-1)
                {
                    color[neigh]=1-color[node];//assign opposite color
                    q.add(neigh);
                }
                else if(color[neigh]==color[node])
                {
                    return false;
                }
            }
        }
        
        return true;
    }
    private void addEdge(int source, int destination, List<List<Integer>> adj) {
		adj.get(source).add(destination);
		adj.get(destination).add(source);
	}
}
