package abstraction.equipe2;
import abstraction.commun.*;

import java.util.ArrayList;
//import java.util.HashMap;
import java.util.List;

import abstraction.commun.Catalogue;
import abstraction.commun.CommandeDistri;
import abstraction.commun.CommandeProduc;
//import abstraction.commun.*;
import abstraction.commun.IProducteur;
import abstraction.equipe6.Carrefour;
import abstraction.fourni.Acteur;

public class Nestle_new implements Acteur, ITransformateurP, ITransformateurD {
	
	private int etape; //indique l'�tape � laquelle on se trouve.
	
	private String nom; //le nom de l'acteur
	
	private List<IDistributeur> clients; // Liste des clients
	private List<IProducteur> fournisseurs; //Liste des fournisseurs
	
	private List<List<CommandeDistri>> historiquecommandesdistri; // Cet historique garde en m�moire les commandes que les distributeurs nous passent.
	private List<CommandeProduc> historiquecommandesprod; //Cet historique garde en m�moire les commandes que nous avons pass�es aux producteurs.
	
	private StockCacao stockcacao; //Le stock de cacao de Nestle
	private StockChocolats stockchocolat; //le stock des diff�rents chocolats de Nestle.
	private Transformation transformation; //La production de Nestle � chaque Step
	private Tresorerie tresorerie; //La tr�sorerie de Nestle.
	private List<Tresorerie> historiquetresorerie;
	private Catalogue catalogue; // Le catalogue qui permet de lancer les commandes des distributeurs
	
	//diff�rents getters utiles et setters.
	//permet d'acc�der au catalogue
	public Catalogue getCatalogue() {
		return this.catalogue;
	}
	
	
	
	//un getter pour l'historique de la tr�sorerie
	public List<Tresorerie> getHistoriquetresorerie() {
		return historiquetresorerie;
	}
	 
	//Permet d'acc�der en lecture au num�ro d'�tape
	public int getEtape() {
		return this.etape;
	}
	
	//Permet de passer d'une �tape � une autre
	public void setEtape() {
		this.etape++;
	}
	
	//retourne la liste des clients
	public List<IDistributeur> getClients() {
		return this.clients;
	}
	
	//ajoute un client � la liste
	public void ajouterClient(IDistributeur d) {
		this.clients.add(d);
	}
	
	
	//retourne la liste des fournisseurs
	public List<IProducteur> getFournisseurs() {
		return this.fournisseurs;
	}
	
	//ajoute un fournisseur � la liste
	public void ajouterFournisseurs(IProducteur p) {
		this.fournisseurs.add(p);
	}
	
	
	//Permet d'ajouter une liste de commandes de distributeurs � l'hitorique
	public void ajouterCommandeDistri(List<CommandeDistri> lcd) {
		this.historiquecommandesdistri.add(lcd);
	}
	
	//Acc�de � la liste de commande des distributeurs de l'�tape k.
	public List<CommandeDistri> getCommandeDistri(int k) {
		return this.historiquecommandesdistri.get(k);
	}
	
	//Permet d'ajouter une liste de commandes de distributeurs � l'hitorique
	public void ajouterCommandeProduc(CommandeProduc cp) {
		this.historiquecommandesprod.add(cp);
	}
	
	//Acc�de � la liste de commande des distributeurs de l'�tape k.
	public CommandeProduc getCommandeProduc(int k) {
		return this.historiquecommandesprod.get(k);
	}
	
	//Acc�de en lecture au stock de cacao.
	public StockCacao getStockcacao() {
		return stockcacao;
	}
	
	//Acc�de en lecture aux stocks de chocolats
	public StockChocolats getStockchocolat() {
		return stockchocolat;
	}

	//Acc�de en lecture � la transformation
	public Transformation getTransformation() {
		return transformation;
	}

	//Acc�de en lecture � la tr�sorerie
	public Tresorerie getTresorerie() {
		return tresorerie;
	}
	
	//permet de construire la catalogue au d�part
	//Permet de construire le catalogue (tous les tarifs sont � z�ro)
		public void Catalogue(double prix50, double prix60, double prix70) {
			this.catalogue = new Catalogue();
			Plage plage1 = new Plage(0., 500., 0);
			Plage plage2 = new Plage(500., 1000., 0.03);
			Plage plage3 = new Plage(1000., 2000., 0.05);
			Plage plage4 = new Plage(2000., 1000000000, 0.07);
			List<Plage> liste = new ArrayList<Plage>();
			liste.add(plage1); liste.add(plage2); liste.add(plage3); liste.add(plage4);
			Tarif tarif1 = new Tarif(prix50, liste);
			Tarif tarif2 = new Tarif(prix60, liste);
			Tarif tarif3 = new Tarif(prix70, liste);
			this.catalogue.add(Constante.PRODUIT_50, tarif1);
			this.catalogue.add(Constante.PRODUIT_60, tarif2);
			this.catalogue.add(Constante.PRODUIT_70, tarif3);
		}
		
		//initialise le catalogue avec les prix � 0
		public void CatalogueInitial() {
			this.Catalogue(8,8,8);
		}
		

	//M�thodes des interfaces
	
	//Interface ITransformateurP
	
	
	/*public double annonceQuantiteDemandee() {
		double resultat = 0.0;
		for (IDistributeur d : this.getCommandesdistri().keySet()) {
		 	for (CommandeDistri c : this.getCommandesdistri().get(d)) {
				resultat+=c.getQuantite()*c.getProduit().getRatioCacao()
						*(Constante.ACHAT_SANS_PERTE+(Constante.PERTE_MINIMALE + Math.random()*(Constante.VARIATION_PERTE)))
						*Constante.DEMANDE_ACTEURS;
			}
		}
		return resultat;
	}*/
	
	//M�thode annexe qui retourne la quantit� totale demand�e par une liste de commandedistributeur
	//On suppose que la liste contient que des commandes concernant PRODUIT_50; PRODUIT_60 et PRODUIT_70
	public static double QuantiteCacaoNecessaire(List<CommandeDistri> l) {
		double quantite = 0;
		for (CommandeDistri cd : l) { //Pour les commandesdistri re�ues � la step pr�c�dentes...
			quantite+=cd.getProduit().getRatioCacao()*cd.getQuantite();
		}
		return quantite;
	}
	
	//Retourne la quantit� de cacao souhait�e par Nestle. Cette m�thode est appel�e par le march�.
	//Tels que nous avons impl�ment� les stocks de cacao, il faut prendre en compte la commande des distributeurs,
	//Calculer la quantit� de cacao necessaire et y ajouter la marge de cacao souhait�e
	public double annonceQuantiteDemandee() {
		int etape = this.getEtape();
		if (etape == 0) { // Si on a pas encore re�u de commande, si rien ne s'est pass�...
			return 0;
		}
		else { //Si on a re�u des commandesdes distributeurs
			double quantitenecessaire = QuantiteCacaoNecessaire(this.getCommandeDistri(etape-1));
			double quantitestockcacao = this.getStockcacao().getStockcacao().get(Constante.CACAO);
			return (quantitenecessaire - quantitestockcacao)*(1+Constante.MARGE_DE_SECURITE)*Constante.DEMANDE_ACTEURS;
		}
	}
	
	// Declenche la mise a jour de la tresorerie de du stock de CACAO
	//et l'historique des commandes
	public void notificationVente(CommandeProduc c) {
		tresorerie.setTresorerieAchat(c);
		this.stockcacao.MiseAJourStockLivraison(Constante.CACAO,c.getQuantite());
		this.historiquecommandesprod.add(c);
	}

	//Celle ci est d�pr�ci�e. Il est inutile de la remplir
	//Methode inutile
		public double annonceQuantiteDemandee(IProducteur p) {
			return 0;
		}
		
	//M�thode d�pr�ci�e
	//m�thode inutile
		public void notificationVente(IProducteur p) {
		}

		public String getNom() {
			return this.nom;
		}



	@Override
	public List<CommandeDistri> CommandeFinale(List<CommandeDistri> list) {
		// TODO Auto-generated method stub
		return null;
	}

	public double CommandeTotaleChoco50(List<CommandeDistri> list){
	double commandechoco50totale=0;
	for(int i=0;i<list.size();i++){
		if(list.get(i).getProduit()==Constante.PRODUIT_50){
			commandechoco50totale+=list.get(i).getQuantite();
		}
	}
	return commandechoco50totale;
	}
	
	public double CommandeTotaleChoco60(List<CommandeDistri> list){
		double commandechoco60totale=0;
		for(int i=0;i<list.size();i++){
			if(list.get(i).getProduit()==Constante.PRODUIT_50){
				commandechoco60totale+=list.get(i).getQuantite();
			}
		}
		return commandechoco60totale;
		}
	
	public double CommandeTotaleChoco70(List<CommandeDistri> list){
		double commandechoco70totale=0;
		for(int i=0;i<list.size();i++){
			if(list.get(i).getProduit()==Constante.PRODUIT_50){
				commandechoco70totale+=list.get(i).getQuantite();
			}
		}
		return commandechoco70totale;
		}
	
	
	@Override
	public List<CommandeDistri> livraisonEffective(List<CommandeDistri> list) {
		List<CommandeDistri> livraisoneffective=new ArrayList<CommandeDistri>();
		
			for(int i=0;i<list.size();i++){
				if(this.CommandeTotaleChoco50(list)<=this.stockchocolat.getStockchocolats().get(Constante.PRODUIT_50)){
					if(list.get(i).getProduit()==Constante.PRODUIT_50){
						livraisoneffective.add(list.get(i));
					}else{
						if()
					}
				}
				if(this.CommandeTotaleChoco60(list)<=this.stockchocolat.getStockchocolats().get(Constante.PRODUIT_60)){
					if(list.get(i).getProduit()==Constante.PRODUIT_60)
					livraisoneffective.add(list.get(i));
				}
				if(this.CommandeTotaleChoco70(list)<=this.stockchocolat.getStockchocolats().get(Constante.PRODUIT_70)){
					if(list.get(i).getProduit()==Constante.PRODUIT_70)
					livraisoneffective.add(list.get(i));
				}
				
				
			}
			
			// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CommandeDistri> offre(List<CommandeDistri> list) {
		List<CommandeDistri> offre = new ArrayList<CommandeDistri>();
		for(int i=0;i<list.size();i++){
			if(list.get(i).getProduit()==Constante.PRODUIT_50){
				if(stockchocolat.getStockchocolats().get(Constante.PRODUIT_50)>=0.5*list.get(i).getQuantite()){
					offre.add(list.get(i));
				}else{
					CommandeDistri offrealternative=new CommandeDistri
							(list.get(i).getAcheteur(),list.get(i).getProduit(),list.get(i).getQuantite(),list.get(i).getPrixTonne());
					offre.add(offrealternative);
				}
			}else{
				if(list.get(i).getProduit()==Constante.PRODUIT_60){
					if(stockchocolat.getStockchocolats().get(Constante.PRODUIT_60)>=0.5*list.get(i).getQuantite()){
						offre.add(list.get(i));
					}else{
						CommandeDistri offrealternative=new CommandeDistri
								(list.get(i).getAcheteur(),list.get(i).getProduit(),list.get(i).getQuantite(),list.get(i).getPrixTonne());
						offre.add(offrealternative);
					}
				}else{
					if(list.get(i).getProduit()==Constante.PRODUIT_70){
						if(stockchocolat.getStockchocolats().get(Constante.PRODUIT_70)>=0.5*list.get(i).getQuantite()){
							offre.add(list.get(i));
						}else{
							CommandeDistri offrealternative=new CommandeDistri
									(list.get(i).getAcheteur(),list.get(i).getProduit(),list.get(i).getQuantite(),list.get(i).getPrixTonne());
							offre.add(offrealternative);
						}
					}else{
						System.out.println("Le produit que vous demandez n'est pas disponible");
					}
				}
			}
		}
		return offre;
	}

	//M�thode d�pr�ci�e
	//M�thode inutile
	public List<CommandeDistri> Offre(List<CommandeDistri> o) {
		return null;
	}

	//Annonce le prix auquel on propose d'acheter le cacao
	//Pour simplifier, on ach�te eu prix du march� avec de l'al�atoire 
	//renvoie le prix du march� + ou - 10%
	public double annoncePrix() {
		double alea = Math.random()*0.2-0.1;
		return MarcheProducteur.LE_MARCHE.getCours()*alea;
	}
	
	public void next() {
		// TODO Auto-generated method stub
	}
	
	
	//D�but des tests sur la classe Nestl�
	//Il ne faudra tester que les m�thodes de l'interface, les autres �tant �videntes
	public static void main(String[] args) {
		Nestle_new nestle = new Nestle_new();
		System.out.println(nestle.annoncePrix());
		System.out.println(nestle.annoncePrix());
		System.out.println(nestle.annoncePrix());
		System.out.println("si les trois fluctuent entre le cours du march� +-10% alors le test est bon");
		
		
		
		//Test de la m�thode offre :
		
		StockChocolats stockchoco=new StockChocolats();
		List<CommandeDistri> list= new ArrayList<CommandeDistri>();
		Produit prod=new Produit("Chocolat80",0.8);
		Carrefour c1=new Carrefour();
		Carrefour c2=new Carrefour();
		Carrefour c3=new Carrefour();
		CommandeDistri cd1= new CommandeDistri(c1,Constante.PRODUIT_50,200,3000);
		CommandeDistri cd2= new CommandeDistri(c2,Constante.PRODUIT_50,20,1100);
		CommandeDistri cd3= new CommandeDistri(c3,Constante.PRODUIT_50,500,2050);
		CommandeDistri cd4= new CommandeDistri(c1,Constante.PRODUIT_60,100,1400);
		CommandeDistri cd5= new CommandeDistri(c2,Constante.PRODUIT_70,70,2000);
		CommandeDistri cd6= new CommandeDistri(c3,prod,50,1800);
		list.add(cd1);
		list.add(cd2);
		list.add(cd3);
		list.add(cd4);
		list.add(cd5);
		list.add(cd6);
		stockchoco.MiseAJourStockTransformation(Constante.PRODUIT_50, 30);
		if(nestle.offre(list).size()==6){
			System.out.println("Erreur,on accepte de livrer des produits dont on ne dispose pas");
		}else{
			if(nestle.offre(list).get(0).getQuantite()==200){
				System.out.println("Erreur, on accepte de tout livrer alors que le stock de chocolatn'est pas suffisant");
			}else{
				if(nestle.offre(list).get(1).getQuantite()==10){
					System.out.println("Erreur, la commande des ditributeurs est tout le temps divis�e par deux");
				}else{
					if(nestle.offre(list).get(3).getProduit()!=Constante.PRODUIT_60){
						System.out.println("On ajoute pas le bon produit dans le commande, probl�me ajout PRODUIT_60");
					}else{
						if(nestle.offre(list).get(0).getProduit()!=Constante.PRODUIT_50){
							System.out.println("On ajoute pas le bon produit dans la commande, probl�me ajout PRODUIT_50 ");
						}else{
							if(nestle.offre(list).get(4).getProduit()!=Constante.PRODUIT_70){
								System.out.println("On ajoute pas le bon produit dans la commande, probl�me ajout PRODUIT_70");
							}else{
								System.out.println("Il semble que la m�thode fonctionne");
							}
						}
					}
				}
			}
		}
	}
}
