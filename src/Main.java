import java.util.*;
public class Main {

    public static void main(String[] args) {
        ArrayList<Patient> patients = new ArrayList<Patient>();
        Patient patient;
        DatabaseHandler dataBase;
        String option;
        Menu menu = new Menu();
        int choice;

        dataBase = new DatabaseHandler("C:/Patient DataBase/baza danych pacjentow.txt");
        patients = dataBase.tryReadDataBase();

        do {
            option = menu.menuOptions();
            if(option.equals("1")){
                if(patients.size() != 0) {
                    for (int i = 0; i < patients.size(); i++) {
                        System.out.println(i + ". " + patients.get(i));
                    }
                }
                else
                    System.out.println("Lista pacjentow jest pusta");
                menu.backToMenu();
            }
            else if (option.equals("2")) {
                do {
                    patient = new Patient();
                    menu.setNext(false);
                    menu.setMenu(false);
                    menu.readName(patient);
                    menu.readPesel(patient);
                    menu.readBloodPressure(patient);
                    menu.readTemperature(patient);
                    menu.readGlucoseLevel(patient);
                    patients.add(patient);
                    menu.addNextPatient();
                    if(!menu.getNext())
                        menu.backToMenu();
                } while (menu.getNext());
            }
            else if(option.equals("3")){
                patient = new Patient();
                menu.setMenu(false);
                if(patients.size() != 0){
                    patient = patients.get(menu.choosePatient(patients));
                    menu.readIntake(patient);
                    menu.readIsVomit(patient);
                    menu.readUrine(patient);
                    menu.readStoolType(patient);
                    menu.readOtherSources(patient);
                    System.out.println("Bilans plynow to: " + patient.fluidBalance());
                    patient.setBalance(patient.fluidBalance());
                    menu.backToMenu();
                }
                else{
                    System.out.println("Lista pacjentow jest pusta!");
                    menu.setMenu(true);
                }
            }
            else if(option.equals("4")){
                do {
                    if (patients.size() != 0) {
                        menu.removePatient(patients);
                    } else {
                        System.out.println("Lista pacjentow jest pusta!");
                        menu.setMenu(true);
                    }
                    menu.backToMenu();
                }while(menu.getNext());
            }
            else if(option.equals("5")){
                do {
                    choice = menu.choosePatient(patients);
                    menu.chooseParameter(choice, patients);
                    menu.backToMenu();
                }while(menu.getNext());
            }
            else if(option.equals("0")){
                menu.inputClose();
                dataBase.tryCreateDataBase(patients);
                dataBase.outputClose();
                System.exit(0);
            }
            else {
                System.out.println("Podaj poprawna pozycje menu");
                menu.setMenu(true);
            }
        }while(menu.getMenu());

        menu.inputClose();
        for(int i = 0; i < patients.size(); i++){
            System.out.println(i + ". " + patients.get(i));
        }
        dataBase.tryCreateDataBase(patients);
        dataBase.outputClose();
    }
}
