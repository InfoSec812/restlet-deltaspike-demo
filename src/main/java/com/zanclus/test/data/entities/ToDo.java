package com.zanclus.test.data.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Builder;

/**
 *
 * @author <a href="">Deven Phillips</a>
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="todo")
@XmlRootElement(name="todo")
public class ToDo implements Serializable {

    @Id
    @XmlAttribute(required = true)
    private Long id;

    @XmlElement(required = true)
    private String title;

    @XmlElement(nillable = true)
    private String description;

    @XmlElement(required = false)
    private Date due;

    @XmlAttribute(required = true)
    private Boolean completed = Boolean.FALSE;
}
