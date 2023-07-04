package com.dlsu;

import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;

import java.util.List;

public class NetworkService {
    private final Network network;
    private final FileReader fileReader;

    public NetworkService() {
        this.network = new Network();
        this.fileReader = new FileReader();
    }

    public void loadRelationships(String filePath) {
        Relationship[] relationships = fileReader.readFile(filePath);
        for (Relationship relationship : relationships) {
            network.addRelationship(relationship);
        }
    }

    public List<Integer> getFriendsOf(int id) {
        return network.getFriendsOf(id);
    }

    public GraphPath<Integer, DefaultEdge> findPath(int id1, int id2) {
        DijkstraShortestPath<Integer, DefaultEdge> dijkstraAlg =
                new DijkstraShortestPath<>(network.getGraph()); // assuming `network.getGraph()` returns your graph object
        return dijkstraAlg.getPath(id1, id2);
    }
}

