package com.example.freeforfun.freeforfun.persistence.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="users")
@NamedQueries({
        @NamedQuery(name=User.QUERY_FIND_BY_USERNAME_AND_PASSWORD, query = "SELECT u FROM User u " +
                "WHERE u.username = :username AND u.password = :password"),
        @NamedQuery(name=User.QUERY_FIND_BY_USERNAME, query = "SELECT u FROM User u " +
                "WHERE u.username = :username"),
        @NamedQuery(name=User.QUERY_DELETE_AFTER_USERNAME, query = "DELETE FROM User u " +
                "WHERE u.username = :username")
})
public class User implements Serializable {

    public static final String QUERY_FIND_BY_USERNAME_AND_PASSWORD = "findUserByUsernameAndPassword";
    public static final String QUERY_FIND_BY_USERNAME = "findUserByUsername";
    public static final String QUERY_DELETE_AFTER_USERNAME = "deleteUserByUsername";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_user")
    private Integer id;

    @Column(name="firstname")
    private String firstName;

    @Column(name="lastname")
    private String lastName;

    @Column(name="password")
    private String password;

    @Column(name="email")
    private String email;

    @Column(name="mobile_number")
    private String mobileNumber;

    @Column(name="username")
    private String username;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_roles")
    private Role role;

    public User() {
    }

    public User(String firstName, String lastName, String password, String email, String mobileNumber, String username, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.username = username;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", username='" + username + '\'' +
                ", role=" + role +
                '}';
    }
}
