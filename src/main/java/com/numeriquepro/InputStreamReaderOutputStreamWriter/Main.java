package com.numeriquepro.InputStreamReaderOutputStreamWriter;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) {
        try (InputStreamReader isr = new InputStreamReader(System.in)) {
            System.out.print("Enter some letters:");
            int letters = isr.read();   // <-- 1ère lecture

            while (isr.ready()) {       // <-- vérifie si d’autres caractères sont dispos
                System.out.println((char) letters);
                letters = isr.read();   // <-- lit caractère par caractère
            }


            System.out.println();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try (OutputStreamWriter osw = new OutputStreamWriter(System.out)) {
            osw.write("Hello World");
            osw.write(97);
            osw.write(10);
            osw.write('A');
            osw.write('\n');

            char[] arr = "hello world".toCharArray();
            osw.write(arr);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


    }
}