package com.proyecto.rr7.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "OPAC")
@NamedQuery(name = "Opac.findAll", query = "SELECT o FROM Opac o")
@Getter
@Setter
public class Opac implements Serializable {

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

    @Column(name = "CLAVE_OPER_ANA_CONEX")
    private String claveOperAnaConex;

    @Column(name = "SUB_CLAVE_OPER_ANA_CONEX")
    private String subClaveOperAnaConex;

    @Column(name = "OPERACION")
    private String operacion;

    @Column(name = "RAMO")
    private String ramo;

    @Column(name = "SUBRAMO")
    private String subRam;

    @Column(name = "SUBSUBRAMO")
    private String subSubRamo;

    @Column(name = "SALDO")
    private double saldo;

}
