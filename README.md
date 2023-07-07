# Processo Seletivo Manager

Processo Seletivo Manager é um projeto simples em Java construído para manejar candidatos que estão em um processo de vários estágios. A aplicação
validate o nomes, maneja o progresso através de várias fases e provê o status de cada candidato. O projeto utiliza
principios SOLID para uma programação orientada a objetos, procurando focar em escalabilidade e qualidade de código.



## Getting Started

Para rodar, siga os passos abaixo:

### Prerequisites

* JDK (Java Development Kit)

### Installation

1. Clone o repositório
   ```sh
   git clone https://github.com/your_username_/SegundoTeste.git
   ```
   
2. Abra na IDE

### Utilização
Para utilizar o projeto, importe as seguintes classes:

```java
import segundoteste.candidatos.Candidato;
import segundoteste.errors.;
import segundoteste.fases.;
import segundoteste.validators.nameValidator.*;
```

Crie uma instância da classe Segundo:

```java
Segundo processManager = new Segundo();
```

Agora você pode começar a utilizar os methods providos. Por exemplo, para iniciar o processo para um candidato:

```java
int candidateCode = processManager.iniciarProcesso("Candidate Name");
```

Para marcar uma entrevista:

```java
processManager.marcarEntrevista(candidateCode);
```

E para aprovar um candidato:

```java
processManager.aprovarCandidato(candidateCode);
```

Você pode checkar o status do candidato com:

```java
String status = processManager.verificarStatusCandidato(candidateCode);
```

Para obter uma lista de todos os candidatos aprovados:

```java
List<String> approvedCandidates = processManager.obterAprovados();
```

### Classes Overview

Esse projeto possui as seguintes classes:

  - **Segundo** - A classe core, que implementa a interface**IProcessManager**. Maneja of processo de lidar coms os candidatos através das fases. Também utiliza **Validators** para checkar a conformidade dos nomes com determinados padrões.
    
  <br>
  
  - **AbsFase** - Classe base abstrata para diferentes fases do processo (**Recebidos**, **Qualificados**, **Aprovados**). Ela maneja os candidatos dentro de cada fase.
    
  <br>
  
  - **Candidato** - Representa a entidade Candidato. Contém informações sobre o candidato e sua fase atual.

  <br>
  
  - **Validator** and its implementations (**ValidatorNaoVazio**, **ValidatorCaracteres**, **ValidatorTamanho**) - Utiliza Chain
      of Responsability para validar o nome do candidato.
    
  <br>

  - **Errors package** - Contém errors padrões para alguns cenários de erros frequentes na aplicação.
  <br>

### Built With

  [Java](https://www.java.com/)

### Contact

Alan Martini - [@linkedin](https://www.linkedin.com/in/alangmartini/) - gmartinialan@gmail.com

Project Link: [https://github.com/alangmartini/BootCamp-SegundoTeste-ProcessoSeletivo-Java](https://github.com/alangmartini/BootCamp-SegundoTeste-ProcessoSeletivo-Java)

Todo e qualquer feedback é muito vindo!
