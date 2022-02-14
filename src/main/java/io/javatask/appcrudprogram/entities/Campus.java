package io.javatask.appcrudprogram.entities;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name="campus")
@Data
public class Campus{

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="university")
    private String university;

    @Column(name = "campus_name")
    private String campusName;

    @Column(name="faculties",nullable = false)
    @OneToMany(mappedBy = "campus",fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<Faculty> faculties;

}
