# FX Deals Data Warehouse

An advanced FX deals data warehouse solution tailored for Bloomberg, built to efficiently import, validate, and persist foreign exchange (FX) deal data. This system enforces strict validation rules and a no-rollback policy, ensuring data consistency and reliability.

## 📑 Table of Contents
- [Project Overview](#project-overview)
- [Key Features](#key-features)
- [Technology Stack](#technology-stack)
- [How to Get Started](#how-to-get-started)
- [API Guide](#api-guide)
- [Project Organization](#project-organization)

## Project Overview

FX Deals Data Warehouse is engineered to efficiently manage the intake, validation, and storage of foreign exchange (FX) deal data. This system incorporates comprehensive validation checks, prevents duplicate entries, and adheres to a no-rollback policy for imported data. Designed to handle large volumes of FX transactions, it ensures that only valid, unique records are saved to the database.

## Key Features

- ✅ **Comprehensive deal validation**:
    - Ensures deal ID is unique.
    - Validates both "from" and "to" currency ISO codes.
    - Verifies deal timestamp format (ISO 8601).
    - Confirms the deal amount is numeric and correctly formatted.

- 🚫 **Duplicate prevention**:
    - Automatically detects and prevents the import of duplicate FX deals.

- 💾 **Persistence with no rollback**:
    - All successfully validated records are committed to the database, with no rollback allowed, ensuring consistency.

- 📝 **Error handling and logging**:
    - Implements detailed logging for all requests and errors, providing full traceability.

- 🔒 **Scalability**:
    - Built to scale efficiently, using MongoDB in a hosted cluster for production environments.


## Tech Stack

### Core Technologies
- Java 17
- Spring Boot 3.2.0
- mongoDB
- Docker & Docker Compose

### Development Tools
- Maven
- JUnit 5
- AssertJ
- Mockito
- SLF4J

## Getting Started

### Prerequisites
- JDK 17
- Docker & Docker Compose
- Maven 3.8+

### Installation

1. Clone the repository
```bash
git clone https://github.com/Elkarroudi/Fx-Deals
cd Fx-Deals
```

2. Start Application
```bash
make up
```

### Using Makefile

The project includes a Makefile with the following commands:
```bash
make up      # Start Docker containers
make down    # Stop Docker containers
make test    # Run tests
make clean   # Clean build files
```

## API Documentation

### Deal Endpoint

```
POST /api/v1/deals
Content-Type: application/json
```

Request Body:
```json
{
  "dealUniqueId": "PRD123",
  "fromCurrency": "USD",
  "toCurrency": "EUR",
  "dealTimestamp": "2025-01-09T14:30:00Z",
  "dealAmount": 1200.50
}

```

Response Body:
```json
{
  "timestamp": "2025-01-09T11:01:53.921894527",
  "success": true,
  "message": "Fx Deal created successfully",
  "data": {
    "id": "677f9e9198c0531ed12d1ba4",
    "dealUniqueId": "PRD123",
    "fromCurrency": "USD",
    "toCurrency": "EUR",
    "dealTimestamp": "2025-01-09T14:30:00",
    "dealAmount": 1200.50
  }
}
```

Custom Error Response:
```json
{
    "timestamp": "2025-01-09T11:07:51.724177637",
    "success": false,
    "type": "DUPLICATE_FX_DEAL_ID",
    "message": "Fx Deal with unique id: PRD123 already exists"
}
```
```json

{
  "timestamp": "2025-01-09T11:11:16.408981505",
  "success": false,
  "type": "VALIDATION_ERROR",
  "errors": {
        "toCurrency": "To Currency is required",
        "dealAmount": "Deal Amount is required",
        "dealUniqueId": "Deal Unique ID is required",
        "dealTimestamp": "Deal Timestamp is required",
        "fromCurrency": "From Currency is required"
  }
}
```
## Project Structure
```
Fx-Deals/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── org/progresssoft/fxdeals/
│   │   │       ├── config/
|   |   |       |── controller/
|   |   |       |── dto/
|   |   |       |── entity/
│   │   │       ├── repository/
│   │   │       ├── service/
│   │   │       └── util/
│   │   └── resources/
│   └── test/
├── Dockerfile
└── docker-compose.yml 
└── Makefile
```