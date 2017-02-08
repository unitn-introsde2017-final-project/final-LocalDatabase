package introsde.resources;

import introsde.model.*;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.PersistenceUnit;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import introsde.model.*;

@Stateless // will work only inside a Java EE application
@LocalBean // will work only inside a Java EE application
@Path("/steps")
public class StepResource {
	// Allows to insert contextual objects into the class,
    // e.g. ServletContext, Request, Response, UriInfo
    @Context
    UriInfo uriInfo;
    @Context
    Request request;

    // will work only inside a Java EE application
    @PersistenceUnit(unitName="final-LocalDatabase")
    EntityManager entityManager;

    // will work only inside a Java EE application
    @PersistenceContext(unitName = "final-LocalDatabase",type=PersistenceContextType.TRANSACTION)
    private EntityManagerFactory entityManagerFactory;
    
 	@GET
 	@Path("{stepId}")
 	@Produces({MediaType.APPLICATION_JSON})
 	public Step getStep(@PathParam("stepId") int id) {
 		System.out.println("Getting step for id: " + id + "...");
 		Step step = Step.getStepById(id);
 		if (step == null)
 			throw new RuntimeException("Get: Step with " + id + " not found");
 		return step;
 	}

 	@PUT
 	@Consumes({MediaType.APPLICATION_JSON })
 	public Response putStep(Step step) {
 		//System.out.println("--> Updating Step... " + this.id);
 		System.out.println("--> Step gotten: "+step.toString());
 		System.out.println("--> Step gotten: "+step.getDate());
 		System.out.println("--> Step gotten: "+step.getId());

 		Response res;;
 		
 		if (step.getId() == 0) { // Create step
 			res = Response.noContent().build();
 			Step.saveStep(step);
 		} else { // Modify step
 			res = Response.created(uriInfo.getAbsolutePath()).build();
 			Step.updateStep(step);
 		}

 		return res;
 	}
}
