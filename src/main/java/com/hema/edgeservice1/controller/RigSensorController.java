package com.hema.edgeservice1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hema.edgeservice1.model.RigSensor;
import com.hema.edgeservice1.service.RigSensorService;



@RestController
@RequestMapping("/api/Rig")
public class RigSensorController {
	
	@Autowired
	private RigSensorService rigSensorService;
	
	@PostMapping("")
    public RigSensor add(@RequestBody RigSensor RigSensor) {
//        LOGGER.info("Department add: {}", RigSensor);
       RigSensor rigsensor=rigSensorService.addRigSensor(RigSensor);
       return rigsensor ;
    }
	
	@GetMapping("/{id}")
	public RigSensor getById(@PathVariable int id)
	{
		return rigSensorService.getRigSensorById(id);
	}
	
	@GetMapping("")
	public List<RigSensor> getAllRigSensor()
	{
		return rigSensorService.getAllRigSensor();
	}
	
	@DeleteMapping("/{id}")
	public String deleteRigSensor(@PathVariable int id)
	{
		rigSensorService.deleteRigSensor(id);
		return "SensorId "+id+" Deleted";
		
	}
	

}
