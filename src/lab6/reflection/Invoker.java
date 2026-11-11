package lab6.reflection;
import java.lang.reflect.Method;

public class Invoker {
    public void run() {
        System.out.println(" 1. Рефлексия ");
        MyClass obj = new MyClass();
        Class<?> clazz = obj.getClass();

        // возвращает все методы класса вместе с приват и протектед в виде массива
        Method[] methods = clazz.getDeclaredMethods();

        for (Method method : methods) {
            // Проверяем есть ли над методом  аннотация
            if (method.isAnnotationPresent(Repeat.class)) {
                // Получаем саму аннотацию чтобы прочитать параметр
                Repeat repeat = method.getAnnotation(Repeat.class);
                int times = repeat.times();

                // снимаем защиту для вызова защищенных и приватных
                method.setAccessible(true);

                System.out.println("Вызываем метод: " + method.getName() + " (" + times + " раз)");

                for (int i = 0; i < times; i++) {
                    try {
                        if (method.getParameterCount() == 1) {
                            if (method.getParameterTypes()[0] == int.class) {
                                method.invoke(obj, 42); // Передаем число 42
                            } else if (method.getParameterTypes()[0] == String.class) {
                                method.invoke(obj, "Строка"); // Передаем строку
                            }
                        } else {
                            method.invoke(obj); // Если параметров нет
                        }
                    } catch (Exception e) {
                        System.out.println("Ошибка при вызове: " + e.getMessage());
                    }
                }
            }
        }
        System.out.println();
    }
}
