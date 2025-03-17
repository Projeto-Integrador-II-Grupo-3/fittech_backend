package com.generation.projetointegradorfit.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.generation.projetointegradorfit.model.Treino;
import com.generation.projetointegradorfit.model.Usuario;
import com.generation.projetointegradorfit.model.UsuarioLogin;
import com.generation.projetointegradorfit.repository.TreinoRepository;
import com.generation.projetointegradorfit.repository.UsuarioRepository;
import com.generation.projetointegradorfit.service.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TreinoRepository treinoRepository;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> getAll() {
        return ResponseEntity.ok(usuarioRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getById(@PathVariable Long id) {
        return usuarioRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/usuario/{nome}")
    public ResponseEntity<List<Usuario>> getByUsuario(@PathVariable String nome) {
        return ResponseEntity.ok(usuarioRepository.findAllByNomeContainingIgnoreCase(nome));
    }

    @PostMapping
    public ResponseEntity<Usuario> post(@Valid @RequestBody Usuario usuario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioRepository.save(usuario));
    }

    @PutMapping
    public ResponseEntity<Usuario> put(@Valid @RequestBody Usuario usuario) {
        return usuarioRepository.findById(usuario.getId())
                .map(resposta -> ResponseEntity.ok(usuarioRepository.save(usuario)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/{id}/atualizar-treino")
    public ResponseEntity<Usuario> atualizarTreinoUsuario(@PathVariable Long id, @RequestBody Map<String, Long> request) {
        Long treinoId = request.get("treinoId");

        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        Optional<Treino> treinoOptional = treinoRepository.findById(treinoId);

        if (usuarioOptional.isEmpty() || treinoOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Usuario usuario = usuarioOptional.get();
        Treino treino = treinoOptional.get();

        usuario.adicionarTreino(treino);
        treino.setUsuario(usuario);

        usuarioRepository.save(usuario);
        treinoRepository.save(treino);

        return ResponseEntity.ok(usuario);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        usuarioRepository.deleteById(id);
    }

    // Login do usuário
    @PostMapping("/logar")
    public ResponseEntity<UsuarioLogin> autenticarUsuario(@RequestBody Optional<UsuarioLogin> usuarioLogin) {
        return usuarioService.autenticarUsuario(usuarioLogin)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Usuario> postUsuario(@Valid @RequestBody Usuario usuario) {
        return usuarioService.cadastrarUsuario(usuario)
                .map(resposta -> ResponseEntity.status(HttpStatus.CREATED).body(resposta))
                .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    @PutMapping("/atualizar")
    public ResponseEntity<Usuario> putUsuario(@Valid @RequestBody Usuario usuario) {
        return usuarioService.atualizarUsuario(usuario)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Cálculo do IMC
    @PutMapping("/{id}/calcular-imc")
    public ResponseEntity<Usuario> calcularIMC(@PathVariable Long id) {
        usuarioService.calcularEAtualizarIMC(id);
        Usuario usuario = usuarioService.getUsuarioComIMC(id);
        return ResponseEntity.ok(usuario);
    }

    @GetMapping("/{id}/imc")
    public ResponseEntity<Usuario> obterUsuarioComIMC(@PathVariable Long id) {
        Usuario usuario = usuarioService.getUsuarioComIMC(id);
        return ResponseEntity.ok(usuario);
    }
}
