package com.continuity.continuity.model;


import javax.persistence.*;

@Entity
@Table(name = "crewmember")
public class Crewmember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

//    @Column(columnDefinition = "integer")
    private Integer morale;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMorale() {
        return morale;
    }

    public void setMorale(Integer morale) {
        this.morale = morale;
    }
}
