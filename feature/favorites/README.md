# ❤️ Favorites - Sistema de Favoritos

Este módulo implementa o **sistema de favoritos**, permitindo aos usuários salvar suas séries preferidas e gerenciá-las em uma lista dedicada.

## 🎯 Objetivo

A feature de favoritos fornece:
- ✅ **Lista de séries favoritas**
- ✅ **Adicionar/remover favoritos**
- ✅ **Persistência local**
- ✅ **Sincronização** entre telas
- ✅ **Interface dedicada**

## 🏗️ Arquitetura

### 📊 **Data Layer**
- **FavoritesRepository** - Repositório de favoritos
- **FavoriteMediasDao** - Acesso aos dados locais
- **FavoriteMappers** - Transformação de dados

### 🧠 **Domain Layer**
- **GetFavorites** - Recuperar lista de favoritos
- **ToggleFavorite** - Adicionar/remover favorito
- **IsFavorite** - Verificar status de favorito

### 🎨 **Presentation Layer**
- **FavoritesViewModel** - Gerenciamento de estado
- **FavoritesScreen** - Interface principal
- **FavoritesList** - Lista de favoritos

## 🔧 Funcionalidades Principais

### 📱 **Lista de Favoritos**
Exibe todas as séries marcadas como favoritas pelo usuário.

### ❤️ **Toggle de Favoritos**
Permite adicionar/remover séries dos favoritos de qualquer tela.

### 💾 **Persistência Local**
Armazena favoritos localmente usando Room Database.

### 🔄 **Sincronização**
Mantém status de favorito sincronizado entre todas as telas.

## 🔗 Dependências

- **Core Room** - Persistência local
- **Core Model** - Modelos de domínio
- **UI Components** - Componentes reutilizáveis
- **Navigation** - Sistema de navegação

---

[⬅️ Voltar às Features](../README.md) | [🔍 Ver Search](../search/README.md) | [📋 Ver Episodes](../episodes/README.md)