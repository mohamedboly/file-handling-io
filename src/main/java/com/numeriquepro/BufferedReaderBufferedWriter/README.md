# README — BufferedReader vs BufferedWriter (Java I/O)

## 🔹 1. BufferedReader

### ➤ Définition

`BufferedReader` est une classe Java (`java.io.BufferedReader`) qui permet de **lire efficacement du texte** depuis un flux de caractères (`Reader`) en utilisant un **tampon mémoire**.

* Sans buffer : chaque appel à `read()` lit directement depuis la source (lent).
* Avec buffer : les données sont lues par blocs et stockées en mémoire, ce qui rend les lectures plus rapides et pratiques.

👉 Conversion typique :

```
InputStream (octets) → InputStreamReader (chars) → BufferedReader (buffer + méthodes pratiques)
```

### ➤ Constructeurs

```java
BufferedReader(Reader in)
BufferedReader(Reader in, int bufferSize)
```

### ➤ Méthodes principales

* `String readLine()` → lit une ligne entière (jusqu’au saut de ligne).
* `int read()` → lit un caractère (ou -1 si fin de fichier).
* `boolean ready()` → teste si le flux est prêt à être lu.
* `void close()` → ferme le flux.

### ➤ Exemples d’utilisation

#### 1. Lecture depuis la console

```java
try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
    System.out.println("You typed: " + br.readLine());
} catch (IOException e) {
    e.printStackTrace();
}
```

#### 2. Lecture d’un fichier ligne par ligne

```java
try (BufferedReader br = new BufferedReader(new FileReader("note.txt"))) {
    String line;
    while ((line = br.readLine()) != null) {
        System.out.println(line);
    }
} catch (IOException e) {
    e.printStackTrace();
}
```

---

## 🔹 2. BufferedWriter

### ➤ Définition

`BufferedWriter` est une classe Java (`java.io.BufferedWriter`) qui permet **d’écrire efficacement du texte** dans un flux de sortie (`Writer`) en utilisant un **tampon mémoire**.

* Sans buffer : chaque `write()` écrit directement dans le fichier/sortie (lent).
* Avec buffer : les caractères sont accumulés et écrits en bloc.

👉 Conversion typique :

```
BufferedWriter (buffer + méthodes pratiques) → FileWriter / OutputStreamWriter → OutputStream (octets)
```

### ➤ Constructeurs

```java
BufferedWriter(Writer out)
BufferedWriter(Writer out, int bufferSize)
```

### ➤ Méthodes principales

* `void write(String s)` → écrit une chaîne.
* `void write(int c)` → écrit un caractère.
* `void newLine()` → écrit une fin de ligne adaptée au système.
* `void flush()` → force l’écriture immédiate du buffer.
* `void close()` → ferme le flux.

### ➤ Exemples d’utilisation

#### 1. Écriture simple dans un fichier

```java
try (BufferedWriter bw = new BufferedWriter(new FileWriter("out.txt"))) {
    bw.write("Bonjour");
    bw.newLine();
    bw.write("Deuxième ligne");
} catch (IOException e) {
    e.printStackTrace();
}
```

#### 2. Ajout en fin de fichier

```java
try (BufferedWriter bw = new BufferedWriter(new FileWriter("out.txt", true))) {
    bw.write("Nouvelle ligne ajoutée");
    bw.newLine();
} catch (IOException e) {
    e.printStackTrace();
}
```

#### 3. Forcer l’écriture

```java
try (BufferedWriter bw = new BufferedWriter(new FileWriter("out.txt"))) {
    bw.write("Texte temporaire...");
    bw.flush(); // écrit immédiatement
} catch (IOException e) {
    e.printStackTrace();
}
```

---

## 🔹 3. Comparatif BufferedReader vs BufferedWriter

| Aspect        | BufferedReader                                   | BufferedWriter                                 |
| ------------- | ------------------------------------------------ | ---------------------------------------------- |
| Rôle          | Lire du texte efficacement                       | Écrire du texte efficacement                   |
| Source/Cible  | `Reader` (ex : FileReader, InputStreamReader)    | `Writer` (ex : FileWriter, OutputStreamWriter) |
| Méthode phare | `readLine()`                                     | `newLine()`                                    |
| Performance   | Lecture par blocs                                | Écriture par blocs                             |
| Cas d’usage   | Lire un fichier ligne par ligne, saisir du texte | Générer un fichier texte, écrire des logs      |

---

## ✅ Résumé

* **BufferedReader** = lecture optimisée + pratique (`readLine`).
* **BufferedWriter** = écriture optimisée + pratique (`newLine`).
* Les deux améliorent les performances en évitant les accès directs à la source/sortie pour chaque caractère.
