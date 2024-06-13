package com.proyecto.rr7.services;

import com.proyecto.rr7.util.ResponseMessage;

import java.io.IOException;

public interface CalcularReporteService {

    public ResponseMessage procesarRr7(int[] idsArchivos, String anioMes, String clvCompania) throws IOException;

    public ResponseMessage updateRR7(int[] idsArchivos, String anioMes, String clvCompania) throws IOException ;

}
