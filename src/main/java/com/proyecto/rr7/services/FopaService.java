package com.proyecto.rr7.services;

import com.proyecto.rr7.entities.ArchivoRespuesta;

import java.io.IOException;

public interface FopaService {

    ArchivoRespuesta obtenerFopa(String nombre, String anioMes) throws IOException;

    int getExistInfoFopa(String anioMes);

}
