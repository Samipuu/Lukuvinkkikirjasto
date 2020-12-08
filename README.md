![Java CI with Gradle](https://github.com/Samipuu/Reading-tips-2020-covid-edition/workflows/Java%20CI%20with%20Gradle/badge.svg)

[![codecov](https://codecov.io/gh/Samipuu/Reading-tips-2020-covid-edition/branch/main/graph/badge.svg?token=PHH5CGO9F2)](https://codecov.io/gh/Samipuu/Reading-tips-2020-covid-edition)

# Lukuvinkkikirjasto

[Backlog](https://docs.google.com/spreadsheets/d/1kZ0gFiGDwlGnhyhhpXqEHOJM38GCwa6GD7lcROB76bw/edit?usp=sharing)


[Sprint 1 BackLog](https://docs.google.com/spreadsheets/d/1kZ0gFiGDwlGnhyhhpXqEHOJM38GCwa6GD7lcROB76bw/edit#gid=600760406)

[Sprint 2 BackLog](https://docs.google.com/spreadsheets/d/1kZ0gFiGDwlGnhyhhpXqEHOJM38GCwa6GD7lcROB76bw/edit#gid=204788354)

Definition of Done

Määrittelemme valmiiksi tehdyn tarkoittamaan sitä, että vaatimus on analysoitu, suunniteltu, ohjelmoitu, testattu, testaus automatisoitu, dokumentoitu, integroitu muuhun ohjelmistoon ja viety tuotantoympäristöön. 

## Asennusohje

### Suorittaminen

Ohjelma voidaan suorittaa komennolla ```./gradlew run``` komentorivillä projektin juuressa kansiossa ReadingTips.

### Lukuvinkkikirjaston käyttö

Lukuvinkkikirjastossa voi luoda uusia lukuvinkkejä komennolla ```Add```. Komennon jälkeen käyttäjää pyydetään syöttämään tarvittavat tiedot lukuvinkistä. Käyttäjä voi komennolla ```Print All``` tulostaa kaikki luodut lukuvinkit.

Komennolla ```Exit``` lukuvinkkikirjasto voidaan sulkea.

### Buildaaminen

Lukuvinkkikirjaston voi buildata suoritettavaksi .jar-tiedostoksi komennolla ```./gradlew distTar```, jonka jälkeen luotu *ReadingTips-shadow.tar*-tiedosto (polussa *./build/distributions/*) tulee purkaa. Purkamisen jälkeen ohjelman voi suoritaa komennolla ```java -jar ReadingTips-shadow/lib/ReadingTips-all.jar```.

### Releasen .jar-tiedoston suorittaminen

Releasen *.jar*-tiedoston voi suorittaa komennolla ```java -jar ReadingTips.jar```.

## Käyttöohje

### Uuden lukuvinkin lisääminen

Uuden lukuvinkin voi lisätä komenonlla ```add```. Tämän jälkeen ohjelma pyytää syöttämään lukuvinkin tyypin (book, video, podcast, blogpost) sekä lukuvinkkin tiedot.

### Lukuvinkkien tulostaminen

Kaikki lukuvinkkikirjastoon tallennetut lukuvinkit voi tulostaa komennolla ```print all```.

### Lukuvinkin muokkaaminen

Lukuvinkkikirjastossa olevaa lukuvinkkiä voi muokata komennolla ```edit```, jonka jälkeen ohjelma pyytää syöttämään muokattavan lukuvinkin ID:n. Jos ID:llä löytyy lukuvinkkikirjastosta lukuvinkki, pyytää ohjelma syöttämään lukuvinkin kentän, jota halutaan muuttaa.

### Lukuvinkin poistaminen

Lukuvinkkikirjastossa olevan lukuvinkin voi poistaa komenonlla ```delete```, jonka jälkeen ohjelma pyytää syöttämään poistettavan lukuvinkin ID:n.
