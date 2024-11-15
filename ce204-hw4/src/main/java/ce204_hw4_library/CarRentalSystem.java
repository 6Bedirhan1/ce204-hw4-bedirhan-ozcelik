package ce204_hw4_library;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class  CarRentalSystem {
  //cars and vans are two collections used to store cars of type Cars and vans of type Vans respectively
  private Car cars[] = new Car[50];
  private Van vans[] = new Van[50];
  private static Scanner scanner = new Scanner(System.in);
  private List<User> users = new ArrayList<>();

  public static DateFormat format = new SimpleDateFormat("dd/MM/yyyy"); //Basic format expected from the User
  /**
   * @name loginPage
   * This the main class used to get details from the user and display the menu driven options
   * This class acts as the businedd layer in our applications
   **/
  public void loginPage() {
    System.out.println("--------WELCOME---------");
    System.out.println("Press 1 for Sign In");
    System.out.println("Press 2 for Sign Up");
    System.out.print("--> ");
    int choice = scanner.nextInt();
    scanner.nextLine(); // Consume the newline character

    switch (choice) {
      case 1:
        if (signIn()) {
          run();
        } else {
          loginPage();
        }

        break;

      case 2:
        signUp();
        loginPage();
        break;

      default:
        System.out.println("Invalid choice. Please try again.");
        loginPage();
        break;
    }
  }
  /**
   * @name signIn
   * @retval [\b boolean]
   * Used to sign
   **/
  private boolean signIn() {
    System.out.print("Enter Username: ");
    String username = scanner.nextLine();
    System.out.print("Enter Password: ");
    String password = scanner.nextLine();

    // Check username and password (dummy implementation)
    for (User user : users) {
      if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
        System.out.println("Sign in successful!");
        return true;
      }
    }

    System.out.println("Invalid username or password. Please try again.");
    return false;
  }
  /**
   * @name signUp
   * used to sign up
   **/
  private void signUp() {
    System.out.println("Sign Up");
    System.out.print("Enter Username: ");
    String username = scanner.nextLine();

    // Check if the username already exists
    for (User user : users) {
      if (user.getUsername().equals(username)) {
        System.out.println("Username already exists. Please choose a different username.");
        signUp();
        return;
      }
    }

    System.out.print("Enter Password: ");
    String password = scanner.nextLine();
    // Create a new user and add it to the list
    User newUser = new User(username, password);
    users.add(newUser);
    System.out.println("Sign up successful!");
  }

  /**
  * @name run
  * This the method called from main method
  * this contains the menudriven interface to communicate with the user
  **/
  public void run() {
    while (true) {
      System.out.println("\n**** CAR-RENT SYSTEM MENU ****\n");
      System.out.println("Add vehicle:            1");
      System.out.println("Rent vehicle:           2");
      System.out.println("Return vehicle:         3");
      System.out.println("Vehicle Maintenance:    4");
      System.out.println("Complete Maintenance:   5");
      System.out.println("Display All Vehicles:   6");
      System.out.println("Log Out:                7");
      System.out.println("Exit Program:           8");
      System.out.print("Enter your choice: ");
      Scanner sc= new Scanner(System.in);
      int choice = sc.nextInt();
      sc.nextLine();

      switch (choice) {
        case 1:
          this.add(sc); //Method used to add either cars or vans
          break;

        case 2:
          this.rent(sc); //Method used to rent either cars or vans
          break;

        case 3:
          this.returnCar(sc); //Method used to return a car after being rented
          break;

        case 4:
          this.vehicleMaintenance(sc); //Method used to set either car or van to maintenance
          break;

        case 5:
          this.completeMaintenance(sc);  //Method used to complete the maintenance
          break;

        case 6:
          this.getDetails();  //Method used to get the details of cars or vans if present
          break;

        case 7:
          loginPage();
          return;

        case 8:
          sc.close(); //Closing the scanner if 7 is selected by the user
          return;

        default:
          System.out.println("Please try again.");
          break;
      }
    }
  }

  /**
  * @name add
  * @param [in] scan [\b Scanner]
  * Used to add either cars or vans to the list
  **/
  private void add(Scanner scan) {
    int i=0;
    String vehicleID="";
    int seats=0;
    String maintenanceDate=null;
    System.out.print("Vehicle Type(Van or Car): ");
    String vehicleType = scan.nextLine();

    while (!(vehicleType.equalsIgnoreCase("car") ||vehicleType.equalsIgnoreCase("van"))) {
      System.out.print("Please enter either van or car: ");
      vehicleType = scan.nextLine();
    }

    System.out.print("Year: ");
    int year = Integer.parseInt(scan.nextLine());

    while (year<0 || year >3000) {
      System.out.println("Please enter a valid year");
      year=Integer.parseInt(scan.nextLine());
    }

    System.out.print("Car Name: ");
    String make = scan.nextLine();
    System.out.print("Model Name: ");
    String model = scan.nextLine();

    if(vehicleType.equals("car")|| vehicleType.equals("Car")) {
      System.out.print("Vehicle ID: C_");
      vehicleID = scan.nextLine();
      vehicleID="C_"+vehicleID;

      if(this.vans[0]!=null && vehicleID.contains("C_")) {
        for(i=0; this.vans[i]!=null; i++) {
          if ((this.vans[i].getVehicleId()).equals(vehicleID)) {
            System.out.println("ID Already used, Please add a new vehicle");
            return;
          }
        }
      }

      System.out.print("Last Maintenance (dd/mm/yyyy): ");
      maintenanceDate = scan.next();
      format.setLenient(false);

      while(maintenanceDate.trim().length() != ((SimpleDateFormat) format).toPattern().length()) {
        System.out.println("Please enter a valid date in the format dd/mm/yyyy: ");
        maintenanceDate = scan.nextLine();
      }

      String dateSplit[] = maintenanceDate.split("/");
      DateTime Lastmain= new DateTime(Integer.parseInt(dateSplit[0]),Integer.parseInt(dateSplit[1]),Integer.parseInt(dateSplit[2]));

      if(i<50) {
        Vehicle newVehicle= new Van(vehicleID,year,make,model,0,new VehicleType(seats,Lastmain));
        this.vans[i]= (Van) newVehicle;
        System.out.println(newVehicle.toString());
      }
    }

    if(vehicleType.equalsIgnoreCase("van")|| vehicleType.equals("Van") ) {
      System.out.print("Vehicle ID: V_");
      vehicleID = scan.nextLine();
      vehicleID="V_"+vehicleID;

      if(this.vans[0]!=null && vehicleID.contains("V_")) {
        for(i=0; this.vans[i]!=null; i++) {
          if ((this.vans[i].getVehicleId()).equals(vehicleID)) {
            System.out.println("ID Already used, Please add a new vehicle");
            return;
          }
        }
      }

      seats = 15;
      System.out.print("Last Maintenance (dd/mm/yyyy): ");
      maintenanceDate = scan.next();
      format.setLenient(false);

      while(maintenanceDate.trim().length() != ((SimpleDateFormat) format).toPattern().length()) {
        System.out.println("Please enter a valid date in the format dd/mm/yyyy: ");
        maintenanceDate = scan.nextLine();
      }

      String dateSplit[] = maintenanceDate.split("/");
      DateTime Lastmain= new DateTime(Integer.parseInt(dateSplit[0]),Integer.parseInt(dateSplit[1]),Integer.parseInt(dateSplit[2]));

      if(i<50) {
        Vehicle newVehicle= new Van(vehicleID,year,make,model,0,new VehicleType(seats,Lastmain));
        this.vans[i]= (Van) newVehicle;
        System.out.println(newVehicle.toString());
      }
    }
  }


  /**
  * @name rent
   * @param [in] sc [\b Scanner]
  * Used to rent either available car or available van
  **/
  private void rent(Scanner sc) {
    System.out.print("Vehicle id: ");
    String id = sc.nextLine();
    String type="";

    if(id.contains("C_") && this.vans[0]==null) {
      System.out.println("There are no cars currently at the moment.");
      return;
    }

    if(id.contains("V_") && this.vans[0]==null) {
      System.out.println("There are no vans currently at the moment.");
      return;
    }

    if(this.vans[0]!=null && id.contains("C_")) {
      boolean flag =false;

      for(int i=0; this.vans[i]!=null; i++) {
        if ((this.vans[i].getVehicleId()).equals(id)) {
          if(this.vans[i].vehicleStatus!=0) {
            System.out.println("The car with ID : "+id+" is already either rented or under maintenance, please choose another car.");
            return;
          }

          type="car";
          flag=true;
          break;
        }
      }

      if(!flag) {
        System.out.println("ID is incorrect, please try again!");
        return;
      }
    }

    if(this.vans[0]!=null && id.contains("V_")) {
      boolean flag =false;

      for(int i=0; this.vans[i]!=null; i++) {
        if ((this.vans[i].getVehicleId()).equals(id)) {
          if(this.vans[0].vehicleStatus!=0) {
            System.out.println("The van with ID : "+id+" is already either rented or under maintenance. \nPlease choose another van.");
            return;
          }

          type="van";
          flag=true;
          break;
        }
      }

      if(!flag) {
        System.out.println("Id is incorrect, please try again!");
        return;
      }
    }

    if(!(id.contains("V_") || id.contains("C_"))) {
      System.out.println("Please Enter a Valid ID either starting from 'V_' or 'C_'.");
      return;
    }

    System.out.print("Customer ID: ");
    String cusId = sc.next();
    System.out.print("Rent date( dd/mm/yyyy): ");
    String date = sc.next();
    format.setLenient(false);

    while(date.trim().length() != ((SimpleDateFormat) format).toPattern().length()) {
      System.out.println("Please enter a valid date in the format dd/mm/yyyy: ");
      date = sc.nextLine();
    }

    String dates[] = date.split("/");
    DateTime rentDate= new DateTime(Integer.parseInt(dates[0]),Integer.parseInt(dates[1]),Integer.parseInt(dates[2]));
    System.out.print("How many days?: ");
    int days= sc.nextInt();

    if(type.equals("car")) {
      for(int i=0; this.vans[0]!=null; i++) {
        if ((this.vans[i].getVehicleId()).equals(id)) {
          if(this.vans[i].rent(cusId,rentDate,days))
            break;
          else {
            System.out.println("Vehicle "+id+" could not be rented.");
            return;
          }
        }
      }

      System.out.println("Vehicle "+id+" is now rented by customer "+cusId);
    }

    if(type.equals("van")) {
      for(int i=0; this.vans[i]!=null; i++) {
        if ((this.vans[i].getVehicleId()).equals(id)) {
          if(this.vans[i].rent(cusId,rentDate,days))
            break;
          else {
            System.out.println("Vehicle "+id+" could not be rented");
            return;
          }
        }
      }

      System.out.println("Vehicle "+id+" is now rented by customer "+cusId);
    }
  }

  /**
   * @name returnCar
     * @param [in] sc [\b Scanner]
   * Used to return a rented car or van
   **/
  private void returnCar(Scanner sc) {
    System.out.print("VehicleId: ");
    String id = sc.next();

    if(this.vans[0]==null ) {
      System.out.println("There are no cars, please add cars.");
      return;
    }

    if(this.vans[0]==null ) {
      System.out.println("There are no vans, please add cars.");
      return;
    }

    if(this.vans[0]!=null && id.contains("C_")) {
      boolean flag =false;

      for(int i=0; this.cars[i]!=null; i++) {
        if ((this.cars[i].getVehicleId()).equals(id)) {
          System.out.print("Return date( dd/mm/yyyy): ");
          String date = sc.next();
          String dates[] = date.split("/");
          DateTime returnDate= new DateTime(Integer.parseInt(dates[0]),Integer.parseInt(dates[1]),Integer.parseInt(dates[2]));

          if(this.cars[i].returnVehicle(returnDate)) {
            System.out.println(this.cars[i].records[this.cars[i].getLastElementIndex()].getDetails());
          } else {
            System.out.println("Vehicle cannot be returned as it may have been never rented");
            return;
          }

          flag=true;
          break;
        }
      }

      if(!flag) {
        System.out.println("Id is incorrect");
        return;
      }
    }

    if(this.vans[0]!=null && id.contains("V_")) {
      boolean flag =false;

      for(int i=0; this.vans[i]!=null; i++) {
        if ((this.vans[i].getVehicleId()).equals(id)) {
          System.out.print("Return date( dd/mm/yyyy): ");
          String date = sc.next();
          String dates[] = date.split("/");
          DateTime returndate= new DateTime(Integer.parseInt(dates[0]),Integer.parseInt(dates[1]),Integer.parseInt(dates[2]));

          if(this.vans[i].returnVehicle(returndate)) {
            System.out.println(this.vans[i].records[this.vans[i].getLastElementIndex()].getDetails());
          } else {
            System.out.println("Vehicle cannot be returned");
            return;
          }

          flag=true;
          break;
        }
      }

      if(!flag) {
        System.out.println("Id is incorrect");
        return;
      }
    }
  }

  /**
   * @name vehicleMaintenance
     * @param [in] sc [\b Scanner]
   * Method used to set either car or van to maintenance
   **/

  private void vehicleMaintenance(Scanner sc) {
    System.out.print("Vehicle id: ");
    String id = sc.next();

    if(this.cars[0]==null ) {
      System.out.println("There are no cars, please add cars.");
      return;
    }

    if(this.vans[0]==null ) {
      System.out.println("There are no vans, please add cars.");
      return;
    }

    if(this.cars[0]!=null && id.contains("C_")) {
      boolean flag =false;

      for(int i=0; this.cars[i]!=null; i++) {
        if ((this.cars[i].getVehicleId()).equals(id)) {
          if(this.cars[i].performMaintenance())
            System.out.println("Vehicle "+id+" is now under maintenance");
          else {
            System.out.println("Vehicle "+id+" could not be sent for maintenance");
            return;
          }

          flag=true;
          break;
        }
      }

      if(!flag) {
        System.out.println("Id is incorrect");
        return;
      }
    }

    if(this.vans[0]!=null && id.contains("V_")) {
      boolean flag =false;

      for(int i=0; this.vans[i]!=null; i++) {
        if ((this.vans[i].getVehicleId()).equals(id)) {
          if(this.vans[i].performMaintenance())
            System.out.println("Vehicle "+id+" is now under maintenance");
          else {
            System.out.println("Vehicle "+id+" could not be sent for maintenance");
            return;
          }

          flag=true;
          break;
        }
      }

      if(!flag) {
        System.out.println("Id is incorrect");
        return;
      }
    }
  }

  /**
   * @name completeMaintenance
     * @param [in] sc [\b Scanner]
   * Method used to complete maintenance of either car or van
   **/
  private void completeMaintenance(Scanner sc) {
    System.out.print("Enter vehicle ID: ");
    String id = sc.next();

    if(this.cars[0]==null ) {
      System.out.println("There are no cars, please add cars.");
      return;
    }

    if(this.vans[0]==null ) {
      System.out.println("There are no vans, please add cars.");
      return;
    }

    if(this.cars[0]!=null && id.contains("C_")) {
      boolean flag =false;

      for(int i=0; this.cars[i]!=null; i++) {
        if ((this.cars[i].getVehicleId()).equals(id)) {
          System.out.print("Maintenance completion date (dd/mm/yyyy) :");
          String date = sc.next();
          String dates[] = date.split("/");
          DateTime maintenanceDate= new DateTime(Integer.parseInt(dates[0]),Integer.parseInt(dates[1]),Integer.parseInt(dates[2]));

          if(this.cars[i].completeMaintenance(maintenanceDate))
            System.out.println("Vehicle "+id+" has all maintenance completed and ready for rent");
          else {
            System.out.println("Vehicle "+id+" could not complete maintenance");
            return;
          }

          flag=true;
          break;
        }
      }

      if(!flag) {
        System.out.println("ID is incorrect, Please try again");
        return;
      }
    }

    if(this.vans[0]!=null && id.contains("V_")) {
      boolean flag =false;

      for(int i=0; this.vans[i]!=null; i++) {
        if ((this.vans[i].getVehicleId()).equals(id)) {
          System.out.print("Maintenance completion date (dd/mm/yyyy) :");
          String date = sc.next();
          String dates[] = date.split("/");
          DateTime maintenanceDate= new DateTime(Integer.parseInt(dates[0]),Integer.parseInt(dates[1]),Integer.parseInt(dates[2]));

          if(this.vans[i].completeMaintenance(maintenanceDate))
            System.out.println("Vehicle "+id+" has all maintenance completed and ready for rent");
          else {
            System.out.println("Vehicle "+id+" could not complete maintenance");
            return;
          }

          flag=true;
          break;
        }
      }

      if(!flag) {
        System.out.println("Id is incorrect");
        return;
      }
    }
  }

  /**
   * @name getDetails
   * Method used to get details of car or van with their rental history
   **/
  private void getDetails() {
    if(cars[0]!=null && vans[0]!=null) {
      System.out.println("There are no cars or vans to display, please enter some vehicles and try again");
      return;
    }

    if(cars[0]!=null) {
      System.out.println("***********Vehicle**********");

      for (int i = 0; this.cars[i] != null; i++)
        System.out.println(this.cars[i].getDetails());
    }

    if(vans[0]!=null) {
      System.out.println("***********Vehicle**********");

      for (int i = 0; this.vans[i] != null; i++)
        System.out.println(this.vans[i].getDetails());
    }
  }
}