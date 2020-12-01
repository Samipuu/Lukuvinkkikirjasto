Feature: User can add a book information

    Scenario: User can add a book
        Given the system is launched
        When command add is given
        Then program is quit

    Scenario: User can exit
        Given the system is launched
        When command exit is given
        Then program is quit

    Scenario: User can add a book with attributes
        Given the system is launched
        When book is created with title "Test Book" description "Book Description" author "Book Author"
        Then program is quit

    Scenario: User can add a book with title
        Given the system is launched
        When command add is given
        When book is created with title "Test Book" description "Book Description" author "Book Author"
        When command print all is given
        Then title "Test Book" is returned
