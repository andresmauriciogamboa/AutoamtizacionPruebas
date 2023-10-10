Feature: Ejemplo de Request para Udemy

@API
Scenario: Prueba Get al endpoint
Given I send a get request to the https://api.github.com URI
Then I get a 200 status code

@API2
Scenario: Validar la cantidad de entradas de la respuesta.
Given I send a get request to the https://jsonplaceholder.typicode.com URI
Then I validate there are 10 items on the /users endpoint


@API3
Scenario: Validar que un elemento esta en la respuesta.
Given I send a get request to the https://jsonplaceholder.typicode.com URI
Then I validate there is a value: Bret in the response at /users endpoint

@API4
Scenario: Validar que un elemento esta en la respuesta.
Given I send a get request to the https://jsonplaceholder.typicode.com URI
Then I validate there is a value of street: Kulas Light in the response at /users endpoint
   