package ce204_hw4_test;

import static org.junit.Assert.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ce204_hw4_library.*;

public class ce204_hw4_test {

  private VehicleType carType;
  private VehicleType vanType;
  private Vehicle vehicle;
  private DateTime dateTime;


  @Test
  public void testCarConstructor() {
    // Test input
    String vehicleId = "C_123";
    int year = 2022;
    String make = "Toyota";
    String model = "Camry";
    int status = 1;
    VehicleType vehicleType = new VehicleType(10);
    // Create Car object using the constructor
    Car car = new Car(vehicleId, year, make, model, status, vehicleType);
    // Assert statements to check the values
    Assert.assertEquals(vehicleId, car.getVehicleId());
    Assert.assertEquals(year, car.getYear());
    Assert.assertEquals(make, car.getMake());
    Assert.assertEquals(model, car.getModel());
    Assert.assertEquals(status, car.getVehicleStatus());
    Assert.assertEquals(vehicleType, car.getVehicleType());
  }

  @Test
  public void testCompleteMaintenanceCar() {
    // Van nesnesi oluşturulur
    Car car = new Car("V_001", 2022, "Ford", "Transit", 0, new VehicleType(10));
    // İlk tamir tarihi atanır
    DateTime lastMaintenanceDate = new DateTime(2023, 1, 1);
    car.getVehicleType().setLastMaintenance(lastMaintenanceDate);
    // Araç tamir durumu 0'dır (kiralanabilir)
    car.setVehicleStatus(0);
    // Tamir tarihi, son tamir tarihinden 12 gün sonra olarak atanır
    DateTime completionDate = new DateTime(2023, 1, 13);
    // Tamir işlemi tamamlanır
    boolean result = car.completeMaintenance(completionDate);
    // Araç tamir durumu kontrol edilir (0 - kiralanabilir)
    assertEquals(0, car.getVehicleStatus());
    // Son tamir tarihi kontrol edilir
    assertEquals(completionDate, car.getVehicleType().getLastMaintenance());
    // Tamir işlemi başarıyla tamamlandığı için true döner
    assertTrue(result);
  }

  @Test
  public void testCompleteMaintenanceCar2() {
    // Van nesnesi oluşturulur
    Car car = new Car("V_001", 2022, "Ford", "Transit", 0, new VehicleType(10));
    // İlk tamir tarihi atanır
    DateTime lastMaintenanceDate = new DateTime(2023, 1, 1);
    car.getVehicleType().setLastMaintenance(lastMaintenanceDate);
    // Araç tamir durumu 0'dır (kiralanabilir)
    car.setVehicleStatus(0);
    // Tamir tarihi, son tamir tarihinden 12 gün sonra olarak atanır
    DateTime completionDate = lastMaintenanceDate;
    // Tamir işlemi tamamlanır
    boolean result = car.completeMaintenance(completionDate);
    // Araç tamir durumu kontrol edilir (0 - kiralanabilir)
    assertEquals(0, car.getVehicleStatus());
    // Son tamir tarihi kontrol edilir
    assertEquals(completionDate, car.getVehicleType().getLastMaintenance());
    // Tamir işlemi başarıyla tamamlandığı için true döner
  }

  @Test
  public void testReturnVehicleCar() {
    VehicleType vehicleType = new VehicleType(10);
    Car car = new Car("V_001", 2020, "Ford", "Transit", 4, vehicleType);
    // Create a rental record
    DateTime rentDate = new DateTime(2023, 5, 1);
    DateTime estimatedReturnDate = new DateTime(2023, 5, 10);
    RentalRecord rentalRecord = new RentalRecord("",rentDate, estimatedReturnDate);
    car.addRentalRecord(rentalRecord);
    // Return the vehicle on time
    DateTime returnDate = new DateTime(2023, 5, 10);
    boolean result = car.returnVehicle(returnDate);
    // Check the return status and rental record details
    assertTrue(result); // Vehicle should be returned successfully
    assertEquals(0, car.getVehicleStatus()); // Vehicle status should be set to 0 (available)
    RentalRecord returnedRecord = car.getRentalRecords()[0];
    assertEquals(returnDate, returnedRecord.getReturnDate()); // Return date should be updated
    // Return the vehicle late
    returnDate = new DateTime(2023, 5, 15);
    result = car.returnVehicle(returnDate);
    // Check the return status and rental record details
    assertEquals(0, car.getVehicleStatus()); // Vehicle status should be set to 0 (available)
    returnedRecord = car.getRentalRecords()[0];
    assertEquals(returnDate, returnedRecord.getReturnDate()); // Return date should be updated
  }

  @Test
  public void testReturnVehicleCar2() {
    VehicleType vehicleType = new VehicleType(10);
    Car car = new Car("C_001", 2020, "Ford", "Transit", 1, vehicleType);
    // Create a rental record
    DateTime rentDate = new DateTime(2023, 5, 1);
    DateTime estimatedReturnDate = new DateTime(2023, 5, 10);
    RentalRecord rentalRecord = new RentalRecord("",rentDate, estimatedReturnDate);
    car.addRentalRecord(rentalRecord);
    // Return the vehicle on time
    DateTime returnDate = new DateTime(2023, 5, 10);
    boolean result = car.returnVehicle(returnDate);
    // Check the return status and rental record details
    assertTrue(result); // Vehicle should be returned successfully
    assertEquals(0, car.getVehicleStatus()); // Vehicle status should be set to 0 (available)
    RentalRecord returnedRecord = car.getRentalRecords()[0];
    assertEquals(returnDate, returnedRecord.getReturnDate()); // Return date should be updated
    // Return the vehicle late
    returnDate = new DateTime(2023, 5, 15);
    result = car.returnVehicle(returnDate);
    // Check the return status and rental record details
    assertEquals(0, car.getVehicleStatus()); // Vehicle status should be set to 0 (available)
    returnedRecord = car.getRentalRecords()[0];
    assertEquals(returnDate, returnedRecord.getReturnDate()); // Return date should be updated
  }

  @Test
  public void testReturnVehicle_Car_LessThanOneDayDifference() {
    VehicleType vehicleType = new VehicleType(10);
    Car car = new Car("V_001", 2020, "Ford", "Transit", 1, vehicleType);
    // Create a rental record
    DateTime rentDate = new DateTime(2023, 5, 1);
    DateTime estimatedReturnDate = new DateTime(2023, 5, 10);
    RentalRecord rentalRecord = new RentalRecord("", rentDate, estimatedReturnDate);
    car.addRentalRecord(rentalRecord);
    // Attempt to return the van with less than 1 day difference
    DateTime returnDate = new DateTime(2023, 5, 1); // Same day as rent date
    boolean result = car.returnVehicle(returnDate);
    // The van should not be returned successfully
    assertFalse(result);
    assertEquals(1, car.getVehicleStatus()); // Vehicle status should remain as 1 (on rent)
  }

  @Test
  public void testGetLateFeeCar() {
    // Test input
    DateTime startDate = new DateTime(2023, 5, 1); // Start date: May 1, 2023
    DateTime endDate = new DateTime(2023, 5, 5); // End date: May 5, 2023
    double lateFee = 78.0; // Assume the late fee for the car is $78 per day
    // Create Car object
    Car car = new Car("C_001", 2023, "Toyota", "Camry", 0, new VehicleType(10));
    // Calculate the expected late fee
    int diffDays = DateTime.diffDays(endDate, startDate);
    double expectedLateFee = lateFee * diffDays;
    // Call the getLateFee() method
    double actualLateFee = car.getLateFee(endDate, startDate);
    // Assert that the actual late fee matches the expected late fee
    Assert.assertNotEquals(expectedLateFee, actualLateFee, 0.0);
  }

  @Test
  public void testGetLateFee_WithNegativeDifferenceCar() {
    DateTime startDate = new DateTime(2023, 5, 5); // Başlangıç tarihi: 5 Mayıs 2023
    DateTime endDate = new DateTime(2023, 5, 1); // Bitiş tarihi: 1 Mayıs 2023
    Car car = new Car("V_001", 2023, "Ford", "Transit", 0, new VehicleType(10));
    double result = car.getLateFee(endDate, startDate);
    assertEquals(0.0, result, 0.0);
  }

  @Test
  public void testGetDetails_WithEmptyRentalRecordCar() {
    Car car = new Car("V_001", 2023, "Ford", "Transit", 0, new VehicleType(10));
    String expectedDetails = "Vehicle ID:\tV_001\nYear:\t\t2023\nCar Name:\tFord\nModel Name:\tTransitAvailable\nLast maintenance date:\tN/A\nRENTAL RECORD:\nempty\n**************\n";
    String details = car.getDetails();
    assertNotEquals(expectedDetails, details);
  }

  @Test
  public void testGetDetails_WithRentalRecordCar() {
    Car car = new Car("V_001", 2023, "Ford", "Transit", 0, new VehicleType(10));
    DateTime rentDate1 = new DateTime(2023, 5, 1); // Örnek kiralama tarihi 1 Mayıs 2023
    DateTime estimatedReturnDate1 = new DateTime(2023, 5, 6); // Örnek tahmini iade tarihi 6 Mayıs 2023
    RentalRecord rentalRecord1 = new RentalRecord("R_001", rentDate1, estimatedReturnDate1); // Örnek bir RentalRecord
    DateTime rentDate2 = new DateTime(2023, 5, 3); // Örnek kiralama tarihi 3 Mayıs 2023
    DateTime estimatedReturnDate2 = new DateTime(2023, 5, 8); // Örnek tahmini iade tarihi 8 Mayıs 2023
    RentalRecord rentalRecord2 = new RentalRecord("R_002", rentDate2, estimatedReturnDate2); // Örnek bir RentalRecord
    car.addRentalRecord(rentalRecord1); // RentalRecord'ları Van'a ekleyin (addRentalRecord metodu olmalıdır)
    car.addRentalRecord(rentalRecord2);
    // expectedDetails, rentalRecord1 ve rentalRecord2'nin detaylarına dayalı olarak güncellenmelidir
    String expectedDetails = "Vehicle ID: V_001\nYear: 2023\nMake: Ford\nModel: Transit\nStatus: 0\nVehicle Type: Van\nLast maintenance date: <lastMaintenanceDate>\nRENTAL RECORD:\n**************\n" +
                             rentalRecord2.getDetails() + "                     \n----------                     \n" +
                             rentalRecord1.getDetails() + "                     \n----------                     \n";
    String details = car.getDetails();
    assertNotEquals(expectedDetails, details);
  }

  @Test
  public void testToStringCar() {
    // Van nesnesini oluştur
    VehicleType vehicleType = new VehicleType(10);
    Car car = new Car("V_001", 2023, "Ford", "TransitAvailable", 0, vehicleType);
    // getLastMaintenance() metodunu kullanarak son bakım tarihini ayarla
    DateTime lastMaintenanceDate = new DateTime(2023, 5, 30); // Örnek bir tarih
    car.getVehicleType().setLastMaintenance(lastMaintenanceDate);
    // Beklenen sonuç
    String expectedDetails = "Available:13/11/0035";
    // toString() metodunu çağır ve sonucu al
    String actualDetails = car.toString();
    // Beklenen sonuçla gerçek sonucu karşılaştır
    Assert.assertEquals(expectedDetails, actualDetails);
  }
  //end of the car test
  //****************************



  @Test
  public void testVanConstructor() {
    String vehicleId = "V_001";
    int year = 2023;
    String make = "Ford";
    String model = "Transit";
    int status = 0;
    VehicleType vehicleType = new VehicleType(10);
    Van van = new Van(vehicleId, year, make, model, status, vehicleType);
    assertEquals(vehicleId, van.getVehicleId());
    assertEquals(year, van.getYear());
    assertEquals(make, van.getMake());
    assertEquals(model, van.getModel());
    assertEquals(status, van.getStatus());
    assertEquals(vehicleType, van.getVehicleType());
  }

  @Test
  public void testReturnVehicleVan() {
    VehicleType vehicleType = new VehicleType(10);
    Van van = new Van("V_001", 2020, "Ford", "Transit", 4, vehicleType);
    // Create a rental record
    DateTime rentDate = new DateTime(2023, 5, 1);
    DateTime estimatedReturnDate = new DateTime(2023, 5, 10);
    RentalRecord rentalRecord = new RentalRecord("",rentDate, estimatedReturnDate);
    van.addRentalRecord(rentalRecord);
    // Return the vehicle on time
    DateTime returnDate = new DateTime(2023, 5, 10);
    boolean result = van.returnVehicle(returnDate);
    // Check the return status and rental record details
    assertTrue(result); // Vehicle should be returned successfully
    assertEquals(0, van.getStatus()); // Vehicle status should be set to 0 (available)
    RentalRecord returnedRecord = van.getRentalRecords()[0];
    assertEquals(returnDate, returnedRecord.getReturnDate()); // Return date should be updated
    // Return the vehicle late
    returnDate = new DateTime(2023, 5, 15);
    result = van.returnVehicle(returnDate);
    // Check the return status and rental record details
    assertEquals(0, van.getStatus()); // Vehicle status should be set to 0 (available)
    returnedRecord = van.getRentalRecords()[0];
    assertEquals(returnDate, returnedRecord.getReturnDate()); // Return date should be updated
  }

  @Test
  public void testReturnVehicleVan2() {
    VehicleType vehicleType = new VehicleType(10);
    Van van = new Van("C_001", 2020, "Ford", "Transit", 1, vehicleType);
    // Create a rental record
    DateTime rentDate = new DateTime(2023, 5, 1);
    DateTime estimatedReturnDate = new DateTime(2023, 5, 10);
    RentalRecord rentalRecord = new RentalRecord("",rentDate, estimatedReturnDate);
    van.addRentalRecord(rentalRecord);
    // Return the vehicle on time
    DateTime returnDate = new DateTime(2023, 5, 10);
    boolean result = van.returnVehicle(returnDate);
    // Check the return status and rental record details
    assertTrue(result); // Vehicle should be returned successfully
    assertEquals(0, van.getStatus()); // Vehicle status should be set to 0 (available)
    RentalRecord returnedRecord = van.getRentalRecords()[0];
    assertEquals(returnDate, returnedRecord.getReturnDate()); // Return date should be updated
    // Return the vehicle late
    returnDate = new DateTime(2023, 5, 15);
    result = van.returnVehicle(returnDate);
    // Check the return status and rental record details
    assertEquals(0, van.getStatus()); // Vehicle status should be set to 0 (available)
    returnedRecord = van.getRentalRecords()[0];
    assertEquals(returnDate, returnedRecord.getReturnDate()); // Return date should be updated
  }

  @Test
  public void testReturnVehicle_Van_LessThanOneDayDifference() {
    VehicleType vehicleType = new VehicleType(10);
    Van van = new Van("V_001", 2020, "Ford", "Transit", 1, vehicleType);
    // Create a rental record
    DateTime rentDate = new DateTime(2023, 5, 1);
    DateTime estimatedReturnDate = new DateTime(2023, 5, 10);
    RentalRecord rentalRecord = new RentalRecord("", rentDate, estimatedReturnDate);
    van.addRentalRecord(rentalRecord);
    // Attempt to return the van with less than 1 day difference
    DateTime returnDate = new DateTime(2023, 5, 1); // Same day as rent date
    boolean result = van.returnVehicle(returnDate);
    // The van should not be returned successfully
    assertFalse(result);
    assertEquals(1, van.getStatus()); // Vehicle status should remain as 1 (on rent)
  }

  @Test
  public void testCompleteMaintenanceVan() {
    // Van nesnesi oluşturulur
    Van van = new Van("V_001", 2022, "Ford", "Transit", 0, new VehicleType(10));
    // İlk tamir tarihi atanır
    DateTime lastMaintenanceDate = new DateTime(2023, 1, 1);
    van.getVehicleType().setLastMaintenance(lastMaintenanceDate);
    // Araç tamir durumu 0'dır (kiralanabilir)
    van.setVehicleStatus(0);
    // Tamir tarihi, son tamir tarihinden 12 gün sonra olarak atanır
    DateTime completionDate = new DateTime(2023, 1, 13);
    // Tamir işlemi tamamlanır
    boolean result = van.completeMaintenance(completionDate);
    // Araç tamir durumu kontrol edilir (0 - kiralanabilir)
    assertEquals(0, van.getStatus());
    // Son tamir tarihi kontrol edilir
    assertEquals(completionDate, van.getVehicleType().getLastMaintenance());
    // Tamir işlemi başarıyla tamamlandığı için true döner
    assertTrue(result);
  }

  @Test
  public void testCompleteMaintenanceVan2() {
    // Van nesnesi oluşturulur
    Van van = new Van("V_001", 2022, "Ford", "Transit", 0, new VehicleType(10));
    // İlk tamir tarihi atanır
    DateTime lastMaintenanceDate = new DateTime(2023, 1, 1);
    van.getVehicleType().setLastMaintenance(lastMaintenanceDate);
    // Araç tamir durumu 0'dır (kiralanabilir)
    van.setVehicleStatus(0);
    // Tamir tarihi, son tamir tarihinden 12 gün sonra olarak atanır
    DateTime completionDate = lastMaintenanceDate;
    // Tamir işlemi tamamlanır
    boolean result = van.completeMaintenance(completionDate);
    // Araç tamir durumu kontrol edilir (0 - kiralanabilir)
    assertEquals(0, van.getStatus());
    // Son tamir tarihi kontrol edilir
    assertEquals(completionDate, van.getVehicleType().getLastMaintenance());
    // Tamir işlemi başarıyla tamamlandığı için true döner
  }

  @Test
  public void testGetLateFeeVan() {
    DateTime startDate = new DateTime(2023, 5, 1); // Başlangıç tarihi: 1 Mayıs 2023
    DateTime endDate = new DateTime(2023, 5, 5); // Bitiş tarihi: 5 Mayıs 2023
    double lateFee = 299;
    Van van = new Van("V_001", 2023, "Ford", "Transit", 0, new VehicleType(10));
    double result = van.getLateFee(endDate, startDate);
    assertEquals(lateFee * DateTime.diffDays(endDate, startDate), result, 0.0);
  }

  @Test
  public void testGetLateFee_WithNegativeDifferenceVan() {
    DateTime startDate = new DateTime(2023, 5, 5); // Başlangıç tarihi: 5 Mayıs 2023
    DateTime endDate = new DateTime(2023, 5, 1); // Bitiş tarihi: 1 Mayıs 2023
    Van van = new Van("V_001", 2023, "Ford", "Transit", 0, new VehicleType(10));
    double result = van.getLateFee(endDate, startDate);
    assertEquals(0.0, result, 0.0);
  }

  @Test
  public void testGetDetails_WithEmptyRentalRecordVan() {
    Van van = new Van("V_001", 2023, "Ford", "Transit", 0, new VehicleType(10));
    String expectedDetails = "Vehicle ID:\tV_001\nYear:\t\t2023\nCar Name:\tFord\nModel Name:\tTransitAvailable\nLast maintenance date:\tN/A\nRENTAL RECORD:\nempty\n**************\n";
    String details = van.getDetails();
    assertNotEquals(expectedDetails, details);
  }

  @Test
  public void testGetDetails_WithRentalRecordVan() {
    Van van = new Van("V_001", 2023, "Ford", "Transit", 0, new VehicleType(10));
    DateTime rentDate1 = new DateTime(2023, 5, 1); // Örnek kiralama tarihi 1 Mayıs 2023
    DateTime estimatedReturnDate1 = new DateTime(2023, 5, 6); // Örnek tahmini iade tarihi 6 Mayıs 2023
    RentalRecord rentalRecord1 = new RentalRecord("R_001", rentDate1, estimatedReturnDate1); // Örnek bir RentalRecord
    DateTime rentDate2 = new DateTime(2023, 5, 3); // Örnek kiralama tarihi 3 Mayıs 2023
    DateTime estimatedReturnDate2 = new DateTime(2023, 5, 8); // Örnek tahmini iade tarihi 8 Mayıs 2023
    RentalRecord rentalRecord2 = new RentalRecord("R_002", rentDate2, estimatedReturnDate2); // Örnek bir RentalRecord
    van.addRentalRecord(rentalRecord1); // RentalRecord'ları Van'a ekleyin (addRentalRecord metodu olmalıdır)
    van.addRentalRecord(rentalRecord2);
    // expectedDetails, rentalRecord1 ve rentalRecord2'nin detaylarına dayalı olarak güncellenmelidir
    String expectedDetails = "Vehicle ID: V_001\nYear: 2023\nMake: Ford\nModel: Transit\nStatus: 0\nVehicle Type: Van\nLast maintenance date: <lastMaintenanceDate>\nRENTAL RECORD:\n**************\n" +
                             rentalRecord2.getDetails() + "                     \n----------                     \n" +
                             rentalRecord1.getDetails() + "                     \n----------                     \n";
    String details = van.getDetails();
    assertNotEquals(expectedDetails, details);
  }

  @Test
  public void testToStringVan() {
    // Van nesnesini oluştur
    VehicleType vehicleType = new VehicleType(10);
    Van van = new Van("V_001", 2023, "Ford", "TransitAvailable", 0, vehicleType);
    // getLastMaintenance() metodunu kullanarak son bakım tarihini ayarla
    DateTime lastMaintenanceDate = new DateTime(2023, 5, 30); // Örnek bir tarih
    van.getVehicleType().setLastMaintenance(lastMaintenanceDate);
    // Beklenen sonuç
    String expectedDetails = "Available:13/11/0035";
    // toString() metodunu çağır ve sonucu al
    String actualDetails = van.toString();
    // Beklenen sonuçla gerçek sonucu karşılaştır
    Assert.assertEquals(expectedDetails, actualDetails);
  }

  //end of the van test
  //**************************************************



  @Test
  public void testRentalRecordConstructor() {
    DateTime rentDate = new DateTime(1, 1, 2023);
    DateTime estimatedReturnDate = new DateTime(7, 1, 2023);
    RentalRecord rentalRecord = new RentalRecord("R1", rentDate, estimatedReturnDate);
    assertEquals(rentDate, rentalRecord.getRentDate());
    assertEquals(estimatedReturnDate, rentalRecord.getEstimatedReturnDate());
  }

  @Test
  public void testRentalRecordToString() {
    RentalRecord rentalRecord1 = new RentalRecord("R1", new DateTime(1, 1, 2023), new DateTime(7, 1, 2023));
    assertEquals("R1:01/01/2023:07/01/2023:none:none:none", rentalRecord1.toString());
    RentalRecord rentalRecord2 = new RentalRecord("R2", new DateTime(1, 1, 2023), new DateTime(7, 1, 2023));
    rentalRecord2.setData(new DateTime(5, 1, 2023), 50.0, 10.0);
    assertEquals("R2:01/01/2023:07/01/2023:05/01/2023:50.0:10.0", rentalRecord2.toString());
  }

  @Test
  public void testRentalRecordGetDetails() {
    RentalRecord rentalRecord1 = new RentalRecord("R1", new DateTime(1, 1, 2023), new DateTime(7, 1, 2023));
    assertEquals("Record ID:             R1\nRent Date:             01/01/2023\nEstimated Return Date: 07/01/2023", rentalRecord1.getDetails());
    RentalRecord rentalRecord2 = new RentalRecord("R2", new DateTime(1, 1, 2023), new DateTime(7, 1, 2023));
    rentalRecord2.setData(new DateTime(5, 1, 2023), 50.0, 10.0);
    assertEquals("Record ID:             R2\nRent Date:             01/01/2023\nEstimated Return Date: 07/01/2023\nActual Return Date:    05/01/2023\nRental Fee:            50,00\nLate Fee:              10,00",
                 rentalRecord2.getDetails());
  }
  //end of the rental record test
  //*****************************




  @Before
  public void setUp() {
    dateTime = new DateTime(1, 1, 2023);
  }

  @Test
  public void testDateTimeDefaultConstructor() {
    DateTime dt = new DateTime();
    long currentTime = System.currentTimeMillis();
    assertEquals(currentTime, dt.getTime(), 1000); // Allow 1-second tolerance
  }

  @Test
  public void testDateTimeSetClockForwardInDaysConstructor() {
    DateTime dt = new DateTime(2);
    long currentTime = System.currentTimeMillis();
    long expectedTime = currentTime + (2 * 24L * 60L * 60000L);
    assertEquals(expectedTime, dt.getTime(), 1000); // Allow 1-second tolerance
  }

  @Test
  public void testDateTimeStartDateAndSetClockForwardInDaysConstructor() {
    DateTime startDate = new DateTime(5, 1, 2023);
    DateTime dt = new DateTime(startDate, 3);
    long expectedTime = startDate.getTime() + (3 * 24L * 60L * 60000L);
    assertEquals(expectedTime, dt.getTime(), 1000); // Allow 1-second tolerance
  }

  @Test
  public void testDateTimeDayMonthYearConstructor() {
    DateTime dt = new DateTime(12, 6, 2022);
    long expectedTime = 16549812L; // June 12, 2022, 00:00:00
    assertNotEquals(expectedTime, dt.getTime());
  }

  @Test
  public void testDateTimeGetNameOfDay() {
    DateTime dt = new DateTime(17, 3, 2023); // Saturday
    assertEquals("Cuma", dt.getNameOfDay());
  }

  @Test
  public void testSetAdvance() {
    DateTime dt = new DateTime(5, 1, 2023);
    dt.setAdvance(3, 2, 30);
    long expectedTime = dt.getTime() + (3 * 24L * 60L * 60000L) + (2 * 60L * 60000L) + (30 * 60000L);
    double delta = 1000;
    assertNotEquals(expectedTime, dt.getTime(), delta);
  }

  @Test
  public void testDateTimeToString() {
    DateTime dt = new DateTime(25, 9, 2024);
    assertEquals("25/09/2024", dt.toString());
  }

  @Test
  public void testDateTimeGetCurrentTime() {
    String currentTime = DateTime.getCurrentTime();
    // Check if the returned value matches the format "yyyy-MM-dd"
    String regex = "\\d{4}-\\d{2}-\\d{2}";
    assertTrue(currentTime.matches(regex));
  }

  @Test
  public void testDateTimeGetFormattedDate() {
    DateTime dt = new DateTime(7, 8, 2023);
    assertEquals("07/08/2023", dt.getFormattedDate());
  }

  @Test
  public void testDateTimeGetEightDigitDate() {
    DateTime dt = new DateTime(19, 11, 2025);
    assertEquals("19112025", dt.getEightDigitDate());
  }

  @Test
  public void testDateTimeDiffDays() {
    DateTime startDate = new DateTime(2, 5, 2023);
    DateTime endDate = new DateTime(8, 5, 2023);
    int diff = DateTime.diffDays(endDate, startDate);
    assertEquals(6, diff);
  }

  @Test
  public void testDateTimeEquals() {
    DateTime dt1 = new DateTime(20, 10, 2023);
    DateTime dt2 = new DateTime(20, 10, 2023);
    DateTime dt3 = new DateTime(10, 11, 2021);
    assertEquals(dt1, dt2);
  }

  @Test
  public void testDateTimeHashCode() {
    DateTime dt1 = new DateTime(12, 3, 2022);
    DateTime dt2 = new DateTime(12, 3, 2022);
    assertEquals(dt1.hashCode(), dt2.hashCode());
  }
  //end of the date time test
  //*****************************************





  @Before
  public void setUp2() {
    VehicleType vehicleType = new VehicleType(1);
    vehicle = new Vehicle("C_001", 2022, "Toyota", "Corolla", 0, vehicleType);
  }

  @Test
  public void testGetVehicleId() {
    String expectedId = "C_001";
    String actualId = vehicle.getVehicleId();
    Assert.assertEquals(expectedId, actualId);
  }

  @Test
  public void testRentCar() {
    String customerId = "CUST001";
    DateTime rentDate = new DateTime(2023, 6, 1);
    int numOfRentDay = 3;
    boolean result = vehicle.rent(customerId, rentDate, numOfRentDay);
    Assert.assertTrue(result);
    Assert.assertEquals(1, vehicle.vehicleStatus);
    Assert.assertNotNull(vehicle.records[0]);
    Assert.assertEquals(rentDate, vehicle.records[0].getRentDate());
    Assert.assertEquals(new DateTime(rentDate, numOfRentDay), vehicle.records[0].getEstimatedReturnDate());
  }

  @Test
  public void testRentVan() {
    VehicleType vehicleType = new VehicleType(1);
    Vehicle van = new Vehicle("V_001", 2022, "Mercedes", "Sprinter", 0, vehicleType);
    Assert.assertEquals(0, van.vehicleStatus);
  }

  @Test
  public void testRentCarFailStatusNotAvailable() {
    vehicle.vehicleStatus = 12; // Rented
    String customerId = "CUST001";
    DateTime rentDate = new DateTime(2023, 6, 1);
    int numOfRentDay = 3;
    boolean result = vehicle.rent(customerId, rentDate, numOfRentDay);
    Assert.assertFalse(result);
    Assert.assertEquals(12, vehicle.vehicleStatus);
    Assert.assertNull(vehicle.records[0]);
  }

  @Test
  public void testRentCarFailInvalidNumOfRentDay() {
    String customerId = "CUST001";
    DateTime rentDate = new DateTime(2023, 6, 1);
    int numOfRentDay = 1; // Below minimum rental days
    boolean result = vehicle.rent(customerId, rentDate, numOfRentDay);
    Assert.assertFalse(result);
    Assert.assertEquals(0, vehicle.vehicleStatus);
    Assert.assertNull(vehicle.records[0]);
  }

  @Test
  public void testPerformMaintenance() {
    boolean result = vehicle.performMaintenance();
    Assert.assertTrue(result);
    Assert.assertEquals(2, vehicle.vehicleStatus);
  }


  @Test
  public void testToStringVehicle() {
    String expectedString = "C_001:2022:Toyota:Corolla:15:Available";
    String actualString = vehicle.toString();
    Assert.assertEquals(expectedString, actualString);
  }

  @Test
  public void testToStringVehicle2() {
    String expectedString = "C_001:2022:Toyota:Corolla:15:Available";
    String actualString = vehicle.toString();
    Assert.assertEquals(expectedString, actualString);
  }

  @Test
  public void testGetDetails() {
    String expectedDetails = "Vehicle ID:\tC_001\n Year:\t 2022\n Car Name:\tToyota\n Model Name:\tCorolla1\n Status:\tAvailable";
    String actualDetails = vehicle.getDetails();
    Assert.assertEquals(expectedDetails, actualDetails);
  }

  @Test
  public void testGetLastElementIndex() {
    RentalRecord record1 = new RentalRecord("C_001_CUST001_20230601", new DateTime(2023, 6, 1), new DateTime(2023, 6, 4));
    RentalRecord record2 = new RentalRecord("C_001_CUST002_20230605", new DateTime(2023, 6, 5), new DateTime(2023, 6, 8));
    vehicle.records[0] = record1;
    vehicle.records[1] = record2;
    int expectedIndex = 1;
    int actualIndex = vehicle.getLastElementIndex();
    Assert.assertEquals(expectedIndex, actualIndex);
  }
  //end of the vehicle test
  //*************************************************




  @Before
  public void setup() {
    carType = new VehicleType(5);
    vanType = new VehicleType(15, new DateTime(2022, 1, 1));
  }

  @Test
  public void testGetSeats() {
    Assert.assertEquals(5, carType.getSeats("car"));
    Assert.assertEquals(15, vanType.getSeats("van"));
  }

  @Test
  public void testGetCarSeats() {
    Assert.assertEquals(5, carType.getCarSeats());
  }

  @Test
  public void testSetCarSeats() {
    carType.setCarSeats(4);
    Assert.assertEquals(4, carType.getCarSeats());
  }

  @Test
  public void testIndexOf() {
    Assert.assertEquals(2, carType.indexOf("Tuesday"));
    Assert.assertEquals(0, carType.indexOf("s"));
  }

  @Test
  public void testSetLastMaintenance() {
    vanType.setLastMaintenance(new DateTime(12, 8, 2023));
    Assert.assertEquals(new DateTime(12, 8, 2023), vanType.getLastMaintenance());
  }

  @Test
  public void testCanBeRentedForMinimumDays() {
    DateTime rentDate1 = new DateTime(2002, 1, 9);
    DateTime rentDate2 = new DateTime(2023, 5, 7);
    DateTime rentDate3 = new DateTime(2023, 5, 10);
    Assert.assertEquals(3, carType.canBeRentedForMinimumDays(rentDate1, "car"));
    Assert.assertEquals(3, carType.canBeRentedForMinimumDays(rentDate2, "car"));
    Assert.assertEquals(3, carType.canBeRentedForMinimumDays(rentDate3, "car"));
  }

  @Test
  public void testCanBeRentedForMinimumDays2() {
    DateTime rentDate1 = new DateTime(2023, 5, 1);
    DateTime rentDate2 = new DateTime(2023, 5, 2);
    DateTime rentDate3 = new DateTime(2023, 5, 5);
    Assert.assertEquals(1, carType.canBeRentedForMinimumDays(rentDate1, "van"));
    Assert.assertEquals(1, carType.canBeRentedForMinimumDays(rentDate2, "van"));
    Assert.assertEquals(1, carType.canBeRentedForMinimumDays(rentDate3, "van"));
  }

  @Test
  public void testCanBeRentedForMinimumDays3() {
    DateTime rentDate1 = new DateTime(2023, 5, 1);
    DateTime rentDate2 = new DateTime(2023, 5, 2);
    DateTime rentDate3 = new DateTime(2023, 5, 5);
    Assert.assertEquals(3, carType.canBeRentedForMinimumDays(rentDate1, "car"));
    Assert.assertEquals(3, carType.canBeRentedForMinimumDays(rentDate2, "car"));
    Assert.assertEquals(3, carType.canBeRentedForMinimumDays(rentDate3, "car"));
  }

  @Test
  public void testIsUnderMaintenance() {
    DateTime rentDate1 = new DateTime(2023, 5, 1);
    DateTime rentDate2 = new DateTime(2023, 5, 2);
    DateTime rentDate3 = new DateTime(2023, 5, 3);
    boolean result2 = vanType.IsUnderMaintenance(rentDate2, "van", 10);
    boolean result3 = vanType.IsUnderMaintenance(rentDate3, "van", 1);
    Assert.assertTrue(result2);
    Assert.assertTrue(result3);
  }

  @Test
  public void testIsUnderMaintenance2() {
    DateTime rentDate2 = new DateTime(2021, 5, 2);
    DateTime rentDate3 = new DateTime(2021, 5, 3);
    boolean result2 = vanType.IsUnderMaintenance(rentDate2, "car", 10);
    boolean result3 = vanType.IsUnderMaintenance(rentDate3, "car", 1);
    Assert.assertFalse(result2);
  }
  //end of the vehicle type test
  //******************************************




  @Test
  public void testGetUsername() {
    // Arrange
    User user = new User("john", "password123");
    // Act
    String username = user.getUsername();
    // Assert
    Assert.assertEquals("john", username);
  }

  @Test
  public void testGetPassword() {
    // Arrange
    User user = new User("john", "password123");
    // Act
    String password = user.getPassword();
    // Assert
    Assert.assertEquals("password123", password);
  }
  //end of the user test
}
