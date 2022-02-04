package com.comit.model;

import java.io.Serializable;
import java.lang.annotation.Inherited;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlID;

import lombok.Getter;
import lombok.Setter;


@Entity
public class Route implements Serializable{
    
private static final long serialVersionUID = 1L;

     @Id 
     @GeneratedValue(strategy = GenerationType.IDENTITY)
 
     private Long id;
     private String airline;
     private String departure;
     private String destination;
     private Boolean deleted = false;
    


	public Long getId() {
		return id;
	}
	public String getAirline() {
		return airline;
	}
	public void setAirline(String airline) {
		this.airline = airline;
	}
	public String getDeparture() {
		return departure;
	}
	public void setDeparture(String departure) {
		this.departure = departure;
	}
    public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public Boolean getDeleted() {
		return deleted;
	}
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

}
