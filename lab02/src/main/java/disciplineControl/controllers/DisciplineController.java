package disciplineControl.controllers;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import disciplineControl.entities.Disciplina;
import disciplineControl.services.DisciplineService;

@RestController
public class DisciplineController {

	@Autowired
	private DisciplineService disciplineService;

	@RequestMapping("/")
	public String index() {
		return "...";
	}

	@RequestMapping("/v1/api/disciplinas")
	public ResponseEntity<List<Disciplina>> getDisciplines() {
		return new ResponseEntity<List<Disciplina>>(disciplineService.getDisciplinas(), HttpStatus.OK);
	}

	@PostMapping("/v1/api/disciplinas")
	public ResponseEntity<Disciplina> addNewDiscipline(@RequestBody Disciplina discipline) {
		return new ResponseEntity<Disciplina>(disciplineService.addDisciplina(discipline), HttpStatus.CREATED);
	}

	@RequestMapping("/v1/api/disciplinas/{id}")
	public ResponseEntity<Disciplina> getDiscipline(@PathVariable Long id) {

		Optional<Disciplina> disciplinaEncontrada = disciplineService.getDisciplina(id);

		if (disciplinaEncontrada.isPresent()) {
			return new ResponseEntity<Disciplina>(disciplinaEncontrada.get(), HttpStatus.OK);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Disciplina nao encontrada");
		}

	}

	@PutMapping("/v1/api/disciplinas/{id}/nome")
	@Transactional
	public ResponseEntity<Disciplina> setDisciplina(@PathVariable Long id, @RequestBody Disciplina disciplina) {

		Optional<Disciplina> disciplinaEncontrada = disciplineService.getDisciplina(id);
		disciplinaEncontrada.get().setNome(disciplina.getNome());

		if (disciplinaEncontrada.isPresent()) {
			return new ResponseEntity<Disciplina>(disciplineService.setNomeDisciplina(id, disciplinaEncontrada).get(),
					HttpStatus.OK);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Disciplina nao encontrada");
		}
	}

	@PutMapping("/v1/api/disciplinas/{id}/nota")
	public ResponseEntity<Disciplina> setNotaDisciplina(@PathVariable Long id, @RequestBody Disciplina disciplina) {

		Optional<Disciplina> disciplinaEncontrada = disciplineService.getDisciplina(id);
		disciplinaEncontrada.get().setNome(disciplina.getNome());

		if (disciplinaEncontrada.isPresent()) {
			return new ResponseEntity<Disciplina>(disciplineService.setNotaDisciplina(id, disciplinaEncontrada).get(),
					HttpStatus.OK);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Disciplina nao encontrada");
		}
	}

	@DeleteMapping("/v1/api/disciplinas/{id}")
	public ResponseEntity<Disciplina> removeDisciplinaId(@PathVariable Long id) {

		Optional<Disciplina> disciplinaEncontrada = disciplineService.getDisciplina(id);

		if (disciplinaEncontrada.isPresent()) {
			return new ResponseEntity<Disciplina>(disciplineService.removeDisciplina(id).get(), HttpStatus.OK);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Disciplina nao encontrada");
		}

	}

	@RequestMapping("/v1/api/disciplinas/ranking")
	public ResponseEntity<List<Disciplina>> getDisciplinesOrdered() {
		return new ResponseEntity<List<Disciplina>>(disciplineService.getDisciplinesOrdered(), HttpStatus.OK);
	}

}
