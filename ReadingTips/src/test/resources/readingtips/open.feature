Feature: User can open a tip

    Scenario: User can play video
        Given the system is launched
        When video is created with title "Joe Rogan Experience #1531 - Miley Cyrus" author "Joe" description "Joe ja Miley juttelee asioista" url "Joe Rogan Experience #1531 - Miley Cyrus.mp4"
        And command open is given with ID of video with title "Joe Rogan Experience #1531 - Miley Cyrus"
        Then video will open with title "Joe Rogan Experience #1531 - Miley Cyrus"

    Scenario: User can play podcast
        Given the system is launched
        When podcast is created with title "https://www.supla.fi/audio/3748656" author "RadioRock" description "Korporaatio"
        And command open is given with ID of podcast with title "https://www.supla.fi/audio/3748656"
        Then podcast will open with title "https://www.supla.fi/audio/3748656"