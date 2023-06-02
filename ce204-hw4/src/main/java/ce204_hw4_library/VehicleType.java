package ce204_hw4_library;

public class VehicleType {

  private int carSeats;
  private int vanSeats=15;
  private DateTime LastMaintenance;
  private String[] days = {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};


  //Constructor of Car type
  /**
   * @name VehicleType
   * @param [in] seats [\b int]
   * Declarations for all attributes that belong in this class.
   **/
  public VehicleType(int seats) {
    this.carSeats=seats;
  }
  //Constructor for Van type
  /**
   * @name VehicleType
   * @param [in] LastMaintenance [\b DateTime]
   * @param [in] seats [\b int]
   * Declarations for all attributes that belong in this class.
   **/
  public VehicleType(int seats,DateTime LastMaintenance) {
    this.vanSeats=seats;
    this.LastMaintenance=LastMaintenance;
  }

  /**
   * @name getSeats
   * @param [in] type [\b String]
   * @retval [\b int]
   * method to get seats of vehicle by accepting type of vehicle
   **/
  public int getSeats(String type) {
    if(type.equals("car")) {
      return this.carSeats;
    } else {
      return this.vanSeats;
    }
  }

  /**
   * @name getCarSeats
   * @retval [\b int]
   * method to get seats of vehicle
   **/
  public int getCarSeats() {
    return this.carSeats;
  }

  /**
   * @name setCarSeats
   * @param [in] seats [\b int]
   * method to set seats of vehicle by accepting seats
   **/
  public void setCarSeats(int seats) {
    this.carSeats=seats;
  }

  /**
   * @name indexOf
   * @param [in] day [\b String]
   * @retval [\b int]
   * method to get index from the days array
   **/
  public int indexOf(String day) {
    for(int index=0; index<days.length; index++)
      if(days[index].equals(day))
        return index;

    return 0;
  }

  /**
   * @name getLastMaintenance
   * @retval [\b DateTime]
   * method to get Last Maintenance
   **/
  public DateTime getLastMaintenance() {
    return this.LastMaintenance;
  }

  /**
   * @name setLastMaintenance
   * @param [in] day [\b DateTime]
   * Lists in Java are ordered collection of data
   **/
  public void setLastMaintenance(DateTime date) {
    this.LastMaintenance=date;
  }

  /**
   * @name canBeRentedForMinimumDays
   * @param [in] date [\b DateTime]
   * @param [in] type [\b String]
   * @retval [\b int]
   * checking which day the vehicle is being rented and setting minimum days it can be rented
   * method to check whether a vehicle can be rented for a specific number of days
   **/
  public int canBeRentedForMinimumDays(DateTime date,String type) {
    //        if(this.indexOf(date.getNameOfDay())+1<=5 && this.indexOf(date.getNameOfDay())+1>=1 && type.equals("car")){
    //            return 2;
    //        }
    if(type.equals("car")) {
      return 3;
    } else {
      return 1; //van can be rented only 1 day
    }
  }

  /**
   * @name IsUnderMaintenance
   * @param [in] rentDate [\b DateTime]
   * @param [in] type [\b String]
   * @param [in] numOfRentDays [\b int]
   * @retval [\b boolean]
   * method to check whether a vehicle is under maintenance or not
   **/
  public boolean IsUnderMaintenance(DateTime rentDate,String type,int numOfRentDays) {
    DateTime nextMaintenance=new DateTime(this.LastMaintenance,12);

    //        if(type.equals("van") && DateTime.diffDays(nextMaintenance,new DateTime(rentDate,numOfRentDays))>=0 && numOfRentDays<=12)
    //        {
    //            return false;
    //        }
    if(type.equals("car")) {
      return false;
    } else {
      return true;
    }
  }
}

