package entities;

import io.github.homberghp.gensquared_annotations.ID;
import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author Pieter van den Hombergh {@code p.vandehombergh@gmail.com}
 */
public class Tutor {

    @ID String  abreviation;
    String firstname;
    String lastname;
    String tussenvoegsel;
    String academicTitle;
    LocalDate dob;
    String gender;
    String teaches;

    public Tutor( String abreviation, String firstname, String lastname, String tussenvoegsel,
            String academicTitle, LocalDate dob, String gender,
            String teaches ) {
        this.abreviation= abreviation;
        this.firstname = firstname;
        this.lastname = lastname;
        this.tussenvoegsel = tussenvoegsel;
        this.academicTitle = academicTitle;
        this.dob = dob;
        this.gender = gender;
        this.teaches = teaches;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname( String firstname ) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname( String lastname ) {
        this.lastname = lastname;
    }

    public String getTussenvoegsel() {
        return tussenvoegsel;
    }

    public void setTussenvoegsel( String tussenvoegsel ) {
        this.tussenvoegsel = tussenvoegsel;
    }

    public String getAcademicTitle() {
        return academicTitle;
    }

    public void setAcademicTitle( String academicTitle ) {
        this.academicTitle = academicTitle;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob( LocalDate dob ) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender( String gender ) {
        this.gender = gender;
    }

    public String getTeaches() {
        return teaches;
    }

    public void setTeaches( String teaches ) {
        this.teaches = teaches;
    }

    public String getAbreviation() {
        return abreviation;
    }

    @Override
    public String toString() {
        return "Tutor{" + "abreviation=" + abreviation + ", firstname=" +
                firstname + ", lastname=" + lastname + ", tussenvoegsel=" +
                tussenvoegsel + ", academicTitle=" + academicTitle + ", dob=" +
                dob + ", gender=" + gender + ", teaches=" + teaches + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode( this.abreviation );
        hash = 53 * hash + Objects.hashCode( this.firstname );
        hash = 53 * hash + Objects.hashCode( this.lastname );
        hash = 53 * hash + Objects.hashCode( this.tussenvoegsel );
        hash = 53 * hash + Objects.hashCode( this.academicTitle );
        hash = 53 * hash + Objects.hashCode( this.dob );
        hash = 53 * hash + Objects.hashCode( this.gender );
        hash = 53 * hash + Objects.hashCode( this.teaches );
        return hash;
    }

    @Override
    public boolean equals( Object obj ) {
        if ( this == obj ) {
            return true;
        }
        if ( obj == null ) {
            return false;
        }
        if ( getClass() != obj.getClass() ) {
            return false;
        }
        final Tutor other = (Tutor) obj;
        if ( !Objects.equals( this.abreviation, other.abreviation ) ) {
            return false;
        }
        if ( !Objects.equals( this.firstname, other.firstname ) ) {
            return false;
        }
        if ( !Objects.equals( this.lastname, other.lastname ) ) {
            return false;
        }
        if ( !Objects.equals( this.tussenvoegsel, other.tussenvoegsel ) ) {
            return false;
        }
        if ( !Objects.equals( this.academicTitle, other.academicTitle ) ) {
            return false;
        }
        if ( !Objects.equals( this.gender, other.gender ) ) {
            return false;
        }
        if ( !Objects.equals( this.teaches, other.teaches ) ) {
            return false;
        }
        if ( !Objects.equals( this.dob, other.dob ) ) {
            return false;
        }
        return true;
    }


}
