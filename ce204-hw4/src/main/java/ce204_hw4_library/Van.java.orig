package ce204_hw4_library;

public class Van extends Vehicle{

    private double rate=235;
    private double lateFee=299;
    private RentalRecord[] rentalRecords;
    private int rentalRecordCount;
    /**
     * @name Van
     * @param [in] vehicleId [\b String]
     * @param [in] year [\b int]
     * @param [in] make [\b String]
     * @param [in] model [\b String]
     * @param [in] status [\b int]
     * @param [in] vehicleType [\b VehicleType]
     * @retval [\b double]
     *  value-added network is a private network provider
     **/
    public Van(String vehicleId, int year, String make, String model, int status, VehicleType vehicleType) {
        super(vehicleId, year, make, model, status, vehicleType);
        this.rentalRecords = new RentalRecord[10];
        this.rentalRecordCount = 0;
    }
    /**
     * @name getVehicleId
     * @retval [\b String]
     * Specifies the tool id.
     **/
    public String getVehicleId(){
        return this.Vehicle_id;
    }
    /**
     * @name getYear
     * @retval [\b int]
     * Specifies the tool get year.
     **/
    public int getYear(){
        return this.Year;
    }
    /**
     * @name getMake
     * @retval [\b String]
     * Specifies the tool get make.
     **/
    public String getMake(){
        return this.Make;
    }
    /**
     * @name getModel
     * @retval [\b String]
     * Specifies the tool get model.
     **/
    public String getModel(){
        return this.Model;
    }
    /**
     * @name getStatus
     * @retval [\b int]
     * Specifies the tool get status.
     **/
    public int getStatus(){
        return this.vehicleStatus;
    }
    /**
     * @name getVehicleType
     * @retval [\b VehicleType]
     * Specifies the tool get vehicle type
     **/
    public VehicleType getVehicleType(){
        return this.vehicleType;
    }
    /**
     * @name setVehicleStatus
     * @param [in] status [\b int]
     * Specifies the tool add rental record
     **/
    public void setVehicleStatus(int status) {
        this.vehicleStatus = status;
    }
    /**
     * @name addRentalRecord
     * @param [in] rentalRecord [\b RentalRecord]
     * Specifies the tool add rental record
     **/
    public void addRentalRecord(RentalRecord rentalRecord) {
        if (rentalRecordCount < rentalRecords.length) {
            rentalRecords[rentalRecordCount] = rentalRecord;
            rentalRecordCount++;
        }
    }
    /**
     * @name getRentalRecords
     * @retval [\b RentalRecord[]]
     * Specifies the tool add rental record
     **/
    public RentalRecord[] getRentalRecords() {
        return rentalRecords;
    }

    /**
     * @name getLateFee
     * @param [in] endDate [\b DateTime]
     * @param [in] startDate [\b DateTime]
     * @retval [\b double]
     * Indicates the Late Fee
     **/
    public double getLateFee(DateTime endDate,DateTime startDate){
        if(DateTime.diffDays(endDate,startDate)>0)
            return this.lateFee* DateTime.diffDays(endDate,startDate);
        else
            return this.lateFee=0.0;
    }

    /**
     * @name returnVehicle
     * @param [in] returnDate [\b DateTime]
     * @retval [\b boolean]
     * returns the tool value
     **/
    public boolean returnVehicle(DateTime returnDate) {
        String vehicleType;
        if (this.getVehicleId().contains("C_"))
            vehicleType = "car";
        else
            vehicleType = "van";
        if (this.getStatus() != 0) {
            RentalRecord lastRentalRecord = this.getRentalRecords()[this.rentalRecordCount - 1];
            DateTime estimatedDate = lastRentalRecord.getEstimatedReturnDate();
            DateTime rentDate = lastRentalRecord.getRentDate();

            if (vehicleType.equals("van") && DateTime.diffDays(returnDate, rentDate) < 1) {
                return false;
            } else {
                double rent = this.rate * DateTime.diffDays(returnDate, rentDate);
                double lateFee = this.getLateFee(returnDate, estimatedDate);
                this.setVehicleStatus(0);
                return true;
            }
        } else {
            return false;
        }
    }

    /**
     * @name completeMaintenance
     * @param [in] completionDate [\b DateTime]
     * @retval [\b boolean]
     * Provides information about completed Maintenance
     **/
    public boolean completeMaintenance(DateTime completionDate)
    {
        if(this.vehicleStatus!=2 && DateTime.diffDays(completionDate,this.vehicleType.getLastMaintenance())<12)
            return false;
        this.vehicleType.setLastMaintenance(completionDate);
        this.vehicleStatus=0;
        return true;
    }

    /**
     * @name toString
     * @retval [\b String]
     * Method used to get details of van
     **/
    public String toString()
    {
        String details = super.toString();
        DateTime lastMaintenanceDate= this.vehicleType.getLastMaintenance();
        details += ":"+ lastMaintenanceDate.toString();
        return details;
    }

    /**
     * @name getDetails
     * @retval [\b String]
     * Method used to get details of van with their rental history
     * Prints the rental record of van
     **/
    public String getDetails() {
        String details = super.getDetails();
        details += "\nLast maintenance date: ";
//        if (this.vehicleType.getLastMaintenance() != null) {
//            details += this.vehicleType.getLastMaintenance().toString();
//        } else {
            details += "N/A"; // Varsayılan bir mesaj veya değer
        
        
        if (this.records[0] == null) {
            details += "\nRENTAL RECORD:\nempty\n**************\n";
//        } else {
//            details += "\nRENTAL RECORD:\n**************\n";
//            int count = 0;
//            for (int index = 0; this.records[index] != null; index++) {
//                count++;
//            }
//            for (int index = count - 1; index >= 0; index--) {
//                details += this.records[index].getDetails() + "                     \n";
//                for (int innerIndex = 0; innerIndex < 10; innerIndex++) {
//                    details += "-";
//                }
//                details += "                     \n";
//            }
        }       
        return details;
    }
}