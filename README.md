# Bikepulse Connection

Proyecto que consiste en una API REST que consume los datos del sistema de bicicletas públicas **BiciCoruña** (A Coruña) a través del estándar **GBFS** (General Bikeshare Feed Specification), los almacena en una base de datos **PostgreSQL** y los expone mediante endpoints HTTP para su consumo por parte de la aplicación frontend de Bikepulse.

---

## Características

- Consumo de datos en tiempo real desde la API GBFS oficial de BiciCoruña.
- Almacenamiento histórico de las estaciones y de su estado en PostgreSQL.
- Actualización de datos mediante endpoint protegido con token.
- Endpoints para consultar estaciones llenas, vacías, ranking, resumen de la red e historial por estación.
- Configuración CORS para el frontend (aplicación web de Bikepulse).
- Disponible para ejecutarse en Docker.

---

## Tecnologías

- Java 17
- Spring Boot 4.1.1
- PostgreSQL
- Spring Data JDBC 4.1.1
- Spring Web MVC 4.1.1
- Jackson Databind 2.22.2 
- Maven
- Docker 21 

---

## Arquitectura

```
┌──────────────────┐    GBFS     ┌────────────────────┐
│  API BiciCoruña  ├────────────►│   BikeApiClient    │
└──────────────────┘             │  (cliente HTTP)    │
                                 └─────────┬──────────┘
                                           │
                                           ▼
                                 ┌────────────────────┐
                                 │    DataUpdater     │
                                 │   (orquestador)    │
                                 └─────────┬──────────┘
                                           │
                                           ▼
                                 ┌────────────────────┐
                                 │     PostgreSQL     │
                                 │  estacion /        │
                                 │  registro_estacion │
                                 └─────────┬──────────┘
                                           │
                                           ▼
                                 ┌────────────────────┐
                                 │ Repositorios /     │
                                 │   Controllers      │
                                 └─────────┬──────────┘
                                           │
                                           ▼
                                    Endpoints REST
```

**Flujo de trabajo:**
1. `BikeApiClient` obtiene las estaciones y su estado actual desde la API GBFS.
2. `DataUpdater` realiza la sincronización: inserta o actualiza las estaciones en la tabla `estacion` y registra su estado en `registro_estacion`.
3. Los repositorios ejecutan las consultas que alimentan los endpoints del controller.

---

## Estructura del proyecto

```
src/main/java
├── com.bikepulse.bikepulse_api
│   ├── BikeApiClient.java          # Cliente HTTP de la API GBFS
│   ├── DataUpdater.java            # Orquestador de actualización de datos
│   ├── BikepulseApiApplication.java
│   ├── bikepulse/
│   │   └── BikeApiClient.java
│   ├── config/
│   │   └── CorsConfig.java         # Configuración CORS
│   ├── controller/
│   │   ├── Controller.java         # Endpoints públicos
│   │   └── UpdateController.java   # Endpoint de actualización protegido
│   ├── database/
│   │   ├── DatabaseConnection.java
│   │   ├── StationDatabase.java
│   │   └── StationStatusDatabase.java
│   ├── repositories/
│   │   ├── StationDataRepository.java
│   │   ├── StationRepository.java
│   │   └── StationStatusRepository.java
│   ├── stationData/                # Modelos de datos (estación y estado)
│   └── stations/                   # DTOs de respuestas personalizadas
└── utils/
    ├── BikeEnum.java               # Enum (FULL, EMPTY, RANKING)
    └── Mapping.java                # Utilidad de mapeo de datos
```

---

## Configuración

La configuración se realiza mediante variables de entorno, definidas en `src/main/resources/application.properties`:

| `DB_DATASOURCE`: URL de conexión a PostgreSQL |
| `DB_USERNAME` | Usuario de la base de datos |
| `DB_PASSWORD` | Contraseña de la base de datos |
| `DB_DRIVER` | Driver de conexión |
| `PORT` | Puerto del servidor |
| `UPDATE_TOKEN` | Token para el endpoint de actualización |

---


## Base de datos

El proyecto utiliza dos tablas:

### `estacion`

| Campo | Tipo | Descripción |
|---|---|---|
| `id_estacion` | int | Identificador interno (PK) |
| `id_externo` | varchar | Identificador externo (GBFS) |
| `nombre` | varchar | Nombre de la estación |
| `direccion` | varchar | Dirección |
| `cp` | varchar | Código postal |
| `capacidad` | int | Capacidad de la estación |
| `latitud` / `longitud` | double | Coordenadas geográficas |
| `es_carga` | boolean | Es estación de carga |
| `es_virtual` | boolean | Es estación virtual |

### `registro_estacion`

| Campo | Tipo | Descripción |
|---|---|---|
| `id_estacion` | int | FK a `estacion` |
| `fecha` | timestamp | Fecha del registro |
| `bicis_disponibles` | int | Bicis disponibles |
| `bicis_averiadas` | int | Bicis averiadas |
| `huecos_disponibles` | int | Huecos disponibles |
| `huecos_averiados` | int | Huecos averiados |
| `esta_alquilando` | boolean | ¿Permite alquilar? |
| `esta_devolviendo` | boolean | ¿Permite devolver? |
| `esta_instalada` | boolean | ¿Está instalada? |

---

## Endpoints

| Método | Ruta | Descripción |
|---|---|---|
| `GET` | `/stations` | Todas las estaciones |
| `GET` | `/station/{id}` | Datos de una estación concreta |
| `GET` | `/station/{id}/history` | Historial de una estación (últimos 30 registros) |
| `GET` | `/station/{id}/status` | Estado actual de una estación |
| `GET` | `/station/full` | Estaciones llenas |
| `GET` | `/station/empty` | Estaciones vacías |
| `GET` | `/station/summary` | Resumen actual de la red |
| `GET` | `/station/ranking` | Top 5 de estaciones con más bicis |
| `POST` | `/update` | Actualiza los datos desde la API GBFS |
| `POST` | `/admin/update` | Actualiza los datos (requiere token, ver abajo) |


---

## 📝 Licencia

Este proyecto está desarrollado con fines personales y/o académicos. Los datos de estaciones pertenecen al servicio público de bicicletas de A Coruña (BiciCoruña) y se consumen a través de su API GBFS.
