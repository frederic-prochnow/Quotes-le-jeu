/**
 * CODER EN IJAVA GRACE A LA LIBRAIRIE AP.jar
 */
import extensions.*;
class affichage extends Program {

    // VARIABLE GLOBAL
    boolean finish = false;
    boolean [][] mur   = new boolean [50][150];
    char    [][] monde = new char    [50][150];
    int[] pos = new int[]{2,2};
    String resultat = "";

    // FONCTION DE RECONNAISSANCE DE FRAPPE SUR LE CLAVIER
    void keyTypedInConsole (char key) {
	cursor(52,0);
	print("VOUS AVEZ APPUYER SUR LA TOUCHE " + key + " (PRESSER LA TOUCHE 'q' POUR QUITTER LE JEU A TOUT MOMENT) ");
	switch(key) {
	case ANSI_UP:
	    //print("up");
	    controle("up");
	    break;
	    
	case ANSI_DOWN:
	    //print("down");
	    controle("down");
	    break;
	    
	case ANSI_LEFT:
	    //print("left");
	    controle("left");
	    break;
		    
	case ANSI_RIGHT:
	    //print("right");
	    controle("right");
	    break;
		    
	case 'q':
	    print("stop");
	    finish = true;
	    break;
	}
    }

    // FONCTIONS DE LA SOURIE
    // ces fonctions sont vides pour qu'il n'affiche aucun message d'erreur
    // si l'utilisateur passe la sourie sur l'interface graphique
    void mouseChanged(String name, int x, int y, int button, String event) {
    }

    void mouseHasMoved(int x,int y){
    }
    
    void mouseChanged(int x,int y) {
    }
    
    void textEntered(String text){ 
	resultat = text;
    }

    // FONCTION DE GESTION DU DEPLACEMENT DU JOUEUR
    void controle(String phrase) {
	int l = pos[0];
	int c = pos[1];
	cursor(52,0);
	print(phrase+" ("+l+","+c+") => "+monde[l][c]+" |"+monde[l+1][c]+"|");
	if(equals(phrase,"up") && monde[l][c] == 'X' && monde[l-1][c] != '.') {
	    cursor(l,c);
	    background("white");
	    text("black");
	    print(' ');
	    monde[l-1][c] = 'X';
	    monde[l][c]   = ' ';
      	    pos[0] = pos[0]-1;
	    cursor(l-1,c);
	    print('X');
	    cursor(l,c+1); afficher(l,c+1);
	    cursor(l,c+2); afficher(l,c+2);
	    cursor(l,c+3); afficher(l,c+3);
	}
	if(equals(phrase,"down") && monde[l][c] == 'X' && monde[l+1][c] != '.') {
	    cursor(l,c);
	    background("white");
	    text("black");
	    print(' ');
	    monde[l+1][c] = 'X';
	    monde[l][c] = ' ';
	    pos[0] = pos[0]+1;
	    cursor(l+1,c);
	    print('X');
	    cursor(l,c+1); afficher(l,c+1);
	    cursor(l,c+2); afficher(l,c+2);
	    cursor(l,c+3); afficher(l,c+3);
	}
	if(equals(phrase,"left") && monde[l][c] == 'X' && monde[l][c-1] != '.') {
	    cursor(l,c);
	    background("white");
	    text("black");
	    print(' ');
	    monde[l][c-1] = 'X';
	    monde[l][c] = ' ';
	    pos[1] = pos[1]-1;
	    cursor(l,c-1);
	    print('X');
	    cursor(l,c+1); afficher(l,c+1);
	    cursor(l,c+2); afficher(l,c+2);
	    cursor(l,c+3); afficher(l,c+3);
	}
	if(equals(phrase,"right") && monde[l][c] == 'X' && monde[l][c+1] != '.') {
	    cursor(l,c);
	    background("white");
	    text("black");
	    print(' ');
	    monde[l][c+1] = 'X';
	    monde[l][c] = ' ';
	    pos[1] = pos[1]+1;
	    cursor(l,c+1);
	    print('X');
	    cursor(l,c+1); afficher(l,c+1);
	    cursor(l,c+2); afficher(l,c+2);
	    cursor(l,c+3); afficher(l,c+3);
	}
    }

    // FONCTION PERMETTANT DE REDUIRE UNE BOUCLE FOR EN 1 LIGNE
    // cette fonction est utilisé pour le parcours du tableau définissant
    // le passage par défaut du personnage
    void boucle (char signe,int place,int i,int sup, int constante) {
	if (signe == '+') {
	    for (int j= i; j<sup; j++) {
		if(place == 1) {
		    mur [constante][j] = false;
		}
		if(place == 2) {
		    mur [j][constante] = false;
		}
	    }
	}
	if (signe == '-') {
	    for (int j=i; j>sup; j--){
		if(place == 1) {
		    mur [constante][j] = false;
		}
		if(place == 2) {
		    mur [j][constante] = false;
		}
	    }
	}
    }

    // FONCTIONS DE DEFINITIONS DU MURS ET DU CHEMIN DE PASSAGE DU PERSONNAGE
    void chemins_et_murs_fixe () {
	// DEFINITION DES MURS FIXES
	for(int l= 0; l<length(monde,1); l++) {
	    for(int c= 0; c<length(monde,2); c++) {
		if(// MURS PRINCIPAUX EXTERIEURS
		   l==0 || l==(length(monde,1)-1) || c==0|| c==(length(monde,2)-1)
		   // MURS PRINCIPAUX INTERIEURS
		   || l== 25 || c== 75){
		    monde [l][c] = '.';
		    mur [l][c] = false;
		}
	    }
	}
	
	// DEFINITION DU CHEMIN NIVEAU 1
	mur [2][2] = false;            mur [3][2] = false;          boucle('+',1,2,8,3);           boucle('+',2,3,6,8);
	boucle('+',1,8,13,6);          boucle('+',2,6,9,13);        boucle('-',1,13,6,9);          boucle('+',2,9,17,6);
	boucle('+',1,6,26,17);         boucle('-',2,17,4,26);       boucle('+',1,26,60,4);         boucle('+',2,4,9,60);
	boucle('-',1,60,50,9);         boucle('+',2,9,18,50);       boucle('+',1,50,64,18);        boucle('+',2,18,25,64);

	// DEFINITION DU CHEMIN NIVEAU 2
	boucle('+',2,26,30,64);        boucle('-',1,64,56,30);      boucle('+',2,30,36,56);        boucle('+',1,56,61,36);
	boucle('+',2,36,40,61);        boucle('-',1,61,49,40);      boucle('-',2,40,33,49);        boucle('-',1,49,28,33);
	boucle('+',2,33,43,28);        boucle('-',1,28,24,43);      boucle('-',2,43,38,24);        boucle('-',1,24,18,38);
	boucle('+',2,38,47,18);        boucle('+',1,18,33,47);      boucle('-',2,47,41,33);        boucle('+',1,33,43,41);
	boucle('+',2,41,47,43);        boucle('+',1,43,60,47);      boucle('-',2,47,44,60);        boucle('+',1,60,75,44);
	boucle('+',2,30,44,63);
	
	// DEFINITION DU CHEMIN NIVEAU 3
	boucle('+',1,76,92,44);        boucle('-',2,44,37,92);      boucle('+',1,92,102,37);       boucle('-',2,37,45,102);
	boucle('-',1,102,100,45);      boucle('-',2,45,43,100);     boucle('-',1,100,97,43);       boucle('+',2,43,46,97);
	boucle('-',1,97,92,46);        boucle('+',2,46,48,92);      boucle('+',1,92,107,48);       boucle('-',2,48,41,107);
	boucle('+',1,107,118,41);      boucle('+',2,41,45,118);     boucle('+',1,118,127,45);      boucle('-',2,45,38,127);
	boucle('+',2,37,45,102);       boucle('+',1,127,137,38);    boucle('-',2,38,31,137);       boucle('-',1,137,119,31);
	boucle('-',2,31,27,119);       boucle('+',1,119,133,27);    boucle('-',2,27,25,133);

	// DEFINITION DU CHEMIN NIVEAU 4 ET ARRIVEE FINAL
	boucle('-',2,24,20,133);       boucle('-',1,133,115,20);    boucle('-',2,20,12,115);       boucle('+',1,115,119,12);
	boucle('+',2,12,17,119);       boucle('+',1,119,126,17);    boucle('-',2,17,10,126);       boucle('+',1,126,131,10);
	boucle('+',2,10,17,131);       boucle('+',1,131,142,17);    boucle('-',2,17,5,142);        boucle('-',1,142,124,5);
	boucle('+',2,5,8,124);         boucle('-',1,124,119,8);     boucle('-',2,8,5,119);         boucle('-',1,119,110,5);
	boucle('+',2,5,13,110);        boucle('-',1,110,103,13);    boucle('-',2,13,7,103);        boucle('-',1,103,96,7);
	boucle('+',2,7,16,96);         boucle('-',1,96,86,16);      boucle('+',2,16,22,86);        boucle('-',1,86,79,22);
	boucle('-',2,22,14,79);        boucle('+',1,78,82,14);      boucle('+',1,78,82,15);        boucle('+',1,78,82,16);
       
    }
    
    /*void testAfficher() {
	assertEquals('*', afficher(0,2));
	assertEquals(' ', afficher(3,2));
	}*/
    
    // FONCTION D AFFICHAGE D UNE CASE AU COORDONNE DONNEE
    void afficher(int l, int c) {
	cursor(l,c);
	if (monde [l][c] == '.'){
	    if(// MURS PRINCIPAUX EXTERIEURS
	       l==0 || l==(length(monde,1)-1) || c==0 || c==(length(monde,2)-1)
	       // MURS PRINCIPAUX INTERIEURS
	       || l== 25 || c== 75){
		background("blue");
		print(monde [l][c]);
		reset();
	    } else {
		background("black");
		print(monde [l][c]);
	    }
	}
	if(monde [l][c] == 'X') {
	    background("white");
	    text("black");
	    print(monde [l][c]);
	    reset();
	}
	if(monde [l][c] == '+') {
	    background("red");
	    print(monde [l][c]);
	}
	if(monde [l][c] == '*') {
	    background("green");
	    print(monde [l][c]);
	}
	if(monde [l][c] == ' ') {   
	    background("white");
	    print(monde [l][c]);
	}
    }

    // FONCTION DE TEST DE LA FONCTION VERIFCHAINE
    void testVerifChaine() {
	assertTrue(verifChaine("lol","lol"));
	assertFalse(verifChaine("lol","lal"));
    }

    // FONCTION DE VERIFICATION IDENTIQUES DE DEUX CHAINES DE CARACTERES
    boolean verifChaine(String chaine1, String chaine2){
	if(length(chaine1)!=length(chaine2)){
	    return false;
	}
	for(int i=0; i<length(chaine1); i++){
	    if(charAt(chaine1,i)!=charAt(chaine2,i)){
		return false;
	    }
	}
	return true;
    }
    
    /*void testafficher_question() {
	assertEquals();
    }*/

    // FONCTION D AFFICHAGE ET DE GESTION  EN MODE GRAPHIQUE DES QUESTIONS
    void afficher_question(Image img,boolean b,int i,int j,String phrase) {
	 setMessage(img,"Votre Réponse :");
	 setText(img,"");
	 if(pos[0] == i && pos[1] == j) {
		show(img);
		while(b == false) {
		    //print(">> "+phrase+" == "+resultat);
		    delay(50);
		    if(verifChaine(phrase,resultat)) {
			b = true;
		    }
		}
		close(img);
	   }
    }

    // FONCTION D AFFICHAGE DE L ANIMATION DE BIENVENUE
    // la porte qui s'ouvre symbolise l'accés dans l'univers du jeu
    void bienvenue() {
	
	Image PORTEGIF0 = new Image ("PORTEGIF0","../ressources/PORTE_GIF00.JPEG");
	Image PORTEGIF1 = new Image ("PORTEGIF1","../ressources/PORTE_GIF01.JPEG");
	Image PORTEGIF2 = new Image ("PORTEGIF2","../ressources/PORTE_GIF02.JPEG");
	Image PORTEGIF3 = new Image ("PORTEGIF3","../ressources/PORTE_GIF03.JPEG");
	Image PORTEGIF4 = new Image ("PORTEGIF4","../ressources/PORTE_GIF04.JPEG");
	Image PORTEGIF5 = new Image ("PORTEGIF5","../ressources/PORTE_GIF05.JPEG");
	Image PORTEGIF6 = new Image ("PORTEGIF6","../ressources/PORTE_GIF06.JPEG");
	Image PORTEGIF7 = new Image ("PORTEGIF7","../ressources/PORTE_GIF07.JPEG");
	Image PORTEGIF8 = new Image ("PORTEGIF8","../ressources/PORTE_GIF08.JPEG");
	Image PORTEGIF9 = new Image ("PORTEGIF9","../ressources/PORTE_GIF09.JPEG");
	Image PORTEGIF10 = new Image ("PORTEGIF10","../ressources/PORTE_GIF10.JPEG");
	Image PORTEGIF11 = new Image ("PORTEGIF11","../ressources/PORTE_GIF11.JPEG");
	Image PORTEGIF12 = new Image ("PORTEGIF12","../ressources/PORTE_GIF12.JPEG");
	Image PORTEGIF13 = new Image ("PORTEGIF13","../ressources/PORTE_GIF13.JPEG");
	Image PORTEGIF14 = new Image ("PORTEGIF14","../ressources/PORTE_GIF14.JPEG");
	Image PORTEGIF15 = new Image ("PORTEGIF15","../ressources/PORTE_GIF15.JPEG");
	Image PORTEGIF16 = new Image ("PORTEGIF16","../ressources/PORTE_GIF16.JPEG");
	Image PORTEGIF17 = new Image ("PORTEGIF17","../ressources/PORTE_GIF17.JPEG");
	Image PORTEGIF18 = new Image ("PORTEGIF18","../ressources/PORTE_GIF18.JPEG");
	Image PORTEGIF19 = new Image ("PORTEGIF19","../ressources/PORTE_GIF19.JPEG");
	Image PORTEGIF20 = new Image ("PORTEGIF20","../ressources/PORTE_GIF20.JPEG");
	Image PORTEGIF21 = new Image ("PORTEGIF21","../ressources/PORTE_GIF21.JPEG");
	Image PORTEGIF22 = new Image ("PORTEGIF22","../ressources/PORTE_GIF22.JPEG");
	Image PORTEGIF23 = new Image ("PORTEGIF23","../ressources/PORTE_GIF23.JPEG");
	Image PORTEGIF24 = new Image ("PORTEGIF24","../ressources/PORTE_GIF24.JPEG");
	Image PORTEGIF25 = new Image ("PORTEGIF25","../ressources/PORTE_GIF25.JPEG");
	Image PORTEGIF26 = new Image ("PORTEGIF26","../ressources/PORTE_GIF26.JPEG");
	Image PORTEGIF27 = new Image ("PORTEGIF27","../ressources/PORTE_GIF27.JPEG");
	Image PORTEGIF28 = new Image ("PORTEGIF28","../ressources/PORTE_GIF28.JPEG");
	Image PORTEGIF29 = new Image ("PORTEGIF29","../ressources/PORTE_GIF29.JPEG");
	Image PORTEGIF30 = new Image ("PORTEGIF30","../ressources/PORTE_GIF30.JPEG");
	Image PORTEGIF31 = new Image ("PORTEGIF31","../ressources/PORTE_GIF31.JPEG");
	Image PORTEGIF32 = new Image ("PORTEGIF32","../ressources/PORTE_GIF32.JPEG");
	Image PORTEGIF33 = new Image ("PORTEGIF33","../ressources/PORTE_GIF33.JPEG");
	Image PORTEGIF34 = new Image ("PORTEGIF34","../ressources/PORTE_GIF34.JPEG");
	Image PORTEGIF35 = new Image ("PORTEGIF35","../ressources/PORTE_GIF35.JPEG");
	Image PORTEGIF36 = new Image ("PORTEGIF36","../ressources/PORTE_GIF36.JPEG");
	Image PORTEGIF37 = new Image ("PORTEGIF37","../ressources/PORTE_GIF37.JPEG");
	Image PORTEGIF38 = new Image ("PORTEGIF38","../ressources/PORTE_GIF38.JPEG");
	Image PORTEGIF39 = new Image ("PORTEGIF39","../ressources/PORTE_GIF39.JPEG");
	Image PORTEGIF40 = new Image ("PORTEGIF40","../ressources/PORTE_GIF40.JPEG");
	Image PORTEGIF41 = new Image ("PORTEGIF41","../ressources/PORTE_GIF41.JPEG");
	Image PORTEGIF42 = new Image ("PORTEGIF42","../ressources/PORTE_GIF42.JPEG");
	Image PORTEGIF43 = new Image ("PORTEGIF43","../ressources/PORTE_GIF43.JPEG");
	Image PORTEGIF44 = new Image ("PORTEGIF44","../ressources/PORTE_GIF44.JPEG");
	Image PORTEGIF45 = new Image ("PORTEGIF45","../ressources/PORTE_GIF45.JPEG");
	Image PORTEGIF46 = new Image ("PORTEGIF46","../ressources/PORTE_GIF46.JPEG");
	Image PORTEGIF47 = new Image ("PORTEGIF47","../ressources/PORTE_GIF47.JPEG");
	Image PORTEGIF48 = new Image ("PORTEGIF48","../ressources/PORTE_GIF48.JPEG");
	Image PORTEGIF49 = new Image ("PORTEGIF49","../ressources/PORTE_GIF49.JPEG");
	Image PORTEGIF50 = new Image ("PORTEGIF50","../ressources/PORTE_GIF50.JPEG");
	Image PORTEGIF51 = new Image ("PORTEGIF51","../ressources/PORTE_GIF51.JPEG");
	Image PORTEGIF52 = new Image ("PORTEGIF52","../ressources/PORTE_GIF52.JPEG");
	Image PORTEGIF53 = new Image ("PORTEGIF53","../ressources/PORTE_GIF53.JPEG");
	
	// OUVERTURE AVEC DELAIS DES IMAGES DE L ANIMATION
	show(PORTEGIF0);   delay(200);  show(PORTEGIF1);   delay(200);  show(PORTEGIF2);   delay(200);
	show(PORTEGIF3);   delay(200);  show(PORTEGIF4);   delay(200);  show(PORTEGIF5);   delay(200);
	show(PORTEGIF6);   delay(200);	show(PORTEGIF7);   delay(200); 	show(PORTEGIF8);   delay(200); 
	show(PORTEGIF9);   delay(200); 	show(PORTEGIF10);  delay(200);	show(PORTEGIF11);  delay(200);
	show(PORTEGIF12);  delay(200);	show(PORTEGIF13);  delay(200);	show(PORTEGIF14);  delay(200);
	show(PORTEGIF15);  delay(200);	show(PORTEGIF16);  delay(200);	show(PORTEGIF17);  delay(200);
	show(PORTEGIF18);  delay(200);	show(PORTEGIF19);  delay(200);  show(PORTEGIF20);  delay(200);
	show(PORTEGIF21);  delay(200);  show(PORTEGIF22);  delay(200);  show(PORTEGIF23);  delay(200);
	show(PORTEGIF24);  delay(200);  show(PORTEGIF25);  delay(200);  show(PORTEGIF26);  delay(200);
	show(PORTEGIF27);  delay(200);  show(PORTEGIF28);  delay(200);  show(PORTEGIF29);  delay(200);
	show(PORTEGIF30);  delay(200);	show(PORTEGIF31);  delay(200);	show(PORTEGIF32);  delay(200);
	show(PORTEGIF33);  delay(200);	show(PORTEGIF34);  delay(200);	show(PORTEGIF35);  delay(200);
	show(PORTEGIF36);  delay(200);	show(PORTEGIF37);  delay(200);	show(PORTEGIF38);  delay(200);
	show(PORTEGIF39);  delay(200);	show(PORTEGIF40);  delay(200);	show(PORTEGIF41);  delay(200);
	show(PORTEGIF42);  delay(200);	show(PORTEGIF43);  delay(200);	show(PORTEGIF44);  delay(200);
	show(PORTEGIF45);  delay(200);	show(PORTEGIF46);  delay(200);	show(PORTEGIF47);  delay(200);
	show(PORTEGIF48);  delay(200);	show(PORTEGIF49);  delay(200);	show(PORTEGIF50);  delay(200);
	show(PORTEGIF51);  delay(200);	show(PORTEGIF52);  delay(200);	show(PORTEGIF53);  delay(200);

	// FERMETURE DES FENTRES DES IMAGES DE L ANIMATION
	close(PORTEGIF0);       close(PORTEGIF1);       close(PORTEGIF2);	close(PORTEGIF3);       close(PORTEGIF4);
	close(PORTEGIF5);	close(PORTEGIF6);	close(PORTEGIF7);	close(PORTEGIF8);	close(PORTEGIF9);
	close(PORTEGIF10);	close(PORTEGIF11);	close(PORTEGIF12);	close(PORTEGIF13);	close(PORTEGIF14);
	close(PORTEGIF15);	close(PORTEGIF16);	close(PORTEGIF17);	close(PORTEGIF18);	close(PORTEGIF19);
	close(PORTEGIF20);	close(PORTEGIF21);	close(PORTEGIF22);	close(PORTEGIF23);	close(PORTEGIF24);
	close(PORTEGIF25);      close(PORTEGIF26);      close(PORTEGIF27);	close(PORTEGIF28);      close(PORTEGIF29);
	close(PORTEGIF30);	close(PORTEGIF31);	close(PORTEGIF32);	close(PORTEGIF33);	close(PORTEGIF34);
	close(PORTEGIF35);	close(PORTEGIF36);	close(PORTEGIF37);	close(PORTEGIF38);	close(PORTEGIF39);
	close(PORTEGIF40);	close(PORTEGIF41);	close(PORTEGIF42);	close(PORTEGIF43);	close(PORTEGIF44);
	close(PORTEGIF45);	close(PORTEGIF46);	close(PORTEGIF47);	close(PORTEGIF48);	close(PORTEGIF49);
	close(PORTEGIF50);	close(PORTEGIF51);	close(PORTEGIF52);	close(PORTEGIF53);
    }

    // FONCTION DE GESTION DE L'AFFICHAGE GENERAL DU JEU
    void affichage () {
	for(int l= 0; l<length(monde,1); l++) {
	    for(int c= 0; c<length(monde,2); c++) {
		afficher(l,c);
	    }
	    //print("\n");
	    backward(200);
	}
    }

    // LE JEU
    void algorithm () {
	
        // ANIMATION DE BIENVENUE DANS LE JEU
	bienvenue();
	
	// INITIALISATION TABLEAU MONDE
	for(int l= 0; l<length(mur,1); l++) {
	    for(int c= 0; c<length(mur,2); c++) {
		monde [l][c] = ' ';
	    }
	}
	
	// INITIALISATION TABLEAU MUR
	for(int l= 0; l<length(mur,1); l++) {
	    for(int c= 0; c<length(mur,2); c++) {
		mur [l][c] = true;
	    }
	}
			
	// DEGINIATION DES CHEMINS ET MURS FIXES
	chemins_et_murs_fixe();
	
	// DEFINITION ALEATOIRE DES MURS INTERIEURS
	for(int l= 0; l<length(mur,1); l++) {
	    for(int c= 0; c<length(mur,2); c++) {
		double aleatoire = random();
		if(mur [l][c] == true && aleatoire <=0.6) {
		    monde [l][c] = '.';
		    mur [l][c] = false;
		}
	    }
	}

	// DEFINITION DU PERSONNAGE
	char personnage = 'X';
	
	// DEFINITION DE LA CASE DE DEPART
	monde [2][2] = personnage;
	
	// CHARGEMENT DES IMAGES POUR LES QUESTIONS
	// NIVEAU 1
	Image i_n1q1 = newImage ("i_n1q1","../ressources/N1Q1.JPEG");
	Image i_n1q2 = newImage ("i_n1q2","../ressources/N1Q2.JPEG");
	Image i_n1q3 = newImage ("i_n1q3","../ressources/N1Q3.JPEG");
	Image i_n1q4 = newImage ("i_n1q4","../ressources/N1Q4.JPEG");
	Image i_n1q5 = newImage ("i_n1q5","../ressources/N1Q5.JPEG");
	// NIVEAU 2
	Image i_n2q1 = newImage ("i_n2q1","../ressources/N2Q1.JPEG");
	Image i_n2q2 = newImage ("i_n2q2","../ressources/N2Q2.JPEG");
	Image i_n2q3 = newImage ("i_n2q3","../ressources/N2Q3.JPEG");
	Image i_n2q4 = newImage ("i_n2q4","../ressources/N2Q4.JPEG");
	Image i_n2q5 = newImage ("i_n2q5","../ressources/N2Q5.JPEG");
	// NIVEAU 3
	Image i_n3q1 = newImage ("i_n3q1","../ressources/N3Q1.JPEG");
	Image i_n3q2 = newImage ("i_n3q2","../ressources/N3Q2.JPEG");
	Image i_n3q3 = newImage ("i_n3q3","../ressources/N3Q4.JPEG");
	Image i_n3q4 = newImage ("i_n3q4","../ressources/N3Q5.JPEG");
	Image i_n3q5 = newImage ("i_n3q5","../ressources/N3Q6.JPEG");
	// NIVEAU 4
	Image i_n4q1 = newImage ("i_n4q1","../ressources/N4Q1.JPEG");
	Image i_n4q2 = newImage ("i_n4q2","../ressources/N4Q2.JPEG");
	Image i_n4q3 = newImage ("i_n4q3","../ressources/N4Q3.JPEG");
	Image i_n4q4 = newImage ("i_n4q4","../ressources/N4Q4.JPEG");
	//Image i_n4q5 = newImage ("i_n4q5","../ressources/N4Q5.JPEG");
	
	// DEFINITION DES LIEUX DES QUESTIONS
	// BOOLEAN REPONSE      DEBLOCAGE MUR         AFFICHAGE LOGO         REPONSE A ECRICRE
	boolean n1q1 = false;   mur[17][16] = false;  monde[17][16] = '+';   String r_n1q1 = "impair";
	boolean n1q2 = false;   mur[10][26] = false;  monde[10][26] = '+';   String r_n1q2 = "99";
	boolean n1q3 = false;   mur[4][44] = false;   monde[4][44] = '+';    String r_n1q3 = "25";
	boolean n1q4 = false;   mur[13][50] = false;  monde[13][50] = '+';   String r_n1q4 = "addition";
	boolean n1q5 = false;   mur[23][63] = false;  monde[23][63] = '+';   String r_n1q5 = "3.14";
	
	boolean n2q1 = false;   mur[36][58] = false;  monde[33][44] = '+';   String r_n2q1 = "1939";
	boolean n2q2 = false;   mur[40][51] = false;  monde[40][51] = '+';   String r_n2q2 = "londre";
	boolean n2q3 = false;   mur[33][44] = false;  monde[33][44] = '+';   String r_n2q3 = "au revoir"; 
	boolean n2q4 = false;   mur[42][27] = false;  monde[42][27] = '+';   String r_n2q4 = "maitre gims"; 
	boolean n2q5 = false;   mur[47][52] = false;  monde[47][52] = '+';   String r_n2q5 = "rugby"; 
	
	boolean n3q1 = false;   mur[37][99] = false;  monde[37][99] = '+';   String r_n3q1 = "pass"; 
	boolean n3q2 = false;   mur[46][96] = false;  monde[46][96] = '+';   String r_n3q2 = "futur"; 
	boolean n3q3 = false;   mur[48][10] = false;  monde[48][10] = '+';   String r_n3q3 = "j ai range ma chambre"; 
	boolean n3q4 = false;   mur[39][127] = false; monde[39][127] = '+';  String r_n3q4 = "arrivee"; 
	boolean n3q5 = false;   mur[31][125] = false; monde[31][125] = '+';  String r_n3q5 = "mes chaussures sont bleues"; 
	
	boolean n4q1 = false;   mur[17][115] = false; monde[17][115] = '+';  String r_n4q1 = "fleche"; 
	boolean n4q2 = false;   mur[10][128] = false; monde[10][128] = '+';  String r_n4q2 = "clavier"; 
	boolean n4q3 = false;   mur[8][122] = false;  monde[8][122] = '+';   String r_n4q3 = "you tube"; 
	boolean n4q4 = false;   mur[13][107] = false; monde[13][107] = '+';  String r_n4q4 = "facebook"; 
	//boolean n4q5 = false;   mur[16][90] = false;  monde[16][98] = '+';   String r_n4q5 = "";

	// DEFINITION DU LIEU DE DEBLOQUAGE
	// NIVEAU 1
	for(int i=63; i<66; i++) {
	    monde [25][i] = '*';
	}
	// NIVEAU 2
	for(int i=43; i<46; i++) {
	    monde [i][75] = '*';
	}
	// NIVEAU 3
	for(int i=132; i<135; i++) {
	    monde [25][i] = '*';
	}
	// NIVEAU 4
	for(int i=78; i<82; i++) {
	    monde [14][i] = '*';
	    monde [15][i] = '*';
	    monde [16][i] = '*';
	}
	
	// ALLOCATION VALEUR ET AFFICHAGE DU TABLEAU MONDE
	clearScreen();
	affichage();
	enableKeyTypedInConsole(true);

	// DEFINITION DU JEU
	while (!finish) {
	    delay(250);

	    //GESTION DE L AFFICHAGE GRAPHIQUE DES QUESTIONS
	    afficher_question(i_n1q1,n1q1,17,16,r_n1q1);    afficher_question(i_n2q1,n2q1,36,58,r_n2q1);
	    afficher_question(i_n1q2,n1q2,10,26,r_n1q2);    afficher_question(i_n2q2,n2q2,40,51,r_n2q2);
	    afficher_question(i_n1q3,n1q3,4,44,r_n1q3);     afficher_question(i_n2q3,n2q3,33,44,r_n2q3);
	    afficher_question(i_n1q4,n1q4,13,50,r_n1q4);    afficher_question(i_n2q4,n2q4,42,27,r_n2q4);
	    afficher_question(i_n1q5,n1q5,23,63,r_n1q5);    afficher_question(i_n2q5,n2q5,47,52,r_n2q5);
	    
	    afficher_question(i_n3q1,n3q1,37,99,r_n3q1);    afficher_question(i_n4q1,n4q1,17,115,r_n4q1);
	    afficher_question(i_n3q2,n3q2,46,96,r_n3q2);    afficher_question(i_n4q2,n4q2,10,128,r_n4q2);
	    afficher_question(i_n3q3,n3q3,48,10,r_n3q3);    afficher_question(i_n4q3,n4q3,8,122,r_n4q3);
	    afficher_question(i_n3q4,n3q4,39,127,r_n3q4);   afficher_question(i_n4q4,n4q4,13,107,r_n4q4);
	    afficher_question(i_n3q5,n3q5,31,125,r_n3q5); //afficher_question(i_n4q5,n4q5,16,90,r_n4q5);

	    //GESTION DU DEBLOCAGE DES NIVEAUX
	    //NIVEAU 1
	    if(n1q1 == true && n1q2 == true && n1q3 == true && n1q4 == true && n1q5 == true) {
		background("white");
		cursor(25,63); print(" "); monde[25][63] = ' ';
		cursor(25,64); print(" "); monde[25][64] = ' ';
		cursor(25,65); print(" "); monde[25][65] = ' ';
		cursor(25,66); print(" "); monde[25][66] = ' ';
		reset();
	    }
	    //NIVEAU 2
	    if(n2q1 == true && n2q2 == true && n2q3 == true && n2q4 == true && n2q5 == true) {
		background("white");
		cursor(43,75); print(" "); monde[43][75] = ' ';
		cursor(44,75); print(" "); monde[44][75] = ' ';
		cursor(45,75); print(" "); monde[45][75] = ' ';
		cursor(46,75); print(" "); monde[46][75] = ' ';
		reset();
	    }
	    //NIVEAU 3
	    if(n3q1 == true && n3q2 == true && n3q3 == true && n2q4 == true && n3q5 == true) {
		background("white");
		cursor(25,132); print(" "); monde[25][132] = ' ';
		cursor(25,133); print(" "); monde[25][133] = ' ';
		cursor(25,134); print(" "); monde[25][134] = ' ';
		cursor(25,135); print(" "); monde[25][135] = ' ';
		reset();
	    }
	    //NIVEAU 4
	    if(n4q1 == true && n4q2 == true && n4q3 == true && n4q4 == true /*&& n4q5 == true*/) {
		background("white");
		cursor(14,78); print(" "); monde[14][78] = ' ';
		cursor(15,78); print(" "); monde[15][78] = ' ';
		cursor(16,78); print(" "); monde[16][78] = ' ';
		cursor(14,79); print(" "); monde[14][79] = ' ';
		cursor(15,79); print(" "); monde[15][79] = ' ';
		cursor(16,79); print(" "); monde[16][79] = ' ';
		cursor(14,80); print(" "); monde[14][80] = ' ';
		cursor(15,80); print(" "); monde[15][80] = ' ';
		cursor(16,80); print(" "); monde[16][80] = ' ';
		cursor(14,81); print(" "); monde[14][81] = ' ';
		cursor(15,81); print(" "); monde[15][81] = ' ';
		cursor(16,81); print(" "); monde[16][81] = ' ';
		cursor(14,82); print(" "); monde[14][82] = ' ';
		cursor(15,82); print(" "); monde[15][82] = ' ';
		cursor(16,82); print(" "); monde[16][82] = ' ';
		reset();
	    }
	}
    }
}
