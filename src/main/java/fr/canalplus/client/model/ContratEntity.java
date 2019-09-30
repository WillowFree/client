package fr.canalplus.client.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "t_contrat")
public class ContratEntity {

    public ContratEntity() {
    }

    public ContratEntity(String libelle) {
        this.libelle = libelle;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "libelle", nullable = false, unique = true)
    private String libelle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", referencedColumnName = "id", nullable = false)
    private ClientEntity clientEntity;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "adresse_id", referencedColumnName = "id", nullable = false)
    private AdresseEntity adresseEntity;

    public ContratEntity setClientEntity(ClientEntity clientEntity) {
        this.clientEntity = clientEntity;
        return this;
    }

    public AdresseEntity getAdresseEntity() {
        return adresseEntity;
    }

    public ContratEntity setAdresseEntity(AdresseEntity adresseEntity) {
        this.adresseEntity = adresseEntity;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContratEntity that = (ContratEntity) o;
        return Objects.equals(libelle, that.libelle) &&
                Objects.equals(clientEntity, that.clientEntity);
    }

    @Override
    public int hashCode() {

        return Objects.hash(libelle, clientEntity);
    }

    @Override
    public String toString() {
        return "ContratEntity{" +
                "id=" + id +
                ", libelle='" + libelle + '\'' +
                ", clientEntity=" + clientEntity +
                '}';
    }
}
