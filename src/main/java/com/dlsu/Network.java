package com.dlsu;

import java.util.*;

public class Network {
    private final Graph graph;

    public Network() {
        this.graph = new Graph();
    }

    public void addRelationship(Relationship relationship) {
        int id = relationship.getId();
        int friend = relationship.getFriend();

        this.graph.addVertex(id);
        this.graph.addVertex(friend);

        this.graph.addEdge(id, friend);
    }

    public Set<Integer> getFriendsOf(int id) {
        return graph.getNeighbors(id);
    }

    public Graph getGraph() {
        return graph;
    }
}
