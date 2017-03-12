package com.minglefield.domain;

import com.minglefield.dto.RegisterUserCommand;

import javax.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "EMAIL_ADDRESS")
    private String emailAddress;

    // TODO encrypting
    private String password;

    public User() {
        // default constructor
    }

    public User(String firstName, String lastName, String emailAddress, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.password = password;
    }

    public static User createFromRegisterCommand(RegisterUserCommand registerUserCommand) {
        return new User(registerUserCommand.firstName, registerUserCommand.lastName,
                registerUserCommand.emailAddress,
                registerUserCommand.password);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return firstName + " " + lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    // TODO encrypting
    public String getPassword() {
        return password;
    }
}
