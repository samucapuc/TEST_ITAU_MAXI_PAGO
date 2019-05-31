package br.com.samuel.handlers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

import com.google.gson.Gson;

import br.com.samuel.exceptions.StandardError;

public class CustomExceptionMapper implements ExceptionMapper<ConstraintViolationException> {
 
    @Override
    public Response toResponse(ConstraintViolationException exception) {
    	Gson gson = new Gson();
        return Response.status(Status.BAD_REQUEST).entity(gson.toJson(prepareListMessage(exception))).build();
    }
    private List<StandardError> prepareListMessage(ConstraintViolationException exception) {
		List<StandardError> listError = new ArrayList<StandardError>();
		for (ConstraintViolation<?> error : exception.getConstraintViolations()) {
			listError.add(new StandardError(System.currentTimeMillis(), Response.Status.BAD_REQUEST.getStatusCode(),
					"Param is required",error.getPropertyPath() + " " + error.getMessage()));
		}
		return listError;
    }
}
