# TV Maze - Aplicativo de Séries de TV

- Vídeo demonstrativo das features: https://youtu.be/bbFSuiOfIx4
- APK: https://drive.google.com/file/d/1NY3esYOI4AJiQHf1jIJTT1zkR7roh-_5/view?usp=sharing

Este é um projeto **Kotlin Multiplatform**/**Compose Multiplatform** que tem como objetivo criar uma experiência interativa e completa para explorar, pesquisar e gerenciar séries de TV. O aplicativo utiliza a API pública [The TV Maze](https://www.tvmaze.com/api) para fornecer informações detalhadas sobre séries e episódios.

## 🎯 O que o projeto faz?

O **TV Maze** é um aplicativo moderno que permite aos usuários:

- 🔍 **Pesquisar séries de TV** usando uma barra de busca intuitiva
- 📺 **Visualizar detalhes completos** de séries (sinopse, gêneros, rating, etc.)
- 📋 **Explorar episódios** organizados por temporada
- ✅ **Marcar episódios como assistidos** com persistência local
- ❤️ **Favoritar séries** para acesso rápido
- 🎨 **Personalizar temas** (Light/Dark + Material You)
- 📱 **Experiência multiplataforma** (Android, iOS, Desktop)

## ✨ Funcionalidades Implementadas

### 🔍 **Busca de Séries**
- Barra de busca responsiva com busca assíncrona
- Lista de resultados com poster, título e ano
- Histórico de buscas com persistência local
- Gerenciamento de histórico (deletar buscas individuais ou todas)

### 📺 **Detalhes da Série**
- Informações completas: poster, sinopse, gêneros, rating, datas
- Lista de episódios agrupados por temporada
- Interface responsiva (suporte a tablets e desktop)
- Layout adaptativo para diferentes tamanhos de tela

### ✅ **Gestão de Episódios**
- Checkbox para marcar episódios como "assistidos"
- Persistência local com Room Database
- Estado mantido ao navegar entre telas
- Sincronização em tempo real

### ❤️ **Sistema de Favoritos**
- Marcar séries como favoritas
- Lista dedicada de favoritos
- Integração com resultados de busca
- Persistência local

### 🎨 **Sistema de Temas**
- Suporte a Material You (Android 12+)
- Tema claro e escuro
- Personalização de cores
- Persistência de preferências

### 🔄 **Arquitetura Multiplataforma**
- Lógica de negócio compartilhada entre plataformas
- Implementações específicas por plataforma quando necessário
- Suporte completo a Android, iOS e Desktop (JVM)

## 🏗️ Arquitetura

O projeto segue os princípios da **Clean Architecture** com uma estrutura modular bem definida:

```
📦 TVMaze
├── 🎯 [composeApp/](./composeApp/README.md)          # Aplicação principal
├── 🧠 [core/](./core/README.md)                     # Módulos compartilhados
│   ├── [dto/](./core/dto/README.md)                 # Data Transfer Objects
│   ├── [mapper/](./core/mapper/README.md)           # Mappers entre camadas
│   ├── [model/](./core/model/README.md)             # Modelos de domínio
│   ├── [network/](./core/network/README.md)         # Cliente HTTP e networking
│   ├── [provider/](./core/provider/README.md)       # Providers (Room, DataStore, Koin)
│   └── [utils/](./core/utils/README.md)             # Utilitários
├── 🎨 [ui/](./ui/README.md)                         # Componentes de UI reutilizáveis
│   ├── [components/](./ui/components/README.md)     # Componentes Compose
│   ├── [navigation/](./ui/navigation/README.md)     # Sistema de navegação
│   ├── [theme/](./ui/theme/README.md)               # Temas e cores
│   └── [utils/](./ui/utils/README.md)               # Utilitários de UI
└── ⚡ [feature/](./feature/README.md)               # Features organizadas por funcionalidade
    ├── [search/](./feature/search/README.md)        # Busca de séries
    ├── [media_details/](./feature/media_details/README.md) # Detalhes da série
    ├── [episodes/](./feature/episodes/README.md)    # Gestão de episódios
    ├── [favorites/](./feature/favorites/README.md)  # Sistema de favoritos
    └── [theme_selection/](./feature/theme_selection/README.md) # Seleção de temas
```

### 🧪 Testes Unitários

O projeto inclui **testes unitários** abrangentes que demonstram as habilidades de testabilidade:

- ✅ **Use Cases** - Testes da lógica de negócio
- ✅ **Repositories** - Testes das camadas de dados
- ✅ **Mappers** - Testes de transformação de dados
- ✅ **ViewModels** - Testes da camada de apresentação
- ✅ **Utils** - Testes de funções utilitárias

**Estratégia de Testes:**
- Uso de **mocks e fakes** para isolamento
- Testes de **casos de sucesso e erro**
- Cobertura das **funcionalidades críticas**
- Testes **coroutine-friendly** com `runTest`

## 🚀 Como Executar

### 📱 **Aplicativo Android**

Para compilar e executar a versão de desenvolvimento do aplicativo Android, use a configuração de execução do widget de execução na barra de ferramentas do seu IDE ou compile diretamente do terminal:

- **macOS/Linux**
  ```shell
  ./gradlew :composeApp:assembleDebug
  ```
- **Windows**
  ```shell
  .\gradlew.bat :composeApp:assembleDebug
  ```

### 🖥️ **Aplicativo Desktop (JVM)**

Para compilar e executar a versão de desenvolvimento do aplicativo desktop, use a configuração de execução do widget de execução na barra de ferramentas do seu IDE ou execute diretamente do terminal:

- **macOS/Linux**
  ```shell
  ./gradlew :composeApp:run
  ```
- **Windows**
  ```shell
  .\gradlew.bat :composeApp:run
  ```

### 🍎 **Aplicativo iOS**

Para compilar e executar a versão de desenvolvimento do aplicativo iOS, use a configuração de execução do widget de execução na barra de ferramentas do seu IDE ou abra o diretório [/iosApp](./iosApp) no Xcode e execute a partir de lá.

---

## 🛠️ Tecnologias Utilizadas

- **Kotlin Multiplatform** - Código compartilhado entre plataformas
- **Jetpack Compose Multiplatform** - UI moderna e declarativa
- **Koin** - Injeção de dependências
- **Ktor** - Cliente HTTP para networking
- **Room** - Banco de dados local
- **DataStore** - Armazenamento de preferências
- **Coil** - Carregamento de imagens
- **Material 3** - Design system moderno

---

Saiba mais sobre [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)…
