package disciplineControl.controllers;

import java.util.Comparator;
import java.util.List;

import javax.xml.ws.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
		return new ResponseEntity<List<Disciplina>>(disciplineService.getDisciplines(), HttpStatus.OK);
	}

	@PostMapping("/v1/api/disciplinas")
	public ResponseEntity<Disciplina> addNewDiscipline(@RequestBody Disciplina discipline) {
		return new ResponseEntity<Disciplina>(disciplineService.addDisciplina(discipline), HttpStatus.CREATED);
	}

	@RequestMapping("/v1/api/disciplinas/{id}")
	public ResponseEntity<Disciplina> getDiscipline(@PathVariable int id) {

		Disciplina disciplinaEncontrada = disciplineService.getDisciplinas(id);

		if (disciplinaEncontrada != null) {
			return new ResponseEntity<Disciplina>(disciplinaEncontrada, HttpStatus.OK);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Disciplina nao encontrada");
		}

	}

	@PutMapping("/v1/api/disciplinas/{id}/nome")
	public ResponseEntity<Disciplina> setDisciplina(@PathVariable int id, @RequestBody Disciplina disciplina) {

		Disciplina disciplinaEncontrada = disciplineService.getDisciplinas(id);
		disciplinaEncontrada.setNome(disciplina.getNome());

		if (disciplinaEncontrada != null) {
			return new ResponseEntity<Disciplina>(disciplineService.setNomeDisciplina(id, disciplinaEncontrada),
					HttpStatus.OK);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Disciplina nao encontrada");
		}
	}

	@PutMapping("/v1/api/disciplinas/{id}/nota")
	public ResponseEntity<Disciplina> setNotaDisciplina(@PathVariable int id, @RequestBody Disciplina disciplina) {
		Disciplina disciplinaEncontrada = disciplineService.getDisciplinas(id);
		disciplinaEncontrada.setNota(disciplina.getNota());

		if (disciplinaEncontrada != null) {
			return new ResponseEntity<Disciplina>(disciplineService.setNotaDisciplina(id, disciplinaEncontrada),
					HttpStatus.OK);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Disciplina nao encontrada");
		}
	}

	@DeleteMapping("/v1/api/disciplinas/{id}")
	public ResponseEntity<Disciplina> removeDisciplinaId(@PathVariable int id) {

		Disciplina disciplinaEncontrada = disciplineService.getDisciplinas(id);

		if (disciplinaEncontrada != null) {
			return new ResponseEntity<Disciplina>(disciplineService.deleteDisciplina(id), HttpStatus.OK);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Disciplina nao encontrada");
		}

	}

	@RequestMapping("/v1/api/disciplinas/ranking")
	public ResponseEntity<List<Disciplina>> getDisciplinesOrdered() {
		return new ResponseEntity<List<Disciplina>>(disciplineService.getDisciplinesOrdered(), HttpStatus.OK);
	}

}
