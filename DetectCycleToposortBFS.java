import java.util.*;
public class DetectCycleToposortBFS {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

		System.out.println("Enter number of vertices");

		int v = sc.nextInt();
		DetectCycleToposortBFS graph = new DetectCycleToposortBFS();
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
		System.out.println(graph.detectCycle(v,adj));
        sc.close();
		
    }
    private boolean detectCycle(int v, List<List<Integer>> adj) {
        int topo[]=new int[v];
        int indegree[]=new int[v];
        for (int i = 0; i < v; i++) {
            for (Integer neighbor: adj.get(i)) {
              indegree[neighbor]++;
            }
        }
        Queue<Integer> q=new LinkedList<>();
        for (int i = 0; i < indegree.length; i++) {
            if(indegree[i]==0)
            {
                q.add(i);
            }
        }
        int ind=0;
        int counter=0;
        while(!q.isEmpty())
        {
            Integer node=q.poll();
            topo[ind++]=node;
            counter++;
            for (Integer neigh : adj.get(node)) {
                indegree[neigh]--;
                if(indegree[neigh]==0)
                {
                    q.add(neigh);
                }    
            }
        }
        if(counter ==v)
        {
            return false;
        }
        return true;
    }
    private void addEdge(int source, int destination, List<List<Integer>> adj) {
        adj.get(source).add(destination);
        
    } 
}

