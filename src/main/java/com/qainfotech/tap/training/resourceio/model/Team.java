package com.qainfotech.tap.training.resourceio.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Ramandeep RamandeepSingh AT QAInfoTech.com
 */
public class Team {

	private final String name;
	private final Integer id;
	private final List<Individual> members;

	public Team(Map<String, Object> teamMap) {
       name = teamMap.get("name").toString();
   id = (Integer) teamMap.get("id");
   members = (List<Individual>) teamMap.get("members");
	}

	/**
	 * get team name
	 *
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * get team id
	 *
	 * @return
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * get list of individuals that are members of this team
	 *
	 * @return
	 */
	public List<Individual> getMembers() {
		return members;
	}

	/**
	 * get a list of individuals that are members of this team and are also
	 * active
	 *
	 * @return
	 */
	public List<Individual> getActiveMembers() {

		List<Individual> list1 = new ArrayList<Individual>();
		Iterator<Individual> itr = members.iterator();
		while (itr.hasNext()) {
			Individual ind = itr.next();
			Boolean a = true;

			Boolean b = ind.isActive();

			if (a == b) {

				list1.add(ind);
			}

		}

		return list1;
	}

	/**
	 * get a list of individuals that are members of this team but are inactive
	 *
	 * @return
	 */
	public List<Individual> getInactiveMembers() {

		List<Individual> list1 = new ArrayList<Individual>();

		Iterator<Individual> itr = members.iterator();
		while (itr.hasNext()) {
			Individual ind = itr.next();
			Boolean a = false;
			Boolean b = ind.isActive();

			if (a == b) {
				list1.add(ind);
			}

		}
		return list1;

	}
}
