package task2;

interface Age{
    int x = 21;
    void getAge();
}

class Demo {
    public static void main(String[] agrs) {
        Age obj = new Age() {
            @Override
            public void getAge() {
                System.out.print("Возраст: " + x);
            }
        };
        obj.getAge();
    }
}
