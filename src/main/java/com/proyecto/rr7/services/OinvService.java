package com.proyecto.rr7.services;

import com.proyecto.rr7.entities.ArchivoRespuesta;

import java.io.IOException;

public interface OinvService {

    ArchivoRespuesta obtenerOinv(String nombre, String anioMes) throws IOException;

    int getExistInfoOinv(String anioMes);

}
