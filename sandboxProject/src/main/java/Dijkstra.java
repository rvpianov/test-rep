import java.util.*;

public class Dijkstra {
    static class Edge {
        int target;
        int weight;

        Edge(int target, int weight) {
            this.target = target;
            this.weight = weight;
        }
    }

    public static void dijkstra(List<List<Edge>> graph, int source) {
        int n = graph.size();
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[source] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.add(new int[]{source, 0});

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int node = current[0];
            int currentDist = current[1];

            // Если нашли более короткий путь — пропускаем
            if (currentDist > dist[node]) continue;

            for (Edge edge : graph.get(node)) {
                int newDist = dist[node] + edge.weight;

                if (newDist < dist[edge.target]) {
                    dist[edge.target] = newDist;
                    pq.add(new int[]{edge.target, newDist});
                }
            }
        }

        // Вывод результата
        for (int i = 0; i < n; i++) {
            System.out.println("Расстояние от " + source + " до " + i + " = " + dist[i]);
        }
    }

    public static void main(String[] args) {
        int n = 5;
        List<List<Edge>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        // Добавляем рёбра (ориентированный граф)
        graph.get(0).add(new Edge(1, 10));
        graph.get(0).add(new Edge(4, 5));
        graph.get(1).add(new Edge(2, 1));
        graph.get(4).add(new Edge(1, 3));
        graph.get(4).add(new Edge(2, 9));
        graph.get(4).add(new Edge(3, 2));
        graph.get(2).add(new Edge(3, 4));
        graph.get(3).add(new Edge(2, 6));
        graph.get(3).add(new Edge(0, 7));

        dijkstra(graph, 0);
    }
}
