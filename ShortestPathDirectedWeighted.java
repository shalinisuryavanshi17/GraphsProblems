import java.util.*;

class Pair
{
    private int v;
    private int weight;
    Pair(int _v, int _w) { v = _v; weight = _w; }
    int getV() { return v; }
    int getWeight() { return weight; }
}
public class ShortestPathDirectedWeighted {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter number of vertices");

        int v = sc.nextInt();
        ShortestPathDirectedWeighted graph = new ShortestPathDirectedWeighted();
        List<List<Pair>> adj = new ArrayList<>();
        for (int i = 0; i < v; i++) {
            adj.add(new ArrayList<Pair>());
        }
        System.out.println("Enter number of edges ");
        int e = sc.nextInt();
        System.out.println("Enter source and destination and weight");
        for (int i = 0; i < e; i++) {
            int source = sc.nextInt();
            int destination = sc.nextInt();
            int weight = sc.nextInt();
            graph.addEdge(source, destination, weight, adj);
        }
        graph.shortestPath(0, adj, v);
        sc.close();
    }

    private void addEdge(int source, int destination, int weight, List<List<Pair>> adj) {
        adj.get(source).add(new Pair(destination, weight));

    }

    void topologicalSortUtil(int node, Boolean visited[], Stack<Integer> stack, List<List<Pair>> adj) {

        visited[node] = true;
        for (Pair it : adj.get(node)) {
            if (visited[it.getV()] == false) {
                topologicalSortUtil(it.getV(), visited, stack, adj);
            }
        }
        stack.push(node);
    }

    void shortestPath(int s, List<List<Pair>> adj, int N) {
       Stack<Integer> stack=new Stack<>();
        int dist[] = new int[N];

        Boolean visited[] = new Boolean[N];
        for (int i = 0; i < N; i++)
        visited[i] = false;

        for (int i = 0; i < N; i++)
            if (visited[i] == false)
                topologicalSortUtil(i, visited, stack, adj);

        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[s] = 0;

        while (stack.empty() == false) {
            Integer node = stack.pop();
            
            if (dist[node] != Integer.MAX_VALUE) {
                for (Pair it : adj.get(node)) {
                    if (dist[node] + it.getWeight() < dist[it.getV()]) {
                        dist[it.getV()] = dist[node] + it.getWeight();
                    }
                }
            }
        }

        for (int i = 0; i < N; i++) {
            if (dist[i] == Integer.MAX_VALUE)
                System.out.print("INF ");
            else
                System.out.print(dist[i] + " ");
        }
    }
}
