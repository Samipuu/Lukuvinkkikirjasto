Feature: User can read a book's information

    Scenario: User can read a book's isbn
        Given a book with a title and an isbn is added
        When command getIsbn() is given
        Then isbn is printed