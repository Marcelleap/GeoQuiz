# GeoQuiz - Registro de Modificações (Changelog)

Este documento descreve as modificações feitas no projeto GeoQuiz original, as mudanças implementadas na nova versão, e as versões do Android Studio, Gradle, SDK e API utilizados em cada etapa do desenvolvimento.

## Repositório Original: [GeoQuiz Original](https://github.com/udofritzke/GeoQuiz)
**Autor:** Udo Fritzke  
**Link do Repositório:** https://github.com/udofritzke/GeoQuiz.<br>
**Descrição:** GeoQuiz é um aplicativo Android de perguntas e respostas para testar conhecimentos geográficos, baseado no exemplo do livro "Android Programming: The Big Nerd Ranch Guide".  
**Versão do Android Studio:** Não reportada 
**Funcionalidades:**
- Apresentação de perguntas com botões de resposta "Verdadeiro" ou "Falso".
- Exibição de feedback após responder (se correto ou incorreto).
- Contador de perguntas respondidas corretamente.
- Funcionalidade básica de navegação entre perguntas.

---

## Repositório Atual: [GeoQuiz Modificado](https://github.com/Marcelleap/GeoQuiz)
**Autor:** João Henriuqe dos Santos Ferreira, Marcelle Andrade Pereira e Vinicius Corrêa de Assis.  
**Descrição:** Esta versão do GeoQuiz contém modificações para aprimorar o registro e a manipulação das respostas dos usuários. Foram implementadas novas funcionalidades que expandem a experiência de uso do aplicativo.

### Modificações na versão atual:
**1. Cadastro Automático de Respostas**
   - O sistema agora registra automaticamente a resposta do usuário ao pressionar os botões 'VERDADEIRO' ou 'FALSO'.  
   - As respostas são salvas com as seguintes informações:
     - UUID da questão.
     - RESPOSTA_CORRETA (0 ou 1).
     - RESPOSTA_OFERECIDA (valor: verdadeiro ou falso).
     - COLOU (se o usuário utilizou algum artifício de cola ou não).

**2. Remoção do Botão 'CADASTRA'**
   - O botão 'CADASTRA', que anteriormente permitia o registro manual das respostas, foi removido, pois o cadastro agora ocorre automaticamente.

**3. Atualização do Botão 'MOSTRA'**
   - O botão 'MOSTRA' exibe agora todas as respostas cadastradas automaticamente. O usuário pode ver o histórico de respostas diretamente.

**4. Adição do Botão 'DELETA'**
   - Foi adicionado um botão 'DELETA', que permite excluir as respostas registradas, proporcionando controle adicional sobre o histórico de respostas.

### Novas Funcionalidades e Melhorias:
- **Aprimoramento do sistema de armazenamento de respostas:** O registro de respostas é mais eficiente e automatizado.
- **Interface simplificada:** Com a remoção do botão 'CADASTRA', a interface ficou mais limpa e fácil de usar.
- **Maior controle sobre o histórico:** A adição do botão 'DELETA' e a visualização pelo botão 'MOSTRA' permitem um gerenciamento mais fácil das respostas.

---

## Versões do Android Studio, Gradle, SDK e API Utilizados

### GeoQuiz Original
- **Android Studio 3.0.1**
  - Esta versão foi utilizada para o desenvolvimento inicial do GeoQuiz.
  - Primeira implementação das funcionalidades básicas de perguntas e respostas.
  - Suporte a APIs mais antigas do Android e configuração mínima de projeto.

### GeoQuiz Modificado
- **Android Studio 2024.1.2 (Coala)**
  - Implementação do cadastro automático de respostas e demais funcionalidades descritas.
  - Melhorias na compatibilidade com as versões mais recentes do Android.
  - Interface aprimorada com remoção e adição de botões para simplificação da experiência do usuário.

- **Gradle 8.6.1**
  - Utilizado para gerenciar as dependências do projeto, garantindo maior desempenho e compatibilidade com as versões atuais do Android Studio.

- **SDK 24 e API 33**
  - **SDK 24** como versão mínima de compatibilidade para dispositivos mais antigos.
  - **API 33** para suporte às funcionalidades mais recentes e compatibilidade com dispositivos modernos.
---

### Histórico de Versões

- **Versão 4.0.0**  
  - Implementação do cadastro automático de respostas.
  - Remoção do botão 'CADASTRA'.
  - Atualização da funcionalidade do botão 'MOSTRA'.
  - Adição do botão 'DELETA'.  
  - **Android Studio 2024.1.2 (Coala), Gradle 8.6.1, SDK 24 e API 33**

- **Versão 3.0.0**  
  - Baseado na versão original, com funcionalidades de quiz e navegação básica de perguntas e respostas.  
  - **Android Studio X.X.X - (Não repostada)**
