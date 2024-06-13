package com.proyecto.rr7.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "INVE")
@NamedQuery(name = "Inve.findAll", query = "SELECT i FROM Inve i")
@Getter
@Setter
public class Inve implements Serializable {

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

    @Column(name = "CONSECUTIV")
    private String consecutiv;

    @Column(name = "CLASIFICACION")
    private String clasificacion;

    @Column(name = "TIPO_ORG")
    private String tipoOrg;

    @Column(name = "CVE_ORG")
    private String cveOrg;

    @Column(name = "CONTRATO")
    private String contrato;

    @Column(name = "AFECTACION")
    private double afectacion;

    @Column(name = "ISIN")
    private String isin;

    @Column(name = "PARAMTRIZ")
    private String paramtriz;

    @Column(name = "CVE_EMISOR")
    private String cveEmisor;

    @Column(name = "SERIE")
    private String serie;

    @Column(name = "TIPO_VALOR")
    private String tipoValor;

    @Column(name = "DESCRIP")
    private String descrip;

    @Column(name = "LIQUIDEZ")
    private String liquidez;

    @Column(name = "FCH_EMI")
    private String fchEmi;

    @Column(name = "FCH_ADQ")
    private String fchAdq;

    @Column(name = "FCH_VTO")
    private String fchVto;

    @Column(name = "VALOR_NOM")
    private double valorNom;

    @Column(name = "TITULOS")
    private double titulos;

    @Column(name = "CTO_ADQ")
    private double ctoAdq;

    @Column(name = "VAL_MERCADO")
    private double valMercado;

    @Column(name = "PRECIO_UNI")
    private double precioUni;

    @Column(name = "TASA_VAL")
    private double tasaVal;

    @Column(name = "INC_DEL_VAL")
    private double incDelVal;

    @Column(name = "PERIOD_AMORT")
    private double periodAmort;

    @Column(name = "TASA_AMORT")
    private double tasaAmort;

    @Column(name = "PREMIO")
    private double premio;

    @Column(name = "PLAZO")
    private double plazo;

    @Column(name = "MDA_BASE")
    private String mdaBase;

    @Column(name = "CALIF")
    private String calif;

    @Column(name = "IN_X_DEV")
    private double inXDev;

    @Column(name = "PLAZO_CUPON")
    private double plazoCupon;

    @Column(name = "TASA_CUPO")
    private double tasaCupo;

    @Column(name = "CVE_TAS_REF")
    private String cveTasRef;

    @Column(name = "DETERIORO")
    private double deterioro;

    @Column(name = "D_X_INT")
    private double dXInt;

    @Column(name = "TOTAL_ACT")
    private double totalAct;

    @Column(name = "TIPO_EMISORA")
    private String tipoEmisora;

    @Column(name = "CALIFONCPT")
    private String califoncpt;

    @Column(name = "REGLA_CUPON")
    private String reglaCupon;

    @Column(name = "TII_AMP_DER")
    private double tiiAmpDer;

    @Column(name = "TASA_PACT_DER")
    private double tasaPactDer;

    @Column(name = "DIV_X_COB")
    private double divXCob;

    @Column(name = "NIVEL_FONDOS")
    private String nivelFondos;

    @Column(name = "INT_LP_CUBRE_CP")
    private String intLpCubreCp;

    @Column(name = "NOTA_ESTRUCTURADA")
    private String notaEstructurada;

    @Column(name = "TIPO_NOTA_ESTRUCTURADA")
    private String tipoNotaEstructurada;

    @Column(name = "PORTA_SEG_FLEX")
    private String portaSegFlex;

    @Column(name = "TASA_GARANTI")
    private double tasaGaranti;

    @Column(name = "NEGOCIABLE")
    private String negociable;

    @Column(name = "TIPO_GARANTIA_COBERTURA")
    private String tipoGarantiaCobertura;

    @Column(name = "CVE_PONDERADOR_CONTRAPARTE")
    private String cvePonderadorContraparte;

    @Column(name = "CALIFONGARAN")
    private String califongaran;

    @Column(name = "VE_PONDERADOR_GARANTIA")
    private String vePonderadorGarantia;

    @Column(name = "VALOR_CONVERSION_A_RIESGOCPT")
    private double valorConversionARiesgocpt;

    @Column(name = "VALOR_GARAN_COBER")
    private double valorGaranCober;

    @Column(name = "CALCE")
    private String calce;

    @Column(name = "INC_X_VALUA_CBI")
    private double incXValuaCbi;

    @Column(name = "INC_X_VALUA_OP")
    private double incXValuaOp;

    @Column(name = "INC_X_VALUA_RCS")
    private double incXValuaRcs;

    @Column(name = "CUENTABANCARIA")
    private String cuentaBancaria;

    @Column(name = "ANIOMES")
    private String anioMes;

}
