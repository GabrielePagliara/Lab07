package it.polito.tdp.poweroutages.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.poweroutages.model.Nerc;
import it.polito.tdp.poweroutages.model.PowerOutages;

public class PowerOutageDAO {

	public List<Nerc> getNercList() {

		String sql = "SELECT id, value FROM nerc";
		List<Nerc> nercList = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Nerc n = new Nerc(res.getInt("id"), res.getString("value"));
				nercList.add(n);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return nercList;
	}

	public List<PowerOutages> getPowerOutagesList(int id_nerc) {

		String sql = "SELECT YEAR(date_event_began) AS year, date_event_began, date_event_finished, HOUR(TIMEDIFF(date_event_finished,date_event_began)) AS hour, customers_affected "
				+ "FROM poweroutages " + "WHERE nerc_id = ?";

		List<PowerOutages> poList = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, id_nerc);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				PowerOutages p = new PowerOutages(res.getInt("year"), res.getTimestamp("date_event_began"),
						res.getTimestamp("date_event_finished"), res.getInt("hour"), res.getInt("customers_affected"));
				poList.add(p);
			}

			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return poList;
	}

}
