# 🔧 Provider - Infraestrutura e Configurações

Este módulo contém os **Providers** que fornecem funcionalidades de infraestrutura essenciais para o aplicativo, incluindo banco de dados, armazenamento de preferências e injeção de dependências.

## 🎯 Objetivo

Os Providers garantem:
- ✅ **Configuração centralizada** de infraestrutura
- ✅ **Abstração** de implementações específicas de plataforma
- ✅ **Injeção de dependências** organizada
- ✅ **Testabilidade** com mocks e fakes
- ✅ **Manutenibilidade** de configurações

## 📁 Sub-módulos

### 🗄️ room/
**Room Database** - Configuração e gerenciamento do banco de dados local.

### 💾 data_store/
**DataStore** - Armazenamento de preferências e configurações.

### 🏗️ koin/
**Koin** - Configuração de injeção de dependências.

## 🔧 Componentes Principais

### 🗄️ **Room Database**

#### **AppDatabase**
Database principal com entidades: SearchHistoryItemEntity, FavoriteMediaEntity, WatchedEpisodeEntity.

#### **DAOs**
- **LastSearchesDao** - Operações de histórico
- **FavoriteMediasDao** - Operações de favoritos
- **WatchedEpisodesDao** - Operações de episódios

### 💾 **DataStore**

#### **Configuração Multiplataforma**
Implementações específicas para Android, iOS e Desktop.

#### **Preferências Armazenadas**
- **Theme** - Tema selecionado (Light/Dark/Material You)
- **Search Bar Position** - Posição da barra de busca
- **User Settings** - Configurações do usuário

### 🏗️ **Koin Configuration**

#### **Módulos Principais**
Módulos para Core, Features e configurações específicas de plataforma.

#### **Inicialização**
Configuração centralizada de injeção de dependências.

## 🔄 Fluxo de Dados

### 📊 **Persistência Local**
```
Domain Model
    ↓
Repository
    ↓
DAO/DataStore
    ↓
Room/DataStore
    ↓
Local Storage
```

### 🔄 **Injeção de Dependências**
```
Application Start
    ↓
Koin Initialization
    ↓
Module Registration
    ↓
Dependency Injection
    ↓
Feature Usage
```

## 🔗 Dependências

### 🗄️ **Room**
- **Room Runtime** - Database core
- **Room Compiler** - Code generation
- **SQLite Bundled** - SQLite for KMP

### 💾 **DataStore**
- **DataStore Core** - Preferences storage
- **DataStore Preferences** - Preferences implementation

### 🏗️ **Koin**
- **Koin Core** - Dependency injection
- **Koin Android** - Android integration
- **Koin Compose** - Compose integration

---

[⬅️ Voltar ao Core](../README.md) | [🌐 Ver Network](../network/README.md) | [🛠️ Ver Utils](../utils/README.md)
