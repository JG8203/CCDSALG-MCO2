package com.dlsu;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import java.util.ArrayList;
import java.util.List;

public class Network {
    private Graph<Integer, DefaultEdge> graph;

    public Network() {
        this.graph = new SimpleGraph<>(DefaultEdge.class);
    }

    public void addRelationship(Relationship relationship) {
        int id = relationship.getId();
        int friend = relationship.getFriend();

        this.graph.addVertex(id);
        this.graph.addVertex(friend);

        this.graph.addEdge(id, friend);
    }

    public List<Integer> getFriendsOf(int id) {
        return Graphs.neighborListOf(graph, id);
    }

    public List<DefaultEdge> findPath(int id, int friend) {
        DijkstraShortestPath<Integer, DefaultEdge> shortestPath = new DijkstraShortestPath<>(graph);
        List<DefaultEdge> path = shortestPath.getPath(id, friend).getEdgeList();

        // Check if the path exists and return an empty list if it doesn't
        if (path == null) {
            return new ArrayList<>();
        }

        return path;
    }

    public Graph<Integer, DefaultEdge> getGraph() {
        return graph;
    }
}
