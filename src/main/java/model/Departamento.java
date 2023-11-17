package model;

import java.util.HashSet;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Departamento")
@NamedQuery(name = "Departamento.findAll", query = "SELECT d FROM Departamento d")
@NamedQuery(name = "Departamento.findByJefeId", query = "SELECT d FROM Departamento d WHERE d.jefe.id = ?1")
@NamedQuery(name = "Departamento.findByName", query = "SELECT d FROM Departamento d WHERE d.nombre LIKE :nombre")
public class Departamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	String nombre;

	@MapsId
	@OneToOne(cascade = CascadeType.ALL)
	Empleado jefe;

	@OneToMany(mappedBy = "departamento")
	HashSet<Empleado> empleados;

	/**
	 * Devuelve representación de un departamento
	 * 
	 * @return string
	 */
	public String show() {
		if (id == 0) {
			return "no departamento!!!";
		}

		StringBuilder sb = new StringBuilder();
		sb.append(String.format("%2d:%-20s:", id, nombre));
		if (jefe == null || jefe.getNombre() == null) {
			sb.append("sin jefe!!");
		} else {
			sb.append(String.format("jefe [%2d:%s]", jefe.getId(), jefe.getNombre()));
		}

		return sb.toString();
	}
	
	public Boolean addEmpleado(Empleado e) {
//		If it has a Departamento, its first removed from that departamento
		if (e.getDepartamento() != null) {
			e.getDepartamento().getEmpleados().remove(e);
		}
//		Added to this departamento
		e.setDepartamento(this);
		return empleados.add(e);
	}

	public Boolean removeEmpleado(Empleado e) {
		e.setDepartamento(null);
		return empleados.remove(e);
	}
	
}
