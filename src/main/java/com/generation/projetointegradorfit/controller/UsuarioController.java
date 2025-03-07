package com.generation.projetointegradorfit.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.projetointegradorfit.model.Usuario;
import com.generation.projetointegradorfit.model.UsuarioLogin;
import com.generation.projetointegradorfit.repository.UsuarioRepository;
import com.generation.projetointegradorfit.service.UsuarioService;

import jakarta.validation.Valid;
@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {    @Autowired
private UsuarioRepository usuarioRepository;


   @GetMapping
   public ResponseEntity<List<Usuario>> getAll() {
       return ResponseEntity.ok(usuarioRepository.findAll());
   }


   @GetMapping("/{id}")
   public ResponseEntity<Usuario> getById(@PathVariable Long id) {
       return usuarioRepository.findById(id).map(resposta -> ResponseEntity.ok(resposta))
               .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
   }


   @GetMapping("/usuario/{nome}")
   public ResponseEntity<List<Usuario>> getByUsuario(@PathVariable String usuario ){
       return ResponseEntity.ok(usuarioRepository.findAllByNomeContainingIgnoreCase(usuario));
   }


   @PostMapping
   public ResponseEntity<Usuario> post(@Valid @RequestBody Usuario usuario){
       return ResponseEntity.status(HttpStatus.CREATED)
               .body(usuarioRepository.save(usuario));
   }


   @PutMapping
   public ResponseEntity<Usuario> put(@Valid @RequestBody Usuario usuario) {
       return usuarioRepository.findById(usuario.getId())
               .map(resposta -> ResponseEntity.status(HttpStatus.OK)
                       .body(usuarioRepository.save(usuario)))
               .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
   }


   @ResponseStatus(HttpStatus.NO_CONTENT)
   @DeleteMapping("/{id}")
   public void delete(@PathVariable Long id) {
       Optional<Usuario> usuario = usuarioRepository.findById(id);


       if(usuario.isEmpty())
           throw new ResponseStatusException(HttpStatus.NOT_FOUND);


       usuarioRepository.deleteById(id);
   }
   //Login do usuario
 //-----------------------------------------------------------------------------------------------------------------
	
   @PostMapping("/logar")
	public ResponseEntity<UsuarioLogin> autenticarUsuario(@RequestBody Optional<UsuarioLogin> usuarioLogin){
		
		return usuarioService.autenticarUsuario(usuarioLogin)
				.map(resposta -> ResponseEntity.status(HttpStatus.OK).body(resposta))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}
   

	@PostMapping("/cadastrar")
	public ResponseEntity<Usuario> postUsuario(@RequestBody @Valid Usuario usuario) {

		return usuarioService.cadastrarUsuario(usuario)
			.map(resposta -> ResponseEntity.status(HttpStatus.CREATED).body(resposta))
			.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());

	}

	@PutMapping("/atualizar")
	public ResponseEntity<Usuario> putUsuario(@Valid @RequestBody Usuario usuario) {
		
		return usuarioService.atualizarUsuario(usuario)
			.map(resposta -> ResponseEntity.status(HttpStatus.OK).body(resposta))
			.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
   
   //Metodo para imc
//-----------------------------------------------------------------------------------------------------------------
   @Autowired
   private UsuarioService usuarioService;
    
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
