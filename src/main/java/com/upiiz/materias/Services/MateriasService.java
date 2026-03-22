package com.upiiz.materias.Services;
import com.upiiz.materias.Models.Materia;
import com.upiiz.materias.dto.MateriaDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class MateriasService {
    private final List<Materia> materias = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong();

    public List<Materia> obtenerTodas() {
        return materias;
    }

    public Materia obtenerPorId(Long id) {
        return materias.stream()
                .filter(m -> m.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void guardar(MateriaDTO materiaDTO) {
        Materia nuevaMateria = new Materia();
        nuevaMateria.setId(counter.incrementAndGet());
        nuevaMateria.setNombre(materiaDTO.getNombre());
        nuevaMateria.setCreditos(materiaDTO.getCreditos());

        materias.add(nuevaMateria);
    }

    public void actualizar(Long id, MateriaDTO materiaDTO) {
    for (Materia m : materias){
        if (m.getId().equals(id)){
            m.setNombre(materiaDTO.getNombre());
            m.setCreditos(materiaDTO.getCreditos());
            return;
            }
        }
    }
    
    public void eliminar(Long id) {
        materias.removeIf(m -> m.getId().equals(id));
    }

}
