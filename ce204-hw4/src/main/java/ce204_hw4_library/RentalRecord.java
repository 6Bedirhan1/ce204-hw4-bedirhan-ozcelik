package ce204_hw4_library;

public class RentalRecord extends DateTime {
  private String RentId;
  private DateTime RentDate;
  private DateTime returnDate;
  private DateTime EstimatedReturnDate;
  private DateTime ActualReturnDate;
  private Double RentalFee;
  private Double LateFee;
  /**
   * @name RentalRecord
   * @param [in] RentId [\b String]
   * @param [in] Rentdate [\b DateTime]
   * @param [in] EstimatedReturnDate [\b DateTime]
   * The use of car rental management system can standardize the management and operation of enterprises
   **/
  public RentalRecord(String RentId,DateTime Rentdate,DateTime EstimatedReturnDate) {
    this.RentId=RentId;
    this.RentDate=Rentdate;
    this.EstimatedReturnDate=EstimatedReturnDate;
  }
  /**
   * @name setData
   * @param [in] ActualDate [\b DateTime]
   * @param [in] RentalFee [\b Double]
   * @param [in] LateFee [\b Double]
   * Car rental management system is a simple and easy-to-use system for car rental companies
   **/
  public void setData(DateTime ActualDate,Double RentalFee,Double LateFee) {
    this.ActualReturnDate=ActualDate;
    this.RentalFee=RentalFee;
    this.LateFee=LateFee;
  }
  /**
   * @name getEstimatedReturnDate
   * @retval [\b DateTime]
   * Returns a hash code for the invoking object
   **/
  public DateTime getEstimatedReturnDate() {
    return this.EstimatedReturnDate;
  }
  /**
   * @name getRentDate
   * @retval [\b DateTime]
   * Sets the time and date as specified by time
   **/
  public DateTime getRentDate() {
    return this.RentDate;
  }
  /**
   * @name getReturnDate
   * @retval [\b DateTime]
   * Sets the time and date as specified by time
   **/
  public DateTime getReturnDate() {
    return returnDate;
  }
  /**
     * @name toString
     * @retval [\b String]
     * which represents an elapsed time in milliseconds from midnight
     **/
  public String toString() {
    if(this.ActualReturnDate==null && this.RentalFee==null && this.LateFee==null) {
      String data = this.RentId+":"+this.RentDate.toString()+":"+this.EstimatedReturnDate.toString()+":none:none:none";
      return data;
    } else {
      return this.RentId+":"+this.RentDate.toString()+":"+this.EstimatedReturnDate.toString()+":"+this.ActualReturnDate.toString()+":"+this.RentalFee.toString()+":"+this.LateFee.toString();
    }
  }
  /**
   * @name getDetails
   * @retval [\b String]
   * The String pattern will be used to format dates
   **/
  public String getDetails() {
    if(this.ActualReturnDate==null && this.RentalFee==null && this.LateFee==null) {
      String data =
        "Record ID:             " + this.RentId
        + "\nRent Date:             " + this.RentDate.toString()
        + "\nEstimated Return Date: " + this.EstimatedReturnDate.toString();
      return data;
    } else {
      return "";
    }
  }
}