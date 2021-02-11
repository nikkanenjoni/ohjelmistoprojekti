# Huomioita relaatiomallista

**Päiväys:** 11.2.2021

Mielestäni relaatiokaavio on kattava ja vaikuttaisi täyttävän käyttöliittymän tarpeet. Mietin OstoLippu-taulun pääavainta ja Tilaus-taulun FK2:sta. Eikö OstoLippu voisi viitata ennemmin Tilaus-taulun pääavaimeen? OstoLippu voi sitten saada vielä oman pääavaimen erikseen, jos sellainen halutaan. OstoLippu-taulussa voisi olla tieto siitä, kuinka monta tällaista lippua on ostettu kyseisessä tilauksessa. 

Lisäksi tallentaisin lipun myyntihinnan myös tuohon OstoLippu-tauluun, jotta tiedetään, mihin hintaan ko. lippu/liput on myyty, vaikka lipun hintaa esim. laskettaisiin tapahtuman lähetyessä tai järjestäjällä olisi jotain alennuskoodeja tms. 
Lisäksi olisikohan tuonne Tapahtuma-tauluun hyvä lisätä myös samanlainen Kuvaus-sarake kuin tuolla Lippu-taulussa? Sitähän ei nykyisen käyttöliittymän perusteella tarvita, mutta ajattelisin siitä voisi olla iloa myöhemmin. 


