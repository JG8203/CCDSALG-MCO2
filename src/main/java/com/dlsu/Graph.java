package com.dlsu;

import java.util.*;

public class Graph {
    private final Map<Integer, Set<Integer>> adjacencyList;

    public Graph() {
        this.adjacencyList = new HashMap<>();
    }

    public void addVertex(int vertex) {
        adjacencyList.putIfAbsent(vertex, new HashSet<>());
    }

    public void addEdge(int source, int target) {
        addVertex(source);
        addVertex(target);
        adjacencyList.get(source).add(target);
        adjacencyList.get(target).add(source);  // As it's an undirected graph.
    }

    public Set<Integer> getNeighbors(int vertex) {
        return adjacencyList.get(vertex);
    }

    public Set<Edge> getEdges() {
        Set<Edge> edges = new HashSet<>();
        for (int vertex : adjacencyList.keySet()) {
            for (int neighbor : adjacencyList.get(vertex)) {
                edges.add(new Edge(vertex, neighbor));
            }
        }
        return edges;
    }
    public Map<Integer, Integer> breadthFirstSearch(int start) {
        Map<Integer, Integer> parentMap = new HashMap<>();
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();

        queue.add(start);
        visited.add(start);
        parentMap.put(start, null);  // The start node doesn't have a parent

        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (int neighbor : getNeighbors(current)) {
                if (!visited.contains(neighbor)) {
                    queue.add(neighbor);
                    visited.add(neighbor);
                    parentMap.put(neighbor, current);
                }
            }
        }
        return parentMap;
    }
    public List<Integer> reconstructPath(int start, int goal, Map<Integer, Integer> parentMap) {
        List<Integer> path = new ArrayList<>();
        Integer currentNode = goal;

        while (currentNode != null) {
            path.add(currentNode);
            currentNode = parentMap.get(currentNode);
        }

        // The path is from goal to start, so reverse it
        Collections.reverse(path);

        // If the start of the path isn't the start node, there's no path
        if (!path.get(0).equals(start)) {
            return new ArrayList<>();
        }

        return path;
    }
}
