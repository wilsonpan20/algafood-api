package com.will.shop.algafoodapi.infrastructure.repository;

import com.will.shop.algafoodapi.domain.model.Cozinha;
import com.will.shop.algafoodapi.domain.model.Restaurante;
import com.will.shop.algafoodapi.domain.repository.RestauranteRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class RetauranteRepositoryImp implements RestauranteRepository {
    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Restaurante> listar() {
        TypedQuery<Restaurante> query = manager.createQuery("from Restaurante ", Restaurante.class);
        return query.getResultList();
    }

    @Override
    public Restaurante getById(Long id) {
        return manager.find(Restaurante.class, id);
    }


    @Transactional
    @Override
    public Restaurante adcionar(Restaurante restaurante) {
        return manager.merge(restaurante);
    }


    @Transactional
    @Override
    public void remover(Restaurante restaurante) {
        restaurante = getById(restaurante.getId());
        manager.remove(restaurante);

    }
}
