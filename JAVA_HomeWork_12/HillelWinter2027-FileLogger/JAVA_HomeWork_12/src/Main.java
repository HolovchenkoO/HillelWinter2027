import filesLoggers.*;

public class Main {
    public static void main(String[] args) {
        // 1. Створюємо конфігурацію вручну або завантажуємо через Loader (якщо є файл config.txt)
        FileLoggerConfiguration config = new FileLoggerConfiguration(
                "Log.txt",
                LoggingLevel.DEBUG,
                150,
                "[%TIME%][%LEVEL%] Повідомлення: %MESSAGE%"
        );git


        Logger logger = new FileLogger(config);

        //Тестуємо логування
        for (int i = 1; i <= 10; i++) {
            logger.info("Тестове інформаційне повідомлення номер " + i);
            logger.debug("Деталізоване налагоджувальне повідомлення номер " + i);

            try { Thread.sleep(500); } catch (InterruptedException e) {} // Пауза для різного часу в логах
        }

        System.out.println("Логування завершено. Перевірте папку проекту на наявність нових файлів.");
    }
}