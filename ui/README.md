# 🎨 UI - Componentes de Interface

Este diretório contém todos os componentes de UI reutilizáveis, sistema de navegação, temas e utilitários de interface do aplicativo TV Maze. Todos os componentes são construídos com Jetpack Compose Multiplatform.

## 🎯 Objetivo

O módulo UI fornece:
- ✅ **Componentes reutilizáveis** em Compose
- ✅ **Sistema de navegação** unificado
- ✅ **Temas e cores** consistentes
- ✅ **Utilitários de UI** comuns
- ✅ **Design system** padronizado

## 📁 Estrutura dos Módulos UI

### 🧩 [components/](./components/README.md)
**Componentes Reutilizáveis** - Elementos de UI que podem ser utilizados em múltiplas telas.

### 🧭 [navigation/](./navigation/README.md)
**Sistema de Navegação** - Navegação unificada entre telas usando Navigation Compose.

### 🎨 [theme/](./theme/README.md)
**Sistema de Temas** - Temas, cores e tipografia do aplicativo.

### 🛠️ [utils/](./utils/README.md)
**Utilitários de UI** - Funções auxiliares e extensões para Compose.

## 🔧 Componentes Principais

### 🎬 **MediaItemCard**
Card responsivo para exibir séries de TV com poster, título e informações básicas.

### 🖼️ **AsyncPicture**
Componente de imagem com loading automático, fallbacks e efeitos visuais.

### ⭐ **StarsRating**
Componente de avaliação em estrelas com suporte a diferentes tamanhos.

### 🔘 **IconButton**
Botão customizado com ícone e diferentes estilos.

### ✨ **ShimmerEffect**
Efeito de loading shimmer para melhorar a percepção de performance.

## 🧭 Sistema de Navegação

### 🗺️ **NavGraph Principal**
Gerencia a navegação entre todas as telas da aplicação.

### 📍 **Rotas Definidas**
- **SearchRoute** - Tela de busca
- **MediaDetailsRoute** - Detalhes da série
- **FavoritesRoute** - Lista de favoritos
- **EpisodesRoute** - Lista de episódios

### 🔗 **Navegação Tipada**
Navegação segura com argumentos tipados para evitar erros.

## 🎨 Sistema de Temas

### 🌈 **Paleta de Cores**
Esquemas de cores para temas claro e escuro.

### 📝 **Tipografia**
Hierarquia de textos consistente seguindo Material 3.

## 🔗 Dependências

- **Jetpack Compose Multiplatform** - Framework de UI
- **Navigation Compose** - Sistema de navegação
- **Material 3** - Design system
- **Coil** - Carregamento de imagens
- **Core Model** - Modelos de domínio

---

[⬅️ Voltar ao README Principal](../README.md) | [🧠 Ver Core](../core/README.md) | [⚡ Ver Features](../feature/README.md)