# 📦 DTO - Data Transfer Objects

Este módulo contém os **Data Transfer Objects (DTOs)** que representam as estruturas de dados recebidas da API externa TVMaze. Os DTOs são responsáveis por mapear os dados JSON da API para objetos Kotlin.

## 🎯 Objetivo

Os DTOs servem como uma camada de isolamento entre a API externa e o domínio da aplicação, permitindo:
- ✅ Desacoplamento da API externa
- ✅ Mapeamento seguro de dados
- ✅ Tratamento de campos opcionais
- ✅ Serialização/Deserialização automática

## 📋 DTOs Implementados

### 🎬 **MediaDto**
Representa uma série de TV com todas as informações básicas (ID, nome, imagem, gêneros, status, datas, rating, sinopse).

### 📺 **EpisodeDto**
Representa um episódio individual de uma série (ID, nome, temporada, número, imagem, resumo, data de exibição).

### 🖼️ **ImageDto**
Representa informações de imagem/poster (URLs medium e original).

### ⭐ **RatingDto**
Representa a avaliação de uma série (média de rating).

### 🔍 **MediaResultDto**
Wrapper para resultados de busca que contém a série e score de relevância.

## 🔧 Características Técnicas

### 📡 **Serialização**
- Utiliza **Kotlinx Serialization** para mapeamento JSON
- Anotações `@SerialName` para mapear nomes de campos
- Suporte a campos opcionais com tipos nullable

### 🛡️ **Segurança**
- Todos os campos são opcionais (`?`) para tratar dados inconsistentes da API
- Tratamento seguro de valores nulos
- Validação de tipos durante deserialização

### 🔄 **Mapeamento**
- DTOs são transformados em modelos de domínio através de **Mappers**
- Isolamento entre camada de dados e domínio
- Flexibilidade para mudanças na API

## 📊 Endpoints Relacionados

- **Busca de Séries**: `/search/shows?q={query}`
- **Detalhes da Série**: `/shows/{id}`
- **Episódios da Série**: `/shows/{id}/episodes`

---

[⬅️ Voltar ao Core](../README.md) | [📋 Ver Mappers](../mapper/README.md)