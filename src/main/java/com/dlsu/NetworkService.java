package com.dlsu;

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

    public List<DefaultEdge> findPath(int id1, int id2) {
        return network.findPath(id1, id2);
    }
}
