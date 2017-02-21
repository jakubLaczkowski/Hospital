public abstract class PatientBase {
    private String name;
    private String pesel;
    private String bloodPressure;
    private double temperature;
    private double glucoseLevel;


    // GETTERS
    public String getName(){
        return name;
    }
    public String getBloodPressure(){
        return bloodPressure;
    }
    public double getTemperature(){
        return temperature;
    }
    public double getGlucoseLevel(){
        return glucoseLevel;
    }
    public String getPesel(){
        return pesel;
    }

    // SETTERS
    public void setName(String name) {
        this.name = name;
    }
    public void setBloodPressure(String bloodPressure) {
        this.bloodPressure = bloodPressure;
    }
    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }
    public void setGlucoseLevel(double glucoseLevel) {
        this.glucoseLevel = glucoseLevel;
    }
    public void setPesel(String pesel) {
        this.pesel = pesel;
    }



}
