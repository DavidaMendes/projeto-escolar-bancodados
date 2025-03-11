package davidamendes.springboot;

import davidamendes.springboot.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import davidamendes.springboot.model.Aluno;


@Component
public class MetodosAluno {
    @Autowired
    private AlunoRepository repository;

    Aluno aluno = new Aluno();

    public Aluno findById(Integer id){
        if(id != null){
            return repository.findById(id).get();
        } else {
            return null;
        }

    }

    public void saveAluno(String nome, String sobrenome, String ultimoNome, String email, String cpf, String telefone, String idade){
        Aluno newAluno = new Aluno();
        newAluno.setNome(nome);
        newAluno.setSobrenome(sobrenome);
        newAluno.setUltimoNome(ultimoNome);
        newAluno.setEmail(email);
        newAluno.setCpf(cpf);
        newAluno.setTelefone(telefone);
        newAluno.setIdade(idade);
        repository.save(newAluno);

        System.out.println(newAluno);
    }

    public void removeAluno(Integer id){
        repository.deleteById(id);
    }

    public void editAluno(int idAluno, int resposta, String informacao){
        Aluno aluno = repository.findById(idAluno).get();

        switch (resposta) {
            case 1:
                aluno.setNome(informacao);
                break;
            case 2:
                aluno.setSobrenome(informacao);
                break;
            case 3:
                aluno.setUltimoNome(informacao);
                break;
            case 4:
                aluno.setEmail(informacao);
                break;
            case 5:
                aluno.setCpf(informacao);
                break;
            case 6:
                aluno.setTelefone(informacao);
                break;
            case 7:
                aluno.setIdade(informacao);
                break;
            default:
                System.out.println("Opção inválida.");
                return;
        }

        repository.save(aluno);
        System.out.println("Aluno editado com sucesso!");
    }


}
