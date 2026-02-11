package task3;

public class Main {
    public static void main(String[] args) {
        BCipher bCipher = new BCipher();
        String source = "Привет, Мир";
        String encoded = bCipher.encode(source);
        String decoded = bCipher.decode(encoded);

        System.out.println("Исходная строка: " + source);
        System.out.println("Зашифрованная строка: " + encoded);
        System.out.println("Расшифрованная строка: " + decoded);
    }
}
