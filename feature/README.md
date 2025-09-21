# ⚡ Feature - Funcionalidades da Aplicação

Este diretório contém todas as **features** da aplicação TV Maze, organizadas seguindo a arquitetura Clean Architecture com separação clara entre camadas de dados, domínio e apresentação.

## 🎯 Objetivo

Os módulos de feature garantem:
- ✅ **Separação de responsabilidades** por funcionalidade
- ✅ **Arquitetura Clean** bem definida
- ✅ **Testabilidade** isolada
- ✅ **Manutenibilidade** simplificada
- ✅ **Reutilização** de código

## 📁 Estrutura das Features

### 🔍 [search/](./search/README.md)
**Busca de Séries** - Funcionalidade de busca por séries de TV com histórico.

### 📺 [media_details/](./media_details/README.md)
**Detalhes da Série** - Visualização detalhada de informações de uma série.

### 📋 [episodes/](./episodes/README.md)
**Gestão de Episódios** - Lista e gerenciamento de episódios assistidos.

### ❤️ [favorites/](./favorites/README.md)
**Sistema de Favoritos** - Gerenciamento de séries favoritas do usuário.

### 🎨 [theme_selection/](./theme_selection/README.md)
**Seleção de Temas** - Interface para escolha de temas da aplicação.

### 🎭 [material_you/](./material_you/README.md)
**Material You** - Implementação do Material You para personalização.

### 🏠 [main/](./main/README.md)
**Tela Principal** - Orchestração principal da aplicação.

## 🏗️ Arquitetura por Feature

Cada feature segue a estrutura de Clean Architecture:

### 📊 **Data Layer**
- **Repositories** - Implementação de repositórios
- **DataSources** - Fontes de dados (API, Database)
- **Mappers** - Transformação de dados

### 🧠 **Domain Layer**  
- **Use Cases** - Regras de negócio
- **Repositories** - Contratos de repositórios
- **Models** - Entidades de domínio

### 🎨 **Presentation Layer**
- **ViewModels** - Lógica de apresentação
- **Screens** - Interfaces de usuário
- **Components** - Componentes específicos da feature

## 🔄 Fluxo de Dados

```
UI (Presentation)
    ↓
ViewModel
    ↓
Use Case (Domain)
    ↓
Repository (Data)
    ↓
DataSource (Network/Local)
```

## 🔗 Dependências Comuns

- **Core Modules** - Funcionalidades compartilhadas
- **UI Components** - Componentes reutilizáveis
- **Navigation** - Sistema de navegação
- **Koin** - Injeção de dependências

---

[⬅️ Voltar ao README Principal](../README.md) | [🧠 Ver Core](../core/README.md) | [🎨 Ver UI](../ui/README.md)