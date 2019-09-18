package disciplineControl.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Disciplina implements Comparable<Disciplina> {

	private String nome;
	private double nota;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String comentarios;
	private int likes;

	public Disciplina(String nome, float nota) {
		super();
		this.nome = nome;
		this.nota = nota;
	}

	public Disciplina(String nome) {
		super();
		this.nome = nome;
	}

	public Disciplina(String nome, double nota, long id, String comentarios, int likes) {
		super();
		this.nome = nome;
		this.nota = nota;
		this.id = id;
		this.comentarios = comentarios;
		this.likes = likes;
	}

	public Disciplina() {
		super();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getNota() {
		return nota;
	}

	public void setNota(double nota) {
		this.nota = nota;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Disciplina other = (Disciplina) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public int compareTo(Disciplina d) {
		if (this.getNota() > d.getNota()) {
			return -1;
		} else if (this.getNota() < d.getNota()) {
			return 1;
		}
		return 0;
	}

	@Override
	public String toString() {
		return "Disciplina [nome=" + nome + ", id=" + id + "]";
	}

}
