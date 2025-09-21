# 🧠 Core - Módulos Compartilhados

Este diretório contém todos os módulos compartilhados que fornecem funcionalidades fundamentais para o aplicativo TV Maze. Estes módulos são utilizados por todas as features e garantem a consistência e reutilização de código.

## 📁 Estrutura dos Módulos Core

### 📦 [DTO](./dto/README.md)
**Data Transfer Objects** - Objetos de transferência de dados que representam as estruturas de dados vindas da API externa.

### 🔄 [Mapper](./mapper/README.md)
**Mappers** - Responsáveis por transformar DTOs em modelos de domínio e vice-versa.

### 📋 [Model](./model/README.md)
**Modelos de Domínio** - Representam as entidades de negócio do aplicativo.

### 🌐 [Network](./network/README.md)
**Networking** - Cliente HTTP e configurações de rede para comunicação com APIs externas.

### 🔧 [Provider](./provider/README.md)
**Providers** - Módulos que fornecem funcionalidades de infraestrutura.

### 🛠️ [Utils](./utils/README.md)
**Utilitários** - Funções auxiliares e extensões que podem ser reutilizadas em todo o projeto.

## 🎯 Principais Responsabilidades

1. **📡 Comunicação com APIs** - Cliente HTTP configurado para TVMaze API
2. **🗄️ Persistência de Dados** - Room Database para dados locais
3. **⚙️ Configurações** - DataStore para preferências do usuário
4. **🔄 Transformação de Dados** - Mappers entre camadas
5. **📦 Injeção de Dependências** - Configuração do Koin
6. **🛠️ Utilitários Comuns** - Funções auxiliares reutilizáveis

## 🔗 Dependências

- **Ktor** - Cliente HTTP
- **Room** - Banco de dados local
- **DataStore** - Armazenamento de preferências
- **Koin** - Injeção de dependências
- **Kotlinx Serialization** - Serialização JSON
- **Kotlinx Coroutines** - Programação assíncrona

---

[⬅️ Voltar ao README Principal](../README.md)