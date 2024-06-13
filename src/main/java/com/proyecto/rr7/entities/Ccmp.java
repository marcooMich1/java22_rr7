package com.proyecto.rr7.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "CCMP")
@NamedQuery(name = "Ccmp.findAll", query = "SELECT c FROM Ccmp c")
@Getter
@Setter
public class Ccmp implements Serializable {

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

    @Column(name = "CAPITAL_CONTABLE")
    private double capitalContable;

    @Column(name = "IXV_INM_NETO")
    private double ixvInmNeto;

    @Column(name = "UTIL_VAL_CAPITAL")
    private double utilValCapital;

    @Column(name = "INC_CAP_INM")
    private double incCapInm;

    @Column(name = "CAPITAL_PAGADO")
    private double capitalPagado;

    @Column(name = "IXV_INM_NETO_RT")
    private double ixvInmNetoRt;

    @Column(name = "IXV_INM_NETO_SUSCEPT")
    private double ixvInmNetoRtSuscept;

    @Column(name = "CMP")
    private double cmp;

    @Column(name = "CMP_EXIGIDO")
    private double cmpExigido;

    @Column(name = "SOBRANTE")
    private double sobrante;

    @Column(name = "ANIOMES")
    private String anioMes;

}
