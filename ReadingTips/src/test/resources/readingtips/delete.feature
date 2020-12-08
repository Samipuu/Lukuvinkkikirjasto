Feature: User can delete a tip

    Scenario: User can delete a book
        Given the system is launched
        When tip is created with type "book" title "Delete Book"
        And book with title "Delete Book" is deleted
        And command print all is given
        Then book title "Delete Book" is not printed