package data;

import javafx.beans.property.SimpleStringProperty;

import java.io.Serializable;

public class TelefonEntry implements Serializable {
  
  private final SimpleStringProperty lastName = new SimpleStringProperty();
  private final SimpleStringProperty  firstName = new SimpleStringProperty();
  private final SimpleStringProperty  number = new SimpleStringProperty();
  private boolean isNew = true;
  
  public TelefonEntry() {
    this.lastName.set("Max");
    this.firstName.set("Mustermann");
    this.number.set("112");
    isNew = true;
  }
  
  public TelefonEntry(String lastName, String firstName, String number) {
    this.lastName.set(lastName);
    this.firstName.set(firstName);
    this.number.set(number);
    isNew = false;
  }
  
  public String getLastName() {
    return lastName.get();
  }
  
  public void setLastName(String value) {
    lastName.set(value);
    isNew = false;
  }
  
  public String getFirstName() {
    return firstName.get();
  }
  
  public void setFirstName(String value) {
    firstName.set(value);
    isNew = false;
  }
  
  public String getNumber() {
    return number.get();
  }
  
  public void setNumber(String value) {
    number.set(value);
    isNew = false;
  }
  
  public boolean isNew() {
    return isNew;
  }

  @Override
  public String toString() {
      return getFirstName() + "\\" + getLastName() + "\\" + getNumber();

  }

}
