package com.hkta.educentresystem.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hkta.educentresystem.dto.CustomUserDetails;
import com.hkta.educentresystem.dto.ResponseMessage;
import com.hkta.educentresystem.dto.SimpleCentre;
import com.hkta.educentresystem.dto.SimpleListResponse;
import com.hkta.educentresystem.entity.Centre;
import com.hkta.educentresystem.entity.User;
import com.hkta.educentresystem.exception.ResourceNotFoundException;
import com.hkta.educentresystem.service.CentreService;
import com.hkta.educentresystem.service.UserService;

@Controller
@RequestMapping(value = "/centres")
public class CentreController {

	private static final Logger logger = LoggerFactory.getLogger(CentreController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private CentreService centreService;
	
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

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getCentre(@PathVariable("id") Long id) {
		Centre centre = centreService.findOne(id);
		return ResponseEntity.status(HttpStatus.OK).body(centre);
	}
	
	@RequestMapping(value="/document/{id}", method = RequestMethod.GET)
	public ResponseEntity<InputStreamResource> download(@PathVariable("id") Long id) {
		Centre centre = centreService.findOne(id);
		
		if (centre != null && centre.getDiscountComissionPdf() != null) {
			InputStreamResource inputStreamResource = new InputStreamResource(new ByteArrayInputStream(centre.getDiscountComissionPdf()));
			return ResponseEntity.status(HttpStatus.OK).header("Content-Disposition", "attachment; filename=\""+centre.getDiscountComissionPdfFileName()+"\"").contentType(MediaType.APPLICATION_OCTET_STREAM).body(inputStreamResource);
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getCentreByUserId() {
		CustomUserDetails userDetails = (CustomUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userService.findOne(userDetails.getId());
		
		if (!centreService.isRequestAllowed(user.getTutorialCentre() == null ? null : user.getTutorialCentre().getId())) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseMessage("views.centre.response.message.error.get.notallowed"));
		}
		return ResponseEntity.status(HttpStatus.OK).body(user.getTutorialCentre());
	}

	@RequestMapping(value="/update", method = RequestMethod.POST, consumes = {"multipart/form-data"} )
	@ResponseBody
	public ResponseEntity<ResponseMessage> update(@RequestPart(name="logo", required=false) MultipartFile logo, @RequestPart(name="discountComission", required=false) MultipartFile discountComission, @RequestPart("centre") Centre centre) throws IOException {
		if (logo != null) {
			centre.setLogo(logo.getBytes());
		}else {
			Centre originalCentre = centreService.findOne(centre.getId());
			centre.setLogo(originalCentre.getLogo());
		}
		if (discountComission != null) {
			centre.setDiscountComissionPdf(discountComission.getBytes());
		}else {
			Centre originalCentre = centreService.findOne(centre.getId());
			centre.setDiscountComissionPdf(originalCentre.getDiscountComissionPdf());
		}
		if (!centreService.isRequestAllowed(centre.getId())) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseMessage("views.centre.response.message.error.update.notallowed"));
		}
		Centre updatedCentre = centreService.save(centre);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ResponseMessage(updatedCentre.getId(), Centre.class.getSimpleName(), "views.centre.response.message.success.update"));
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE )
	@ResponseBody
	public ResponseEntity<ResponseMessage> delete(@PathVariable("id") Long id) {
		Centre Centre = centreService.findOne(id);
		if (Centre != null) {
			centreService.delete(Centre);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ResponseMessage(id, Centre.class.getSimpleName(), "views.centre.response.message.success.delete"));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage("views.centre.response.message.delete.create"));
	} 

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.POST, consumes = {"multipart/form-data"})
	@ResponseBody
	public ResponseEntity<ResponseMessage> add(@RequestPart(name="logo", required=false) MultipartFile logo, @RequestPart(name="discountComission", required=false) MultipartFile discountComission, @RequestPart("centre") Centre centre) throws IOException {
		if (logo != null) {
			centre.setLogo(logo.getBytes());
		}
		if (discountComission != null) {
			centre.setDiscountComissionPdf(discountComission.getBytes());
		}
		Centre newCentre = centreService.save(centre);
		if (newCentre == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage("views.centre.response.message.error.create"));
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage(newCentre.getId(), Centre.class.getSimpleName(),  "views.centre.response.message.success.create"));
	}

}
