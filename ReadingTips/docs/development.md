# Development

## Tools

## Build Automation

### Gradle

**Suorittaminen Gradlella**
Ohjelma voidaan suorittaa komennolla ```./gradlew run``` komentorivillä projektin juuressa kansiossa ReadingTips. Buildattu release voidaan suorittaa komennolla ```java -jar Readingtips.jar```.

**Buildaaminen ja suorittaminen**
Lukuvinkkikirjaston voi buildata jaettavaksi paketiksi komennolla ```./gradlew distTar```, jonka jälkeen luotu build/distributions/ReadingTips.tar tulee purkaa esimerkiksi komennolla tar -xvf ReadingTips.tar  
Purkamisen jälkeen ohjelman voi suoritaa komennolla ```bin/ReadingTips``` (tai Windowsissa bin/ReadingTips.bat).  


### Database Management

#### DBeaver

1. download
https://dbeaver.io/download/

2. install

3. run

    3.1. select SQL/H2 Embedded  

    JDBC URL: *updates after selecting path below*  

    for example:  
    jdbc:h2:E:\github_tsekkaukset\ohjelmistotuotanto\miniprojekti\Reading-tips-2020-covid-edition\ReadingTips\readingtips.mv.db

    Path: *select readingtyps.mv.db path and readingtips.mv.db*  
    
    for example:
    E:\github_tsekkaukset\ohjelmistotuotanto\miniprojekti\Reading-tips-2020-covid-edition\ReadingTips\readingtips.mv.db

    Username: sa  
    Password: <tyhjä>

4. see ER-diagram:  
PUBLIC -> ER Diagram

5. make SQL-queries:  
Main Menu -> SQL Editor

6. other fancy database stuff
