package com.proyecto.rr7.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "FOPA")
@NamedQuery(name = "Fopa.findAll", query = "SELECT f FROM Fopa f")
@Getter
@Setter
public class Fopa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "NIVEL1")
    private String nivel1;

    @Column(name = "NIVEL2")
    private String nivel2;

    @Column(name = "NIVEL3")
    private String nivel3;

    @Column(name = "NIVEL4")
    private String nivel4;

    @Column(name = "MONEDA")
    private String moneda;

    @Column(name = "AFECTACION")
    private String afectacion;

    @Column(name = "NIVEL_FONDOS")
    private String nivelfondos;

    @Column(name = "SALDO")
    private double saldo;

    @Column(name = "ANIOMES")
    private String anioMes;

}
