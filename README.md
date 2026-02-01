# Spring Boot CRUD Operations Overview

**Spring Boot** is a framework that simplifies the development of Java-based enterprise applications. It provides production-ready features and reduces boilerplate code. **CRUD operations** (Create, Read, Update, Delete) are the core operations performed on data in applications.

## Key Features of Spring Boot

- **Auto-Configuration:** Automatically configures Spring applications based on dependencies.  
- **Standalone Applications:** Can run independently without deploying on external servers.  
- **Embedded Servers:** Supports embedded Tomcat, Jetty, or Undertow.  
- **Opinionated Defaults:** Reduces configuration complexity.  
- **Spring Ecosystem Support:** Integrates easily with Spring Data, Spring Security, etc.

## CRUD Operations

CRUD operations are the basic operations for managing data:

| Operation | HTTP Method | Description                       |
|-----------|------------|-----------------------------------|
| Create    | POST       | Add new data to the database      |
| Read      | GET        | Retrieve data from the database   |
| Update    | PUT/PATCH  | Modify existing data              |
| Delete    | DELETE     | Remove data from the database     |

## Basic Components for CRUD in Spring Boot

1. **Entity Class:** Represents the database table using JPA annotations (`@Entity`, `@Id`, `@GeneratedValue`).  
2. **Repository Interface:** Extends `JpaRepository` or `CrudRepository` for database operations.  
3. **Service Class:** Contains business logic for CRUD operations.  
4. **Controller Class:** Handles HTTP requests (`@RestController`) and maps endpoints for CRUD operations.  
5. **Application Properties:** Configures database connection (`application.properties` or `application.yml`).

## Example Workflow

1. **Entity Class**

    ```java
    import jakarta.persistence.*;
    
    @Entity
    public class Employee {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String name;
        private String email;
    
        // Getters and Setters
    }
2. **Repository Interface**
   ```java
   import org.springframework.data.jpa.repository.JpaRepository;
   public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    }
3.**Controller Class**
  ```java
   import org.springframework.beans.factory.annotation.Autowired;
   import org.springframework.web.bind.annotation.*;
   import java.util.List;
        
    @RestController
    @RequestMapping("/employees")
    public class EmployeeController {

    @Autowired
    private EmployeeRepository repository;

    // Create
    @PostMapping
    public Employee createEmployee(@RequestBody Employee emp) {
        return repository.save(emp);
    }

    // Read All
    @GetMapping
    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    // Read by ID
    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    // Update
    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee empDetails) {
        Employee emp = repository.findById(id).orElse(null);
        if(emp != null) {
            emp.setName(empDetails.getName());
            emp.setEmail(empDetails.getEmail());
            return repository.save(emp);
        }
        return null;
    }

    // Delete
    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        repository.deleteById(id);
    }
}


