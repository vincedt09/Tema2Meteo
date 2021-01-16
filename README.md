# Aplicatie pentru vreme


## Cuprins
1. [Introducere](#introducere)
2. [Mod de utilizare](#mod-de-utilizare)
    * [Exemplu fisier de intrare](#exemplu fisier)
3. [Diagrame UML](#diagrameuml)
    * [Diagrama de clase](#clase)
    * [Diagrama de activitati](#activitati)
4. [Realizator](#autor)

## Introducere
Acest repositoy contine o aplicatie JavaFX care ofera utilizatorului, pe baza optiunilor introduse, informatii cu privire la starea meteorologica actuala.

## Mod de utilizare
Aplicatia se bazeaza pe un fisier de input care contine datele de localizare ale oraselor.
Aceste date trebuie sa se regaseasca in fisierul **_src/main/resources/input.txt_** in urmatoarea ordine unde simbolul '#' reprezinta caracterul delimitator:
ID#NumeleOrasului#Latitudine#Longitudine#CodulTarii.

### Exemplu fisier de intrare
819827#Razvilka	55.591667#37.740833#RU<br/>
524901#Moscow#55.752220#37.615555#RU<br/>
2973393#Tarascon#43.805828#4.660280#FR<br/>
2986678#Ploufragan#48.491409#-2.794580#FR<br/>

