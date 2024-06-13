package com.proyecto.rr7.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "INDE")
@NamedQuery(name = "Inde.findAll", query = "SELECT i FROM Inde i")
@Getter
@Setter
public class Inde implements Serializable {

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

    @Column(name = "AFECTACION")
    private String afectacion;

    @Column(name = "MDA_BASE")
    private String mdaBase;

    @Column(name = "FCH_ADQ")
    private String fchAdq;

    @Column(name = "LIQUIDEZ")
    private String liquidez;

    @Column(name = "ISIN")
    private String isin;

    @Column(name = "EMISOR")
    private String emisor;

    @Column(name = "DESCRIPC")
    private String descripc;

    @Column(name = "PARAMETRIZ")
    private String parametriz;

    @Column(name = "SERIE")
    private String serie;

    @Column(name = "TIPO_VALOR")
    private String tipoValor;

    @Column(name = "NUM_CONTRS")
    private String numContrs;

    @Column(name = "FCH_EMI")
    private String fchEmi;

    @Column(name = "FCH_VTO")
    private String fchVto;

    @Column(name = "TASA_RDTO")
    private String tasaRdto;

    @Column(name = "CALIFIC")
    private String calific;

    @Column(name = "TIPO_MOD")
    private String tipoMod;

    @Column(name = "TIPO_ORG")
    private String tipoOrg;

    @Column(name = "CONTRATO")
    private String contrato;

    @Column(name = "CLAVE_ORG")
    private String claveOrg;

    @Column(name = "PRECIO_EJ")
    private String precioEj;

    @Column(name = "IND_EFECT")
    private String indEfect;

    @Column(name = "CTO_ADQ_POSICION_ACTIVA")
    private String ctoAdqPosicionActiva;

    @Column(name = "CTO_ADQ_POSICION_PASIVA")
    private String ctoAdqPosicionPasiva;

    @Column(name = "NETO_COTIZACION")
    private String netoCotizacion;

    @Column(name = "COTIZACION_POSICION_ACTIVA")
    private String cotizacionPosicionActiva;

    @Column(name = "COTIZACION_POSICION_PASIV")
    private String cotizacionPosicionPasiv;

    @Column(name = "NETO_ADQUISICION")
    private String netoAdquisicion;

    @Column(name = "PRIMA_EVALUADA_OPCIONES")
    private String primaEvaluadaOpciones;

    @Column(name = "PRIMA_PAGADA_OPCIONES")
    private String primaPagadaOpciones;

    @Column(name = "INCREMENTO_VALUACION")
    private String incrementoValuacion;

    @Column(name = "APORT_GARANT_DERIVADOS")
    private String aportGarantDerivados;

    @Column(name = "CONSEC_DV")
    private String consecDv;

    @Column(name = "MONTO_EFECTO")
    private String montoEfecto;

    @Column(name = "NIVEL_FONDOS")
    private String nivelFondos;

    @Column(name = "TITULOS")
    private String titulos;

    @Column(name = "TIPO_EMISORA")
    private String tipoEmisora;

    @Column(name = "PRECIO_EJ_PAS")
    private String precioEjPas;

    @Column(name = "MONEDA_ACT")
    private String monedaAct;

    @Column(name = "MONEDA_PAS")
    private String monedaPas;

    @Column(name = "PZO_PAGO_ACT")
    private String pzoPagoAct;

    @Column(name = "PZO_PAGO_PAS")
    private String pzoPagoPas;

    @Column(name = "TIPO_MOD_PAS")
    private String tipoModPas;

    @Column(name = "PORTA_SEG_FLEX")
    private String portaSegFlex;

    @Column(name = "TASA_GARANTI")
    private String tasaGaranti;

    @Column(name = "CALCE")
    private String calce;

    @Column(name = "TASA_PACTADA_SWAP_ACT")
    private String tasaPactadaSwapAct;

    @Column(name = "TASA_PACTADA_SWAP_PAS")
    private String tasaPactadaSwapPas;

    @Column(name = "ANIOMES")
    private String anioMes;

}
