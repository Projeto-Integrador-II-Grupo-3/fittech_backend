# FIT TECH | Backend do Projeto Integrador

Este repositório contém o backend do 2º Desafio do Projeto Integrador do Bootcamp de Desenvolvimento Fullstack da Generation Brasil.
O objetivo do projeto é fornecer uma API robusta para gerenciar os dados e funcionalidades da aplicação, implementando boas práticas de desenvolvimento e segurança.

## Funcionalidades

- Cadastro e autenticação de usuários.
- CRUD completo para:
  - Entidades principais do sistema.
  - Relacionamentos entre entidades.
- Rotas protegidas para operações que requerem autenticação.
- Validação de dados de entrada.

## Estrutura do Projeto

- **Entidades:**
  - Usuários, produtos, pedidos (ou outras entidades específicas do projeto).
- **Segurança:**
  - Implementação de autenticação e autorização com JWT.
- **Repositórios:**
  - Gerenciamento de entidades com Spring Data JPA.
- **Controladores:**
  - Endpoints REST para comunicação com o frontend.

## Tecnologias Utilizadas

- **Linguagem:** Java 11+
- **Framework:** Spring Boot
- **Banco de Dados:** MySQL
- **Segurança:** Spring Security com JWT
- **Ferramentas:** Maven, Postman, IntelliJ IDEA / Eclipse

## Como Executar o Projeto

1. **Clone o Repositório:**
   ```bash
   git clone https://github.com/Projeto-Integrador-II-Grupo-3/backend2.git
   ```

2. **Configure o Banco de Dados:**
   - Certifique-se de que o MySQL está instalado e em execução.
   - Atualize o arquivo `application.properties` com as credenciais do banco de dados.

3. **Instale as Dependências:**
   ```bash
   ./mvnw install
   ```

4. **Execute o Projeto:**
   ```bash
   ./mvnw spring-boot:run
   ```

5. **Teste a API:**
   Utilize o Postman ou outra ferramenta para interagir com os endpoints.

## Contribuindo

1. Faça um fork deste repositório.
2. Crie um branch para sua feature ou correção:
   ```bash
   git checkout -b minha-feature
   ```
3. Commit suas alterações:
   ```bash
   git commit -m "Adiciona nova funcionalidade"
   ```
4. Envie para o branch principal:
   ```bash
   git push origin minha-feature
   ```
5. Abra um Pull Request no GitHub.

## Licença

Este projeto está sob a licença MIT. Consulte o arquivo `LICENSE` para mais detalhes.

## Desenvolvedores

Este projeto foi desenvolvido em colaboração pelos integrantes do Grupo 3:

- Tiago Alves (PO)
- Fatima Machado (Tester)
- Daffne Vieira Rodrigues (Desenvolvedora)
- Robert Matheus (Desenvolvedor)
- Kaue Oliveira (Desenvolvedor)
- João Vitor Bispo (Desenvolvedor)
- Thalita Alves Simão (Desenvolvedora)


---

