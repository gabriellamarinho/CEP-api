#CEP-api

Este projeto é um buscador de endereços com base no CEP (Código de Endereçamento Postal). Utiliza a API pública do ViaCEP para buscar informações sobre o endereço correspondente a um CEP informado. O sistema permite que o usuário consulte um endereço de forma simples e rápida através de uma interface de linha de comando (CLI).
Funcionalidades

    Consulta de endereço via CEP, utilizando a API do ViaCEP.
    Validação do formato do CEP (deve conter exatamente 8 dígitos numéricos).
    Exibe o endereço completo ou uma mensagem de erro caso o CEP não seja encontrado.

Tecnologias Utilizadas

    Java 17 (ou superior)
    Spring Boot (para integração e criação de componentes)
    RestTemplate (para realizar a chamada HTTP à API do ViaCEP)
    Scanner (para interação com o usuário na linha de comando)

Como Rodar o Projeto
1. Clonando o Repositório

Para começar, clone o repositório:

git clone https://github.com/seu-usuario/CEP-api.git
