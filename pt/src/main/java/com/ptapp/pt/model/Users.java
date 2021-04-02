package com.ptapp.pt.model;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


//@DynamicUpdate not working to be fixed

@Entity
@Table(name = "users")
@SecondaryTable(name = "users_login", pkJoinColumns = @PrimaryKeyJoinColumn(name = "user_name"))
@EntityListeners(AuditingEntityListener.class)
public class Users {

	private @Id String userName;
	@Column(nullable = false) 
	private @NotBlank String firstName;
	private @NotBlank String lastName;
	private @CreatedDate Date createdAt;
	private @LastModifiedDate Date last_modified_at;

	@Column(name = "password", table = "users_login")
	private @NotBlank String password;
	@Column(name = "logged_in", table = "users_login")
	private @NotBlank boolean loggedIn;

	public Users() {

	}

	public Users(@NotBlank String userName, @NotBlank String firstName, @NotBlank String lastName,
			@NotBlank String password) {

		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password =  password;
		this.loggedIn = false;

	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getLastModifiedAt() {
		return last_modified_at;
	}

	public void setLastModifiedAt(Date lastModifiedAt) {
		this.last_modified_at = lastModifiedAt;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (this == obj)
			return true;

		if (!(obj instanceof Users))
			return false;

		Users user = (Users) obj;
		return Objects.equals(userName, user.userName);
		

	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return Objects.hash(userName);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "User{username='" + userName + '\'' + ", firstName='" + firstName + '\'' + ", lastName=" + lastName
				+ ", password=" + password + ", isLoggedIn=" + loggedIn + '}';
	}

}
