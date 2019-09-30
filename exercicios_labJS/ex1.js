function create_turma(disciplina, vagas) {

  let d = {};
  d.qtdVagas = vagas;
  d.nome = disciplina;
  d.matriculados = []; 
  
  d.matricule = function (aluno) { 
    if (d.vagas() > 0) { 
        d.matriculados.push(aluno);  
        return true;
    }
    else 
      return false; 
  }; 

  d.alunos = function () { return d.matriculados; }
  d.vagas = function () { return d.qtdVagas - (d.matriculados.length); }

  return d; 
}

let atal = create_turma("atal", 1);

atal.matricule("abdc123");

atal.matricule("abdc133");
atal.matricule("abdc143");
atal.matricule("abdc12663");
atal.matricule("abdc123gg");
atal.matricule("abdc123aa");
atal.matricule("abdc123ddd");

console.log(atal); 

console.log(atal.alunos());

console.log(atal.vagas()); 
