import java.util.*;

class GraphImplementation {
    // adjacency list
    private LinkedList<Integer> adj[];

    public GraphImplementation() {
    }

    GraphImplementation(int v) {

        // initialize adjacency list
        adj = new LinkedList[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new LinkedList<Integer>();
        }
    }

    public void addEdge(int sr, int dr) {
        // adding edges
        adj[sr].add(dr);
        adj[dr].add(sr);
    }
    //get min distance from sr to dr
    public int bfs(int sr,int dr)
    {
       boolean visited[]=new boolean[adj.length];
       int parent[]=new int[adj.length];
       Queue<Integer> queue=new LinkedList<>();
       queue.offer(sr);
       parent[sr]=-1;
       visited[sr]=true;
       while(!queue.isEmpty())
       {
           int curr=queue.poll();
           if(curr==dr){
               break;
           }
           //traverse through the neighbors of current element
           for (int neighbor : adj[curr]) {
               if(!visited[neighbor])
               {
                   visited[neighbor]=true;
                   queue.offer(neighbor);
                   parent[neighbor]=curr;
               }
           }
       }
       int cur=dr;
       int distance=0;
       while(parent[cur]!=-1)
       {
           System.out.print(cur+" -> ");
          cur= parent[cur];
           distance++;
       }
       return distance;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter number of vertices and edges");
        int v = sc.nextInt();
        int e = sc.nextInt();
        GraphImplementation graph = new GraphImplementation(v);
        System.out.println("enter "+e+"no of edges");
        for (int i = 0; i < e; i++) {
            int sr=sc.nextInt();
            int dr=sc.nextInt();
            graph.addEdge(sr, dr);
        }
        System.out.println("enter source and destination");
        System.out.println("min distance is "+graph.bfs(
            sc.nextInt(), sc.nextInt())+" ");

    }
}
