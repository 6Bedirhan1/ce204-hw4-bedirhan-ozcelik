package ce204_hw4_library;

public class User {
  private String username;
  private String password;
  /**
   * @name User
   * @param [in] username [\b String]
   * @param [in] password [\b String]
   * @retval [\b double]
   * Select the file-based directory service ID from the Select Directory Service drop-down list and click Select
   **/
  public User(String username, String password) {
    this.username = username;
    this.password = password;
  }
  /**
   * @name getUsername
   * @retval [\b String]
   * Specifies a unique user name for the user
   **/
  public String getUsername() {
    return username;
  }
  /**
   * @name getPassword
   * @retval [\b String]
   * Specifies the password for the user
   **/
  public String getPassword() {
    return password;
  }
}