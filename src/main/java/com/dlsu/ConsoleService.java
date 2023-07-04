package com.dlsu;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.graph.DefaultEdge;

import java.util.List;
import java.util.Scanner;

public class ConsoleService {
    private final Scanner scanner;

    public ConsoleService() {
        this.scanner = new Scanner(System.in);
    }

    public String promptFilePath() {
        System.out.print("Input file path: ");
        return scanner.nextLine();
    }

    public int promptMenuOption() {
        System.out.println("[1] Get friend list");
        System.out.println("[2] Get connection");
        System.out.println("[3] Exit");
        System.out.print("Enter your choice: ");
        return scanner.nextInt();
    }

    public int promptPersonID() {
        System.out.println("Enter ID of Person: ");
        return scanner.nextInt();
    }

    public void displayFriendsList(int id, List<Integer> friends) {
        System.out.println("Person " + id + " has " + friends.size() + " friends!");
        System.out.println("List of friends: ");
        for(Integer friend : friends){
            System.out.print(friend + " ");
        }
        System.out.println("");
    }

    public void displayConnectionPath(int id1, int id2, GraphPath<Integer, DefaultEdge> path) {
        if(path != null) {
            Graph<Integer, DefaultEdge> graph = path.getGraph();
            System.out.println("There is a connection from " + id1 + " to " + id2 + "!");
            List<DefaultEdge> edgeList = path.getEdgeList();
            for(DefaultEdge edge : edgeList) {
                System.out.println(graph.getEdgeSource(edge) + " is friends with " + graph.getEdgeTarget(edge));
            }
        } else {
            System.out.println("Cannot find a connection between " + id1 + " and " + id2);
        }
    }
}
