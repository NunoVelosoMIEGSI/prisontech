# prisontech
Projeto de DAI 2019/2020 - prisiontech

Comandos uteis

Hiptser

    jhipster (gerar aplicações (tem de ser corrido na pasta onde vai ser gerada a aplicação))

    jhipster --blueprints vuejs (gerar aplicações com Vuejs (tem de ser corrido na pasta onde vai ser gerada a aplicação))

    jhipster entity [nome da tabela] (cria tabelas a partir da linha de comandos)

    jhipster import-jdl [ficheiro_jdl.jdl] (cria tabelas a partir de um ficheiro jdl)

    ./gradlew (fazer a build de uma aplicação feita com gradle)

    ./mvnw (fazer a build de uma aplicação feita com maven)

    npm start (iniciar a aplicação com live reload (frontend))

    jhipster docker-compose (para gerar os docker containers da aplicação (tem de ser corrido na pasta onde vão ser gerados os
    containers da aplicação)) depois de gerar os containers é necessário executar os comandos que o jhipster indica

    ./gradlew -Pprod bootJar jibDockerBuild (para builds com gradle)



Docker

    docker image ls -a (para ver as imagens)

    docker-compose up -d (para iniciar os containers (tem de ser corrido na pasta onde estão criados))

    docker container ls -a (para ver os containers a correr)