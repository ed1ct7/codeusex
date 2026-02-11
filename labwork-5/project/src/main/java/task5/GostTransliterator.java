package task5;

import java.util.LinkedHashMap;
import java.util.Map;

public class GostTransliterator implements ITransliterator {
    private static final Map<String, String> CYR_TO_LAT = new LinkedHashMap<>();
    private static final Map<String, String> LAT_TO_CYR = new LinkedHashMap<>();

    static {
        CYR_TO_LAT.put("а", "a");
        CYR_TO_LAT.put("б", "b");
        CYR_TO_LAT.put("в", "v");
        CYR_TO_LAT.put("г", "g");
        CYR_TO_LAT.put("д", "d");
        CYR_TO_LAT.put("е", "e");
        CYR_TO_LAT.put("ё", "yo");
        CYR_TO_LAT.put("ж", "zh");
        CYR_TO_LAT.put("з", "z");
        CYR_TO_LAT.put("и", "i");
        CYR_TO_LAT.put("й", "j");
        CYR_TO_LAT.put("к", "k");
        CYR_TO_LAT.put("л", "l");
        CYR_TO_LAT.put("м", "m");
        CYR_TO_LAT.put("н", "n");
        CYR_TO_LAT.put("о", "o");
        CYR_TO_LAT.put("п", "p");
        CYR_TO_LAT.put("р", "r");
        CYR_TO_LAT.put("с", "s");
        CYR_TO_LAT.put("т", "t");
        CYR_TO_LAT.put("у", "u");
        CYR_TO_LAT.put("ф", "f");
        CYR_TO_LAT.put("х", "x");
        CYR_TO_LAT.put("ц", "cz");
        CYR_TO_LAT.put("ч", "ch");
        CYR_TO_LAT.put("ш", "sh");
        CYR_TO_LAT.put("щ", "shh");
        CYR_TO_LAT.put("ъ", "``");
        CYR_TO_LAT.put("ы", "y'");
        CYR_TO_LAT.put("ь", "`");
        CYR_TO_LAT.put("э", "e`");
        CYR_TO_LAT.put("ю", "yu");
        CYR_TO_LAT.put("я", "ya");

        LAT_TO_CYR.put("shh", "щ");
        LAT_TO_CYR.put("yo", "ё");
        LAT_TO_CYR.put("zh", "ж");
        LAT_TO_CYR.put("cz", "ц");
        LAT_TO_CYR.put("ch", "ч");
        LAT_TO_CYR.put("sh", "ш");
        LAT_TO_CYR.put("``", "ъ");
        LAT_TO_CYR.put("y'", "ы");
        LAT_TO_CYR.put("e`", "э");
        LAT_TO_CYR.put("yu", "ю");
        LAT_TO_CYR.put("ya", "я");
        LAT_TO_CYR.put("a", "а");
        LAT_TO_CYR.put("b", "б");
        LAT_TO_CYR.put("v", "в");
        LAT_TO_CYR.put("g", "г");
        LAT_TO_CYR.put("d", "д");
        LAT_TO_CYR.put("e", "е");
        LAT_TO_CYR.put("z", "з");
        LAT_TO_CYR.put("i", "и");
        LAT_TO_CYR.put("j", "й");
        LAT_TO_CYR.put("k", "к");
        LAT_TO_CYR.put("l", "л");
        LAT_TO_CYR.put("m", "м");
        LAT_TO_CYR.put("n", "н");
        LAT_TO_CYR.put("o", "о");
        LAT_TO_CYR.put("p", "п");
        LAT_TO_CYR.put("r", "р");
        LAT_TO_CYR.put("s", "с");
        LAT_TO_CYR.put("t", "т");
        LAT_TO_CYR.put("u", "у");
        LAT_TO_CYR.put("f", "ф");
        LAT_TO_CYR.put("x", "х");
        LAT_TO_CYR.put("`", "ь");
    }

    @Override
    public String CyrillicToLatin(String text) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            String lower = String.valueOf(Character.toLowerCase(ch));
            String mapped = CYR_TO_LAT.get(lower);

            if (mapped == null) {
                result.append(ch);
            } else if (Character.isUpperCase(ch)) {
                result.append(Character.toUpperCase(mapped.charAt(0))).append(mapped.substring(1));
            } else {
                result.append(mapped);
            }
        }
        return result.toString();
    }

    @Override
    public String LatinToCyrillic(String text) {
        StringBuilder result = new StringBuilder();
        int i = 0;

        while (i < text.length()) {
            boolean found = false;

            for (Map.Entry<String, String> entry : LAT_TO_CYR.entrySet()) {
                String key = entry.getKey();
                int end = i + key.length();
                if (end > text.length()) {
                    continue;
                }

                String part = text.substring(i, end);
                if (part.equalsIgnoreCase(key)) {
                    String value = entry.getValue();
                    if (!part.isEmpty() && Character.isUpperCase(part.charAt(0))) {
                        value = value.toUpperCase();
                    }
                    result.append(value);
                    i = end;
                    found = true;
                    break;
                }
            }

            if (!found) {
                result.append(text.charAt(i));
                i++;
            }
        }

        return result.toString();
    }
}
