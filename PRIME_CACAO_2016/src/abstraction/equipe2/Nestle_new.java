package abstraction.equipe2;

import java.util.List;

import abstraction.commun.Catalogue;
import abstraction.commun.CommandeDistri;
import abstraction.commun.CommandeProduc;
import abstraction.commun.*;
import abstraction.commun.IProducteur;
import abstraction.commun.ITransformateur;
import abstraction.fourni.Acteur;

public class Nestle_new implements Acteur, ITransformateur {
	
	private int etape; //indique l'�tape � laquelle on se trouve.
	
	private List<IDistributeur> clients; // Liste des clients
	private List<IProducteur> fournisseurs; //Liste des fournisseurs
	
	private List<List<CommandeDistri>> historiquecommandesdistri; // Cet historique garde en m�moire les commandes que les distributeurs nous passent.
	private List<CommandeProduc> historiquecommandesprod; //Cet historique garde en m�moire les commandes que nous avons pass�es aux producteurs.
	
	private StockCacao stockcacao; //Le stock de cacao de Nestle
	private StockChocolats stockchocolat; //le stock des diff�rents chocolats de Nestle.
	private Transformation transformation; //La production de Nestle � chaque Step
	private Tresorerie tresorerie; //La tr�sorerie de Nestle.
	
	//diff�rents getters utiles et setters.
	
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
	public void getCommandeProduc(int k) {
		this.historiquecommandesprod.get(k);
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

	
	//M�thodes des interfaces
	
	//Interface ITransformateurP
	
	//M�thode annexe qui retourne la quantit� totale demand�e par une liste de commandedistributeur
	//On suppose que la liste contient que des cmmandes concernant PRODUIT_50; PRODUIT_60 et PRODUIT_70
	public static double QuantiteCacaoNecessaire(List<CommandeDistri> l) {
		double quantite = 0;
		for (CommandeDistri cd : l) { //Pour les commandesdistri re�ues � la step pr�c�dentes...
			if (cd.getProduit() == Constante.PRODUIT_50) { //Pour du chocolat � 50%
				quantite+=cd.getQuantite()*0.5;
			}
			else if (cd.getProduit() == Constante.PRODUIT_60) {
				quantite+=cd.getQuantite()*0.6;
			}
			else  {
				quantite+=cd.getQuantite()*0.7;
			}
		}
		return quantite;
	}
	
	//Retourne la quantit� de cacao souhait�e par Nestle. Cette m�thode est appel�e par le march�.
	//Tels que nous avons impl�ment� les stocks de cacao, il faut prendre en compte la commande des distributeurs,
	//Calculer la quantit� de cacao necessaire et y ajouter la marge de cacao souhait�e
	@Override
	public double annonceQuantiteDemandee() {
		int etape = this.getEtape();
		if (etape == 0) { // Si on a pas encore re�u de commande, si rien ne s'est pass�...
			return 0;
		}
		else { //Si on a re�u des commandesdes distributeurs
			double quantitenecessaire = QuantiteCacaoNecessaire(this.getCommandeDistri(etape-1));
			double quantitestockcacao = this.getStockcacao().getStockcacao().get(Constante.CACAO);
			return (quantitenecessaire - quantitestockcacao)*(1+Constante.MARGE_DE_SECURITE);
		}
	}

	//Celle ci est d�pr�ci�e. Il est inutile de la remplir
	@Override
	public double annonceQuantiteDemandee(IProducteur p) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double annoncePrix() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Catalogue getCatalogue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void notificationVente(CommandeProduc c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notificationVente(IProducteur p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<CommandeDistri> CommandeFinale(List<CommandeDistri> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CommandeDistri> livraisonEffective(List<CommandeDistri> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CommandeDistri> Offre(List<CommandeDistri> o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CommandeDistri> offre(List<CommandeDistri> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getNom() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void next() {
		// TODO Auto-generated method stub
		
	}
	

}
