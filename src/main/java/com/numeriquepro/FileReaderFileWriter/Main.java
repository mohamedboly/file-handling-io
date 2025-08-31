package com.numeriquepro.FileReaderFileWriter;

import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try (FileReader fr = new FileReader("data.txt")) {
            int letters = fr.read();
            while(fr.ready()) {
                System.out.println((char) letters);
                letters = fr.read();
            }

            System.out.println();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
