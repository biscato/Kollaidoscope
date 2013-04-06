package com.biscato.kollaidoscope.rest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.biscato.kollaidoscope.model.User;
import com.biscato.kollaidoscope.persistence.UserDAO;

@Path("/users")
public class UserResource {
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<User> getUsers(@QueryParam("teamid") long teamId) {
		UserDAO userDAO = new UserDAO();
		ArrayList<User> userList = (ArrayList<User>)userDAO.getAllEntities();
		Collection<User> usersToRemove = new ArrayList<User>();
		try{
			Long id = new Long(teamId); //helper to check if teamId has been provided -> TODO:check if checkable by JAXRS spec
			if(id != null){
				long[] memberInTeam;
				boolean found;
				for(int i=0; i<userList.size(); i++){
					found = false;
					memberInTeam = userList.get(i).getMemberInTeam(); 
					for(long teamIdOfUser : memberInTeam){
						if(teamIdOfUser == teamId){
							found = true;
						}
					}
					if(found == false){
						usersToRemove.add(userList.get(i));
					}
					
				}
			}
		}catch(Exception e){
			System.out.println("Error when executing filter logic for teamid: "+teamId);
		}
		if(usersToRemove.size() >0){
			userList.remove(usersToRemove);
		}
 		return userList;
	}

	@GET
	@Path("{id}")
	public User getUserForId(@PathParam("id") long id) {
		UserDAO userDAO = new UserDAO();
		//TODO: Error handling: a) wrong id b) no id (e.g. character instead of int) c) other error
		return userDAO.getEntityForId(id);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public User createUser(User user){
		//TODO: Error handling
		UserDAO usr = new UserDAO();
		return usr.createEntity(user);
	}
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	//TODO: return boolean for success or failure
	public void deleteAllUsers(){
		UserDAO usr = new UserDAO();
		usr.deleteAllEntities();
	}
}
