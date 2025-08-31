# README — Utilisation de la classe `File` en Java

La classe **`java.io.File`** sert à représenter et manipuler des chemins de fichiers ou de répertoires.
⚠️ **Important** : un objet `File` ne représente qu’un *chemin*, pas forcément un fichier physique existant.

---

## 🔹 1. Création d’un objet `File`

```java
File f = new File("exemple.txt");
```

* Crée un objet `File` représentant le chemin `exemple.txt` (dans le répertoire courant).
* À ce stade, **le fichier n’existe pas encore sur le disque**.

---

## 🔹 2. Méthodes courantes

| Méthode             | Description                                                                                   |
| ------------------- | --------------------------------------------------------------------------------------------- |
| `createNewFile()`   | Crée physiquement un nouveau fichier vide. Retourne `true` si créé, `false` si déjà existant. |
| `delete()`          | Supprime le fichier/répertoire (retourne `true` si succès).                                   |
| `exists()`          | Vérifie si le fichier/répertoire existe.                                                      |
| `getName()`         | Renvoie le **nom** du fichier (ex: `exemple.txt`).                                            |
| `getAbsolutePath()` | Renvoie le chemin absolu complet.                                                             |
| `length()`          | Taille du fichier (en octets).                                                                |
| `isFile()`          | Retourne `true` si c’est un fichier.                                                          |
| `isDirectory()`     | Retourne `true` si c’est un répertoire.                                                       |
| `mkdir()`           | Crée un répertoire.                                                                           |
| `mkdirs()`          | Crée un répertoire et toute la hiérarchie manquante.                                          |
| `list()`            | Retourne la liste des fichiers dans un répertoire.                                            |

---

## 🔹 3. Exemples pratiques

### ➤ Créer un fichier

```java
try {
    File f = new File("data.txt");
    if (f.createNewFile()) {
        System.out.println("Fichier créé : " + f.getName());
    } else {
        System.out.println("Fichier existe déjà.");
    }
} catch (IOException e) {
    e.printStackTrace();
}
```

### ➤ Supprimer un fichier

```java
File f = new File("data.txt");
if (f.delete()) {
    System.out.println("Supprimé : " + f.getName());
} else {
    System.out.println("Échec de suppression.");
}
```

### ➤ Vérifier et afficher des infos

```java
File f = new File("data.txt");
if (f.exists()) {
    System.out.println("Nom : " + f.getName());
    System.out.println("Chemin absolu : " + f.getAbsolutePath());
    System.out.println("Taille : " + f.length() + " octets");
    System.out.println("Est un fichier ? " + f.isFile());
} else {
    System.out.println("Fichier introuvable");
}
```

### ➤ Créer un répertoire

```java
File dir = new File("monDossier");
if (dir.mkdir()) {
    System.out.println("Répertoire créé : " + dir.getName());
}
```

### ➤ Lister le contenu d’un répertoire

```java
File dir = new File("."); // répertoire courant
String[] fichiers = dir.list();
for (String nom : fichiers) {
    System.out.println(nom);
}
```

---

## ✅ Résumé

* `File` = représente un **chemin** (fichier ou dossier).
* **Ne crée pas automatiquement** le fichier/répertoire → il faut appeler `createNewFile()` ou `mkdir()`.
* Offre de nombreuses méthodes pour vérifier, créer, supprimer et lister fichiers/répertoires.
* Utile en combinaison avec `FileReader` / `FileWriter` pour manipuler du contenu texte.
