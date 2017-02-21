public class Patient extends PatientBase{
    private boolean isVomit;
    private double urine;
    private double intake;
    private double otherSource;
    private int stoolType;
    private double stool;
    private double vomit;
    private double balance;

    public Patient(){
        urine = 0;
        intake = 0;
        otherSource = 0;
        stool = 0;
        vomit = 0;
        balance = -100000000000d;
    }

    // MATH
    public double waterFromVomit(double vomit){
        return vomit*MedicalConstants.PERCENTAGE_OF_WATER_IN_VOMIT;
    }
    public double waterFromTemperature(double temperature){
        return (temperature - MedicalConstants.DEFAULT_BODY_TEMPERATURE)*MedicalConstants.WATER_LOSS_AFTER_1C_TEMP_INCREASE;
    }
    public double waterFromStool(int stoolType){
        if(stoolType == 1)
            return stool*MedicalConstants.PERCENTAGE_OF_WATER_IN_NORMAL_STOOL;
        else
            return stool*MedicalConstants.PERCENTAGE_OF_WATER_IN_DIARRHOEA;
    }
    public double fluidBalance(){
        return intake - urine - waterFromVomit(vomit) - waterFromStool(stoolType) - otherSource - waterFromTemperature(getTemperature()) - MedicalConstants.CONSTANT_LOSS_OF_FLUIDS;
    }


    public double getIntake() {
        return intake;
    }
    public double getVomit() {
        return vomit;
    }
    public double getOtherSource() {
        return otherSource;
    }
    public double getUrine() {
        return urine;
    }
    public int getStoolType(){
        return stoolType;
    }
    public double getStool() {
        return stool;
    }
    public boolean getIsVomit() {
        return isVomit;
    }
    public double getBalance() { return balance; }


    public void setIntake(double intake) {
        this.intake = intake;
    }
    public void setVomit(double vomit) {
        this.vomit = vomit;
    }
    public void setUrine(double urine) {
        this.urine = urine;
    }
    public void setOtherSource(double otherSource) {
        this.otherSource = otherSource;
    }
    public void setStoolType(int stoolType) {
        this.stoolType = stoolType;
    }
    public void setStool(double stool) {
        this.stool = stool;
    }
    public void setIsVomit(boolean isVomit){
        this.isVomit = isVomit;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }


    public String toString(){
        StringBuilder sb = new StringBuilder(100);
        sb.append("Imie: ");
        sb.append(getName());
        sb.append(", Pesel: ");
        sb.append(getPesel());
        sb.append(", cisnienie: ");
        sb.append(getBloodPressure());
        sb.append(", temperatura: ");
        sb.append(getTemperature());
        sb.append(", poziom cukru: ");
        sb.append(getGlucoseLevel());
        if(getBalance() != -100000000000d) {
            sb.append(", bilans plynow: ");
            sb.append(getBalance());
        }
        return sb.toString();
    }
    public boolean equals(Object otherObject){
        if(this == otherObject)
            return true;
        if(otherObject == null)
            return false;
        if(!(otherObject instanceof Patient))
            return false;

        Patient other = (Patient) otherObject;
        return getPesel().equals(other.getPesel());
    }
    public int hashCode(){
        return 40*Integer.parseInt(getPesel());
    }
}


