# 🔄 Mapper - Transformação de Dados

Este módulo contém os **Mappers** responsáveis por transformar dados entre diferentes camadas da aplicação, principalmente de DTOs (Data Transfer Objects) para modelos de domínio e vice-versa.

## 🎯 Objetivo

Os Mappers garantem:
- ✅ **Separação de responsabilidades** entre camadas
- ✅ **Transformação segura** de dados
- ✅ **Flexibilidade** para mudanças na API
- ✅ **Reutilização** de lógica de mapeamento
- ✅ **Testabilidade** isolada

## 📋 Mappers Implementados

### 🎬 **MediaResultMapper**
Transforma resultados de busca da API em modelos de domínio.

### 📺 **EpisodeMapper**
Transforma episódios da API em modelos de domínio.

### 🖼️ **ImageMapper**
Transforma dados de imagem da API em modelos de domínio.

### ⭐ **StarsMapper**
Transforma avaliações da API em componentes de UI.

### ❤️ **FavoriteShowMapper**
Mapeia entre entidades de favoritos e modelos de domínio.

## 🔧 Características Técnicas

### 🛡️ **Tratamento de Erros**
- **Retorno nullable** para dados inválidos
- **Filtros automáticos** de dados inconsistentes
- **Logs de erro** para debugging
- **Fallbacks** para campos opcionais

### 🔄 **Bidirecionalidade**
- Mapeamento **DTO → Domain**
- Mapeamento **Domain → Entity** (para persistência)
- Mapeamento **Entity → Domain** (para recuperação)

## 🔗 Dependências

- **Core DTO** - Para tipos de entrada
- **Core Model** - Para tipos de saída
- **Core Utils** - Para funções auxiliares

## 📊 Fluxo de Dados

```
API Response (JSON)
        ↓
    DTO Layer
        ↓
    Mapper Layer  ← Transformação
        ↓
    Domain Model
        ↓
    Business Logic
```

---

[⬅️ Voltar ao Core](../README.md) | [📦 Ver DTOs](../dto/README.md) | [📋 Ver Models](../model/README.md)