package com.picoloto.projetoviasoft.services;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.picoloto.projetoviasoft.domain.Nfe;
import com.picoloto.projetoviasoft.dto.NfeDTO;
import com.picoloto.projetoviasoft.exceptions.DataIntegrityException;
import com.picoloto.projetoviasoft.exceptions.ObjectNotFoundException;
import com.picoloto.projetoviasoft.repository.NfeRepository;

@Service
@Component
@EnableScheduling
public class NfeService {

	@Scheduled(cron = "0 0/5 * 1/1 * ?")
	public void verificaStatusDoServicoJob() {
		System.out.println("Verificando... " + new Date());

		try {
			Document doc = Jsoup.connect("http://www.nfe.fazenda.gov.br/portal/disponibilidade.aspx").get();
			Element content = doc.getElementById("conteudoDinamico");
			System.out.println(content);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Autowired
	private NfeRepository repo;

	public Nfe find(Integer id) {
		Optional<Nfe> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Nfe.class.getName()));
	}

	public Nfe insert(Nfe obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public Nfe update(Nfe obj) {
		Nfe newObj = find(obj.getId());

		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos");
		}
	}

	public List<Nfe> findAll() {
		return repo.findAll();
	}

	public Nfe fromDTO(NfeDTO objDTO) {
		return new Nfe(objDTO.getId(), objDTO.getName(), objDTO.getEmail());
	}

	private void updateData(Nfe newObj, Nfe obj) {
		newObj.setName(obj.getName());
	}
}
