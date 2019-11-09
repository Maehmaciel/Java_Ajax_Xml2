function criarAjaxGet(servlet, funcao)
{
    ajax = new XMLHttpRequest();
    ajax.onreadystatechange = funcao;
    ajax.open("GET", servlet, true);
    ajax.send();
}

function criarAjaxDelete(servlet, funcao)
{
    ajax = new XMLHttpRequest();
    ajax.onreadystatechange = funcao;
    ajax.open("GET", servlet, true);
    ajax.send();
}


function criarAjaxPost(servlet, recurso)
{


    ajax = new XMLHttpRequest();
    ajax.onreadystatechange = function () {

        if (this.readyState == 4 && this.status == 200)
        {
            criarAjaxGet("controle", mostrar)
        }
    }
    ajax.open("POST", servlet, true);
    ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    ajax.send("dados=" + recurso);
    console.log(recurso)
}
function mostrar()
{
    index=document.getElementById('la')
    index.innerHTML=null
    if (this.readyState == 4 && this.status == 200)
    {
        doc = this.responseXML;

        raiz = doc.documentElement;
        texto = "";
        filhos = raiz.childNodes;
        tam = filhos.length;
        j = 1;
        for (i = 0; i < tam; i++)
        {
            
                if (filhos[i].nodeType == 1)
                {
                    
                    li=document.createElement('li')
                    li.innerHTML=filhos[i].children[1].childNodes[0].nodeValue + ": " + filhos[i].children[2].childNodes[0].nodeValue
                    
                    apagar=document.createElement('a')
                            apagar.innerHTML='apagar'
                    editar=document.createElement('a')
                            editar.innerHTML='Editar'
                    apagar.href="apagar("+j+")";
                    editar.href="editar("+j+")";
                    li.appendChild(apagar)
                    li.appendChild(editar)
                    li.innerHTML+="<hr>"
                    li.id=j
                   index.appendChild(li)
                   j++
                }
            

        }
    }
}


function criarPessoa()
{
    
    raiz = doc.documentElement;
    x = doc.getElementsByTagName("root")[0]

    nome = document.getElementById('nome').value;
    telefone = document.getElementById('telefone').value;

    pessoa = doc.createElement("Pessoa");
    acaoXml = doc.createElement("acao");
    acaoTxtXml = doc.createTextNode("Inserir");
    acaoXml.appendChild(acaoTxtXml)
    pessoa.appendChild(acaoXml)
    nomeXml = doc.createElement("nome");
    nomeTxtXml = doc.createTextNode(nome);
    nomeXml.appendChild(nomeTxtXml)
    pessoa.appendChild(nomeXml)
    telefoneXml = doc.createElement("telefone");
    telefoneTxtXml = doc.createTextNode(telefone);
    telefoneXml.appendChild(telefoneTxtXml);
    pessoa.appendChild(telefoneXml)


    x.appendChild(pessoa)
    recurso = x.innerHTML
    criarAjaxPost("controle",recurso)
}


function apagar(){
    
}

function editar(){
    
}

