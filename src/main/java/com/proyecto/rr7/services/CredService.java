package com.proyecto.rr7.services;

import com.proyecto.rr7.entities.ArchivoRespuesta;

import java.io.IOException;

public interface CredService {

    ArchivoRespuesta obtenerCred(String nombre, String anioMes) throws IOException;

    int getExistInfoCred(String anioMes);
}
