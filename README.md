# 📚 Biblioteca Virtual - API RESTful

GRUAPIM - IFSP GRU
Eytor de Sousa Lima - GU3045307
João Ruffino dos Santos -GU3060195
Thiago Morales Ribeiro - GU3042766

API desenvolvida para gerenciar uma biblioteca virtual com funcionalidades de empréstimo e devolução de livros, com controle de acesso por autenticação JWT. O projeto segue os princípios REST e busca oferecer uma experiência simples, segura e eficiente tanto para usuários leitores quanto para administradores da plataforma.

---

## 🎯 Objetivo e Público-Alvo da API

Esta API foi criada como solução para o tema "Biblioteca Virtual com Empréstimos e Devoluções", focando no controle de acervo e circulação de livros em ambiente digital.

**Público-alvo:**
- Alunos e professores de cursos de tecnologia
- Desenvolvedores interessados em aprender REST APIs com autenticação
- Instituições que desejam digitalizar o processo de empréstimos de acervo

---

## ✅ Funcionalidades Implementadas

### 🔐 Autenticação e Autorização
- Cadastro de usuários (nome, e-mail, senha)
- Login com geração de token JWT
- Controle de acesso por papéis: `Usuário` e `Administrador`
- Acesso restrito aos próprios empréstimos (exceto admins)

### 📚 Livros
- Cadastro, edição e remoção de livros (admin)
- Listagem de livros disponíveis (todos os usuários)
- Visualização da disponibilidade dos livros
- Visualização de detalhes dos livros

### 📖 Empréstimos e Devoluções
- Solicitação de empréstimo de livros disponíveis
- Registro da devolução de livros emprestados
- Impedimento de empréstimo caso o livro esteja indisponível
- Histórico pessoal de empréstimos e devoluções (usuário)
- Acompanhamento geral de movimentações (admin)

### 📊 Consultas e Controle (admin)
- Identificação de livros mais emprestados
- Relatórios de uso por período
- Registro automático de data de empréstimo e prazo de devolução

---

## 🛠️ Execução Local

### ✅ Pré-requisitos
- Java 17+
- Maven ou Gradle
- Banco de dados relacional (ex: H2, MySQL)
- IDE (IntelliJ, VS Code ou Eclipse)

### ⚙️ Build e Execução

```bash
# Clone o repositório
git clone https://github.com/seu-usuario/biblioteca-api.git
cd biblioteca-api

# Compile e rode
./mvnw spring-boot:run
