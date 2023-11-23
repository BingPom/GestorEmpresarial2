package model;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Proyecto")
@NamedQuery(name = "Proyecto.findAll", query = "SELECT p FROM Proyecto p")
@NamedQuery(name = "Proyecto.findByName", query = "SELECT p FROM Proyecto p WHERE p.nombre LIKE :nombre")
public class Proyecto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nombre;

	@ManyToMany(mappedBy = "proyectos", fetch = FetchType.EAGER)
	@Builder.Default
	private Set<Empleado> empleados = new HashSet<Empleado>();

	@Override
	public String toString() {
		List<String> emps = empleados.stream().map(e -> e.getId().toString()).sorted().toList();
		return String.format("[%d,%s,%s]", id, nombre, emps);
	}

	/**
	 * Devuelve representación de un proyecto
	 * 
	 * @return string
	 */
	public String show() {
		if (id == 0) {
			return "no proyecto!!!";
		}

		StringBuilder sb = new StringBuilder();
		sb.append(String.format("%2d : %-20s : empleados -> ", id, nombre));

		for (Empleado e : empleados) {
			sb.append(String.format("[%2d : %s] ", e.getId(), e.getNombre()));
		}

		return sb.toString();
	}

	public Boolean addEmpleado(Empleado e) {
		if (!this.getEmpleados().contains(e) || !e.getProyectos().contains(this)) {
			if (this.getEmpleados().add(e)) {
				e.getProyectos().add(this);
				return true;
			}
		}
		return false;
	}

	public Boolean removeEmpleado(Empleado e) {
		if (!this.getEmpleados().contains(e) || !e.getProyectos().contains(this)) {
			return false;
		}
		return this.empleados.remove(e) && e.getProyectos().remove(this);
	}
	
	public void removeAllEmpleados() {
		for (Empleado e : empleados) {
			e.removeProyecto(this);
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Proyecto other = (Proyecto) obj;
		return Objects.equals(id, other.id);
	}

}
