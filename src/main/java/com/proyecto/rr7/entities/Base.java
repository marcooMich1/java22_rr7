package com.proyecto.rr7.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "BASE")
@NamedQuery(name = "Base.findAll", query = "SELECT r FROM Base r")
@Getter
@Setter
public class Base implements Serializable {

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
    private int moneda;

    @Column(name = "OPERACION")
    private String operacion;

    @Column(name = "RAMO")
    private String ramo;

    @Column(name = "SUBRAMO")
    private String subramo;

    @Column(name = "SUBSUBRAMO")
    private String subsubramo;

    @Column(name = "RSVA_TOT")
    private double rsvaTot;

    @Column(name = "RSVA_RET")
    private double rsvaRet;

    @Column(name = "RSVA_CED")
    private double rsvaCed;

    @Column(name = "POR_CP")
    private double porCp;

    @Column(name = "ANIOMES")
    private String anioMes;

}
