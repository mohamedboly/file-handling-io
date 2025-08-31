# README — FileReader vs FileWriter (Java I/O texte)

Ce guide compare **FileReader** (lecture) et **FileWriter** (écriture) pour les **fichiers texte** en Java, avec des exemples concis et les bonnes pratiques.

---

## 1) Définition rapide

* **FileReader** : lit des **caractères** depuis un fichier texte. (basé sur l’encodage **par défaut** de la JVM)
* **FileWriter** : écrit des **caractères** dans un fichier texte. (encodage **par défaut** de la JVM)

> 💡 Pour contrôler l’encodage (UTF‑8 recommandé), préférez `InputStreamReader(new FileInputStream(...), StandardCharsets.UTF_8)` et `OutputStreamWriter(new FileOutputStream(...), StandardCharsets.UTF_8)`.

---

## 2) Différences clés

| Point                     | FileReader                                        | FileWriter                                      |
| ------------------------- | ------------------------------------------------- | ----------------------------------------------- |
| Rôle                      | Lecture de texte (char)                           | Écriture de texte (char)                        |
| Encodage                  | Utilise **charset par défaut** (non configurable) | Idem                                            |
| Alternative avec encodage | `InputStreamReader(FileInputStream, Charset)`     | `OutputStreamWriter(FileOutputStream, Charset)` |
| Bufferisation conseillée  | `BufferedReader`                                  | `BufferedWriter`                                |
| Cas d’usage               | Lire un fichier `.txt`, `.csv` simple             | Générer un `.txt`, `.log`, etc.                 |

---

## 3) Exemples — FileReader

### 3.1 Lecture **caractère par caractère**

```java
import java.io.*;

public class ReadChars {
    public static void main(String[] args) {
        try (FileReader fr = new FileReader("data.txt")) { // encodage par défaut
            int ch;
            while ((ch = fr.read()) != -1) {
                System.out.print((char) ch);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

### 3.2 Lecture **ligne par ligne** (recommandée)

```java
import java.io.*;

public class ReadLines {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

### 3.3 Lecture **avec encodage explicite (UTF‑8)** ✅

```java
import java.io.*;
import java.nio.charset.StandardCharsets;

public class ReadUtf8 {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(
                 new InputStreamReader(new FileInputStream("data.txt"), StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

---

## 4) Exemples — FileWriter

### 4.1 Écriture simple (écrase le fichier)

```java
import java.io.*;

public class WriteSimple {
    public static void main(String[] args) {
        try (FileWriter fw = new FileWriter("out.txt")) { // encodage par défaut
            fw.write("Bonjour le monde\n");
            fw.write("Deuxième ligne\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

### 4.2 Ajout en fin de fichier (*append*)

```java
import java.io.*;

public class WriteAppend {
    public static void main(String[] args) {
        try (FileWriter fw = new FileWriter("out.txt", true)) { // append = true
            fw.write("Ligne ajoutée\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

### 4.3 Écriture efficace avec `BufferedWriter`

```java
import java.io.*;

public class WriteBuffered {
    public static void main(String[] args) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("out.txt"))) {
            bw.write("Ligne 1");
            bw.newLine();
            bw.write("Ligne 2");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

### 4.4 Écriture **UTF‑8** (bonne pratique) ✅

```java
import java.io.*;
import java.nio.charset.StandardCharsets;

public class WriteUtf8 {
    public static void main(String[] args) {
        try (BufferedWriter bw = new BufferedWriter(
                 new OutputStreamWriter(new FileOutputStream("out.txt"), StandardCharsets.UTF_8))) {
            bw.write("Café, emoji 😊, caractères accentués");
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

---

## 5) Pièges & bonnes pratiques

* **Encodage** : `FileReader`/`FileWriter` utilisent **le charset par défaut** → peut varier selon la machine/IDE. Pour des fichiers portables, **spécifie l’encodage** (UTF‑8) via `InputStreamReader`/`OutputStreamWriter`.
* **Chemin du fichier** : un chemin relatif est résolu depuis le **working directory** (pas le package de la classe). Utilise des chemins explicites ou charge via le **classpath** (`getResourceAsStream`).
* **Bufferisation** : préfère `BufferedReader/BufferedWriter` pour la performance et les méthodes pratiques (`readLine`, `newLine`).
* **Essayer/fermer** : utilise **try‑with‑resources** pour fermer automatiquement les flux.
* **Binaire vs texte** : pour du binaire (images, PDF, ZIP), utilise `FileInputStream`/`FileOutputStream`, **pas** Reader/Writer.

---

## 6) Mini‑cheatsheet

```java
// Lire (texte, UTF‑8)
try (var br = new BufferedReader(new InputStreamReader(
        new FileInputStream("data.txt"), StandardCharsets.UTF_8))) {
    br.lines().forEach(System.out::println);
}

// Écrire (texte, UTF‑8)
try (var bw = new BufferedWriter(new OutputStreamWriter(
        new FileOutputStream("out.txt"), StandardCharsets.UTF_8))) {
    bw.write("Hello UTF‑8");
    bw.newLine();
}
```

---

## 7) Quand choisir quoi ?

* **Lecture simple, petit fichier, pas d’exigence d’encodage** → `FileReader` (avec `BufferedReader`).
* **Encodage maîtrisé / internationalisation** → `InputStreamReader + UTF‑8`.
* **Écriture simple** → `FileWriter` (écrase) ou `FileWriter(…, true)` (append).
* **Écriture avec encodage** → `OutputStreamWriter + UTF‑8`.

---

**Conclusion** : *FileReader/FileWriter* sont pratiques mais **liés au charset par défaut**. En production, **préférez contrôler l’encodage** via *InputStreamReader/OutputStreamWriter* (UTF‑8), et utilisez la **bufferisation** pour de meilleures performances.
