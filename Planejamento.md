# Android DDM

## Aula 1: Introdução ao Android e Configuração do Ambiente

**Conteúdo:**

- **História do Android:**
  - Evolução do Android e suas principais versões.
  - Características e funcionalidades do sistema operacional.
  
- **Ecossistema Android:**
  - Google Play Store e distribuição de aplicativos.

- **Arquitetura do Android:**
  - Visão geral das camadas do sistema operacional Android.
  - Componentes principais: Activities, Services, Broadcast Receivers, Content Providers.

- **Configuração do Ambiente de Desenvolvimento:**
  - Instalação do Android Studio e configuração do SDK.
  - Api Levels e compatibilidade com diferentes versões.
  - Configuração do emulador e conexão com dispositivos físicos(ADB).
  - Visão geral da estrutura de um projeto Android.

**Atividade Prática:**

- Criar um novo projeto no Android Studio.
- Desenvolver uma aplicação simples "Hello World" em Java.
- Alterar o aplicativo para ser um contador de cliques.
- Testar o aplicativo no emulador e em um dispositivo físico.

- Explorar rapidamente a internacionalização (i18n) alterando o idioma do aplicativo.
- Familiarização com o Android Studio, emulador e ferramentas de design.
   

## Aula 2: Primeiros Passos com Activities e Intents

**Conteúdo:**

- **Activities:**
  - Conceito de Activity no Android.
  - Detecção de eventos do ciclo de vida.
  

- **Intents:**
  - Intents explícitos e implícitos.
  - Navegação entre Activities.

- **Internacionalização (i18n) e Localização (l10n):**
  
  - Gerenciamento de recursos de strings para diferentes idiomas.

**Atividade Prática:**

- Criar múltiplas Activities e navegar entre elas usando Intents.
- Adaptar a aplicação "Hello World" para suportar múltiplos idiomas.
- Testar a aplicação em diferentes configurações de idioma do dispositivo.

## Aula 3: Layouts e Interface de Usuário

**Conteúdo:**

- **Layouts Básicos:**
  - LinearLayout, RelativeLayout, ConstraintLayout.

- **Widgets e Componentes de Interface:**
  - TextView, EditText, Button, ImageView.

- **Design Responsivo:**
  - Adaptando layouts para diferentes tamanhos de tela.
  - Uso de dimensões e recursos de layout.

**Atividade Prática:**

- Desenvolver uma aplicação "Contador de Clicks".
- Implementar um botão que incrementa um contador ao ser clicado.
- Personalizar a interface utilizando estilos e temas básicos.
- Aplicar conceitos de i18n na interface criada.

## Aula 4: Introdução a Services e Broadcast Receivers

**Conteúdo:**

- **Services:**
  - Conceito e tipos de Services (Foreground, Background).
  - Ciclo de vida de um Service.

- **Broadcast Receivers:**
  - Conceito e uso de Broadcast Receivers.
  - Registrando Receivers estática e dinamicamente.

- **Intents e Comunicações:**
  - Envio e recepção de broadcasts.
  - Uso de Intents para comunicação entre componentes.

**Atividade Prática:**

- Implementar um Service simples que executa em segundo plano.
- Criar um Broadcast Receiver que responde a mudanças no estado do dispositivo (ex: bateria, conectividade).
- Integrar o Service e o Receiver à aplicação existente.

## Aula 5: Ciclo de Vida Avançado e Gerenciamento de Estado

**Conteúdo:**

- **Ciclo de Vida das Activities:**
  - Métodos de callback: onCreate, onStart, onResume, onPause, onStop, onDestroy, onRestart.
  - Gerenciamento de estado e mudanças de configuração (ex: rotação da tela).

- **Salvando e Restaurando Estado:**
  - Uso de onSaveInstanceState e onRestoreInstanceState.
  - Persistência de dados durante mudanças de configuração.

**Atividade Prática:**

- Modificar o aplicativo "Contador de Clicks" para salvar e restaurar o estado do contador.
- Implementar logs nos métodos de ciclo de vida para entender a sequência de chamadas.
- Testar o comportamento da aplicação durante rotações e mudanças de idioma.

## Aula 6: Arquitetura MVVM e Data Binding

**Conteúdo:**

- **Padrão Arquitetural MVVM:**
  - Conceito e benefícios do Model-View-ViewModel.
  - Separação de responsabilidades entre camadas.

- **Data Binding no Android:**
  - Configuração do Data Binding.
  - Ligação de dados entre a View e o ViewModel.

- **ViewModel:**
  - Criação e uso do ViewModel.
  - Ciclo de vida do ViewModel em relação à Activity.

**Atividade Prática:**

- Refatorar o aplicativo "Contador de Clicks" para seguir o padrão MVVM.
- Implementar Data Binding para atualizar a interface em resposta às mudanças no ViewModel.
- Garantir que a internacionalização seja mantida após as mudanças.

## Aula 7: Content Providers e Persistência de Dados

**Conteúdo:**

- **Content Providers:**
  - Conceito e funcionalidade.
  - Acesso a dados entre aplicativos.
  - URI e ContentResolver.

- **Persistência de Dados:**
  - Visão geral das opções de armazenamento (SharedPreferences, SQLite, arquivos).
  - Quando utilizar Content Providers.

**Atividade Prática:**

- Acessar contatos ou imagens do dispositivo usando Content Providers.
- Implementar um Content Provider personalizado para compartilhar dados entre aplicativos.
- Explorar permissões necessárias para acessar Content Providers.

## Aula 8: SharedPreferences e Armazenamento de Configurações

**Conteúdo:**

- **SharedPreferences:**
  - Armazenamento e recuperação de dados primitivos.
  - Modos de acesso e boas práticas.

- **Configurações do Aplicativo:**
  - Salvando preferências do usuário.
  - Implementação de telas de configurações.

**Atividade Prática:**

- Modificar o aplicativo "Contador de Clicks" para salvar o contador usando SharedPreferences.
- Criar uma tela de configurações que permita ao usuário alterar preferências (ex: tema, idioma).
- Aplicar as preferências salvas na inicialização do aplicativo.

## Aula 9: Banco de Dados SQLite e Repositórios

**Conteúdo:**

- **Banco de Dados SQLite:**
  - Conceitos básicos e vantagens de usar SQLite.
  - Estrutura de um banco de dados relacional.

- **SQLiteOpenHelper:**
  - Criação e atualização de bancos de dados.
  - Gerenciamento de versões do banco de dados.

- **Repository Pattern:**
  - Separação da lógica de acesso a dados.
  - Integração com o MVVM.

**Atividade Prática:**

- Criar um aplicativo de lista de tarefas com banco de dados SQLite.
- Implementar a camada de dados seguindo o padrão Repository.
- Utilizar o ViewModel para interagir com o Repository.

## Aula 10: Operações CRUD com SQLite

**Conteúdo:**

- **Operações CRUD:**
  - Create, Read, Update, Delete.
  - Uso de ContentValues e Cursor.

- **Consultas e Filtragem de Dados:**
  - Consultas parametrizadas.
  - Evitando SQL Injection.

**Atividade Prática:**

- Expandir o aplicativo de lista de tarefas para incluir funcionalidades de edição e exclusão.
- Implementar busca e filtragem de tarefas.
- Garantir que a aplicação suporte múltiplos idiomas nas entradas do usuário.

## Aula 11: RecyclerView e Listagens Avançadas

**Conteúdo:**

- **RecyclerView:**
  - Vantagens sobre ListView.
  - Estrutura básica: Adapter, ViewHolder.

- **Data Binding com RecyclerView:**
  - Ligação de dados em itens da lista.
  - Atualização eficiente da lista.

- **LiveData e Observers:**
  - Introdução ao LiveData.
  - Observando mudanças nos dados no ViewModel.

**Atividade Prática:**

- Implementar o RecyclerView no aplicativo de lista de tarefas.
- Utilizar LiveData para atualizar a lista em tempo real.
- Aplicar estilos e layouts personalizados aos itens da lista.

## Aula 12: Serviços em Profundidade

**Conteúdo:**

- **Foreground Services:**
  - Quando e como utilizar.
  - Notificações associadas a serviços em primeiro plano.

- **Background Services e Restrições:**
  - Limitações em segundo plano nas versões recentes do Android.
  - Uso do WorkManager para tarefas em segundo plano.

- **Comunicação entre Services e Activities:**
  - Bound Services.
  - Messenger e AIDL (Introdução).

**Atividade Prática:**

- Implementar um serviço que executa tarefas periódicas em segundo plano.
- Mostrar notificações ao usuário sobre o status das tarefas.
- Integrar o serviço ao aplicativo de lista de tarefas.

## Aula 13: Broadcast Receivers e Eventos do Sistema

**Conteúdo:**

- **Broadcast Receivers Detalhados:**
  - Recebendo broadcasts do sistema e de outros aplicativos.
  - Diferenciação entre broadcasts estáticos e dinâmicos.

- **Eventos Comuns do Sistema:**
  - Mudanças na conectividade de rede.
  - Eventos de bateria e energia.
  - Detecção de instalação/desinstalação de aplicativos.

**Atividade Prática:**

- Criar um Broadcast Receiver que responda a mudanças na conectividade de rede.
- Implementar ações no aplicativo baseadas nos broadcasts recebidos.
- Gerenciar permissões necessárias para receber certos broadcasts.

## Aula 14: Internacionalização e Localização Avançadas

**Conteúdo:**

- **Internacionalização (i18n):**
  - Estratégias para criar aplicativos que suportem múltiplos idiomas.
  - Recursos específicos para diferentes idiomas e regiões.

- **Localização (l10n):**
  - Formatadores de dados baseados na localização (datas, números, moedas).
  - Exibição de conteúdo adaptado à região do usuário.

**Atividade Prática:**

- Expandir a aplicação existente para suportar mais idiomas e regiões.
- Adicionar formatos de dados localizados à interface.
- Testar a aplicação em diferentes configurações regionais.

## Aula 15: Acesso a Arquivos e Permissões

**Conteúdo:**

- **Acesso a Arquivos:**
  - Leitura e escrita de arquivos no armazenamento interno e externo.
  - Gerenciamento de permissões para acesso a arquivos.

- **Permissões Dinâmicas:**
  - Solicitação de permissões em tempo de execução.
  - Gerenciamento de permissões sensíveis (armazenamento, câmera).

**Atividade Prática:**

- Implementar funcionalidades para salvar e ler arquivos na aplicação de lista de tarefas.
- Solicitar e gerenciar permissões necessárias para acessar arquivos.
- Explorar a integração com o sistema de arquivos do dispositivo.

## Aula 16: Integração com APIs Externas

**Conteúdo:**

- **Consumo de APIs REST:**
  - Uso do Retrofit para chamadas de API.
  - Manipulação de respostas JSON.

- **Autenticação e Segurança:**
  - Implementação de autenticação em APIs.
  - Boas práticas para segurança de dados.

**Atividade Prática:**

- Integrar a aplicação de lista de tarefas com uma API externa para sincronização de dados.
- Implementar autenticação usando tokens.
- Exibir dados recebidos da API na interface do aplicativo.

## Aula 17: Testes e Qualidade de Código

**Conteúdo:**

- **Testes Unitários e Instrumentação:**
  - Uso do JUnit e Espresso para testes de unidades e de interface.
  - Estratégias para garantir qualidade e robustez do código.

- **Testes de Interface do Usuário (UI):**
  - Automação de testes de UI com Espresso.
  - Boas práticas para garantir uma experiência de usuário consistente.

**Atividade Prática:**

- Escrever testes unitários para a aplicação de lista de tarefas.
- Implementar testes de interface com Espresso.
- Revisar e melhorar a cobertura de testes da aplicação.

## Aula 18: Performance e Otimização

**Conteúdo:**

- **Otimização de Desempenho:**
  - Monitoramento e análise de desempenho com Android Profiler.
  - Identificação e resolução de problemas de desempenho.

- **Boas Práticas de Desenvolvimento:**
  - Técnicas para manter o aplicativo rápido e responsivo.
  - Gerenciamento eficiente de recursos e memória.

**Atividade Prática:**

- Analisar o desempenho da aplicação de lista de tarefas.
- Aplicar técnicas de otimização para melhorar a resposta da aplicação.
- Monitorar o uso de memória e identificar possíveis melhorias.

## Aula 19: Publicação e Distribuição

**Conteúdo:**

- **Preparação para Publicação:**
  - Criação de um APK e assinatura digital.
  - Configuração de variantes de build e configurações de release.

- **Distribuição:**
  - Publicação na Google Play Store.
  - Gerenciamento de versões e atualizações.

**Atividade Prática:**

- Preparar a aplicação para publicação criando um APK assinado.
- Configurar e realizar o upload na Google Play Store.
- Testar a publicação e atualização da aplicação.

## Aula 20: Revisão e Projeto Final

**Conteúdo:**

- **Revisão dos Conceitos:**
  - Revisão dos principais tópicos abordados durante o curso.
  - Discussão sobre boas práticas e padrões de desenvolvimento.

- **Projeto Final:**
  - Desenvolvimento de um projeto final integrando os conceitos aprendidos.
  - Apresentação e revisão dos projetos.

**Atividade Prática:**

- Finalizar o projeto integrador que utiliza todos os conceitos abordados no curso.
- Apresentar o projeto para a turma e realizar uma revisão crítica.
- Receber feedback e realizar ajustes finais.

