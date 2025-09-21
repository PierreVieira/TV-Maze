# 🧭 Navigation - Sistema de Navegação

Este módulo contém o **sistema de navegação** unificado do aplicativo TV Maze, construído com Navigation Compose para fornecer navegação tipada e segura entre telas.

## 🎯 Objetivo

O sistema de navegação fornece:
- ✅ **Navegação tipada** e segura
- ✅ **Deep linking** support
- ✅ **Argument passing** tipado
- ✅ **Navigation state** management
- ✅ **Back stack** handling

## 🗺️ Estrutura de Navegação

### 📍 **Rotas Principais**
- **SearchRoute** - Tela de busca
- **MediaDetailsRoute** - Detalhes da série (com mediaId)
- **FavoritesRoute** - Lista de favoritos
- **EpisodesRoute** - Lista de episódios (com mediaId)

### 🎯 **NavGraph Principal**
Gerencia toda a navegação da aplicação com diferentes grafos para cada feature.

## 📋 Grafos de Navegação

### 🔍 **Search Graph**
Navegação da tela de busca e funcionalidades relacionadas.

### 📺 **Media Details Graph**
Navegação para detalhes da série e episódios.

### ❤️ **Favorites Graph**
Navegação para lista de favoritos.

### 📋 **Episodes Graph**
Navegação para lista de episódios de uma série.

## 🔧 Funcionalidades Avançadas

### 🔗 **Deep Linking**
Suporte a deep linking para acessar telas específicas.

### 📱 **Platform-Specific Navigation**
Navegação adaptada para diferentes plataformas (Android, iOS, Desktop).

### 🎯 **Type-Safe Navigation**
Navegação tipada com argumentos seguros para evitar erros.

## 🔗 Dependências

- **Navigation Compose** - Sistema de navegação
- **Jetpack Compose** - Framework de UI
- **Core Model** - Modelos de domínio
- **Feature Modules** - Módulos de features

---

[⬅️ Voltar ao UI](../README.md) | [🧩 Ver Components](../components/README.md) | [🎨 Ver Theme](../theme/README.md)