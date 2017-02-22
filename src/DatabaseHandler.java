import java.util.*;
import java.io.*;
import org.apache.log4j.*;
public class DatabaseHandler {
    private static final Logger LOGGER = Logger.getLogger(DatabaseHandler.class);
    private String dbPath;
    private File bazaDanych;
    private File logFile;
    private PrintWriter output;
    private BufferedReader input;

    public DatabaseHandler(String dbPath){
        this.dbPath = dbPath;
        bazaDanych = new File(dbPath);
    }
    public void createDataBase(ArrayList<Patient> patients) throws FileNotFoundException{
        StringBuilder sb;
        try {
            output = new PrintWriter(bazaDanych);
            for (int i = 0; i < patients.size(); i++) {
                sb = new StringBuilder();
                sb.append(patients.get(i).getName()).append(" ").append(patients.get(i).getPesel()).append(" ").append(patients.get(i).getBloodPressure())
                        .append(" ").append(patients.get(i).getTemperature()).append(" ").append(patients.get(i).getGlucoseLevel());
                if (patients.get(i).getBalance() != -100000000000d)
                    sb.append(" ").append(patients.get(i).getBalance());
                output.print(sb);
                output.println();
            }
        }catch(FileNotFoundException e) {
            LOGGER.error("File not found", e);
        }catch(ArrayIndexOutOfBoundsException g){
            LOGGER.error("Array index out of bounds", g);
        }finally{
            output.close();
        }
    }
    public ArrayList<Patient> readDataBase()throws FileNotFoundException, IOException{
        Patient patient;
        String line = null;
        String[] lineSplitted;
        ArrayList<Patient> patients = new ArrayList<Patient>();
        input = new BufferedReader(new FileReader(bazaDanych));
        while((line = input.readLine()) != null){
            patient = new Patient();
            lineSplitted = line.split(" ");
            patient.setName(lineSplitted[0]);
            patient.setPesel(lineSplitted[1]);
            patient.setBloodPressure(lineSplitted[2]);
            try {
                patient.setTemperature(Double.parseDouble(lineSplitted[3]));
                patient.setGlucoseLevel(Double.parseDouble(lineSplitted[4]));
            }catch(NumberFormatException g){
                LOGGER.error("Wrong number format", g);
            }
            try {
                patient.setBalance(Double.parseDouble(lineSplitted[5]));
            }catch(ArrayIndexOutOfBoundsException e){
                LOGGER.error("Array index out of bounds", e);
            }catch(NumberFormatException g){
                LOGGER.error("Wrong number format", g);
            }
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
            LOGGER.error("File not found", e);
        }catch(IOException g){
            LOGGER.error(g.getMessage(), g);
        }
        return patients;
    }
    public void tryCreateDataBase(ArrayList<Patient> patients){
        try{
            createDataBase(patients);
        }catch(FileNotFoundException e){
            LOGGER.error("File not found", e);
        }
    }
    public void outputClose(){
        output.close();
    }

}