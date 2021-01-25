# Ohjelmistoprojekti
Ohjelmistoprojekti kurssin repository

## Sisältö

- [Scrum-syklin yleiskuvaus](#Scrym-syklin-yleiskuvaus)
- [Sprintit](#Sprintit)
- [Työjonot](#Tyojonot)
- [Roolit](#Roolit)
- [Kokoukset](#Kokoukset)
- [Miksi Scrum toimii?](#Miksi-Scrum-toimii)
- [Git](#Git)

## Scrum-syklin yleiskuvaus

## Sprintit

## Työjonot

Jonossa on featureita, joista tärkein ensimmäisenä, toisiksi tärkein toisena jne. Featureita toteutetaan jonosta tärkein-ensin -periaatteella. 

## Roolit

## Kokoukset

## Miksi Scrum toimii? 

## Git

Ota ensin klooni repositorysta omalle koneellesi: `git clone git@github.com:nikkanenjoni/ohjelmistoprojekti.git` 

Jos et ole tehnyt SSH-avaimia ja laittanut niitä github-tilillesi, joudut käyttämään https-protokollaa gitin kanssa. Valitettavasti sen tuki ollaan tosin poistamassa, eli suosittelen tekemään avaimet. Http-protokollalla: `https://github.com/nikkanenjoni/ohjelmistoprojekti.git`

Kun olet kloonannut repositoryn, mene hakemistoon `ohjelmistoprojekti` ja ota itsellesi `develop` branch

`git pull origin develop`

Nyt voit siirtyä develop branchiin komennolla: 

`git checkout develop`

Tee nyt itsellesi uusi haara, jossa alat vääntää omaa featureasi:

`git checkout -b feature1`

Muokkaa tiedostoja, miten haluat viedäksesi projektia eteenpäin. 
Sen jälkeen tallenna muutokset ja mergeä develop-haaraan:

```
git add .
git commit -m "Kerro mitä olet muuttanut (esim. päivitin README-tiedostoa)"
git checkout develop
git merge --no-ff feature1
```
Korvaa `feature1` jollain muulla, mikä on mielestäsi parempi nimi haarallesi. 

Nyt olet tehnyt muutoksia, mutta et ole vielä työntänyt niitä githubiin. Työnnä githubin:

`git push origin develop`

Valmista on!

