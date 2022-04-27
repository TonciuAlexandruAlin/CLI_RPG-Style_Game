Dificultate: Medie
Timp de lucru: In jur de o saptamana de lucru

	Jocul este facut doar in terminal, deoarece nu m-am incadrat in timp si 
pentru interfata grafica. In clasa game se alege mai intai daca vrem sa jucam un
joc random, sau sa mergem pe o varianta predefinita a jocului.

	In cadrul ambelor variante se introduce mai intai mail-ul si parola, apoi
caracterul cu care player-ul vrea sa joace.

	In cadrul jocului random, dupa alegerea caracterului, se afiseaza stats-urile,
impreuna cu level-ul si experienta caracterului. Dupa aceasta afisare, apare harta cu
spatiile nevizitate, care pot contine celula goala, un magazin pentru aprovizionare cu
potiuni, un inamic cu care se va incepe o lupta sau finish-ul jocului. Prin introducerea 
caracterelor "N"(in sus), "S"(in jos), "E"(in dreapta), "W"(in stanga) caracterul se va 
deplasa pe harta, explorand spatiile notate cu "?". In cazul in care vom da de un magazin
ne vor fi afisati numarul de banuti pe care ii detinem, alaturi de lista de potiuni de
vanzare. Achizitiile se vor face prin tastarea indexului aratat in stanga potiunii, sau
se va iesi din magazin prin introducerea tastei "9". In cazul in care vom da de un inamic
ni se vor afisa statisticile acestuia, alaturi de ale noastre. Se va putea alege dintre
a ataca cu un atac normal, a ataca cu o abilitate si a se folosi o potiune din inventarul
nostru. In cazul in care alegem un hit, viata inamicul va fi scazuta cu o valoare care
depinde de level-ul caracterului si de clasa acestuia. In cazul in care este aleasa 
varianta de Skill, va fi afisata lista de abilitati ale player-ului si se va alege prin
tastarea indexului o abilitate dintre acestea. In cazul in care este aleasa varianta de
utilizare a unei potiuni, in cazul in care exista, potiunea va fi folosita si se va
elimina din inventar. Atentie, inamicul are 50% sansa sa evite orice fel de atac venit
asupra lui. Daca lupta este castigata, se vor primi banuti cu o sansa de 80%, alaturi de
niste puncte de experienta pentru caracter. Cand s-a ajuns la finish, sectiunea de 
mape completate din cont va creste, progresul fiind salvat in json-ul de conturi.

	In cadrul jocului prestabilit, dupa alegerea caracterului, ca la sectiunea random,
se vor afisa stats-urile, impreuna cu level-ul si experienta caracterului. Deplasarea la
alte casute se va face prin tasta "p". Prima apasare a acesteia ne va duce 3 casute la
dreapta, astfel intrand in shop. Urmatoarele 2 apasari ale tastei vor cumpara 2 potiuni,
una de incarcare a vietii si una de incarcare a manei. Dupa cumpararea acestora, ne vom 
deplasa la dreapta cu o casuta si apoi in jos inca 3 casute. Acolo ne va astepta un 
inamic cu care ne vom lupta. Vom folosi pe rand abilitatile pe care le avem, apoi vom
folosi ambele potiuni pe care le-am cumaprat din magazin. Dupa ce am folosit tot ce 
aveam la dispozitie, vom ataca inamicul cu atacuri normale pana la sfarsitul bataliei.
Ultima apasare a tastei "p" ne va duce la sfarsitul jocului, progresul fiind salvat
in json-ul de conturi.