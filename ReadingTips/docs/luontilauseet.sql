
-- DROPs;
-- many-to-many tables;
DROP TABLE IF EXISTS BlogTag;
DROP TABLE IF EXISTS BookTag;
DROP TABLE IF EXISTS PodcastTag;
DROP TABLE IF EXISTS VideoTag;
DROP TABLE IF EXISTS BlogPostCourse;
DROP TABLE IF EXISTS BookCourse;
DROP TABLE IF EXISTS PodcastCourse;
DROP TABLE IF EXISTS VideoCourse;
-- main tables;
DROP TABLE IF EXISTS BlogPost;
DROP TABLE IF EXISTS Book;
DROP TABLE IF EXISTS Podcast;
DROP TABLE IF EXISTS Video;
-- common attribute tables
DROP TABLE IF EXISTS Tag;
DROP TABLE IF EXISTS Course;

-- TABLEs;

-- BlogPost;
CREATE TABLE BlogPost (
    id SERIAL PRIMARY KEY,
    created TIMESTAMP NOT NULL,
    modified TIMESTAMP NOT NULL,
    title VARCHAR(100) NOT NULL, --common
    author VARCHAR(100) NOT NULL, --common
    description VARCHAR(500) NOT NULL, --common    
    url VARCHAR(100) NOT NULL --special
);

-- Book;
CREATE TABLE Book (
    id SERIAL PRIMARY KEY,
    created TIMESTAMP NOT NULL,
    modified TIMESTAMP NOT NULL,    
    title VARCHAR(100) NOT NULL, --common
    author VARCHAR(100) NOT NULL, --common
    description VARCHAR(500) NOT NULL, --common
    isbn VARCHAR(100) NOT NULL --special
);

-- Podcast;
CREATE TABLE Podcast (
    id SERIAL PRIMARY KEY,
    created TIMESTAMP NOT NULL,
    modified TIMESTAMP NOT NULL,    
    title VARCHAR(100) NOT NULL, --common
    author VARCHAR(100) NOT NULL, --common
    description VARCHAR(500) NOT NULL, --common
    nimi VARCHAR(100) NOT NULL --special
);

-- Video;
CREATE TABLE Video (
    id SERIAL PRIMARY KEY,
    created TIMESTAMP NOT NULL,
    modified TIMESTAMP NOT NULL,    
    title VARCHAR(100) NOT NULL, --common
    author VARCHAR(100) NOT NULL, --common
    description VARCHAR(500) NOT NULL, --common
    url VARCHAR(100) NOT NULL, --special
    length BIGINT NOT NULL DEFAULT 0, -- special
    position BIGINT NOT NULL DEFAULT 0 --special
);


-- Tag;
CREATE TABLE Tag (
    id SERIAL PRIMARY KEY,
    created TIMESTAMP NOT NULL,
    modified TIMESTAMP NOT NULL,        
    teksti VARCHAR(100) NOT NULL,
    CONSTRAINT teksti_UNIQUE UNIQUE (teksti)
);
-- many-to-many tables to Tag;
CREATE TABLE BlogTag (
    id SERIAL PRIMARY KEY,
    blog_id INTEGER REFERENCES BlogPost(id),
    tag_id INTEGER REFERENCES Tag(id)
);
CREATE TABLE BookTag (
    id SERIAL PRIMARY KEY,
    book_id INTEGER REFERENCES Book(id),
    tag_id INTEGER REFERENCES Tag(id)
);
CREATE TABLE PodcastTag (
    id SERIAL PRIMARY KEY,
    podcast_id INTEGER REFERENCES Podcast(id),
    tag_id INTEGER REFERENCES Tag(id)
);
CREATE TABLE VideoTag (
    id SERIAL PRIMARY KEY,
    video_id INTEGER REFERENCES VIDEO(id),
    tag_id INTEGER REFERENCES Tag(id)
);

-- Course;
CREATE TABLE Course (
    id SERIAL PRIMARY KEY,
    created TIMESTAMP NOT NULL,
    modified TIMESTAMP NOT NULL,        
    nimi VARCHAR(100) NOT NULL,
    CONSTRAINT nimi_UNIQUE UNIQUE (nimi)
);
-- many-to-many tables to Course
CREATE TABLE BlogPostCourse (
    id SERIAL PRIMARY KEY,
    blog_id INTEGER REFERENCES BlogPost(id),
    course_id INTEGER REFERENCES Course(id)
);
CREATE TABLE BookCourse (
    id SERIAL PRIMARY KEY,
    book_id INTEGER REFERENCES Book(id),
    course_id INTEGER REFERENCES Course(id)
);
CREATE TABLE PodcastCourse (
    id SERIAL PRIMARY KEY,
    podcast_id INTEGER REFERENCES Podcast(id),
    course_id INTEGER REFERENCES Course(id)
);
CREATE TABLE VideoCourse (
    id SERIAL PRIMARY KEY,
    video_id INTEGER REFERENCES VIDEO(id),
    course_id INTEGER REFERENCES Course(id)
);
