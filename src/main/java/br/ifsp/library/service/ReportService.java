package br.ifsp.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ifsp.library.dto.LibUseDTO;
import br.ifsp.library.dto.MostBorrowedDTO;
import br.ifsp.library.repository.ReservationRepository;

@Service
public class ReportService {
	@Autowired
	ReservationRepository reservationRepository;
	
	public List<MostBorrowedDTO> reportLoans(){
	    return reservationRepository.findMostBorrowedBooks();
	}
	
	public LibUseDTO reportLibUse(){
	    LibUseDTO dto = new LibUseDTO();
	    dto.setTotalLoans(reservationRepository.count());
	    dto.setTotalUsers(reservationRepository.countDistinctUsers());
	    MostBorrowedDTO mostBorrowed = reservationRepository.findMostBorrowedBooks().stream().findFirst().orElse(null);
	    dto.setMostLoans(mostBorrowed);
	    return dto;
	}
}
