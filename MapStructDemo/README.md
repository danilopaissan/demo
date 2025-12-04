# MapStructDemo

Small Spring Boot project that demonstrates how to map JPA entities to DTOs using MapStruct. It exposes simple REST APIs for `Category` and `Product`, persists data with Spring Data JPA on an in‑memory H2 database, and seeds sample data at startup.

## Tech stack
- Java 25
- Spring Boot 4.x (Web, Data JPA)
- MapStruct 1.5.x (component model: `spring`)
- Lombok
- H2 (in‑memory) for demo purposes
- Maven

## What this project shows
- Clean separation between Entities and DTOs
- Compile‑time generated mappers with MapStruct (`CategoryMapper`, `ProductMapper`)
- Simple layered architecture: Controller → Service → Repository → Entity/DTO
- Basic tests for mappers

## Run locally
Prerequisites: Java 25 and Maven installed.

```bash
mvn spring-boot:run
```

The app starts on http://localhost:8080.

### H2 Console
- URL: http://localhost:8080/h2-console
- JDBC URL: `jdbc:h2:mem:mapstructdemo`
- User: `sa`
- Password: (empty)

Settings are defined in `src/main/resources/application.properties`.

## REST endpoints (quick overview)
- Products
  - `GET /api/products` – list all products
  - `GET /api/products/{id}` – get product by id
  - `GET /api/products/category/{categoryId}` – list products by category
  - `POST /api/products` – create product (request/response uses `ProductDto`)
  - `PUT /api/products/{id}` – update product
  - `DELETE /api/products/{id}` – delete product

- Categories (similar pattern)
  - `GET /api/categories`
  - `GET /api/categories/{id}`
  - `POST /api/categories`
  - `PUT /api/categories/{id}`
  - `DELETE /api/categories/{id}`

Sample data is inserted at startup by `DataInitializer`.

## Build and test
```bash
mvn clean verify
```

Mapper tests are under `src/test/java/.../mapper`.

## Project layout (key folders)
```
MapStructDemo/
  ├─ src/main/java/net/differentplace/demo/mapstruct/mapstructdemo/
  │   ├─ controller/        # REST controllers
  │   ├─ service/           # business logic
  │   ├─ repository/        # Spring Data JPA repositories
  │   ├─ entity/            # JPA entities
  │   ├─ dto/               # DTOs exposed by the API
  │   └─ mapper/            # MapStruct mappers
  └─ src/main/resources/
      └─ application.properties
```

## Notes
- MapStruct is configured via the Maven Compiler Plugin to generate Spring beans (`-Amapstruct.defaultComponentModel=spring`).
- Lombok and MapStruct binding are added to annotation processors for smooth interoperability.
