public class Patient extends PatientBase{
    private boolean isVomit;
    private double urine = 0;
    private double intake = 0;
    private double otherSource = 0;
    private int stoolType;
    private double stool = 0;
    private double vomit = 0;
    private double balance=-100000000000d;
    public static final int CONSTANT_LOSS = 800;

    // MATH
    public double waterFromVomit(double vomit){
        return vomit*0.95;
    }
    public double waterFromTemperature(double temperature){
        return (temperature - 36.6)*333;
    }
    public double waterFromStool(int stoolType){
        if(stoolType == 1)
            return stool*0.75;
        else
            return stool*0.95;
    }
    public double fluidBalance(){
        //   System.out.println(intake + "-" + urine + "-" + waterFromVomit(vomit) + "-" + waterFromStool(stoolType) + "-" + otherSource + "-" + waterFromTemperature(getTemperature()) + "-" + CONSTANT_LOSS);
        return intake - urine - waterFromVomit(vomit) - waterFromStool(stoolType) - otherSource - waterFromTemperature(getTemperature()) - CONSTANT_LOSS;
    }

    // GETTERS
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

    // SETTERS
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

    // OVERRIDES
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
        return 7*Integer.parseInt(getPesel());
    }
}


