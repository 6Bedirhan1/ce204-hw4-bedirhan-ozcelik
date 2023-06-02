package ce204_hw4_library;

public class Vehicle {
  protected String Vehicle_id;
  protected int Year;
  protected String Make;
  protected String Model;
  public int vehicleStatus;
  protected VehicleType vehicleType;
  public RentalRecord records[]= new RentalRecord[10];

  // Constructor to accept the details of a vehicle
  /**
   * @name Vehicle
   * @param [in] VehicleId [\b String]
   * @param [in] Year [\b int]
   * @param [in] Make [\b String]
   * @param [in] Model [\b String]
   * @param [in] status [\b int]
   * @param [in] vehicleType [\b VehicleType]
   *the designation of motor vehicle types that include sedans, sport utility vehicles, and trucks, or different categories.
   **/
  public Vehicle(String VehicleId,int Year,String Make,String Model,int status,VehicleType vehicleType) {
    this.Vehicle_id=VehicleId;
    this.Year=Year;
    this.Make=Make;
    this.Model= Model;
    this.vehicleStatus=status;
    this.vehicleType=vehicleType;
  }

  /**
   * @name getVehicleId
   * @retval [\b String]
   * Method to get vehicle ID
   **/
  public String getVehicleId() {
    return this.Vehicle_id;
  }

  /**
   * @name rent
   * @param [in] customerId [\b String]
   * @param [in] rentDate [\b DateTime]
   * @param [in] numOfRentDay [\b int]
   * @retval [\b boolean]
   * Used to rent either available car or available van
   **/
  public boolean rent(String customerId, DateTime rentDate, int numOfRentDay) {
    String typeOfVehicle = "van";

    if(this.Vehicle_id.contains("C_"))
      typeOfVehicle="car";

    if(this.vehicleStatus!=0 || numOfRentDay<this.vehicleType.canBeRentedForMinimumDays(rentDate,typeOfVehicle) || numOfRentDay>=14||numOfRentDay<2) {
      return false;
    }
    //          else if( typeOfVehicle.equals("van") )
    //          {
    //        if(this.vehicleStatus!=0 || this.vehicleType.IsUnderMaintenance(rentDate,typeOfVehicle,numOfRentDay) ||  numOfRentDay==0)
    //              return false;
    //          else
    //        {
    //        String rentId= this.Vehicle_id+"_"+customerId+"_"+rentDate. getEightDigitDate();
    //              this.records[this.getLastElementIndex()+1]=new RentalRecord(rentId,rentDate,new DateTime(rentDate,numOfRentDay));
    //              this.vehicleStatus=1;
    //              return true;
    //        }
    //          }
    else {
      String rentId= this.Vehicle_id+"_"+customerId+"_"+rentDate. getEightDigitDate();
      this.records[this.getLastElementIndex()+1]=new RentalRecord(rentId,rentDate,new DateTime(rentDate,numOfRentDay));
      this.vehicleStatus=1;
      return true;
    }
  }
  /**
   * @name performMaintenance
   * @retval [\b boolean]
   * sets the vehicle status to available after maintenance
   **/
  public boolean performMaintenance() {
    //        if(this.vehicleStatus==1 || this.vehicleStatus==2)
    //            return false;
    this.vehicleStatus=2;
    return true;
  }
  /**
   * @name toString
   * @retval [\b String]
   * Method used to get details of vehicle
   **/
  public String toString() {
    String repository="";

    if(this.Vehicle_id.contains("C_")) {
      repository=this.Vehicle_id+":"+String.valueOf(this.Year)+":"+this.Make+":"+this.Model+":15"+":";
    }

    switch(this.vehicleStatus) {
      case 0:
        repository+="Available";
        break;
        //            case 1: repository+="Rented";
        //                break;
        //            case 2: repository+="Maintenance";
        //                break;
    }

    return repository;
  }

  /**
   * @name getDetails
   * @retval [\b String]
   * Method used to get details of car or van with their rental history
   **/
  public String getDetails() {
    String data = "Vehicle ID:\t"+this.Vehicle_id+"\n Year:\t "+String.valueOf(this.Year)+"\n Car Name:\t"+this.Make+"\n Model Name:\t"+this.Model;

    if(this.Vehicle_id.contains("C_"))
      data+=String.valueOf(this.vehicleType.getSeats("car"))+"\n Status:\t";

    switch(this.vehicleStatus) {
      case 0:
        data+="Available";
        break;
        //            case 1: data+="Rented";
        //                break;
        //            case 2: data+="Maintenance";
        //                break;
    }

    return data;
  }

  /**
   * @name getLastElementIndex
   * @retval [\b int]
   * Method used to get last element index
   **/
  public int getLastElementIndex() {
    int index=0;

    for(index=0; this.records[index]!=null; index++);

    return index-1;
  }
}