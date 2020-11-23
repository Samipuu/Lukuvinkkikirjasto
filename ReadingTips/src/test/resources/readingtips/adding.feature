Feature: User can add a book information

    Scenario: User can add a book
        Given the system is launched
        When command add is given
        Then program is quit

    Scenario: User can exit
        Given the system is launched
        When command exit is given
        Then program is quit
