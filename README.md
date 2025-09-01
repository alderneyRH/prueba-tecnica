Sistema de Fidelización de Clientes para Marcas de Ropa 👕
📋 Descripción
Aplicación web diseñada para la captura y fidelización de clientes de marcas de ropa. Permite a los usuarios registrarse mediante un formulario intuitivo, seleccionar su marca preferida y gestionar su información de fidelización de manera eficiente.
🚀 Características Principales

✅ Formulario de registro de clientes intuitivo y responsive
✅ Selección de marcas de ropa para fidelización
✅ Gestión completa de datos de clientes
✅ Renderizado dinámico de información desde base de datos
✅ Experiencia de usuario fluida y optimizada
✅ API RESTful para manejo de datos

🛠️ Tecnologías Utilizadas
Backend

Lenguaje: Java
Framework: Spring Boot
Base de Datos: MySQL
Puerto: 8080

Frontend

Lenguaje: JavaScript
Markup: HTML5
Estilos: CSS3
Puerto: 5500

Herramientas de Desarrollo

Base de Datos: MySQL Workbench
API Testing: Postman
Control de Versiones: Git

📋 Requisitos Previos
Antes de ejecutar este proyecto, asegúrate de tener instalado:

Java JDK (versión 11 o superior)
MySQL (versión 8.0 o superior)
MySQL Workbench (para gestión de BD)
Maven (para dependencias de Java)
Git
Navegador web moderno
Postman (opcional, para testing de API)

🔧 Instalación
1. Clonar el repositorio
git clone https://github.com/tu-usuario/sistema-fidelizacion-marcas.git
cd sistema-fidelizacion-marcas

2. Configurar Base de Datos
   # Abrir MySQL Workbench y crear base de datos
CREATE DATABASE fidelizacion_clientes;

# Importar esquema de tablas
mysql -u root -p fidelizacion_clientes < database/schema.sql

# (Opcional) Importar datos de prueba
mysql -u root -p fidelizacion_clientes < database/seed.sql

3. Configurar Backend (Spring Boot)
   cd backend

# Configurar application.properties
# Editar src/main/resources/application.properties con tus credenciales de BD

# Compilar y ejecutar
mvn clean install
mvn spring-boot:run

# El backend estará disponible en http://localhost:8080

4. Configurar Frontend
   cd frontend

# Abrir con Live Server o servidor web
# El frontend estará disponible en http://localhost:5500

▶️ Uso
Ejecutar la Aplicación

1. Iniciar Backend:
  cd backend
  mvn spring-boot:run
Backend disponible en: http://localhost:8080

3. Iniciar Frontend:
   cd frontend
# Abrir index.html con Live Server o tu servidor preferido
Frontend disponible en: http://localhost:5500

Flujo de Uso

 1.  El cliente accede al formulario web
 2.  Completa sus datos personales
 3.  Selecciona la marca de ropa de su preferencia
 4.  El sistema procesa y almacena la información
 5.  Confirmación de registro exitoso


Backend - application.properties
# Configuración de Base de Datos
spring.datasource.url=jdbc:mysql://localhost:3306/fidelizacion_clientes
spring.datasource.username=root
spring.datasource.password=Ampar010
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA/Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

# Puerto del servidor
server.port=8080

# CORS (para desarrollo)
spring.web.cors.allowed-origins=http://localhost:5500

Frontend - Configuración
// config.js - Configuración de API
const API_BASE_URL = 'http://localhost:8080/api';

// Ejemplo de uso en tu JavaScript
fetch(`${API_BASE_URL}/clientes`)
    .then(response => response.json())
    .then(data => console.log(data));


Frontend
# Abrir en navegador
# Verificar formulario de registro
# Probar envío de datos
# Validar renderizado de marcas


API Testing con Postman

Importar collection de Postman (si está disponible)
Configurar environment con:

base_url: http://localhost:8080


Probar endpoints principales:

GET /api/marcas - Listar marcas
POST /api/clientes - Registrar cliente
GET /api/clientes - Listar clientes

🚀 Deployment
Preparación para Producción

1. Backend:
Started loyaltyapi

2. Frontend:
   Subir archivos HTML, CSS, JS a servidor web
   Configurar CORS en backend para dominio de producción
3. Base de Datos:
   Configurar MySQL en servidor de producción
 Actualizar credenciales en application.properties
 Ejecutar scripts de schema.sql



🤝 Contribución

 1. Fork el proyecto
 2. Crea una rama para tu feature (git checkout -b feature/AmazingFeature)
 3. Commit tus cambios (git commit -m 'Add some AmazingFeature')
4. Push a la rama (git push origin feature/AmazingFeature)
5. Abre un Pull Request

✉️ Contacto

Alderney Ramirez - alderneyramirez@gmail.com
Link del Proyecto: https://github.com/alderneyRH/prueba-tecnica/tree/main


🙏 Agradecimientos

Spring Boot por el framework robusto de backend
MySQL por la gestión confiable de datos
Postman por facilitar el testing de APIs
Comunidad de desarrolladores por recursos y documentación

"Con mucho esfurzo dedicacion, me algrea hacer entrega de mi proyecto, con mucha espectativa, pero a la ves emocionado a ver lo mucho que e aprendido, y la gran opornutidad que tengo de poder pertencer a esta maravillosa empresea. (con ganas de ir su+) 
























































































































  
