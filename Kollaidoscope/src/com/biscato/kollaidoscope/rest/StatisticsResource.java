package com.biscato.kollaidoscope.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.biscato.kollaidoscope.model.TeamResult;

@Path("/statistics")
public class StatisticsResource {

//	@GET
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public TeamResult getTeamResults(){
//		return new TeamResult();
//	}
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public TeamResult getTeamResults(
			@DefaultValue("0") @QueryParam("teamid") int teamId, 
			@DefaultValue("0") @QueryParam("from") long from,
			@DefaultValue("9356225671694") @QueryParam("to") long to
			){
		return new TeamResult(from, to, teamId);
	}
}