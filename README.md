# API - Processo Seletivo Manager

API Processo Seletivo Manager é a implementação em formato API de um [projeto simples em Java](https://github.com/alangmartini/BootCamp-SegundoTeste-ProcessoSeletivo-Java) construído para manejar candidatos que estão em um processo de vários estágios. A aplicação valida os nomes, maneja o progresso através de várias fases e provê o status de cada candidato. O projeto utiliza
principios SOLID para uma programação orientada a objetos, procurando focar em escalabilidade e qualidade de código.


## Getting Started

Para rodar, siga os passos abaixo:

### Prerequisites

* JDK (Java Development Kit)

### Installation

1. Clone o repositório
   ````sh
   git clone git@github.com:alangmartini/BootCamp-TerceiroTeste-API-ProcessoSeletivo-Java.git
   ````
   
2. Instale as dependências  do maven
````sh
  mvn clean install
````

3. Inicialize o projeto
````sh
  mvn spring-boot:run
````

## Endpoints

### Iniciar Processo Seletivo
*POST /api/v1/hiring/start*

Inicia o processo com um novo candidato

#### Request Body

```
{
   "nome": "Fulano"
}
```

#### Responses

201 **OK**:
```
{
   "codCandidato": 1
}
```

400 **BAD REQUEST**:
```
Candidato ja participa do processo.
```

### Marcar Entrevista
*POST /api/v1/hiring/schedule*

Marca entrevista para um candidato

#### Request Body

```
{
   "codCandidato": integer
}
```

#### Responses

200 **OK**:
```
{
   "message": "Entrevista Marcada"
}
```

400 **BAD REQUEST**
```
Candidato nao encontrado
```

### Desqualificar candidato
*POST /api/v1/hiring/disqualify*

Desqualifica um candidato

#### Request Body

```
{
   "codCandidato": integer
}
```

#### Responses

200 **OK**:
```
{
   "message": "Candidato Desqualificado"
}
```

400 **BAD REQUEST**
```
   Candidato nao encontrado
```

### Aprovar candidato
*POST /api/v1/hiring/approve*

Aprova um candidato

#### Request Body

```
{
   "codCandidato": integer
}
```

#### Responses

200 **OK**:
```
{
   "message": "Candidato Aprovado"
}
```

400 **BAD REQUEST**
```
Candidato nao encontrado
```


### Resetar Processo Seletivo
*DELETE /api/v1/hiring/reset*

Reseta o Processo Seletivo, limpando todos os candidatos de todas as fases.

#### Responses

200 **OK**:
```
{
   "message": "Processo Resetado"
}
```

### Get Status do Candidato
*GET /api/v1/hiring/status/candidate/{id}*

Requere o status  do candidato.

#### Parameters

id: integer (required) - Candidate ID.

#### Responses

200 **OK**:
```
{
   "message": "Status: StatusDoCandido"
}
```

400 **BAD REQUEST**
```
Candidato nao encontrado
```

### Get Approved Candidates
*GET /api/v1/hiring/approved*

Gets all approved candidates.

#### Responses

200 **OK**:
```
{
"message": ["string1", "string2", ...]
}
```

200 **OK**:
```
{
"message": []
}
```


### Built With

  [Java](https://www.java.com/)
  [Spring](https://spring.io/projects/spring-boot)

### Contact

Alan Martini - [@linkedin](https://www.linkedin.com/in/alangmartini/) - gmartinialan@gmail.com

Project Link: [https://github.com/alangmartini/BootCamp-TerceiroTeste-API-ProcessoSeletivo-Java](https://github.com/alangmartini/BootCamp-TerceiroTeste-API-ProcessoSeletivo-Java)

Projeto do serviço do Processo Seletivo: [https://github.com/alangmartini/BootCamp-SegundoTeste-ProcessoSeletivo-Java](https://github.com/alangmartini/BootCamp-SegundoTeste-ProcessoSeletivo-Java)

Todo e qualquer feedback é muito vindo!
