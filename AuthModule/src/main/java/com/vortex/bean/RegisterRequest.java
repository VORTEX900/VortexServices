package com.vortex.bean;

import java.util.Date;

public class RegisterRequest {
	
	private String alias;
    private String password;
    private String name;
    private String surname;
    private int role;
    private Date birthDate;

    public String getAlias() { return alias; }
    public void setAlias(String alias) { this.alias = alias; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	public String getSurname() { return surname; }
	public void setSurname(String surname) { this.surname = surname; }
	public int getRole() { return role; }
	public void setRole(int role) { this.role = role; }
	public Date getBirthDate() { return birthDate; }
	public void setBirthDate(Date birthDate) { this.birthDate = birthDate; }

    
}
