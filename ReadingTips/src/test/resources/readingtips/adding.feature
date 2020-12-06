Feature: User can add a book information

    Scenario: User can add a book
        Given the system is launched
        When command add is given with type "book"
        Then tip with type "book" is created

    Scenario: User can exit
        Given the system is launched
        When command exit is given
        Then program is quit

    Scenario: User can add a book with attributes
        Given the system is launched
        When tip is created with type "book" title "Test Book" author "Book Author" description "Book Description"
        Then tip with type "book" title "Test Book" author "Book Author" description "Book Description" is created

    Scenario: User can add a book with title
        Given the system is launched
        When tip is created with type "book" title "Test Book" author "Book Author" description "Book Description"
        When command print all is given
        Then book title "Test Book" is returned

    Scenario: User can add a podcast with attributes
        Given the system is launched
        When podcast is created with title "Test Podcast" author "Podcast Author" description "Podcast Description"
        When command print all is given
        Then podcast title "Test Podcast" is returned

    Scenario: User can edit a book
        Given the system is launched
        When book with id 1 is edit with attributes title "Title change" author "Change of author" description "Description is changed"
        Then book title "Title change" is returned

    Scenario: User can edit a podcast
        Given the system is launched
        When tip is created with type "podcast" title "Funny podcast" author "Pena" description "This is a funny podcast"
        When tip with title "Funny podcast" is edit with attributes title "Not so funny podcast" author "Matti" description "Author changed not funny anymore" tags "boring" courses "course1"
        Then tip has been changed with attributes "Not so funny podcast" author "Matti" description "Author changed not funny anymore" tags "boring" courses "course1"

    Scenario: User can print specific tip
        Given the system is launched
        When tip is created with type "book" title "This is a book"
        When tip with title "This is a book" is commanded to print
        Then tip with title "This is a book" is printed
