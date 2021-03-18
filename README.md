# Microservicio - ms-movimientoscuenta-personasmobile-exp


Entrega los movimientos de las cuentas corrientes de un cliente


1. [Ownership](/readme.md#1-ownership)
2. [Operaciones](/readme.md#2-operaciones)
3. [Especificación Técnica](/readme.md#3-especificaci%C3%B3n-t%C3%A9cnica)
4. [Dependencias](/readme.md#4-ejecuci%C3%B3n)
5. [Eventos](/readme.md#5-eventos)
6. [Ciclo de Vida](/readme.md#6-ciclo-de-vida)
7. [Change Log](/readme.md#7-change-log)

## 1. Ownership
Esta sección detalla quién es el responsable del ciclo de vida del microservicio.

### 1.1. Business Owner
Unidad de negocio responsable y dueña del microservicio. 
  - Unidad de Negocio: <Nombre de la célula>
  - Responsable: Andrea Ferreti
  --- Contacto: <usuario>@bci.cl - Teléfono: +56-<Teléfono>

### 1.2. Technical Owner
Unidad técnica referente del microservicio.
  - Unidad Técnica: Unidad de APIs y Microservicios
  - Responsable: Patricio Zúñiga
  --- Contacto: patricio.zuniga@externos.bci.cl - Teléfono: +56-<Teléfono>

## 2. Operaciones
Detalla las operaciones que se pueden explotar a través del microservicio. 

| Operación | Descripción Capacidad |
| ------ | ------ | 
| operacion1 | Descripcion 1 |
| operacion2 | Descripcion 2 |

## 3. Especificación Técnica
Detalle técnico del microservicio provisto por especificación Swagger.

<URL a especificación Swagger>
  
## 4. Dependencias
Detalla los sistemas y otros microservicios que son necesarios para el funcionamiento de este microservicio.

### 4.1. Microservicios

| Dependencia | Descripción de la dependencia | Operación |
| ------  | ------  | ------  |
| ms-microservicioX-neg  | Descripción microservicioX  | /Bian1/bian2/operacionX  |

### 4.2. Repositorio de Datos

| Repositorio| Descripción |
| ------  | ------  |
| Redis | Base de Datos en memoria que permite realizar caching para el Microservicio  |

### 4.3. Backends

| Dependencia | Descripción de la dependencia | Operación |
| ------  | ------  | ------  |
| Oracle Service Bus  | Descripción Servicio Oracle Service Bus  | /endpointOSB  |

## 5. Eventos
En esta sección se especifican los eventos que utiliza el microservicio como parte su lógica de negocio.
| Evento | Descripción | publicador/suscriptor |
| ------  | ------  | ------  |
| journal  | Evento para journalizar  | publicador  |

## 6. Ciclo de Vida
En esta sección se especifica el procedimiento para disponibilizar el microservicio.

### 6.1. Compilación
Detalla la forma en que el microservicio debe ser compilado:

./gradlew build

### 6.2. Configuraciones
Detalla las configuraciones necesarias para que el microservicio pueda operar:

#### 6.2.1. Propiedades
| Clave | Valor | Responsable Integracion | Responsable QA | Responsable Produccion |
| ------  | ------ | ------ | ------ | ------ |
| spring.aplication.name | ms-xxx-neg | Pedro | Juan | Diego | 
| tasaPizarra| 2.4 | Pedro | Juan | Diego |

#### 6.2.2. Secretos
| Clave | Valor | Responsable Integracion | Responsable QA | Responsable Produccion |
| ------  | ------ | ------ | ------ | ------ |
| tuxedo.user | usuario | Pedro | Juan | Diego | 
| tuxedo.password | password | Pedro | Juan | Diego |

### 6.3. Ejecución

#### 6.3.1. Contenedor
Especifica la forma en que se ejecuta la aplicación en un contenedor docker:

docker run -e SPRING_PROFILES_ACTIVE='<ambiente>' -e CONTEXT_PATH='/operaciones-transversales-de-producto/gestion-de-cuenta/ms-movimientoscuenta-personasmobile-exp/<MS_VERSION>' -e SPRING_CLOUD_CONFIG_URI='http://config-server-service.bci-infra:8888' -e VAULT_SCHEME='https' -e VAULT_HOST='vault-server-service.bci-infra' -e VAULT_PORT='8200' -e VAULT_TOKEN='<VAULT_TOKEN>' -e VAULT_AUTHENTICATION='TOKEN' -e VAULT_TRUST_STORE='file:/vol-ms/pubcerts.ts' -e VAULT_TRUST_STORE_PWD='<VAULT_TRUST_STORE_PWD>' -e MS_VERSION='<MS_VERSION>'  -p 8080:8080 --name=ms-movimientoscuenta-personasmobile-exp registry.ng.bluemix.net/reg_ic/ ms-movimientoscuenta-personasmobile-exp:<MS_VERSION>

Descripción de variables de entorno:
* SPRING_PROFILES_ACTIVE: <Descripción>
-- Integración: integracion
-- QA: qa
-- Producción: produccion


#### 6.3.2. Recursos Utilizados
Detalla los recursos utilizados por una instancia de un contenedor docker.

| Recurso | Requerido |
| ------ | ------ |
| CPU | Detallar cuánto recurso de CPU se debería destinar a una instancia de microsevicio |
| Memoria | Detallar cuánto recurso de Memoria se debería destinar a una instancia de microsevicio |
| Storage | Detallar cuánto almacenamiento se debería reservar para una instancia de microsevicio |
| Throughput| Detallar cuántos usuarios concurrentes permiten la especificación de recursos descrita anteriormente |


## 7. Change Log
### Versión Version release (Fecha Release)
 - Detallar cambios realizados en release
 - Version mas reciente debe ir primero
 
### Versión 6.6.6 (09/03/2018)
 - Detallar cambios realizados en release 

