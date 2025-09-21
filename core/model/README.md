# 📋 Model - Modelos de Domínio

Este módulo contém os **modelos de domínio** que representam as entidades de negócio do aplicativo TV Maze. Os modelos são organizados em sub-módulos para melhor organização e responsabilidade.

## 🎯 Objetivo

Os modelos de domínio servem como:
- ✅ **Contrato de negócio** entre camadas
- ✅ **Entidades puras** sem dependências externas
- ✅ **Validação de regras** de negócio
- ✅ **Tipagem forte** para segurança

## 📁 Sub-módulos

### 🏗️ common/
**Modelos Comuns** - Entidades principais do domínio da aplicação.

### 📊 data_status/
**Data Status** - Wrapper para estados de carregamento de dados.

### 🎨 theme/
**Temas** - Modelos relacionados ao sistema de temas.

## 🔧 Características Técnicas

### 🛡️ **Imutabilidade**
- Todas as classes são **data classes**
- Propriedades **read-only** quando possível
- **Copy** para modificações

### 📊 **Estados de Dados**
- Uso de `DataStatus<T>` para estados de carregamento
- Tratamento elegante de estados Loading/Error/Success
- Extensões para mapeamento de estados

### 🔄 **Serialização**
- Suporte a **Kotlinx Serialization**
- Anotações para persistência
- Compatibilidade com JSON

## 📋 Modelos Principais

### 🎬 **MediaItemModel**
Representa séries de TV com todas as informações (ID, nome, imagem, gêneros, datas, estrelas, sinopse, favorito).

### 📺 **EpisodeModel**
Representa episódios com informações (ID, mediaId, nome, temporada, número, imagem, assistido).

### 🖼️ **ImageModel**
Representa imagens com URLs medium e original.

### ⭐ **StarsModel**
Representa avaliações em estrelas (rating, estrelas preenchidas, total de estrelas).

## 📊 **DataStatus Wrapper**

### 🎯 **Propósito**
O `DataStatus<T>` é um wrapper que representa o estado de carregamento de dados (Loading, Loaded, Error).

### ✅ **Benefícios**
- **Estados explícitos** de carregamento
- **Tratamento unificado** de erros
- **Type safety** para dados carregados
- **Extensões** para mapeamento e transformação

## 🔗 Dependências

- **Kotlinx Serialization** - Para serialização
- **Kotlinx Coroutines** - Para estados assíncronos
- **Core Utils** - Para funções auxiliares

---

[⬅️ Voltar ao Core](../README.md) | [🔄 Ver Mappers](../mapper/README.md) | [🌐 Ver Network](../network/README.md)
