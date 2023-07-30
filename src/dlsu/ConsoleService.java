package com.dlsu;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

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
        System.out.print("Enter ID of Person: ");
        return scanner.nextInt();
    }

    public void displayFriendsList(int id, Set<Integer> friends) {
        System.out.println("Person " + id + " has " + friends.size() + " friends!");
        System.out.println("List of friends: ");
        for(Integer friend : friends){
            System.out.printf(friend + " ");
        }
        System.out.println("");
    }

    public void displayConnectionPath(int start, int goal, List<Integer> path) {
        if(!path.isEmpty()) {
            System.out.println("There is a connection from " + start + " to " + goal + "!");
            for (int i = 0; i < path.size() - 1; i++) {
                System.out.println(path.get(i) + " is friends with " + path.get(i + 1));
            }
        } else {
            System.out.println("Cannot find a connection between " + start + " and " + goal);
        }
    }
}
