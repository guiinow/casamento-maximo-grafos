import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class InstanceGenerator {

    public static List<int[]> generateGraph(int vertices, int edges) {
        Random random = new Random();
        List<int[]> edgeList = new ArrayList<>();

        for (int i = 0; i < edges; i++) {
            int u = random.nextInt(vertices);
            int v = random.nextInt(vertices);

            while (u == v || edgeExists(edgeList, u, v)) {
                u = random.nextInt(vertices);
                v = random.nextInt(vertices);
            }

            edgeList.add(new int[]{u, v});
        }

        return edgeList;
    }

    private static boolean edgeExists(List<int[]> edgeList, int u, int v) {
        for (int[] edge : edgeList) {
            if ((edge[0] == u && edge[1] == v) || (edge[0] == v && edge[1] == u)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {

        Scanner scanner  = new Scanner(System.in);
        System.out.println("Informe a quantidade de vértices e arestas de um grafo qualquer, sem peso e não-orientado:");
        int vertices = scanner.nextInt();
        int edges = scanner.nextInt();
        scanner.close();

        List<int[]> graph = generateGraph(vertices, edges);

        System.out.println("Grafo " + vertices + "x" + edges);
        for (int[] edge : graph) {
            System.out.println(edge[0] + " " + edge[1]);
        }
    }
}
