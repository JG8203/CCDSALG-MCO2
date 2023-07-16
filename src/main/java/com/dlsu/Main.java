package com.dlsu;

import java.util.List;
import java.util.Set;

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
                    Set<Integer> friends = networkService.getFriendsOf(id);
                    consoleService.displayFriendsList(id, friends);
                    break;
                case 2:
                    int id1 = consoleService.promptPersonID();
                    int id2 = consoleService.promptPersonID();
                    List<Integer> path = networkService.findPath(id1, id2);
                    consoleService.displayConnectionPath(id1, id2, path);
                    break;
            }
            choice = consoleService.promptMenuOption();
        }
    }
}
