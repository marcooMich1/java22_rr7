package com.proyecto.rr7.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "DEUD")
@NamedQuery(name = "Deud.findAll", query = "SELECT d FROM Deud d")
@Getter
@Setter
public class Deud implements Serializable {

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

    @Column(name = "CONSECUTIVO")
    private double consecutivo;

    @Column(name = "OPERACION")
    private String operacion;

    @Column(name = "CVE_RAMO")
    private String cveRamo;

    @Column(name = "PLAZO")
    private String plazo;

    @Column(name = "AFECTACION")
    private String afectacion;

    @Column(name = "PRIMAS_POR_COBRAR_TOTAL")
    private double primasPorCobrarTotal;

    @Column(name = "RECARGOS")
    private double recargos;

    @Column(name = "IMPUESTOS")
    private double impuestos;

    @Column(name = "DERECHOS_POLIZA")
    private double derechosPoliza;

    @Column(name = "RECARGOS_DEV")
    private double recargosDev;

    @Column(name = "DERECHOS_POLIZA_DEV")
    private double derechosPolizaDev;

    @Column(name = "COMI_X_DEV")
    private double comiXDev;

    @Column(name = "PRIMAS_POR_COBRAR_AFECTO")
    private double primasPorCobrarAfecto;

    @Column(name = "ANIOMES")
    private String anioMes;

}
