package task5;

public class Main {
    public static void main(String[] args) {
        ITransliterator transliterator = new GostTransliterator();

        String cyr = "Привет, как дела?";
        String lat = transliterator.CyrillicToLatin(cyr);
        String back = transliterator.LatinToCyrillic(lat);

        System.out.println("Кириллица: " + cyr);
        System.out.println("Латиница: " + lat);
        System.out.println("Обратно: " + back);
    }
}
