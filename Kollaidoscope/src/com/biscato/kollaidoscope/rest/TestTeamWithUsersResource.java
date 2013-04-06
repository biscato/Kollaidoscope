package com.biscato.kollaidoscope.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.biscato.kollaidoscope.model.User;
import com.biscato.kollaidoscope.persistence.UserDAO;
import com.biscato.kollaidoscope.model.Team;
import com.biscato.kollaidoscope.persistence.TeamDAO;

@Path("/teamAndUsers")
public class TestTeamWithUsersResource {

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public void createTeamWithUsers(){

		Team team = new Team();
		team = createTeam();
		team = persistTeam(team);
		
		ArrayList<User> users = new ArrayList<User>();
		long[] teams = new long[1];
		teams[0] = team.getTeamId();
		users = createUsersForTeams(teams);
		users = (ArrayList<User>)persistUsers(users);

	}
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	//TODO: return boolean for success or failure
	public void deleteAllTeamsAndUsers(){
		deleteUsers();
		deleteTeams();
	}

	private void deleteTeams() {
		TeamDAO teamDAO = new TeamDAO();
		teamDAO.deleteAllEntities();
	}

	private void deleteUsers() {
		UserDAO userDAO = new UserDAO();
		userDAO.deleteAllEntities();
	}
	
	private Team createTeam(){
		Team team = new Team();
		
		team.setActive(true);
		team.setCompany("biscato");
		team.setEmail("contact@biscato.com");
		team.setName("Office of the CTO");
		team.setTeamId(1);
		team.setWebsite("http://www.biscato.com");
		
		return team;
	}
	
	private Team persistTeam(Team team){
		TeamDAO teamDAO = new TeamDAO();
		return teamDAO.createEntity(team);
	}
	
	private List<User> persistUsers(ArrayList<User> users){
		UserDAO userDAO = new UserDAO();
		return userDAO.createAllEntities(users);
	}
	
	private ArrayList<User> createUsersForTeams(long[] teams){

		ArrayList<User> users = new ArrayList<User>();
		User usr = new User();
		
		usr.setCompany("SAP");
		usr.setEmail("janruessel@gmail.com");
		usr.setFirstName("Jan");
		usr.setLastName("Rüssel");
		usr.setMemberInTeam(teams);
		usr.setRole("teamlead");
		usr.setUserId(1);
		users.add(usr);
		usr = null;
		
		usr = new User();
		usr.setCompany("biscato");
		usr.setEmail("florian.liesenfeld@gmail.com");
		usr.setFirstName("Florian");
		usr.setLastName("Liesenfeld");
		usr.setMemberInTeam(teams);
		usr.setRole("member");
		usr.setUserId(2);
		users.add(usr);
		usr = null;

		usr = new User();
		usr.setCompany("GM");
		usr.setEmail("su@gmail.com");
		usr.setFirstName("Su");
		usr.setLastName("Wieeigentlich");
		usr.setMemberInTeam(teams);
		usr.setRole("member");
		usr.setUserId(3);
		users.add(usr);
		usr = null;
		
		//TODO: implement user validity form and to
	
		return users;
		
	}
}
