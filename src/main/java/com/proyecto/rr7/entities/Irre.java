package com.proyecto.rr7.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "IRRE")
@NamedQuery(name = "Irre.findAll", query = "SELECT i FROM Irre i")
@Getter
@Setter
public class Irre implements Serializable {

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
    private String nivelFondos;

    @Column(name = "CVE_RAMO")
    private String cveRamo;

    @Column(name = "OPERACION")
    private String operacion;

    @Column(name = "SALDO")
    private double saldo;

    @Column(name = "ANIOMES")
    private String anioMes;

}
