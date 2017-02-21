import java.util.*;
import java.io.*;
public class Database {
    File bazaDanych = new File("C:\\Patient Database\\baza danych pacjentow.txt");
    PrintWriter output;
    BufferedReader input;

    public void createDataBase(ArrayList<Patient> patients) throws FileNotFoundException{
        output = new PrintWriter(bazaDanych);
        for(int i=0; i<patients.size(); i++){
            output.print(patients.get(i).getName() + " " + patients.get(i).getPesel() + " " + patients.get(i).getBloodPressure()
                    + " " + patients.get(i).getTemperature() + " " + patients.get(i).getGlucoseLevel());
            if(patients.get(i).getBalance() != -100000000000d)
                output.print(" " + patients.get(i).getBalance());
            output.println();
        }
        output.close();
    }
    public ArrayList<Patient> readDataBase()throws FileNotFoundException, IOException{
        input = new BufferedReader(new FileReader(bazaDanych));
        ArrayList<Patient> listaPacjentow = new ArrayList<Patient>();
        Patient pacjent;
        String line = null;
        String[] lineSplitted;
        while((line = input.readLine()) != null){
            pacjent = new Patient();
            lineSplitted = line.split(" ");
            pacjent.setName(lineSplitted[0]);
            pacjent.setPesel(lineSplitted[1]);
            pacjent.setBloodPressure(lineSplitted[2]);
            pacjent.setTemperature(Double.parseDouble(lineSplitted[3]));
            pacjent.setGlucoseLevel(Double.parseDouble(lineSplitted[4]));
            try {
                pacjent.setBalance(Double.parseDouble(lineSplitted[5]));
            }catch(ArrayIndexOutOfBoundsException e){};
            listaPacjentow.add(pacjent);
        }
        input.close();
        return listaPacjentow;
    }
    public ArrayList<Patient> tryReadDataBase(){
        ArrayList<Patient> listaPacjentow = new ArrayList<Patient>();
        try{
            listaPacjentow = readDataBase();
        }catch(FileNotFoundException e){
            System.out.println("Plik zrodlowy nie istnieje");
        }catch(IOException g){
            g.getMessage();
        }
        return listaPacjentow;
    }
    public void tryCreateDataBase(ArrayList<Patient> listaPacjentow){
        try{
            createDataBase(listaPacjentow);
        }catch(FileNotFoundException e){
            System.out.println("Plik zrodlowy nie istnieje, baza nie zostala utworzona");
        }
    }
    public void outputClose(){
        output.close();
    }

}