package escola.service;

import escola.exception.RecursoNaoEncontradoException;
import escola.exception.RegraNegocioException;
import escola.model.Sala;
import escola.repository.SalaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalaService {

    private final SalaRepository salaRepository;

    public SalaService(SalaRepository salaRepository) {
        this.salaRepository = salaRepository;
    }

    public Sala salvar(Sala sala) {
        // Regra: não pode código repetido
        if (salaRepository.findByCodigo(sala.getCodigo()).isPresent()) {
            throw new RegraNegocioException("Já existe uma sala cadastrada com o código: " + sala.getCodigo());
        }
        return salaRepository.save(sala);
    }

    public Sala buscarPorId(Long id) {
        // Regra: se não achar a sala dá 404
        return salaRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Sala não encontrada com o ID: " + id));
    }

    public List<Sala> listarTodas() {
        return salaRepository.findAll();
    }

    public Sala reservar(Long id) {
        Sala sala = buscarPorId(id);

        // Regra: não pode reservar sala que já tá reservada
        if (Boolean.TRUE.equals(sala.getReservada())) {
            throw new RegraNegocioException("A sala com ID " + id + " já está reservada.");
        }

        sala.setReservada(true);
        return salaRepository.save(sala);
    }

    public Sala liberar(Long id) {
        Sala sala = buscarPorId(id);

        // Regra: só pode liberar se tiver reservada
        if (Boolean.FALSE.equals(sala.getReservada())) {
            throw new RegraNegocioException("A sala com ID " + id + " não está reservada para ser liberada.");
        }

        sala.setReservada(false);
        return salaRepository.save(sala);
    }

    public void excluir(Long id) {
        Sala sala = buscarPorId(id);

        // Regra: não pode excluir se tiver reservada
        if (Boolean.TRUE.equals(sala.getReservada())) {
            throw new RegraNegocioException("Não é possível excluir uma sala que está reservada.");
        }

        salaRepository.delete(sala);
    }
}