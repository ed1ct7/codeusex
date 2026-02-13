package task4.Bank;

public enum Bank {
    Sberbank("Сбербанк"),
    TBank("T-Банк"),
    AlphaBank("Альфабанк"),
    SPBbank("СПБбанк");
    ;

    private final String bankName;

    Bank(String name){
        this.bankName = name;
    }

    public static Bank MakeFromValue(String stringvalue){
        for(Bank s : Status.values()){
            if(s.name.equals(stringvalue)){
                return s;
            }
        }
    }

    public String getBankName() {
        return bankName;
    }
}
