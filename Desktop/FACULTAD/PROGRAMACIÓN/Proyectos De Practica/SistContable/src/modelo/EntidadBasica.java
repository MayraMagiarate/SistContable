package modelo;

import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;

/**
 *
 * @author MAYFER
 */
@MappedSuperclass// clase padre de las entidades
@Inheritance(strategy = InheritanceType.JOINED)// joined: copia los atributos del padre=redundancia
// singletable: 1 tabla con todos los atributos de las demas
// txclass:crea 2 tablas unidas por id 
public abstract class EntidadBasica implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;//como Primary key

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public EntidadBasica() {
    }

}
