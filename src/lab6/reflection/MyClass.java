package lab6.reflection;

public class MyClass {

    public void publicMethod1() {
        System.out.println("Вызван публичный метод 1");
    }

    public void publicMethod2(String message) {
        System.out.println("Вызван публичный метод 2 с сообщением: " + message);
    }

    @Repeat(times = 3) // Вызовется 3 раза
    protected void protectedMethod1() {
        System.out.println("  -> Вызван защищенный метод 1");
    }

    protected void protectedMethod2() {
        System.out.println("Вызван защищенный метод 2");
    }

    @Repeat(times = 2) // Вызовется 2 раза
    private void privateMethod1() {
        System.out.println("  -> Вызван приватный метод 1");
    }

    @Repeat // Используется дефолтное значение (1 раз)
    private void privateMethod2(int number) {
        System.out.println("  -> Вызван приватный метод 2 с числом: " + number);
    }
}
