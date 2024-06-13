package com.proyecto.rr7.services;

import com.proyecto.rr7.entities.CCArchivosRR7;
import com.proyecto.rr7.entities.Rr7ArchivoHistorico;

import java.util.List;

public interface RR7ArchivoHistoricoService {

    public List<CCArchivosRR7> consultarRR7(String anioMes);

    public int getExistReporteHistorico(int anio, String mes, int idArchivo);

    public int existInfoBalanza(int anio, String mes);

    public Rr7ArchivoHistorico findByIdArchivo(int id, int anio, int mes, String archivo);

}
