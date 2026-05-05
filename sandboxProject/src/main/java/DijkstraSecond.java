import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class DijkstraSecond {
    // Класс для представления ребра графа
    static class Edge {
        int target;
        int weight;

        Edge(int target, int weight) {
            this.target = target;
            this.weight = weight;
        }
    }

    // Класс для хранения узла в приоритетной очереди
    static class Node implements Comparable<Node> {
        int id;
        int distance;

        Node(int id, int distance) {
            this.id = id;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.distance, other.distance);
        }
    }

    public static int[] dijkstra(List<List<Edge>> graph, int startNode) {
        int n = graph.size();
        int[] distances = new int[n];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[startNode] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(startNode, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int u = current.id;

            // Если найденный путь длиннее уже записанного, пропускаем
            if (current.distance > distances[u]) continue;

            for (Edge edge : graph.get(u)) {
                int v = edge.target;
                int weight = edge.weight;

                // Релаксация ребра
                if (distances[u] != Integer.MAX_VALUE && distances[u] + weight < distances[v]) {
                    distances[v] = distances[u] + weight;
                    pq.add(new Node(v, distances[v]));
                }
            }
        }
        return distances;
    }

    public static void main(String[] args) {
        int numNodes = 5;
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < numNodes; i++) graph.add(new ArrayList<>());

        // Пример графа: (откуда, куда, вес)
        graph.get(0).add(new Edge(1, 10));
        graph.get(0).add(new Edge(4, 3));
        graph.get(1).add(new Edge(2, 2));
        graph.get(4).add(new Edge(1, 1));
        graph.get(4).add(new Edge(2, 8));
        graph.get(4).add(new Edge(3, 2));
        graph.get(2).add(new Edge(3, 9));
        graph.get(3).add(new Edge(2, 7));

        int startNode = 0;
        int[] results = dijkstra(graph, startNode);

        System.out.println("Кратчайшие расстояния от узла " + startNode + ":");
        for (int i = 0; i < results.length; i++) {
            System.out.println("До узла " + i + ": " + (results[i] == Integer.MAX_VALUE ? "недостижимо" : results[i]));
        }
    }
}

