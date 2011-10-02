package org.ecommerce.rbac.api.management;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.ecommerce.rbac.dto.Operations;
import org.ecommerce.rbac.dto.Permissions;
import org.ecommerce.rbac.dto.Roles;
import org.ecommerce.rbac.dto.User;
import org.ecommerce.rbac.dto.Users;

/**
Copyright (C) 2001 by Radu Viorel Cosnita

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.*/

/**
 * This is the rbac users manager. Here you can find all required methods
 * that make this implementation fully compliant with RBAC 2.0 standard (from users
 * perspective). Based on this interface you can generate a rest client really easy.
 * 
 * @author Radu Viorel Cosnita
 * @version 1.0
 * @since 01.10.2011
 * @see https://github.com/rcosnita/eCommerce-RBAC/wiki/Rest-api
 */

@Path("/")
@Produces({"application/json"})
public interface RbacUsersManager {
	/**
	 * Method used to obtain all currently defined users.
	 * 
	 * @return
	 */
	@Path("/")
	@GET
	public Users loadAllUsers();
	
	/**
	 * Method used to get an user from RBAC system by id.
	 * 
	 * @param userId
	 * @return
	 */
	@Path("/{userId}")
	@GET
	public User loadUserById(@PathParam("userId") Integer userId);
	
	/**
	 * Method used to load all user roles.
	 * 
	 * @param userId User unique identifier.
	 * @return
	 */
	@Path("/{userId}/roles")
	@GET
	public Roles loadUserRoles(@PathParam("userId") Integer userId);
	
	/**
	 * Method used to load all user permissions. Do not confuse it 
	 * with activated permissions.
	 * 
	 * @param userId User unique identifier.
	 * @return
	 */
	@Path("/{userId}/permissions")
	@GET	
	public Permissions loadUserPermissions(@PathParam("userId") Integer userId);
	
	/**
	 * Method used to load all operations a user is allowed to execute
	 * on a specified object.
	 *  
	 * @param userId User unique identifier.
	 * @param objectId Object unique identifier.
	 * @return
	 */
	@Path("/{userId}/operations/{objectId}")
	@GET	
	public Operations loadUserOperationsForObject(
			@PathParam("userId") Integer userId, 
			@PathParam("objectId") Integer objectId);
	
	/**
	 * Method used to create a new user. 
	 * 
	 * @param userId User unique identifier.
	 * @param user User object.
	 */
	@Path("/{userId}")
	@POST
	@Consumes("application/json")
	public void createUser(@PathParam("userId") Integer userId, User user);
	
	/**
	 * Method used to update an existing user.
	 * 
	 * @param userId User unique identifier.
	 * @param user User instance that contains all new values that should be updated.
	 */
	@Path("/{userId}")
	@PUT
	@Consumes("application/json")	
	public void updateUser(@PathParam("userId") Integer userId, User user);
	
	/**
	 * Method used to delete an existing user.
	 * 
	 * @param userId User unique identifier.
	 */
	@Path("/{userId}")
	@DELETE
	public void deleteUser(Integer userId);
	
	/**
	 * Method used to stop all active sessions of an user.
	 * 
	 * @param userId User unique identifier.
	 */
	@Path("/{userId}/sessions")
	@DELETE
	public void deleteUserSessions(Integer userId);
}