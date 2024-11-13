package com.forum.repository;

import com.forum.model.Resposta;
import com.forum.model.Topico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RespostaRepository extends JpaRepository<Resposta, Long> {

    Resposta findByTopico(Topico topico);
}
