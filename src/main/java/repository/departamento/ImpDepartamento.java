package repository.departamento;

import java.util.List;
import java.util.Optional;

import model.Departamento;

public class ImpDepartamento implements DepartamentoRepository {

	@Override
	public Boolean create(Departamento entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Departamento> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Departamento> findById(Integer id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}
	
	public Optional<Departamento> findByName(String str) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Boolean update(Departamento entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Boolean delete(Departamento entity) {
		// TODO Auto-generated method stub
		return false;
	}

}
