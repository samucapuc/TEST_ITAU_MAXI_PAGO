package br.com.samuel.controllers;

import java.io.StringWriter;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.google.gson.Gson;

import br.com.samuel.dto.DistanceDTO;
import br.com.samuel.dto.DistanceXML;
import br.com.samuel.enums.TypeMetrics;
import br.com.samuel.enums.TypeOut;
import br.com.samuel.service.CityService;
import br.com.samuel.validations.ValidateEnum;

@Path("/city")
public class CityController {
	@GET
	@Path("/getdistances")
	@Produces(value = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })

	public Response getDistances(
			@ValidateEnum(targetClassType = TypeMetrics.class, required = true, message = "value for metrics is required or invalid, enter KM or MI") @QueryParam("metrics") String metrics,
			@ValidateEnum(targetClassType = TypeOut.class, required = true, message = "value for out is required or invalid, enter XML or JSON") @QueryParam("out") String out) {
		Gson gson = new Gson();
		try {
			CityService cityService = new CityService();
			List<DistanceDTO> list = cityService.getDistance(TypeMetrics.valueOf(metrics));
			return TypeOut.JSON.equals(TypeOut.valueOf(out)) ? Response.ok(gson.toJson(list)).build()
					: Response.ok().type(MediaType.TEXT_XML).entity(genXML(list)).build();
		} catch (JAXBException e) {
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity(gson.toJson(e.getMessage())).build();
		} catch (Exception e) {
			return Response.status(Status.BAD_REQUEST).entity(gson.toJson(e.getMessage())).build();
		}
	}

	private String genXML(List<DistanceDTO> list) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(DistanceXML.class);
		Marshaller m = context.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		StringWriter sw = new StringWriter();
		DistanceXML d = new DistanceXML();
		d.setDistances(list);
		m.marshal(d, sw);
		return sw.toString();
	}

}
