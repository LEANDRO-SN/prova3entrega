const BASE_URL = 'http://localhost:8080/api/futebol';

function getProdutoFromForm() {
    const nome = document.getElementById('nome').value;
    const preco = document.getElementById('preco').value;
    const quantidade = document.getElementById('quantidade').value;
    const status = document.getElementById('status').value;
    return { nome, preco: parseFloat(preco), quantidade: parseInt(quantidade), status };
}

async function listarTodos() {
    const response = await fetch(BASE_URL);
    const produtos = await response.json();
    const lista = document.getElementById('lista-produtos');
    const total = document.getElementById('total-produtos');
    lista.innerHTML = '';
    produtos.forEach(produto => {
        const item = document.createElement('li');
        item.textContent = `ID: ${produto.id} | Nome: ${produto.nome} | Preço: ${produto.preco} | Qtd: ${produto.quantidade} | Status: ${produto.status}`;
        lista.appendChild(item);
    });
    total.textContent = `Total de produtos: ${produtos.length}`;
}

async function salvar() {
    const produto = getProdutoFromForm();
    await fetch(BASE_URL, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(produto)
    });
    listarTodos();
    limparCampos();
}

async function atualizar() {
    const id = document.getElementById('id').value;
    if (!id) { alert("Informe o ID para atualizar!"); return; }

    const produto = getProdutoFromForm();
    try {
        const response = await fetch(`${BASE_URL}/${id}`, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(produto)
        });
        if (!response.ok) throw new Error('Erro na atualização');
        listarTodos();
        limparCampos();
    } catch (error) {
        console.error(error);
        alert("Erro ao atualizar.");
    }
}

async function deletar() {
    const id = document.getElementById('id').value;
    if (!id) { alert("Informe o ID para deletar!"); return; }
    await fetch(`${BASE_URL}/${id}`, { method: 'DELETE' });
    listarTodos();
    limparCampos();
}

function consultar() {
    const id = document.getElementById('id').value;
    if (!id) { alert("Informe o ID para consultar!"); return; }

    fetch(`${BASE_URL}/${id}`)
        .then(res => {
            if (!res.ok) throw new Error('Não encontrado');
            return res.json();
        })
        .then(prod => {
            document.getElementById('nome').value = prod.nome || '';
            document.getElementById('preco').value = prod.preco || '';
            document.getElementById('quantidade').value = prod.quantidade || '';
            document.getElementById('status').value = prod.status || '';
        })
        .catch(e => { alert("Produto não encontrado!"); });
}

function limparCampos() {
    document.getElementById('id').value = '';
    document.getElementById('nome').value = '';
    document.getElementById('preco').value = '';
    document.getElementById('quantidade').value = '';
    document.getElementById('status').value = '';
}

document.addEventListener('DOMContentLoaded', listarTodos);