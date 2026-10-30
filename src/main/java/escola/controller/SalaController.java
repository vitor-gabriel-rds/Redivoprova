package escola.controller;

import escola.model.Sala;
import escola.service.SalaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salas")
public class SalaController {

    private final SalaService salaService;

    public SalaController(SalaService salaService) {
        this.salaService = salaService;
    }

    // POST /salas -> 201 Created
    // O @Valid aciona o Bean Validation da classe Sala. Se falhar, o Spring retorna 400 Bad Request
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Sala cadastrar(@Valid @RequestBody Sala sala) {
        return salaService.salvar(sala);
    }

    // GET /salas -> 200 OK
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Sala> listar() {
        return salaService.listarTodas();
    }

    // GET /salas/{id} -> 200 OK
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Sala buscarPorId(@PathVariable Long id) {
        return salaService.buscarPorId(id);
    }

    // PATCH /salas/{id}/reservar -> 200 OK
    @PatchMapping("/{id}/reservar")
    @ResponseStatus(HttpStatus.OK)
    public Sala reservar(@PathVariable Long id) {
        return salaService.reservar(id);
    }

    // PATCH /salas/{id}/liberar -> 200 OK
    @PatchMapping("/{id}/liberar")
    @ResponseStatus(HttpStatus.OK)
    public Sala liberar(@PathVariable Long id) {
        return salaService.liberar(id);
    }

    // DELETE /salas/{id} -> 204 No Content
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        salaService.excluir(id);
    }
}