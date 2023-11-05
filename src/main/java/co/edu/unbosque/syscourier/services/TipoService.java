package co.edu.unbosque.syscourier.services;

import co.edu.unbosque.syscourier.models.repositories.TipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipoService {

    @Autowired
    private TipoRepository tipoRepository;

    public String getValorByID(int id) {
        return tipoRepository.getValorByID(id);
    }
}
