package abstraction.equipe2;



import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import abstraction.commun.*;


public class Transformation {
	
	//variables d'instance, 
	//repr�sente � l'�tape n la quantit� produite de chocolat de chaque esp�ce
	private HashMap<Produit,Double> transformation;
		
	//accesseur en lecture
	public HashMap<Produit,Double> getTransformation() {
		return this.transformation;
	}
	
	//"setter" qui permet de mettre � jour la production de produit p avec un double transformation
	//c'est cette m�thode qui va �tre appel�e par tout un tas de m�thodes du groupe
	public void setTransformation (Produit p, double transformation) {
		this.transformation.put(p, transformation);
	}
	
	//Constructeurs
	//Un constructeur g�n�ral 
	public Transformation(double chocolat50, double chocolat60, double chocolat70){
		this.transformation=new HashMap<Produit,Double>();
		this.transformation.put(Constante.PRODUIT_50, chocolat50);
		this.transformation.put(Constante.PRODUIT_60, chocolat60);
		this.transformation.put(Constante.PRODUIT_70, chocolat70);
	}
	
	//Remplit le dictionnaire avec 0 comme valeur de production pour chaque 
	//(� appeler dans le constructeur de Nestle)
	public Transformation(){
		this(0.0,0.0,0.0);
	}
	
	//Il faut maintenant d�terminer 
	//quel est le Distributeur qui a command� le plus lors de la step pr�c�dente
	//Il faut alors obtenir le total des commandes des distributeurs.
	//Cette m�thode statique prend une liste de commande en argument 
	//et renvoie un dictionnaire de IDistributeur, double 
	//indiquant la quantite totale de chocolat demande pour chaque IDistributeur
	public static HashMap<IDistributeur, Double> CommandesTotales(List<CommandeDistri> lcd) {
		HashMap<IDistributeur, Double> dictionnaire = new HashMap<IDistributeur, Double>();
		for (CommandeDistri cd : lcd) {
			dictionnaire.put(cd.getAcheteur(), 0.);
		}
		for (CommandeDistri cd : lcd) {
			double quantite = dictionnaire.get(cd.getAcheteur());
			dictionnaire.put(cd.getAcheteur(), quantite+cd.getQuantite());
		}
		return dictionnaire;
	}
	
	//La m�thode qui suit me sert � trier une liste. 
	//Elle s'av�re n�cessaire pour classe les Distributeurs
	public static void TrierDecroissant(List<Double> l) {
		List<Double> resultat = new ArrayList<Double>();
		int taille = l.size();
		Collections.sort(l);
		for (int i=0; i<taille; i++) {
			resultat.add(l.get(taille-1-i));
		}
		l =  resultat;
	}
	
	//Cette m�thode prend en argument une liste de CommandeDistri
	//et renvoie une liste de distributeur class� par ordre de priorit� de transformation
	//le premier sera celui � faire en priorit�, et ainsi de suite.
	public static List<IDistributeur> Priorite(List<CommandeDistri> lcd) {
		List<IDistributeur> priorite = new ArrayList<IDistributeur>();
		List<Double> valeurscommandes = new ArrayList<Double>();
		HashMap<IDistributeur, Double> dictionnaire = CommandesTotales(lcd);
		//Ce for permet de remplir la liste des valeurs de commandes totales.
		for (IDistributeur d : dictionnaire.keySet()) {
			valeurscommandes.add(dictionnaire.get(d));
		}
		//on trie cette liste
		TrierDecroissant(valeurscommandes);
		
		//ce for permet de remplir la liste des distributeur 
		//en s'aidant de la liste obtenue precedemment
		for (double quantite : valeurscommandes) {
			for (IDistributeur d : dictionnaire.keySet()) {
				if(dictionnaire.get(d) == quantite && !priorite.contains(d)) {
					priorite.add(d);
				}
			}
		}
		return priorite;
	}
	
	//on poss�de donc l'ordre dans lequel faire les commandes, 
	//c'est l'ordre d�fini par la liste renvoy�e par la methode ci dessus
	//maintenant, il faut executer les commandes. 
	//Pour cela il faut d'abord calculer la quantit� de cacao n�cessaire
	//prends un IDistributeur en argument, et une Liste de commandesdistri
	//calcule la quantite de cacao necessaire pour
	//la r�alisation de toutes ces commandes

	public static double CacaoNecessaire(IDistributeur dist, List<CommandeDistri> lcd) {
		double cacaonecessaire = 0.;
		for (CommandeDistri cd : lcd) {
			if (cd.getAcheteur().equals(dist)) {
				if (cd.getProduit().equals(Constante.PRODUIT_50)) {
					cacaonecessaire+=cd.getQuantite()*Constante.RATIO_TRANSFORMATION_50;
				}
				else if (cd.getProduit().equals(Constante.PRODUIT_60)) {
					cacaonecessaire+=cd.getQuantite()*Constante.RATIO_TRANSFORMATION_60;
				}
				else {
					cacaonecessaire+=cd.getQuantite()*Constante.RATIO_TRANSFORMATION_70;
				}
			}
		}
		return cacaonecessaire;
	}
	
	//Enfin, une fois que l'on a la cacao n�cessaire, 
	//il faut alors produire en tenant compte des stocks
	//Cette m�thode produit pour un Distributeur particulier
	public void setTransformation(IDistributeur d, List<CommandeDistri> lcd, StockCacao scac, StockChocolats schoc) {
		
	}
	
	//Methode permettant de savoir quel chocolat on privil�gie ( on privil�gie le chocolat 
	//que veut le meilleur acheteur du step pr�c�dent en % )
	public void repartitionChocolat(){
//A COMPLETER
	}
	
// Methode permettant de transformer le cacao en chocolat : doit retirer le cacao du stock de cacao
// doit ajouter le chocolat cr�� dans le stock de chocolat
	public void setTransformation(HashMap<Produit, Double> transformation) {
//A COMPLETER
	}
	
	
	//Methodes toString et equals
	//Pas s�r que la methode toString serve ici
	/*public String toString(){
		return "La quantite de chocolat_50% transformee est egale a "+this.getChocolat50()+" T. "+"\n"
				+"La quantite de chocolat_60% transformee est egale a "+this.getChocolat60()+" T. "+"\n"
				+"La quantite de chocolat_70% transformee est egale a "+this.getChocolat70()+" T. "+"\n";
	}*/
	
	public boolean equals(Object o){
		return false;
	}
	
	
	/**Etapes de la transformation :
	 * 
	 * 		1. Recuperer la Commande des distributeurs
	 * 
	 * 		2. Calculer la perte de cacao transformee a partir de Constante : PERTE_MINIMALE + VARIATION_PERTE
	 * 
	 * 		3. Calculer la Marge de securite a partir de Constante : MARGE_DE_SECURITE 
	 * 
	 * 		4. Prendre le cacao de Stockcacao : determiner la quantite a transformer 
	 * 			en fonction de la Commande des distributeurs + marge de securite
	 * 
	 * 		5. Transformation en chocolat : 
	 * 				ex. chocolat50% : 0.5*(cacaoATransformer) (+ 0.5*autresIngredients  
	 * 			autresIngredient toujours disponibles et compris dans COUT_TRANSFORMATION ?)
	 * 
	 * 		6. Stocker le chocolat fabrique dans Stockchocolat
	 */
	
	public double quantiteCommandeDistri(){
		
		//List<CommandeDistri> commande = this.CommandeFinale();
		/**pb de next dans les echanges distri-transfo ?*/
		return 0; //A COMPLETER
	}
	
	public double calculPerteCacao(){
		return 0; //A COMPLETER
	}
	
	public double calculMargedesecurite(){
		return 0; //A COMPLETER
	}
	
	/**Calcul du cacao a transformer en fonction de 
	 * la commande , la perte et la marge de securite
	 * 
	 * 
	 *  */
	public double cacaoATransformer(){
		double cacaoATransformer = 0;
		
		double commande = this.quantiteCommandeDistri();
		double perte = this.calculPerteCacao();
		double marge =this.calculMargedesecurite();
		
		cacaoATransformer = commande + perte + marge;
		
		return cacaoATransformer; 
	}
	
	
	/**Mise a jour du stock de cacao
	 * on retire le cacaoATransformer() de StockCacao
	 *  
	 * */
	
	public void retirerCacaoStock(){
		StockCacao stockCacao = new StockCacao();
		double cacaoATransformer = this.cacaoATransformer();
		
		;//A COMPLETER
	}
	

	/**Transformation transformerCacaoChocolat(): 
	 * methode qui recupere la quantite de cacao a transformer 
	 * puis retourne les quantites de chocolat transformes 
	 * dans un objet de type Transformation
	 * 
	 * 
	 * ps: on peut eventuellement completer en rajoutant les autres ingredients 
	 * */
	
	public void transformerCacaoChocolat(){
		
	}
	
	
	/**Mise a jour du Stock de chocolats transformes 
	 * appel methode transformerCacaoChocolat()
	 * */
	//Le stock va �tre mis � jour par la classe stock
	/*public void ajouterChocolatStock(){
		Transformation t = this.transformerCacaoChocolat();
		StockChocolats stockchocolats = new StockChocolats();
		
		stockchocolats.MiseAJourStockTransformation(Constante.PRODUIT_50, t.getChocolat50());
		stockchocolats.MiseAJourStockTransformation(Constante.PRODUIT_60, t.getChocolat60());
		stockchocolats.MiseAJourStockTransformation(Constante.PRODUIT_70, t.getChocolat70());
		;	
	}*/
	

}
