Feature: Flight search
  To allow a customer to find for a flight.

    Scenario: Finding flights from Paris to Berlin
        Given I am on the BlazeDemo page
        When I search for a flight from "Paris" to "Berlin" and click the button
        Then I choose the first flight and click "Choose This Flight"
          And I fill the name input with "João Farias"
          And I fill the address input with "Rua dos Combatentes do Ultramar nº19"
          And I fill the city input with "Campia"
          And I fill the zipcode input with "3670-056"
          And I select the "American Express" card
          And I fill the card with 123456789
          And card's month is 12
          And card's year is 2024 
          And the name on card is "Zé do Quim"
          And finnaly I click on the confirm button
          And I can see the confirmation order