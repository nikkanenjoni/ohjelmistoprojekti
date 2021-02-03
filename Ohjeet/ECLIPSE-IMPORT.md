# Ohje projectin Eclipseen tuomiseen

## Alkuvalmistelut

Lataa projekti Githubista ottamalla joko uusi `git clone` tai päivitä nykyinen projektisi komennolla `git pull origin develop`

Tämän jälkeen lataa viimeisin versio Eclipsestä osoitteesta: [https://www.eclipse.org/downloads/](https://www.eclipse.org/downloads/) 


## Import

### Valitse workspace

Käynnistä Eclipse. Eclipsen pitäisi kysyä, mitä hakemistoa haluat käyttää Workspacena:

![Workspace](Eclipse/workspace.png "Workspace")

Valitse se hakemisto, missä tämä projekti on. Varmista, että valitsemassasi hakemistossa on *SCRUM.md* ja *README.md* ja lisäksi hakemisto *linko*.

### File - Import

Tämän jälkeen valitse ylhäältä *File* ja valikosta *Import*, minkä jälkeen sinulle pitäisi avautua seuraava ikkuna: 

![Import](Eclipse/import.png "Import")

Kirjoita ylhäällä olevalle riville *folder* ja lopputuloksen pitäisi olla jotain tällaista: 

![Import folder](Eclipse/folder.png "Import folder")

Valitse *Next*

### Hakemiston Importtaaminen

Sinun pitäisi nyt olla päätynyt seuraavanlaiseen näkymään: 

![Select folder to import](Eclipse/select_source.png "Select folder to import")

Klikkaa *Directory* oikean yläreunan tuntumasta. Valitse *linko* hakemisto (varmista, että valitsemastasi hakemistosta löytyy mm. **pom.xml**-tiedosto). 

Näkymä pitäisi olla nyt jotain tällaista:

![Folder selected](Eclipse/source-ready.png "Folder selected")

Paina oikealta alhaalta *Finish*

### Valmista

Jos kaikki meni kuten piti, olet nyt importannut projektin Eclipseen. Jos onnistuit, sinun pitäisi saada seuraava näkymä:

![Import valmis](Eclipse/valmis.png "Import valmis")

Jos näkymä ei ole tuo, voit valita esim. *Window* - *Show View* - *Project Explorer*


