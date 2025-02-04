package com.services.Implementations;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entities.Discussion;
import com.entities.Messa;
import com.entities.enums.LastSender;
import com.repositories.DiscussionRepository;
import com.repositories.MessaRepository;
import com.services.Interfaces.MessaService;
@Service
public class MessaServiceImp implements MessaService {
	@Autowired
	MessaRepository RepoMessa;
	@Autowired
	DiscussionRepository RepoDisc;

	@Override
	public String SendMessage(String refdisc, String sender, String messagecontent) {
		System.out.println("refdisc : "+refdisc+" sender : "+sender+" message : "+messagecontent);
		Discussion discMessages = RepoDisc.findById(refdisc).orElse(null);
		String[] recepient = refdisc.split("_");
		boolean side = recepient[0].compareTo(sender) == 0;
		discMessages.setLastSender(side ? LastSender.Destinataire : LastSender.Source);
		discMessages.setVue_disc(false);
		Messa message = new Messa();
		try {
			messagecontent = com.Utils.MessagerieUtils.filterbadwords(messagecontent);
		} catch (FileNotFoundException e) {
			System.out.println("fichier peut pas le lire");
			e.printStackTrace();
		}
		message.setContenu_msg(messagecontent);
		message.setSender(side ? recepient[0] : recepient[1]);
		if (discMessages.getMessages() == null) {
			List<Messa> messages = new ArrayList<Messa>();
			messages.add(message);
			discMessages.setMessages(messages);
		} else
			discMessages.getMessages().add(message);
		RepoDisc.save(discMessages);
		return messagecontent;
	}

	@Override
	public boolean filterMessage(String mess) {
		// EnumUtils.isValidEnum(MotInterdits.class, mess);
		return true;
	}

}
