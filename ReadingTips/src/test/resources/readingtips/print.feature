Feature: User can print a tip

    Scenario: User can print specific tip
        Given the system is launched
        When tip is created with type "book" title "This is a book"
        When tip with title "This is a book" is commanded to print
        Then tip with title "This is a book" is printed

    Scenario: User can print help
        Given the system is launched
        When command "help" is given
        Then text "commands" is printed

    Scenario: User can print tips with specific tags
        Given the system is launched
        When tip is created with type "book" title "Kissakirja" tag "kissat"        
        And commands print and tag and "kissat" are given
        Then tip with title "Kissakirja" is printed

    Scenario: User can print tips with title
        Given the system is launched
        When tip is created with type "book" title "Kissakirja" tag "kissat"        
        And commands print and title and "Kissakirja" are given
        Then tip with title "Kissakirja" is printed

    Scenario: User can print tips with specific course
        Given the system is launched
        When tip is created with type "book" title "Koirakirja" course "koiranhoito"
        And commands print and course and "koiranhoito" are given
        Then tip with title "Koirakirja" is printed