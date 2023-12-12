package Manager;

import Domain.Organaizer;
import OrganaizerDatabase.OrganaizerDatabase;

import java.util.List;
import java.util.Scanner;

public class OrganaizerManager {
    private OrganaizerDatabase organaizerDatabase;

    public OrganaizerManager() {
        organaizerDatabase = new OrganaizerDatabase();
    }

    public void showOrganaizer() {
        try {
            List<Organaizer> organaizer = organaizerDatabase.getAllOrganaizer();


            if (organaizer.isEmpty()) {
                System.out.println("Нет назначенных событий");
            } else {
                System.out.println("Предстоящие события:");
                for (Organaizer teacherCourse : organaizer) {
                    System.out.println(teacherCourse);
                }
            }
        } finally {
            organaizerDatabase.closeConnection();
        }
    }

    public void addOrganaizer() {
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Событие:");
            String type = scanner.nextLine();

            System.out.println("Дата:");
            String date = scanner.nextLine();

            System.out.println("Время:");
            String time = scanner.nextLine();

            System.out.println("Описание:");
            String description = scanner.nextLine();

            Organaizer organaizer = new Organaizer();
            organaizer.setOrganaizerType(type);
            organaizer.setDate(date);
            organaizer.setTime(time);
            organaizer.setDescription(description);

            organaizerDatabase.addOrganaizerEvents(organaizer);

            System.out.println("Событие добавлено");
        } finally {
            organaizerDatabase.closeConnection();
        }
    }

    public void updateOrganaizer() {
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Введите ID события:");
            int id = scanner.nextInt();
            scanner.nextLine();

            Organaizer OrganaizerToUpdate = organaizerDatabase.getOrganaizer(id);

            if (OrganaizerToUpdate == null) {
                System.out.println("Событие не найдено");
                return;
            }

            System.out.println("Новое событие:");
            String type = scanner.nextLine();

            System.out.println("Новая дата:");
            String date = scanner.nextLine();

            System.out.println("Новое время:");
            String time = scanner.nextLine();

            System.out.println("Новое описание:");
            String description = scanner.nextLine();

            OrganaizerToUpdate.setOrganaizerType(type);
            OrganaizerToUpdate.setDate(date);
            OrganaizerToUpdate.setTime(time);
            OrganaizerToUpdate.setDescription(description);

            organaizerDatabase.updateOrganaizer(OrganaizerToUpdate);

            System.out.println("Событие добавлено");
        } finally {
            organaizerDatabase.closeConnection();
        }
    }

    public void deleteOrganaizer() {
        try {
            Scanner scanner = new Scanner(System.in);

            System.out.println("ID события:");
            int id = scanner.nextInt();
            scanner.nextLine();

            Organaizer teacherCourseToDelete = organaizerDatabase.getOrganaizer(id);

            if (teacherCourseToDelete == null) {
                System.out.println("Событие с указанным ID не найдено");
                return;
            }

            organaizerDatabase.deleteOrganaizer(id);

            System.out.println("Событие удалено");
        } finally {
            organaizerDatabase.closeConnection();
        }
    }
}
