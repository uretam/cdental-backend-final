# cDental Backend

[![Java 17](https://img.shields.io/badge/Java-17-blue?style=flat)](https://www.java.com/)
[![Spring Boot 3.5.15](https://img.shields.io/badge/Spring%20Boot-3.5.15-brightgreen?style=flat)](https://spring.io/projects/spring-boot)
[![Spring Cloud 2025.0.3](https://img.shields.io/badge/Spring%20Cloud-2025.0.3-blueviolet?style=flat)](https://spring.io/projects/spring-cloud)
[![MySQL 8.0](https://img.shields.io/badge/MySQL-8.0-orange?style=flat)](https://www.mysql.com/)
[![Liquibase Managed](https://img.shields.io/badge/Liquibase-Spring%20Boot%20BOM-blue?style=flat)](https://www.liquibase.org/)

## Descripción del proyecto

cDental Backend es un ecosistema de microservicios Java/Spring Boot diseñado para gestionar un dominio clínico odontológico. El sistema incluye un `api-gateway` para enrutamiento, autenticación centralizada y servicios independientes para pacientes, odontólogos, tratamientos, citas, pagos, historial clínico e insumos.

Cada servicio está construido con Spring Boot 3.5.15 y Java 17. Los servicios que requieren persistencia usan JPA y Liquibase, y cada uno se conecta a su propia base de datos MySQL 8.0 orquestada por Docker Compose.

## Mapa de arquitectura y puertos

<table style="width:100%; border-collapse: collapse;">
  <thead>
    <tr style="background:#f3f4f6; text-align:left;">
      <th style="border:1px solid #d1d5db; padding:8px;">Componente</th>
      <th style="border:1px solid #d1d5db; padding:8px;">Puerto</th>
      <th style="border:1px solid #d1d5db; padding:8px;">Ruta base en Gateway</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td style="border:1px solid #d1d5db; padding:8px;">api-gateway</td>
      <td style="border:1px solid #d1d5db; padding:8px;">8080</td>
      <td style="border:1px solid #d1d5db; padding:8px;">/auth/**, /pacientes/**, /odontologos/**, /tratamientos/**, /citas/**, /pagos/**, /historiales/**, /insumos/**</td>
    </tr>
    <tr>
      <td style="border:1px solid #d1d5db; padding:8px;">auth-service</td>
      <td style="border:1px solid #d1d5db; padding:8px;">8081</td>
      <td style="border:1px solid #d1d5db; padding:8px;">/auth/**</td>
    </tr>
    <tr>
      <td style="border:1px solid #d1d5db; padding:8px;">paciente-service</td>
      <td style="border:1px solid #d1d5db; padding:8px;">8082</td>
      <td style="border:1px solid #d1d5db; padding:8px;">/pacientes/**</td>
    </tr>
    <tr>
      <td style="border:1px solid #d1d5db; padding:8px;">odontologo-service</td>
      <td style="border:1px solid #d1d5db; padding:8px;">8083</td>
      <td style="border:1px solid #d1d5db; padding:8px;">/odontologos/**</td>
    </tr>
    <tr>
      <td style="border:1px solid #d1d5db; padding:8px;">tratamientos-service</td>
      <td style="border:1px solid #d1d5db; padding:8px;">8084</td>
      <td style="border:1px solid #d1d5db; padding:8px;">/tratamientos/**</td>
    </tr>
    <tr>
      <td style="border:1px solid #d1d5db; padding:8px;">citas-service</td>
      <td style="border:1px solid #d1d5db; padding:8px;">8085</td>
      <td style="border:1px solid #d1d5db; padding:8px;">/citas/**</td>
    </tr>
    <tr>
      <td style="border:1px solid #d1d5db; padding:8px;">pagos-service</td>
      <td style="border:1px solid #d1d5db; padding:8px;">8086</td>
      <td style="border:1px solid #d1d5db; padding:8px;">/pagos/**</td>
    </tr>
    <tr>
      <td style="border:1px solid #d1d5db; padding:8px;">historial-service</td>
      <td style="border:1px solid #d1d5db; padding:8px;">8087</td>
      <td style="border:1px solid #d1d5db; padding:8px;">/historiales/**</td>
    </tr>
    <tr>
      <td style="border:1px solid #d1d5db; padding:8px;">insumos-service</td>
      <td style="border:1px solid #d1d5db; padding:8px;">8088</td>
      <td style="border:1px solid #d1d5db; padding:8px;">/insumos/**</td>
    </tr>
  </tbody>
</table>

## Manual de referencia de endpoints

> Todos los endpoints deben ser invocados a través del API Gateway usando `http://localhost:8080`.

### auth-service

<table style="width:100%; border-collapse: collapse;">
  <thead>
    <tr style="background:#f3f4f6; text-align:left;">
      <th style="border:1px solid #d1d5db; padding:8px;">Método</th>
      <th style="border:1px solid #d1d5db; padding:8px;">Endpoint</th>
      <th style="border:1px solid #d1d5db; padding:8px;">Descripción</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td style="border:1px solid #d1d5db; padding:8px;">![POST](https://img.shields.io/badge/POST-green?style=flat)</td>
      <td style="border:1px solid #d1d5db; padding:8px;">`POST /auth/login`</td>
      <td style="border:1px solid #d1d5db; padding:8px;">Autenticar usuario y obtener token</td>
    </tr>
    <tr>
      <td style="border:1px solid #d1d5db; padding:8px;">![GET](https://img.shields.io/badge/GET-blue?style=flat)</td>
      <td style="border:1px solid #d1d5db; padding:8px;">`GET /auth/users`</td>
      <td style="border:1px solid #d1d5db; padding:8px;">Obtener lista de usuarios</td>
    </tr>
    <tr>
      <td style="border:1px solid #d1d5db; padding:8px;">![GET](https://img.shields.io/badge/GET-blue?style=flat)</td>
      <td style="border:1px solid #d1d5db; padding:8px;">`GET /auth/users/{id}`</td>
      <td style="border:1px solid #d1d5db; padding:8px;">Obtener usuario por ID</td>
    </tr>
  </tbody>
</table>

<pre><code>{
  "username": "admin",
  "password": "secret123"
}</code></pre>

<pre><code>{
  "token": "eyJhbGciOi...",
  "username": "admin",
  "role": "ADMIN"
}</code></pre>

### paciente-service

<table style="width:100%; border-collapse: collapse;">
  <thead>
    <tr style="background:#f3f4f6; text-align:left;">
      <th style="border:1px solid #d1d5db; padding:8px;">Método</th>
      <th style="border:1px solid #d1d5db; padding:8px;">Endpoint</th>
      <th style="border:1px solid #d1d5db; padding:8px;">Descripción</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td style="border:1px solid #d1d5db; padding:8px;">![GET](https://img.shields.io/badge/GET-blue?style=flat)</td>
      <td style="border:1px solid #d1d5db; padding:8px;">`GET /pacientes`</td>
      <td style="border:1px solid #d1d5db; padding:8px;">Listar todos los pacientes</td>
    </tr>
    <tr>
      <td style="border:1px solid #d1d5db; padding:8px;">![GET](https://img.shields.io/badge/GET-blue?style=flat)</td>
      <td style="border:1px solid #d1d5db; padding:8px;">`GET /pacientes/{id}`</td>
      <td style="border:1px solid #d1d5db; padding:8px;">Obtener paciente por ID</td>
    </tr>
    <tr>
      <td style="border:1px solid #d1d5db; padding:8px;">![GET](https://img.shields.io/badge/GET-blue?style=flat)</td>
      <td style="border:1px solid #d1d5db; padding:8px;">`GET /pacientes/rut/{rut}`</td>
      <td style="border:1px solid #d1d5db; padding:8px;">Buscar paciente por RUT</td>
    </tr>
    <tr>
      <td style="border:1px solid #d1d5db; padding:8px;">![POST](https://img.shields.io/badge/POST-green?style=flat)</td>
      <td style="border:1px solid #d1d5db; padding:8px;">`POST /pacientes`</td>
      <td style="border:1px solid #d1d5db; padding:8px;">Crear paciente</td>
    </tr>
    <tr>
      <td style="border:1px solid #d1d5db; padding:8px;">![PUT](https://img.shields.io/badge/PUT-yellow?style=flat)</td>
      <td style="border:1px solid #d1d5db; padding:8px;">`PUT /pacientes/{id}`</td>
      <td style="border:1px solid #d1d5db; padding:8px;">Actualizar paciente</td>
    </tr>
    <tr>
      <td style="border:1px solid #d1d5db; padding:8px;">![DELETE](https://img.shields.io/badge/DELETE-red?style=flat)</td>
      <td style="border:1px solid #d1d5db; padding:8px;">`DELETE /pacientes/{id}`</td>
      <td style="border:1px solid #d1d5db; padding:8px;">Eliminar paciente</td>
    </tr>
  </tbody>
</table>

<pre><code>{
  "rut": "12.345.678-9",
  "nombre": "Juan Pérez",
  "correo": "juan.perez@example.com"
}</code></pre>

### odontologo-service

<table style="width:100%; border-collapse: collapse;">
  <thead>
    <tr style="background:#f3f4f6; text-align:left;">
      <th style="border:1px solid #d1d5db; padding:8px;">Método</th>
      <th style="border:1px solid #d1d5db; padding:8px;">Endpoint</th>
      <th style="border:1px solid #d1d5db; padding:8px;">Descripción</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td style="border:1px solid #d1d5db; padding:8px;">![GET](https://img.shields.io/badge/GET-blue?style=flat)</td>
      <td style="border:1px solid #d1d5db; padding:8px;">`GET /odontologos`</td>
      <td style="border:1px solid #d1d5db; padding:8px;">Listar odontólogos</td>
    </tr>
    <tr>
      <td style="border:1px solid #d1d5db; padding:8px;">![GET](https://img.shields.io/badge/GET-blue?style=flat)</td>
      <td style="border:1px solid #d1d5db; padding:8px;">`GET /odontologos/{id}`</td>
      <td style="border:1px solid #d1d5db; padding:8px;">Obtener odontólogo por ID</td>
    </tr>
    <tr>
      <td style="border:1px solid #d1d5db; padding:8px;">![POST](https://img.shields.io/badge/POST-green?style=flat)</td>
      <td style="border:1px solid #d1d5db; padding:8px;">`POST /odontologos`</td>
      <td style="border:1px solid #d1d5db; padding:8px;">Crear odontólogo</td>
    </tr>
    <tr>
      <td style="border:1px solid #d1d5db; padding:8px;">![PUT](https://img.shields.io/badge/PUT-yellow?style=flat)</td>
      <td style="border:1px solid #d1d5db; padding:8px;">`PUT /odontologos/{id}`</td>
      <td style="border:1px solid #d1d5db; padding:8px;">Actualizar odontólogo</td>
    </tr>
    <tr>
      <td style="border:1px solid #d1d5db; padding:8px;">![DELETE](https://img.shields.io/badge/DELETE-red?style=flat)</td>
      <td style="border:1px solid #d1d5db; padding:8px;">`DELETE /odontologos/{id}`</td>
      <td style="border:1px solid #d1d5db; padding:8px;">Eliminar odontólogo</td>
    </tr>
  </tbody>
</table>

<pre><code>{
  "rut": "12.345.678-9",
  "nombre": "Dra. María Suárez",
  "especialidad": "Ortodoncia"
}</code></pre>

### tratamientos-service

<table style="width:100%; border-collapse: collapse;">
  <thead>
    <tr style="background:#f3f4f6; text-align:left;">
      <th style="border:1px solid #d1d5db; padding:8px;">Método</th>
      <th style="border:1px solid #d1d5db; padding:8px;">Endpoint</th>
      <th style="border:1px solid #d1d5db; padding:8px;">Descripción</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td style="border:1px solid #d1d5db; padding:8px;">![GET](https://img.shields.io/badge/GET-blue?style=flat)</td>
      <td style="border:1px solid #d1d5db; padding:8px;">`GET /tratamientos`</td>
      <td style="border:1px solid #d1d5db; padding:8px;">Listar tratamientos</td>
    </tr>
    <tr>
      <td style="border:1px solid #d1d5db; padding:8px;">![GET](https://img.shields.io/badge/GET-blue?style=flat)</td>
      <td style="border:1px solid #d1d5db; padding:8px;">`GET /tratamientos/{id}`</td>
      <td style="border:1px solid #d1d5db; padding:8px;">Obtener tratamiento por ID</td>
    </tr>
    <tr>
      <td style="border:1px solid #d1d5db; padding:8px;">![POST](https://img.shields.io/badge/POST-green?style=flat)</td>
      <td style="border:1px solid #d1d5db; padding:8px;">`POST /tratamientos`</td>
      <td style="border:1px solid #d1d5db; padding:8px;">Crear tratamiento</td>
    </tr>
    <tr>
      <td style="border:1px solid #d1d5db; padding:8px;">![PUT](https://img.shields.io/badge/PUT-yellow?style=flat)</td>
      <td style="border:1px solid #d1d5db; padding:8px;">`PUT /tratamientos/{id}`</td>
      <td style="border:1px solid #d1d5db; padding:8px;">Actualizar tratamiento</td>
    </tr>
    <tr>
      <td style="border:1px solid #d1d5db; padding:8px;">![DELETE](https://img.shields.io/badge/DELETE-red?style=flat)</td>
      <td style="border:1px solid #d1d5db; padding:8px;">`DELETE /tratamientos/{id}`</td>
      <td style="border:1px solid #d1d5db; padding:8px;">Eliminar tratamiento</td>
    </tr>
  </tbody>
</table>

<pre><code>{
  "nombre": "Limpieza dental",
  "descripcion": "Pulido y remover sarro",
  "precioBase": 120.50,
  "duracionEstimadaMinutos": 45,
  "activo": true
}</code></pre>

### citas-service

<table style="width:100%; border-collapse: collapse;">
  <thead>
    <tr style="background:#f3f4f6; text-align:left;">
      <th style="border:1px solid #d1d5db; padding:8px;">Método</th>
      <th style="border:1px solid #d1d5db; padding:8px;">Endpoint</th>
      <th style="border:1px solid #d1d5db; padding:8px;">Descripción</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td style="border:1px solid #d1d5db; padding:8px;">![GET](https://img.shields.io/badge/GET-blue?style=flat)</td>
      <td style="border:1px solid #d1d5db; padding:8px;">`GET /citas`</td>
      <td style="border:1px solid #d1d5db; padding:8px;">Listar todas las citas</td>
    </tr>
    <tr>
      <td style="border:1px solid #d1d5db; padding:8px;">![GET](https://img.shields.io/badge/GET-blue?style=flat)</td>
      <td style="border:1px solid #d1d5db; padding:8px;">`GET /citas/{id}`</td>
      <td style="border:1px solid #d1d5db; padding:8px;">Obtener cita por ID</td>
    </tr>
    <tr>
      <td style="border:1px solid #d1d5db; padding:8px;">![POST](https://img.shields.io/badge/POST-green?style=flat)</td>
      <td style="border:1px solid #d1d5db; padding:8px;">`POST /citas`</td>
      <td style="border:1px solid #d1d5db; padding:8px;">Crear cita</td>
    </tr>
    <tr>
      <td style="border:1px solid #d1d5db; padding:8px;">![PUT](https://img.shields.io/badge/PUT-yellow?style=flat)</td>
      <td style="border:1px solid #d1d5db; padding:8px;">`PUT /citas/{id}`</td>
      <td style="border:1px solid #d1d5db; padding:8px;">Actualizar cita</td>
    </tr>
    <tr>
      <td style="border:1px solid #d1d5db; padding:8px;">![DELETE](https://img.shields.io/badge/DELETE-red?style=flat)</td>
      <td style="border:1px solid #d1d5db; padding:8px;">`DELETE /citas/{id}`</td>
      <td style="border:1px solid #d1d5db; padding:8px;">Eliminar cita</td>
    </tr>
  </tbody>
</table>

<pre><code>{
  "pacienteId": 1,
  "odontologoId": 2,
  "fechaHora": "2026-06-25T14:30:00",
  "motivo": "Consulta general"
}
</code></pre>

### pagos-service

<table style="width:100%; border-collapse: collapse;">
  <thead>
    <tr style="background:#f3f4f6; text-align:left;">
      <th style="border:1px solid #d1d5db; padding:8px;">Método</th>
      <th style="border:1px solid #d1d5db; padding:8px;">Endpoint</th>
      <th style="border:1px solid #d1d5db; padding:8px;">Descripción</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td style="border:1px solid #d1d5db; padding:8px;">![GET](https://img.shields.io/badge/GET-blue?style=flat)</td>
      <td style="border:1px solid #d1d5db; padding:8px;">`GET /pagos`</td>
      <td style="border:1px solid #d1d5db; padding:8px;">Listar pagos</td>
    </tr>
    <tr>
      <td style="border:1px solid #d1d5db; padding:8px;">![GET](https://img.shields.io/badge/GET-blue?style=flat)</td>
      <td style="border:1px solid #d1d5db; padding:8px;">`GET /pagos/{id}`</td>
      <td style="border:1px solid #d1d5db; padding:8px;">Obtener pago por ID</td>
    </tr>
    <tr>
      <td style="border:1px solid #d1d5db; padding:8px;">![POST](https://img.shields.io/badge/POST-green?style=flat)</td>
      <td style="border:1px solid #d1d5db; padding:8px;">`POST /pagos`</td>
      <td style="border:1px solid #d1d5db; padding:8px;">Registrar nuevo pago</td>
    </tr>
    <tr>
      <td style="border:1px solid #d1d5db; padding:8px;">![PUT](https://img.shields.io/badge/PUT-yellow?style=flat)</td>
      <td style="border:1px solid #d1d5db; padding:8px;">`PUT /pagos/{id}`</td>
      <td style="border:1px solid #d1d5db; padding:8px;">Actualizar pago</td>
    </tr>
    <tr>
      <td style="border:1px solid #d1d5db; padding:8px;">![DELETE](https://img.shields.io/badge/DELETE-red?style=flat)</td>
      <td style="border:1px solid #d1d5db; padding:8px;">`DELETE /pagos/{id}`</td>
      <td style="border:1px solid #d1d5db; padding:8px;">Eliminar pago</td>
    </tr>
  </tbody>
</table>

<pre><code>{
  "historialId": 10,
  "monto": 450.00,
  "metodoPago": "TARJETA",
  "fechaPago": "2026-06-25T12:00:00",
  "completado": true
}</code></pre>

### historial-service

<table style="width:100%; border-collapse: collapse;">
  <thead>
    <tr style="background:#f3f4f6; text-align:left;">
      <th style="border:1px solid #d1d5db; padding:8px;">Método</th>
      <th style="border:1px solid #d1d5db; padding:8px;">Endpoint</th>
      <th style="border:1px solid #d1d5db; padding:8px;">Descripción</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td style="border:1px solid #d1d5db; padding:8px;">![GET](https://img.shields.io/badge/GET-blue?style=flat)</td>
      <td style="border:1px solid #d1d5db; padding:8px;">`GET /historiales`</td>
      <td style="border:1px solid #d1d5db; padding:8px;">Listar historiales clínicos</td>
    </tr>
    <tr>
      <td style="border:1px solid #d1d5db; padding:8px;">![GET](https://img.shields.io/badge/GET-blue?style=flat)</td>
      <td style="border:1px solid #d1d5db; padding:8px;">`GET /historiales/{id}`</td>
      <td style="border:1px solid #d1d5db; padding:8px;">Obtener historial por ID</td>
    </tr>
    <tr>
      <td style="border:1px solid #d1d5db; padding:8px;">![POST](https://img.shields.io/badge/POST-green?style=flat)</td>
      <td style="border:1px solid #d1d5db; padding:8px;">`POST /historiales`</td>
      <td style="border:1px solid #d1d5db; padding:8px;">Crear historial clínico</td>
    </tr>
    <tr>
      <td style="border:1px solid #d1d5db; padding:8px;">![PUT](https://img.shields.io/badge/PUT-yellow?style=flat)</td>
      <td style="border:1px solid #d1d5db; padding:8px;">`PUT /historiales/{id}`</td>
      <td style="border:1px solid #d1d5db; padding:8px;">Actualizar historial clínico</td>
    </tr>
    <tr>
      <td style="border:1px solid #d1d5db; padding:8px;">![DELETE](https://img.shields.io/badge/DELETE-red?style=flat)</td>
      <td style="border:1px solid #d1d5db; padding:8px;">`DELETE /historiales/{id}`</td>
      <td style="border:1px solid #d1d5db; padding:8px;">Eliminar historial clínico</td>
    </tr>
  </tbody>
</table>

<pre><code>{
  "pacienteId": 1,
  "citaId": 5,
  "tratamientoId": 3,
  "observaciones": "Seguimiento post tratamiento",
  "fechaRegistro": "2026-06-25T15:00:00",
  "activo": true
}</code></pre>

### insumos-service

<table style="width:100%; border-collapse: collapse;">
  <thead>
    <tr style="background:#f3f4f6; text-align:left;">
      <th style="border:1px solid #d1d5db; padding:8px;">Método</th>
      <th style="border:1px solid #d1d5db; padding:8px;">Endpoint</th>
      <th style="border:1px solid #d1d5db; padding:8px;">Descripción</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td style="border:1px solid #d1d5db; padding:8px;">![GET](https://img.shields.io/badge/GET-blue?style=flat)</td>
      <td style="border:1px solid #d1d5db; padding:8px;">`GET /insumos`</td>
      <td style="border:1px solid #d1d5db; padding:8px;">Listar insumos</td>
    </tr>
    <tr>
      <td style="border:1px solid #d1d5db; padding:8px;">![GET](https://img.shields.io/badge/GET-blue?style=flat)</td>
      <td style="border:1px solid #d1d5db; padding:8px;">`GET /insumos/{id}`</td>
      <td style="border:1px solid #d1d5db; padding:8px;">Obtener insumo por ID</td>
    </tr>
    <tr>
      <td style="border:1px solid #d1d5db; padding:8px;">![POST](https://img.shields.io/badge/POST-green?style=flat)</td>
      <td style="border:1px solid #d1d5db; padding:8px;">`POST /insumos`</td>
      <td style="border:1px solid #d1d5db; padding:8px;">Crear insumo</td>
    </tr>
    <tr>
      <td style="border:1px solid #d1d5db; padding:8px;">![PUT](https://img.shields.io/badge/PUT-yellow?style=flat)</td>
      <td style="border:1px solid #d1d5db; padding:8px;">`PUT /insumos/{id}`</td>
      <td style="border:1px solid #d1d5db; padding:8px;">Actualizar insumo</td>
    </tr>
    <tr>
      <td style="border:1px solid #d1d5db; padding:8px;">![DELETE](https://img.shields.io/badge/DELETE-red?style=flat)</td>
      <td style="border:1px solid #d1d5db; padding:8px;">`DELETE /insumos/{id}`</td>
      <td style="border:1px solid #d1d5db; padding:8px;">Eliminar insumo</td>
    </tr>
  </tbody>
</table>

<pre><code>{
  "nombre": "Anestesia",
  "stockActual": 100,
  "stockMinimo": 20,
  "activo": true
}</code></pre>

## Guía de despliegue local con Docker

1. Construye cada microservicio con su Maven Wrapper local.

```bash
cd auth-service && ./mvnw clean package -DskipTests
cd ../paciente-service && ./mvnw clean package -DskipTests
cd ../odontologo-service && ./mvnw clean package -DskipTests
cd ../tratamientos-service && ./mvnw clean package -DskipTests
cd ../citas-service && ./mvnw clean package -DskipTests
cd ../pagos-service && ./mvnw clean package -DskipTests
cd ../historial-service && ./mvnw clean package -DskipTests
cd ../insumos-service && ./mvnw clean package -DskipTests
```

2. Regresa a la raíz del proyecto:

```bash
cd ..
```

3. Inicia toda la plataforma con Docker Compose:

```bash
docker compose up --build
```

4. Verifica el API Gateway:

```bash
http://localhost:8080
```

## Notas importantes

- Cada microservicio usa su propia base de datos MySQL 8.0 definida en `docker-compose.yml`.
- El gateway expone todas las rutas a través de `http://localhost:8080` y enruta a los servicios individuales según la ruta base.
- Los servicios `pagos-service` e `insumos-service` exponen rutas con prefijo interno `api/` en su controlador (`/api/pagos` y `/api/insumos`).
- Los DTOs validan los campos principales como `rut`, `correo`, `fechaHora`, `precioBase`, `monto` y `stockActual`.
