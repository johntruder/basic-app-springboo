package org.demo.myapp.rest.services.commons;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;

/**
 * Abstract class for REST services <br>
 * 
 * @author xxx
 *
 * @param <ENTITY>
 * @param <DTO>
 */
public abstract class GenericService<ENTITY, DTO> {

	private final ModelMapper mapper = new ModelMapper();

	private final Class<ENTITY> entityClass;
	private final Class<DTO> dtoClass;

	/**
	 * Constructor
	 * 
	 * @param entityClass
	 * @param dtoClass
	 */
	protected GenericService(Class<ENTITY> entityClass, Class<DTO> dtoClass) {
		super();
		this.entityClass = entityClass;
		this.dtoClass = dtoClass;
	}

	/**
	 * Converts DTO to JPA ENTITY
	 * 
	 * @param dto
	 * @return
	 */
	protected ENTITY dtoToEntity(DTO dto) {
		return mapper.map(dto, entityClass);
		// return BookMapper.getInstance().dtoToEntity(dto);
	}

	/**
	 * Converts JPA ENTITY to DTO
	 * 
	 * @param entity
	 * @return
	 */
	protected DTO entityToDto(ENTITY entity) {
		return mapper.map(entity, dtoClass);
		// return BookMapper.getInstance().entityToDto(entity);
	}

	/**
	 * Converts an Optional JPA ENTITY to DTO
	 * 
	 * @param optionalEntity
	 * @return the DTO or null if nothing in optional
	 */
	protected DTO entityToDto(Optional<ENTITY> optionalEntity) {
		if (optionalEntity.isPresent()) {
			return entityToDto(optionalEntity.get());
		} else {
			return null;
		}
	}

	/**
	 * Converts a collection of JPA ENTITIES to a collection of DTO
	 * 
	 * @param entities
	 * @return
	 */
	protected List<DTO> entityListToDtoList(Iterable<ENTITY> entities) {
		List<DTO> dtoList = new LinkedList<>();
		if (entities != null) {
			for (ENTITY e : entities) {
				dtoList.add(entityToDto(e));
			}
		}
		return dtoList;
	}
}
