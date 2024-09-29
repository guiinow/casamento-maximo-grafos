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
        String graphDirectory = "testGraphs";
        int numberOfInstances = 200;
        int[] graphSizes = new int[numberOfInstances];
        int[] maxMatchings = new int[numberOfInstances];
        long[] executionTimes = new long[numberOfInstances];
        int totalExecutionTime = 0;

        BlossomAlgorithm blossomAlgorithm = new BlossomAlgorithm();
        List<String> sizesList = blossomAlgorithm.getGraphSizes();

        for (int instanceIndex = 0; instanceIndex < numberOfInstances; instanceIndex++) {
            String graphFilePath = graphDirectory + "/graph" + (instanceIndex + 1) + ".txt";
            int[][] graph = readGraphFromFile(graphFilePath);

            long startTime = System.currentTimeMillis();

            BlossomAlgorithm ba = new BlossomAlgorithm();
            int maxMatching = ba.findMaxMatching(graph, instanceIndex);

            long endTime = System.currentTimeMillis();
            long instanceExecutionTime = endTime - startTime;
            totalExecutionTime += instanceExecutionTime;

            graphSizes[instanceIndex] = Integer.parseInt(sizesList.get(instanceIndex));
            maxMatchings[instanceIndex] = maxMatching / 2;
            executionTimes[instanceIndex] = instanceExecutionTime;

            System.out.println("[########## " + (instanceIndex + 1) + "/" + numberOfInstances + " ##########]");
            System.out.println("Graph size: " + graphSizes[instanceIndex]);
            System.out.println("Max matching: " + maxMatchings[instanceIndex]);
            System.out.println("Execution time in ms: " + executionTimes[instanceIndex] + "ms");
            System.out.println("Execution time in s: " + executionTimes[instanceIndex] / 1000 + "s");
        }

        System.out.println("Total execution time in ms: " + totalExecutionTime + "ms");
        System.out.println("Total execution time in s: " + totalExecutionTime / 1000 + "s");
        System.out.println("Graph sizes: " + Arrays.toString(graphSizes));
        System.out.println("Max matchings:  " + Arrays.toString(maxMatchings));
        System.out.println("Execution times:   " + Arrays.toString(executionTimes));

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("results.txt", true))) {
            writer.write("Total execution time: " + totalExecutionTime);
            writer.newLine();
            writer.write("Graph sizes: " + Arrays.toString(graphSizes));
            writer.newLine();
            writer.write("Max matchings: " + Arrays.toString(maxMatchings));
            writer.newLine();
            writer.write("Execution times: " + Arrays.toString(executionTimes));
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public static int[][] readGraphFromFile(String filename) {
        List<int[]> edges = new ArrayList<>();
        int maxVertexId = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] edgeEndpoints = line.split(" ");
                int vertex1 = Integer.parseInt(edgeEndpoints[0]);
                int vertex2 = Integer.parseInt(edgeEndpoints[1]);
                edges.add(new int[]{vertex1, vertex2});
                maxVertexId = Math.max(maxVertexId, Math.max(vertex1, vertex2));
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        int[][] graph = new int[maxVertexId + 1][maxVertexId + 1];
        for (int[] edge : edges) {
            graph[edge[0]][edge[1]] = 1;
            graph[edge[1]][edge[0]] = 1;
        }

        return graph;
    }

    public int findMaxMatching(int[][] graph, int instanceIndex) {
        int numberOfVertices = graph.length;
        int[] matching = new int[numberOfVertices];
        Arrays.fill(matching, -1);
        int matchingCount = 0;
        String subGraphDirectory = "maxGraphs";
        new java.io.File(subGraphDirectory).mkdirs();

        for (int vertex = 0; vertex < numberOfVertices; vertex++) {
            boolean[] visitedVertices = new boolean[numberOfVertices];
            if (bfsSearch(graph, vertex, visitedVertices, matching)) {
                matchingCount++;
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(subGraphDirectory + "/subGraph" + instanceIndex + ".txt", true))) {
            for (int vertex = 1; vertex < matching.length; vertex++) {
                if (matching[vertex] > 0) {
                    writer.write(vertex + " - " + matching[vertex]);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }

        return matchingCount;
    }

    private boolean bfsSearch(int[][] graph, int startVertex, boolean[] visitedVertices, int[] matching) {
        for (int neighborVertex = 0; neighborVertex < graph.length; neighborVertex++) {
            if (graph[startVertex][neighborVertex] == 1 && !visitedVertices[neighborVertex]) {
                visitedVertices[neighborVertex] = true;
                if (matching[neighborVertex] < 0 || bfsSearch(graph, matching[neighborVertex], visitedVertices, matching)) {
                    matching[neighborVertex] = startVertex;
                    return true;
                }
            }
        }
        return false;
    }

    public List<String> getGraphSizes() {
        List<String> sizesList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("sizes.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                sizesList.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return sizesList;
    }
}
