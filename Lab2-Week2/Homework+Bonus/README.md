
- **LocationType** este o clasa abstracta, iar subclasele acesteia sunt : 
  + Airport, City, GasStation, Village;
- Exista o relatie de compozitie de la **Location** la **LocationType**, cu alte cuvinte **Location** nu poate exista ara **LocationType**;\
- Clasa **Problem** are ca atribute un array de tipul **Location** si unul de tipul **Road**, iar prin metoda **.goFromXtoY(Location locationX, Location locationY)** se verifica daca exista un drum de la **locationX**, la **locationY** folosind roads din atributul arrayRoads al clasei;
- Clasa **Algorithm** este o clasa abstracta(are ca si atribut un obiect de tipul **Problem** si 2 Locations), iar subclasa acesteia este **DijkstraAlgorithm**. Prin metodele **solveForShortestTime()** si **solveForShortestPath()** se calculeaza cel mai rapid, respectiv cel mai scurt drum de la **location1** la **location2**
 
