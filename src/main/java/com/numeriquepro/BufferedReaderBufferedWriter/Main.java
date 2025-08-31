package com.numeriquepro.BufferedReaderBufferedWriter;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("You typed: " + br.readLine());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
            while (br.ready()) {
                System.out.println(br.readLine());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("out.txt"))) {
            bw.write("Bonjour");
            bw.newLine();
            bw.write("Deuxième ligne");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
