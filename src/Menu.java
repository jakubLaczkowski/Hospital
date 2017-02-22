import java.util.*;
import org.apache.log4j.*;
public class Menu {
    private static final Logger LOGGER = Logger.getLogger(Menu.class);
    private Scanner input;
    private boolean czyPoprawne;
    private boolean menu;
    private boolean next;
    private String option;
    private StringBuilder sb;


    public Menu(){
        input = new Scanner(System.in).useLocale(Locale.US);
        czyPoprawne = false;
        menu = false;
        next = false;
    }
    public String menuOptions(){
        sb = new StringBuilder();
        sb.append("-------------------------------------")
        .append("\n")
        .append("1. Wyswietl liste pacjentow.")
        .append("\n")
        .append("2. Dodaj pacjenta do listy.")
        .append("\n")
        .append("3. Oblicz bilans plynow.")
        .append("\n")
        .append("4. Usun pacjenta z bazy.")
        .append("\n")
        .append("5. Aktualizacja parametrow pacjenta.")
        .append("\n")
        .append("0. Wyjdz z programu.")
        .append("\n")
        .append("-------------------------------------");
        System.out.println(sb);
        option = input.nextLine();
        return option;
    }
    public void readName(Patient patient){
        System.out.println("Podaj imie:");
        patient.setName(input.nextLine());
    }
    public void readPesel(Patient patient){
        System.out.println("Podaj pesel:");
        patient.setPesel(input.nextLine());
    }
    public void readBloodPressure(Patient patient){
        System.out.println("Podaj cisnienie:");
        patient.setBloodPressure(input.nextLine());
    }
    public void readTemperature(Patient patient) {
        czyPoprawne = false;
        while (!czyPoprawne) {
            System.out.println("Podaj temperature:");
            try {
                patient.setTemperature(input.nextDouble());
                czyPoprawne = true;
            } catch (InputMismatchException e) {
                LOGGER.error("Wrong input format", e);
            }
            input.nextLine();
        }
    }

    public void readGlucoseLevel(Patient patient) {
        czyPoprawne = false;
        while (!czyPoprawne) {
            System.out.println("Podaj poziom cukru:");
            try {
                patient.setGlucoseLevel(input.nextDouble());
                czyPoprawne = true;
            } catch (InputMismatchException e) {
                LOGGER.error("Wrong input format", e);
            }
            input.nextLine();
        }
    }

    public void readUrine(Patient patient) {
        czyPoprawne = false;
        while (!czyPoprawne) {
            System.out.println("Podaj ilosc moczu:");
            try {
                patient.setUrine(input.nextDouble());
                czyPoprawne = true;
            } catch (InputMismatchException e) {
                LOGGER.error("Wrong input format", e);
            }
            input.nextLine();
        }
    }

    public void readIntake(Patient patient) {
        czyPoprawne = false;
        while (!czyPoprawne) {
            System.out.println("Podaj ilosc przyjetych plynow:");
            try {
                patient.setIntake(input.nextDouble());
                czyPoprawne = true;
            } catch (InputMismatchException e) {
                LOGGER.error("Wrong input format", e);
            }
            input.nextLine();
        }
    }

    public void readStoolType(Patient patient) {
        czyPoprawne = false;
        while (!czyPoprawne) {
            System.out.println("Jaki typ stolca wystapil? 1. Normalny 2. Biegunka");
            option = input.nextLine();
            try {
                if (option.equalsIgnoreCase("1")) {
                    patient.setStoolType(1);
                    System.out.println("Podaj ilosc stolca w ml");
                    try {
                        patient.setStool(input.nextDouble());
                        czyPoprawne = true;
                    } catch (InputMismatchException e) {
                        LOGGER.error("Wrong input format", e);
                    }finally{
                        input.nextLine();
                    }
                } else if (option.equalsIgnoreCase("2")) {
                    patient.setStoolType(2);
                    System.out.println("Podaj ilosc stolca w ml");
                    try {
                        patient.setStool(input.nextDouble());
                        czyPoprawne = true;
                    } catch (InputMismatchException e) {
                        LOGGER.error("Wrong input format", e);
                    }finally{
                        input.nextLine();
                    }
                } else
                    LOGGER.error("Number out of bounds");
            } catch (InputMismatchException e) {
                LOGGER.error("Wrong input format", e);
            }
        }

    }

    public void readIsVomit(Patient patient) {
        czyPoprawne = false;
        while (!czyPoprawne) {
            System.out.println("Czy wystapily wymioty? T/N:");
            option = input.nextLine();
            if (option.equalsIgnoreCase("t")) {
                System.out.println("Podaj ilosc wymiotow:");
                try {
                    patient.setVomit(input.nextDouble());
                    czyPoprawne = true;
                } catch (InputMismatchException e) {
                    LOGGER.error("Wrong input format", e);
                }
            } else if (option.equalsIgnoreCase("n")) {
                patient.setVomit(0);
                czyPoprawne = true;
            } else
                LOGGER.error("Number out of bounds");
        }
    }

    public void readOtherSources(Patient patient) {
        czyPoprawne = false;
        while (!czyPoprawne) {
            System.out.println("Jesli wystapily inne zrodla utraty plynow, podaj ilosc wydalonego plynu w ml:");
            try {
                patient.setOtherSource(input.nextDouble());
                czyPoprawne = true;
            } catch (InputMismatchException e) {
                LOGGER.error("Wrong input format", e);
            }
            input.nextLine();
        }
    }

    public void backToMenu() {
        czyPoprawne = false;
        while (!czyPoprawne) {
            System.out.println("Czy chcesz wrocic do glownego menu? T/N");
            option = input.nextLine();
            if (option.equalsIgnoreCase("t")) {
                menu = true;
                czyPoprawne = true;
            } else if (option.equalsIgnoreCase("n")) {
                menu = false;
                czyPoprawne = true;
            } else
                LOGGER.error("Wrong input");
        }
    }

    public void addNextPatient() {
        czyPoprawne = false;
        while (!czyPoprawne) {
            System.out.println("Czy chcesz dodac kolejnego pacjenta? T/N");
            option = input.nextLine();
            if (option.equalsIgnoreCase("t")) {
                next = true;
                czyPoprawne = true;
            } else if (option.equalsIgnoreCase("n")) {
                next = false;
                czyPoprawne = true;
            } else {
                LOGGER.error("Wrong input");
            }
        }
    }

    public int choosePatient(ArrayList<Patient> patients){
        czyPoprawne = false;
        int j=1000000;
        System.out.println("Ktorego pacjenta chcesz wybrac?");
        while(!czyPoprawne) {
            for (int i = 0; i < patients.size(); i++) {
                System.out.println(i + ". " + patients.get(i));
            }
            try {
                j = input.nextInt();
                if(j<= patients.size())
                    czyPoprawne = true;
                else
                    LOGGER.error("Array index out of bounds");
            }catch(InputMismatchException e){
                LOGGER.error("Wrong input format", e);
            }
            input.nextLine();
        }
        return j;
    }
    public void removePatient(ArrayList<Patient> patients){
        czyPoprawne = false;
        System.out.println("Ktorego pacjenta chcesz usunac?");
        while(!czyPoprawne) {
            for (int i = 0; i < patients.size(); i++) {
                System.out.println(i + ". " + patients.get(i));
            }
            try {
                patients.remove(input.nextInt());
                czyPoprawne = true;
            } catch (IndexOutOfBoundsException e) {
                LOGGER.error("Array index out of bounds", e);
            }catch(InputMismatchException g){
                LOGGER.error("Wrong input format", g);
            }
            input.nextLine();
        }
    }
    public void chooseParameter(int choice, ArrayList<Patient> patients){
        czyPoprawne = false;
        while(!czyPoprawne) {
            System.out.println("Ktory parametr chcesz zaktualizowac?");
            System.out.println("1. Cisnienie 2. Temperatura 3. Poziom cukru");
            option = input.nextLine();
            if(option.equals("1")) {
                System.out.println("Podaj nowa wartosc");
                patients.get(choice).setBloodPressure(input.nextLine());
                czyPoprawne = true;
            }
            else if(option.equals("2")) {
                System.out.println("Podaj nowa wartosc");
                try {
                    patients.get(choice).setTemperature(input.nextDouble());
                    czyPoprawne = true;
                }catch(InputMismatchException e){
                    LOGGER.error("Wrong input format", e);
                }
            }
            else if(option.equals("3")) {
                System.out.println("Podaj nowa wartosc");
                try {
                    patients.get(choice).setGlucoseLevel(input.nextDouble());
                    czyPoprawne = true;
                }catch(InputMismatchException e){
                    LOGGER.error("Wrong input format", e);
                }
            }
            else
                LOGGER.error("Wrong input format/Array index out of bounds");
            input.nextLine();
        }
    }
    public void inputClose(){
        input.close();
    }



    public boolean getNext() {
        return next;
    }

    public boolean getMenu() {
        return menu;
    }


    public void setNext(boolean next) {
        this.next = next;
    }

    public void setMenu(boolean menu) {
        this.menu = menu;
    }

}

