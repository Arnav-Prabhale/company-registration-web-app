package com.web;

public class CompanyEntry {
	
	
	private int id;
	private String name;
	private String registrationDate;
	private String addressLine1;
	private String addressLine2;
	private String country;
	private String state;
	public CompanyEntry(int id, String name, String registrationDate, String addressLine1, String addressLine2, String country,
			String state, String city) {
		super();
		this.id = id;
		this.name = name;
		this.registrationDate = registrationDate;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.country = country;
		this.state = state;
		this.city = city;
	}
	private String city;
	private String completeAddress;
	private String license;
	private String type;
	private String radio;
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public void setCompleteAddress(String completeAddress) {
		this.completeAddress = completeAddress;
	}
	private String branchAddress;
	private String countryName;
	
	
	public CompanyEntry(int id, String name, String registrationDate, String addressLine1, String addressLine2,
			String country, String state, String city, String license, String type, String radio,
			String branchAddress, String countryName) {
		super();
		this.id = id;
		this.name = name;
		this.registrationDate = registrationDate;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.country = country;
		this.state = state;
		this.city = city;
		this.license = license;
		this.type = type;
		this.radio = radio;
		this.branchAddress = branchAddress;
		this.completeAddress = getCompleteAddress();
		this.countryName = countryName;
	}
	public CompanyEntry(String name, String registrationDate, String addressLine1, String addressLine2, String country,
			String state, String city, String license, String type, String radio, String branchAddress, String countryName) {
		super();
		this.name = name;
		this.registrationDate = registrationDate;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.country = country;
		this.state = state;
		this.city = city;
		this.license = license;
		this.type = type;
		this.radio = radio;
		this.branchAddress = branchAddress;
		this.completeAddress = getCompleteAddress();
		this.countryName = countryName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}
	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	public String getAddressLine2() {
		return addressLine2;
	}
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getLicense() {
		return license;
	}
	public void setLicense(String license) {
		this.license = license;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getRadio() {
		return radio;
	}
	public void setRadio(String radio) {
		this.radio = radio;
	}
	public String getBranchAddress() {
		return branchAddress;
	}
	public void setBranchAddress(String branchAddress) {
		this.branchAddress = branchAddress;
	}
	@Override
	public String toString() {
		return "CompanyEntry [name=" + name + ", registrationDate=" + registrationDate + ", addressLine1="
				+ addressLine1 + ", addressLine2=" + addressLine2 + ", country=" + country + ", state=" + state
				+ ", city=" + city + ", license=" + license + ", type=" + type + ", radio=" + radio + ", branchAddress="
				+ branchAddress + "]";
	}
	
	public String getCompleteAddress() {
		setCompleteAddress();
		return completeAddress;
	}
	public void setCompleteAddress() {
		String completeAdd = "";
		if(addressLine1 != null)
		{
			completeAdd += addressLine1;
		}
		if(addressLine2 != null && addressLine2.length() > 1)
		{
			if(completeAdd.length() > 1)
			{
				completeAdd += ", " + addressLine2;
			}
			else
			{
				completeAdd += addressLine2;
			}
		}
		if(city != null)
		{
			if(completeAdd.length() > 1)
			{
				completeAdd += ", " + city;
			}
			else
			{
				completeAdd += city;
			}
		}
		if(state != null)
		{
			if(completeAdd.length() > 1)
			{
				completeAdd += ", " + state;
			}
			else
			{
				completeAdd += state;
			}
		}
		if(countryName != null)
		{
			if(completeAdd.length() > 1)
			{
				completeAdd += ", " + countryName;
			}
			else
			{
				completeAdd += countryName;
			}
		}
		
		
		this.completeAddress = completeAdd;
	}
	
	

}
