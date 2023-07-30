package com.dlsu;

public class Edge {
    private final int source;
    private final int target;

    public Edge(int source, int target) {
        this.source = source;
        this.target = target;
    }

    public int getSource() {
        return source;
    }

    public int getTarget() {
        return target;
    }
}
