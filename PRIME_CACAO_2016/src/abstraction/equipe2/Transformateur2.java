package abstraction.equipe2;

import abstraction.fourni.Acteur;
import abstraction.fourni.Indicateur;
import abstraction.fourni.Monde;

public class Transformateur2 implements Acteur, ITransformateur2, IVendeur2{
	
	private String nom;
	private Indicateur achats;
	private Indicateur ventes;
	private Indicateur solde;

	public Transformateur2(String nom, Monde monde) {
		this.nom = nom;
		this.achats = new Indicateur("Achats de "+this.nom, this, 0.0);
		this.ventes = new Indicateur("Ventes de "+this.nom, this, 0.0);
		this.solde = new Indicateur("Solde de "+this.nom, this, 10000000.0);
		Monde.LE_MONDE.ajouterIndicateur( this.achats );
		Monde.LE_MONDE.ajouterIndicateur( this.ventes );
		Monde.LE_MONDE.ajouterIndicateur( this.solde );
	}
	
	public String getNom() {
		return "Producteur "+this.nom;
	}

	public void next() {
		this.ventes.setValeur(this, 0.0);
	}
	
	
	//ce code calcule le cout de revient et le cout de revient unitaire de Nestl� France !
	//p en euros, q en kilos
	public static double[] CoutInts (double p, double q){ 
		double[] CI =new double[2] ;
		CI[0] = 13003370+q*(5+p);
		CI[1] = CI[0]*0.6/q; // 600g de cacao �quivalent � 1kg de chocolat
		return CI;
	}
	
	// le stosk � l'instant t d�pend de la quantit� demand� pour l'instant t+2 
	//et de la quantit� produite pour l'instant t+1
	public static double stock_cacao (int[] T) {
		int s = T[2] - T[1];
		return s;
	}
	
	//la quantit� demand�e aux producteurs est proportionnelle 
	//� la quantit� de chocolat que nous demande les distributeurs.
	public static double quantiteDemandee (double qdd) {
		double qdp = 0.6*qdd;
		return qdp;  
	}
	
	//Le prix du kilo de chocolat �tant fix�, tout ce que l'on peut calculer c'est la marge que l'on se fait.
	public static double Marge (double cru) {
		double M = ((15-cru)/cru)*100;
		return M;
	}
	
	//M�thode principale de test de CoutInts, d�f�aire les "/*" pour l'activer
	    public static void main(String[] args) {
		int p = 3;
		int q = 1153000;
		double[] CI = CoutInts(p,q);
		System.out.println(CI.length);
		System.out.println("le cout de revient de Nestl� France � la p�riode t est de "+CI[0]);
		System.out.println("le cout de revient unitaire de Nestl� France � la p�riode t est de "+CI[1]);
		
		System.out.println("la marge sur couts directs que Nestl� se fait est de : "+Marge(CI[1])+"%");
		
		double s0 = 300.6;
		int qd = 100;
		int qp = 200;
		System.out.println("le stock � l'instant t est de " + stock(s0, qd, qp));
		
		double qdd = 30000;
		System.out.println("la quantit� demand�e est de "+quantit�Demand�e(qdd));
		
		
	}
	
	

}
