function create_turma(disciplina, vagas) {

  return {

    qtdVagas : vagas,
    nome : disciplina,
    matriculados : [],

    matricule : function (aluno) { 
      if (this.vagas() > 0) { 
        this.matriculados.push(aluno);  
        return true;
      }
      else 
        return false; 
    },

    alunos : function () { return this.matriculados; },
    vagas : function () { return this.qtdVagas - (this.matriculados.length); }

  };

}

let atal = create_turma("atal", 3);

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
