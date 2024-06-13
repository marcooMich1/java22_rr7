package com.proyecto.rr7.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "COPE")
@NamedQuery(name = "Cope.FindAll", query = "SELECT c FROM Cope c")
@Getter
@Setter
public class Cope implements Serializable {

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

    @Column(name = "CLAVE_CTO_OPER")
    private String claveCtoOper;

    @Column(name = "SUB_CLAVE_CTO_OPER")
    private String subClaveCtoOper;

    @Column(name = "OPERACION")
    private String operacion;

    @Column(name = "RAMO")
    private String ramo;

    @Column(name = "SUBRAMO")
    private String subRamo;

    @Column(name = "SUBSUBRAMO")
    private String subSubRamo;

    @Column(name = "SALDO")
    private double saldo;

}
