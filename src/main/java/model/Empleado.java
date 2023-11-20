package model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
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
@Table(name = "Empleado")
@NamedQuery(name = "Empleado.findAll", query = "FROM Empleado")
@NamedQuery(name = "Empleado.findByDepartamentoId", query = "SELECT e FROM Empleado e WHERE e.departamento.id = :id")
@NamedQuery(name = "Empleado.findByProyectoId", query = "SELECT e FROM Empleado e WHERE :id IN (SELECT p.id FROM e.proyectos p)")
@NamedQuery(name = "Empleado.findByName", query = "SELECT e FROM Empleado e WHERE e.nombre LIKE :nombre")
public class Empleado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	String nombre;
	Double salario;

	@ManyToOne()
	@JoinColumn(name = "departamento")
	Departamento departamento;

	@ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	@Builder.Default
	Set<Proyecto> proyectos = new HashSet<Proyecto>();

	/**
	 * Devuelve representación de un empleado
	 * 
	 * @return string
	 */
	public String show() {
		if (id == 0) {
			return "no empleado!!!";
		}

		StringBuilder sb = new StringBuilder();

		sb.append(String.format("%2d:%20s:%4.2f:", id, nombre, salario));
		sb.append(":");
		if (departamento == null || departamento.getNombre() == null) {
			sb.append("sin departamento!!");
		} else {
			sb.append(String.format("Departamento [%2d:%s]", departamento.getId(), departamento.getNombre()));
		}

		return sb.toString();
	}

	public Boolean addProyecto(Proyecto p) {
		if (p.getEmpleados().contains(this) || this.getProyectos().contains(p)) {
			return false;
		}
		return this.proyectos.add(p) && p.getEmpleados().add(this);
	}

	public Boolean removeEmpleado(Proyecto p) {
		if (p.getEmpleados().contains(this) || this.getProyectos().contains(p)) {
			return false;
		}
		return this.proyectos.remove(p) && p.getEmpleados().remove(this);
	}

}
