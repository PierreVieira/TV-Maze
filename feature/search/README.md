# 🔍 Search - Busca de Séries

Este módulo implementa a funcionalidade de **busca por séries** de TV, incluindo histórico de buscas e integração com a API TVMaze.

## 🎯 Objetivo

A feature de busca fornece:
- ✅ **Busca em tempo real** por séries
- ✅ **Histórico de buscas** recentes
- ✅ **Sugestões** de pesquisa
- ✅ **Estados de loading** e erro
- ✅ **Integração com favoritos**

## 🏗️ Arquitetura

### 📊 **Data Layer**
- **SearchRepository** - Implementação do repositório de busca
- **SearchDataSource** - Fonte de dados da API TVMaze
- **SearchHistoryDao** - Acesso ao histórico local

### 🧠 **Domain Layer**
- **SearchUseCase** - Lógica de busca principal
- **SaveRecentSearch** - Salvar busca recente
- **GetSearchHistory** - Recuperar histórico

### 🎨 **Presentation Layer**
- **SearchViewModel** - Gerenciamento de estado
- **SearchScreen** - Interface principal
- **SearchBar** - Componente de busca

## 🔧 Funcionalidades Principais

### 🔍 **Busca por Séries**
Permite buscar séries por nome com resultados em tempo real.

### 📝 **Histórico de Buscas**
Mantém histórico das últimas buscas realizadas pelo usuário.

### ⭐ **Integração com Favoritos**
Mostra status de favorito diretamente nos resultados de busca.

### 🔄 **Estados de UI**
Gerencia estados de loading, sucesso, erro e lista vazia.

## 🔗 Dependências

- **Core Network** - Cliente HTTP
- **Core Room** - Persistência local
- **Core Model** - Modelos de domínio
- **UI Components** - Componentes reutilizáveis

---

[⬅️ Voltar às Features](../README.md) | [📺 Ver Media Details](../media_details/README.md) | [❤️ Ver Favorites](../favorites/README.md)