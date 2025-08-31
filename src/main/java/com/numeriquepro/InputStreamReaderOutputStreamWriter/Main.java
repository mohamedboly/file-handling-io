package com.numeriquepro.InputStreamReaderOutputStreamWriter;

import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        try (InputStreamReader isr = new InputStreamReader(System.in)) {
            System.out.print("Enter some letters:");
            int letters = isr.read();   // <-- 1ère lecture

            while (isr.ready()) {       // <-- vérifie si d’autres caractères sont dispos
                System.out.println((char) letters);
                letters = isr.read();   // <-- lit caractère par caractère
            }

            isr.close();
            System.out.println();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}