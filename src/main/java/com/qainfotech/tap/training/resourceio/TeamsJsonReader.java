package com.qainfotech.tap.training.resourceio;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

//import org.json.JSONArray;
//import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import com.qainfotech.tap.training.resourceio.exceptions.ObjectNotFoundException;
import com.qainfotech.tap.training.resourceio.model.Individual;
import com.qainfotech.tap.training.resourceio.model.Team;

/**
 *
 * @author Ramandeep RamandeepSingh AT QAInfoTech.com
 */
public class TeamsJsonReader {

	/**
	 * get a list of individual objects from db json file
	 *
	 * @return
	 */
	JSONObject jo;
	List<Individual> listind;
	List<Team> listtem;

	public TeamsJsonReader() {
		try {
			FileReader fr = new FileReader(
					new File("D:\\assignment-resource-io-master\\src\\main\\resources\\db.json"));
			JSONParser parser = new JSONParser();
			Object ob = parser.parse(fr);
			JSONObject jo = (JSONObject) ob;
			listind = new ArrayList<Individual>();
			JSONArray ja = (JSONArray) jo.get("individuals");
			JSONObject job[] = new JSONObject[ja.size()];
			for (int i = 0; i < ja.size(); i++) {
				job[i] = (JSONObject) ja.get(i);
				Integer id = ((Long) job[i].get("id")).intValue();
				String name = job[i].get("name").toString();
				Boolean active = (Boolean) job[i].get("active");
				Map<String, Object> map = new HashMap();
				map.put("id", id);
				map.put("name", name);
				map.put("active", active);
				Individual ind = new Individual(map);
				listind.add(ind);

			}
			listtem = new ArrayList<Team>();
			List<Individual> list1;

			JSONArray jaa = (JSONArray) jo.get("teams");
			JSONObject joa[] = new JSONObject[jaa.size()];
			for (int i = 0; i < jaa.size(); i++) {
				list1 = new ArrayList<Individual>();
				joa[i] = (JSONObject) jaa.get(i);
				Integer id = ((Long) joa[i].get("id")).intValue();
				String name = joa[i].get("name").toString();

				JSONArray jas = (JSONArray) joa[i].get("members");
				for (int j = 0; j < jas.size(); j++) {
					Integer idd = ((Long) jas.get(j)).intValue();
					Individual ind = getIndividualById(idd);
					list1.add(ind);

				}
				System.out.println();
				Map<String, Object> map = new HashMap();

				map.put("id", id);
				map.put("name", name);
				map.put("members", list1);

				Team tem = new Team(map);
				listtem.add(tem);

			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public List<Individual> getListOfIndividuals() {

		return listind;
	}

	/**
	 * get individual object by id
	 *
	 * @param id
	 *            individual id
	 * @return
	 * @throws com.qainfotech.tap.training.resourceio.exceptions.ObjectNotFoundException
	 */
	public Individual getIndividualById(Integer id) throws ObjectNotFoundException {

		Individual ind = null;
		int flag = 0;
		Iterator<Individual> itr = listind.iterator();
		while (itr.hasNext()) {
			ind = itr.next();
			int a = id;
			int b = ind.getId();
			if (a == b) {
				flag = 1;
				break;
			}

		}
		if (flag == 1)
			return ind;
		else
			throw new ObjectNotFoundException("Individual", "id", id.toString());

	}

	/**
	 * get individual object by name
	 *
	 * @param name
	 * @return
	 * @throws com.qainfotech.tap.training.resourceio.exceptions.ObjectNotFoundException
	 */
	public Individual getIndividualByName(String name) throws ObjectNotFoundException {

		Individual ind = null;
		Iterator<Individual> itr = listind.iterator();
		int flag = 0;

		while (itr.hasNext()) {
			ind = itr.next();
			String a = name;
			String b = ind.getName();

			if (a.equalsIgnoreCase(b)) {
				flag = 1;
				break;
			}

		}
		if (flag == 0)
			throw new ObjectNotFoundException("Individual", "name", name);
		else
			return ind;

	}

	/**
	 * get a list of individual objects who are not active
	 *
	 * @return List of inactive individuals object
	 */
	public List<Individual> getListOfInactiveIndividuals() {
		List<Individual> list1 = new ArrayList<Individual>();
		Iterator<Individual> itr = listind.iterator();
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

	/**
	 * get a list of individual objects who are active
	 *
	 * @return List of active individuals object
	 */
	public List<Individual> getListOfActiveIndividuals() {

		List<Individual> list1 = new ArrayList<Individual>();

		Iterator<Individual> itr = listind.iterator();
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
	 * get a list of team objects from db json
	 *
	 * @return
	 */
	public List<Team> getListOfTeams() {

		return listtem;
	}
}
