package com.example.aag.eval_01;

/**
 * Created by Herce on 22/02/2017.
 */

public class DatosUsuario {

  private String FirstName;
  private String LastName;
  private String SecondLastName;
  private String StatusAccount;
  private Boolean NoticePrivacyFlag;
  private Double Stature;
  private String Gender;
  private Long CompanyId;
  private String AssociateImage;
  private String AssociateId;
  private String nmComplete;
  private String username;
  private String password;

  public String getFirstName() {
    return FirstName;
  }

  public void setFirstName(String FirstName) {
    this.FirstName = FirstName;
  }

  public String getSecondLastName() {
    return SecondLastName;
  }

  public void setSecondLastName(String SecondLastName) {
    this.SecondLastName = SecondLastName;
  }

  public String getLastName() {
    return LastName;
  }

  public void setLastName(String LastName) {
    this.LastName = LastName;
  }

  public Long getCompanyId() {
    return CompanyId;
  }

  public void setCompanyId(Long CompanyId) {
    this.CompanyId = CompanyId;
  }

  public String getAssociateId() {
    return AssociateId;
  }

  public void setAssociateId(String AssociateId) {
    this.AssociateId = AssociateId;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.username = password;
  }

  private static final DatosUsuario holder = new DatosUsuario();

  public static DatosUsuario getInstance() {
    return holder;
  }
}