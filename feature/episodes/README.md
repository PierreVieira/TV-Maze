# 📋 Episodes - Gestão de Episódios

Este módulo implementa a funcionalidade de **gestão de episódios**, permitindo visualizar e marcar episódios como assistidos, organizados por temporadas.

## 🎯 Objetivo

A feature de episódios fornece:
- ✅ **Lista de episódios** por temporada
- ✅ **Marcar como assistido** individual
- ✅ **Organização por temporadas**
- ✅ **Persistência local** do progresso
- ✅ **Interface intuitiva**

## 🏗️ Arquitetura

### 📊 **Data Layer**
- **EpisodesRepository** - Repositório de episódios
- **EpisodesDataSource** - Fonte de dados da API
- **WatchedEpisodesDao** - Gestão local de assistidos

### 🧠 **Domain Layer**
- **GetEpisodes** - Recuperar episódios da série
- **ToggleEpisodeWatched** - Marcar/desmarcar como assistido
- **GetWatchedEpisodes** - Recuperar progresso local

### 🎨 **Presentation Layer**
- **EpisodesViewModel** - Gerenciamento de estado
- **EpisodesScreen** - Interface principal
- **SeasonSection** - Componente de temporada
- **EpisodeRow** - Item individual de episódio

## 🔧 Funcionalidades Principais

### 📺 **Lista de Episódios**
Exibe todos os episódios organizados por temporadas com informações básicas.

### ✅ **Marcar como Assistido**
Permite marcar/desmarcar episódios individuais como assistidos.

### 📊 **Organização por Temporadas**
Agrupa episódios por temporada com headers expansíveis.

### 💾 **Persistência Local**
Salva o progresso localmente usando Room Database.

## 🔗 Dependências

- **Core Network** - Cliente HTTP
- **Core Room** - Persistência local
- **Core Model** - Modelos de domínio
- **UI Components** - Componentes reutilizáveis

---

[⬅️ Voltar às Features](../README.md) | [📺 Ver Media Details](../media_details/README.md) | [❤️ Ver Favorites](../favorites/README.md)