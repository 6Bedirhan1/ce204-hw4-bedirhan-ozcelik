package ce204_hw4_library;


public class Car extends  Vehicle {

  private double rate=235;
  private double rentRate=78;
  private double lateFee;
  private int seats=0;
  private RentalRecord[] rentalRecords;
  private int rentalRecordCount;
  /**
   * @name Car
   * @param [in] VehicleId [\b String]
   * @param [in] Year [\b int]
   * @param [in] Make [\b String]
   * @param [in] Model [\b String]
   * @param [in] status [\b int]
   * @param [in] vehicleType [\b VehicleType]
   * Class to save all the car details
   * This class contains all the calculations and details of the car
   * It extends Vehicle so it has all the features of a vehicle
   **/
  public Car(String VehicleId,int Year,String Make,String Model,int status,VehicleType vehicleType) {
    super(VehicleId,Year,Make,Model,status,vehicleType);
    this.rentalRecords = new RentalRecord[10];
    this.rentalRecordCount = 0;
    seats=vehicleType.getCarSeats();
  }
  /**
   * @name getYear
   * @retval [\b int]
   * Used to add either cars or vans to the list
   **/
  public int getYear() {
    return this.Year;
  }
  /**
   * @name getMake
   * @retval [\b String]
   * Used to add either cars or vans to the list
   **/
  public String getMake() {
    return this.Make;
  }
  /**
   * @name getModel
   * @retval [\b String]
   * Used to add either cars or vans to the list
   **/
  public String getModel() {
    return this.Model;
  }
  /**
   * @name setVehicleStatus
   * @param [in] status [\b int]
   * Used to add either cars or vans to the list
   **/
  public void setVehicleStatus(int status) {
    this.vehicleStatus = status;
  }
  /**
   * @name getVehicleStatus
   * @retval [\b int]
   * Used to add either cars or vans to the list
   **/
  public int getVehicleStatus() {
    return this.vehicleStatus;
  }
  /**
   * @name getVehicleType
   * @retval [\b VehicleType]
   * Used to add either cars or vans to the list
   **/
  public VehicleType getVehicleType() {
    return this.vehicleType;
  }
  /**
   * @name addRentalRecord
   * @param [in] rentalRecord [\b RentalRecord]
   * Used to add either cars or vans to the list
   **/
  public void addRentalRecord(RentalRecord rentalRecord) {
    if (rentalRecordCount < rentalRecords.length) {
      rentalRecords[rentalRecordCount] = rentalRecord;
      rentalRecordCount++;
    }
  }
  /**
   * @name getRentalRecords
   * @param [in] rentalRecord [\b RentalRecord]
   * @retval [\b RentalRecord[]]
   * Used to add either cars or vans to the list
   **/
  public RentalRecord[] getRentalRecords() {
    return rentalRecords;
  }

  /**
   * @name getLateFee
   * @param [in] endDate [\b DateTime]
   * @param [in] startDate [\b DateTime]
   * @retval [\b double]
   * Used to add either cars or vans to the list
   **/
  public double getLateFee(DateTime endDate,DateTime startDate) {
    if(DateTime.diffDays(endDate,startDate)>0)
      return this.lateFee* DateTime.diffDays(endDate,startDate);
    else
      return this.lateFee=0.0;
  }

  /**
   * @name returnVehicle
   * @param [in] returnDate [\b DateTime]
   * @retval [\b boolean]
   * Used to add either cars or vans to the list
   **/
  public boolean returnVehicle(DateTime returnDate) {
    String vehicleType;

    if (this.getVehicleId().contains("C_"))
      vehicleType = "car";
    else
      vehicleType = "van";

    if (this.getVehicleStatus() != 0) {
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
   * sets the vehicle status to available after maintenance
   **/
  public boolean completeMaintenance(DateTime completionDate) {
    if(this.vehicleStatus!=2 && DateTime.diffDays(completionDate,this.vehicleType.getLastMaintenance())<12)
      return false;

    this.vehicleType.setLastMaintenance(completionDate);
    this.vehicleStatus=0;
    return true;
  }


  /**
   * @name toString
   * @retval [\b String]
   * Method used to get details of car with their rental history
   * Prints the rental record of car
   **/
  public String toString() {
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