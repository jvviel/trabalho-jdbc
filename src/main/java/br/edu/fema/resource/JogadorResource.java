package br.edu.fema.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.fema.model.Jogador;
import br.edu.fema.service.JogadorService;

@RestController
@RequestMapping("/jogadores")
public class JogadorResource {
	
	@Autowired
	private JogadorService jogadorService;

	@GetMapping("/{id}")
	public Jogador buscarLiga(@PathVariable Long id) {
		return jogadorService.buscarPorId(id);
	}
	
	@GetMapping
	public List<Jogador> listarTodos() {
		return jogadorService.listarTodos();
	}
	
	@PostMapping
	public ResponseEntity<?> gravarLiga(@RequestBody Jogador jogador) {
		return jogadorService.gravar(jogador);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> alterarEsporte(@RequestBody Jogador jogador, @PathVariable Long id) {
		return jogadorService.alterar(jogador, id);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> removerEsporte(@PathVariable Long id) {
		return jogadorService.remover(id);
	}
}
