Bonus :
- Bonus este o clasa abstracta, iar clasele mostenite din aceasta sunt : **CycleGraph** (care contine metode pentru rezolvarea problemei 1) si **RegularGraph** (care contine metode pentru rezolvarea problemei 2);\
CycleGraph : 
Cycle Graph -> este un graf care este format dintr-un singur circuit, iar fiecare nod are gradul 2\
- folosind metoda **.createMatrix()** construim matricea de adiacenta corespunzatoare unui C<sub>n</sub>, in care pentru fiecare nod i ( i de la 1..n-1 ), construim muchia (i,i+1); la acestea adaugam si muchia (n,1);\
- pentru a calcula puterea N a matricei, este apelata metoda **printMatrixToThePowerOfN(int powerN)**, in cadrul careia se apeleaza alta metoda pentru a inmulti o linie cu o coloana;\
RegularGraph :
k-Regular Graph -> este un graf in care fiecare nod al sau are gradul k\
- folosind metoda **.createMatrix()** construim matricea de adiacenta corespunzatoare unui astfel de graf k-regulat; Metoda de constructie :(se considera nodurile puse sub forma de cerc)\
    - daca k este par :\
        - pentru fiecare nod i (i de la 1..n) este construita muchie intre cele mai aropiate k noduri (k/2 noduri din stanga si k/2 noduri din dreapta);\
    - daca k este impar : (pentru a exista un asemenea graf n este par (suma gradelor, k*n, trebuie sa fie para))\
        - pentru fiecare nod i (i de la 1..n) este construita muchie intre cele mai aropiate (k-1) noduri ((k-1)/2 noduri din stanga si (k-1)/2 noduri din dreapta);\
        - a k-a muchie se construieste intre nodul i si nodul opus acestuia (dispus pe cercul initial);\
- prin metoda **public void printMatrix()** se afiseaza matricea tocmai creata;
- metoda **printIfTheCreatedMatrixIsCorrect()** este o metoda de testare a matricei tocmai create - se verifica daca fiecare nod are gradul k si de asemenea, daca matricea este simetrica (o matrice de adiacenta pentru un graf neorientat trebuie sa aiba aceasta proprietate);\