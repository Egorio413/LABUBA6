package lab6.filesystem;
import java.io.IOException;
import java.nio.file.*;
import java.util.Comparator;
import java.util.stream.Stream;

public class FileSystemTask {

    private static final String SURNAME = "Abramov";
    private static final String NAME = "Egor";

    public void run() {
        System.out.println(" 2. Файловая система ");

        // Базовая директория
        Path baseDir = Paths.get(SURNAME);

        try {
            Files.createDirectories(baseDir);
            System.out.println("1. Создана директория: " + baseDir.toAbsolutePath());

            // Внутри создать файл <name>
            Path nameFile = baseDir.resolve(NAME + ".txt");
            Files.writeString(nameFile, "Тут могла быть ваша реклама"); //автоматическое закрытие
            System.out.println("2. Создан файл: " + nameFile.getFileName());

            // Вложенные директории
            Path nestedDirs = baseDir.resolve("dir1").resolve("dir2").resolve("dir3");
            Files.createDirectories(nestedDirs); // создает все папки по цепочке

            Path copiedFile = nestedDirs.resolve(NAME + ".txt");
            // перезапишет файл если он уже есть
            Files.copy(nameFile, copiedFile, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("3. Файл скопирован в: " + nestedDirs);

            Path dir1 = baseDir.resolve("dir1");
            Files.writeString(dir1.resolve("file1.txt"), "Содержимое файла 1");
            System.out.println("4. Создан файл: dir1/file1.txt");

            Path dir2 = dir1.resolve("dir2");
            Files.writeString(dir2.resolve("file2.txt"), "Содержимое файла 2");
            System.out.println("5. Создан файл: dir1/dir2/file2.txt");

            // Рекурсивный обход директории
            System.out.println("\n6. Обход директории " + SURNAME + ":");
            // создаем поток  из всех файлов и папок внутри базовой директории
            try (Stream<Path> paths = Files.walk(baseDir)) {
                paths.forEach(path -> {
                    //  папка ли это
                    String prefix = Files.isDirectory(path) ? "D: " : "F: ";
                    // возвращает только имя без полного пути (dir1)
                    System.out.println(prefix + path.getFileName());
                });
            }

            System.out.println("\n7. Удаление директории dir1...");
            try (Stream<Path> paths = Files.walk(dir1)) {
                //  сортирует пути так чтобы файлы внутри папок шли перед самими папками.
                // Иначе мы не сможем удалить папку, пока в ней есть файлы.
                paths.sorted(Comparator.reverseOrder())
                        .forEach(path -> {
                            try {
                                Files.delete(path);
                            } catch (IOException e) {
                                System.out.println("Не удалось удалить: " + path);
                            }
                        });
            }
            System.out.println("Директория dir1 успешно удалена.");

        } catch (IOException e) {
            System.out.println("Ошибка ввода-вывода: " + e.getMessage());
        }
    }
}