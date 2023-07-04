package com.dlsu;

import org.jgrapht.graph.DefaultEdge;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ConsoleService consoleService = new ConsoleService();
        NetworkService networkService = new NetworkService();

        String filePath = consoleService.promptFilePath();
        networkService.loadRelationships(filePath);

        int choice = consoleService.promptMenuOption();

        while (choice != 3) {
            switch(choice){
                case 1:
                    int id = consoleService.promptPersonID();
                    List<Integer> friends = networkService.getFriendsOf(id);
                    consoleService.displayFriendsList(id, friends);
                    break;
                case 2:
                    int id1 = consoleService.promptPersonID();
                    int id2 = consoleService.promptPersonID();
                    // TODO: Deprecated getEdge and getSource methods doon sa isang method. Fix sa actual impl.
                    break;
            }
            choice = consoleService.promptMenuOption();
        }
    }
}
