package fr.treeptik.criteria.test;

import java.util.List;

import fr.treeptik.criteria.orm.Condition;
import fr.treeptik.criteria.orm.Criteria;
import fr.treeptik.criteria.pojo.Personne;

public class Runtime {

	public static void main(String[] args) {

		Criteria<Personne> criteria = new Criteria<Personne>(Personne.class);
		
		List<Personne> list = criteria.add(Condition.between("age",3, 7)).list();
		//List<Personne> listinf = criteria.add(Condition.inf("age", 10)).list();
		
		
		for (Personne personne : list) {
			System.out.println(personne.getNom());
			//System.out.println(personne.getPrenom());
			
		}
		
	}

}
