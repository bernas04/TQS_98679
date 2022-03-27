# Exercise 3.1

## Identify a couple of examples on the use of AssertJ expressive methods chaining
Nas classes `A_EmployeeRepositoryTest.java` e `B_EmployeeService_UnitTest.java`, respetivamente, podem ser encontradas os seguintes métodos **AssertJ** em cadeia:

```java
assertThat(allEmployees).hasSize(3).extracting(Employee::getName).contains(alex.getName(), john.getName(), bob.getName())
assertThat(allEmployees).hasSize(3).extracting(Employee::getName).containsOnly(alex.getName(), ron.getName(), bob.getName())
```

## Identify an example in which you mock the behavior of the repository (and avoid involving a database)
Na classe `B_EmployeeService_UnitTest.java`

```java
Mockito.when(employeeRepository.findByName(john.getName())).thenReturn(john);
Mockito.when(employeeRepository.findByName(alex.getName())).thenReturn(alex);
Mockito.when(employeeRepository.findByName("wrong_name")).thenReturn(null);
Mockito.when(employeeRepository.findById(john.getId())).thenReturn(Optional.of(john));
Mockito.when(employeeRepository.findAll()).thenReturn(allEmployees);
Mockito.when(employeeRepository.findById(-99L)).thenReturn(Optional.empty());
```

## What is the difference between standard @Mock and @MockBean?

## What is the role of the file “application-integrationtest.properties”? In which conditions will it be used?

## The sampleproject demonstratesthree test strategies to assess anAPI (C, D and E)developedwith SpringBoot.Which are the main/keydifferences?