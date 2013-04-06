package com.biscato.kollaidoscope.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.biscato.kollaidoscope.model.Team;
import com.biscato.kollaidoscope.persistence.TeamDAO;

public class TeamResource {
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Team> getTeams() {
		TeamDAO teamDAO = new TeamDAO();
 		return teamDAO.getAllEntities();
	}

	@GET
	@Path("{id}")
	public Team getTeamForId(@PathParam("id") long id) {
		TeamDAO teamDAO = new TeamDAO();
		//TODO: Error handling: a) wrong id b) no id (e.g. character instead of int) c) other error
		return teamDAO.getEntityForId(id);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Team createTeam(Team team){
		//TODO: Error handling
		TeamDAO teamDAO = new TeamDAO();
		return teamDAO.createEntity(team);
	}
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	//TODO: return boolean for success or failure
	//TODO: Delete all references to deleted teams for users.
	public void deleteAllTeams(){
		TeamDAO teamDAO = new TeamDAO();
		teamDAO.deleteAllEntities();
	}
}
