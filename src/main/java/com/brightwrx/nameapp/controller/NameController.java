package com.brightwrx.nameapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.brightwrx.nameapp.exception.NameNotFoundException;
import com.brightwrx.nameapp.exception.InvalidNameRequestException;
import com.brightwrx.nameapp.model.Name;
import com.brightwrx.nameapp.repository.NameRepository;

/**
 * Name Controller exposes a series of RESTful endpoints to CRUD Names
 */
@RestController
public class NameController {

	@Autowired
	private NameRepository nameRepository;
	
	@RequestMapping(value = "/names", method = RequestMethod.POST)
    public @ResponseBody Name createName(            
            @RequestParam(value="firstName", required=true) String firstName,
            @RequestParam(value="lastName", required=true) String lastName,
            @RequestParam(value="meaning", required=false) String meaning,
            @RequestParam(value="populartiyIndex", required=false) String populartiyIndex) {
			
			Name name = new Name(firstName, lastName, meaning, Integer.parseInt(populartiyIndex));
        	nameRepository.save(name);
            return name;
    }
	
	/**
	 * Get name using id. Returns HTTP 404 if name not found
	 * 
	 * @param nameId
	 * @return retrieved name
	 */
	@RequestMapping(value = "/names/{nameId}", method = RequestMethod.GET)
	public Name getName(@PathVariable("nameId") Long nameId) {
		
		/* validate name Id parameter */
		if (null==nameId) {
			throw new InvalidNameRequestException();
		}
		
		Name name = nameRepository.findOne(nameId);
		
		if(null==name){
			throw new NameNotFoundException();
		}
		
		return name;
	}
	
	/**
	 * Gets all names.
	 *
	 * @return the names
	 */
	@RequestMapping(value = "/names", method = RequestMethod.GET)
	public List<Name> getNames() {
		
		return (List<Name>) nameRepository.findAll();
	}
	
	/**
	 * Deletes the name with given name id if it exists and returns HTTP204.
	 *
	 * @param nameId the name id
	 */
	@RequestMapping(value = "/names/{nameId}", method = RequestMethod.DELETE)
	public void removeName(@PathVariable("nameId") Long nameId, HttpServletResponse httpResponse) {

		if(nameRepository.exists(nameId)){
			Name name = nameRepository.findOne(nameId);
			nameRepository.delete(name);	
		}
		httpResponse.setStatus(HttpStatus.NO_CONTENT.value());
	}
}