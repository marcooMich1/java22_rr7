package com.proyecto.rr7.services;

import com.proyecto.rr7.entities.ArchivoRespuesta;

import java.io.IOException;

public interface OactService {

    ArchivoRespuesta obtenerOact(String nombre) throws IOException;

    void updateOact(int anio, int mes);

}
