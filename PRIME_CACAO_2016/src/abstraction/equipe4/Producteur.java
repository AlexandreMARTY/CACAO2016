package abstraction.equipe4;
import abstraction.fourni.*;
import java.util.ArrayList;
import abstraction.commun.*;
public class Producteur implements Acteur,IProducteur{

	private String nom; 
	private Stock stock; 
	private Journal journal;
	//production semi annuelle
	private double prod; 
	private Tresorerie treso;
	private ProductionBiannuelle prodBiannu; 
	
	private ArrayList<ITransformateur> transformateurs;
	
	//Constructeur de l'acteur Producteur 2
	
    public Producteur(Monde monde) {
       this.nom = Constantes.NOM_PRODUCTEUR_2;
	   this.treso = new Tresorerie(this);
	   this.stock = new Stock(this);
       this.prodBiannu = new ProductionBiannuelle(1200000);
       this.journal = new Journal("Journal de "+this.nom);
       this.prod=1200000;
       this.transformateurs= new ArrayList<ITransformateur>();
       for (Acteur a : Monde.LE_MONDE.getActeurs()) {
			if (a instanceof ITransformateur) {
				this.transformateurs.add((ITransformateur)(a));
			}
		}       
       Monde.LE_MONDE.ajouterJournal(this.journal);
       Monde.LE_MONDE.ajouterIndicateur(this.pertes);
    }


    // getter
    
    
    public Journal getJournal() {
		return this.journal;
	}
    
    public String getNom() {
		return this.nom;
	}
    

    public double getProd() {
		return this.prod;
	}
    
    public ProductionBiannuelle getProdBiannu() {
		return this.prodBiannu;
	}
    
    public Stock getStock() {
		return this.stock;
	}
    
    public ArrayList<ITransformateur> getTransformateurs() {
		return this.transformateurs;
	}
    
    public Tresorerie getTreso() {
		return this.treso;
	}

	// le next du producteur 2	
	public void next(){
		
		//production semi annuelle
		if (Monde.LE_MONDE.getStep()%12==1){ 
			this.getProdTotaleUtilisable().setValeur(this,this.prod-this.pertes.getValeur());
			this.augmentationStock(this.getProdTotaleUtilisable().getValeur());
			this.journal.ajouter("Production de semi annuelle de " + this.getProdTotaleUtilisable());
		}
		//Commandes			
		this.stock.setPerteStock();
		this.stock.perteDeStock();
		for (ITransformateur t : this.transformateurs){
			double qtVendu = t.annonceQuantiteDemandee(this);
			t.notificationVente(this);
			this.venteRealisee(qtVendu, (Acteur)t);
		}
	}


	// return un double valant la quantité disponible 
	//pour chaque transformateur a chaque step
	public double annonceQuantiteMiseEnVente(ITransformateur t) {
		return (this.stock.getStock().getValeur()/(13-Monde.LE_MONDE.getStep()%12));
	}
	

	//Modification du stock et de la tresorerie suite a une vente
	public void venteRealisee(double qtVendue,Acteur a) {
		this.getTreso().setFond(qtVendue);
		this.stock.reductionStock(qtVendue);
		this.journal.ajouter("Vente de " + qtVendue+" auprès de " + a.getNom() + " au step numéro "+ Monde.LE_MONDE.getStep());
	}


	//Ventes réalisées auprès du transformateur "Le reste du Monde"
	
	public void venteResteMonde(){
		double alea = Math.random()*(0.9-0.87)+0.87;
		
}
