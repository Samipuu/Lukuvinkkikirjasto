Feature: User can edit a tip

    Scenario: User can edit a book
        Given the system is launched
        When tip is created with type "book" title "Test Book" author "Book Author" description "Book Description"
        And book with id 1 is edit with attributes title "Title change" author "Change of author" description "Description is changed"
        Then book title "Title change" is returned

    Scenario: User can edit a book with attributes
        Given the system is launched
        When tip is created with type "book" title "Test Book" author "Book Author" description "Book Description"
        And book with id 1 is edit with attributes title "Title change" author "Change of author" description "Description is changed" isbn "123-456"
        Then book title "Title change" is returned

    Scenario: User can edit a podcast
        Given the system is launched
        When tip is created with type "podcast" title "Funny podcast" author "Pena" description "This is a funny podcast"
        When tip with title "Funny podcast" is edit with attributes title "Not so funny podcast" author "Matti" description "Author changed not funny anymore" tags "boring" courses "course1"
        Then tip has been changed with attributes "Not so funny podcast" author "Matti" description "Author changed not funny anymore" tags "boring" courses "course1"


    Scenario: User can edit a video
        Given the system is launched
        When video is created with title "Funny video" author "Pena" description "Very funny" url "www.suomi.fi"
        When video with title "Funny video" is edit with attributes title "Not so funny video" author "Matti" url "www.ruotsi.fi"
        Then tip has been changed with attributes "Not so funny video" author "Matti" url "www.ruotsi.fi"

    Scenario: User can edit a podcast with attributes
        Given the system is launched
        When tip is created with type "podcast" title "Funny podcast" author "Pena" description "This is a funny podcast"
        When tip with title "Funny podcast" is edit with attributes title "Not so funny podcast" author "Matti" description "Author changed not funny anymore" url "www.pena.fi" podcast name "Matin podcastit"
        Then tip has been changed with attributes "Not so funny podcast" author "Matti" description "Author changed not funny anymore" url "www.pena.fi" podcast name "Matin podcastit"
