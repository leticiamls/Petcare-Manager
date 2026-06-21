# 🐾 PetCare Manager

![Status do Projeto](https://img.shields.io/badge/Status-Concluído-success?style=for-the-badge)
![React](https://img.shields.io/badge/React-20232A?style=for-the-badge&logo=react&logoColor=61DAFB)
![Tailwind CSS](https://img.shields.io/badge/Tailwind_CSS-38B2AC?style=for-the-badge&logo=tailwind-css&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)

O **PetCare Manager** é um sistema web responsivo desenvolvido para modernizar e simplificar a gestão de clínicas veterinárias. O projeto une um backend seguro em Java com uma interface vibrante e interativa em React.

## 🎨 Identidade Visual (Neo-Playful)
A interface foi projetada utilizando a estética **Neo-Playful** (ou Neo-Brutalism leve). O design utiliza bordas pretas espessas, sombras sólidas deslocadas, cantos arredondados e cores vibrantes de alto contraste (Ciano e Bege) para criar um ambiente amigável, acessível e fácil de operar no dia a dia agitado de uma clínica.

## ✨ Funcionalidades e Controle de Acesso (RBAC)

O sistema possui inteligência de roteamento e renderização condicional baseada no cargo do usuário autenticado:

* 👑 **Administrador (ADMIN):**
    * Gerenciamento total da equipe da clínica (Cadastro/Inativação de Funcionários e Veterinários).
* 📋 **Funcionário (Recepção):**
    * Gestão de Clientes e Pets (Cadastro, Edição e Soft Delete).
    * Abertura e agendamento de novas Consultas.
    * Cancelamento de consultas.
* 🩺 **Veterinário (VET):**
    * Acesso exclusivo à sua própria agenda do dia.
    * Preenchimento de prontuário eletrônico (Sintomas, Diagnósticos e Medicamentos).
    * Finalização de consultas médicas.

## 🛠️ Tecnologias Utilizadas

**Front-end:**
* React (com Vite)
* TypeScript
* Tailwind CSS v4
* React Router DOM (Proteção de Rotas)
* Lucide React (Ícones)

**Back-end:**
* Java / Spring Boot
* Spring Security & JWT (JSON Web Tokens)
* Criptografia BCrypt
* Banco de Dados (H2 / MySQL)

## 🚀 Como executar o projeto localmente

### Pré-requisitos
Certifique-se de ter o **Node.js** e o **Java JDK** instalados em sua máquina.

### 1. Rodando o Back-end (Spring Boot)
1. Clone o repositório do backend.
2. Abra a pasta do projeto na sua IDE (Eclipse/IntelliJ).
3. Execute a classe principal `PetCareApplication.java`.
4. O servidor iniciará na porta `8080`.
*Nota: O banco de dados fará o "seed" automático de um usuário Administrador padrão na primeira execução.*

### 2. Rodando o Front-end (React)
1. Clone este repositório.
2. Abra o terminal na pasta do projeto e instale as dependências:
   ```bash
   npm install
