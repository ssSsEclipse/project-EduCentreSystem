package com.hkta.educentresystem.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hkta.educentresystem.dto.CustomUserDetails;
import com.hkta.educentresystem.dto.ResponseMessage;
import com.hkta.educentresystem.dto.SimpleCentre;
import com.hkta.educentresystem.dto.SimpleListResponse;
import com.hkta.educentresystem.entity.Centre;
import com.hkta.educentresystem.exception.ResourceNotFoundException;
import com.hkta.educentresystem.service.impl.CentreServiceImpl;
import com.hkta.educentresystem.service.impl.UserServiceImpl;

@Controller
@RequestMapping(value = "/centres")
public class CentreController {

	private static final Logger logger = LoggerFactory.getLogger(CentreController.class);

	private static String VIEW_CENTRES = "centres";

	@Autowired
	private CentreServiceImpl centreService;
	@Autowired
	private UserServiceImpl userService;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getPage() {
		ModelAndView model = new ModelAndView();
		model.setViewName(VIEW_CENTRES);
		return model;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = { "/list" }, method = RequestMethod.GET)
	@ResponseBody
	public Page<Centre> pageCentres(@RequestParam(value = "page") int page, @RequestParam(value = "size") int size) {
		Page<Centre> resultPage = centreService.findAllPaged(page, size);
		if (page > resultPage.getTotalPages()) {
			throw new ResourceNotFoundException();
		}
		return resultPage;
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = { "/simpleList" }, method = RequestMethod.GET)
	@ResponseBody
	public SimpleListResponse<SimpleCentre> listCentres() {
		SimpleListResponse<SimpleCentre> response = new SimpleListResponse<SimpleCentre>();
		List<SimpleCentre> simpleListResult = new ArrayList<SimpleCentre>();
		
		List<Centre> centres = centreService.findAll();
		for (Centre centre: centres) {
			simpleListResult.add(new SimpleCentre(centre.getSchoolName(), centre.getId().toString()));
		}
		Comparator<SimpleCentre> comparator = new Comparator<SimpleCentre>() {
		    @Override
			public int compare(SimpleCentre o1, SimpleCentre o2) {
				if (o1.getName() == null || o2.getName() == null) {
					return -1;
				}
				return o1.getName().compareTo(o2.getName());
			}
		};
		Collections.sort(simpleListResult, comparator);
		response.setResults(simpleListResult);
		response.setSuccess(true);
		return response;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getCentre(@PathVariable("id") Long id) {
		if (!isRequestAllowed(id)) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseMessage("Not allowed to get other Centre"));
		}
		Centre centre = centreService.findOne(id);
		return ResponseEntity.status(HttpStatus.OK).body(centre);
	}

	@RequestMapping(method = RequestMethod.PATCH )
	@ResponseBody
	public ResponseEntity<ResponseMessage> updateCentre(@RequestBody Centre centre) {
		if (!isRequestAllowed(centre.getId())) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseMessage("Not allowed to modify other Centre"));
		}
		Centre updatedCentre = centreService.save(centre);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ResponseMessage(updatedCentre.getId(), Centre.class.getName(), "Centre updated!"));
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE )
	@ResponseBody
	public ResponseEntity<ResponseMessage> deletePerson(@PathVariable("id") Long id) {
		Centre Centre = centreService.findOne(id);
		if (Centre != null) {
			centreService.delete(Centre);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ResponseMessage(id, Centre.class.getName(), "Centre deleted!"));
		}
		return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(new ResponseMessage("Unable to delete Centre"));
	} 

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<ResponseMessage> addCentre(@RequestBody Centre centre) {
		Centre newCentre = centreService.save(centre);
		if (newCentre == null) {
			return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body(new ResponseMessage("Unable to add Centre"));
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage(newCentre.getId(), Centre.class.getName(),  "Centre created!"));
	}
	
	private boolean isRequestAllowed(Long requestingCentreId) {
		CustomUserDetails userDetails = (CustomUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
			return true;
		}else if (userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER"))) {
			Centre userCentre = userService.findOne(userDetails.getId()).getTutorialCentre();
			if (userCentre != null && userCentre.getId() == requestingCentreId) {
				return true;
			}
		}
		return false;
	}

}
