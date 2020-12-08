Feature: User can add a tip

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

    Scenario: User can add a blog post with attributes
        Given the system is launched
        When blog post is created with title "Test Blog" author "Blog Author" description "Blog Description"
        When command print all is given
        Then blog post title "Test Blog" is returned

    Scenario: User can add a video with attributes
        Given the system is launched
        When video is created with title "Test Video" author "Video Author" description "Video Description" url "www.url.fi"
        When command print all is given
        Then video title "Test Video" is returned