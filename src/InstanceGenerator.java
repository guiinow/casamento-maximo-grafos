// Autores: Guilherme Ferreira 19.2.8981 | Vinicius Niquini 21.1.8008

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
    public static void main(String[] args) throws IOException {
        int instances = 200;
        Random random = new Random();
        
        String directory = "testGraphs";
        new java.io.File(directory).mkdirs();
        
        for (int i = 0; i < instances; i++) {

            int vertices = random.nextInt(0, 20000);
            int edges = random.nextInt(0, 20000);
            int maxEdges = vertices * (vertices - 1) / 2;
            if (edges > maxEdges) {
                edges = maxEdges;
            }

            System.out.println("Grafo " + i + ": " + vertices + "x" + edges);
            
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(directory + "/graph" + (i + 1) + ".txt"))) {
                List<int[]> graph = generateGraph(vertices, edges);
                
                for (int[] edge : graph) {
                    writer.write(edge[0] + " " + edge[1]);
                    writer.newLine();
                }
            } catch (IOException e) {
                e.getMessage();     
            }
            
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("results.txt", true))) {
                writer.write("Grafo " + i + ": " + vertices + "x" + edges + " | ");
            } catch (IOException e) {
                e.getMessage();     
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter("sizes.txt", true))) {
                String size = Integer.toString(vertices + edges);
                writer.write(size);
                writer.newLine();
            } catch (IOException e) {
                e.getMessage();     
            }
        }
    }
}
