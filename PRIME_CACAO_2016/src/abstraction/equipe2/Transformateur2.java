package abstraction.equipe2;

import abstraction.fourni.Acteur;
import abstraction.fourni.Indicateur;
import abstraction.fourni.Monde;
import abstraction.fourni.v0.Marche;

import java.util.ArrayList;
import java.util.List;

import abstraction.commun.*;;

public class Transformateur2 implements Acteur, ITransformateur{
	
	private String nom;
	private Indicateur achats;
	private Indicateur ventes;
	private Indicateur solde;
	
	public Transformateur2(Monde monde) {
		this.nom = Constantes.NOM_TRANSFORMATEUR_1;
		this.achats = new Indicateur("Achats de "+this.nom, this, 0.0);
		this.ventes = new Indicateur("Ventes de "+this.nom, this, 0.0);
		this.solde = new Indicateur("Solde de "+this.nom, this, 10000000.0);
		Monde.LE_MONDE.ajouterIndicateur( this.achats );
		Monde.LE_MONDE.ajouterIndicateur( this.ventes );
		Monde.LE_MONDE.ajouterIndicateur( this.solde );		
	}
	
	public static final Stock stock_cacao=new Stock();
	public static final Stock stock_chocolat=new Stock();
	public static final Banque tresorerie=new Banque();
	public static final Commandes commandes = new Commandes();
	

	public String getNom() {
		return "Producteur "+this.nom;
	}
	
	public static double prixDeVente(){
		return 15.0;
	}
	
	private List<IProducteur> getProducteurs() {
		List<IProducteur> prod = new ArrayList<IProducteur>();
		for (Acteur a : Monde.LE_MONDE.getActeurs()) {
			if (a instanceof IProducteur) {
				prod.add((IProducteur)(a));
			}
		}
		return prod;
	}
	
	public double annonceQuantiteDemandee(IProducteur p) {
		if(MondeV1.LE_MONDE.getActeur(Constantes.NOM_PRODUCTEUR_1)==p){
			return Math.min(commandes.quantiteDemandee(0.3), p.annonceQuantiteMiseEnVente(this)) ;
		}
		else{
			if(MondeV1.LE_MONDE.getActeur(Constantes.NOM_PRODUCTEUR_2)==p){
				return Math.min(commandes.quantiteDemandee(0.3), p.annonceQuantiteMiseEnVente(this)) ;
			}
			else{
				return 0.0;
			}
		}
	}
	
	private List<IDistributeur> getDistributeurs() {
		List<IDistributeur> distributeurs = new ArrayList<IDistributeur>();
		for (Acteur a : Monde.LE_MONDE.getActeurs()) {
			if (a instanceof ITransformateur) {
				distributeurs.add((IDistributeur)(a));
			}
		}
		return distributeurs;
	}

	
	public void notificationVente(IProducteur p) {
		double commande = this.annonceQuantiteDemandee(p);
		this.solde.setValeur(this, this.solde.getValeur()-p.annoncePrix()*commande);
		stock_cacao.ajout_cacao();
	}
	
	

	

	//M�thode principale de test de CoutInts, d�faire les "/*" pour l'activer
	public void next() {
		double qdd = 0;
		for (IDistributeur d : this.getDistributeurs()) {
		qdd += d.getDemande(this);
		}
		commandes.setCommandes(qdd);
		for (IProducteur p : this.getProducteurs()) {
			
		commandes.quantiteDemandee (0.3);
		notificationVente(p);
		}
		commandes.quantiteDemandeeMonde(0.4);
		stock_chocolat.ajout_chocolat();
		tresorerie.Tresorerie(this.getProducteurs().get(0), this.getProducteurs().get(1));
	}
}
	/*
		//setT(qdd);
		quantiteDemandee(T, 0.3);
		quantiteDemandee(T, 0.3);
		quantiteDemandee(T, 0.4);
		setS1(T);
		setS2(T);
		stock_cacao(T, S1);
		stock_chocolat(T, S2);
	}*/
	/*    public static void main(String[] args) {
=======
>>>>>>> branch 'master' of https://github.com/AlexandreMARTY/CACAO2016.git
	    /*public static void main(String[] args) {
>>>>>>> branch 'master' of https://github.com/AlexandreMARTY/CACAO2016.git
		double p = 3;
		double prixdevente=15;
		System.out.println("La longueur du tableau CI est de :" + CI.length);


		System.out.println("la quantite de cacao achetee est "+0.6*T[2] +"kg de cacao");
		System.out.println("la quantite de chocolat demandee par les distributeurs est"+ T[3]+"kg de chocolat");
		System.out.println("la quantite de cacao transformee en chocolat � cet step est de "+ 0.6*T[1]+"kg");
		System.out.println("la quantite de chocolat livre est de" +T[0] + "kg");
		
		

		if (stock_cacao(T,S1)<0){

			System.out.println("Erreur dans le syst�me");
		}else{
			System.out.println("Le stock de cacao semble valide");
		}
		
		if (stock_chocolat(T,S2)<0) {
			System.out.println("Erreur dans le syst�me");
		}else{
			System.out.println("Le stock de chocolat semble valide");
		}
	
		
		for(int i=0;i<4;i++){
			if (T[i]<0){
				System.out.println("Erreur dans les valeurs du tableau des demandes");
			}else {
				if (T[i]==0){
					System.out.println("Gr�ve des distributeurs");
				}else{
					System.out.println("Les demandes semblent valides");
				}
			}
		}
		
		//Pour nous permettre de savoir si on a mit le bon prix pour les co�ts d'achats du cacao et de vente du chocolat
		
		if (Marge(prixdevente,p,T)<0){
			System.out.println("Vente � perte -> Acheter moins cher ou vendre plus cher");
		}else{
			if (Marge(prixdevente,p,T)<30){
				System.out.println("Les prix d'achats du cacao et les prix de vente du chocolat sont bons ");
			}else{
				System.out.println("On peut baisser les prix de vente du chocolat");
			}
		}
		*/

