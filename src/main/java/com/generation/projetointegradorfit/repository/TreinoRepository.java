package com.generation.projetointegradorfit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.projetointegradorfit.model.Treino;

@Repository
public interface TreinoRepository extends JpaRepository<Treino, Long> {

    // Busca treinos por descrição (ignorando maiúsculas e minúsculas)
    List<Treino> findAllByDescricaoContainingIgnoreCase(String descricao);

    // Busca treinos por nome (ignorando maiúsculas e minúsculas)
    List<Treino> findAllByTreinoContainingIgnoreCase(String treino);

    // Busca treinos por usuário
    List<Treino> findByUsuarioId(Long usuarioId);

    // Busca treinos por nome ou descrição (ignorando maiúsculas e minúsculas)
    List<Treino> findAllByTreinoContainingIgnoreCaseOrDescricaoContainingIgnoreCase(String treino, String descricao);
}