
- **LocationType** este o clasa abstracta, iar subclasele acesteia sunt : 
  + Airport, City, GasStation, Village;
- Exista o relatie de compozitie de la **Location** la **LocationType**, cu alte cuvinte **Location** nu poate exista ara **LocationType**;\
- Clasa **Problem** are ca atribute un array de tipul **Location** si unul de tipul **Road**, iar prin metoda **.goFromXtoY(Location locationX, Location locationY)** se verifica daca exista un drum de la **locationX**, la **locationY** folosind roads din atributul arrayRoads al clasei;
- Clasa **Algorithm** este o clasa abstracta(are ca si atribut un obiect de tipul **Problem** si 2 Locations), iar subclasa acesteia este **DijkstraAlgorithm**. Prin metodele **solveForShortestTime()** si **solveForShortestPath()** se calculeaza cel mai rapid, respectiv cel mai scurt drum de la **location1** la **location2**
-  Rezultate teste : 
-  **Rezultate Homework** :\
problema 1 este valida : true\
Se poate ajunge de la Location{type=City{population=120000, nrUniversities=1}, name='Suceava', coordinateX=100, coordinateY=70} la\
Location{type=City{population=70000, nrUniversities=0}, name='Galati', coordinateX=450, coordinateY=-200}\
:true\
Se poate ajunge de la Location{type=GasStation{priceDiesel=7.3, pricePetrol=6.89}, name='Petrom-Gara', coordinateX=389, coordinateY=-35} la\
Location{type=City{population=90000, nrUniversities=2}, name='Cluj', coordinateX=-300, coordinateY=-180}\
:false\
Solution for shortest route :\
For getting from location  : "Suceava" to location : "Galati" you can follow this route :\
Location{type=City{population=120000, nrUniversities=1}, name='Suceava', coordinateX=100, coordinateY=70}\
Location{type=City{population=70000, nrUniversities=0}, name='Galati', coordinateX=450, coordinateY=-200}\
Solution for fastest route : \
For getting from location  : "Suceava" to location : "Galati" you can follow this route : \
Location{type=City{population=120000, nrUniversities=1}, name='Suceava', coordinateX=100, coordinateY=70}\
Location{type=City{population=121000, nrUniversities=3}, name='Iasi', coordinateX=400, coordinateY=-30}\
Location{type=City{population=70000, nrUniversities=0}, name='Galati', coordinateX=450, coordinateY=-200}\
- **Rezultate Bonus** :\
For getting from location  : "City943" to location : "City64" you can follow this route : \
Location{type=City{population=10000, nrUniversities=3}, name='City943', coordinateX=943, coordinateY=943}\
Location{type=City{population=10000, nrUniversities=3}, name='City838', coordinateX=838, coordinateY=838}\
Location{type=City{population=10000, nrUniversities=3}, name='City733', coordinateX=733, coordinateY=733}\
Location{type=City{population=10000, nrUniversities=3}, name='City465', coordinateX=465, coordinateY=465}\
Location{type=City{population=10000, nrUniversities=3}, name='City484', coordinateX=484, coordinateY=484}\
Location{type=City{population=10000, nrUniversities=3}, name='City184', coordinateX=184, coordinateY=184}\
Location{type=City{population=10000, nrUniversities=3}, name='City64', coordinateX=64, coordinateY=64}\
This test used 13272320 memory and executed in 715 milliseconds \
 
