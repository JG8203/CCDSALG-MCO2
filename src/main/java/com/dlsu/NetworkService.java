package com.dlsu;

import java.util.List;
import java.util.Map;
import java.util.Set;

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

    public Set<Integer> getFriendsOf(int id) {
        return network.getFriendsOf(id);
    }

    public List<Integer> findPath(int start, int goal) {
        Map<Integer, Integer> parentMap = network.getGraph().breadthFirstSearch(start);
        return network.getGraph().reconstructPath(start, goal, parentMap);
    }
}
