package com.proyecto.rr7.services;

import com.proyecto.rr7.entities.ArchivoRespuesta;

import java.io.IOException;

public interface AcreService {

    ArchivoRespuesta obtenerACRE(String nombre) throws IOException;

    void updateAcre(int anio, int mes);

}
