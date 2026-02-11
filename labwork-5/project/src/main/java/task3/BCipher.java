package task3;

public class BCipher implements ICipher {
    private static final String UPPER = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
    private static final String LOWER = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";

    @Override
    public String encode(String str) {
        return convert(str);
    }

    @Override
    public String decode(String str) {
        return convert(str);
    }

    private String convert(String str) {
        StringBuilder result = new StringBuilder();
        for (char ch : str.toCharArray()) {
            int indexUpper = UPPER.indexOf(ch);
            int indexLower = LOWER.indexOf(ch);

            if (indexUpper >= 0) {
                result.append(UPPER.charAt(UPPER.length() - 1 - indexUpper));
            } else if (indexLower >= 0) {
                result.append(LOWER.charAt(LOWER.length() - 1 - indexLower));
            } else {
                result.append(ch);
            }
        }
        return result.toString();
    }
}
