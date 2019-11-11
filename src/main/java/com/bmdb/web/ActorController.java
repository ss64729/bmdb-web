package com.bmdb.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

import com.bmdb.business.Actor;
import com.bmdb.db.ActorRepository;

@CrossOrigin
@RestController
@RequestMapping("/actors")
public class ActorController {
	@Autowired
	ActorRepository actorRepo;

	// Return all actor with logging
	@GetMapping("/")
	public JsonResponse listActor() {
		JsonResponse jr = null;
		try {
			jr = JsonResponse.getInstance(actorRepo.findAll());

		} catch (Exception e) {
			jr = JsonResponse.getInstance(e);
			e.printStackTrace();
		}
		return jr;
	}

	@GetMapping("/{id}")
	public JsonResponse getActor(@PathVariable int id) {
		JsonResponse jr = null;
		try {
			jr = JsonResponse.getInstance(actorRepo.findById(id));
		} catch (Exception e) {
			jr = JsonResponse.getInstance(e);
			e.printStackTrace();
		}

		return jr;
	}

	@PostMapping("/")
	public JsonResponse addActor(@RequestBody Actor a) {
		JsonResponse jr = null;
		try {
			jr = JsonResponse.getInstance(actorRepo.save(a));
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
	public JsonResponse updateActor(@RequestBody Actor a) {
		JsonResponse jr = null;
		try {
			if (actorRepo.existsById(a.getId())) {
				jr = JsonResponse.getInstance(actorRepo.save(a));
			} else {
				jr = JsonResponse.getInstance("error updating actor id: " + a.getId() + " doesn't exist");
			}
		} catch (Exception e) {
			jr = JsonResponse.getInstance(e);
			e.printStackTrace();
		}

		return jr;
	}

	@DeleteMapping("/{id}")
	public JsonResponse deleteActory(@PathVariable int id) {
		JsonResponse jr = null;
		try {
			if (actorRepo.existsById(id)) {
				actorRepo.deleteById(id);
				jr = JsonResponse.getInstance("Delete successful");
			} else {
				jr = JsonResponse.getInstance("error deleting actor id: " + id + " doesn't exist");
			}
		} catch (DataIntegrityViolationException dive) {
			jr = JsonResponse.getInstance(dive.getRootCause().getMessage());
			dive.printStackTrace();

		} catch (Exception e) {
			jr = JsonResponse.getInstance(e);
			e.printStackTrace();
		}

		return jr;
	}

}
