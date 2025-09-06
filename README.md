
# Translation Management Service (Java Spring Boot)

A high-performance, scalable Translation Management System built with Spring Boot.

## Features

- ✅ Store translations for multiple locales (e.g., `en`, `fr`, `es`, etc.)
- ✅ Tag translations for context (`web`, `mobile`, `desktop`, etc.)
- ✅ CRUD API endpoints for translations
- ✅ Search translations by key, value, or tag
- ✅ JSON export endpoint 
- ✅ Token-based authentication (Bearer Token)
- ✅ Command to populate 100k+ records for performance testing
- ✅ Docker
- ✅ CDN-ready endpoint
- ✅ Unit & performance tests

---

## Setup Instructions

### 1. Clone the Repository

```bash
 git clone <your-repo-url>
```

### 2. Build via Docker

```bash
 chmod +x build.sh
./build.sh
```

### 3. Run via Docker

```bash
 chmod +x run.sh
./run.sh
```


## API Endpoints

### Auth

Use token: `Authorization: Bearer test-token`


### Translations

| Method | Endpoint                            | Description                                     |
| ------ | ----------------------------------- | ----------------------------------------------- |
| `POST` | `/api/translations`                 | Create translation                              |
| `PUT`  | `/api/translations/{id}`            | Update translation                              |
| `GET`  | `/api/translations/{id}`            | Get translation by ID                           |
| `GET`  | `/api/translations`                 | Search translations by `tag`, `key`, or `value` |
| `GET`  | `/api/translations/export`          | Export all translations (JSON)                  |
| `POST` | `/api/translations/generate-sample` | Generate 100k sample records                    |

#### ✅ Example `curl` Commands

**Create Translation**
```bash
 curl -X POST http://localhost:8080/api/translations \
-H "Authorization: Bearer test-token" \
-H "Content-Type: application/json" \
-d '{"key":"greeting","value":"Hello","locale":"en","tag":"web"}'
```

**Update Translation**
```bash
 curl -X PUT http://localhost:8080/api/translations/1 \
-H "Authorization: Bearer test-token" \
-H "Content-Type: application/json" \
-d '{"key":"greeting","value":"Hi","locale":"en","tag":"mobile"}'
```

**Get Translation by ID**
```bash
 curl -X GET http://localhost:8080/api/translations/1 -H "Authorization: Bearer test-token"
```

**Search Translations**
```bash
 curl -X GET "http://localhost:8080/api/translations?tag=mobile&key=greeting" \
-H "Authorization: Bearer test-token"
```

**Export Translations**
```bash
 curl -X GET http://localhost:8080/api/translations/export \
-H "Authorization: Bearer test-token"
```

**Generate Sample Data**
```bash
 curl -X POST "http://localhost:8080/api/translations/generate-sample?count=100000" \
-H "Authorization: Bearer test-token"
```

### CDN-Compatible

| Method | Endpoint                     |
| ------ | ---------------------------- |
| `GET`  | `/cdn/translations/{locale}` |

**Example:**
```bash
 curl -X GET http://localhost:8080/cdn/translations/en
```

---

## Project Structure

```
src/main/java/com/example/translation
├── config           # Security config
├── controller       # REST controllers
├── dto              # Request/response DTOs
├── model            # JPA entity classes
├── repository       # Spring Data JPA interfaces
├── service          # Business logic interfaces & impl
├── util             # Helper utilities
src/test/java/com/example/translation
├── controller       # Controller tests
```

---

## Tech Stack

- Java 17
- Spring Boot 3
- Spring Security
- H2 Memory Database
- Swagger (springdoc-openapi)

---

## Security

- Token-based auth using simple bearer token (`test-token`) for demonstration
- All endpoints require this token

---

## Tests

- JUnit tests included in `src/test/`
- To run tests:

```
 mvn test
```
---

## Docker Setup

Includes:
- `Dockerfile` for Spring Boot app


