# Reading tips 2020 - covid edition

## Loppuraportti

### Työskentelyn aikatauluttaminen

Projekti kesti kolme viikkoa. Jokainen viikko kului saman aikataulun mukaan:

Päivä | Mitä tehtiin
------- | -------------
Torstai | Asiakastapaaminen ja sprintin karkea suunnittelu
Lauantai | Dailyscrum: Sprint on lähtenyt käyntiin ja käydään läpi tarkemmin vaatimusten määrittely sekä tehtävien jako.
Sunnuntai | Dailyscrum: Tarkistetaan, että työt sujuvat ja katsotaan tarkemmin, että aikataulu pitää.
Tiistai | Dailyscrum: Tarkistetaan sprintin tilanne. Työt pitäisi tältä viikolta olla suunnilleen tehty.
Torstai | Asiakaspalaverin jälkeen retrospektiivi. Katsotaan miten viikko sujui, mikä meni hyvin ja mitä olisi voinut tehdä paremmin.


### Ongelmat

#### Sprint 1

kerrataan jokaisen sprintin aikana kohdatut ongelmat (prosessiin-, projektityöskentelyyn- ja teknisiin asioihin liittyvät)

Ensimmäisessä sprintissä aiheutti ongelmia eri toimintavaiheiden hahmottaminen yhtenä kokonaisuutena. Tietokantaa oli hankala lähteä luomaan ennenkuin tiedään vinkkien luokkienrakenne. Sovelluslogiikkaa oli hankala lähteä luomaan ilman vinkkien luokkarakennetta ja tietokantaa. Mentiin enemmän periaatteella, että se joka luo kyseisen asian, suunnittelee sen samalla. Myöhemmät liittyvät osat suunnitellaan sitten tämän mukaan seuraavan tekijän toimesta. Tämä toimi meillä, koska ihmiset saivat aikataulutettua aikansa eri päiville, mutta voisi olla suurempi ongelma kaikkien toimiessa samanaikaisesti. Tältä olisi tietenkin voitu välttyä hieman tarkemmalla suunnittelulla. 

#### Sprint 2

Teknisiä ongelmia ilmeni ryhmän jäsenten tietokoneiden yhteentoimivuudessa. Tausta-asennuksissa oli eroavaisuuksia, jonka takia kaikki kirjastojen osat eivät toimineet kaikilla. Tähän kului turhaa aikaa. Loppupeleissä ongelma selätettiin kommentoimalla toimimattomat osat jokaisella kerralla pois ja jälleen takaisin.

Mockiton käyttö cucumber testeissä databasen mockkaamiseen aiheutti ongelmia. Testien saaminen loogisesti järkeväksi oli tällä tavalla hankalaa. Luovuimmekin databasen mockkaamisesta ja loimme tälle vain erillisen testikannan. Mockiton käyttöönotto ei kuitenkaan mennyt täysin hukkaan, koska sitä käytettiin Sprint 3 tehdyn ulkoisen sovelluksen avaamisen testaamiseen. Tähän tarkoitukseen kirjasto toimikin hyvin. 

#### Sprint 3

Sovellus alkoi olemaan hyvässä vaiheessa. Pieniä ongelmia aiheutti kuitenkin sen päättäminen, mitkä user storyt viimeiseen sprinttiin otettaisiin. Lopuksi aiheutti vielä aikatauluhaasteita valittujen user storyjen loppuunsaattaminen erityisesti testien osalta.

### Onnistumiset

Yhteistyö sujui erittäin hyvin. Viikkoaikataulu sovittiin heti projektin alettua ja kaikki pääsivät paikalle tapaamisiin joka kerta. Tapaamisissa sovittiin aikataulu ja toteutettavat toiminnallisuudet. Apua ja tietoa eri osien toiminnasta sai Telegramin kautta suhteellisen nopeasti melkein kellonajasta riippumatta. Kukin teki osuutensa ja user storyistä saatiin suurin osa valmiiksi testejä myöten.

### Parannukset seuraavaa kertaa varten

Haasteita aiheutti työn eteneminen user storyä ajatellen. Emme pyrkineet tekemään yksi user story kerrallaan, vaan teimme niitä pienempiä taskejä, jotka user storylle kirjattiin. Nämä taskit olisi pitänyt ajatella tarkemmin ja kattavammin heti aluksi ja ajatella työn etenemistä user story kerrallaan, jotta saataisiin aina yksi user story valmiiksi, eikä kovin monta olisi yhtä aikaa kesken. Tätä varten olisi voitu käyttää esimerkiksi jonkinlaisia WIP-rajoitteita, joilla olisi rajattu työ aina yhteen user storyyn kerrallaan. Tässä oltaisi voitu käyttää lisäksi apuna esim. Kanban-taulua.

#### Mitä olisi voinut tehdä paremmin teknisesti:  
  
1. Tietokannan olisi voinut suunnitella helpommin laajennettavaksi vähentämällä tyypitystä JA piilottaen tietokannan toteutuksen funktiorajapinnan taakse niin ettei Javassa tarvitsisi kirjoittaa ollenkaan SQL-lauseita funktiokutsujen lisäksi. Tämä olisi ollut työläämpi mutta jatkoakehitystä ajatellen parempi ratkaisu. Tämän projektin puitteissa valittu ratkaisu oli kuitenkin toimiva ja aikarajoitteen takia parempi.  

2. Käyttöliittymän koodin olisi voinut jakaa yhdestä isosta luokasta osiin, joka komento omaksi luokakseen ja luokkarakenteella joka sallisi helpon laajennettavuuden erilaisiin käyttöliittymiin ( esim. teksti väriteksti, JavaFX ). Myös unit-testien kirjoittaminen olisi helpottunut huomattavasti. Tässäkin projektin aikaraja tuli vastaan loppudemon lähestyessä eikä hyötyjä olisi juuri saavutettu tämän projektin puitteissa.  


### Mitä opittiin?

Kurssin aikana opittiin erityisesti testaamisesta. Jokaisella oli myös eri taustat ja osasivat asioita eri pohjilta. Varmasti kukin oppi hieman erilaisia asioita, kun jokainen teki projektiin liittyenkin vähän eri asioita.

Myös työn ajatteleminen user storyn kannalta oli asia, joka opittiin. Lisäksi opittiin myös jakamaan user storyt pienempiin osiin teknisiksi taskeiksi sekä hieman arvioimaan sekä user storyjen että myös teknisten taskien vaatimaa työmäärää. Projekti opetti myös teknisten taskien jakamista muiden ryhmän jäsenten kanssa, saman teknisen taskin koodaamista useamman koodarin toimesta.

Projekti opetti lisäksi hyvin agilen ja scrumin toimintatapoja käytännössä sekä sitä, miten itseohjautuva tiimi organisoituu ja toimii.

### Mitä oltaisiin haluttu lisäksi oppia?

Vastaavasti, kuin edellisessä kohdassa, koska jokainen teki eri asioita, saattoi tietyt asiat jäädä aina kultakin vähemmälle oppimiselle. Osa teki cucumberia, osa mockitoa ja osa tietokantaa. Kattava oppiminen jäi vähän vähemmälle.

### Mikä oli turhaa?

Varsinaista turhaa työtä ei tehty. Varsinaiseen suunnitteluun olisi voinut käyttää enemmän aikaa, mutta koska osaamistaso on vielä melko alhainen, ei suunnittelua olisi voinut juuri paremmin vielä tehdä. Suunnittelemalla olisi voinut pystyä tietyn toiminnot tekemään suoraviivaisemmin.

### Lopuksi

Yhteenvetona projekti onnistui hyvin pienistä haasteista huolimatta.

###### Osallistujat
* Laura North
* Sami Suonpää
* Jussi Asp
* Harri Tiitinen
* Aatu Kallio
