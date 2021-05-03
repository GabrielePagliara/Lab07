package it.polito.tdp.poweroutages.model;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.poweroutages.DAO.PowerOutageDAO;

public class Model {

	PowerOutageDAO podao;

	private List<PowerOutages> best;
	private List<PowerOutages> allCases;

	public Model() {
		podao = new PowerOutageDAO();
	}

	public List<Nerc> getNercList() {
		return podao.getNercList();
	}

	public List<PowerOutages> getPowerOutagesList(int id_nerc) {
		return podao.getPowerOutagesList(id_nerc);
	}

	public List<PowerOutages> worstCaseAnalisys(int id_nerc, int x, int y) {
		this.best = null;

		List<PowerOutages> allCases = new ArrayList<>(getPowerOutagesList(id_nerc));

		List<PowerOutages> parziale = new ArrayList<>();
		sequenza(allCases, 0, parziale, x, y);
		return best;

	}

	int h = 0;
	boolean entrato = false;

	private void sequenza(List<PowerOutages> casesList, int livello, List<PowerOutages> parziale, int oreMax,
			int anniMax) {

		// condizione terminale
		if ((livello == casesList.size()) && (livello != 0)) {
			Double numCostumers = calcolaCostumers(parziale);
			if (best == null || numCostumers > calcolaCostumers(best))
				best = new ArrayList<>(parziale);
		} else {
			for (PowerOutages p : casesList) {
				if (p.getHour() < oreMax && entrato == false) {
					if (controllo(p, parziale, anniMax, oreMax)) {
						h = h + p.getHour();
						if (h >= oreMax) {
							entrato = true;
							Double numCostumers = calcolaCostumers(parziale);
							if (best == null || numCostumers > calcolaCostumers(best))
								best = new ArrayList<>(parziale);
						} else {
							parziale.add(p);
						}
						sequenza(casesList, livello + 1, parziale, oreMax, anniMax);
						parziale.remove(p);
						h = h - p.getHour();
						entrato = false;
					}
				}
			}

		}

	}

	private boolean controllo(PowerOutages p, List<PowerOutages> parziale, int Y, int oreMax) {
		// controlliamo il primo caso appena entra che non possiamo confrontare gli anni
		if (parziale.isEmpty())
			return true;

		// confrontiamo il primo elemento della lista con l'oggetto che passiamo
		for (PowerOutages po : parziale) {
			if (po.equals(p))
				return false;
		}

		for (PowerOutages po : parziale) {
			if ((po.getYear() - p.getYear()) <= Y)
				return true;
			else
				return false;
		}

		return false;
	}

	private Double calcolaCostumers(List<PowerOutages> parziale) {
		double numTot = 0.0;
		for (PowerOutages po : parziale) {
			numTot = numTot + po.getCustomers();
		}
		return numTot;
	}

}
