package com.nisum.prueba.models;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User implements Serializable {
    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;
    @Column(length = 200, nullable = false)
    private String name;
    @Column(length = 100, unique = true, nullable = false)
    private String email;
    @Column(length = 90, nullable = false)
    private String password;
    @Temporal(TemporalType.DATE)
    @Column(name = "created_at", nullable = false)
    private Date created;
    @Temporal(TemporalType.DATE)
    @Column(name = "modified_at")
    private Date modified;
    @Temporal(TemporalType.DATE)
    private Date lastLogin;
    @Column(columnDefinition = "boolean default false")
    private boolean isActive;

}
