Feature: User can read a book's information

    Scenario: User can add a book
        Given the system is launched
        When command add is given
        Then question Title is printed

    Scenario: User can add a book
        Given the system is launched
        When command exit is given
        Then question Title is printed