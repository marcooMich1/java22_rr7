package com.proyecto.rr7.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "OACT")
@NamedQuery(name = "Oact.findAll", query = "SELECT o FROM Oact o")
@Getter
@Setter
public class Oact implements Serializable {

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

    @Column(name = "CVE_OTROS_ACTIVOS")
    private String cveOtrosActivos;

    @Column(name = "SUB_CVE_OTROS_ACTIVOS")
    private String subCveOtrosActivos;

    @Column(name = "AFECTACION")
    private String afectacion;

    @Column(name = "NIVEL_FONDOS")
    private String nivFondProp;

    @Column(name = "VALORES")
    private double valores;

}
