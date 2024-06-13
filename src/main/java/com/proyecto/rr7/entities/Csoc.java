package com.proyecto.rr7.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "CSOC")
@NamedQuery(name = "Csoc.findAll", query = "SELECT c FROM Csoc c")
@Getter
@Setter
public class Csoc implements Serializable {

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

    @Column(name = "CLAVE_CAP_SOC")
    private String claveCapSoc;

    @Column(name = "SUB_CLAVE_CAP_SOC")
    private String subClaveCapSoc;

    @Column(name = "AFECTACION")
    private String afectacion;

    @Column(name = "NIVEL_FONDOS")
    private String nivelFondos;

    @Column(name = "SALDO")
    private double saldo;

}
