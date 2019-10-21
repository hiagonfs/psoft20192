function disciplina(id, nome, creditos, pre_requisitos) {

    let _id = id; 
  
      _disciplina = {
  
        // atributos
          id: _id,
          nome : nome,
          creditos : creditos,
          pre_requisitos: [],
          
          // functions
          set_id: (novoId) => id = novoId,
          get_id: () => id,
          set_nome: (novoNome) => nome = novoNome,
          get_nome: () => nome,
          addPreRequisito: (pre_requisito) => pre_requisitos.push(pre_requisito)

  
      };
  
      return _disciplina; 
  
  }
  