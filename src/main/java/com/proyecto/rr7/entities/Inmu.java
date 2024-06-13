package com.proyecto.rr7.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "INMU")
@NamedQuery(name = "Inmu.findAll", query = "SELECT i FROM Inmu i")
@Getter
@Setter
public class Inmu implements Serializable {

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

    @Column(name = "CONSECUTIV")
    private double consecutiv;

    @Column(name = "AFECTACION")
    private String afectacion;

    @Column(name = "NIVEL_FONDOS")
    private String nivelFondos;

    @Column(name = "CALLE")
    private String calle;

    @Column(name = "NUMERO")
    private String numero;

    @Column(name = "COLONIA")
    private String colonia;

    @Column(name = "CP")
    private String cp;

    @Column(name = "ENTIDAD")
    private String entidad;

    @Column(name = "TIPO_INM")
    private String tipoInm;

    @Column(name = "CLASIF_INM")
    private String clasifInm;

    @Column(name = "FCH_ADQ")
    private String fchAdq;

    @Column(name = "FCH_ULT_VAL")
    private String fchUltVal;

    @Column(name = "FCH_CAP_SV")
    private String fchCapSv;

    @Column(name = "FCH_INI_ARREN")
    private String fchIniArren;

    @Column(name = "FCH_VEN_ARREN")
    private String fchVenArren;

    @Column(name = "ESTATUS_P_R")
    private String estatusPR;

    @Column(name = "FCH_INI_CONS")
    private String fchIniCons;

    @Column(name = "CTO_HIST")
    private double ctoHist;

    @Column(name = "DEPRECI_HI")
    private double depreciHi;

    @Column(name = "INCR_VAL")
    private double incrVal;

    @Column(name = "DEPRE_INC")
    private double depreInc;

    @Column(name = "TOTAL_INMUEBLE")
    private double totalInmueble;

    @Column(name = "SUPERAVIT_AFECTO")
    private double superavitAfecto;

    @Column(name = "CAP_SUPERAVIT")
    private double capSuperavit;

    @Column(name = "GASTOS_AN")
    private double gastosAn;

    @Column(name = "RENTAS_AN")
    private double rentasAn;

    @Column(name = "PORCENTAJE_AFECT")
    private double porcentajeAfect;

    @Column(name = "IMPORTE_AFECTO_TOTAL")
    private double importeAfectoTotal;

    @Column(name = "TASA_RDTO")
    private double tasaRdto;

    @Column(name = "VALOR_FIS")
    private double valorFis;

    @Column(name = "VALOR_RENT")
    private double valorRent;

    @Column(name = "VALOR_COMER")
    private double valorComer;

    @Column(name = "PORTA_SEG_FLEX")
    private String portaSegFlex;

    @Column(name = "TASA_GARANTI")
    private double tasaGaranti;

    @Column(name = "SUPERAVIT_AFECTO_OP")
    private double superavitAfectoOp;

    @Column(name = "SUPERAVIT_AFECTO_RCS")
    private double superavitAfectoRcs;

    @Column(name = "ANIOMES")
    private String anioMes;

}
