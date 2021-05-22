# Resource-ws
Backend del servicio que expone recursos como imágenes alojadas en disco, utilizando Spring Boot 2.3 y Spring WebFlux.

# Requisitos
Tener instalado un **JDK 11** y **Maven 3.6.X**. Es preferible utilizar un Eclipse reciente o Spring Tools Suite.

# Estructura del proyecto
Se trata de un proyecto Maven sin módulos, con una estructura muy simple y poco código.

# Compilacion y ejecución

```bash
mvn clean package
java -jar arget/resource-ws.jar
```

Desde el Eclipse, se puede arrancar el proyecto utilizando la clase **ResourceWSApp.java** y pulsando **Run as** > **Java Application**.

## Docker

Se añade un **Dockerfile** para crear una imagen Docker con el jar compilado.

```bash
docker build --no-cache -t resource-ws:1.0-amd64 .
docker run --name resource-ws -it --rm resource-ws:1.0-amd64
```

**Docker build**: Genera una imagen Docker.
* **--no-cache**: Indica que genere todas las capas del contenedor de nuevo, sin cachear nada.
* **-t**: El nombre del contenedor
* **.**: Indica la ruta en la cual está el Dockerfile y los recursos del contenedor.

**Docker run**: Crea un contenedor a partir de una imagen Docker y lo ejecuta.
* **--name**: Es el nombre que se le dará al contenedor una vez arrancado.
* **-it**: Se indica que la ejecución sea interactiva con la consola actual. Así puedes hacer un Ctrl+C para finalizar el contenedor.
* **--rm**: Indica que se borre el contenedor creado tras finalizar la ejecución.

```
docker build --no-cache -t rekomind-resource-ws:1.2.0 .

docker run --name rekomind-resource-ws -it \
	-v /Users/tanis/Dev/rekomind/rekomind-resource-ws/src/src/main/resources/local/:/etc/rekomind-resource-ws \
	-v /Users/tanis/Dev/rekomind/rekomind-resource-ws/resources:/data/resources \
	-e LOG4J2_CONFIG_FILE="/etc/rekomind-resource-ws/log4j2-local.xml" \
	-e RESOURCES_PATH="/data/resources/" \
	-p 8082:8082 \
	--rm rekomind-resource-ws:1.2.0
```

# Para ver una imagen en local

http://localhost:8082/rekomind-resource-ws/images/console.png