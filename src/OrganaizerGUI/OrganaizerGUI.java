package OrganaizerGUI;

import Manager.OrganaizerManager;
import Domain.Organaizer;

import java.util.Scanner;

public class OrganaizerGUI {
    private OrganaizerManager organaizerManager;
    private Scanner scanner;

    public OrganaizerGUI() {
        organaizerManager = new OrganaizerManager();
        scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            System.out.print("> ");
            String command = scanner.nextLine();

            if (command.equals("ShowEvents")) {
                organaizerManager.showOrganaizer();
            } else if (command.equals("AddEvents")) {
                Organaizer teacherCourse = new Organaizer();
                organaizerManager.addOrganaizer();
            } else if (command.equals("UpdateEvents")) {
                Organaizer teacherCourse = new Organaizer();
                organaizerManager.updateOrganaizer();
            } else if (command.equals("DeleteEvents")) {
                organaizerManager.deleteOrganaizer();
            } else if (command.equals("Exit")) {
                break;
            } else {
                System.out.println("Incorrect input");
            }
        }
    }
}
