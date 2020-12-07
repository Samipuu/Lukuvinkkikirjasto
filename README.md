![Java CI with Gradle](https://github.com/Samipuu/Reading-tips-2020-covid-edition/workflows/Java%20CI%20with%20Gradle/badge.svg)

[![codecov](https://codecov.io/gh/Samipuu/Reading-tips-2020-covid-edition/branch/main/graph/badge.svg?token=PHH5CGO9F2)](https://codecov.io/gh/Samipuu/Reading-tips-2020-covid-edition)

# Lukuvinkkikirjasto

[Backlog](https://docs.google.com/spreadsheets/d/1kZ0gFiGDwlGnhyhhpXqEHOJM38GCwa6GD7lcROB76bw/edit?usp=sharing)


[Sprint 1 BackLog](https://docs.google.com/spreadsheets/d/1kZ0gFiGDwlGnhyhhpXqEHOJM38GCwa6GD7lcROB76bw/edit#gid=600760406)

[Sprint 2 BackLog](https://docs.google.com/spreadsheets/d/1kZ0gFiGDwlGnhyhhpXqEHOJM38GCwa6GD7lcROB76bw/edit#gid=204788354)

Definition of Done

Määrittelemme valmiiksi tehdyn tarkoittamaan sitä, että vaatimus on analysoitu, suunniteltu, ohjelmoitu, testattu, testaus automatisoitu, dokumentoitu, integroitu muuhun ohjelmistoon ja viety tuotantoympäristöön. 

## Käyttöohje

### Suorittaminen

Ohjelma voidaan suorittaa komennolla ```./gradlew run``` komentorivillä projektin juuressa kansiossa ReadingTips.

### Lukuvinkkikirjaston käyttö

Lukuvinkkikirjastossa voi luoda uusia lukuvinkkejä komennolla ```Add```. Komennon jälkeen käyttäjää pyydetään syöttämään tarvittavat tiedot lukuvinkistä. Käyttäjä voi komennolla ```Print All``` tulostaa kaikki luodut lukuvinkit.

Komennolla ```Exit``` lukuvinkkikirjasto voidaan sulkea.

### Buildaaminen

./gradlew distTar

