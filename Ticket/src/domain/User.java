package domain;

public class User {
    protected int user_id;
    protected String name;
    protected String dni;
    protected String email;
    protected String rol;

    public User(int user_id, String name, String dni, String email, String rol) {
        setUser_id(user_id);
        setName(name);
        setDni(dni);
        setEmail(email);
        setRol(rol);
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "ID: "+user_id+"\nName: "+name+"\nDNI: "+dni+"\nEmail: "+email+"\nRol: "+rol;
    }
}
