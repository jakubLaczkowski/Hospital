import java.util.*;
import java.io.*;
public class DatabaseHandler {
    private String dbPath;
    private File bazaDanych;
    private PrintWriter output;
    private BufferedReader input;

    public DatabaseHandler(String dbPath){
        this.dbPath = dbPath;
        bazaDanych = new File(dbPath);

    }
    public void createDataBase(ArrayList<Patient> patients) throws FileNotFoundException{
        StringBuilder sb;
        output = new PrintWriter(bazaDanych);
        try {
            for (int i = 0; i < patients.size(); i++) {
                sb = new StringBuilder();
                sb.append(patients.get(i).getName()).append(" ").append(patients.get(i).getPesel()).append(" ").append(patients.get(i).getBloodPressure())
                        .append(" ").append(patients.get(i).getTemperature()).append(" ").append(patients.get(i).getGlucoseLevel());
                if (patients.get(i).getBalance() != -100000000000d)
                    sb.append(" ").append(patients.get(i).getBalance());
                output.print(sb);
                output.println();
            }
        }catch(Exception e) {
        }finally{
            output.close();
        }
    }
    public ArrayList<Patient> readDataBase()throws FileNotFoundException, IOException{
        Patient patient;
        String line = null;
        String[] lineSplitted;
        input = new BufferedReader(new FileReader(bazaDanych));
        ArrayList<Patient> patients = new ArrayList<Patient>();
        while((line = input.readLine()) != null){
            patient = new Patient();
            lineSplitted = line.split(" ");
            patient.setName(lineSplitted[0]);
            patient.setPesel(lineSplitted[1]);
            patient.setBloodPressure(lineSplitted[2]);
            try {
                patient.setTemperature(Double.parseDouble(lineSplitted[3]));
                patient.setGlucoseLevel(Double.parseDouble(lineSplitted[4]));
            }catch(NumberFormatException g){};
            try {
                patient.setBalance(Double.parseDouble(lineSplitted[5]));
            }catch(ArrayIndexOutOfBoundsException e){
            }catch(NumberFormatException g){};
            patients.add(patient);
        }
        input.close();
        return patients;
    }
    public ArrayList<Patient> tryReadDataBase(){
        ArrayList<Patient> patients = new ArrayList<Patient>();
        try{
            patients = readDataBase();
        }catch(FileNotFoundException e){
            System.out.println("Plik zrodlowy nie istnieje");
        }catch(IOException g){
            g.getMessage();
        }
        return patients;
    }
    public void tryCreateDataBase(ArrayList<Patient> patients){
        try{
            createDataBase(patients);
        }catch(FileNotFoundException e){
            System.out.println("Plik zrodlowy nie istnieje, baza nie zostala utworzona");
        }
    }
    public void outputClose(){
        output.close();
    }

}