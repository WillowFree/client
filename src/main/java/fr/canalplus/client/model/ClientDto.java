package fr.canalplus.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientDto {

    private String canal;

    private long id;

    @NotNull
    private String nom;

    @NotNull
    private String prenom;

    @NotNull
    private OffsetDateTime dateDeNaissance;

    private long adresseId;

    @NotNull
    private String libelleAdresse;

    private String codePostaleAdresse;

    private String villeAdresse;

    private Pays paysAdresse;

    private TypeAdresse typeAdresse;

    public String getCanal() {
        return canal;
    }

    public ClientDto setCanal(String canal) {
        this.canal = canal;
        return this;
    }

    public long getId() {
        return id;
    }

    public ClientDto setId(long id) {
        this.id = id;
        return this;
    }

    public String getNom() {
        return nom;
    }

    public ClientDto setNom(String nom) {
        this.nom = nom;
        return this;
    }

    public String getPrenom() {
        return prenom;
    }

    public ClientDto setPrenom(String prenom) {
        this.prenom = prenom;
        return this;
    }

    public OffsetDateTime getDateDeNaissance() {
        return dateDeNaissance;
    }

    public ClientDto setDateDeNaissance(OffsetDateTime dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
        return this;
    }

    public long getAdresseId() {
        return adresseId;
    }

    public ClientDto setAdresseId(long adresseId) {
        this.adresseId = adresseId;
        return this;
    }

    public String getLibelleAdresse() {
        return libelleAdresse;
    }

    public ClientDto setLibelleAdresse(String libelleAdresse) {
        this.libelleAdresse = libelleAdresse;
        return this;
    }

    public String getCodePostaleAdresse() {
        return codePostaleAdresse;
    }

    public ClientDto setCodePostaleAdresse(String codePostaleAdresse) {
        this.codePostaleAdresse = codePostaleAdresse;
        return this;
    }

    public String getVilleAdresse() {
        return villeAdresse;
    }

    public ClientDto setVilleAdresse(String villeAdresse) {
        this.villeAdresse = villeAdresse;
        return this;
    }

    public Pays getPaysAdresse() {
        return paysAdresse;
    }

    public ClientDto setPaysAdresse(Pays paysAdresse) {
        this.paysAdresse = paysAdresse;
        return this;
    }

    public TypeAdresse getTypeAdresse() {
        return typeAdresse;
    }

    public ClientDto setTypeAdresse(TypeAdresse typeAdresse) {
        this.typeAdresse = typeAdresse;
        return this;
    }

    @Override
    public String toString() {
        return "ClientDto{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", dateDeNaissance=" + dateDeNaissance +
                ", libelleAdresse='" + libelleAdresse + '\'' +
                ", codePostaleAdresse='" + codePostaleAdresse + '\'' +
                ", villeAdresse='" + villeAdresse + '\'' +
                ", paysAdresse=" + paysAdresse +
                ", typeAdresse=" + typeAdresse +
                '}';
    }
}
