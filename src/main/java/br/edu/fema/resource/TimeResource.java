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

import br.edu.fema.model.Time;
import br.edu.fema.service.TimeService;

@RestController
@RequestMapping("/times")
public class TimeResource {
	
	@Autowired
	private TimeService timeService;

	@GetMapping("/{id}")
	public Time buscarLiga(@PathVariable Long id) {
		return timeService.buscarPorId(id);
	}
	
	@GetMapping
	public List<Time> listarTodos() {
		return timeService.listarTodos();
	}
	
	@PostMapping
	public ResponseEntity<?> gravarLiga(@RequestBody Time time) {
		return timeService.gravar(time);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> alterarEsporte(@RequestBody Time time, @PathVariable Long id) {
		return timeService.alterar(time, id);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> removerEsporte(@PathVariable Long id) {
		return timeService.remover(id);
	}
}
