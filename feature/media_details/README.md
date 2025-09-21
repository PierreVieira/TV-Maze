# 📺 Media Details - Detalhes da Série

Este módulo implementa a funcionalidade de **visualização detalhada** de séries de TV, incluindo informações completas, gestão de favoritos e navegação para episódios.

## 🎯 Objetivo

A feature de detalhes fornece:
- ✅ **Informações completas** da série
- ✅ **Gestão de favoritos** (adicionar/remover)
- ✅ **Navegação para episódios**
- ✅ **Interface responsiva**
- ✅ **Estados de loading** e erro

## 🏗️ Arquitetura

### 📊 **Data Layer**
- **MediaDetailsRepository** - Repositório de detalhes
- **MediaDetailsDataSource** - Fonte de dados da API
- **FavoritesDao** - Gestão de favoritos local

### 🧠 **Domain Layer**
- **GetMediaDetails** - Recuperar detalhes da série
- **ToggleFavorite** - Alternar estado de favorito
- **CheckFavoriteStatus** - Verificar se é favorito

### 🎨 **Presentation Layer**
- **MediaDetailsViewModel** - Gerenciamento de estado
- **MediaDetailsScreen** - Interface principal
- **MediaInfoCard** - Componente de informações

## 🔧 Funcionalidades Principais

### 📱 **Informações da Série**
Exibe poster, título, sinopse, gêneros, rating, datas e status.

### ❤️ **Gestão de Favoritos**
Permite adicionar/remover séries dos favoritos com feedback visual.

### 📋 **Navegação para Episódios**
Botão para navegar para a lista de episódios da série.

### 📱 **Design Responsivo**
Layout que se adapta a diferentes tamanhos de tela.

## 🔗 Dependências

- **Core Network** - Cliente HTTP
- **Core Room** - Persistência local
- **Core Model** - Modelos de domínio
- **UI Components** - Componentes reutilizáveis
- **Navigation** - Sistema de navegação

---

[⬅️ Voltar às Features](../README.md) | [🔍 Ver Search](../search/README.md) | [📋 Ver Episodes](../episodes/README.md)