/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patientModel;

/**
 *
 * @author USER
 */
public class user {
    
    private String firstName;
    private String lastName;
    private String age;
    private String email;
    private String patientType;
    private String message;
    private String gender;       
           
    
    public user(String firstName, String lastName, String age, String email, String patientType, String gender, String message){
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.patientType = patientType;
       this.gender = gender;
        this.message = message;
    
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getPatientType() {
        return patientType;
    }

    public String getGender() {
        return gender;
    }

    public String getMessage() {
        return message;
    }
    
    
}
