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
A anotação ***@Mock*** é usada com *Junit* e *Mockito* e permite realizar testes sem a implementação final de uma dependência (p.e. de uma dada função).
Por outro lado,***@MockBean*** é usado para permitir adicionar objetos *mock* ao *SpringBoot*, permitindo a realização de testes de integração.

## What is the role of the file “application-integrationtest.properties”? In which conditions will it be used?
O ficheiro `application-integrationtest.properties` tem configurações utilizadas pelo *SpringBoot*, como por exemplo, as de ligação à base de dados.
Quando estão a ser feitos os testes de integração, é usado esse ficheiro (que tem as credenciais da BD) através da notação ```@TestPropertySource```

## The sampleproject demonstrates three test strategies to access an API (C, D and E) developed with SpringBoot. Which are the main/key differences?
* Na estratégia **C**, o objetivo é testar os *Controllers* usando *mocks*, por isso os pedidos são feitos através de uma instância da classe MockMVC.
* Na estratégia **D**, o objetivo é usar todos os componentes, bem como ter uma base de dados, embora ainda seja usada uma instância da classe *MockMVC* para simular os pedidos e respostas **HTTP**
* Na estratégia **E**, são usados todos os componentes e uma base de dados. Neste caso são usados pedidos *HTTP client* reais através da classe TestRestTemplate.
