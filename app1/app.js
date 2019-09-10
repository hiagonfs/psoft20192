console.log("app rodando!"); 

let disciplinas = []; 

const endereço = 'https://lab01-projsw-ufcg.herokuapp.com/api/disciplinas'; 

function fetch_disciplinas(dados) {

    disciplinas = dados;
    let $disciplinas = document.querySelector("div"); 
    $disciplinas.innerHTML = ''; 
    disciplinas.forEach((e,i) => {
        let $p = document.createElement("p");
        $disciplinas.appendChild($p); 
        $p.innerText = "nome da disciplina: " + disciplinas[i].nome + ", nota: " + disciplinas[i].nota;  
    }) 
}

(function run() {
    fetch(endereço)
    .then(r => r.json())
    .then(fetch_disciplinas)
}()); 

