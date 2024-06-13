package com.proyecto.rr7.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "RIFI")
@NamedQuery(name = "Rifi.findAll", query = "SELECT r FROM Rifi r")
@Getter
@Setter
public class Rifi implements Serializable {

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

    @Column(name = "CLAVE_RIF")
    private String claveRif;

    @Column(name = "SUB_CLAVE_RIF")
    private String subClaveRif;

    @Column(name = "OPERACION")
    private String operacion;

    @Column(name = "RAMO")
    private String ramo;

    @Column(name = "SUBRAMO")
    private String subram;

    @Column(name = "SUBSUBRAMO")
    private String subsubram;

    @Column(name = "SALDO")
    private double saldo;

}
