package com.dlsu;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReader {
    public static Relationship[] readFile(String filepath) {
        List<Relationship> list = new ArrayList<>();

        try {
            File f = new File(filepath);
            Scanner scanner = new Scanner(f);

            scanner.nextLine();

            while (scanner.hasNextInt()) {
                int id = scanner.nextInt();
                int friend = scanner.nextInt();
                Relationship relationship = new Relationship(id, friend);
                list.add(relationship);
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        }

        Relationship[] relationships = new Relationship[list.size()];
        relationships = list.toArray(relationships);

        return relationships;
    }
}
