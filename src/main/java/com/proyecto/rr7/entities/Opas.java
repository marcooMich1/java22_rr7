package com.proyecto.rr7.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "OPAS")
@NamedQuery(name = "Opas.findAll", query = "SELECT o FROM Opas o")
@Getter
@Setter
public class Opas implements Serializable {

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

    @Column(name = "CLAVE_OTROS_PASIV")
    private String claveOtrosPasiv;

    @Column(name = "SUB_CLAVE_OTROS_PASIV")
    private String subClaveOtrosPasiv;

    @Column(name = "SALDO")
    private double saldo;

}
