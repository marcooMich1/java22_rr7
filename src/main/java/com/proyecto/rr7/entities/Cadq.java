package com.proyecto.rr7.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "CADQ")
@NamedQuery(name = "Cadq.findAll", query = "SELECT c FROM Cadq c")
@Getter
@Setter
public class Cadq implements Serializable {

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

    @Column(name = "CLAVE_CTO_ADQ")
    private String claveCtoAdq;

    @Column(name = "SUB_CLAVE_CTO_ADQ")
    private String subClaveCtoAdq;

    @Column(name = "OPERACION")
    private String operacion;

    @Column(name = "RAMO")
    private String ramo;

    @Column(name = "SUBRAMO")
    private String subram;

    @Column(name = "SUBSUBRAMO")
    private String subsubramo;

    @Column(name = "SALDO")
    private double saldo;

}
