package pe.edu.upc.meetus.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

import pe.edu.upc.meetus.business.crud.OrganizerService;
import pe.edu.upc.meetus.model.entity.Organizer;

@Named("organizerView")
@ViewScoped
public class OrganizerView implements Serializable {

	private List<Organizer> organizers;
	private Organizer organizerSelected;
	private List<Organizer> organizersSelected;
	private Organizer organizerSearch;

	@Inject
	private OrganizerService organizerService;

	@PostConstruct
	public void init() {
		organizersSelected = new ArrayList<>();
		organizerSearch = new Organizer();
		getAllOrganizer();
	}

	public boolean hasOrganizersSelected() {
		if (organizersSelected.isEmpty()) {
			return false;
		}
		return true;
	}

	public boolean hasOrganizerSelected() {
		if (organizersSelected.size() == 1) {
			return true;
		}
		return false;
	}

	public void createNew() {
		organizerSelected = new Organizer();
	}

	public void editOrganizerSelected() {
		organizerSelected = organizersSelected.get(0);
	}

	public void saveOrganizer() {
		try {
			if (organizerSelected.getId() == null) {
				organizerService.create(organizerSelected);
				organizers.add(organizerSelected);
			} else {
				organizerService.update(organizerSelected);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		PrimeFaces.current().executeScript("PF('organizerDialog').hide()");
		PrimeFaces.current().ajax().update("organizerDataTable");
	}

	public void deleteOrganizer() {
		try {
			this.organizers.remove(organizerSelected);
			organizerService.deleteById(this.organizerSelected.getId());
			this.organizerSelected = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "Remove", "Item Removed"));
	}

	public void searchOrganizer() {
		try {
			organizers = organizerService.findByName(organizerSearch.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getAllOrganizer() {
		try {
			organizers = organizerService.getAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Organizer> getOrganizers() {
		return organizers;
	}

	public void setOrganizerService(OrganizerService organizerService) {
		this.organizerService = organizerService;
	}

	public Organizer getOrganizerSelected() {
		return organizerSelected;
	}

	public void setOrganizerSelected(Organizer organizerSelected) {
		this.organizerSelected = organizerSelected;
	}

	public List<Organizer> getOrganizersSelected() {
		return organizersSelected;
	}

	public void setOrganizersSelected(List<Organizer> organizersSelected) {
		this.organizersSelected = organizersSelected;
	}

	public Organizer getOrganizerSearch() {
		return organizerSearch;
	}

	public void setOrganizerSearch(Organizer organizerSearch) {
		this.organizerSearch = organizerSearch;
	}

}
