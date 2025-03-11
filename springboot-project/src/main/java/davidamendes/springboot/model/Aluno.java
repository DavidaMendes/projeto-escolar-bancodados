package davidamendes.springboot.model;

import jakarta.persistence.*;

@Entity
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "aluno_id")
    private Integer id;
    @Column(length =  50, nullable = false)
    private String nome;
    @Column(length =  50, nullable = false)
    private String sobrenome;
    @Column(length =  100, nullable = false)
    private String ultimoNome;
    @Column(length =  100, nullable = false)
    private String email;
    @Column(length =  100, nullable = false)
    private String cpf;
    @Column(length =  100, nullable = false)
    private String telefone;
    @Column(length =  100, nullable = false)
    private String idade;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getUltimoNome() {
        return ultimoNome;
    }

    public void setUltimoNome(String ultimoNome) {
        this.ultimoNome = ultimoNome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    @Override
    public String toString() {
        return "Aluno: \n" +
                "Matricula: " + id + "\n" +
                "Nome: " + nome + " " + sobrenome + " " + ultimoNome + "\n" +
                "Email: " + email + "\n" +
                "CPF: " + cpf + "\n" +
                "Telefone: " + telefone + "\n" +
                "Idade: " + idade
                ;
    }
}
