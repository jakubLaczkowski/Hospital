import java.util.*;
public class Main {

    public static void main(String[] args) {
        ArrayList<Patient> listaPacjentow = new ArrayList<Patient>();
        Patient pacjent;
        Database dataBase = new Database();
        String option;
        Menu menu = new Menu();
        int choice;

        listaPacjentow = dataBase.tryReadDataBase();

        do {
            option = menu.menuOptions();
            if(option.equals("1")){
                if(listaPacjentow.size() != 0) {
                    for (int i = 0; i < listaPacjentow.size(); i++) {
                        System.out.println(i + ". " + listaPacjentow.get(i));
                    }
                }
                else
                    System.out.println("Lista pacjentow jest pusta");
                menu.backToMenu();
            }
            else if (option.equals("2")) {
                do {
                    pacjent = new Patient();
                    menu.setNext(false);
                    menu.setMenu(false);
                    menu.readName(pacjent);
                    menu.readPesel(pacjent);
                    menu.readBloodPressure(pacjent);
                    menu.readTemperature(pacjent);
                    menu.readGlucoseLevel(pacjent);
                    listaPacjentow.add(pacjent);
                    menu.addNextPatient();
                    if(!menu.getNext())
                        menu.backToMenu();
                } while (menu.getNext());
            }
            else if(option.equals("3")){
                pacjent = new Patient();
                menu.setMenu(false);
                if(listaPacjentow.size() != 0){
                    pacjent = listaPacjentow.get(menu.choosePatient(listaPacjentow));
                    menu.readIntake(pacjent);
                    menu.readIsVomit(pacjent);
                    menu.readUrine(pacjent);
                    menu.readStoolType(pacjent);
                    menu.readOtherSources(pacjent);
                    System.out.println("Bilans plynow to: " + pacjent.fluidBalance());
                    pacjent.setBalance(pacjent.fluidBalance());
                    menu.backToMenu();
                }
                else{
                    System.out.println("Lista pacjentow jest pusta!");
                    menu.setMenu(true);
                }
            }
            else if(option.equals("4")){
                do {
                    if (listaPacjentow.size() != 0) {
                        menu.removePatient(listaPacjentow);
                    } else {
                        System.out.println("Lista pacjentow jest pusta!");
                        menu.setMenu(true);
                    }
                    menu.backToMenu();
                }while(menu.getNext());
            }
            else if(option.equals("5")){
                do {
                    choice = menu.choosePatient(listaPacjentow);
                    menu.chooseParameter(choice, listaPacjentow);
                    menu.backToMenu();
                }while(menu.getNext());
            }
            else if(option.equals("0")){
                menu.inputClose();
                dataBase.tryCreateDataBase(listaPacjentow);
                dataBase.outputClose();
                System.exit(0);
            }
            else {
                System.out.println("Podaj poprawna pozycje menu");
                menu.setMenu(true);
            }
        }while(menu.getMenu());

        menu.inputClose();
        for(int i=0; i< listaPacjentow.size(); i++){
            System.out.println(i + ". " + listaPacjentow.get(i));
        }
        dataBase.tryCreateDataBase(listaPacjentow);
        dataBase.outputClose();
    }
}
