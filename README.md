# Solution for Self-saving for your retirement

## Overview

### Project Implement:
- Transaction Builder
- Transaction validation
- Temporal Constraint Validation
- Return Calculation for (NPS, NIFTY 50 Index)
- Performance (Optimization measurements)

This application build using:
- Java 21 
- spring boot 
- Maven
- Docker

> I used Java because of it clean Architecture enforcement with excellent performance benchmarking which help to manage the heavy computation and recommended for production ready scalable apps.

### REST APIs:

1. Parse transactions
2. Validate transactions
3. Apply temporal rules (Q, P, K periods)
4. Calculate returns (NPS / Index)
5. Measure performance (time, memory, threads)

The system is stateless and does not require a database.

## Architecture

The project follows a layered structure:

```aiignore
org.example.financial
|-------controller
|-------service
|-------dto
|-------util
|-------model
```

- **Controller Layer** → REST endpoints
- **Service Layer** → Business logic
- **DTO Layer** → Input/Output models
- **Util Layer** → Date parsing & tax calculations

---

## APIs

Base URL:
```http://localhost:5477/blackrock/challenge/v1/....```

## Testing

Unit testing implement using:

- JUnit %
- Spring boot Test
- MockMVC (for controller testing)

```aiignore
mvn test
```

## Docker & Instruction for Running code

### ***Docker Image:***
``
blk-hacking-ind-shivangi-keshri
``

### **Public Docker Image link**

```aiignore
https://hub.docker.com/r/shivangikeshri/blk-hacking-ind-shivangi-keshri
```

### **Run command**
```
docker run -d -p 5477:5477 shivangikeshri/blk-hacking-ind-shivangi-keshri:latest
```

### Example End Point

```aiignore
# Transcation Builder
http://localhost:8080/blackrock/challenge/v1/transactions:parse
# Performance Matrix
http://localhost:5477/blackrock/challenge/v1/performance:index
```

**POSTMAN** Document API Links: 
``https://documenter.getpostman.com/view/23376912/2sBXcEkLMN``

## Author

#### Shivangi Keshri ***(Backend Developer)*** 
contact: shivangikeshri21@gmail.com
