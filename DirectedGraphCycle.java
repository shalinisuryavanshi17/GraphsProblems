import java.util.*;

public class DirectedGraphCycle {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter number of vertices");

        int v = sc.nextInt();
        DirectedGraphCycle graph = new DirectedGraphCycle();
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
        System.out.println(graph.isCyclic(v, adj));
        sc.close();
    }

    private boolean checkCycle(int node, List<List<Integer>> adj, int vis[], int dfsVis[]) {
        vis[node] = 1;
        dfsVis[node] = 1;

        for (Integer it : adj.get(node)) {
            if (vis[it] == 0) {
                if (checkCycle(it, adj, vis, dfsVis) == true) {
                    return true;
                }

            } else if (dfsVis[it] == 1) {
                return true;
            }
        }
        dfsVis[node] = 0;
        return false;
    }

    public boolean isCyclic(int N, List<List<Integer>> adj) {
        int vis[] = new int[N];
        int dfsVis[] = new int[N];

        for (int i = 0; i < N; i++) {
            if (vis[i] == 0) {
                if (checkCycle(i, adj, vis, dfsVis) == true)
                    return true;
            }
        }
        return false;
    }

    private void addEdge(int source, int destination, List<List<Integer>> adj) {
        adj.get(source).add(destination);
        
    }

}
