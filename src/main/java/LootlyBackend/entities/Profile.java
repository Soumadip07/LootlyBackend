package LootlyBackend.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="profile")
@Getter
@Setter
@NoArgsConstructor
public class Profile {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY) 
	    @Column(name = "profile_id")  
	    private Long profileId; 

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    private String contactNumber;
    
    
    @Column(name = "profile_image")  
    private String profileImage;

}
