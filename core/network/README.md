# 🌐 Network - Cliente HTTP e Networking

Este módulo contém toda a infraestrutura de rede necessária para comunicação com APIs externas, especificamente a API TVMaze. Utiliza Ktor como cliente HTTP principal.

## 🎯 Objetivo

O módulo Network fornece:
- ✅ **Cliente HTTP configurado** com Ktor
- ✅ **Tratamento de erros** robusto
- ✅ **Configurações de timeout** e retry
- ✅ **Serialização JSON** automática
- ✅ **Logging** de requisições
- ✅ **Testabilidade** com mocks

## 🔧 Componentes Principais

### 🌐 **HttpClient**
Cliente HTTP configurado com todas as dependências necessárias.

**Características:**
- **Base URL** configurada para TVMaze API
- **Timeout** de 10 segundos para requisições
- **Content-Type** JSON automático
- **Serialização** configurada com tolerância a erros

### 🛡️ **RequestHandler**
Handler responsável por executar requisições HTTP com tratamento de erros.

**Funcionalidades:**
- **Wrapper Result** para tratamento de erros
- **Logging** automático de requisições
- **Retry logic** para falhas temporárias
- **Validação** de status codes

## 📡 Endpoints da API

- **Busca de Séries**: `/search/shows?q={query}`
- **Detalhes da Série**: `/shows/{id}`
- **Episódios da Série**: `/shows/{id}/episodes`

## ⚙️ Configurações

### 🔗 **Base URL**
```
https://api.tvmaze.com
```

### ⏱️ **Timeouts**
- **Request Timeout**: 10 segundos
- **Connect Timeout**: 10 segundos
- **Socket Timeout**: 10 segundos

## 🛡️ Tratamento de Erros

### 📋 **Tipos de Erro**
- **NetworkException** - Falhas de conectividade
- **HttpException** - Erros HTTP (4xx, 5xx)
- **SerializationException** - Erros de deserialização
- **TimeoutException** - Timeouts de requisição

## 🔗 Dependências

- **Ktor Client Core** - Cliente HTTP base
- **Ktor Client Content Negotiation** - Serialização JSON
- **Ktor Client Mock** - Para testes
- **Kotlinx Serialization JSON** - Serialização
- **Kotlinx Coroutines** - Programação assíncrona

## 📊 Fluxo de Requisição

```
Repository
    ↓
RequestHandler
    ↓
HttpClient
    ↓
TVMaze API
    ↓
JSON Response
    ↓
Serialization
    ↓
Domain Model
```

---

[⬅️ Voltar ao Core](../README.md) | [📦 Ver DTOs](../dto/README.md) | [🔄 Ver Mappers](../mapper/README.md)