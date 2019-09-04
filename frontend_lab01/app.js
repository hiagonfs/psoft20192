console.log("app rodando!"); 

let URL = 'https://lab01-projsw-ufcg.herokuapp.com/api/disciplinas'; 

function fetch_disciplinas() {

    fetch(URL)
    .then(resposta => resposta.json())
    .then(dados => {
        console.log(dados);
    })

}

$p = document.querySelector('body');

window.fd = fetch_disciplinas;
