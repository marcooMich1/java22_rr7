package com.proyecto.rr7.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "FOND")
@NamedQuery(name = "Fond.findAll", query = "SELECT f FROM Fond f")
@Getter
@Setter
public class Fond implements Serializable {

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

    @Column(name = "CONSECUTIVO_INVE")
    private double consecutivoInve;

    @Column(name = "CVE_EMISOR_FOND")
    private String cveEmisorFond;

    @Column(name = "SERIE_FOND")
    private String serieFond;

    @Column(name = "TIPO_VALOR_FO")
    private String tipoValorFo;

    @Column(name = "CVE_EMISOR_INST")
    private String cveEmisorInst;

    @Column(name = "SERIE_INST")
    private String serieInst;

    @Column(name = "TIPO_VALOR_INST")
    private String tipoValorInst;

    @Column(name = "CLASIFICACION")
    private String clasificacion;

    @Column(name = "AFECTACION")
    private String afectacion;

    @Column(name = "ISIN")
    private String isin;

    @Column(name = "PARAMETRIZ")
    private String parametriz;

    @Column(name = "CONSECUTIVO_INST")
    private double consecutivoInst;

    @Column(name = "DESCRIPC")
    private String descripc;

    @Column(name = "LIQUIDEZ")
    private String liquidez;

    @Column(name = "MONEDA")
    private String moneda;

    @Column(name = "FCH_EMI_INST")
    private String fchEmiInst;

    @Column(name = "PORC_PARTIC_INST")
    private double porcParticInst;

    @Column(name = "CTO_ADQ_INST")
    private double ctoAdqInst;

    @Column(name = "VAL_COT_INST")
    private double valCotInst;

    @Column(name = "INC_DEC_VALUA")
    private double incDecValua;

    @Column(name = "CALIF")
    private String calif;

    @Column(name = "MDA_BASE")
    private double mdaBase;

    @Column(name = "NIVEL_FONDOS")
    private String nivelFondos;

    @Column(name = "ANIOMES")
    private String anioMes;

}
