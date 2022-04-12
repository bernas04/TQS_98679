Feature: Book search
  To allow a customer to find his favourite books quickly, the library must offer multiple ways to search for a book.
 
  Scenario: Search books by publication year
    Given a book with the title 'One good book', written by 'Anonymous', published in 2013-03-14
      And another book with the title 'Some other book', written by 'Tim Tomson', published in 2014-08-23
      And another book with the title 'How to cook a dino', written by 'Fred Flintstone', published in 2012-01-01
    When the customer searches for books published between 2013-01-01 and 2014-12-31
    Then 2 books should have been found
      And Book 1 should have the title 'Some other book'
      And Book 2 should have the title 'One good book'

  Scenario: Search books by author
    Given a book with the title 'One good book', written by 'Anonymous', published in 2013-03-14
      And another book with the title 'Some other book', written by 'Tim Tomson', published in 2014-08-23
      And another book with the title 'How to cook a dino', written by 'Fred Flintstone', published in 2012-01-01
      And another book with the title 'This is the best book', written by 'Tim Tomson', published in 2004-08-23
    When the customer searches for books written by 'Tim Tomson'
    Then 2 books should have been found
      And Book 1 should have the title 'Some other book'
      And Book 2 should have the title 'This is the best book'

  Scenario: Search books by category
    Given a 'romance' book with the title 'One good book', written by 'Anonymous', published in 2013-03-14
      And another 'thriller' book with the title 'Some other book', written by 'Tim Tomson', published in 2014-08-23
      And another 'scary' book with the title 'Some other book', written by 'Tim Tomson', published in 2014-08-23
    When the customer searches for 'thriller' books
    Then 1 book should have been found
      And Book 1 should have the title 'Some other book'

  Scenario: Correct non-zero number of books found by author
    Given I have the following books in the store
      | The Devil in the White City          | 30   |
      | The Lion, the Witch and the Wardrobe | 20   |
      | In the Garden of Beasts              | 10   |
    When I search for books that costs more than 10
    Then I find 2 books