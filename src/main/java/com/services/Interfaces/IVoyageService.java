package com.services.Interfaces;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.entities.Voyage;

public interface IVoyageService {
	String ajoutVoyage(Voyage voyage);
	String suppVoyage(int id);
	String modifierVoyage(int id, Voyage voyage);
	List<Voyage> getAllVoyage();
	List<Voyage> findByDestination(String destination);
	String addEmployeeToVoyage(Long id,int idVoyage);
	List<Voyage> findVoyageFromdate(String month,int year);
	StringBuffer getpricefromdate(String month,int year,Long idEntreprise);
	StringBuffer getprice(Long idEntreprise);

}
