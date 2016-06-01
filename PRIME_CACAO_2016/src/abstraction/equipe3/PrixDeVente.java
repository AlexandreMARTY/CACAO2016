package abstraction.equipe3;

import java.util.ArrayList;
import java.util.List;

import abstraction.commun.Catalogue;
import abstraction.commun.ITransformateur;
import abstraction.commun.Produit;

/* Classe qui s'occupe de g�rer les prix des diff�rents produits */

public class PrixDeVente {
	
	private ArrayList<Double> prixDeVente;   // prix différents selon le produit
	private ArrayList<Catalogue> catalogues;
	private ArrayList<Double> marge; // marge prise sur la vente des tablettes de chocolat qui diffère selon le produit (donnée en pourcentage)
	private ArrayList<ITransformateur> transfos;
	private ArrayList<Produit> produits;
	
	public PrixDeVente() {
		// TODO Auto-generated constructor stub
		this.prixDeVente = new ArrayList<Double>();
		this.catalogues = new ArrayList<Catalogue>();
		this.marge = new ArrayList<Double>();
		this.transfos=new ArrayList<ITransformateur>();
	}
	
	public void ajouterTransfo(ITransformateur t) {
		this.transfos.add(t);
	}
	public ArrayList<ITransformateur> getTransfos(){
		return this.transfos;
	}
	public ArrayList<Double> getPrixDeVente() {
		return this.prixDeVente;
	}
	public List<Catalogue> getCatalogues() {
		return this.catalogues;
	}
	public ArrayList<Double> getMarge() {
		return this.marge;
	}
	public void setMarge(ArrayList<Double> m) {
		this.marge = m;	
	}
	public double getMargeParProduit(Produit p) {
		double m;
		if (p.getNomProduit()=="50%") {
			m = 0.1;	
		}
		else {
			if (p.getNomProduit()=="60%") {
				m = 0.05;
			}
			else {
				m = 0.2;
			}
		} return m;
	}
	public double getPrixDeVenteParProduit (Produit p) {
		ArrayList<ITransformateur> l = this.getTransfos();
		double prixVente = 0;
		for (int i=0 ; i<l.size(); i++) {
			prixVente += l.get(i).getCatalogue().getTarif(p).getPrixTonne()*(1+this.getMargeParProduit(p));
		}
		return prixVente/l.size();
	}
	
	/*m�thode qui initialise PrixDeVente en ajoutant les transformateurs, les produits, les marges et les prix de vente */
	
	public void initialisePrixDeVente(Leclercv2 Leclerc){
		for (ITransformateur t : Leclerc.getTransformateurs()){
			this.ajouterTransfo(t);
		}
	}
	
	/*set le prix de vente d'un produit*/
	
	public void setPrixDeVente(Produit p){
		
	}
	
	/*utilise m�thode pr�c�dente pour un set de tous les produits*/
	
	public void setPrixDeVente() { 		 
			
	}
	
	/*m�thode appel�e dans le next de Leclerc, qui demande les catalogues et set le prix de vente et la marge de chaque produit*/
	
	public void actualisePrixDeVente(){
		
	}
}
