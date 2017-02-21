import java.util.*;

public class Menu {
    private Scanner input = new Scanner(System.in).useLocale(Locale.US);
    private boolean czyPoprawne = false;
    private boolean menu = false;
    private boolean next = false;
    String option;

    public String menuOptions(){
        System.out.println("-------------------------------------");
        System.out.println("1. Wyswietl liste pacjentow.\n2. Dodaj pacjenta do listy.\n3. Oblicz bilans plynow.\n" +
                "4. Usun pacjenta z bazy.\n5. Aktualizacja parametrow pacjenta.\n0. Wyjdz z programu.");
        System.out.println("-------------------------------------");
        option = input.nextLine();
        return option;
    }
    public void readName(Patient pacjent){
        System.out.println("Podaj imie:");
        pacjent.setName(input.nextLine());
    }
    public void readPesel(Patient pacjent){
        System.out.println("Podaj pesel:");
        pacjent.setPesel(input.nextLine());
    }
    public void readBloodPressure(Patient pacjent){
        System.out.println("Podaj cisnienie:");
        pacjent.setBloodPressure(input.nextLine());
    }
    public void readTemperature(Patient pacjent) {
        czyPoprawne = false;
        while (!czyPoprawne) {
            System.out.println("Podaj temperature:");
            try {
                pacjent.setTemperature(input.nextDouble());
                czyPoprawne = true;
            } catch (InputMismatchException e) {
                System.out.println("Podaj poprawna wartosc!");
            }
            input.nextLine();
        }
    }

    public void readGlucoseLevel(Patient pacjent) {
        czyPoprawne = false;
        while (!czyPoprawne) {
            System.out.println("Podaj poziom cukru:");
            try {
                pacjent.setGlucoseLevel(input.nextDouble());
                czyPoprawne = true;
            } catch (InputMismatchException e) {
                System.out.println("Podaj poprawna wartosc!");
            }
            input.nextLine();
        }
    }

    public void readUrine(Patient pacjent) {
        czyPoprawne = false;
        while (!czyPoprawne) {
            System.out.println("Podaj ilosc moczu:");
            try {
                pacjent.setUrine(input.nextDouble());
                czyPoprawne = true;
            } catch (InputMismatchException e) {
                System.out.println("Podaj poprawna wartosc!");
            }
            input.nextLine();
        }
    }

    public void readIntake(Patient pacjent) {
        czyPoprawne = false;
        while (!czyPoprawne) {
            System.out.println("Podaj ilosc przyjetych plynow:");
            try {
                pacjent.setIntake(input.nextDouble());
                czyPoprawne = true;
            } catch (InputMismatchException e) {
                System.out.println("Podaj poprawna wartosc!");
            }
            input.nextLine();
        }
    }

    public void readStoolType(Patient pacjent) {
        czyPoprawne = false;
        while (!czyPoprawne) {
            System.out.println("Jaki typ stolca wystapil? 1. Normalny 2. Biegunka");
            option = input.nextLine();
            try {
                if (option.equalsIgnoreCase("1")) {
                    pacjent.setStoolType(1);
                    System.out.println("Podaj ilosc stolca w ml");
                    try {
                        pacjent.setStool(input.nextDouble());
                        czyPoprawne = true;
                        input.nextLine();
                    } catch (InputMismatchException e) {
                        System.out.println("Podaj poprawna wartosc!");
                        input.nextLine();
                    }
                } else if (option.equalsIgnoreCase("2")) {
                    pacjent.setStoolType(2);
                    System.out.println("Podaj ilosc stolca w ml");
                    try {
                        pacjent.setStool(input.nextDouble());
                        input.nextLine();
                        czyPoprawne = true;
                    } catch (InputMismatchException e) {
                        System.out.println("Podaj poprawna wartosc!");
                        input.nextLine();
                    }
                } else
                    System.out.println("Podaj poprawna wartosc!");
            } catch (InputMismatchException e) {
                System.out.println("Podaj poprawna wartosc!");
            }
        }

    }

    public void readIsVomit(Patient pacjent) {
        czyPoprawne = false;
        while (!czyPoprawne) {
            System.out.println("Czy wystapily wymioty? T/N:");
            option = input.nextLine();
            if (option.equalsIgnoreCase("t")) {
                System.out.println("Podaj ilosc wymiotow:");
                try {
                    pacjent.setVomit(input.nextDouble());
                    czyPoprawne = true;
                } catch (InputMismatchException e) {
                    System.out.println("Podaj poprawna wartosc!");
                }
            } else if (option.equalsIgnoreCase("n")) {
                pacjent.setVomit(0);
                czyPoprawne = true;
            } else
                System.out.println("Podaj poprawna wartosc!");
        }
    }

    public void readOtherSources(Patient pacjent) {
        czyPoprawne = false;
        while (!czyPoprawne) {
            System.out.println("Jesli wystapily inne zrodla utraty plynow, podaj ilosc wydalonego plynu w ml:");
            try {
                pacjent.setOtherSource(input.nextDouble());
                czyPoprawne = true;
            } catch (InputMismatchException e) {
                System.out.println("Podaj poprawna wartosc!");
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
                System.out.println("Podaj T lub N");
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
                System.out.println("Podaj T lub N");
            }
        }
    }

    public int choosePatient(ArrayList<Patient> listaPacjentow){
        czyPoprawne = false;
        System.out.println("Ktorego pacjenta chcesz wybrac?");
        int j=1000000;
        while(!czyPoprawne) {
            for (int i = 0; i < listaPacjentow.size(); i++) {
                System.out.println(i + ". " + listaPacjentow.get(i));
            }
            try {
                j = input.nextInt();
                if(j<= listaPacjentow.size())
                    czyPoprawne = true;
                else
                    System.out.println("Podaj poprawny indeks!");
            }catch(InputMismatchException e){
                System.out.println("Podaj wartosc liczbowa!");
            }
            input.nextLine();
        }
        return j;
    }
    public void removePatient(ArrayList<Patient> listaPacjentow){
        czyPoprawne = false;
        System.out.println("Ktorego pacjenta chcesz usunac?");
        while(!czyPoprawne) {
            for (int i = 0; i < listaPacjentow.size(); i++) {
                System.out.println(i + ". " + listaPacjentow.get(i));
            }
            try {
                listaPacjentow.remove(input.nextInt());
                czyPoprawne = true;
            } catch (IndexOutOfBoundsException g) {
                System.out.println("Podaj poprawna wartosc indeksu!");
            }catch(Exception e){
                System.out.println("Wybrales zly indeks!");
            }
            input.nextLine();
        }
    }
    public void chooseParameter(int choice, ArrayList<Patient> listaPacjentow){
        czyPoprawne = false;
        while(!czyPoprawne) {
            System.out.println("Ktory parametr chcesz zaktualizowac?");
            System.out.println("1. Cisnienie 2. Temperatura 3. Poziom cukru");
            option = input.nextLine();
            if(option.equals("1")) {
                System.out.println("Podaj nowa wartosc");
                listaPacjentow.get(choice).setBloodPressure(input.nextLine());
                czyPoprawne = true;
            }
            else if(option.equals("2")) {
                System.out.println("Podaj nowa wartosc");
                try {
                    listaPacjentow.get(choice).setTemperature(input.nextDouble());
                    czyPoprawne = true;
                }catch(InputMismatchException e){
                    System.out.println("Podaj poprawna wartosc liczbowa!");
                }
            }
            else if(option.equals("3")) {
                System.out.println("Podaj nowa wartosc");
                try {
                    listaPacjentow.get(choice).setGlucoseLevel(input.nextDouble());
                    czyPoprawne = true;
                }catch(InputMismatchException e){
                    System.out.println("Podaj poprawna wartosc liczbowa!");
                }
            }
            else
                System.out.println("Podaj poprawna wartosc indeksu!");
            input.nextLine();
        }
    }
    public void inputClose(){
        input.close();
    }


    // GETTERS
    public boolean getNext() {
        return next;
    }

    public boolean getMenu() {
        return menu;
    }

    // SETTERS
    public void setNext(boolean next) {
        this.next = next;
    }

    public void setMenu(boolean menu) {
        this.menu = menu;
    }

}

