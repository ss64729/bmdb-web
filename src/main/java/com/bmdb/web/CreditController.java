package com.bmdb.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

import com.bmdb.business.Credit;
import com.bmdb.db.CreditRepository;

@CrossOrigin
@RestController
@RequestMapping("/credits")
public class CreditController {
	@Autowired
	CreditRepository creditRepo;

	// Return all credit with logging
	@GetMapping("/")
	public JsonResponse listCredits() {
		JsonResponse jr = null;
		try {
			jr = JsonResponse.getInstance(creditRepo.findAll());

		} catch (Exception e) {
			jr = JsonResponse.getInstance(e);
			e.printStackTrace();
		}
		return jr;
	}

	@GetMapping("/{id}")
	public JsonResponse getCredit(@PathVariable int id) {
		JsonResponse jr = null;
		try {
			jr = JsonResponse.getInstance(creditRepo.findById(id));
		} catch (Exception e) {
			jr = JsonResponse.getInstance(e);
			e.printStackTrace();
		}

		return jr;
	}

	@PostMapping("/")
	public JsonResponse addCredit(@RequestBody Credit c) {
		JsonResponse jr = null;
		try {
			jr = JsonResponse.getInstance(creditRepo.save(c));
		} catch (DataIntegrityViolationException dive) {
			jr = JsonResponse.getInstance(dive.getRootCause().getMessage());
			dive.printStackTrace();

		} catch (Exception e) {
			jr = JsonResponse.getInstance(e);
			e.printStackTrace();
		}

		return jr;
	}

	@PutMapping("/")
	public JsonResponse updateCredit(@RequestBody Credit c) {
		JsonResponse jr = null;
		try {
			if (creditRepo.existsById(c.getId())) {
				jr = JsonResponse.getInstance(creditRepo.save(c));
			} else {
				jr = JsonResponse.getInstance("error updating credit id: " + c.getId() + " doesn't exist");
			}
		} catch (Exception e) {
			jr = JsonResponse.getInstance(e);
		}

		return jr;
	}

	@DeleteMapping("/{id}")
	public JsonResponse deleteCredit(@PathVariable int id) {
		JsonResponse jr = null;
		try {
			if (creditRepo.existsById(id)) {
				creditRepo.deleteById(id);
				jr = JsonResponse.getInstance("Delete successful");
			} else {
				jr = JsonResponse.getInstance("error deleting credit id: " + id + " doesn't exist");
			}
		} catch (Exception e) {
			jr = JsonResponse.getInstance(e);
		}

		return jr;
	}

}
