package com.will.shop.algafoodapi.infrastructure.repository;

import com.will.shop.algafoodapi.domain.model.Cozinha;
import com.will.shop.algafoodapi.domain.model.Estado;
import com.will.shop.algafoodapi.domain.repository.CozinhaRepository;
import com.will.shop.algafoodapi.domain.repository.EstadoRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class EstadoRepositoryImp implements EstadoRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Estado> listar() {
        TypedQuery<Estado> query = manager.createQuery("from Estado", Estado.class);
        return query.getResultList();
    }

    @Override
    public Estado buscar(Long id) {
        return manager.find(Estado.class, id);
    }


    @Transactional
    @Override
    public Estado adcionar(Estado estado) {
        return manager.merge(estado);
    }


    @Transactional
    @Override
    public void remover(Estado estado) {
        estado = buscar(estado.getId());
        manager.remove(estado);

    }

}
