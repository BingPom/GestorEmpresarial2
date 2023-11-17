package model;

import java.util.HashSet;
import java.util.List;

import jakarta.persistence.Entity;
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

	@ManyToMany(mappedBy = "proyectos")
	private HashSet<Empleado> empleados;

	@Override
	public String toString() {
		List<String> emps = empleados.stream().map(e -> e.getId().toString()).sorted().toList();
		return String.format("[%d,%s,%s]", id, nombre, emps);
	}
	
	public Boolean addEmpleado(Empleado e) {
		if (this.getEmpleados().contains(e) || e.getProyectos().contains(this)) {
			return false;
		}
		return this.empleados.add(e) && e.getProyectos().add(this);
	}
	
	public Boolean removeEmpleado(Empleado e) {
		if (this.getEmpleados().contains(e) || e.getProyectos().contains(this)) {
			return false;
		}
		return this.empleados.remove(e) && e.getProyectos().remove(this);
	}
	
}
