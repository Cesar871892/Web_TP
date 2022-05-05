package pe.edu.upc.meetus.model.repository;

import java.util.List;

import pe.edu.upc.meetus.model.entity.Organizer;

public interface OrganizerRepository extends JpaRepository<Organizer, Integer>{
List<Organizer> findByName(String name) throws Exception;
}
