package com.proyecto.rr7.services;

import com.proyecto.rr7.entities.ArchivoRespuesta;

import java.io.IOException;

public interface FondService {

    ArchivoRespuesta obtenerFond(String nombre, String anioMes) throws IOException;

    int getExistInfoFond(String anioMes);

}
