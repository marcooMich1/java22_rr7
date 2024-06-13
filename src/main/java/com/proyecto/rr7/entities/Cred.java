package com.proyecto.rr7.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "CRED")
@NamedQuery(name = "Cred.findAll", query = "SELECT c FROM Cred c")
@Getter
@Setter
public class Cred implements Serializable {

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
    private double consecutiv;

    @Column(name = "AFECTACION")
    private String afectacion;

    @Column(name = "TIPO_CREDITO")
    private String tipoCredito;

    @Column(name = "VAL_HIST_INICIAL")
    private double valHistInicial;

    @Column(name = "SALDO_CIERRE")
    private double saldoCierre;

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

    @Column(name = "APELLIDO_PATERNO")
    private String apellidoPaterno;

    @Column(name = "APELLIDO_MATERNO")
    private String apellidoMaterno;

    @Column(name = "FECHA_VENCIMIENTO")
    private String fechaVencimiento;

    @Column(name = "ESTIM_CASTIGOS")
    private double estimCastigos;

    @Column(name = "D_X_I")
    private double dXI;

    @Column(name = "PAGO_CAPITAL")
    private double pagoCapital;

    @Column(name = "CARTERA_VENCIDA")
    private double carteraVencida;

    @Column(name = "PLAZO_INT")
    private double plazoInt;

    @Column(name = "GARANTIA")
    private String garantia;

    @Column(name = "VALOR_GARAN")
    private double valorGaran;

    @Column(name = "AVALUO_FIS")
    private double avaluoFis;

    @Column(name = "RESERVA_PREVENTIVA")
    private double reservaPreventiva;

    @Column(name = "NIVEL_FONDOS")
    private String nivelFondos;

    @Column(name = "NUMERO_POLIZA_DANIOS")
    private String numeroPolizaDanios;

    @Column(name = "NUMERO_POLIZA_VIDA")
    private String numeroPolizaVida;

    @Column(name = "TIPO_GARANTIA_COBERTURA")
    private String tipoGarantiaCobertura;

    @Column(name = "CALIFONCPT")
    private String califoncpt;

    @Column(name = "CVE_PONDERADOR_CONTRAPARTE")
    private String cvePonderadorContraparte;

    @Column(name = "CALIFONGARAN")
    private String califongaran;

    @Column(name = "CVE_PONDERADOR_GARANTIA")
    private String cvePonderadorGarantia;

    @Column(name = "VALOR_CONVERSION_A_RIESGOCPT")
    private double valorConversionARiesgocpt;

    @Column(name = "VALOR_GARAN_COBER")
    private double valorGaranCober;

    @Column(name = "PORTA_SEG_FLEX")
    private String portaSegFlex;

    @Column(name = "TASA_GARANTI")
    private double tasaGaranti;

    @Column(name = "TASA_INT")
    private double tasaInt;

    @Column(name = "ANIOMES")
    private String anioMes;

}
