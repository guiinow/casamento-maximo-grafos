import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BlossomAlgorithm {

    public static void main(String[] args) {
        String directory = "testGraphs";

        for (int i = 0; i < 5; i++) {

            String graphFilePath = directory + "/graph" + (i + 1) + ".txt";
            int[][] graph = readGraphFromFile(graphFilePath);

            long initialTime = System.currentTimeMillis();
            BlossomAlgorithm ba = new BlossomAlgorithm();
            int maxMatching = ba.maxMatching(graph);
            long finalTime = System.currentTimeMillis();

            long totalTimeInMS = finalTime - initialTime;
            System.out.println("Total time in milliseconds: " + totalTimeInMS + "ms");
            System.out.println("Total time in seconds: " + totalTimeInMS / 1000 + "s");

            try (BufferedWriter writer = new BufferedWriter(new FileWriter("results.txt", true))) {
                writer.write("Graph " + (i + 1) + ": Maximum matching = " + maxMatching);
                writer.newLine();
                writer.write("Total time in milliseconds: " + totalTimeInMS + "ms");
                writer.newLine();
                writer.write("Total time in seconds: " + totalTimeInMS / 1000 + "s");
                writer.newLine();
            } catch (IOException e) {
                e.getMessage();
            }

        }
    
    }

    public static int[][] readGraphFromFile(String filename) {
    List<int[]> edges = new ArrayList<>();
    int maxVertex = 0;

    try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(" ");
            int u = Integer.parseInt(parts[0]);
            int v = Integer.parseInt(parts[1]);
            edges.add(new int[]{u, v});
            maxVertex = Math.max(maxVertex, Math.max(u, v));
        }
    } catch (IOException e) {
        e.getMessage();
    }

    int[][] graph = new int[maxVertex + 1][maxVertex + 1];
    for (int[] edge : edges) {
        graph[edge[0]][edge[1]] = 1;
        graph[edge[1]][edge[0]] = 1;
    }

    return graph;
}

    public int maxMatching(int[][] graph) {
        int n = graph.length;
        int[] match = new int[n];
        Arrays.fill(match, -1);
        int result = 0;

        for (int u = 0; u < n; u++) {
            boolean[] visited = new boolean[n];
            if (bfs(graph, u, visited, match)) {
                result++;
            }
        }
        return result;
    }

    private boolean bfs(int[][] graph, int u, boolean[] visited, int[] match) {
        for (int v = 0; v < graph.length; v++) {
            if (graph[u][v] == 1 && !visited[v]) {
                visited[v] = true;
                if (match[v] < 0 || bfs(graph, match[v], visited, match)) {
                    match[v] = u;
                    return true;
                }
            }
        }
        return false;
    }
}

