package model;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "jefe", referencedColumnName = "id")
	Empleado jefe;

	@OneToMany(mappedBy = "departamento", fetch = FetchType.EAGER)
	@Builder.Default
	Set<Empleado> empleados = new HashSet<Empleado>();

	/**
	 * Devuelve representación de un departamento
	 * 
	 * @return string
	 */
	public String show() {
		if (id == 0) {
			return "no departamento!!!";
		}

		List<String> emps = empleados.stream().map(e -> e.getNombre()).sorted().toList();

		StringBuilder sb = new StringBuilder();
		sb.append(String.format("%2d : %-20s : empleados -> %s : ", id, nombre, emps));
		if (jefe == null || jefe.getNombre() == null) {
			sb.append("sin jefe!!");
		} else {
			sb.append(String.format("jefe [%2d:%s]", jefe.getId(), jefe.getNombre()));
		}

		return sb.toString();
	}

	public Boolean addEmpleado(Empleado e) {
//		If it has a Departamento, its first removed from that departamento
		if (e.getDepartamento() != this) { // Verifica si el empleado no pertenece ya a este departamento
			if (e.getDepartamento() != null) {
				e.getDepartamento().removeEmpleado(e);
			}
//		Added to this departamento
			e.setDepartamento(this);
			return empleados.add(e);

		}
		return false;

	}

	public Boolean removeEmpleado(Empleado e) {
		if (e.getDepartamento() == this) {
			e.setDepartamento(null);
			return empleados.remove(e);
		}
		return false;
	}

	public void removeAllEmpleados() {
		for (Empleado e : empleados) {
			removeEmpleado(e);
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
