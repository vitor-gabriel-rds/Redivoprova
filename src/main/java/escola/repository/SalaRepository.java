package escola.repository;

import escola.model.Sala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface SalaRepository extends JpaRepository<Sala, Long> {
    
    // Método utilitário caso precise buscar pelo código único na prova
    Optional<Sala> findByCodigo(String codigo);
}