package pe.edu.upc.meetus.business.crud;

import java.util.List;

import pe.edu.upc.meetus.model.entity.Organizer;

public interface OrganizerService extends CrudService<Organizer, Integer>{
List<Organizer> findByName(String name) throws Exception;
}
