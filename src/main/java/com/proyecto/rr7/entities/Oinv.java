package com.proyecto.rr7.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "OINV")
@NamedQuery(name = "Oinv.findAll", query = "SELECT o FROM Oinv o")
@Getter
@Setter
public class Oinv implements Serializable {

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

    @Column(name = "AFECTACION")
    private String afectacion;

    @Column(name = "TIPO_ACTIVO")
    private String tipoActivo;

    @Column(name = "VAL_HIST_INICIAL")
    private double valHistInicial;

    @Column(name = "SALDO_CIERRE")
    private double saldoCierre;

    @Column(name = "NUMERO_LICITA")
    private double numeroLicita;

    @Column(name = "FECHA_ADQUI")
    private String fechaAdqui;

    @Column(name = "PERSONALIDAD_JURIDICA")
    private String personalidadJuridica;

    @Column(name = "RAZON_SOCIAL")
    private String razonSocial;

    @Column(name = "PRIMER_NOMBRE")
    private String primerNombre;

    @Column(name = "SEGUNDO_NOMBRE")
    private String segundoNombre;

    @Column(name = "APELLIDO_MATERNO")
    private String apellidoMaterno;

    @Column(name = "APELLIDO_PATERNO")
    private String apellidoPaterno;

    @Column(name = "FCH_VNTO")
    private String fchVnto;

    @Column(name = "D_X_INT")
    private double dXInt;

    @Column(name = "PLAZO_INT")
    private double plazoInt;

    @Column(name = "TASA_INT")
    private double tasaInt;

    @Column(name = "TIPO_DEUDOR")
    private String tipoDeudor;

    @Column(name = "TIPO_DXC")
    private String tipoDxc;

    @Column(name = "IMP_DEU_FIANZAS")
    private double impDeuFianzas;

    @Column(name = "TIPO_DEU_X_RESP")
    private String tipoDeuXResp;

    @Column(name = "TIPO_DIV")
    private String tipoDiv;

    @Column(name = "NIVEL_FONDOS")
    private String nivelFondos;

    @Column(name = "CVE_PONDERADOR_CONTRAPARTE")
    private String cvePonderadorContraparte;

    @Column(name = "CVE_PONDERADOR_GARANTIA")
    private String cvePonderadorGarantia;

    @Column(name = "CALIFONCPT")
    private String califoncpt;

    @Column(name = "CALIFONGARAN")
    private String califongaran;

    @Column(name = "VALOR_CONVERSION_A_RIESGOCPT")
    private double valorConversionARiesgocpt;

    @Column(name = "VALOR_GARAN_COBER")
    private double valorGaranCober;

    @Column(name = "TIPO_GARANTIA_COBERTURA")
    private String tipoGarantiaCobertura;

    @Column(name = "PORTA_SEG_FLEX")
    private String portaSegFlex;

    @Column(name = "TASA_GARANTI")
    private double tasaGaranti;

    @Column(name = "CONTROL_CONSECUTIVO")
    private String controlConsecituvo;

    @Column(name = "DESCRIPCION")
    private String descripcion;

    @Column(name = "ANIOMES")
    private String anioMes;

}
