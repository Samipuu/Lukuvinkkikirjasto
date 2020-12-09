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

### Jaettavan paketin buildaaminen, purkaminen ja ajaminen

Lukuvinkkikirjaston voi buildata jaettavaksi paketiksi komennolla ```./gradlew distTar```, jonka jälkeen luotu build/distributions/ReadingTips.tar tulee purkaa esimerkiksi komennolla tar -xvf ReadingTips.tar  
Purkamisen jälkeen ohjelman voi suoritaa komennolla ```bin/ReadingTips``` (tai Windowsissa bin/ReadingTips.bat).  

## Esimerkkivinkit

Argumentilla examples sovelluksen tietokanta täyttyy muutamista esimerkkivinkeistä ( bin/ReadingTips examples ).

Vinkeissä määritellyt mediatiedostot yritetään ladata downloaded_media_files/files/ -hakemistosta. Jakelupaketissa ja versionhallinnassa näitä tiedostoja ei kuitenkaan ole koska niiden koko on yhteensä useita gigatavuja. downloaded_media_files/ -hakemistosta löytyy scripti download_video_and_audiofiles.sh jonka suorittamalla voi ladata esimerkeissä käytettyjä video- ja audiotiedostoja youtubesta downloaded_media_files/files/ -hakemistoon.

### Lukuvinkkikirjaston käyttö

## Käyttöohje

### Uuden lukuvinkin lisääminen

Uuden lukuvinkin voi lisätä komenonlla ```add```. Tämän jälkeen ohjelma pyytää syöttämään lukuvinkin tyypin (book, video, podcast, blogpost) sekä lukuvinkkin tiedot.

### Lukuvinkkien tulostaminen

Kaikki lukuvinkkikirjastoon tallennetut lukuvinkit voi tulostaa komennolla ```print all```.

### Lukuvinkin muokkaaminen

Lukuvinkkikirjastossa olevaa lukuvinkkiä voi muokata komennolla ```edit```, jonka jälkeen ohjelma pyytää syöttämään muokattavan lukuvinkin ID:n. Jos ID:llä löytyy lukuvinkkikirjastosta lukuvinkki, pyytää ohjelma syöttämään lukuvinkin kentän, jota halutaan muuttaa.

### Lukuvinkin avaaminen

Lukuvinkin voi avata *kokeellisella* järjestelmäintegraatiolla järjestelmään asennetulla ohjelmalla ( mpv mediasoitin, webbiselain ) komennolla ```open```, jonka jälkeen ohjelma pyytää syöttämään avattavan lukuvinkin ID:n.

### Lukuvinkin poistaminen

Lukuvinkkikirjastossa olevan lukuvinkin voi poistaa komennolla ```delete```, jonka jälkeen ohjelma pyytää syöttämään poistettavan lukuvinkin ID:n.

### Help

Komennolla ```help``` tulostuu avulias menu.

### Exit  

Komennolla ```exit``` lukuvinkkikirjasto voidaan sulkea.  
  
# Järjestelmäintegraatio

Lukuvinkkien avaaminen käyttöjärjestelmässä tehdään Bash-komentotulkin kautta. Tämä toiminnallisuus on experimental-statuksella, tuettuna vain muutamalla alustalla ja saattaa toimia tai olla toimimatta. Jälkimmäisen tapahtuessa järjestelmä jatkaa toimintaansa muuten normaalisti halliten mahdollisen poikkeustilanteen. 

## Ubuntu 20.04  
  
Ensisijainen alusta jolla järjestelmäintegraatio testattu. Järjestelmässä täytyy ajaa seuraavat komennot jotta integraatio toimii:  
* sudo apt install mpv  
* sudo apt install ffmpeg  
  
## Windows 10 ja Msys2  
  
Seuraavat komennot jotta integraatio toimii:  
* pacman -S mingw64/mingw-w64-x86_64-mpv  
* pacman -S mingw64/mingw-w64-x86_64-ffmpeg  
