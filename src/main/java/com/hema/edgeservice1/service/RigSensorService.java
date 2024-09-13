package com.hema.edgeservice1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hema.edgeservice1.model.RigSensor;
import com.hema.edgeservice1.repo.RigSensorRepo;


@Service
public class RigSensorService {

	@Autowired
	private RigSensorRepo rigSensorRepo;
	
	public RigSensor addRigSensor(RigSensor rigSensor) {
		
		return rigSensorRepo.save(rigSensor);
	}


	public List<RigSensor> getAllRigSensor() {
		
		List<RigSensor> result=rigSensorRepo.findAll();
		return result;
	}
	
	public RigSensor getRigSensorById(int id)
	{
		return rigSensorRepo.findById(id).orElse(null);
		
	}
	
	public RigSensor deleteRigSensor(int id)
	{
		RigSensor rigsensor=rigSensorRepo.findById(id).orElse(null);
		if(rigsensor!=null)
		{
			rigSensorRepo.deleteById(id);
		}
		
		return null;
	}


}
