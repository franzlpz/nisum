package com.nisum.api.models;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "phones")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Phone implements Serializable {
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	private String id;
	@Column(nullable = false)
	private int number;
	@Column(name ="city_code", length = 2, nullable = false)
	private String cityCode;
	@Column(name ="country_code",length = 3, nullable = false)
	private String countryCode;
	@Column(name ="active",columnDefinition = "boolean default false")
	private boolean isActive;

	@JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
	@ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private User user;
}
