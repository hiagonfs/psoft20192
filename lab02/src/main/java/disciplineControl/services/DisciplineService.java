package disciplineControl.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import disciplineControl.entities.Disciplina;
import disciplineControl.repository.DisciplinaRepository;

@Service
public class DisciplineService {

	private DisciplinaRepository<Disciplina, Long> disciplinaRepositoryDAO;

	public DisciplineService(DisciplinaRepository<Disciplina, Long> disciplinasDAO) {
		super();
		this.disciplinaRepositoryDAO = disciplinasDAO;
	}

	@PostConstruct
	public void initDisciplines() throws Exception {

		ObjectMapper mapper = new ObjectMapper();
		TypeReference<List<Disciplina>> typeReference = new TypeReference<List<Disciplina>>() {
		};
		InputStream input = TypeReference.class.getResourceAsStream("/json/disciplinas.json");
		try {
			List<Disciplina> disciplinas = mapper.readValue(input, typeReference);
			this.disciplinaRepositoryDAO.saveAll(disciplinas);
		} catch (IOException e) {
			throw new Exception("A mensagem eh: " + e.getMessage());
		}

	}

	public Disciplina addDisciplina(Disciplina newDiscipline) {
		return this.disciplinaRepositoryDAO.save(newDiscipline);
	}

	public List<Disciplina> getDisciplinas() {
		return disciplinaRepositoryDAO.findAll();
	}

	public Optional<Disciplina> getDisciplina(Long id) {
		return this.disciplinaRepositoryDAO.findById(id);
	}

	public Optional<Disciplina> removeDisciplina(Long id) {
		Optional<Disciplina> d = getDisciplina(id);
		if (d.isPresent()) {
			this.disciplinaRepositoryDAO.deleteById(id);
		}
		return d;
	}

	public Optional<Disciplina> setNomeDisciplina(Long id, Disciplina newD) {
		Optional<Disciplina> d = getDisciplina(id);
		if (d.isPresent()) {
			d.get().setNome(newD.getNome());
		}
		return d;
	}

	public Optional<Disciplina> setNotaDisciplina(Long id, Disciplina newD) {
		Optional<Disciplina> d = getDisciplina(id);
		if (d.isPresent()) {
			d.get().setNota(newD.getNota());
		}
		return d;
	}

	public List<Disciplina> getDisciplinesOrdered() {
		Collections.sort(this.disciplinaRepositoryDAO.findAll());
		return getDisciplinas();
	}

}
