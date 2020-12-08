Feature: User can delete a tip

    Scenario: User can delete a book
        Given the system is launched
        When tip is created with type "book" title "Test Book"
        And book with title "Test Book" is deleted
        And command print all is given
        Then book title "Test Book" is not printed