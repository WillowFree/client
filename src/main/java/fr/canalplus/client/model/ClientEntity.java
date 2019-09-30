package fr.canalplus.client.model;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(
        name = "t_client",
        uniqueConstraints = @UniqueConstraint(columnNames = {"nom", "prenom", "dateDeNaissance"})
)
public class ClientEntity {

    public ClientEntity() {
    }

    public ClientEntity(String nom, String prenom, OffsetDateTime dateDeNaissance) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateDeNaissance = dateDeNaissance;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nom", nullable = false)
    private String nom;

    @Column(name = "prenom", nullable = false)
    private String prenom;

    @Column(name = "dateDeNaissance", nullable = false)
    private OffsetDateTime dateDeNaissance;

    @OneToMany(mappedBy = "clientEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<AdresseEntity> adresses = new HashSet<>();

    @OneToMany(mappedBy = "clientEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ContratEntity> contrats = new HashSet<>();

    public ClientEntity setId(long id) {
        this.id = id;
        return this;
    }

    public long getId() {
        return id;
    }

    public ClientEntity addAdresse(AdresseEntity adresseEntity) {
        adresses.add(adresseEntity);
        adresseEntity.setClientEntity(this);
        return this;
    }

    public AdresseEntity getAdressePrincipale() {
        return adresses.stream().filter(adresseEntity -> TypeAdresse.PRINCIPAL.equals(adresseEntity.getTypeAdresse())).findFirst().get();
    }

    public ClientEntity addContrat(ContratEntity contratEntity) {
        contrats.add(contratEntity);
        contratEntity.setClientEntity(this);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientEntity client = (ClientEntity) o;
        return Objects.equals(nom, client.nom) &&
                Objects.equals(prenom, client.prenom) &&
                Objects.equals(dateDeNaissance, client.dateDeNaissance);
    }

    @Override
    public int hashCode() {

        return Objects.hash(nom, prenom, dateDeNaissance);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", dateDeNaissance=" + dateDeNaissance +
                ", adresses=" + adresses +
                '}';
    }
}
