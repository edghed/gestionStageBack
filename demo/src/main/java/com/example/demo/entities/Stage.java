package com.example.demo.entities;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Stage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    Date dateDebut;
    Date dateFin;

   

    public Stage() {}

    public Stage(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Long getId() {
        return id;
    }
public Date getDateDebut()
{return dateDebut;}

public void setDateDebut(Date dateDebut)
{this.dateDebut=dateDebut;}

public Date getDateFin(){
    return dateFin;
}

public void setDateFin(Date dateFin){
    this.dateFin=dateFin;
}


    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
