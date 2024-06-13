package com.proyecto.rr7.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "BASE_LAYOUT_RR7")
@NamedQuery(name = "BaseLayoutRr7.findAll", query = "SELECT r FROM BaseLayoutRr7 r")
@Getter
@Setter
public class BaseLayoutRr7 implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDX")
    private int id;

    @Column(name = "ANIO")
    private int anio;

    @Column(name = "MES")
    private int mes;

    @Column(name = "CTA_QTO_NIV_NVO_CAT")
    private String ctaQtoNivNvoCat;

    @Column(name = "TIPO_MONEDA")
    private int tipoMoneda;

    @Column(name = "CTA_CAT_ANT")
    private String ctaCatAnt;

    @Column(name = "SUB_CTA_CAT_ANT")
    private String subCtaCatAnt;

    @Column(name = "RAMO")
    private String ramo;

    @Column(name = "SUBRAMO")
    private String subramo;

    @Column(name = "SUBSUBRAMO")
    private String subsubramo;

    @Column(name = "AFECTO")
    private String afecto;

    @Column(name = "NIV_FOND_PROP_ADMI_")
    private String nivFondPropAdmi;

    @Column(name = "MONT_MON_CONSOLIDADA")
    private double montMonConsolidada;

    @Column(name = "OPERACION")
    private String operacion;

    @Column(name = "CLAVE")
    private String clave;

    @Column(name = "SUBCLAVE")
    private String subClave;

}
