// Autores: Guilherme Ferreira 19.2.8981 | Vinicius Niquini 21.1.8008

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
        int instances = 200;
        int[] x = new int[instances];
        int[] y = new int[instances];
        long[] z = new long[instances];
        int totalTime = 0;

        BlossomAlgorithm aux = new BlossomAlgorithm();
        List<String> size = aux.getSize();

        for (int i = 0; i < instances; i++) {

            String graphFilePath = directory + "/graph" + (i + 1) + ".txt";
            int[][] graph = readGraphFromFile(graphFilePath);
            
            long initialTime = System.currentTimeMillis();
            
            BlossomAlgorithm ba = new BlossomAlgorithm();
            int maxMatching = ba.maxMatching(graph);
            
            long finalTime = System.currentTimeMillis();
            long totalTimeInMS = finalTime - initialTime;
            totalTime += totalTimeInMS;

            x[i] = Integer.parseInt(size.get(i));
            y[i] = maxMatching/2;
            z[i] = totalTimeInMS;

            //Essa função escreve em um arquivo uma lista de nós correspondentes, cada nó (índice) tem em sua casa o nó conectado
            ba.subGraph(graph, i);
            
            System.out.println("[########## " + (i + 1) + "/" + instances + " ##########]");
            System.out.println("Size: " + x[i]);
            System.out.println("Max: " + y[i]);
            System.out.println("Time: " + z[i]);
        }
        System.out.println("Tempo total: " + totalTime);
        System.out.println("Tamanhos: " + Arrays.toString(x));
        System.out.println("Maximos:  " + Arrays.toString(y));
        System.out.println("Tempos:   " + Arrays.toString(z));

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("results.txt", true))) {
                writer.write("Tempo total: " + totalTime);
                writer.newLine();
                writer.write("Tamanhos: " + Arrays.toString(x));
                writer.newLine();
                writer.write("Maximos: " + Arrays.toString(y));
                writer.newLine();
                writer.write("Tempos: " + Arrays.toString(z));
                writer.newLine();
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
            e.printStackTrace();
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

    public void subGraph(int[][] graph , int i) {
        int n = graph.length;
        int[] match = new int[n];
        Arrays.fill(match, -1);
        String directory = "maxGraphs";
        new java.io.File(directory).mkdirs();

        for (int u = 0; u < n; u++) {
            boolean[] visited = new boolean[n];
            bfs(graph, u, visited, match);
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(directory + "/subGraph"+i+".txt" , true))) {
            for (int j=1; j < match.length; j++){
                if (match[j] > 0) {
                    writer.write(j + " - " + match[j]);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
            e.printStackTrace();    
        }
        
    }

    public List<String> getSize() {
        List<String> size = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("sizes.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                size.add(line);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
            e.printStackTrace();
        }
        return size;
    }
}