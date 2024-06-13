package com.proyecto.rr7.services;

import com.proyecto.rr7.entities.ArchivoRespuesta;

import java.io.IOException;

public interface DeudService {

    ArchivoRespuesta obtenerDeud(String nombre, String anioMes) throws IOException;

    int getExistInfoDeud(String anioMes);

}
