import java.util.*;

class Node {
	int first;
	int second;

	Node(int f, int s) {
		this.first = f;
		this.second = s;
	}
}

class GraphImplementation {
	// adjacency list

	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter number of vertices");

		int v = sc.nextInt();
		GraphImplementation graph = new GraphImplementation();
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
		graph.bfsTraversal(v, adj);
		graph.dfsTraversal(v, adj);
		Queue<Integer> q=new LinkedList<>();
		q.add(0);
		boolean visited[]=new boolean[v];
		System.out.println("bfs using recursion ");
		graph.bfsRecursion(v,adj,q,visited);
		System.out.println("is there cycle in graph (BFS) ? " +
		graph.cycleUsingBFS(v, adj));
		System.out.println("is there cycle in graph (DFS) ? " + graph.cycleUsingDFS(v, adj));
		System.out.println("the graph is ");
		graph.printGraph(adj);

		sc.close();
	}

	private void bfsRecursion(int v, List<List<Integer>> adj, Queue<Integer> q,boolean visited[]) {
		if (q.isEmpty()) {
            return;
        }
        int node = q.poll();
        visited[node] = true;
        System.out.print(v + " ");
        for (int u : adj.get(node)) {
            if (!visited[u]) {
                visited[u] = true;
                q.add(u);
            }
        }
		bfsRecursion(v, adj, q,visited);
	}

	private boolean cycleUsingDFS(int v, List<List<Integer>> adj) {
		boolean visited[] = new boolean[v];
		for (int i = 0; i < v; i++) {
			if (!visited[i]) {
				if (checkCycleDFS(i, -1, adj, visited)) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean checkCycleDFS(int node, int parent, List<List<Integer>> adj, boolean[] visited) {

		visited[node] = true;
		for (Integer neighbor : adj.get(node)) {
			if (!visited[neighbor]) {
				checkCycleDFS(neighbor, node, adj, visited);
			} else if (neighbor != parent) {
				return true;
			}

		}

		return false;

	}

	private boolean cycleUsingBFS(int v, List<List<Integer>> adj) {
		boolean visited[] = new boolean[v];
		Queue<Node> q = new LinkedList<>();
		for (int i = 0; i < v; i++) {
			if (!visited[i]) {
				if (checkCycleBFS(i, adj, visited, q)) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean checkCycleBFS(int i, List<List<Integer>> adj, boolean[] visited, Queue<Node> q) {
		q.offer(new Node(i, -1));
		visited[i] = true;
		while (!q.isEmpty()) {
			Integer node = q.peek().first;
			Integer par = q.peek().second;
			q.remove();
			for (Integer neighbor : adj.get(node)) {
				if (!visited[neighbor]) {
					q.offer(new Node(neighbor, node));
					visited[neighbor] = true;
				} else if (par != neighbor) {
					return true;
				}
			}
		}
		return false;

	}

	private void dfsTraversal(int v, List<List<Integer>> adj) {
		boolean visited[] = new boolean[v];
		List<Integer> dfs = new ArrayList<>();
		for (int i = 0; i < v; i++) {
			if (!visited[i]) {
				dfs(i, adj, visited, dfs);
				dfsUsingStack(i,adj,visited,dfs);
			}
		}
		System.out.println("dfs traversal is " + dfs);
	}

	private void dfsUsingStack(int i, List<List<Integer>> adj, boolean[] visited, List<Integer> dfs) {
		Stack<Integer> s=new Stack<>();
		s.push(i);
		visited[i]=true;
		while(!s.isEmpty())
		{
			Integer neighbor=s.pop();
			dfs.add(neighbor);
			for (Integer neigh : adj.get(neighbor)) {
				if(!visited[neigh])
				{
					s.push(neigh);
					visited[neigh]=true;
				}
			}
		}
		
	}

	private void dfs(int i, List<List<Integer>> adj, boolean[] visited, List<Integer> dfs) {

		visited[i] = true;
		dfs.add(i);
		for (Integer neighbor : adj.get(i)) {
			if (!visited[neighbor]) {
				dfs(neighbor, adj, visited, dfs);
			}

		}
	}

	private void bfsTraversal(int v, List<List<Integer>> adj) {
		// step1 create visited array
		boolean visited[] = new boolean[v];
		for (int i = 0; i < v; i++) {
			if (!visited[i]) {
				bfs(i, adj, visited);
			}
		}
	}

	private void bfs(int sr, List<List<Integer>> adj, boolean[] visited) {
		// create queue
		Queue<Integer> q = new LinkedList<>();
		List<Integer> bfs = new ArrayList<>();
		q.offer(sr);
		visited[sr] = true;
		System.out.println("bfs traversal is ");
		while (!q.isEmpty()) {
			Integer parent = q.poll();
			bfs.add(parent);
			for (Integer neighbor : adj.get(parent)) {
				if (!visited[neighbor]) {
					q.offer(neighbor);
					visited[neighbor] = true;
				}
			}
		}
		System.out.println(bfs);
	}

	private void printGraph(List<List<Integer>> adj) {
		for (int i = 0; i < adj.size(); i++) {
			System.out.println("Adjacency list of vertex " + i);
			System.out.print("head");
			for (int j = 0; j < adj.get(i).size(); j++) {
				System.out.print(" -> " + adj.get(i).get(j));
			}
			System.out.println();
		}
	}

	private void addEdge(int source, int destination, List<List<Integer>> adj) {
		adj.get(source).add(destination);
		adj.get(destination).add(source);
	}
}
