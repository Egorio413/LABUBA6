package lab6;
import lab6.filesystem.FileSystemTask;
import lab6.reflection.Invoker;

public class Main {
    public static void main(String[] args) {
        // Запуск первой задачи
        new Invoker().run();

        // Запуск второй задачи
        new FileSystemTask().run();
    }
}
