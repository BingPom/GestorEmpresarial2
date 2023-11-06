package model;

import java.util.HashSet;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQueries;
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
@NamedQueries({
	
})
public class Proyecto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String nombre;
	
	@ManyToMany(mappedBy = "proyecto")
	private HashSet<Empleado> empleados;
	
	@Override
	public String toString() {
		List<String> emps = empleados.stream()
				.map(e -> e.getId().toString())
				.sorted()
				.toList();
		return String.format("[%d,%s,%s]", id, nombre, emps);
	}
}
