# Servidor Web 

Un mini servidor web implementado en **Java** que permite manejar solicitudes HTTP básicas (`GET` y `POST`), servir archivos estáticos (HTML, CSS, JS, imágenes) y exponer un endpoint dinámico para generar números aleatorios.

---

## Empezando

Estas instrucciones te permitirán obtener una copia del proyecto y ejecutarlo en tu máquina local para desarrollo y pruebas.

### Prerrequisitos

Necesitas tener instalados:

- [Java JDK 17+](https://www.oracle.com/java/technologies/downloads/)
- [Apache Maven 3.8+](https://maven.apache.org/)

Verifica las versiones ejecutando:

```bash
java -version
mvn -version
```

### Instalación

Clona el repositorio y compílalo con Maven:

```bash
git clone https://github.com/AngieRamosCortes/WebServerAREP.git
cd WebServer
mvn clean install
```

Ejecuta el servidor con Maven:

```bash
mvn clean compile
mvn clean package
java -jar target/WebServer-1.0.0.jar
```

### Acceso en el navegador

Abre en tu navegador:

```
http://localhost:9090
```
<img width="1919" height="939" alt="image" src="https://github.com/user-attachments/assets/48706c2c-f0f6-4c03-9823-625614b22453" />
<img width="1919" height="944" alt="image" src="https://github.com/user-attachments/assets/13ed3775-13d4-44d5-a4cd-d0b08b9018f4" />

Allí encontrarás una página con pruebas para:

- **GET** → `/hello?nombre=TuNombre`
- **POST** → `/hellopost?nombre=TuNombre`
- **API Random** → `/api/random`

### Desglosar en pruebas de extremo a extremo

Se validan respuestas del servidor ante peticiones HTTP (por ejemplo, que `/hello` y `/hellopost` retornen la respuesta esperada) y que los recursos estáticos se sirvan correctamente.

**Ejemplo manual en postman**:

```bash
GET http://localhost:9090/hello?name=Juan
```
<img width="1349" height="677" alt="image" src="https://github.com/user-attachments/assets/72314940-beaf-4b4c-b1e0-fe1607902b65" />


### Pruebas de estilo de codificación

Se verifica que las clases Java compilen correctamente (Java 17) y se mantengan convenciones básicas (paquetes, nombres de clase, etc.). Puedes integrar herramientas como Checkstyle en el futuro.

---

## Construido con

- **Java 17** – Lenguaje principal
- **Maven** – Gestión de dependencias y build
- **JUnit 4** – Pruebas unitarias

---

---

## Authors

- **Angie Julieth Ramos Cortes** – Trabajo completo

---

## License

Este proyecto está bajo la licencia **MIT** – consulta el archivo `LICENSE.md` para más detalles.

---

## Expresiones de gratitud

- Inspiración en conceptos básicos de servidores web en Java
- Práctica de modularización y manejo de HTTP
- Agradecimientos a la **Escuela Colombiana de Ingeniería Julio Garavito** por el contexto académico
