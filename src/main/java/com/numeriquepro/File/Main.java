package com.numeriquepro.File;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        try {
            File fo = new File("random.txt");
            fo.createNewFile(); // creates the file if it does not exist
            if (fo.delete()) { // deletes the file
                System.out.println(fo.getName());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
