# Smart Invoice Processing System (Spring Boot + JWT)

Minimal scaffold with JWT security and role-based access (USER, MANAGER).

## Quickstart

1. Install JDK 17+ and Maven
2. Run
```
mvn spring-boot:run
```
3. H2 Console: http://localhost:8080/h2-console

## API
- POST /api/auth/register
- POST /api/auth/login
- POST /api/invoices/upload (multipart file)
- GET /api/invoices
- GET /api/invoices/{id}
- POST /api/invoices/{id}/decision (MANAGER only)
- GET /api/vendors

Use Authorization: Bearer <token> for protected endpoints.
