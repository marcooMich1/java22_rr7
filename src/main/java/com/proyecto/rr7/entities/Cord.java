package com.proyecto.rr7.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "CORD")
@NamedQuery(name = "Cord.findAll", query = "SELECT c FROM Cord c")
@Getter
@Setter
public class Cord implements Serializable {

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

    @Column(name = "CLAVE_CTAS_ORDEN")
    private String claveCtasOrden;

    @Column(name = "SUB_CLAVE_CTAS_ORDEN")
    private String subClaveCtasOrden;

    @Column(name = "SALDO")
    private double saldo;

}
