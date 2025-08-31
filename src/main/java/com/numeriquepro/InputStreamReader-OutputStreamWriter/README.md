# Java I/O : InputStreamReader & OutputStreamWriter

## 🔹 1. InputStreamReader

### ➤ Définition

`InputStreamReader` est une classe Java (`java.io`) qui sert de **pont entre un flux d’octets (`InputStream`) et un flux de caractères (`Reader`)**.

* Il lit des **octets bruts** depuis une source (`System.in`, `FileInputStream`, `Socket`, etc.)
* Puis il les **décode** en caractères Unicode (`char`) grâce à un **charset** (UTF-8, ISO-8859-1, etc.).

---

### ➤ Constructeurs

```java
InputStreamReader(InputStream in)
InputStreamReader(InputStream in, String charsetName)
InputStreamReader(InputStream in, Charset cs)
InputStreamReader(InputStream in, CharsetDecoder dec)
```

* **Sans charset** → utilise celui de la JVM par défaut.
* **Avec charset** → permet de choisir explicitement (UTF-8, ISO-8859-1, …).

---

### ➤ Méthodes principales

* `int read()` → lit un caractère (retourne -1 si fin de flux).
* `int read(char[] cbuf, int off, int len)` → lit plusieurs caractères.
* `boolean ready()` → indique si des caractères sont dispo sans bloquer.
* `void close()` → ferme le flux.
* `String getEncoding()` → retourne le charset utilisé.

---

### ➤ Utilisations courantes

#### 1. Lecture console

```java
InputStreamReader isr = new InputStreamReader(System.in, StandardCharsets.UTF_8);
BufferedReader br = new BufferedReader(isr);

System.out.print("Votre nom : ");
String name = br.readLine();
System.out.println("Bonjour " + name);
```

#### 2. Lecture fichier texte

```java
FileInputStream fis = new FileInputStream("data.txt");
InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.ISO_8859_1);

int ch;
while ((ch = isr.read()) != -1) {
    System.out.print((char) ch);
}
isr.close();
```

#### 3. Lecture depuis un socket

```java
Socket socket = new Socket("example.com", 80);
InputStreamReader isr = new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8);
BufferedReader br = new BufferedReader(isr);

String line;
while ((line = br.readLine()) != null) {
    System.out.println(line);
}
```

---

## 🔹 2. OutputStreamWriter

### ➤ Définition

`OutputStreamWriter` est l’inverse d’`InputStreamReader`.

* Il prend des **caractères Unicode (`char`)**
* Et les **encode en octets (`byte`)** via un **charset** avant de les écrire dans un `OutputStream`.

👉 Conversion :

```
chars (Writer)  --[CharsetEncoder]-->  bytes (OutputStream)
```

---

### ➤ Constructeurs

```java
OutputStreamWriter(OutputStream out)
OutputStreamWriter(OutputStream out, String charsetName)
OutputStreamWriter(OutputStream out, Charset cs)
OutputStreamWriter(OutputStream out, CharsetEncoder enc)
```

---

### ➤ Méthodes principales

* `void write(int c)` → écrit un caractère.
* `void write(char[] cbuf, int off, int len)` → écrit plusieurs caractères.
* `void write(String str)` → écrit une chaîne complète.
* `void flush()` → force l’écriture du buffer.
* `void close()` → ferme le flux.
* `String getEncoding()` → retourne le charset utilisé.

---

### ➤ Utilisations courantes

#### 1. Écriture fichier texte

```java
FileOutputStream fos = new FileOutputStream("data.txt");
OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);

osw.write("Bonjour, ça va ?");
osw.close();
```

#### 2. Écriture console

```java
OutputStreamWriter writer = new OutputStreamWriter(System.out, StandardCharsets.UTF_8);
writer.write("Salut 👋\n");
writer.flush();
```

#### 3. Écriture sur un socket

```java
Socket socket = new Socket("example.com", 80);
OutputStreamWriter osw = new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8);
BufferedWriter bw = new BufferedWriter(osw);

bw.write("GET / HTTP/1.1\r\n");
bw.write("Host: example.com\r\n\r\n");
bw.flush();
```

---

## ✅ Résumé

* **InputStreamReader** : convertit des **octets** en **caractères** (lecture).
* **OutputStreamWriter** : convertit des **caractères** en **octets** (écriture).
* Tous deux sont essentiels pour gérer le **texte** avec différents encodages (UTF-8, ISO, etc.) et pour interagir avec fichiers, console ou sockets.
