package abstraction.equipe4;
import abstraction.fourni.*;
import abstraction.commun.*;
import java.util.ArrayList;

public class Producteur implements Acteur,IProducteur{

	private String nom; 
	private Stock stock; 
	private Journal journal;
	private Tresorerie treso;
	private ProductionBiannuelle prodBiannu;
	private MarcheProd marcheProducteur;
	private double moyenneCoursCacao;

	//Constructeur de l'acteur Producteur 2

	public Producteur(Monde monde) {
		this.nom = Constantes.NOM_PRODUCTEUR_2;
		this.treso = new Tresorerie(this);
		this.stock = new Stock(this);
		this.journal = new Journal("Journal de "+this.nom);
		this.prodBiannu=new ProductionBiannuelle(this,1200000);
		Monde.LE_MONDE.ajouterJournal(this.journal);
		this.moyenneCoursCacao = getMoyenneCoursCacao();
	}

	// getter

	public Journal getJournal() {
		return this.journal;
	}

	public String getNom() {
		return this.nom;
	}


	public ProductionBiannuelle getProdBiannu() {
		return this.prodBiannu;
	}

	public Stock getStock() {
		return this.stock;
	}


	public Tresorerie getTreso() {
		return this.treso;
	}

	public MarcheProd getMarcheProducteur() {
		return this.marcheProducteur;
	}


	public void ajoutMarche(MarcheProd m){
		this.marcheProducteur=m;
	}

	// le next du producteur 2	
	public void next(){

		//production semi annuelle
		if (Monde.LE_MONDE.getStep()%12==1){
			// actualisation de toutes les variables du à la récolte semestrielle.
			this.getProdBiannu().production();
			this.getJournal().ajouter("Production de semi annuelle de " + this.getProdBiannu().getProductionFinale() + " en comptant les pertes de "+ this.getProdBiannu().getPerteProduction());

		}
		// modifications des stocks pour causes naturelles et prise en compte des couts de stock
		this.getStock().gererLesStock();
	}

	public double getMoyenneCoursCacao() {
		Historique coursCacao = MarcheProducteur.LE_MARCHE.getHistorique();
		//longueur du tableau regroupant les cours
		int l = coursCacao.getTaille();
		//somme des valeurs du tableau
		double M = coursCacao.get(0).getValeur();
		for (int i=1; i<l; i++) {
			M=M+coursCacao.get(i).getValeur();
		}
		return M/l;
	}

	public double offre() {
		
		
		
	// retourne un double valant la quantité disponible 
	// pour chaque transformateur a chaque step
	public double annonceQuantiteMiseEnVente() {
		return this.offre();
	}

	//Modification du stock et de la tresorerie suite a une vente
	public void venteRealisee(CommandeProduc c) {
		// modifie la tresorerie
		this.vente(c.getQuantite(), c.getPrixTonne());
		// modife les stocks
		this.getStock().reductionStock(c.getQuantite());
		// le note dans le journal
		this.getJournal().ajouter("Vente de " + c.getQuantite() + " au step numéro "+ Monde.LE_MONDE.getStep());
	}

	// ajout de le somme récolté à la trésorerie après une vente
	public void vente(double qtVendue, double prix){		
		this.getTreso().getFond().setValeur(this, this.getTreso().getFond().getValeur()+ qtVendue*prix);
	}

	public void notificationVente(CommandeProduc c) {
		this.venteRealisee(c);

	}

}