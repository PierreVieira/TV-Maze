# 🎯 ComposeApp - Aplicação Principal

Este é o **módulo principal** da aplicação TV Maze, responsável por inicializar e coordenar todos os componentes do sistema usando Jetpack Compose Multiplatform.

## 🎯 Objetivo

O ComposeApp fornece:
- ✅ **Ponto de entrada** multiplataforma
- ✅ **Inicialização** do Koin
- ✅ **Configuração** de temas
- ✅ **Navegação raiz** da aplicação
- ✅ **Integração** de todas as features

## 🏗️ Estrutura

### 📱 **App.kt**
Componente principal que configura tema, navegação e providers.

### 🔧 **AppViewModel**
ViewModel responsável pelo gerenciamento de estado global da aplicação.

### 🧭 **Navigation Setup**
Configuração do sistema de navegação principal com todas as rotas.

## 🔧 Funcionalidades Principais

### 🎨 **Gerenciamento de Temas**
Aplica temas de forma consistente em toda a aplicação.

### 🧭 **Navegação Raiz**
Configura e gerencia toda a navegação da aplicação.

### 📱 **Adaptação de Plataforma**
Adapta a interface para diferentes plataformas (Android, iOS, Desktop).

### 🔄 **Estado Global**
Mantém estados compartilhados entre diferentes telas.

## 🔗 Dependências

- **All Core Modules** - Funcionalidades essenciais
- **All Feature Modules** - Todas as funcionalidades
- **All UI Modules** - Componentes de interface
- **Koin** - Injeção de dependências
- **Navigation Compose** - Sistema de navegação

## 🚀 Targets Suportados

- **Android** - Smartphones e tablets
- **iOS** - iPhone e iPad  
- **Desktop** - Windows, macOS, Linux

---

[⬅️ Voltar ao README Principal](../README.md)