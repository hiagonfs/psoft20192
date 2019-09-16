package disciplineControl.services;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import disciplineControl.entities.Disciplina;
import disciplineControl.repository.DisciplinaRepository;

@Service
public class DisciplineService {

	@Autowired
	private DisciplinaRepository<Disciplina, Long> disciplinaRepositoryDAO;

	public DisciplineService(DisciplinaRepository<Disciplina, Long> disciplinasDAO) {
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
		} catch (Exception e) {
			throw new Exception("Erro no cadastro de alunos!");
		}

	}

	public Disciplina addDisciplina(Disciplina newDiscipline) {
		return this.disciplinaRepositoryDAO.save(newDiscipline);
	}

	public List<Disciplina> getDisciplinas() {
		return disciplinaRepositoryDAO.findAll();
	}

	private void removeDisciplina(int id) {

	}

	public Disciplina setNomeDisciplina(int id, Disciplina d) {
		removeDisciplina(id);
		disciplinas.add(d);
		return d;
	}

	public Disciplina setNotaDisciplina(int id, Disciplina d) {
		removeDisciplina(id);
		disciplinas.add(d);
		return d;
	}

	public List<Disciplina> getDisciplinesOrdered() {
		Collections.sort(disciplinas);
		return disciplinas;
	}

	public Optional<Disciplina> getDisciplina(Long id) {
		return this.disciplinaRepositoryDAO.findById(id);
	}

}
