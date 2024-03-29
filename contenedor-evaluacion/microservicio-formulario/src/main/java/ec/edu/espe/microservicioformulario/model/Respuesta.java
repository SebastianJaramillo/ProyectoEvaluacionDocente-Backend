package ec.edu.espe.microservicioformulario.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "respuesta")
public class Respuesta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "res_id")
	private Long id;

	@Column(name = "res_texto")
	private String texto;

	@Column(name = "pre_id")
	private String preId;

	@Column(name = "doc_evaluado")
	private String docEvaluado;

	@Column(name = "eval_id")
	private Long evalId;

	@OneToOne
	@JoinColumn(name = "pre_id", referencedColumnName = "pre_id", insertable = false, updatable = false)
	private Pregunta pregunta;

	public Respuesta() {
	}

	public Respuesta(Long id, String texto, String preId, String docEvaluado, Long evalId, Pregunta pregunta) {
		this.id = id;
		this.texto = texto;
		this.preId = preId;
		this.docEvaluado = docEvaluado;
		this.evalId = evalId;
		this.pregunta = pregunta;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getPreId() {
		return preId;
	}

	public void setPreId(String preId) {
		this.preId = preId;
	}

	public Pregunta getPregunta() {
		return pregunta;
	}

	public void setPregunta(Pregunta pregunta) {
		this.pregunta = pregunta;
	}

	public String getDocEvaluado() {
		return docEvaluado;
	}

	public void setDocEvaluado(String docEvaluado) {
		this.docEvaluado = docEvaluado;
	}

	public Long getEvalId() {
		return evalId;
	}

	public void setEvalId(Long evalId) {
		this.evalId = evalId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Respuesta other = (Respuesta) obj;
		if (id != other.id)
			return false;
		return true;
	}	
}