package abstraction.equipe2;

import java.util.HashMap;
import java.util.List;

import abstraction.commun.*;
import abstraction.equipe1.Producteur;
import abstraction.fourni.Monde;
import abstraction.fourni.v0.Marche;

public class Distances {
	//Cette classe a pour objectif d'indiques les distances entre chacun des producteurs : 
	//On en a besoin pour le calcul des couts de transport pour une livraison en cacao.
	
	private HashMap<IProducteur, Double> distances;
	
	public static Distances DISTANCES;

	public HashMap<IProducteur, Double> getDistances() {
		return distances;
	}
	
	//Un constructeur qui intialise le dictionnire � vide.
	//Il faudra remplir ce dictionnaire quand on aura les distances avec les autres IProducteur
	public Distances() {
		this.distances = new HashMap<IProducteur, Double>();
	}
	
	//Une m�thode pour ajouter un champ dans le dictionnaire
	public void ajouterproducteur(IProducteur p, double distance) {
		this.getDistances().put(p, distance);
	}
	
	public double Eloignement(IProducteur p) {
		return this.getDistances().get(p);
	}
	
	//Fin de la classe, d�but des tests.
	public static void main(String[] args) {
		//Ajout de producteurs test et remplissage du dictionnaire
		IProducteur producteurtest1 = new Producteur("producteurtest1");
		IProducteur producteurtest2 = new Producteur("producteurtest2");
		IProducteur producteurtest3 = new Producteur("producteurtest3");
		DISTANCES = new Distances();
		DISTANCES.ajouterproducteur(producteurtest1, 3000);
		DISTANCES.ajouterproducteur(producteurtest2, 5000);
		DISTANCES.ajouterproducteur(producteurtest3, 7000);
		
		//test de la m�thode ajouterproducteur
		if (DISTANCES.getDistances() == null) {
			System.out.println("la m�thode ajouterproducteur de fait rien");
			}
			else {
				if (!DISTANCES.getDistances().keySet().contains(producteurtest1)) {
					System.out.println("la m�thode ajouterproducteur n'ajoute pas de producteur");
				}
				else {
					if (DISTANCES.getDistances().get(producteurtest2) != 5000) {
						System.out.println("la m�thode ajouterproducteur n'a pas initialis� producteur test2 � 5000");;
					}
					else {
						if (DISTANCES.getDistances().keySet().size() != 3) {
							System.out.println("A�e, on a ajout� 3 producteurs, mais il y en a "+ DISTANCES.getDistances().keySet().size()+" dans DISTANCES");
						}
						else {
							System.out.println("Ok, ajouterproducteur semble correct");
						}
					}
				}
			}
		//test de la m�thode Eloignement
		if (DISTANCES.Eloignement(producteurtest1) != 3000) {
			System.out.println("A�e, Eloignement sur (producteurtest1) renvoie "+ DISTANCES.Eloignement(producteurtest1)+" au lieu de 3000");
		}
		else {
			System.out.println("Ok, Eloignement semble correct");
		}
	}
	
	//fin des tests
}
//fin de la classe
