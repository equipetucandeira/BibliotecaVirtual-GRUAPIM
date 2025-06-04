package br.ifsp.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ifsp.library.dto.MostBorrowedDTO;
import br.ifsp.library.repository.ReservationRepository;

@Service
public class ReportService {
	@Autowired
	ReservationRepository reservationRepository;
	
	public List<MostBorrowedDTO> reportLoans(){
	    return reservationRepository.findMostBorrowedBooks();
	}
}
